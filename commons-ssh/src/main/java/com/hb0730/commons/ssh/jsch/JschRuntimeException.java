package com.hb0730.commons.ssh.jsch;

/**
 * Jsch异常
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class JschRuntimeException extends RuntimeException {
    public JschRuntimeException(Throwable cause) {
        super(cause);
    }

    public JschRuntimeException(String message) {
        super(message);
    }
}
