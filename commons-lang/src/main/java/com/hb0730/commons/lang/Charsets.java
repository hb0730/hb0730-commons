package com.hb0730.commons.lang;

import java.nio.charset.Charset;

/**
 * 编码
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class Charsets {

    /**
     * 获取编码，参数为空时返回默认编码
     *
     * @param charset 编码
     * @return 编码
     */
    public static Charset toCharset(final Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }

    /**
     * 获取编码,当charset 为<code>null</code>，获取系统编码规则
     *
     * @param charset 编码
     * @return 编码
     */
    public static Charset toCharset(final String charset) {
        return StringUtils.isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset);
    }
}
