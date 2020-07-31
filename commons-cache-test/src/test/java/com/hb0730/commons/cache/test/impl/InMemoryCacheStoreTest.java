package com.hb0730.commons.cache.test.impl;

import com.hb0730.commons.cache.Cache;
import com.hb0730.commons.cache.impl.local.InMemoryCacheStore;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author bing_huang
 * @date 2020/07/31 10:30
 * @since V1.0
 */
public class InMemoryCacheStoreTest {
    Cache<String, String> cache = null;

    @Before
    public void init() {
        cache = new InMemoryCacheStore<>();
    }

    @Test
    public void put() {
        cache.put("test", "test");
        System.out.println(cache.get("test").orElseGet(() -> "为空"));
    }

    @Test
    public void putTimeout() throws InterruptedException {
        cache.put("test", "test", 5000, TimeUnit.MILLISECONDS);
        System.out.println(cache.get("test").orElseGet(() -> "为空"));
        Thread.sleep(6000L);
        System.out.println("sleep:" + cache.get("test").orElseGet(() -> "为空"));
    }

    @Test
    public void putIfAbsentTimeOut() throws InterruptedException {
        cache.put("test", "test", 5000, TimeUnit.MILLISECONDS);
        cache.putIfAbsent("test", "test2", 5000, TimeUnit.MILLISECONDS);
        System.out.println(cache.get("test").orElseGet(() -> "为空"));
        Thread.sleep(6000L);
        cache.putIfAbsent("test", "test2", 5000, TimeUnit.MILLISECONDS);
        System.out.println(cache.get("test").orElseGet(() -> "为空"));
    }

    @Test
    public void delete() {
        cache.put("test", "test");
        System.out.println(cache.get("test").orElseGet(() -> "为空"));
        cache.delete("test");
        System.out.println(cache.get("test").orElseGet(() -> "为空"));
    }
}
