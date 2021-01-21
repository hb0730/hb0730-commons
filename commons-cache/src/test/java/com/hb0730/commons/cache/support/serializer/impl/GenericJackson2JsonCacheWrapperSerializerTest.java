package com.hb0730.commons.cache.support.serializer.impl;

import com.google.common.collect.Lists;
import com.hb0730.commons.cache.BaseTest;
import com.hb0730.commons.cache.CacheWrapper;
import org.junit.Test;

import java.util.List;

public class GenericJackson2JsonCacheWrapperSerializerTest extends BaseTest {

    @Test
    public void serializeTest() {
        CacheWrapper<Object> testData = new CacheWrapper<>();
        testData.setData("冲冲冲");
        GenericJackson2JsonCacheWrapperSerializer serializer = new GenericJackson2JsonCacheWrapperSerializer(true);
        byte[] serialize = serializer.serialize(testData);
    }

    @Test
    public void deserializeTest() {
        CacheWrapper<Object> testData = new CacheWrapper<>();
        testData.setData("冲冲冲");
        GenericJackson2JsonCacheWrapperSerializer serializer = new GenericJackson2JsonCacheWrapperSerializer(true);
        byte[] serialize = serializer.serialize(testData);
        CacheWrapper<Object> deserialize = serializer.deserialize(serialize);

    }

    @Test
    public void javaTypeTest() {
        CacheWrapper<Object> testData = new CacheWrapper<>();
        List<DataClass<String>> listData = Lists.newArrayList();
        listData.add(new DataClass<>("测试1"));
        listData.add(new DataClass<>("测试2"));
        listData.add(new DataClass<>("测试3"));
        testData.setData(listData);
        GenericJackson2JsonCacheWrapperSerializer serializer = new GenericJackson2JsonCacheWrapperSerializer(true);
        byte[] serialize = serializer.serialize(testData);
        CacheWrapper<Object> deserialize = serializer.deserialize(serialize);
        List<DataClass<String>> resultDataTest = (List<DataClass<String>>) deserialize.getData();
    }
}
