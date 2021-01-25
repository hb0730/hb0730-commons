package com.hb0730.commons.lang.codec;

import com.hb0730.commons.lang.constants.Charsets;

import java.nio.charset.Charset;

/**
 * 16进制转换
 * <p>
 * 来自hutool,apache-commons-codec
 *
 * @author bing_huang
 * @since 2.0.3
 */
public class HexUtils {
    /**
     * 16进制 小写 0-9,a-f
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'};

    /**
     * 16进制大写0-9,A-F
     */
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F'};

    /**
     * 将字节数组转换为十六进制字符串,默认小写,utf-8
     *
     * @param data 被编码的字符串
     * @return 十六进制String
     */
    public static String encodeHexString(String data) {
        return encodeHexString(data, Charsets.UTF_8);
    }

    /**
     * 将字节数组转换为十六进制字符串,默认小写
     *
     * @param data    被编码的字符串
     * @param charset 编码
     * @return 十六进制String
     */
    public static String encodeHexString(String data, String charset) {
        return encodeHexString(data, com.hb0730.commons.lang.Charsets.toCharset(charset));
    }

    /**
     * 将字节数组转换为十六进制字符串,默认小写
     *
     * @param data    被编码的字符串
     * @param charset 编码
     * @return 十六进制String
     */
    public static String encodeHexString(String data, Charset charset) {
        return encodeHexString(data, charset, true);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data        被编码的字符串
     * @param charset     编码
     * @param toLowerCase <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
     * @return 十六进制String
     */
    public static String encodeHexString(String data, Charset charset, boolean toLowerCase) {
        return encodeHexString(data.getBytes(charset), toLowerCase);
    }

    /**
     * 将字节数组转换为十六进制字符串,默认小写
     *
     * @param data byte[]
     * @return 十六进制String
     * @since 2.1.0
     */
    public static String encodeHexString(byte[] data) {
        return encodeHexString(data, false);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data        byte[]
     * @param toLowerCase <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
     * @return 十六进制String
     */
    public static String encodeHexString(byte[] data, boolean toLowerCase) {
        return encodeHexString(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * 将字节数组转换为十六进制字符数组,默认小写，默认utf-8
     *
     * @param data 字符串
     * @return 十六进制char[]
     */
    public static char[] encodeHex(String data) {
        return encodeHex(data, Charsets.UTF_8);
    }

    /**
     * 将字节数组转换为十六进制字符数组,默认小写
     *
     * @param data    字符串
     * @param charset 编码
     * @return 十六进制char[]
     */
    public static char[] encodeHex(String data, Charset charset) {
        return encodeHex(data, charset, true);
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data        字符串
     * @param charset     编码
     * @param toLowerCase <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
     * @return 十六进制char[]
     */
    public static char[] encodeHex(String data, Charset charset, boolean toLowerCase) {
        return encodeHex(data.getBytes(charset), toLowerCase);
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data byte[]
     * @return 十六进制char[]
     */
    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data        byte[]
     * @param toLowerCase <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
     * @return 十六进制char[]
     */
    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * 将十六进制字符数组转换为字符串
     *
     * @param data 十六进制string
     * @return 字符串
     */
    public static String decodeHexStr(String data) {
        return decodeHexStr(data, Charsets.UTF_8);
    }

    /**
     * 将十六进制字符数组转换为字符串
     *
     * @param data    十六进制string
     * @param charset 编码
     * @return 字符串
     */
    public static String decodeHexStr(String data, Charset charset) {
        return decodeHexStr(data.toCharArray(), charset);
    }

    /**
     * 将十六进制字符数组转换为字符串，默认utf-8
     *
     * @param data 十六进制char[]
     * @return 字符串
     */
    public static String decodeHexStr(char[] data) {
        return decodeHexStr(data, Charsets.UTF_8);
    }

    /**
     * 将十六进制字符数组转换为字符串
     *
     * @param data    十六进制char[]
     * @param charset 编码
     * @return 字符串
     */
    public static String decodeHexStr(char[] data, Charset charset) {
        return new String(decodeHex(data), charset);
    }

    /**
     * 将十六进制字符数组转换为字符串
     *
     * @param data 十六进制String
     * @return 字符串
     */
    public static byte[] decodeHex(final String data) {
        return decodeHex(data.toCharArray());
    }

    /**
     * 将十六进制字符数组转换为字节数组
     *
     * @param data 十六进制char[]
     * @return byte[]
     * @throws IllegalArgumentException 如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
     */
    public static byte[] decodeHex(final char[] data) {
        int len = data.length;

        if ((len & 0x01) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data     byte[]
     * @param toDigits 用于控制输出的char[]
     * @return 十六进制String
     */
    private static String encodeHexString(final byte[] data, final char[] toDigits) {
        return new String(encodeHex(data, toDigits));
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data     byte[] 内容
     * @param toDigits 用于控制输出的char[]
     * @return 十六进制char[]
     */
    private static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1]; // len*2
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4]; //高位
            out[j++] = toDigits[0x0F & data[i]]; //低位
        }
        return out;
    }

    /**
     * 将十六进制字符转换成一个整数
     *
     * @param ch    十六进制char
     * @param index 十六进制字符在字符数组中的位置
     * @return 一个整数
     * @throws IllegalArgumentException 当ch不是一个合法的十六进制字符时，抛出运行时异常
     */
    private static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new IllegalArgumentException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }
}
