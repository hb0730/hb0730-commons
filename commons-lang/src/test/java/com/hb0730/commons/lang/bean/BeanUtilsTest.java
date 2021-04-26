package com.hb0730.commons.lang.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.util.Map;

@Slf4j
public class BeanUtilsTest {

    @Test
    public void deepCloneTest() throws InstantiationException, IllegalAccessException {
        Test1 test1 = new Test1("测试", "18");
        Object o = BeanUtils.deepClone(test1);
        Assert.assertNotNull(o);
        log.info(o.toString());
        Test2 test2 = new Test2();
        test2.setName("测试2");
        test2.setAge("18");
        test2.setSex("1");
        Object o1 = BeanUtils.deepClone(test2);
        Assert.assertNotNull(o1);
        log.info(o1.toString());
        Object o2 = BeanUtils.deepClone(null);
        Assert.assertNull(o2);
    }

    @Test
    public void getPropertyDescriptorTest() {
        PropertyDescriptor[] descriptor = BeanUtils.getPropertyDescriptor(Test1.class);
        Assert.assertNotNull(descriptor);
        PropertyDescriptor[] descriptor1 = BeanUtils.getPropertyDescriptor(Test2.class);
        Assert.assertNotNull(descriptor1);
    }

    @Test
    public void getPropertyDescriptorMapTest() {
        Map<String, PropertyDescriptor> propertyDescriptorMap = BeanUtils.getPropertyDescriptorMap(Test2.class, false);
        Assert.assertNotNull(propertyDescriptorMap);
        Map<String, PropertyDescriptor> propertyDescriptorMap1 = BeanUtils.getPropertyDescriptorMap(Test1.class, true);
        Assert.assertNotNull(propertyDescriptorMap1);

    }

    @Test
    public void testGetPropertyDescriptorTest() {
        PropertyDescriptor name = BeanUtils.getPropertyDescriptor(Test2.class, "name");
        Assert.assertNotNull(name);
        PropertyDescriptor sex = BeanUtils.getPropertyDescriptor(Test2.class, "sex");
        Assert.assertNotNull(sex);
    }

    @Test
    public void testGetPropertyDescriptor1Test() {
        PropertyDescriptor name = BeanUtils.getPropertyDescriptor(Test2.class, "name", false);
        Assert.assertNotNull(name);
        PropertyDescriptor sex = BeanUtils.getPropertyDescriptor(Test2.class, "SEX", true);
        Assert.assertNotNull(sex);
    }

    @Data
    @EqualsAndHashCode
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    static class Test1 {
        private String name;
        private String age;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    static class Test2 extends Test1 {
        private String sex;
    }
}
