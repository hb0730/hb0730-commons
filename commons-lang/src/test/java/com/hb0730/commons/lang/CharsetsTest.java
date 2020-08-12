package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CharsetsTest {

    @Test
    public void toCharsetTest() {
        String chart = "UTF-8";
        Charset charset = Charsets.toCharset(chart);
        Assert.assertNotNull("字符集编码存在", charset);
        chart = null;
        charset = Charsets.toCharset(chart);
        Assert.assertNotNull("字符集编码存在", charset);
    }

    @Test
    public void testToCharsetTest() {
        Charset chart = StandardCharsets.UTF_8;
        Charset charset = Charsets.toCharset(chart);
        Assert.assertNotNull("字符集编码存在", charset);
        chart = null;
        charset = Charsets.toCharset(chart);
        Assert.assertNotNull("字符集编码存在", charset);
    }
}
