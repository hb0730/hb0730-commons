package com.hb0730.commons.lang.convert.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BooleanConverterTest {
    private BooleanConverter converter;

    @Before
    public void init() {
        converter = new BooleanConverter();
    }

    @Test
    public void convertInternalTest() {
        Boolean convert = converter.convert("1", false);
        Assert.assertTrue(convert);
    }
}
