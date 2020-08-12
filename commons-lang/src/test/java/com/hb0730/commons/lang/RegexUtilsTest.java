package com.hb0730.commons.lang;

import com.hb0730.commons.lang.constants.RegexConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

@Slf4j
public class RegexUtilsTest {
    @Test
    public void test() {
        String str = "^\\d{4}(-|/|.)\\d{1,2}\\1\\d{1,2}$";
        boolean matches = Pattern.matches(str, "1999.01.01");
        Assert.assertTrue(matches);
    }

    @Test
    public void isEmailTest() {
        String email = "exadd@gmail.com";
        Assert.assertTrue("邮箱不合法", RegexUtils.isEmail(email));

        email = "sasdasd";
        Assert.assertTrue("邮箱不合法", RegexUtils.isEmail(email));
    }

    @Test
    public void isZHTest() {
        String zh = "很好";
        boolean matches = RegexUtils.isZH(zh);
        log.info("是否为中文: {} 中文 ? {}", zh, matches);
        zh = "h很好h";
        matches = RegexUtils.isZH(zh);
        log.info("是否为中文: {} 中文 ? {}", zh, matches);
        zh = "asd";
        matches = RegexUtils.isZH(zh);
        log.info("是否为中文: {} 中文 ? {}", zh, matches);
    }

    @Test
    public void isChineseIdCardTest() {
        String idCard = "123112351234182865821";
        boolean matches = RegexUtils.isChineseIdCard(idCard);
        log.info("中国居民身份证: {} 符合? {}", idCard, matches);
        idCard = "350212198809072352";
        matches = RegexUtils.isChineseIdCard(idCard);
        log.info("中国居民身份证: {} 符合? {}", idCard, matches);
        idCard = "35021119780422570X";
        matches = RegexUtils.isChineseIdCard(idCard);
        log.info("中国居民身份证: {} 符合? {}", idCard, matches);
    }

    @Test
    public void isMobileTest() {
        String mobile = "1234123123112";
        boolean matches = RegexUtils.isMobile(mobile);
        log.info("中国移动电话: {} 符合? {}", mobile, matches);
        mobile = "12asd123123112";
        matches = RegexUtils.isMobile(mobile);
        log.info("中国移动电话: {} 符合? {}", mobile, matches);
        mobile = "13000009999";
        matches = RegexUtils.isMobile(mobile);
        log.info("中国移动电话: {} 符合? {}", mobile, matches);
    }

    @Test
    public void isPhoneTest() {
        String phone = "010-88888888";
        Assert.assertTrue("固定电话不符合", RegexUtils.isPhone(phone));
    }

    @Test
    public void isDigitTest() {
        String digit = "123";
        Assert.assertTrue("非正负整数", RegexUtils.isDigit(digit));
    }

    @Test
    public void isDecimalsTest() {
        String digit = "123.3";
        Assert.assertTrue("非正负整数或者浮点数", RegexUtils.isDecimals(digit));
    }

    @Test
    public void isBlankTest() {
        String blank = " ";
        Assert.assertTrue("非空白符", RegexUtils.isBlank(blank));
        blank = "";
        Assert.assertTrue("非空白符", RegexUtils.isBlank(blank));
    }

    @Test
    public void isBirthdayTest() {
        String birthday = "2020/08/10";
        Assert.assertTrue("日期不合法,支持y-M-d,y/M/d,y.M.d", RegexUtils.isBirthday(birthday));
    }

    @Test
    public void isURLTest() {
        String url = "www.baidu.com";
        Assert.assertTrue("非法网址", RegexUtils.isURL(url));
    }

    @Test
    public void isChinaPostCodeTest() {
        String postCode = "100000";
        Assert.assertTrue("中国邮编不正确", RegexUtils.isChinaPostCode(postCode));
    }

    @Test
    public void findTest() {
        String birthday = "2020-08-10";
        Assert.assertTrue("不符合正则" + RegexConstant.BIRTHDAY, RegexUtils.find(birthday, RegexConstant.BIRTHDAY));
    }


}
