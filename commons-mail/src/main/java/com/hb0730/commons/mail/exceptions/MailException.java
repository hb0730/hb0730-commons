package com.hb0730.commons.mail.exceptions;

/**
 * 异常
 *
 * @author bing_huang
 * @since 2.0.0
 */
public class MailException extends RuntimeException {
    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailException(Throwable cause) {
        super(cause);
    }
}
