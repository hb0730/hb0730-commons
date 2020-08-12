package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class StringUtilsTest {

    @Test
    public void trimTest() {
        String str = " asas asad ";
        String newStr = StringUtils.trim(str);
        log.info("去除头尾空格:< {} >length:{},new < {} >length: {}", str, str.length(), newStr, newStr.length());
    }


    @Test
    public void trimStartTest() {
        String str = " asas asad ";
        String newStr = StringUtils.trimStart(str);
        log.info("去除头部空格:<{}>length:{},new <{}>length:{}", str, str.length(), newStr, newStr.length());
    }

    @Test
    public void trimEndTest() {
        String str = " asas asad ";
        String newStr = StringUtils.trimEnd(str);
        log.info("去除尾部空格:<{}>length:{},new <{}>length:{}", str, str.length(), newStr, newStr.length());
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
        log.info("判断是否null或者&quot;&quot;");
        String str = "";
        boolean isEmpty = StringUtils.isEmpty(str);
        Assert.assertTrue(str + "为空", isEmpty);
        str = null;
        isEmpty = StringUtils.isEmpty(str);
        Assert.assertTrue(str + "为null", isEmpty);
    }

    @Test
    public void isEmpty1Test() {
        String str = " ";
        boolean isEmpty = StringUtils.isEmpty(str);
        Assert.assertTrue(str + "不为空", isEmpty);
    }

    @Test
    public void isEmpty2Test() {
        String str = "ss";
        boolean isEmpty = StringUtils.isEmpty(str);
        Assert.assertTrue(str + "不为null", isEmpty);
    }


    @Test
    public void containsTest() {
        String str = "asasas";
        boolean contains = StringUtils.contains(str, 'a');
        Assert.assertTrue(contains);
        log.info("是否包含特殊字符:{}包含'a'? {}", str, contains);

    }

    @Test
    public void testContainsTest() {
        String str = "asasad";
        boolean contains = StringUtils.contains(str, "sa");
        log.info("是否包含特殊字符串: {} 包含 sa ? {}", str, contains);
        Assert.assertTrue(contains);
    }

    @Test
    public void inStringIgnoreCaseTest() {
        log.info("是否包含特定字符串(不区分大小写),");
        String str = "asd";
        boolean contains = StringUtils.inStringIgnoreCase(str, "a", "as", "asd", "asds");
        Assert.assertTrue(contains);

        contains = StringUtils.inStringIgnoreCase(str, "a", "as", "asds");
        Assert.assertTrue("不包含给定的字符串", contains);
    }

    @Test
    public void ensureSuffixTest() {
        String str = "asas@qq.com";
        String newStr = StringUtils.ensureSuffix(str, "@qq.com");
        log.info("确保字符串包含后缀, 原 {},新 {}", str, newStr);
        Assert.assertEquals(str, newStr);

        str = "asad";
        newStr = StringUtils.ensureSuffix(str, "@qq.com");
        log.info("确保字符串包含后缀, 原 {},新 {}", str, newStr);
        Assert.assertEquals("原字符串不以@qq.com结尾", str, newStr);
    }

    @Test
    public void removeEndTest() {
        String str = "asda";
        String newStr = StringUtils.removeEnd(str, "@");
        log.info("删除末尾给定的字符串1.不存在特定结尾: 原 {}, 新 {}", str, newStr);
        Assert.assertEquals(str, newStr);

        newStr = StringUtils.removeEnd(str, "da");
        log.info("删除末尾给定的字符串2.存在特定结尾: 原 {}, 新 {}", str, newStr);
        Assert.assertEquals(str, newStr);
    }

    @Test
    public void appendIfNotContainTest() {
        String str = "http://caca.com";
        String newStr = StringUtils.appendIfNotContain(str, "?", "&");
        log.info("1.追加是否包含特定是否，否则追加其他字符，原 {},现 {}", str, newStr);
        str = "http://cacc.com?asd=as";
        newStr = StringUtils.appendIfNotContain(str, "?", "&");
        log.info("1.追加是否包含特定是否，否则追加其他字符，原 {},现 {}", str, newStr);
    }

    @Test
    public void appendIfNotEndsWithTest() {
        String str = "http://cac.com";
        String newStr = StringUtils.appendIfNotEndsWith(str, "?", "&");
        log.info("1.是否以特定结尾字符追加: 原 {} , 现 {}", str, newStr);

        str = "http://cac.com?";
        newStr = StringUtils.appendIfNotEndsWith(str, "?", "&");
        log.info("1.是否以特定结尾字符追加: 原 {} , 现 {}", str, newStr);
    }

    @Test
    public void endWithTest() {
        log.info("字符串以特定字符结尾");
        String str = "asdda";
        boolean endWith = StringUtils.endWith(str, 'a');
        Assert.assertTrue(endWith);

        endWith = StringUtils.endWith(str, 'd');
        Assert.assertTrue("字符串非特定字符结尾", endWith);
    }

    @Test
    public void isBlankTest() {
        String str = "asd";
        boolean blank = StringUtils.isBlank(str);
        log.info("字符串{}为空? {}", str, blank);

        str = "";
        blank = StringUtils.isBlank(str);
        log.info("字符串{}为空? {}", str, blank);

        str = " ";
        blank = StringUtils.isBlank(str);
        log.info("字符串{}为空? {}", str, blank);

        str = null;
        blank = StringUtils.isBlank(str);
        log.info("字符串{}为空? {}", str, blank);
    }

    @Test
    public void testEqualsTest() {
        boolean equals = StringUtils.equals("", "");
        Assert.assertTrue(equals);
    }

    @Test
    public void testEquals1Test() {
        String str1 = "asd";
        String str2 = "ASD";
        Assert.assertTrue(StringUtils.equals(str1, str2, true));
    }


    @Test
    public void testTrimTest() {
        String str = " ass  asd ";
        String newStr = StringUtils.trim(str, -1);
        log.info("去除头部空格,原:{},现:{}", str, newStr);

        newStr = StringUtils.trim(str, 0);
        log.info("去除全部空格, 原:{},现:{}", str, newStr);

        newStr = StringUtils.trim(str, 1);
        log.info("去除尾部空格, 原:{},现:{}", str, newStr);
    }

    @Test
    public void endWithIgnoreCaseTest() {
        String str = "AsasdDD";
        boolean contains = StringUtils.endWithIgnoreCase(str, "DD");
        Assert.assertTrue(contains);


        contains = StringUtils.endWithIgnoreCase(str, "dd");
        Assert.assertTrue(contains);
    }
}
