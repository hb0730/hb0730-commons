package com.hb0730.commons.lang;

/**
 * chart util
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class CharUtils {
    public static final char SPACE = ' ';


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

}
