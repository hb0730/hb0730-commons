package com.hb0730.commons.lang;

/**
 * 检验
 *
 * @author bing_huang
 * @date 2020/07/31 7:25
 * @since V1.0
 */
public class Validate {
    public static void isTrue(boolean expression, String message, Object... values) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }
}
