package com.hb0730.commons.encrypt.digest;

import com.hb0730.commons.encrypt.constant.Algorithm;
import com.hb0730.commons.lang.codec.HexUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA工具类
 *
 * @author bing_huang
 * @see Digesters
 * @since 1.0.2
 * @deprecated 2.1.0
 */
public class SHAUtils {
    /**
     * SHA加密
     *
     * @param data 文明
     * @return 密文
     */
    public static String sha(String data) {
        return sha(data, Algorithm.SHA);
    }

    /**
     * SHA加密
     *
     * @param data 文明
     * @return 密文
     */
    public static byte[] sha(byte[] data) {
        return sha(data, Algorithm.SHA);
    }

    /**
     * SHA1加密
     *
     * @param data 文明
     * @return 密文
     */
    public static String sha1(String data) {
        return sha(data, Algorithm.SHA1);
    }

    /**
     * SHA1加密
     *
     * @param data 文明
     * @return 密文
     */
    public static byte[] sha1(byte[] data) {
        return sha(data, Algorithm.SHA1);
    }

    /**
     * SHA224加密
     *
     * @param data 文明
     * @return 密文
     */
    public static String sha224(String data) {
        return sha(data, Algorithm.SHA224);
    }

    /**
     * SHA224加密
     *
     * @param data 文明
     * @return 密文
     */
    public static byte[] sha224(byte[] data) {
        return sha(data, Algorithm.SHA224);
    }

    /**
     * SHA256加密
     *
     * @param data 文明
     * @return 密文
     */
    public static String sha256(String data) {
        return sha(data, Algorithm.SHA256);
    }

    /**
     * SHA256加密
     *
     * @param data 文明
     * @return 密文
     */
    public static byte[] sha256(byte[] data) {
        return sha(data, Algorithm.SHA256);
    }

    /**
     * SHA384加密
     *
     * @param data 文明
     * @return 密文
     */
    public static String sha384(String data) {
        return sha(data, Algorithm.SHA384);
    }

    /**
     * SHA384加密
     *
     * @param data 文明
     * @return 密文
     */
    public static byte[] sha384(byte[] data) {
        return sha(data, Algorithm.SHA384);
    }

    /**
     * SHA512加密
     *
     * @param data 文明
     * @return 密文
     */
    public static String sha512(String data) {
        return sha(data, Algorithm.SHA512);
    }

    /**
     * SHA512加密
     *
     * @param data 文明
     * @return 密文
     */
    public static byte[] sha512(byte[] data) {
        return sha(data, Algorithm.SHA512);
    }

    /**
     * SHA3-224加密（JDK9以上）
     *
     * @param data 文明
     * @return 密文
     */
    public static String sha3_224(String data) {
        return sha(data, Algorithm.SHA3_224);
    }

    /**
     * SHA3-224加密（JDK9以上）
     *
     * @param data 文明
     * @return 密文
     */
    public static byte[] sha3_224(byte[] data) {
        return sha(data, Algorithm.SHA3_224);
    }

    /**
     * SHA3-256加密（JDK9以上）
     *
     * @param data 文明
     * @return 密文
     */
    public static String sha3_256(String data) {
        return sha(data, Algorithm.SHA3_256);
    }

    /**
     * SHA3-256加密（JDK9以上）
     *
     * @param data 文明
     * @return 密文
     */
    public static byte[] sha3_256(byte[] data) {
        return sha(data, Algorithm.SHA3_256);
    }

    /**
     * SHA3-384加密（JDK9以上）
     *
     * @param data 文明
     * @return 密文
     */
    public static String sha3_384(String data) {
        return sha(data, Algorithm.SHA3_384);
    }

    /**
     * SHA3-384加密（JDK9以上）
     *
     * @param data 文明
     * @return 密文
     */
    public static byte[] sha3_384(byte[] data) {
        return sha(data, Algorithm.SHA3_384);
    }

    /**
     * SHA3-512加密（JDK9以上）
     *
     * @param data 文明
     * @return 密文
     */
    public static String sha3_512(String data) {
        return sha(data, Algorithm.SHA3_512);
    }

    /**
     * SHA3-512加密(JDK9以上)
     *
     * @param data 文明
     * @return 密文
     */
    public static byte[] sha3_512(byte[] data) {
        return sha(data, Algorithm.SHA3_512);
    }

    /**
     * 字符串 SHA 加密
     *
     * @param data      加密内容
     * @param algorithm 算法类型
     * @return 密文（16进制）
     */
    public static String sha(String data, Algorithm algorithm) {
        byte[] bytes = sha(data.getBytes(), algorithm);
        return HexUtils.encodeHexString(bytes, true);
    }

    /**
     * sha 加密
     *
     * @param data      明文
     * @param algorithm 加密类型
     * @return 密文
     */
    public static byte[] sha(byte[] data, Algorithm algorithm) {
        if (data.length == 0) {
            throw new IllegalArgumentException("data must be not null");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm.getAlgorithm());
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("SHA error", e);
        }
    }
}
