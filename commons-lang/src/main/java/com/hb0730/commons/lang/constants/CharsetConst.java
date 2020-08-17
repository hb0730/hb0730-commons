package com.hb0730.commons.lang.constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 编码常量
 *
 * @author bing_huang
 * @since 1.0.1
 */
public final class CharsetConst {
    private CharsetConst() {
    }

    /**
     * utf-8
     */
    public static final String UTF_8_STRING = "UTF-8";

    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * utf-16
     */
    public static final String UTF16 = "UTF-16";

    /**
     * gbk
     */
    public static final String GBK = "GBK";

}
