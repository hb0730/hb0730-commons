package com.hb0730.commons.lang;

import java.nio.charset.Charset;

/**
 * 编码
 *
 * @author bing_huang
 * @date 2020/08/05 7:50
 * @since V1.0
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
     * 获取编码
     * @param charset 编码
     * @return 编码
     */
    public static Charset toCharset(final String charset) {
        return charset == null ? Charset.defaultCharset() : Charset.forName(charset);
    }
}