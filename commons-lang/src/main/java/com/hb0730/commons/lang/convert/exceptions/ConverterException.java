package com.hb0730.commons.lang.convert.exceptions;

import com.hb0730.commons.lang.exceptions.CommonsLangException;

/**
 * 转换异常
 *
 * @author bing_huang
 * @since 2.0.0
 */
public class ConverterException extends CommonsLangException {
    public ConverterException(String message) {
        super(message);
    }

    public ConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
