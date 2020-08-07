package com.hb0730.commons.cache.exception;

/**
 * cache 异常
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class CacheException extends RuntimeException {

    public CacheException() {
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }
}
