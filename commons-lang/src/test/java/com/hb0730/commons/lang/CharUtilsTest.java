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

    @Test
    public void isNumberTest() {
        boolean number = CharUtils.isNumber('0');
        Assert.assertTrue("非数字", number);
        number = CharUtils.isNumber('9');
        Assert.assertTrue("非数字", number);
        number = CharUtils.isNumber('8');
        Assert.assertTrue("非数字", number);
        number = CharUtils.isNumber('a');
        Assert.assertTrue("非数字", number);
        number = CharUtils.isNumber('A');
        Assert.assertTrue("非数字", number);
        number = CharUtils.isNumber('~');
        Assert.assertTrue("非数字", number);
    }

    @Test
    public void isLetterTest() {
        boolean letter = CharUtils.isLetter('A');
        Assert.assertTrue("非字母", letter);
        letter = CharUtils.isLetter('a');
        Assert.assertTrue("非字母", letter);
        letter = CharUtils.isLetter('1');
        Assert.assertTrue("非字母", letter);
        letter = CharUtils.isLetter('~');
        Assert.assertTrue("非字母", letter);
    }

    @Test
    public void isLetterUpTest() {
        boolean letter = CharUtils.isLetterUp('A');
        Assert.assertTrue("非大写字母", letter);
        letter = CharUtils.isLetterUp('a');
        Assert.assertTrue("非大写字母", letter);
        letter = CharUtils.isLetterUp('1');
        Assert.assertTrue("非大写字母", letter);
        letter = CharUtils.isLetterUp('~');
        Assert.assertTrue("非大写字母", letter);
    }

    @Test
    public void isLetterLowerTest() {
        boolean letter =CharUtils.isLetterUp('a');
        Assert.assertTrue("非小写字母", letter);
        letter =  CharUtils.isLetterUp('A');
        Assert.assertTrue("非小写字母", letter);
        letter = CharUtils.isLetterUp('1');
        Assert.assertTrue("非小写字母", letter);
        letter = CharUtils.isLetterUp('~');
        Assert.assertTrue("非小写字母", letter);
    }
}
