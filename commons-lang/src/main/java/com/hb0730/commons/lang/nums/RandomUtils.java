package com.hb0730.commons.lang.nums;

import java.util.Random;
import java.util.UUID;

/**
 * 随机数
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class RandomUtils {
    public static final char[] N = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static final char[] C = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Z', 'Y', 'Z'};
    public static final char[] S = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Z', 'Y', 'Z'};
    public static final char[] SC = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Z', 'Y', 'Z', '=', '/', '+', '-', '*', '_', '~', '!', '#', '@', '%', '^', '&', '(', ')', '|', '.', '`', ':'};

    /**
     * 获取一个固定长度的随机整数
     *
     * @param size 数字长度
     * @return String
     */
    public static String randomNumber(int size) {
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        for (int i = 0; i < size; i++) {
            sb.append(N[rd.nextInt(N.length)]);
        }
        return sb.toString();
    }

    /**
     * 获取一个随机的字符串
     *
     * @param size       字符串长度
     * @param withNumber 是否包含数字
     * @return String
     */
    public static String randomString(int size, boolean withNumber) {
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        if (withNumber) {
            for (int i = 0; i < size; i++) {
                sb.append(S[rd.nextInt(S.length)]);
            }
        } else {
            for (int i = 0; i < size; i++) {
                sb.append(C[rd.nextInt(C.length)]);
            }
        }
        return sb.toString();
    }

    /**
     * 获取一个随机的字符串,含有特殊字符
     *
     * @param size 字符串长度
     * @return String
     */
    public static String randomStringWithSpecialChar(int size) {
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        for (int i = 0; i < size; i++) {
            sb.append(SC[rd.nextInt(SC.length)]);
        }
        return sb.toString();
    }

    /**
     * 获取UUID
     *
     * @return String
     */
    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 获取某个区间的整形
     *
     * @param min 最小值
     * @param max 最大值
     * @return int
     */
    public static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 获取一个随机整形值
     *
     * @return int
     */
    public static int randomInt() {
        Random random = new Random();
        return random.nextInt();
    }


}
