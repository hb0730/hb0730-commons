package com.hb0730.commons.lang.convert.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class StringConverterTest {
    StringConverter converter;

    @Before
    public void init() {
        converter = new StringConverter();
    }

    @Test
    public void convertInternalTest() {
        String s = converter.convert("test", "测试");
        Assert.assertNotNull(s);
        log.info(s);

        s = converter.convert(null, "测试");
        Assert.assertNotNull(s);
        log.info(s);


        s = converter.convert(null, null);
        Assert.assertNotNull(s);
        log.info(s);
    }
}
