package com.hb0730.commons.http.exception;

/**
 * @author bing_huang
 * @since 1.0.0
 */
public class CommonHttpException extends RuntimeException {
    public CommonHttpException() {
    }

    public CommonHttpException(String message) {
        super(message);
    }

    public CommonHttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
