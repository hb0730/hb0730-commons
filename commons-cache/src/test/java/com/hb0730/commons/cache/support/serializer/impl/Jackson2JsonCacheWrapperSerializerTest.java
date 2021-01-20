package com.hb0730.commons.cache.support.serializer.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Lists;
import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.support.serializer.Serializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

public class Jackson2JsonCacheWrapperSerializerTest {
    Serializer<String> jackson2CacheSerializer = null;

    @Before
    public void setUp() throws Exception {
        jackson2CacheSerializer = new Jackson2JsonCacheWrapperSerializer<>(true, String.class);
    }

    @Test
    public void serializeTest() {
        CacheWrapper<String> testData = new CacheWrapper<>();
        testData.setData("冲冲冲");
        byte[] serialize = jackson2CacheSerializer.serialize(testData);
    }

    @Test
    public void deserializeTest() {
        CacheWrapper<String> testData = new CacheWrapper<>();
        testData.setData("冲冲冲");
        byte[] serialize = jackson2CacheSerializer.serialize(testData);
        CacheWrapper<String> deserialize = jackson2CacheSerializer.deserialize(serialize);
        String data = deserialize.getData();
        Assert.assertNotNull("序列化失败", data);
    }

    @Test
    public void javaTypeTest() {
        CacheWrapper<List<DataClass<String>>> testData = new CacheWrapper<>();
        List<DataClass<String>> listData = Lists.newArrayList();
        listData.add(new DataClass<>("测试1"));
        listData.add(new DataClass<>("测试2"));
        listData.add(new DataClass<>("测试3"));
        testData.setData(listData);
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(DataClass.class, String.class);
        JavaType javaType1 = TypeFactory.defaultInstance().constructParametricType(List.class, javaType);
        Serializer<List<DataClass<String>>> serializer = new Jackson2JsonCacheWrapperSerializer<>(true, javaType1);
        byte[] serialize = serializer.serialize(testData);
        CacheWrapper<List<DataClass<String>>> deserialize = serializer.deserialize(serialize);
        List<DataClass<String>> data = deserialize.getData();
        Assert.assertNotNull("序列化失败", data);
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataClass<T> implements Serializable {
        private T data;
    }
}
