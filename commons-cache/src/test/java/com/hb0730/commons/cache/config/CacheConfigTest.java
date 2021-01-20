package com.hb0730.commons.cache.config;

import com.hb0730.commons.cache.support.serializer.Serializer;
import org.junit.Test;

public class CacheConfigTest {

    @Test
    public void serializerTest() {
        CacheConfig<String, String> config = new CacheConfig<>();
        Serializer<String> serializer = config.getSerializer();
    }
}
