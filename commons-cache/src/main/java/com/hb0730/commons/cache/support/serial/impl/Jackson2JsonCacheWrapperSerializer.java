package com.hb0730.commons.cache.support.serial.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.exception.CacheException;
import com.hb0730.commons.cache.support.serial.AbstractSerializer;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;

/**
 * jackson 序列化与反序列化
 *
 * @author bing_huang
 * @date 2020/07/25 18:04
 * @since V1.0
 */
public class Jackson2JsonCacheWrapperSerializer extends AbstractSerializer {
    public static final Jackson2JsonCacheWrapperSerializer JSON_STRING_SERIALIZER = new Jackson2JsonCacheWrapperSerializer(true);
    @Getter
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static final int IDENTITY_NUMBER = 0x4A953A81;


    public Jackson2JsonCacheWrapperSerializer(boolean useIdentityNumber) {
        super(useIdentityNumber, IDENTITY_NUMBER);
    }

    @Override
    protected Object doDeserialize(@Nullable byte[] buffer) throws Exception {
        if (null == buffer || 0 == buffer.length) {
            return null;
        }
        try {
            if (useIdentityNumber) {
                return this.objectMapper.readValue(buffer, 4, buffer.length - 4, CacheWrapper.class);
            }
            return this.objectMapper.readValue(buffer, CacheWrapper.class);

        } catch (Exception e) {
            throw new CacheException("jackson deserialize error:" + e.getMessage(), e);
        }
    }

    @Override
    protected byte[] doSerialize(@Nonnull ByteArrayOutputStream outStream, Object value) throws Exception {
        if (null == value) {
            return EMPTY_ARRAY;
        }
        this.objectMapper.writeValue(outStream, value);
        outStream.flush();
        return outStream.toByteArray();
    }
}
