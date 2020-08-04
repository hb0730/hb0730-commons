package com.hb0730.commons.cache.test.impl;

import com.hb0730.commons.cache.impl.remote.JedisPoolCache;
import com.hb0730.commons.cache.support.redis.jedis.JedisCacheConfig;
import com.hb0730.commons.cache.support.redis.jedis.JedisProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author bing_huang
 * @date 2020/08/04 9:51
 * @since V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class JedisPoolCacheTest {
    private JedisPoolCache<String, String> cache;

    @Before
    public void init() {
        JedisProperties properties = new JedisProperties();
        JedisCacheConfig<String, String> cacheConfig = new JedisCacheConfig<>();
        cacheConfig.setProperties(properties);
        cache = new JedisPoolCache<>(cacheConfig);
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
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        cache.put("test", "test", 10, TimeUnit.SECONDS);
        cache.putIfAbsent("test", "test2", 10, TimeUnit.SECONDS);
        System.out.println(cache.get("test").orElseGet(() -> "为空"));
        Thread.sleep(20 * 1000);
        cache.putIfAbsent("test", "test2", 1, TimeUnit.HOURS);
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
