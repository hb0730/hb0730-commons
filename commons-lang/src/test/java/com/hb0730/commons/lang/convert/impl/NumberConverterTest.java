package com.hb0730.commons.lang.convert.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class NumberConverterTest {

    @Test
    public void convertInternalTest() {
    }

    @Test
    public void byteTest() {
        Byte b = 'b';
        Byte c = 'c';
        NumberConverter converter = new NumberConverter(Byte.class);
        Object d = converter.convert(b, c);
        Assert.assertNotNull(d);
        log.info(d.toString());
    }

    @Test
    public void shortTest() {
        Short b = 'b';
        Short c = 'c';
        NumberConverter converter = new NumberConverter(Short.class);
        Object d = converter.convert(b, c);
        Assert.assertNotNull(d);
        log.info(d.toString());
    }

    @Test
    public void intTest() {
        Integer b = (int) 'b';
        Integer c = (int) 'c';
        NumberConverter converter = new NumberConverter(Integer.class);
        Object d = converter.convert(b, c);
        Assert.assertNotNull(d);
        log.info(d.toString());
    }

    @Test
    public void longTest() {
        Long b = (long) 'b';
        Long c = (long) 'c';
        NumberConverter converter = new NumberConverter(Long.class);
        Object d = converter.convert(b, c);
        Assert.assertNotNull(d);
        log.info(d.toString());
    }

    @Test
    public void floatTest() {
        Float b = (float) 'b';
        Float c = (float) 'c';
        NumberConverter converter = new NumberConverter(Float.class);
        Object d = converter.convert(b, c);
        Assert.assertNotNull(d);
        log.info(d.toString());
    }

    @Test
    public void doubleTest() {
        Double b = (double) 'b';
        Double c = (double) 'c';
        NumberConverter converter = new NumberConverter(Double.class);
        Object d = converter.convert(b, c);
        Assert.assertNotNull(d);
        log.info(d.toString());
    }

    @Test
    public void charTest() {
        Character b = 'b';
        Character c = 'c';
        CharacterConverter converter = new CharacterConverter();
        Object d = converter.convert(b, c);
        Assert.assertNotNull(d);
        log.info(d.toString());
    }
}
