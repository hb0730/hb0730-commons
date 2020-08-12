package com.hb0730.commons.cache.support.serial;

import javax.annotation.Nullable;

/**
 * 序列化 接口
 *
 * @author bing_huang
 * @see org.springframework.data.redis.serializer
 * @since 1.0.0
 */
public interface Serializer {
    byte[] EMPTY_ARRAY = new byte[0];

    /**
     * 序列化
     *
     * @param obj 序列化对象
     * @return 二进制
     * @throws Exception 序列号异常
     */
    @Nullable
    byte[] serialize(@Nullable Object obj) throws Exception;


    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @return 序列化对象
     * @throws Exception 反序列化异常
     */
    @Nullable
    Object deserialize(@Nullable byte[] bytes) throws Exception;

}
