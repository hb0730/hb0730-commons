package com.hb0730.commons.lang.convert.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CharacterConverterTest {
    private CharacterConverter converter;

    @Before
    public void init() {
        converter = new CharacterConverter();
    }

    @Test
    public void convertInternalTest() {
        Character convert = converter.convert('1', '0');
        Assert.assertNotNull(convert);
        boolean equals = convert.equals('1');
        Assert.assertTrue(equals);
    }
}
