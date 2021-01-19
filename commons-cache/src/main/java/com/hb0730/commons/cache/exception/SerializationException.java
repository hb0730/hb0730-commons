package com.hb0730.commons.cache.exception;

/**
 * 序列化异常
 *
 * @author bing_huang
 * @since 2.0.4
 */
public class SerializationException extends CacheException {
    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
