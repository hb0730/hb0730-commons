package com.hb0730.commons.lang.exceptions;

/**
 * commons lang 异常
 *
 * @author bing_huang
 * @date 2020/08/04 7:28
 * @since V1.0
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
