package com.hb0730.commons.cache.impl.local;

import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.impl.AbstractCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * in-memory cache store(内存存储)
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class InMemoryCacheStore<K, V> extends AbstractCache<K, V> {
    public static final Logger LOGGER = LoggerFactory.getLogger(InMemoryCacheStore.class);
    /**
     * Cache container.
     */
    private Map<K, CacheWrapper<V>> cacheContainer;
    /**
     * Lock.
     */
    private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock(true);
    private final Lock writeLock = cacheLock.writeLock();
    private final Lock readLock = cacheLock.readLock();

    /**
     * 构造函数,内部使用{@link ConcurrentHashMap}
     */
    public InMemoryCacheStore() {
        cacheContainer = new ConcurrentHashMap<>();
    }

    /**
     * 构造函数，设置缓冲区
     *
     * @param cacheMap 缓冲，可以为空,如果为空则使用{@link ConcurrentHashMap}
     * @since 2.0.0
     */
    public InMemoryCacheStore(Map<K, CacheWrapper<V>> cacheMap) {
        if (null == cacheMap) {
            cacheContainer = new ConcurrentHashMap<>();
        } else {
            cacheContainer = cacheMap;
        }
    }

    @Nonnull
    @Override
    public Optional<CacheWrapper<V>> getInternal(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be blank");
        readLock.lock();
        try {
            return Optional.ofNullable(cacheContainer.get(key));
        } finally {
            readLock.unlock();
        }

    }

    @Override
    public void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        // Put the cache wrapper
        writeLock.lock();
        try {
            CacheWrapper<V> wrapper = cacheContainer.put(key, cacheWrapper);
            LOGGER.debug("put[{}] cache result:[{}],original  cache wrapper: [{}]", key, wrapper, cacheWrapper);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Boolean putInternalIfAbsent(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        // Get the value before
        Optional<V> valueOptional = get(key);

        // key存在
        if (valueOptional.isPresent()) {
            LOGGER.warn("Failed to put the cache, because the key: [{}] has been present already", key);
            return false;
        }

        // Put the cache wrapper
        putInternal(key, cacheWrapper);
        return true;
    }

    @Override
    public void delete(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be blank");
        writeLock.lock();
        try {
            cacheContainer.remove(key);
            LOGGER.debug("removed key [{}]", key);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    @PreDestroy
    public void clearCache() {
        clear();
    }

    private void clear() {
        cacheContainer.clear();
    }

}
