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
    }
}
