package com.hb0730.commons.lang.convert.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

@Slf4j
public class OptionalConverterTest {
    private OptionalConverter converter;

    @Before
    public void init() {
        converter = new OptionalConverter();
    }

    @Test
    public void convertInternalTest() {
        Optional<String> optional = (Optional<String>) converter.convert("测试", Optional.of("测试1"));
        Assert.assertNotNull(optional);

        String params = optional.orElseGet(() -> "默认值为空");
        log.info(params);

        optional = (Optional<String>) converter.convert(null, Optional.of("测试1"));
        Assert.assertNotNull(optional);

        params = optional.orElseGet(() -> "默认值为空");
        log.info(params);
    }
}
