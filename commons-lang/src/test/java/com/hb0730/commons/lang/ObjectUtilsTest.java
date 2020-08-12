package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ObjectUtilsTest {

    @Test
    public void isNullTest() {
        String object = null;
        Assert.assertTrue("对象不为null", ObjectUtils.isNull(object));
    }

    @Test
    public void isNotNullTest() {
        String object = "";
        Assert.assertTrue("对象为null", ObjectUtils.isNotNull(object));
    }

    @Test
    public void isEmptyTest() {
        List array = new ArrayList<>();
        Assert.assertTrue("集合不为空", ObjectUtils.isEmpty(array));
    }

    @Test
    public void equalTest() {
        String str = "";
        String newStr = "";
        Assert.assertTrue("对象不相等", ObjectUtils.equal(str, newStr));
    }

    @Test
    public void notEqualTest() {
        String str = " ";
        String newStr = "";
        Assert.assertTrue("对象相等", ObjectUtils.notEqual(str, newStr));
    }

    @Test
    public void lengthTest() {
        String str = " ";
        log.info("对象length {}", ObjectUtils.length(str));
    }

    @Test
    public void compareTest() {
        String str = "";
        String str2 = "";
        int compare = ObjectUtils.compare(str, str2);
        Assert.assertEquals("对象不相等", compare, 0);
    }

    @Test
    public void testCompareTest() {
        String str = "";
        String str2 = "";
        int compare = ObjectUtils.compare(str, str2, true);
        Assert.assertEquals("对象不相等", compare, 0);
    }
}
