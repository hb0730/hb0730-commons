package com.hb0730.commons.cache.impl.remote;

import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.exception.CacheException;
import com.hb0730.commons.cache.support.redis.jedis.JedisCacheConfig;
import com.hb0730.commons.cache.support.redis.jedis.JedisProperties;
import com.hb0730.commons.cache.support.serial.Serializer;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;

/**
 * jedis pool cache
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class JedisPoolCache<K, V> extends AbstractRemoteCache<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisPoolCache.class);
    private final JedisProperties properties;
    private final Serializer serializer;
    @Getter
    private volatile static JedisPool JEDIS_POOL;

    public JedisPoolCache(JedisCacheConfig<K, V> config) {
        super(config);
        this.properties = config.getProperties();
        this.serializer = config.getSerializer();
        if (Objects.isNull(this.properties)) {
            throw new CacheException("jet properties must be not null");
        }
        initRedis();
    }


    private void initRedis() {
        JedisPoolConfig config = new JedisPoolConfig();
        if (null != this.properties.getMaxActive()) {
            config.setMaxTotal(this.properties.getMaxActive());
        }
        if (null != this.properties.getMaxIdle()) {
            config.setMaxIdle(this.properties.getMaxIdle());
        }
        if (null != this.properties.getMaxWaitMillis()) {
            config.setMaxWaitMillis(this.properties.getMaxWaitMillis());
        }
        if (null != this.properties.getMinIdle()) {
            config.setMinIdle(this.properties.getMinIdle());
        }
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);
        JEDIS_POOL = new JedisPool(config,
                this.properties.getHost(),
                this.properties.getPort(),
                this.properties.getTimeout(),
                this.properties.getPassword(),
                this.properties.getDatabase());
    }

    protected JedisPool getJedis() {
        if (JEDIS_POOL == null) {
            synchronized (JedisPoolConfig.class) {
                if (JEDIS_POOL != null) {
                    return JEDIS_POOL;
                }
                initRedis();
                return JEDIS_POOL;
            }
        }
        return JEDIS_POOL;
    }


    @Nonnull
    @Override
    @SuppressWarnings({"unchecked"})
    protected Optional<CacheWrapper<V>> getInternal(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be null");
        try {
            byte[] newKey = buildKey(key);
            try (Jedis jedis = JEDIS_POOL.getResource()) {
                byte[] resultBytes = jedis.get(newKey);
                if (resultBytes != null) {
                    CacheWrapper<V> result = (CacheWrapper<V>) serializer.deserialize(resultBytes);
                    LOGGER.debug("get success key:[{}],result:[{}]", key, result);
                    return Optional.ofNullable(result);
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            LOGGER.error("get error key [{}], message:[{}]", key, e.getMessage());
            throw new CacheException("get cache error:" + e.getMessage());
        }
    }

    @Override
    protected void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        try {
            byte[] keyByte = buildKey(key);
            try (Jedis jedis = JEDIS_POOL.getResource()) {
                byte[] valueBytes = serializer.serialize(cacheWrapper);
                long expireAt = expireTimeMs(cacheWrapper.getCreateAt(), cacheWrapper.getExpireAt());
                jedis.psetex(keyByte, expireAt, valueBytes);
            }
        } catch (Exception e) {
            LOGGER.error("put cache error message:[{}]", e.getMessage(), e);
            throw new CacheException("put cache error :" + e.getMessage());
        }
    }

    @Override
    protected Boolean putInternalIfAbsent(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        try {
            byte[] keyByte = buildKey(key);
            try (Jedis jedis = JEDIS_POOL.getResource()) {
                long expire = expireTimeMs(cacheWrapper.getCreateAt(), cacheWrapper.getExpireAt());
                SetParams params = new SetParams();
                params.nx().px(expire);
                byte[] valueBytes = serializer.serialize(cacheWrapper);
                jedis.set(keyByte, valueBytes, params);
                return true;
            }

        } catch (Exception e) {
            LOGGER.error("put_if_absent cache error message:[{}]", e.getMessage(), e);
            throw new CacheException("put ifAbsent error :" + e.getMessage());
        }
    }

    @Override
    public void delete(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be null");
        try {
            byte[] bytes = buildKey(key);
            try (Jedis jedis = JEDIS_POOL.getResource()) {
                jedis.del(bytes);
                LOGGER.debug("delete cache success key [{}]", key);
            }

        } catch (Exception e) {
            LOGGER.error("delete error message:[{}]", e.getMessage(), e);
            throw new CacheException("delete cache error :" + e.getMessage());
        }
    }
}
