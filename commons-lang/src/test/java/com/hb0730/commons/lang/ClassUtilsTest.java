package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class ClassUtilsTest {

    @Test
    public void isPresentTest() {
        Assert.assertTrue("Integer不能比加载", ClassUtils.isPresent("java.lang.Integer", this.getClass().getClassLoader()));

        Assert.assertTrue("httpClient不能被加载", ClassUtils.isPresent("java.net.http.HttpClient", this.getClass().getClassLoader()));
    }

    @Test
    public void getDefaultValueTest() {
        Object value = null;
        value = ClassUtils.getDefaultValue(byte.class);
        log.info("byte 默认值 {}", value);

        value = ClassUtils.getDefaultValue(short.class);
        log.info("short 默认值 {}", value);

        value = ClassUtils.getDefaultValue(int.class);
        log.info("integer 默认值 {}", value);

        value = ClassUtils.getDefaultValue(long.class);
        log.info("long 默认值 {}", value);

        value = ClassUtils.getDefaultValue(double.class);
        log.info("double 默认值 {}", value);

        value = ClassUtils.getDefaultValue(float.class);
        log.info("float 默认值 {}", value);

        value = ClassUtils.getDefaultValue(boolean.class);
        log.info("boolean 默认值 {}", value);

        value = ClassUtils.getDefaultValue(char.class);
        log.info("char 默认值 {}", value);

        Assert.assertNotNull(value);

    }

    @Test
    public void isBasicClassTest() {
        boolean basicClass = ClassUtils.isBasicClass(Integer.class);
        Assert.assertTrue("对象不为包装类型", basicClass);

        basicClass = ClassUtils.isBasicClass(String.class);
        Assert.assertTrue("对象不为包装类型", basicClass);

        basicClass = ClassUtils.isBasicClass(int.class);
        Assert.assertTrue("对象不为包装类型", basicClass);
    }
}
