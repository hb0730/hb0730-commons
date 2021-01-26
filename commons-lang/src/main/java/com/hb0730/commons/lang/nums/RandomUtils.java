package com.hb0730.commons.lang.nums;

import com.hb0730.commons.lang.StringUtils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class RandomUtils {
    public static final String BASE_NUMBER = "0123456789";
    public static final String BASE_LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String BASE_CHAR = "=/+-*_~!#@%^&()|.`:";
    public static final String BASE_LETTER_NUMBER = BASE_NUMBER + BASE_LETTER;
    public static final String BASE_CHAR_LETTER_NUMBER = BASE_NUMBER + BASE_LETTER + BASE_CHAR;

    /**
     * 获取随机数生成器对象<br>
     * 是JDK 7之后提供并发产生随机数，能够解决多个线程发生的竞争争夺
     *
     * @return {@link ThreadLocalRandom}
     * @see <a href="https://www.jianshu.com/p/9c2198586f9b">ThreadLocalRandom</a>
     * @see <a href="https://www.jianshu.com/p/89dfe990295c">ThreadLocalRandom用法</a>
     * @since 2.0.0
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获取一个固定数字长度的随机整数
     *
     * @param length 数字长度
     * @return String
     */
    public static String randomNumber(int length) {
        return randomString(BASE_NUMBER, length);
    }

    /**
     * 获取一个随机的字符串
     *
     * @param length     字符串长度
     * @param withNumber 是否包含数字
     * @return String
     */
    public static String randomString(int length, boolean withNumber) {
        if (withNumber) {
            return randomString(BASE_LETTER_NUMBER, length);
        } else {
            return randomString(BASE_LETTER, length);
        }
    }

    /**
     * 获取一个随机的字符串,含有特殊字符
     *
     * @param length 字符串长度
     * @return String
     */
    public static String randomStringWithSpecialChar(int length) {
        return randomString(BASE_CHAR_LETTER_NUMBER, length);
    }

    /**
     * 获得一个随机的字符串
     *
     * @param baseString 随机字符选取的样本
     * @param length     字符串的长度
     * @return 随机字符串
     * @since 2.0.0
     */
    public static String randomString(String baseString, int length) {
        if (StringUtils.isBlank(baseString)) {
            return "";
        }
        StringBuilder builder = new StringBuilder(length);
        if (length < 1) {
            length = 1;
        }
        int baseLength = baseString.length();
        for (int i = 0; i < length; i++) {
            int number = randomInt(baseLength);
            builder.append(baseString.charAt(number));
        }
        return builder.toString();
    }

    /**
     * 获取UUID
     *
     * @return String
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取某个区间的整形,[min,max)
     *
     * @param min 最小值
     * @param max 最大值
     * @return int
     */
    public static int randomInt(int min, int max) {
        return getRandom().nextInt(min, max);
    }

    /**
     * 获取指定范围类的随机数,[0,limit)
     *
     * @param limit 限制随机数的范围
     * @return 随机数
     * @since 2.0.0
     */
    public static int randomInt(int limit) {
        return getRandom().nextInt(limit);
    }

    /**
     * 获取一个随机整形值
     *
     * @return int
     */
    public static int randomInt() {
        return getRandom().nextInt();
    }

    /**
     * 随机bytes
     *
     * @param length 随机长度
     * @return bytes
     * @since 2.1.0
     */
    public static byte[] randomBytes(int length) {
        byte[] bytes = new byte[length];
        getRandom().nextBytes(bytes);
        return bytes;
    }


}
