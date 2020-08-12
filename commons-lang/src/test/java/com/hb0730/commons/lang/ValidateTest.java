package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ValidateTest {

    @Test
    public void validateTest() {
        Validate validate = new Validate();
        Assert.assertNotNull(validate);
    }

    @Test
    public void isTrueTest() {
        log.info("验证是否为true");
        Validate.isTrue(true, "true test,%s", "测试");
        Validate.isTrue(false, "true test,%s", "测试");
    }

    @Test
    public void testIsTrueTest() {
        log.info("验证是否为true");
        Validate.isTrue(true, "true test");
        Validate.isTrue(false, "true test");
        Assert.assertTrue(true);
    }

    @Test
    public void testIsTrue1Test() {
        log.info("验证是否为true");
        Validate.isTrue(true);
        Validate.isTrue(false);
        Assert.assertTrue(true);
    }

    @Test
    public void notNullTest() {
        log.info("验证是否为null");
        Validate.notNull("测试", "test %s", "空值");
        Validate.notNull(null, "test %s", "空值");
        Assert.assertNotNull("测试");
    }

    @Test
    public void testNotNullTest() {
        log.info("验证是否为null");

        Validate.notNull("测试");
        Validate.notNull(null);
        Assert.assertNotNull("测试");

    }

    @Test
    public void notEmptyTest() {
        Object[] obj = null;
        obj = new Object[]{"test"};
        Validate.notEmpty(obj, "test %s", "数组为空");
        obj = new Object[]{};
        Validate.notEmpty(obj, "test %s", "数组为空");
        obj = null;
        Validate.notEmpty(obj, "test %s", "数组为null");
    }

    @Test
    public void testNotEmptyTest() {
        log.info("验证数组是否为null");

        Validate.notEmpty(new Object[]{"test"});
        Validate.notEmpty(new Object[]{});
    }

    @Test
    public void testNotEmpty1Test() {
        log.info("验证集合是否为空");
        ArrayList<Object> list = new ArrayList<>();
        list.add("test");
        Validate.notEmpty(list, "test,%s", "集合为空");
        list.clear();
        Validate.notEmpty(list, "test,%s", "集合为空");
        list = null;
        Validate.notNull(list, "test,%s", "集合为空");
        Assert.assertNotNull(list);
    }

    @Test
    public void testNotEmpty2Test() {
        log.info("验证集合是否为空");
        ArrayList<Object> list = new ArrayList<>();

        list.add("test");
        Validate.notEmpty(list);

        list.clear();
        Validate.notEmpty(list);
        Assert.assertNotNull(list);

    }

    @Test
    public void testNotEmpty3Test() {
        log.info("验证map是否空");
        Map<String, String> maps = new HashMap<>();
        maps.put("test", "test");
        Validate.notEmpty(maps, "test,%s", "map为空");

        maps.clear();
        Validate.notEmpty(maps, "test,%s", "map为空");

        maps = null;
        Validate.notEmpty(maps, "test,%s", "map为null");
        Assert.assertNotNull(maps);

    }

    @Test
    public void testNotEmpty4Test() {
        log.info("验证map是否空");
        Map<String, String> maps = new HashMap<>();
        maps.put("test", "test");
        Validate.notEmpty(maps);
        maps.clear();
        Validate.notEmpty(maps);
        Assert.assertNotNull(maps);
    }

    @Test
    public void testNotEmpty5Test() {
        log.info("验证字符是否为null");
        String str = " ";
        Validate.notEmpty(str, "Test,%s", "字符类型为空");
        str = "";
        Validate.notEmpty(str, "Test,%s", "字符类型为空");
        str = null;
        Validate.notEmpty(str, "Test,%s", "字符类型为null");
        Assert.assertNotNull(str);
    }

    @Test
    public void testNotEmpty6Test() {
        log.info("验证字符是否为null");
        String str = " ";
        Validate.notEmpty(str);
        str = "";
        Validate.notEmpty(str);
        Assert.assertNotNull(str);
    }

    @Test
    public void notBlankTest() {
        log.info("验证字符是否为空");
        String str = "asd ";
        Validate.notBlank(str, "Test,%s", "字符类型空白符");
        str = " ";
        Validate.notBlank(str, "Test,%s", "字符类型空白符");
        str = null;
        Validate.notBlank(str, "Test,%s", "字符类型为null");
        Assert.assertNotNull(str);

    }

    @Test
    public void testNotBlankTest() {
        log.info("验证字符是否为空");
        String str = "asd ";
        Validate.notBlank(str);
        str = " ";
        Validate.notBlank(str);
        Assert.assertNotNull(str);
    }
}
