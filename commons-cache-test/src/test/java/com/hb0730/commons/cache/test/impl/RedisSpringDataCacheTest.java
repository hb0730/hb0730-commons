package com.hb0730.commons.cache.test.impl;

import com.hb0730.commons.cache.impl.AbstractCache;
import com.hb0730.commons.cache.impl.remote.RedisSpringDataCache;
import com.hb0730.commons.cache.support.redis.springdata.RedisSpringDataCacheConfig;
import com.hb0730.commons.cache.support.serial.GlobalSerializeMap;
import com.hb0730.commons.cache.support.serial.impl.Jackson2JsonCacheWrapperSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void putTimout2() {
        config.setConnectionFactory(connectionFactory);
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(config);
        cache.putIfAbsent("test", "test2", 200L, TimeUnit.SECONDS);
    }
}
