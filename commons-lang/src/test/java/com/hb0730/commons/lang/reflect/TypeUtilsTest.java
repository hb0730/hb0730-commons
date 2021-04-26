package com.hb0730.commons.lang.reflect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class TypeUtilsTest {

    @Test
    public void testGetClassTest() {
        Class<?> clazz = TypeUtils.getClass(Person.class);
        Assert.assertNotNull(clazz);

        Field[] fields = ParameterizedTypeTest.class.getDeclaredFields();
        for (Field field : fields) {
            Type type = TypeUtils.getType(field);
            clazz = TypeUtils.getClass(type);
        }

        Type type = null;
        clazz = TypeUtils.getClass(type);
    }

    @Test
    public void getTypeTest() {
        Field[] fields = ParameterizedTypeTest.class.getDeclaredFields();
        Assert.assertNotNull(fields);
        for (Field field : fields) {
            Type type = TypeUtils.getType(field);
            String typeName = type.getTypeName();
            log.info(typeName);
        }

        Field field = null;
        Type type = TypeUtils.getType(field);

    }

    @Test
    public void testGetClass1Test() {
        Field[] fields = ParameterizedTypeTest.class.getDeclaredFields();
        Assert.assertNotNull(fields);
        for (Field field : fields) {
            Type type = TypeUtils.getClass(field);
            String typeName = type.getTypeName();
            log.info(typeName);
        }

    }

    @Test
    public void getParamTypeTest() {
        Method[] methods = ParameterizedTypeTest.class.getDeclaredMethods();
        Assert.assertNotNull(methods);
        Method method = methods[0];
        Type type = TypeUtils.getParamType(method, 1);
        try {
            Assert.assertNotNull(type);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void getParamClassTest() {
        Method[] methods = ParameterizedTypeTest.class.getDeclaredMethods();
        Assert.assertNotNull(methods);
        Method method = methods[0];
        Class<?> aClass = TypeUtils.getParamClass(method, 1);
        try {
            Assert.assertNotNull(aClass);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void getParamTypesTest() {
        Method[] methods = ParameterizedTypeTest.class.getDeclaredMethods();
        Assert.assertNotNull(methods);
        Method method = methods[0];
        Type[] paramTypes = TypeUtils.getParamTypes(method);
        log.info("params types length {}", paramTypes.length);
    }

    @Test
    public void getParamClassesTest() {
        Method[] methods = ParameterizedTypeTest.class.getDeclaredMethods();
        Assert.assertNotNull(methods);
        Method method = methods[0];
        Class<?>[] classes = TypeUtils.getParamClasses(method);
        log.info("params class length {}", classes);
    }

    @Test
    public void getReturnTypeTest() {
        Method[] methods = ParameterizedTypeTest.class.getDeclaredMethods();
        Assert.assertNotNull(methods);
        Method method = methods[0];
        Type type = TypeUtils.getReturnType(method);
        log.info("method return type {}", type.getTypeName());
    }

    @Test
    public void getReturnClassTest() {
        Method[] methods = ParameterizedTypeTest.class.getDeclaredMethods();
        Assert.assertNotNull(methods);
        Method method = methods[0];
        Class<?> clazz = TypeUtils.getReturnClass(method);
        log.info("method return clazz {}", clazz.getName());
    }

    @Test
    public void getTypeArgumentTest() {
        Type type = TypeUtils.getTypeArgument(ClassTest.class);
        Assert.assertNotNull(type);
        log.info("argument type {}", type.getTypeName());
    }

    @Test
    public void testGetTypeArgumentTest() {
        Type type = TypeUtils.getTypeArgument(ClassTest.class, 0);
        Assert.assertNotNull(type);
        log.info("argument type {}", type.getTypeName());
    }

    @Test
    public void getTypeArgumentsTest() {
        Type[] types = TypeUtils.getTypeArguments(ClassTest.class);
        Assert.assertNotNull(types);
    }

    @Test
    public void toParameterizedTypeTest() {
        ParameterizedType type = TypeUtils.toParameterizedType(ClassTest.class);
        Assert.assertNotNull(type);
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @EqualsAndHashCode
    @ToString
    static class Person {
        private Integer id;
        private String name;
    }

    private static class ParameterizedTypeTest {
        private List<? extends Number> listUpper;
        private Map<String, ParameterizedTypeTest> map;
        private String str;
        private Map.Entry<String, String> entry;

        public <T> void testType(List<String> a1, List<ArrayList<String>> a2, List<T> a3, //
                                 List<? extends Number> a4, List<ArrayList<String>[]> a5, Map<String, Integer> a6) {
        }
    }

    static class ClassTest implements Input<String, Integer> {

    }

    interface Input<DOMAIN, VO> {

    }

}
