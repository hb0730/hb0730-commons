package com.hb0730.commons.lang.codec;

import com.hb0730.commons.lang.constants.CharsetConst;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Java 8{@link java.util.Base64}封装
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class Base64Utils {

    /**
     * base64 编码
     *
     * @param src 原字节数组
     * @return 编码后的字节数组
     */
    public static byte[] encode(byte[] src) {
        if (src.length == 0) {
            return src;
        }
        return Base64.getEncoder().encode(src);
    }

    /**
     * {@link Base64}解码
     *
     * @param src 原字节数组
     * @return 解码后的字节数组
     */
    public static byte[] decode(byte[] src) {
        if (src.length == 0) {
            return src;
        }
        return Base64.getDecoder().decode(src);
    }

    /**
     * {@link Base64} URL 编码
     *
     * @param src 原字节数组
     * @return 编码后的字节数组
     */
    public static byte[] encodeUrlSafe(byte[] src) {
        if (src.length == 0) {
            return src;
        }
        return Base64.getUrlEncoder().encode(src);
    }

    /**
     * {@link Base64} URL解码
     *
     * @param src 原字节数组
     * @return 解码后的字节数组
     */
    public static byte[] decodeUrlSafe(byte[] src) {
        if (src.length == 0) {
            return src;
        }
        return Base64.getUrlDecoder().decode(src);
    }

    /**
     * {@link Base64} 编码转字符串，默认编码{@link java.nio.charset.StandardCharsets#UTF_8}
     *
     * @param src     原字节数组
     * @param charset 编码，可以为空，为空，则以UTF-8编码
     * @return 编码后的字符串
     */
    public static String encodeToString(byte[] src, Charset charset) {
        if (src.length == 0) {
            return "";
        }
        return new String(encode(src), charset);
    }

    /**
     * {@link Base64} 编码转字符串，默认编码{@link java.nio.charset.StandardCharsets#UTF_8}
     *
     * @param src     原字节数组
     * @param charset 编码，可以为空，为空，则以UTF-8编码
     * @return 编码后的字符串
     */
    public static String encodeToString(byte[] src, String charset) {
        return encodeToString(src, Charset.forName(charset));
    }

    /**
     * {@link Base64} 编码转字符串,以{@link java.nio.charset.StandardCharsets#UTF_8}编码
     *
     * @param src 原字节数组
     * @return 编码后的字符串
     */
    public static String encodeToString(byte[] src) {
        return encodeToString(src, CharsetConst.UTF_8);
    }

    /**
     * {@link Base64}解码，
     *
     * @param src     原字符串
     * @param charset 编码集
     * @return 解码后的字节组
     */
    public static byte[] decodeFromString(String src, Charset charset) {
        if (src.isEmpty()) {
            return new byte[0];
        }
        return decode(src.getBytes(charset));
    }

    /**
     * {@link Base64}解码，
     *
     * @param src     原字符串
     * @param charset 编码集
     * @return 解码后的字节组
     */
    public static byte[] decodeFromString(String src, String charset) {
        return decodeFromString(src, Charset.forName(charset));
    }

    /**
     * {@link Base64}解码，{@link CharsetConst#UTF_8}
     *
     * @param src 原字符串
     * @return 解码后的字节组
     */
    public static byte[] decodeFromString(String src) {
        return decodeFromString(src, CharsetConst.UTF_8);
    }

    /**
     * {@link Base64}，URL编码
     *
     * @param src     原字节组
     * @param charset 编码
     * @return 编码后的字符串
     */
    public static String encodeToUrlSafeString(byte[] src, Charset charset) {
        return new String(encodeUrlSafe(src)
                , charset);
    }

    /**
     * {@link Base64}，URL编码
     *
     * @param src     原字节组
     * @param charset 编码
     * @return 编码后的字符串
     */
    public static String encodeToUrlSafeString(byte[] src, String charset) {
        return encodeToUrlSafeString(src, Charset.forName(charset));
    }

    /**
     * {@link Base64}，URL编码，默认{@link CharsetConst#UTF_8}
     *
     * @param src 原字节组
     * @return 编码后的字符串
     */
    public static String encodeToUrlSafeString(byte[] src) {
        return encodeToUrlSafeString(src, CharsetConst.UTF_8);
    }

    /**
     * {@link Base64}，URL解码
     *
     * @param src     原字符串
     * @param charset 编码类型
     * @return 解码后的字节组
     */
    public static byte[] decodeFromUrlSafeString(String src, Charset charset) {
        return decodeUrlSafe(src.getBytes(charset));
    }

    /**
     * {@link Base64}，URL解码
     *
     * @param src     原字符串
     * @param charset 编码类型
     * @return 解码后的字节组
     */
    public static byte[] decodeFromUrlSafeString(String src, String charset) {
        return decodeFromUrlSafeString(src, Charset.forName(charset));
    }

    /**
     * {@link Base64}，URL，{@link CharsetConst#UTF_8}解码
     *
     * @param src 原字符串
     * @return 解码后的字节组
     */
    public static byte[] decodeFromUrlSafeString(String src) {
        return decodeFromUrlSafeString(src, CharsetConst.UTF_8);
    }

}
