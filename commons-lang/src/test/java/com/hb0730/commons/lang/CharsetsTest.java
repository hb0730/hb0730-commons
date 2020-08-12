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
        Charset charset = Charsets.toCharset("UTF-8");
        Assert.assertNotNull("字符集编码存在", charset);
    }

    @Test
    public void testToCharsetTest() {
        Charset charset = Charsets.toCharset(StandardCharsets.UTF_8);
        Assert.assertNotNull("字符集编码存在", charset);
    }
}
