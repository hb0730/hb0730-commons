package com.hb0730.commons.lang.convert.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class PrimitiveConverterTest {

    @Test
    public void convertInternalTest() {
    }

    @Test
    public void byteTest() {
        byte b = 'b';
        byte c = 'c';
        PrimitiveConverter converter = new PrimitiveConverter(byte.class);
        try {
            Object d = converter.convert(b, (byte) c);
            Assert.assertNotNull(d);
            log.info(d.toString());
        } catch (Exception e) {
            log.error("转换异常");
        }
    }

    @Test
    public void shortTest() {
        short b = 'b';
        short c = 'c';
        PrimitiveConverter converter = new PrimitiveConverter(short.class);
        try {
            Object d = converter.convert(b, c);
            Assert.assertNotNull(d);
            log.info(d.toString());
        } catch (Exception e) {
            log.error("转换异常");
        }
    }

    @Test
    public void intTest() {
        int b = 'b';
        int c = 'c';
        PrimitiveConverter converter = new PrimitiveConverter(int.class);
        try {
            Object d = converter.convert(b, c);
            Assert.assertNotNull(d);
            log.info(d.toString());
        } catch (Exception e) {
            log.error("转换异常");
        }
    }

    @Test
    public void longTest() {
        long b = 'b';
        long c = 'c';
        PrimitiveConverter converter = new PrimitiveConverter(long.class);
        try {
            Object d = converter.convert(b, c);
            Assert.assertNotNull(d);
            log.info(d.toString());
        } catch (Exception e) {
            log.error("转换异常");
        }
    }

    @Test
    public void floatTest() {
        float b = 'b';
        float c = 'c';
        PrimitiveConverter converter = new PrimitiveConverter(float.class);
        try {
            Object d = converter.convert(b, c);
            Assert.assertNotNull(d);
            log.info(d.toString());
        } catch (Exception e) {
            log.error("转换异常");
        }
    }

    @Test
    public void doubleTest() {
        double b = 'b';
        double c = 'c';
        PrimitiveConverter converter = new PrimitiveConverter(double.class);
        try {
            Object d = converter.convert(b, c);
            Assert.assertNotNull(d);
            log.info(d.toString());
        } catch (Exception e) {
            log.error("转换异常");
        }
    }

    @Test
    public void charTest() {
        char b = 'b';
        char c = 'c';
        PrimitiveConverter converter = new PrimitiveConverter(char.class);
        try {
            Object d = converter.convert(b, c);
            Assert.assertNotNull(d);
            log.info(d.toString());
        } catch (Exception e) {
            log.error("转换异常");
        }
    }

    @Test
    public void booleanTest() {
        boolean b = false;
        boolean c = true;
        PrimitiveConverter converter = new PrimitiveConverter(boolean.class);
        try {
            Object d = converter.convert(b, c);
            Assert.assertNotNull(d);
            log.info(d.toString());
        } catch (Exception e) {
            log.error("转换异常");
        }
    }
}
