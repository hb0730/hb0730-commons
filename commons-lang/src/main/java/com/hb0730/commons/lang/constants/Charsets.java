package com.hb0730.commons.lang.constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 编码常量<br>
 * 尽量使用Charsets.UTF8而不是"UTF-8"，减少JDK里的Charset查找消耗.
 *
 * @author bing_huang
 * @since 1.0.1
 */
public interface Charsets {

    /**
     * utf-8
     */
    String UTF_8_NAME = "UTF-8";
    Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * utf-16
     */
    Charset UTF_16 = StandardCharsets.UTF_16;
    String UTF_16_NAME = "UTF-16";

    /**
     * gbk
     */
    Charset GBK = Charset.forName("GBK");
    String GBK_NAME = "GBK";
    /**
     * US-ASCII
     */
    Charset US_ASCII = StandardCharsets.US_ASCII;
    String US_ASCII_NAME = "US-ASCII";

    /**
     * ISO-8859-1
     */
    Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
    String ISO_8859_1_NAME = "ISO-8859-1";
}
