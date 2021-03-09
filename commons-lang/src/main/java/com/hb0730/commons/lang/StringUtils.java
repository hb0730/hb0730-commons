package com.hb0730.commons.lang;

/**
 * 字符串 util
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class StringUtils {

    /**
     * 校验参数是否为<code>""</code><br>
     * 1、为null <br>
     * 2、为不可见字符（如空格）<br>
     * 3、""<br>
     *
     * @param str 要检测的字符
     * @return 是否为空
     */
    public static boolean isBlank(CharSequence str) {
        int len;
        if (null == str || (len = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            if (!CharUtils.isBlank(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验参数不为<code>""</code><br>
     * 1、为null <br>
     * 2、为不可见字符（如空格）<br>
     * 3、""<br>
     *
     * @param str 要检测的字符
     * @return 是否非空
     * @since 1.0.2
     */
    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    /**
     * 比较两个字符串是否相等，默认不忽略大小写
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     * @see #equals(CharSequence, CharSequence, boolean)
     * @since 1.0.1
     */
    public static boolean equals(CharSequence s1, CharSequence s2) {
        return equals(s1, s2, false);
    }

    /**
     * 比较两个字符串是否相等
     *
     * @param s1         字符串1
     * @param s2         字符串2
     * @param ignoreCase 是否忽略大小写
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     * @since 1.0.1
     */
    public static boolean equals(CharSequence s1, CharSequence s2, boolean ignoreCase) {
        if (null == s1) {
            return null == s2;
        }
        if (null == s2) {
            return false;
        }
        if (ignoreCase) {
            return s1.toString().equalsIgnoreCase(s2.toString());
        } else {
            return s1.toString().contentEquals(s2);
        }
    }

    /**
     * 去除头尾空格
     *
     * @param str 需要去掉空格的字符串
     * @return 已去掉空格的字符串
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 去除头部空格
     *
     * @param value 需要处理的字符串
     * @return 已去除头部的字符串
     */
    public static String trimStart(CharSequence value) {
        return trim(value, -1);
    }

    /**
     * 去除尾部空格
     *
     * @param value 需要处理的字符串
     * @return 已去除尾部空格的字符串
     */
    public static String trimEnd(CharSequence value) {
        return trim(value, 1);
    }

    /**
     * 去除首尾全部空格
     *
     * @param value 需要处理的字符串
     * @return 已去除空格的字符串
     */
    public static String trimAll(CharSequence value) {
        return trim(value, 0);
    }

    /**
     * 除去空格
     *
     * @param value 需要处理的字符串
     * @param mode  处理模式:<code>-1</code>表示处理头部,<code>0</code>表示处理首尾,<code>1</code> 表示处理尾部
     * @return 除去指定字符后的的字符串
     * @see String#trim()
     */
    public static String trim(CharSequence value, int mode) {
        if (value == null) {
            return "";
        }
        int len = value.length();
        int start = 0;
        int end = len;
        if (mode <= 0) {
            while ((start < end) && (CharUtils.isBlank(value.charAt(start)))) {
                start++;
            }
        }
        if (mode >= 0) {
            while ((start < end) && (CharUtils.isBlank(value.charAt(end - 1)))) {
                end--;
            }
        }
        if ((start) > 0 || (end < len)) {
            return value.toString().substring(start, end);
        }
        return value.toString();

    }

    /**
     * 判断是否为null或者为 <code>&quot;&quot;</code>
     *
     * @param str 入参
     * @return true: 为null或者为 <code>&quot;&quot;</code>
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || 0 == str.length());
    }

    /**
     * 是否包含特定的字符
     *
     * @param str 字符串
     * @param v   被查找的字符
     * @return true:包含
     */
    public static boolean contains(String str, char v) {
        if (isEmpty(str)) {
            return false;
        }
        return str.indexOf(v) > -1;
    }

    /**
     * 是否包含特定字符串
     *
     * @param str       字符串
     * @param searchStr 被查找的字符串
     * @return true:包含
     */
    public static boolean contains(String str, String searchStr) {
        if (isEmpty(str)) {
            return false;
        }
        return str.contains(searchStr);
    }


    /**
     * 是否包含字符串,不考虑大小写
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
     * 是否以特定字符串结尾
     *
     * @param str    原字符串
     * @param suffix 结尾字符串
     * @return 是否以特定字符串结尾
     */
    public static boolean endWithIgnoreCase(final CharSequence str, CharSequence suffix) {
        return endWith(str, suffix, false);
    }

    /**
     * 字符串是否以给定字符结尾
     *
     * @param str 字符串
     * @param c   字符
     * @return 是否结尾
     */
    public static boolean endWith(CharSequence str, char c) {
        return c == str.charAt(str.length() - 1);
    }

    /**
     * 是否以特定字符串结尾
     *
     * @param str          原字符串
     * @param suffix       结尾字符串
     * @param isIgnoreCase 是否忽略大小写
     * @return 是否以特定字符串结尾
     */
    public static boolean endWith(final CharSequence str, CharSequence suffix, boolean isIgnoreCase) {
        if (null == str || null == suffix) {
            return false;
        }
        if (isIgnoreCase) {
            return str.toString().toLowerCase().endsWith(suffix.toString().toLowerCase());
        } else {
            return str.toString().endsWith(suffix.toString());
        }
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
        return removeSuffix(string, suffix) + suffix;
    }

    /**
     * 删除末尾给定的字符串
     *
     * @param str    原字符串
     * @param remove 需要删除的字符串
     * @return 已移除给定字符串
     */
    public static String removeSuffix(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    /**
     * 删除特定字符串开头
     *
     * @param str    原字符串
     * @param prefix 需要删除的字符串
     * @return 删除后的字符串
     * @since 2.1.1
     */
    public static String removePrefix(final String str, final String prefix) {
        if (isEmpty(str) || isEmpty(prefix)) {
            return str;
        }
        if (str.startsWith(prefix)) {
            return str.substring(prefix.length());
        }
        return str;
    }

    /**
     * 如果给定字符串{@code str}中不包含{@code appendStr}，则在{@code str}后追加{@code appendStr}；
     * 如果已包含{@code appendStr}，则在{@code str}后追加{@code otherwise}
     *
     * @param str       给定的字符串
     * @param appendStr 需要追加的内容
     * @param otherwise 当{@code appendStr}不满足时追加到{@code str}后的内容
     * @return 追加后的字符串
     */
    public static String appendIfNotContain(final String str, final String appendStr, final String otherwise) {
        if (isEmpty(str) || isEmpty(appendStr)) {
            return str;
        }
        if (str.contains(appendStr)) {
            return str.concat(otherwise);
        }
        return str.concat(appendStr);
    }

    /**
     * 是否以特定字符结尾,如果是则以{@code appendStr}追加结尾，如果不是则以suffix结尾
     *
     * @param str       原字符串
     * @param suffix    结尾字符串
     * @param appendStr 追加字符串
     * @return 追加后字符串
     */
    public static String appendIfNotEndsWith(final String str, final String suffix, final String appendStr) {
        if (endWithIgnoreCase(str, suffix)) {
            return str.concat(appendStr);
        }
        return str;
    }

    /**
     * 字符串拼接 <br>
     *
     * @param elements 需要拼接的String
     * @return 拼接后的String
     * @see StringBuilder
     * @since 2.0.2
     */
    public static String join(CharSequence... elements) {
        Validate.notEmpty(elements, "element not empty");
        StringBuilder sb = new StringBuilder(elements.length);
        for (CharSequence element : elements) {
            sb.append(element);
        }
        return sb.toString();
    }

    /**
     * 字符串拼接，如果遇见{@code null},自动跳过
     *
     * @param elements 需要拼接的String
     * @return 拼接后的String
     * @since 2.1.1
     */
    public static String joinNotNull(CharSequence... elements) {
        Validate.notEmpty(elements, "element not empty");
        final StringBuilder sb = new StringBuilder(elements.length);
        for (CharSequence element : elements) {
            if (StringUtils.isBlank(element)) {
                continue;
            }
            sb.append(element);
        }
        return sb.toString();
    }

}
