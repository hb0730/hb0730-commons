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
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * in-memory cache store(内存存储)
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class InMemoryCacheStore<K, V> extends AbstractCache<K, V> {
    public static final Logger LOGGER = LoggerFactory.getLogger(InMemoryCacheStore.class);
    /**
     * Cleaner schedule period. (ms)
     */
    private final static long PERIOD = 5;

    /**
     * Cache container.
     */
    private final Map<K, CacheWrapper<V>> cache_container = new ConcurrentHashMap<>();
    /**
     * Lock.
     */
    private final Lock lock = new ReentrantLock();

    /**
     * 正在执行的定时任务
     */
    public InMemoryCacheStore() {
    }

    @Nonnull
    @Override
    public Optional<CacheWrapper<V>> getInternal(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be blank");
        return Optional.ofNullable(cache_container.get(key));
    }

    @Override
    public void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        // Put the cache wrapper
        CacheWrapper<V> wrapper = cache_container.put(key, cacheWrapper);
        LOGGER.debug("put[{}] cache result:[{}],original  cache wrapper: [{}]", key, wrapper, cacheWrapper);
    }

    @Override
    public Boolean putInternalIfAbsent(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");

        lock.lock();
        try {
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
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void delete(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be blank");
        cache_container.remove(key);
        LOGGER.debug("removed key [{}]", key);
    }


    @PreDestroy
    public void preDestroy() {
        clear();
    }

    private void clear() {
        cache_container.clear();
    }


    private class CacheExpiryCleaner extends TimerTask {

        @Override
        public void run() {
            cache_container.keySet().forEach(key -> {
                if (InMemoryCacheStore.this.get(key).isPresent()) {
                    LOGGER.debug("Deleted the cache: [{}] for expiration", key);
                }
            });
        }
    }

}
