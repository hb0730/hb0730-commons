package com.hb0730.commons.cache.support.serializer.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.exception.SerializationException;
import com.hb0730.commons.cache.support.serializer.AbstractSerializer;
import lombok.Getter;
import org.springframework.cache.support.NullValue;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * jackson 泛型类型的映射
 *
 * @author bing_huang
 * @see org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
 */
public class GenericJackson2JsonCacheWrapperSerializer extends AbstractSerializer<Object> {
    public static final int IDENTITY_NUMBER = 0x4A953A82;
    @Getter
    private final ObjectMapper mapper;

    /**
     * 创建 {@link GenericJackson2JsonRedisSerializer} 和 {@link ObjectMapper}
     *
     * @param useIdentityNumber 是否有标识符
     */
    public GenericJackson2JsonCacheWrapperSerializer(boolean useIdentityNumber) {
        this(useIdentityNumber, new ObjectMapper());
    }

    /**
     * 创建{@link GenericJackson2JsonRedisSerializer}
     *
     * @param useIdentityNumber 是否有标识符
     * @param mapper            {@link ObjectMapper}
     */
    public GenericJackson2JsonCacheWrapperSerializer(boolean useIdentityNumber, ObjectMapper mapper) {
        this(useIdentityNumber, (String) null, mapper);
    }

    /**
     * 创建{@link GenericJackson2JsonRedisSerializer}
     *
     * @param useIdentityNumber     是否有标识符
     * @param classPropertyTypeName 包含属性类型的名称，可以为空，默认为{@code @class}
     * @param mapper                {@link ObjectMapper}
     */
    public GenericJackson2JsonCacheWrapperSerializer(boolean useIdentityNumber, String classPropertyTypeName, ObjectMapper mapper) {
        super(useIdentityNumber, IDENTITY_NUMBER);
        registerNullValueSerializer(mapper, classPropertyTypeName);
        this.mapper = mapper;
        if (StringUtils.hasText(classPropertyTypeName)) {
            mapper.activateDefaultTypingAsProperty(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, classPropertyTypeName);
        } else {
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        }
    }

    /**
     * Register {@link NullValueSerializer} in the given {@link ObjectMapper} with an optional
     * {@code classPropertyTypeName}. This method should be called by code that customizes
     * {@link GenericJackson2JsonRedisSerializer} by providing an external {@link ObjectMapper}.
     *
     * @param objectMapper          the object mapper to customize.
     * @param classPropertyTypeName name of the type property. Defaults to {@code @class} if {@literal null}/empty.
     * @since 2.2
     */
    public static void registerNullValueSerializer(ObjectMapper objectMapper, @org.springframework.lang.Nullable String classPropertyTypeName) {

        // simply setting {@code mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)} does not help here since we need
        // the type hint embedded for deserialization using the default typing feature.
        objectMapper.registerModule(new SimpleModule().addSerializer(new GenericJackson2JsonCacheWrapperSerializer.NullValueSerializer(classPropertyTypeName)));
    }


    @Nullable
    @Override
    protected byte[] doSerialize(@Nonnull ByteArrayOutputStream outStream, @Nullable Object value) throws Exception {
        if (null == value) {
            return EMPTY_ARRAY;
        }
        getMapper().writeValue(outStream, value);
        outStream.flush();
        return outStream.toByteArray();
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected CacheWrapper<Object> doDeserialize(@Nullable byte[] buffer) throws Exception {
        if (null == buffer || 0 == buffer.length) {
            return null;
        }
        try {
            if (useIdentityNumber) {
                return getMapper().readValue(buffer, 4, buffer.length - 4, CacheWrapper.class);
            }
            return getMapper().readValue(buffer, CacheWrapper.class);

        } catch (Exception e) {
            throw new SerializationException("jackson deserialize error:" + e.getMessage(), e);
        }
    }

    /**
     * {@link StdSerializer} adding class information required by default typing. This allows de-/serialization of
     * {@link NullValue}.
     *
     * @author Christoph Strobl
     * @since 1.8
     */
    private static class NullValueSerializer extends StdSerializer<NullValue> {

        private static final long serialVersionUID = 1999052150548658808L;
        private final String classIdentifier;

        /**
         * @param classIdentifier can be {@literal null} and will be defaulted to {@code @class}.
         */
        NullValueSerializer(@org.springframework.lang.Nullable String classIdentifier) {

            super(NullValue.class);
            this.classIdentifier = StringUtils.hasText(classIdentifier) ? classIdentifier : "@class";
        }

        /*
         * (non-Javadoc)
         * @see com.fasterxml.jackson.databind.ser.std.StdSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
         */
        @Override
        public void serialize(NullValue value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException {

            jgen.writeStartObject();
            jgen.writeStringField(classIdentifier, NullValue.class.getName());
            jgen.writeEndObject();
        }
    }
}
