package com.hb0730.commons.cache.support.serializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.hb0730.commons.cache.CacheWrapper;
import com.hb0730.commons.cache.exception.SerializationException;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;

/**
 * 序列化抽象
 *
 * @author bing_huang
 * @since 1.0.0
 */
public abstract class AbstractSerializer<T> implements Serializer<T> {
    @Getter
    protected boolean useIdentityNumber;
    private static final int INIT_BUF_SIZE = 256;
    @Getter
    private final int identityNumber;

    private static ThreadLocal<WeakReference<ByteArrayOutputStream>> threadLocal =
            ThreadLocal.withInitial(() -> new WeakReference<>(new ByteArrayOutputStream(INIT_BUF_SIZE)));

    public AbstractSerializer(boolean useIdentityNumber, int identityNumber) {
        this.useIdentityNumber = useIdentityNumber;
        this.identityNumber = identityNumber;
    }

    /**
     * 执行序列化
     *
     * @param outStream outputStream
     * @param value     需要序列化对象
     * @return 已完成序列化后的对象
     * @throws Exception 序列化异常
     */
    @Nullable
    protected abstract byte[] doSerialize(@Nonnull ByteArrayOutputStream outStream, @Nullable Object value) throws Exception;

    @Nullable
    @Override
    public byte[] serialize(@Nullable CacheWrapper<T> obj) throws SerializationException {
        if (obj == null) {
            return EMPTY_ARRAY;
        }
        WeakReference<ByteArrayOutputStream> ref = threadLocal.get();
        ByteArrayOutputStream bos = ref.get();
        if (bos == null) {
            bos = new ByteArrayOutputStream(INIT_BUF_SIZE);
            threadLocal.set(new WeakReference<>(bos));
        }
        try {
            if (useIdentityNumber) {
                byte[] headerBuffer = new byte[4];
                writeHeader(headerBuffer, identityNumber);
                bos.write(headerBuffer);
            }
            return doSerialize(bos, obj);
        } catch (Exception e) {
            throw new SerializationException("Cannot serialize", e);
        } finally {
            bos.reset();
        }
    }

    /**
     * 反序列化
     *
     * @param buffer 已被序列化对象
     * @return 完成反序列的对象
     * @throws Exception 反序列化异常
     */
    protected abstract CacheWrapper<T> doDeserialize(@Nullable byte[] buffer) throws Exception;


    @Nullable
    @Override
    public CacheWrapper<T> deserialize(@Nullable byte[] buffer) throws SerializationException {
        if (buffer == null || 0 == buffer.length) {
            return null;
        }
        try {
            return doDeserialize(buffer);
        } catch (Exception e) {
            throw new SerializationException("decode error", e);
        }
    }


    protected void writeHeader(byte[] buf, int header) {
        buf[0] = (byte) (header >> 24 & 0xFF);
        buf[1] = (byte) (header >> 16 & 0xFF);
        buf[2] = (byte) (header >> 8 & 0xFF);
        buf[3] = (byte) (header & 0xFF);
    }

    protected JavaType getJavaType(Class<?> clazz) {
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}
