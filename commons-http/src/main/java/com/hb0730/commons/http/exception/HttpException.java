package com.hb0730.commons.http.exception;

/**
 * @author bing_huang
 * @since 1.0.0
 */
public class HttpException extends RuntimeException {
    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
