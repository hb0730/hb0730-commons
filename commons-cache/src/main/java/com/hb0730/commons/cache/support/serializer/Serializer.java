package com.hb0730.commons.cache.support.serializer;

import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.exception.SerializationException;

import javax.annotation.Nullable;

/**
 * 序列化 接口
 *
 * @author bing_huang
 * @see org.springframework.data.redis.serializer
 * @since 1.0.0
 */
public interface Serializer<T> {
    byte[] EMPTY_ARRAY = new byte[0];

    /**
     * 序列化
     *
     * @param obj 序列化对象
     * @return 二进制
     * @throws SerializationException 序列化异常
     */
    @Nullable
    byte[] serialize(@Nullable CacheWrapper<T> obj) throws SerializationException;


    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @return 序列化对象
     * @throws SerializationException 反序列化异常
     */
    @Nullable
    CacheWrapper<T> deserialize(@Nullable byte[] bytes) throws SerializationException;

}
