package com.hb0730.commons.cache.test.impl;

import com.hb0730.commons.cache.impl.AbstractCache;
import com.hb0730.commons.cache.impl.remote.RedisSpringDataCache;
import com.hb0730.commons.cache.support.redis.springdata.RedisSpringDataCacheConfig;
import com.hb0730.commons.cache.support.serial.GlobalSerializeMap;
import com.hb0730.commons.cache.support.serial.impl.Jackson2JsonCacheWrapperSerializer;
import com.hb0730.commons.lang.collection.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author bing_huang
 * @date 2020/07/31 10:32
 * @since V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class RedisSpringDataCacheTest {
    @Autowired
    private RedisConnectionFactory connectionFactory;
    private static RedisSpringDataCacheConfig<String, String> config = new RedisSpringDataCacheConfig<>();
    private static final boolean useJson = true;

    static {
        if (useJson) {
            GlobalSerializeMap.register();
            config.setSerializer(GlobalSerializeMap.get(Jackson2JsonCacheWrapperSerializer.IDENTITY_NUMBER));

        }
    }

    @Test
    public void put() {
        config.setConnectionFactory(connectionFactory);
        AbstractCache<String, String> cache = new RedisSpringDataCache<>(config);
        cache.put("test", "test");
    }

    @Test
    public void get() {

        config.setConnectionFactory(connectionFactory);
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(config);
        Optional<String> test = cache.get("test");
        System.out.println(test.orElseGet(() -> "为空"));
    }

    @Test
    public void getByKeys() {
        config.setConnectionFactory(connectionFactory);
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(config);
        cache.put("key1", "测试1");
        cache.put("key2", "测试2", 30, TimeUnit.MINUTES);
        cache.put("key3", "测试3", 10, TimeUnit.MINUTES);
        cache.put("key4", "测试4");
        List<String> keys = Arrays.asList("key1", "key2", "key3", "key4");
        Optional<List<String>> strings = cache.get(CollectionUtils.newHashSet(keys));
        Assert.assertNotNull(strings);
    }

    @Test
    public void putTimout() {
        config.setConnectionFactory(connectionFactory);
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(config);
        cache.putIfAbsent("test", "test2", 200L, TimeUnit.SECONDS);
    }

    @Test
    public void delete() {
        config.setConnectionFactory(connectionFactory);
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(config);
        cache.delete("test");
    }

    @Test
    public void deleteByKeys() {
        config.setConnectionFactory(connectionFactory);
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(config);
        List<String> keys = Arrays.asList("key1", "key2", "key3", "key4");
        cache.delete(CollectionUtils.newHashSet(keys));
    }

    @Test
    public void putTimout2() {
        config.setConnectionFactory(connectionFactory);
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(config);
        cache.putIfAbsent("test", "test2", 200L, TimeUnit.SECONDS);
    }

    @Test
    public void putIfAbsentTimeOut() throws InterruptedException {
        config.setConnectionFactory(connectionFactory);
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(config);
        cache.put("test", "test", 10, TimeUnit.SECONDS);
        cache.putIfAbsent("test", "test2", 10, TimeUnit.SECONDS);
        System.out.println(cache.get("test").orElseGet(() -> "为空"));
        Thread.sleep(20 * 1000);
        cache.putIfAbsent("test", "test2", 1, TimeUnit.HOURS);
        System.out.println(cache.get("test").orElseGet(() -> "为空"));
    }
}
