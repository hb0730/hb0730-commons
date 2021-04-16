package com.hb0730.commons.lang.io;

import com.hb0730.commons.lang.exceptions.CommonsLangException;

/**
 * IO 运行时异常
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class IORuntimeException extends CommonsLangException {
    public IORuntimeException(Throwable cause) {
        super(cause);
    }
}
