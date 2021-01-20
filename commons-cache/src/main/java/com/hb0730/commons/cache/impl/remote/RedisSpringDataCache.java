package com.hb0730.commons.cache.impl.remote;

import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.exception.CacheException;
import com.hb0730.commons.cache.support.redis.springdata.RedisSpringDataCacheConfig;
import com.hb0730.commons.cache.support.serializer.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.*;

/**
 * spring redis impl
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class RedisSpringDataCache<K, V> extends AbstractRemoteCache<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSpringDataCache.class);
    private final RedisConnectionFactory connectionFactory;
    private final Serializer<V> serializer;

    public RedisSpringDataCache(RedisSpringDataCacheConfig<K, V> config) {
        super(config);
        this.connectionFactory = config.getConnectionFactory();
        this.serializer = config.getSerializer();
        Assert.notNull(connectionFactory, "connectionFactory is required");
    }

    @Nonnull
    @Override
    protected Optional<CacheWrapper<V>> getInternal(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be null");
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[] newKey = buildKey(key);
            byte[] resultBytes = connection.get(newKey);
            if (resultBytes != null) {
                CacheWrapper<V> result = serializer.deserialize(resultBytes);
                LOGGER.debug("get success key:[{}],result:[{}]", key, result);
                return Optional.ofNullable(result);
            }
            return Optional.empty();
        } catch (Exception e) {
            LOGGER.error("get cache error key [{}], message:[{}]", key, e.getMessage());
            throw new CacheException("get cache error", e);
        } finally {
            closeConnection(connection);
        }
    }

    @Nonnull
    @Override
    protected Optional<Map<K, CacheWrapper<V>>> getInternal(@Nonnull Set<K> keys) {
        Assert.notEmpty(keys, "Cache key must not be null");
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            ArrayList<K> keyList = new ArrayList<>(keys);
            byte[][] newKeys = keyList.stream().map((k) -> {
                try {
                    return buildKey(k);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new CacheException("build key error");
                }
            }).toArray(byte[][]::new);
            Map<K, CacheWrapper<V>> resultMap = new HashMap<>();
            if (newKeys.length > 0) {
                List<byte[]> mgetResults = connection.mGet(newKeys);
                for (int i = 0; i < (mgetResults != null ? mgetResults.size() : 0); i++) {
                    byte[] bytes = mgetResults.get(i);
                    K k = keyList.get(i);
                    CacheWrapper<V> result = serializer.deserialize(bytes);
                    resultMap.put(k, result);
                }
                return Optional.of(resultMap);
            }
            return Optional.empty();
        } catch (Exception e) {
            LOGGER.error("get cache error key [{}], message:[{}]", keys, e.getMessage());
            throw new CacheException("get cache error", e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    protected void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[] keyByte = buildKey(key);
            byte[] valueBytes = serializer.serialize(cacheWrapper);
            assert valueBytes != null;
            long expireAt = expireTimeMs(cacheWrapper.getCreateAt(), cacheWrapper.getExpireAt());
            connection.pSetEx(keyByte, expireAt, valueBytes);
            LOGGER.debug("put success then key [{}]", key);
        } catch (Exception e) {
            LOGGER.error("put cache error message:[{}]", e.getMessage(), e);
            throw new CacheException("put cache error", e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    protected Boolean putInternalIfAbsent(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[] newkey = buildKey(key);
            byte[] valueBytes = serializer.serialize(cacheWrapper);
            assert valueBytes != null;
            long expireAt = expireTimeMs(cacheWrapper.getCreateAt(), cacheWrapper.getExpireAt());
            Boolean result = connection.set(newkey, valueBytes,
                    Expiration.milliseconds(expireAt),
                    RedisStringCommands.SetOption.ifAbsent());
            LOGGER.debug("put_is_absent success,then key [{}] result:[{}]", key, result);
            return result;
        } catch (Exception e) {
            LOGGER.error("put_if_absent cache error message:[{}]", e.getMessage(), e);
            throw new CacheException("put ifAbsent error", e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be null");
        RedisConnection connection = null;
        try {
            byte[] keyByte = buildKey(key);
            connection = connectionFactory.getConnection();
            connection.del(keyByte);
            LOGGER.debug("delete success then key [{}]", key);
        } catch (Exception e) {
            LOGGER.error("delete error message:[{}]", e.getMessage(), e);
            throw new CacheException("delete cache error", e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(@Nonnull Set<K> keys) {
        Assert.notEmpty(keys, "Cache key must not be null");
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[][] newKeys = keys.stream().map((k) -> {
                try {
                    return buildKey(k);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new CacheException("build key error", e);
                }
            }).toArray((len) -> new byte[keys.size()][]);
            connection.del(newKeys);
            LOGGER.debug("delete success then key [{}]", keys);
        } catch (Exception e) {
            LOGGER.error("delete error message:[{}]", e.getMessage(), e);
            throw new CacheException("delete cache error", e);
        } finally {
            closeConnection(connection);
        }
    }

    private void closeConnection(RedisConnection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            LOGGER.error("RedisConnection close fail: {}, {}", e.getMessage(), e.getClass().getName());
        }
    }
}
