package com.hb0730.commons.lang.constants;

/**
 * 正则表达
 *
 * @author bing_huang
 * @since 1.0.1
 */
public final class RegexConstant {
    /**
     * 特殊字符
     */
    public static final String[] SPECIAL_CHARS = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};

    /**
     * email
     */
    public static final String EMAIL = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";

    /**
     * 中国居民身份证,居民身份证号码15位或18位，最后一位可能是数字或字母
     */
    public static final String CHINESE_ID_CARD = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";

    /**
     * 中国手机号码，不支持国际化,国际化请看 <a href="https://github.com/google/libphonenumber">libphonenumber</a>
     */
    public static final String CHINA_MOBILE = "^(1)\\d{10}$";

    /**
     * 验证固定电话号码
     */
    public static final String PHONE = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";

    /**
     * 验证整数（正整数和负整数）
     */
    public static final String DIGIT = "\\-?[1-9]\\d+";

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     */
    public static final String DECIMALS = "\\-?[1-9]\\d+(\\.\\d+)?";

    /**
     * 验证空白字符
     */
    public static final String BLANK_SPACE = "\\s+";

    /**
     * 是否为中文
     */
    public static final String ZH = "^[\u4E00-\u9FA5]+$";

    /**
     * 验证日期（年月日）<br>
     * 格式：y-M-d | y.M.d | y/M/d
     */
    public static final String BIRTHDAY = "^\\d{4}(-|/|.)\\d{1,2}\\1\\d{1,2}$";

    /**
     * 验证URL地址合法性
     */
    public static final String URL = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";

    /**
     * 匹配中国邮政编码
     */
    public static final String CHINA_POST_CODE = "[1-9]\\d{5}";

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     * ipv4
     */
    public static final String IP_ADDRESS = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";

    /**
     * 16进制
     *
     * @since 2.1.0
     */
    public static final String HEX = "^[a-f0-9]+$";

}
