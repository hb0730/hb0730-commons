package com.hb0730.commons.lang.reflect;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@Slf4j
public class ReflectUtilsTest {

    @Test
    public void getFieldValueTest() throws NoSuchFieldException, IllegalAccessException {
        Person person = Person.builder().id("1").name("测试").build();
        Object value = ReflectUtils.getFieldValue(person, "name");
        Assert.assertNotNull(value);
        log.info(value.toString());
    }

    @Test
    public void setFieldValueTest() throws NoSuchFieldException, IllegalAccessException {
        Person person = Person.builder().id("1").name("测试").build();
        ReflectUtils.setFieldValue(person, "id", "2");
        Assert.assertNotNull(person);
        log.info(person.toString());
    }

    @Test
    public void getFieldsTest() {
        List<Field> fields = ReflectUtils.getFields(Person.class, false);
        Assert.assertNotNull(fields);
        log.info(fields.toString());

        fields = ReflectUtils.getFields(Person.class, true);
        Assert.assertNotNull(fields);
        log.info(fields.toString());
    }

    @Test
    public void getMethodsTest() {
        List<Method> methods = ReflectUtils.getMethods(Person.class, false);
        Assert.assertNotNull(methods);
        log.info(methods.toString());

        methods = ReflectUtils.getMethods(Person.class, true);
        Assert.assertNotNull(methods);
        log.info(methods.toString());
    }

    @Test
    public void getGetterMethodsTest() {
        List<Method> methods = ReflectUtils.getGetterMethods(Person.class, false);
        Assert.assertNotNull(methods);
        log.info(methods.toString());

        methods = ReflectUtils.getGetterMethods(Person.class, true);
        Assert.assertNotNull(methods);
        log.info(methods.toString());
    }

    @Test
    public void getSetterMethodsTest() {
        List<Method> methods = ReflectUtils.getSetterMethods(Person.class, false);
        Assert.assertNotNull(methods);
        log.info(methods.toString());

        methods = ReflectUtils.getSetterMethods(Person.class, true);
        Assert.assertNotNull(methods);
        log.info(methods.toString());
    }

    @Test
    public void setAccessibleTest() {
        List<Method> methods = ReflectUtils.getSetterMethods(Person.class, false);
        Assert.assertNotNull(methods);
        for (Method method : methods) {
            ReflectUtils.setAccessible(method);
        }
    }

    @Test
    public void invokeTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Person person = Person.builder().build();
        Assert.assertNotNull(person);
        Object test = ReflectUtils.invoke(person, "test");
        log.info(test.toString());

        Object invoke = ReflectUtils.invoke(person, "test", "ttt");
        log.info(invoke.toString());
    }

    @Test
    public void testInvokeTest() throws InvocationTargetException, IllegalAccessException {
        Person person = Person.builder().build();
        Method method = ReflectUtils.getMethod(Person.class, "test");
        Object invoke = method.invoke(person);
        Assert.assertNotNull(invoke);
        log.info(invoke.toString());

        method = ReflectUtils.getMethod(Person.class, "test", String.class);
        invoke = method.invoke(person, "cccc");
        Assert.assertNotNull(invoke);
        log.info(invoke.toString());
    }

    @Test
    public void getMethodArgsTypeTest() {
        Class<?>[] tests = ReflectUtils.getMethodArgsType("test", new Date());
        Assert.assertNotNull(tests);
        log.info(tests.toString());
    }

    @Test
    public void getMethodTest() {
        Method method = ReflectUtils.getMethod(Person.class, "test");
        Assert.assertNotNull(method);
        method = ReflectUtils.getMethod(Person.class, "test", String.class);
        Assert.assertNotNull(method);
    }

    @Test
    public void testGetMethodTest() {
        Method method = ReflectUtils.getMethod(Person.class, false, "test");
        Assert.assertNotNull(method);
        method = ReflectUtils.getMethod(Person.class, false, "test", String.class);
        Assert.assertNotNull(method);

        method = ReflectUtils.getMethod(Person.class, true, "test");
        Assert.assertNotNull(method);
        method = ReflectUtils.getMethod(Person.class, true, "test", String.class);
        Assert.assertNotNull(method);
    }

    @Data
    @EqualsAndHashCode
    @Builder
    static class Person {
        private String id;
        private String name;

        public String test(String params) {

            return params;
        }

        public String test() {
            return "测试";
        }
    }


}
