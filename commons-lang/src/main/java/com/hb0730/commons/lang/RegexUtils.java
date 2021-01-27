package com.hb0730.commons.lang;

import com.hb0730.commons.lang.constants.RegexConstant;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类，验证数据是否符合规范
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class RegexUtils {

    /**
     * 判断字符串是否符合正则
     *
     * @param str   需要校验的字符串
     * @param regex 正则表达式
     * @return true:是否存在符合正则
     */
    public static boolean find(final String str, final String regex) {
        return Pattern.compile(regex).matcher(str).find();
    }

    /**
     * 给定内容是否匹配正则
     *
     * @param regex   正则
     * @param content 内容
     * @return 正则为null或者""则不检查，返回false，内容为null返回false
     * @since 2.1.0
     */
    public static boolean isMatch(String regex, CharSequence content) {
        if (StringUtils.isBlank(regex) || null == content) {
            return false;
        }
        //达式'.'可以匹配任意字符，包括表示一行的结束符。默认情况下，表达式'.'不匹配行的结束符。
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        return isMatch(pattern, content);
    }

    /**
     * 给定内容是否匹配正则
     *
     * @param pattern 模式
     * @param content 内容
     * @return 正则为null或者""则不检查，返回true，内容为null返回false
     * @since 2.1.0
     */
    public static boolean isMatch(Pattern pattern, CharSequence content) {
        if (null == pattern || null == content) {
            return false;
        }
        return pattern.matcher(content).matches();
    }

    /**
     * 校验参数是否为mail格式
     *
     * @param email email格式
     * @return 是否符合email格式
     */
    public static boolean isEmail(final String email) {
        return Pattern.matches(RegexConstant.EMAIL, email);
    }

    /**
     * 校验参数是否为汉字
     *
     * @param chinese 字符串
     * @return 是否为汉字
     */
    public static boolean isZH(String chinese) {
        return Pattern.matches(RegexConstant.ZH, chinese);
    }

    /**
     * 校验参数是否为符合中国居民身份证
     *
     * @param idCard idCard
     * @return 是否符合
     */
    public static boolean isChineseIdCard(String idCard) {
        return Pattern.matches(RegexConstant.CHINESE_ID_CARD, idCard);
    }

    /**
     * 验证手机号码,不支持国际化
     *
     * @param mobile 手机号码
     * @return 是否合法
     * @see RegexConstant#CHINA_MOBILE
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(RegexConstant.CHINA_MOBILE, mobile);
    }

    /**
     * 验证固定电话
     *
     * @param phone 固定电话
     * @return 是否符合
     */
    public static boolean isPhone(String phone) {
        return Pattern.matches(RegexConstant.PHONE, phone);
    }

    /**
     * 验证整数(正，负)
     *
     * @param digit 整数
     * @return 是否符合
     */
    public static boolean isDigit(String digit) {
        return Pattern.matches(RegexConstant.DIGIT, digit);
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals 整数和浮点数
     * @return 是否符合
     */
    public static boolean isDecimals(String decimals) {
        return Pattern.matches(RegexConstant.DECIMALS, decimals);
    }

    /**
     * 验证空白字符,包括：空格、\t、\n、\r、\f、\x0B
     *
     * @param space 空白字符
     * @return 是否符合
     */
    public static boolean isBlank(String space) {
        return Pattern.matches(RegexConstant.BLANK_SPACE, space);
    }

    /**
     * 验证日期（年月日）,包含(y-M-d,y.m.d,y/M/d)
     *
     * @param birthday 日期
     * @return 是否符合
     */
    public static boolean isBirthday(String birthday) {
        return Pattern.matches(RegexConstant.BIRTHDAY, birthday);
    }

    /**
     * 验证URL地址
     *
     * @param value url
     * @return 是否为URL
     */
    public static boolean isURL(String value) {
        try {
            new URL(value);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }

    /**
     * 验证中国邮编
     *
     * @param value 邮编
     * @return 是否符合
     */
    public static boolean isChinaPostCode(String value) {
        Pattern pattern = Pattern.compile(RegexConstant.CHINA_POST_CODE);
        return isMatch(pattern, value);
    }

    /**
     * 验证是否为Hex（16进制）字符串
     *
     * @param value 值
     * @return 是否为Hex（16进制）字符串
     * @since 2.1.0
     */
    public static boolean isHex(String value) {
        //大小写不明感
        Pattern pattern = Pattern.compile(RegexConstant.HEX, Pattern.CASE_INSENSITIVE);
        return isMatch(pattern, value);
    }
}
