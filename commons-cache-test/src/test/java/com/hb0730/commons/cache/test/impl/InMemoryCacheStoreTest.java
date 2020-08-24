package com.hb0730.commons.cache.test.impl;

import com.hb0730.commons.cache.Cache;
import com.hb0730.commons.cache.impl.local.InMemoryCacheStore;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Optional;
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

    @Test
    public void test() throws InterruptedException {
        cache = new InMemoryCacheStore<>(new HashMap<>());
        // 测试写锁
        testWrite(cache);

        // 测试读锁
        testRead(cache);
    }

    private static void testWrite(Cache<String, String> cache) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cache.putIfAbsent("test1", "value1", 5000, TimeUnit.MILLISECONDS);
            }
        }, "Write Thread -1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cache.putIfAbsent("test2", "value2", 5000, TimeUnit.MILLISECONDS);
            }
        }, "Write Thread -2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cache.putIfAbsent("test3", "value3", 5000, TimeUnit.MILLISECONDS);
            }
        }, "Write Thread -3").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cache.putIfAbsent("test3", "value3", 5000, TimeUnit.MILLISECONDS);
            }
        }, "Write Thread -3").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cache.putIfAbsent("test1", "value3", 5000, TimeUnit.MILLISECONDS);
            }
        }, "Write Thread -4").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cache.putIfAbsent("test2", "value3", 5000, TimeUnit.MILLISECONDS);
            }
        }, "Write Thread -5").start();
    }

    private static void testRead(Cache<String, String> cache) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Optional<String> test1 = cache.get("test1");
                log.info("cahce [{}]", test1.orElseGet(() -> "为空"));
            }
        }, "Read Thread - 1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Optional<String> test1 = cache.get("test1");
                log.info("cahce [{}]", test1.orElseGet(() -> "为空"));
            }
        }, "Read Thread - 2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Optional<String> test1 = cache.get("test1");
                log.info("cahce [{}]", test1.orElseGet(() -> "为空"));
            }
        }, "Read Thread - 2").start();
    }
}
