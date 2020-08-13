package com.hb0730.commons.lang;

import com.hb0730.commons.lang.constants.ASCIIStrCache;

/**
 * chart util
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class CharUtils {
    public static final char SPACE = ' ';

    /**
     * 给定对象对应的类是否为字符类，字符类包括：
     *
     * <pre>
     * Character.class
     * char.class
     * </pre>
     *
     * @param value 被检查的对象
     * @return true表示为字符类
     * @since 1.0.2
     */
    public static boolean isChar(Object value) {
        return value != null && (value instanceof Character || value.getClass() == char.class);
    }

    /**
     * 是否为空白符<br>
     *
     * @param value 字符
     * @return 是否空白符
     * @see Character#isWhitespace(char)
     * @see Character#isSpaceChar(char)
     */
    public static boolean isBlank(char value) {
        return isBlank((int) value);
    }

    /**
     * 是否为空白符<br>
     *
     * @param value 字符
     * @return 是否空白符
     * @see Character#isWhitespace(char)
     * @see Character#isSpaceChar(char)
     */
    public static boolean isBlank(int value) {
        return Character.isWhitespace(value)
                || Character.isSpaceChar(value)
                || value == '\ufeff'
                || value == '\u202a';
    }

    /**
     * 字符转为字符串<br>
     *
     * @param c 字符
     * @return 字符串
     * @since 1.0.2
     */
    public static String toString(char c) {
        return ASCIIStrCache.toString(c);
    }

}
