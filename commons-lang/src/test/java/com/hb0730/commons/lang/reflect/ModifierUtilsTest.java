package com.hb0730.commons.lang.reflect;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
public class ModifierUtilsTest {

    @Test
    public void hasModifierTest() {
        boolean b = ModifierUtils.hasModifier(Test1.class, ModifierUtils.ModifierType.STATIC);
        Assert.assertTrue("非静态类", b);
        b = ModifierUtils.hasModifier(Test1.class, null);
        Assert.assertFalse("静态类", b);
        b = ModifierUtils.hasModifier((Class<?>) null, null);
        Assert.assertFalse("静态类", b);
    }

    @Test
    public void testHasModifierTest() {
        Constructor<Test2> constructor = ReflectUtils.getConstructor(Test2.class);
        Constructor<Test2> constructor1 = ReflectUtils.getConstructor(Test2.class, String.class);
        boolean b = ModifierUtils.hasModifier(constructor, ModifierUtils.ModifierType.PRIVATE);
        Assert.assertFalse("私有构造函数", b);
        b = ModifierUtils.hasModifier(constructor1, ModifierUtils.ModifierType.PUBLIC);
        Assert.assertFalse("公开构造函数", b);
        b = ModifierUtils.hasModifier(constructor1, null);
        Assert.assertFalse("公开构造函数", b);
        b = ModifierUtils.hasModifier((Constructor) null, null);
        Assert.assertFalse("公开构造函数", b);
    }

    @Test
    public void testHasModifier1Test() {
        Method test1 = ReflectUtils.getMethod(Test3.class, "test1");
        Method test2 = ReflectUtils.getMethod(Test3.class, "test2");
        Method test3 = ReflectUtils.getMethod(Test3.class, "test3");
        Method test4 = ReflectUtils.getMethod(Test3.class, "test4");
        boolean b = ModifierUtils.hasModifier(test1, ModifierUtils.ModifierType.STATIC);
        Assert.assertTrue("非static方法", b);
        b = ModifierUtils.hasModifier(test3, ModifierUtils.ModifierType.PROTECTED);
        Assert.assertTrue("非Protected方法", b);
        b = ModifierUtils.hasModifier(test4, ModifierUtils.ModifierType.PRIVATE);
        Assert.assertTrue("非Private方法", b);
        b = ModifierUtils.hasModifier(test2, null);
        Assert.assertFalse("public方法", b);
        b = ModifierUtils.hasModifier((Method) null, null);
        Assert.assertFalse("public方法", b);
    }

    @Test
    public void testHasModifier2Test() {
        Field test1 = ReflectUtils.getField(Test4.class, "test1", false);
        Field test2 = ReflectUtils.getField(Test4.class, "test2", false);
        Field test3 = ReflectUtils.getField(Test4.class, "test3", false);
        boolean b = ModifierUtils.hasModifier(test1, ModifierUtils.ModifierType.PRIVATE);
        Assert.assertTrue("非Private", b);
        b = ModifierUtils.hasModifier(test2, ModifierUtils.ModifierType.PUBLIC);
        Assert.assertTrue("非Public", b);
        b = ModifierUtils.hasModifier(test2, ModifierUtils.ModifierType.STATIC);
        Assert.assertTrue("非Static", b);
        b = ModifierUtils.hasModifier(test2, ModifierUtils.ModifierType.FINAL);
        Assert.assertTrue("非Final", b);
        b = ModifierUtils.hasModifier(test3, null);
        Assert.assertFalse("Final", b);
        b = ModifierUtils.hasModifier((Field) null, null);
        Assert.assertFalse("Final", b);


    }

    @Test
    public void isPublicTest() {
        Field test1 = ReflectUtils.getField(Test4.class, "test1", false);
        Field test2 = ReflectUtils.getField(Test4.class, "test2", false);
        boolean aPublic = ModifierUtils.isPublic(test2);
        Assert.assertTrue("非public", aPublic);
        aPublic = ModifierUtils.isPublic(test1);
        Assert.assertFalse("public", aPublic);
        aPublic = ModifierUtils.isPublic((Field) null);
        Assert.assertFalse("public", aPublic);
    }

    @Test
    public void testIsPublicTest() {
        Method test1 = ReflectUtils.getMethod(Test3.class, "test1");
        Method test4 = ReflectUtils.getMethod(Test3.class, "test4");
        boolean aPublic = ModifierUtils.isPublic(test1);
        Assert.assertTrue("非public", aPublic);
        aPublic = ModifierUtils.isPublic(test4);
        Assert.assertFalse("public", aPublic);
        aPublic = ModifierUtils.isPublic((Method) null);
        Assert.assertFalse("public", aPublic);
    }

    @Test
    public void testIsPublic1Test() {
        Constructor<Test2> constructor = ReflectUtils.getConstructor(Test2.class);
        Constructor<Test2> constructor1 = ReflectUtils.getConstructor(Test2.class, String.class);
        boolean aPublic = ModifierUtils.isPublic(constructor1);
        Assert.assertFalse("public", aPublic);
        aPublic = ModifierUtils.isPublic(constructor);
        Assert.assertFalse("public", aPublic);
        aPublic = ModifierUtils.isPublic((Constructor) null);
        Assert.assertFalse("public", aPublic);
    }

    @Test
    public void testIsPublic2Test() {
        boolean aPublic = ModifierUtils.isPublic(Test1.class);
        Assert.assertTrue("非public", aPublic);
        aPublic = ModifierUtils.isPublic(Test2.class);
        Assert.assertFalse("public", aPublic);
        aPublic = ModifierUtils.isPublic((Class) null);
        Assert.assertFalse("非public", aPublic);
    }

    @Test
    public void isStaticTest() {
        Field test1 = ReflectUtils.getField(Test4.class, "test1", false);
        Field test3 = ReflectUtils.getField(Test4.class, "test3", false);
        boolean aStatic = ModifierUtils.isStatic(test1);
        Assert.assertTrue("非Static", aStatic);
        aStatic = ModifierUtils.isStatic(test3);
        Assert.assertFalse("Static", aStatic);
        aStatic = ModifierUtils.isStatic((Field) null);
        Assert.assertFalse("Static", aStatic);
    }

    @Test
    public void testIsStaticTest() {
        Method test1 = ReflectUtils.getMethod(Test3.class, "test1");
        Method test2 = ReflectUtils.getMethod(Test3.class, "test2");
        boolean aStatic = ModifierUtils.isStatic(test1);
        Assert.assertTrue("非Static", aStatic);
        aStatic = ModifierUtils.isStatic(test2);
        Assert.assertFalse("Static", aStatic);
        aStatic = ModifierUtils.isStatic((Field) null);
        Assert.assertFalse("Static", aStatic);

    }

    @Test
    public void testIsStatic1Test() {
        boolean aPublic = ModifierUtils.isStatic(Test1.class);
        Assert.assertTrue("非Static", aPublic);
        aPublic = ModifierUtils.isStatic(Test2.class);
        Assert.assertFalse("Static", aPublic);
        aPublic = ModifierUtils.isStatic((Class) null);
        Assert.assertFalse("Static", aPublic);
    }

    public static class Test1 {
    }

    private class Test2 {

        private Test2() {
        }

        public Test2(String s1) {
        }
    }

    public static class Test3 {
        public static void test1() {

        }

        public void test2() {

        }

        protected void test3() {

        }

        private void test4() {

        }
    }

    public static class Test4 {
        private static String test1;
        public static final String test2 = "";
        protected String test3;
    }
}
