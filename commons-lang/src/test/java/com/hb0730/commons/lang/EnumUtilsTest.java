package com.hb0730.commons.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class EnumUtilsTest {

    @Test
    public void isEnumTest() {
        boolean b = EnumUtils.isEnum(TestEnum.class);
        Assert.assertTrue("当前类非枚举类", b);

        b = EnumUtils.isEnum(Object.class);
        Assert.assertFalse("当前类为枚举类", b);
    }

    @Test
    public void testIsEnumTest() {
        boolean b = EnumUtils.isEnum(TestEnum.TEST1);
        Assert.assertTrue("当前类非枚举类", b);

        b = EnumUtils.isEnum(new Object());
        Assert.assertFalse("当前类为枚举类", b);
    }

    @Test
    public void getFiledValuesTest() {
        List<String> values = EnumUtils.getFiledValues(TestEnum.class, "name");
        Assert.assertNotNull(values);
        values = EnumUtils.getFiledValues(TestEnum2.class, "name");
        Assert.assertNotNull(values);


        values = EnumUtils.getFiledValues(null, "name");
        Assert.assertNull(values);

    }

    @Test
    public void getNameFieldMapTest() {
        Map<String, String> values = EnumUtils.getNameFieldMap(TestEnum.class, "name");
        Assert.assertNotNull(values);
        values = EnumUtils.getNameFieldMap(TestEnum2.class, "name");
        Assert.assertNotNull(values);

        values = EnumUtils.getNameFieldMap(null, "name");
        Assert.assertNull(values);
    }

    @Test
    public void valueToEnumTest() {
        TestEnum testEnum = EnumUtils.valueToEnum(TestEnum.class, "test1");
        Assert.assertNotNull(testEnum);

        testEnum = EnumUtils.valueToEnum(null, "test1");
        Assert.assertNull(testEnum);
        testEnum = EnumUtils.valueToEnum(TestEnum.class, null);
        Assert.assertNull(testEnum);
    }

    @Test
    public void valueToEnum2Test() {
        TestEnum testEnum = EnumUtils.valueToEnum(TestEnum.class, "name", "test1");
        Assert.assertNotNull(testEnum);
        testEnum = EnumUtils.valueToEnum(null, "name", "test1");
        Assert.assertNull(testEnum);
        testEnum = EnumUtils.valueToEnum(TestEnum.class, null, "test1");
        Assert.assertNotNull(testEnum);
        testEnum = EnumUtils.valueToEnum(TestEnum.class, "name", null);
        Assert.assertNull(testEnum);
    }

    public enum TestEnum {
        TEST1("test1", "value1"),
        TEST2("test2", "value2"),
        TEST3("test3", "value3"),
        ;
        private String name;
        private String value;

        TestEnum(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum TestEnum2 {
        ;
        private String name;
        private String value;

        TestEnum2(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
