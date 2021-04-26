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
        try {
            Validate.isTrue(false, "true test,%s", "测试");
        } catch (Exception e) {
            log.info("验证失败，参数为false");
        }
    }

    @Test
    public void testIsTrueTest() {
        log.info("验证是否为true");
        Validate.isTrue(true, "true test");
        try {
            Validate.isTrue(false, "true test");
        } catch (Exception e) {
            log.info("验证失败，参数为false");
        }
    }

    @Test
    public void testIsTrue1Test() {
        log.info("验证是否为true");
        Validate.isTrue(true);
        try {
            Validate.isTrue(false);
        } catch (Exception e) {
            log.info("验证失败，参数为false");
        }
    }

    @Test
    public void notNullTest() {
        log.info("验证是否为null");
        Validate.notNull("测试", "test %s", "空值");
        try {

            Validate.notNull(null, "test %s", "空值");
        } catch (Exception e) {
            log.info("验证失败，参数为null");
        }
    }

    @Test
    public void testNotNullTest() {
        log.info("验证是否为null");

        Validate.notNull("测试");
        try {
            Validate.notNull(null);
        } catch (Exception e) {
            log.info("验证失败，参数为null");
        }

    }

    @Test
    public void notEmptyTest() {
        Object[] obj = null;
        obj = new Object[]{"test"};
        Validate.notEmpty(obj, "test %s", "数组为空");
        obj = new Object[]{};
        try {

            Validate.notEmpty(obj, "test %s", "数组为空");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        obj = null;
        try {
            Validate.notEmpty(obj, "test %s", "数组为null");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Test
    public void testNotEmptyTest() {
        log.info("验证数组是否为null");

        Validate.notEmpty(new Object[]{"test"});
        try {

            Validate.notEmpty(new Object[]{});
        } catch (Exception e) {
            log.info("数组 size为0");
        }
    }

    @Test
    public void testNotEmpty1Test() {
        log.info("验证集合是否为空");
        ArrayList<Object> list = new ArrayList<>();
        list.add("test");
        Validate.notEmpty(list, "test,%s", "集合为空");
        list.clear();
        try {
            Validate.notEmpty(list, "test,%s", "集合为空");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        list = null;
        try {
            Validate.notNull(list, "test,%s", "集合为空");
        } catch (Exception e) {
            log.error(e.getMessage());
        }


    }

    @Test
    public void testNotEmpty2Test() {
        log.info("验证集合是否为空");
        ArrayList<Object> list = new ArrayList<>();

        list.add("test");
        Validate.notEmpty(list);

        list.clear();
        try {
            Validate.notEmpty(list);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testNotEmpty3Test() {
        log.info("验证map是否空");
        Map<String, String> maps = new HashMap<>();
        maps.put("test", "test");
        Validate.notEmpty(maps, "test,%s", "map为空");

        maps.clear();
        try {

            Validate.notEmpty(maps, "test,%s", "map为空");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        try {
            maps = null;
            Validate.notEmpty(maps, "test,%s", "map为null");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testNotEmpty4Test() {
        log.info("验证map是否空");
        Map<String, String> maps = new HashMap<>();
        maps.put("test", "test");
        Validate.notEmpty(maps);
        maps.clear();
        try {
            Validate.notEmpty(maps);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testNotEmpty5Test() {
        log.info("验证字符是否为null");
        String str = " ";
        Validate.notEmpty(str, "Test,%s", "字符类型为空");
        try {
            str = "";
            Validate.notEmpty(str, "Test,%s", "字符类型为空");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        try {
            str = null;
            Validate.notEmpty(str, "Test,%s", "字符类型为null");
        } catch (Exception e) {

        }
    }

    @Test
    public void testNotEmpty6Test() {
        log.info("验证字符是否为null");
        String str = " ";
        Validate.notEmpty(str);
        try {
            str = "";
            Validate.notEmpty(str);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void notBlankTest() {
        log.info("验证字符是否为空");
        String str = "asd ";
        Validate.notBlank(str, "Test,%s", "字符类型空白符");
        try {
            str = " ";
            Validate.notBlank(str, "Test,%s", "字符类型空白符");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        try {
            str = null;
            Validate.notBlank(str, "Test,%s", "字符类型为null");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testNotBlankTest() {
        log.info("验证字符是否为空");
        String str = "asd ";
        Validate.notBlank(str);
        try {
            str = " ";
            Validate.notBlank(str);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
