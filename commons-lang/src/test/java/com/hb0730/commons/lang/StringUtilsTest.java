package com.hb0730.commons.lang;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void trimTest() {
        String str = " asas asad ";
        String newStr = StringUtils.trim(str);
        System.out.println("<" + str + ">length:" + str.length() + ",new<" + newStr + ">length:" + newStr.length());
    }


    @Test
    public void trimStartTest() {
        String str = " asas asad ";
        String newStr = StringUtils.trimStart(str);
        System.out.println("<" + str + ">length:" + str.length() + ",new<" + newStr + ">length:" + newStr.length());
    }

    @Test
    public void trimEndTest() {
        String str = " asas asad ";
        String newStr = StringUtils.trimEnd(str);
        System.out.println("<" + str + ">length:" + str.length() + ",new<" + newStr + ">length:" + newStr.length());
    }

    @Test
    public void trimAllTest() {
        String str = " asas asad ";
        String newStr = StringUtils.trimAll(str);
        System.out.println("<" + str + ">length:" + str.length() + ",new<" + newStr + ">length:" + newStr.length());
    }

    @Test
    public void trimTest2() {
        // 处理头
        trimStartTest();
        //处理尾
        trimEndTest();
        // 处理头尾
        trimTest();
        //处理全部
        trimAllTest();
    }

    @Test
    public void isEmptyTest() {
        String str = "";
        System.out.println("\"\"=" + StringUtils.isEmpty(str));
        str=" ";
        System.out.println("\" \"=" + StringUtils.isEmpty(str));
        str = "ss";
        System.out.println("str=" + StringUtils.isEmpty(str));
        str = null;
        System.out.println("null=" + StringUtils.isEmpty(str));
    }

    @Test
    public void containsTest() {
        String str = "asasas";
        System.out.println(StringUtils.contains(str, 'a'));
    }

    @Test
    public void testContainsTest() {
        String str = "asasad";
        System.out.println(StringUtils.contains(str, "a"));
    }

    @Test
    public void inStringIgnoreCaseTest() {
        String str = "asd";
        System.out.println(StringUtils.inStringIgnoreCase(str, "a", "as", "asd", "asds"));
        System.out.println(StringUtils.inStringIgnoreCase(str, "a", "as", "asds"));
    }

    @Test
    public void ensureSuffixTest() {
        String str = "asas";
        System.out.println(StringUtils.ensureSuffix(str, "@qq.com"));
    }

    @Test
    public void removeEndTest() {
        String str = "asda";
        System.out.println(StringUtils.removeEnd(str, "@"));
        System.out.println(StringUtils.removeEnd(str, "a"));
    }

    @Test
    public void appendIfNotContainTest() {
        String str = "http://cacax.com";
        System.out.println(StringUtils.appendIfNotContain(str, "?", "&"));
        str = "http://cacax.com?1231=1";
        System.out.println(StringUtils.appendIfNotContain(str, "?", "&"));
    }

    @Test
    public void appendIfNotEndsWithTest() {
        String str = "http://cacax.com";
        System.out.println(StringUtils.appendIfNotEndsWith(str, "?", "&"));
        str = "http://cacax.com?";
        System.out.println(StringUtils.appendIfNotEndsWith(str, "?", "&"));
    }

    @Test
    public void endWithTest() {
        String str="asad";
        System.out.println(StringUtils.endWith(str, 'q'));
        System.out.println(StringUtils.endWith(str, 'd'));
    }

}
