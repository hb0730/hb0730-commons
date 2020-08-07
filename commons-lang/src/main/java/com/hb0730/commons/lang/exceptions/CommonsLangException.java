package com.hb0730.commons.lang.exceptions;

/**
 * commons lang 异常
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class CommonsLangException extends RuntimeException {
    public CommonsLangException() {
    }

    public CommonsLangException(String message) {
        super(message);
    }

    public CommonsLangException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonsLangException(Throwable cause) {
        super(cause);
    }

    public CommonsLangException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
