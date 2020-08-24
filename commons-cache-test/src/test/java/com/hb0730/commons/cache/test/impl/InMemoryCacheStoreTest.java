package com.hb0730.commons.cache.test.impl;

import com.hb0730.commons.cache.Cache;
import com.hb0730.commons.cache.impl.local.InMemoryCacheStore;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author bing_huang
 * @date 2020/07/31 10:30
 * @since V1.0
 */
@Slf4j
public class InMemoryCacheStoreTest {
    Cache<String, String> cache = null;

    @Before
    public void init() {
        cache = new InMemoryCacheStore<>();
    }

    @Test
    public void put() {
        cache.put("test", "test");
        Assert.assertNotNull(cache);
        log.info(cache.get("test").orElseGet(() -> "为空"));
    }

    @Test
    public void putTimeout() throws InterruptedException {
        Assert.assertNotNull(cache);
        cache.put("test", "test", 5000, TimeUnit.MILLISECONDS);
        log.info(cache.get("test").orElseGet(() -> "为空"));
        Thread.sleep(6000L);
        log.info("sleep:" + cache.get("test").orElseGet(() -> "为空"));
    }

    @Test
    public void putIfAbsentTimeOut() throws InterruptedException {
        Assert.assertNotNull(cache);
        cache.put("test", "test", 5000, TimeUnit.MILLISECONDS);
        cache.putIfAbsent("test", "test2", 5000, TimeUnit.MILLISECONDS);
        log.info(cache.get("test").orElseGet(() -> "为空"));
        Thread.sleep(6000L);
        cache.putIfAbsent("test", "test2", 5000, TimeUnit.MILLISECONDS);
        log.info(cache.get("test").orElseGet(() -> "为空"));
    }

    @Test
    public void delete() {
        Assert.assertNotNull(cache);
        cache.put("test", "test");
        log.info(cache.get("test").orElseGet(() -> "为空"));
        cache.delete("test");
        log.info(cache.get("test").orElseGet(() -> "为空"));
    }
}
