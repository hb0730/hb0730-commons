package com.hb0730.commons.lang;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

    @Test
    public void isBasicTypeTest() {
        boolean type = ClassUtils.isBasicType(int.class);
        Assert.assertTrue("对象不是基本类型或者包装类型", type);
        type = ClassUtils.isBasicType(Integer.class);
        Assert.assertTrue("对象不是基本类型或者包装类型", type);

        type = ClassUtils.isBasicType(String.class);
        Assert.assertTrue("对象不是基本类型或者包装类型", type);

        type = ClassUtils.isBasicType(Person.class);
        Assert.assertTrue("对象不是基本类型或者包装类型", type);
    }

    @Test
    public void isAllAssignableFromTest() {
        Class<?>[] clazz1 = new Class[]{String.class};
        Class<?>[] clazz2 = new Class[]{String.class};
        boolean assignable = ClassUtils.isAllAssignableFrom(clazz1, clazz2);
        Assert.assertTrue("类型不一致", assignable);

        clazz1 = null;
        clazz2 = null;
        assignable = ClassUtils.isAllAssignableFrom(clazz1, clazz2);
        Assert.assertTrue("类型不一致", assignable);

        clazz1 = new Class[]{};
        clazz2 = new Class[]{};
        assignable = ClassUtils.isAllAssignableFrom(clazz1, clazz2);
        Assert.assertTrue("类型不一致", assignable);

        clazz1 = new Class[]{Integer.class, Long.class};
        clazz2 = new Class[]{Integer.class};
        assignable = ClassUtils.isAllAssignableFrom(clazz1, clazz2);
        Assert.assertTrue("类型不一致", assignable);

        clazz1 = new Class[]{String.class, Person.class};
        clazz2 = new Class[]{String.class, Person.class};
        assignable = ClassUtils.isAllAssignableFrom(clazz1, clazz2);
        Assert.assertTrue("类型不一致", assignable);
    }

    @Data
    @EqualsAndHashCode
    @Builder
    static class Person {

    }
}
