package com.hb0730.commons.json.exceptions;

/**
 * json异常
 *
 * @author bing_huang
 * @since 2.0.0
 */
public class JsonException extends Exception {
    public JsonException(String message) {
        super(message);
    }

    public JsonException(Throwable cause) {
        super(cause);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
