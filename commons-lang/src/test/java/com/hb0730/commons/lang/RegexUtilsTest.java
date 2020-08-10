package com.hb0730.commons.lang;

import org.junit.Test;

import java.util.regex.Pattern;

public class RegexUtilsTest {
    @Test
    public void test() {
        String str = "^\\d{4}(-|/|.)\\d{1,2}\\1\\d{1,2}$";
        System.out.println(Pattern.matches(str, "1999.01.01"));
    }

    @Test
    public void isEmailTest() {
        String email = "sasdasd";
        System.out.println(RegexUtils.isEmail(email));
        email = "exadd@gmail.com";
        System.out.println(RegexUtils.isEmail(email));
    }

    @Test
    public void isZHTest() {
        String zh = "sasd";
        System.out.println(RegexUtils.isZH(zh));
        zh = "h很好h";
        System.out.println(RegexUtils.isZH(zh));
        zh = "很好";
        System.out.println(RegexUtils.isZH(zh));
    }

    @Test
    public void isChineseIdCardTest() {
        String zh = "123112351234182865821";
        System.out.println(RegexUtils.isChineseIdCard(zh));
        zh = "350212198809072352";
        System.out.println(RegexUtils.isChineseIdCard(zh));
        zh = "35021119780422570X";
        System.out.println(RegexUtils.isChineseIdCard(zh));
    }

    @Test
    public void isMobileTest() {
        String mobile = "1234123123112";
        System.out.println(RegexUtils.isMobile(mobile));
        mobile = "12asd123123112";
        System.out.println(RegexUtils.isMobile(mobile));
        mobile = "13000009999";
        System.out.println(RegexUtils.isMobile(mobile));
    }

    @Test
    public void isPhoneTest() {
        String phone = "010-88888888";
        System.out.println(RegexUtils.isPhone(phone));
    }

    @Test
    public void isDigitTest() {
        String digit = "123.2";
        System.out.println(RegexUtils.isDigit(digit));
        digit = "-123";
        System.out.println(RegexUtils.isDigit(digit));
        digit = "123";
        System.out.println(RegexUtils.isDigit(digit));
    }

    @Test
    public void isDecimalsTest() {
        String digit = "123.2";
        System.out.println(RegexUtils.isDecimals(digit));
        digit = "-123.2";
        System.out.println(RegexUtils.isDecimals(digit));
        digit = "-123";
        System.out.println(RegexUtils.isDecimals(digit));
        digit = "123";
        System.out.println(RegexUtils.isDecimals(digit));
    }

    @Test
    public void isBlankTest() {
        String blank = "";
        System.out.println(RegexUtils.isBlank(blank));
        blank = "\r\n";
        System.out.println(RegexUtils.isBlank(blank));
    }

    @Test
    public void isBirthdayTest() {
        String birthday = "2020/08/10";
        System.out.println(RegexUtils.isBirthday(birthday));
        birthday = "2020.08.10";
        System.out.println(RegexUtils.isBirthday(birthday));
        birthday = "1989-09-03";
        System.out.println(RegexUtils.isBirthday(birthday));
    }

    @Test
    public void isURLTest() {
        String url="www.baidu.com";
        System.out.println(RegexUtils.isURL(url));
        url="http://baidu.com";
        System.out.println(RegexUtils.isURL(url));
        url="https://baidu.com";
        System.out.println(RegexUtils.isURL(url));
        url="http://www.baidu.com";
        System.out.println(RegexUtils.isURL(url));
    }

    @Test
    public void isChinaPostCodeTest() {
        String postCode ="100000";
        System.out.println(RegexUtils.isChinaPostCode(postCode));
    }
}
