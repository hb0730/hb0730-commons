package com.hb0730.commons.cache.support.serializer.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.exception.SerializationException;
import com.hb0730.commons.cache.support.serializer.AbstractSerializer;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;

/**
 * jackson 序列化与反序列化
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class Jackson2JsonCacheWrapperSerializer<T> extends AbstractSerializer<T> {
    @Getter
    private final ObjectMapper objectMapper;

    public static final int IDENTITY_NUMBER = 0x4A953A81;


    public Jackson2JsonCacheWrapperSerializer(boolean useIdentityNumber, Class<T> type) {
        super(useIdentityNumber, IDENTITY_NUMBER, type);
        this.objectMapper = new ObjectMapper();
    }

    public Jackson2JsonCacheWrapperSerializer(boolean useIdentityNumber, JavaType type) {
        super(useIdentityNumber, IDENTITY_NUMBER, type);
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 扩展运行设置{@link ObjectMapper}
     *
     * @param objectMapper      {@link ObjectMapper}
     * @param useIdentityNumber 是否使用使用标识号{@link #IDENTITY_NUMBER}
     * @since 2.0.2
     */
    public Jackson2JsonCacheWrapperSerializer(boolean useIdentityNumber, ObjectMapper objectMapper, Class<T> type) {
        super(useIdentityNumber, IDENTITY_NUMBER, type);
        this.objectMapper = objectMapper;
    }

    @Override
    protected CacheWrapper<T> doDeserialize(@Nullable byte[] buffer) throws Exception {
        if (null == buffer || 0 == buffer.length) {
            return null;
        }
        try {
            JavaType javaType = TypeFactory.defaultInstance().constructParametricType(CacheWrapper.class, getJavaType());
            if (useIdentityNumber) {
                return getObjectMapper().readValue(buffer, 4, buffer.length - 4, javaType);
            }
            return getObjectMapper().readValue(buffer, javaType);

        } catch (Exception e) {
            throw new SerializationException("jackson deserialize error:" + e.getMessage(), e);
        }
    }

    @Override
    protected byte[] doSerialize(@Nonnull ByteArrayOutputStream outStream, Object value) throws Exception {
        if (null == value) {
            return EMPTY_ARRAY;
        }
        getObjectMapper().writeValue(outStream, value);
        outStream.flush();
        return outStream.toByteArray();
    }
}
