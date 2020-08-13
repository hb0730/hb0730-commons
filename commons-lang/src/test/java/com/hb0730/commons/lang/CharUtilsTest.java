package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class CharUtilsTest {

    @Test
    public void isBlankTest() {
        char c = ' ';
        Assert.assertTrue("不为空格符", CharUtils.isBlank(c));
    }

    @Test
    public void testIsBlankTest() {
        Assert.assertTrue("不为空格符", CharUtils.isBlank(32));
        Assert.assertTrue("不为空格符", CharUtils.isBlank((int) '\ufeff'));
    }

    @Test
    public void testToStringTest() {
        char c = 'c';
        String s = CharUtils.toString(c);
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void isCharTest() {
        char c = 'c';
        boolean b = CharUtils.isChar(c);
        Assert.assertTrue("参数不合法", b);
        Character character = 'c';
        b = CharUtils.isChar(character);
        Assert.assertTrue("参数不合法", b);
        character = null;
        b = CharUtils.isChar(character);
        Assert.assertTrue("参数不合法", b);
    }
}
