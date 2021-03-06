package com.hb0730.commons.cache.config;

import com.hb0730.commons.cache.support.serializer.Serializer;
import com.hb0730.commons.cache.support.serializer.impl.JdkCacheSerializer;
import lombok.Data;

import java.util.function.Function;

/**
 * cache configuration
 *
 * @author bing_huang
 * @since 1.0.0
 */
@Data
public class CacheConfig<K, V> {
    /**
     * key 转换器
     */
    private Function<K, V> keyConverter;
    /**
     * 序列化
     */
    @SuppressWarnings("rawtypes")
    private Serializer serializer = new JdkCacheSerializer(true);
}
