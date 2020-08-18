package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
public class ObjectUtilsTest {
    @Test
    public void objectUtilsTest() {
        ObjectUtils objectUtils = new ObjectUtils();
        Assert.assertNotNull(objectUtils);
    }

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

        Object obj = null;
        Assert.assertTrue("对象不为空", ObjectUtils.isEmpty(obj));
        obj = " ";
        Assert.assertTrue("对象不为空", ObjectUtils.isEmpty(obj));
        obj = new ArrayList<>();
        Assert.assertTrue("集合不为空", ObjectUtils.isEmpty(obj));
        obj = new HashMap<>();
        Assert.assertTrue("map不为空", ObjectUtils.isEmpty(obj));
        obj = new Object[]{"s"};
        Assert.assertTrue("数组不为空", ObjectUtils.isEmpty(obj));
        obj = Optional.of("cc");
        Assert.assertTrue("Optional不为空", ObjectUtils.isEmpty(obj));
        obj = Integer.valueOf("测试");
        Assert.assertTrue("OInteger不为空", ObjectUtils.isEmpty(obj));
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
        Object obj = null;
        log.info("对象length {}", ObjectUtils.length(obj));
        obj = " ";
        log.info("对象length {}", ObjectUtils.length(obj));
        obj = new ArrayList<String>(1);
        log.info("对象length {}", ObjectUtils.length(obj));
        obj = new HashMap<>(1);
        log.info("对象length {}", ObjectUtils.length(obj));
        obj = new Object[]{"1"};
        log.info("对象length {}", ObjectUtils.length(obj));
        Assert.assertNotNull(obj);
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

        str = null;
        compare = ObjectUtils.compare(str, str2, true);
        Assert.assertEquals("对象不相等", compare, 0);

        str = "";
        str2 = null;
        compare = ObjectUtils.compare(str, str2, true);
        Assert.assertEquals("对象不相等", compare, 0);
    }

    @Test
    public void testHashCodeTest() {
        Object o1 = "1sad";
        Object o2 = new Object();
        Object o3 = Arrays.asList("1", "2");
        int i = ObjectUtils.hashCode(o1, o2, o3);
        Assert.assertNotNull(i);
        log.info(i + "");
    }
}
