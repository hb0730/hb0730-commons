package com.hb0730.commons.lang;

/**
 * @author bing_huang
 * @date 2020/07/30 13:24
 * @since V1.0
 */
public class StringUtils {
    /**
     * 判断是否为null或者为 {@code “”}
     *
     * @param str 入参
     * @return true: 为null或者为 {@code ""}
     */
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }
}
