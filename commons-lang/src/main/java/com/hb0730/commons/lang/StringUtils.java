package com.hb0730.commons.lang;

/**
 * @author bing_huang
 * @date 2020/07/30 13:24
 * @since V1.0
 */
public class StringUtils {
    /**
     * 去空格
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 判断是否为null或者为 {@code “”}
     *
     * @param str 入参
     * @return true: 为null或者为 {@code ""}
     */
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 确保字符串包含后缀
     *
     * @param string 字符串不能为空
     * @param suffix 字符串不能为空
     * @return 字符串包含指定的后缀
     */
    public static String ensureSuffix(String string, String suffix) {
        if (isEmpty(string) || isEmpty(suffix)) {
            return string;
        }
        return removeEnd(string, suffix) + suffix;
    }

    /**
     * 删除末尾给定的字符串
     *
     * @param str    原字符串
     * @param remove 需要删除的字符串
     * @return 已移除给定字符串
     */
    public static String removeEnd(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }
}
