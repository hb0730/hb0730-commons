package com.hb0730.commons.lang;

import java.util.Collection;
import java.util.Map;

/**
 * 检验
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class Validate {
    private static final String DEFAULT_IS_TRUE_EX_MESSAGE = "The validated expression is false";
    private static final String DEFAULT_IS_NULL_EX_MESSAGE = "The validated object is null";
    private static final String DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE = "The validated array is empty";
    private static final String DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE = "The validated collection is empty";
    private static final String DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE = "The validated map is empty";
    private static final String DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE = "The validated character sequence is empty";
    private static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "The validated character sequence is blank";

    public Validate() {
        super();
    }

    /***************Boolean**********/
    /**
     * 校验参数是否为true
     *
     * @param expression 要校验的参数
     * @param message    {@link String#format(String, Object...)} 异常消息，不为空
     * @param value      格式化参数值，建议不为空
     * @throws IllegalArgumentException 如果参数为<code>false</code>
     * @see #isTrue(boolean, String, Object...)
     * @see #isTrue(boolean)
     */
    public static void isTrue(final boolean expression, final String message, final Object value) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, value));
        }
    }

    /**
     * 校验参数是否为true
     *
     * @param expression 要校验的参数
     * @param message    {@link String#format(String, Object...)} 异常消息，不为空
     * @param values     格式化值，建议不为空
     * @throws IllegalArgumentException 如果参数为<code>false</code>
     * @see #isTrue(boolean, String, Object)
     * @see #isTrue(boolean)
     */
    public static void isTrue(boolean expression, String message, Object... values) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

    /**
     * 校验参数是否为true，否则抛出异常
     *
     * @param expression 要检测的参数
     * @throws IllegalArgumentException 如果参数为<code>false</code>
     * @see #isTrue(boolean, String, Object)
     * @see #isTrue(boolean, String, Object)
     */
    public static void isTrue(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException(DEFAULT_IS_TRUE_EX_MESSAGE);
        }
    }
    /******Object**********/
    /**
     * 校验参数是否为<code>null</code>,则抛出异常
     *
     * @param value 要校验的参数
     * @throws NullPointerException 如果参数为<code>null</code>
     * @see #notNull(Object, String, Object...)
     */
    public static void notNull(final Object value) {
        notNull(value, DEFAULT_IS_NULL_EX_MESSAGE);
    }

    /**
     * 校验参数是否为<code>null</code>,则抛出异常
     *
     * @param value   要校验的参数
     * @param message {@link String#format(String, Object...)} 异常消息格式化,不为空
     * @param values  格式化参数值，建议不为空
     * @throws NullPointerException 如果参数为<code>null</code>
     * @see #notNull(Object)
     */
    public static void notNull(final Object value, final String message, final Object... values) {
        if (null == value) {
            throw new NullPointerException(String.format(message, values));
        }
    }
    /*******array*************/

    /**
     * 校验数组类型参数是否为<code>null</code>或者长度为0,则抛出异常
     *
     * @param array 要校验的数组参数
     * @throws NullPointerException     如果参数为<code>null</code>
     * @throws IllegalArgumentException 如果参数为{@code length<=0}
     * @see #notEmpty(Object[], String, Object...)
     */
    public static void notEmpty(final Object[] array) {
        notEmpty(array, DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE);
    }

    /**
     * 校验数组类型参数是否为<code>null</code>或者长度为0,则抛出异常
     *
     * @param array   要校验的数组参数
     * @param message {@link String#format(String, Object...)} 异常消息格式化,不为空
     * @param values  格式化参数值，建议不为空
     * @throws NullPointerException     如果参数为<code>null</code>
     * @throws IllegalArgumentException 如果参数为{@code length<=0}
     * @see #notEmpty(Object[])
     */
    public static void notEmpty(final Object[] array, final String message, final Object... values) {
        if (null == array) {
            throw new NullPointerException(String.format(message, values));
        }
        if (0 == array.length) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

    /************collection*************/
    /**
     * 校验集合类型参数是否为<code>null</code>或者长度为0，则抛出异常
     *
     * @param collection 要检验的参数类型
     * @param <T>        参数类型
     * @throws NullPointerException     如果参数为<code>null</code>
     * @throws IllegalArgumentException 如果参数为{@code length<=0}
     * @see #notEmpty(Collection, String, Object...)
     */
    public static <T extends Collection<?>> void notEmpty(final T collection) {
        notEmpty(collection, DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE);
    }

    /**
     * 校验集合类型参数是否为<code>null</code>或者长度为0，则抛出异常
     *
     * @param collection 要检验的参数类型
     * @param message    {@link String#format(String, Object...)} 异常消息格式化,不为空
     * @param values     格式化参数值，建议不为空
     * @param <T>        参数类型
     * @throws NullPointerException     如果参数为<code>null</code>
     * @throws IllegalArgumentException 如果参数为{@code length<=0}
     * @see #notEmpty(Collection)
     */
    public static <T extends Collection<?>> void notEmpty(final T collection, final String message, final Object... values) {
        if (null == collection) {
            throw new NullPointerException(String.format(message, values));
        }
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

    /*****map***********/
    /**
     * 校验map类型参数是否为<code>null</code>或者长度为0，则抛出异常
     *
     * @param map 要校验的参数
     * @param <T> 参数类型
     * @throws NullPointerException     如果参数为<code>null</code>
     * @throws IllegalArgumentException 如果参数为{@code length<=0}
     * @see #notEmpty(Map, String, Object...)
     */
    public static <T extends Map<?, ?>> void notEmpty(final T map) {
        notEmpty(map, DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE);
    }

    /**
     * 校验map类型参数是否为<code>null</code>或者长度为0，则抛出异常
     *
     * @param map     要校验的参数
     * @param message {@link String#format(String, Object...)} 异常消息格式化,不为空
     * @param values  格式化参数值，建议不为空
     * @param <T>     参数类型
     * @throws NullPointerException     如果参数为<code>null</code>
     * @throws IllegalArgumentException 如果参数为{@code length<=0}
     * @see #notEmpty(Map)
     */
    public static <T extends Map<?, ?>> void notEmpty(final T map, final String message, final Object... values) {
        if (null == map) {
            throw new NullPointerException(String.format(message, values));
        }
        if (map.isEmpty()) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

    /*******not empty string************/
    /**
     * 校验参数字符序列是否为<code>null</code>或者长度为zero,则抛出异常
     *
     * @param str 要校验的参数
     * @param <T> 参数类型
     * @throws NullPointerException     如果参数为<code>null</code>
     * @throws IllegalArgumentException 如果参数为{@code length<=0}
     */
    public static <T extends CharSequence> void notEmpty(final T str) {
        notEmpty(str, DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE);
    }

    /**
     * 校验参数字符序列是否为<code>null</code>或者长度为zero,则抛出异常
     *
     * @param str     要校验的参数
     * @param message {@link String#format(String, Object...)} 异常消息格式化,不为空
     * @param values  格式化参数值，建议不为空
     * @param <T>     参数类型
     * @throws NullPointerException     如果参数为<code>null</code>
     * @throws IllegalArgumentException 如果参数为{@code length<=0}
     */
    public static <T extends CharSequence> void notEmpty(final T str, final String message, final Object... values) {
        if (null == str) {
            throw new NullPointerException(String.format(message, values));
        }
        if (0 == str.length()) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

    /*****not blank string***********/
    /**
     * 校验字符类型参数是否为<code>null</code>或者<code>""</code>
     *
     * @param str 要校验的参数
     * @param <T> 字符序列类型
     * @throws NullPointerException     如果参数为<code>null</code>
     * @throws IllegalArgumentException 如果参数为{@code length<=0}
     */
    public static <T extends CharSequence> void notBlank(final T str) {
        notBlank(str, DEFAULT_NOT_BLANK_EX_MESSAGE);
    }

    /**
     * 校验字符类型参数是否为<code>null</code>或者<code>""</code>
     *
     * @param str     要校验的参数
     * @param message {@link String#format(String, Object...)} 异常消息格式化,不为空
     * @param values  格式化参数值，建议不为空
     * @param <T>     字符序列类型
     * @throws NullPointerException     如果参数为<code>null</code>
     * @throws IllegalArgumentException 如果参数为{@code length<=0}
     */
    public static <T extends CharSequence> void notBlank(final T str, final String message, final Object... values) {
        if (null == str) {
            throw new NullPointerException(String.format(message, values));
        }
        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }
}
