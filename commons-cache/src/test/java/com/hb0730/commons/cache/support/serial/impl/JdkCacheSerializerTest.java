package com.hb0730.commons.cache.support.serial.impl;

import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.support.serial.Serializer;
import com.hb0730.commons.lang.date.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class JdkCacheSerializerTest {
    public Serializer serializer;

    @Before
    public void init() {
        serializer = JdkCacheSerializer.INSTANCE;
    }

    @Test
    public void serializeAndDeserializeTest() throws Exception {
        CacheWrapper<String> wrapper = new CacheWrapper<>();
        wrapper.setCreateAt(new Date());
        Date date = DateUtils.addHours(wrapper.getCreateAt(), 1);
        wrapper.setExpireAt(date);
        System.out.println(wrapper);
        byte[] serialize = serializer.serialize(wrapper);
        wrapper = null;
        wrapper = (CacheWrapper<String>) serializer.deserialize(serialize);
        System.out.println(wrapper);
    }
}
