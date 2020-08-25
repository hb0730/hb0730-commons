package com.hb0730.commons.cache.impl.remote;

import com.hb0730.commons.cache.config.CacheConfig;
import com.hb0730.commons.cache.exception.CacheException;
import com.hb0730.commons.cache.impl.AbstractCache;
import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 远程存储抽象
 *
 * @author bing_huang
 * @since 1.0.0
 */
public abstract class AbstractRemoteCache<K, V> extends AbstractCache<K, V> {
    protected CacheConfig<K, V> config;

    public AbstractRemoteCache(CacheConfig<K, V> config) {
        this.config = config;
    }

    protected byte[] buildKey(K key) throws IOException {
        Object newKey = key;
        if (key instanceof byte[]) {
            newKey = key;
        } else if (key instanceof String) {
            newKey = key;
        } else {
            if (config.getKeyConverter() != null) {
                newKey = config.getKeyConverter().apply(key);
            }
        }
        return buildKeyForByte(newKey);
    }

    protected byte[] buildKeyForByte(Object key) throws IOException {
        Assert.notNull(key, "key must not be null");
        byte[] keyBytesWithOutPrefix = null;
        if (key instanceof String) {
            keyBytesWithOutPrefix = key.toString().getBytes(StandardCharsets.UTF_8);
        } else if (key instanceof byte[]) {
            keyBytesWithOutPrefix = (byte[]) key;
        } else if (key instanceof Number) {
            keyBytesWithOutPrefix = (((Object) key).getClass().getSimpleName() + key).getBytes(StandardCharsets.UTF_8);
        } else if (key instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss,SSS");
            keyBytesWithOutPrefix = (((Object) key).getClass().getSimpleName() + sdf.format(key)).getBytes();
        } else if (key instanceof Boolean) {
            keyBytesWithOutPrefix = key.toString().getBytes(StandardCharsets.UTF_8);
        } else if (key instanceof Serializable) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(key);
            os.close();
            bos.close();
            keyBytesWithOutPrefix = bos.toByteArray();
        } else {
            throw new CacheException("can't convert key of class:" + ((Object) key).getClass());
        }
        return keyBytesWithOutPrefix;
    }
}
