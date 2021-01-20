package com.hb0730.commons.cache.support.serial.impl;

import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.support.serial.Serializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class JdkCacheSerializerTest {
    private Serializer<String> jdkCacheSerializer;

    @Before
    public void before() throws Exception {
        jdkCacheSerializer = (Serializer) new JdkCacheSerializer(true);
    }

    @Test
    public void serializeTest() {
        CacheWrapper<String> testData = new CacheWrapper<String>();
        testData.setData("测试test");
        byte[] serialize = jdkCacheSerializer.serialize(testData);

    }

    @Test
    public void deserializeTest() {
        CacheWrapper<String> testData = new CacheWrapper<String>();
        testData.setData("测试test");
        byte[] serialize = jdkCacheSerializer.serialize(testData);
        CacheWrapper<String> deserialize = jdkCacheSerializer.deserialize(serialize);
        String data = deserialize.getData();
        Assert.assertNotNull("data为空", data);
    }
}
