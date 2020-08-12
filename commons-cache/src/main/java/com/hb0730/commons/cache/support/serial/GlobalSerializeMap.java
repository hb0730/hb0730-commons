package com.hb0730.commons.cache.support.serial;

import com.hb0730.commons.cache.exception.CacheException;
import com.hb0730.commons.cache.support.serial.impl.Jackson2JsonCacheWrapperSerializer;
import com.hb0730.commons.cache.support.serial.impl.JdkCacheSerializer;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局的序列化和反序列化器映射<br>
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class GlobalSerializeMap {
    private static Map<Integer, Serializer> serializerMap;

    /**
     * 加入自定义序列化与反序列化
     *
     * @param identityNumber key
     * @param serializer     value
     */
    public static void put(@Nonnull Integer identityNumber, Serializer serializer) {
        putInternal(identityNumber, serializer);
    }


    synchronized private static void putInternal(@Nonnull Integer identityNumber, Serializer serializer) {
        Assert.notNull(identityNumber, "identity number must not be null");
        if (null == serializerMap) {
            serializerMap = new ConcurrentHashMap<>(16);
        } else if (null != serializerMap.get(identityNumber)) {
            throw new CacheException("identityNumber Already exists");
        }
        serializerMap.put(identityNumber, serializer);
    }

    /**
     * 获取序列化与反序列化
     *
     * @param identityNumber key
     * @return 序列化与反序列化
     */
    public static Serializer get(Integer identityNumber) {
        if (null == serializerMap) {
            return null;
        }
        return serializerMap.get(identityNumber);
    }

    private static boolean isInit = false;

    public static void register() {
        if (!isInit) {
            put(JdkCacheSerializer.IDENTITY_NUMBER, JdkCacheSerializer.INSTANCE);
            put(Jackson2JsonCacheWrapperSerializer.IDENTITY_NUMBER, Jackson2JsonCacheWrapperSerializer.JSON_STRING_SERIALIZER);
            isInit = true;
        }
    }
}
