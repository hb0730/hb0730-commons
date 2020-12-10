package com.hb0730.commons.encrypt.digest;

import com.hb0730.commons.encrypt.constant.Algorithm;
import com.hb0730.commons.lang.codec.HexUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 摘要算法MD5
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class MD5Utils {
    /**
     * md5加密
     *
     * @param data 明文,不为空
     * @return 密文（16进制）
     */
    public static String md5(String data) {
        byte[] bytes = md5(data.getBytes());
        return HexUtils.encodeHexString(bytes, true);
    }

    /**
     * md5加密
     *
     * @param data 明文,不为空
     * @return 密文
     */
    public static byte[] md5(byte[] data) {
        if (data.length == 0) {
            throw new IllegalArgumentException("data must be not null");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance(Algorithm.MD5.getAlgorithm());
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("MD5 error", e);
        }
    }

    /**
     * MD5加密（加盐，多加密一次）
     *
     * @param data 加密内容
     * @param salt 盐
     * @return 密文（16进制）
     */
    public static String md5(String data, String salt) {
        byte[] bytes = md5WithSalt(data.getBytes(), salt.getBytes(), 1);
        return HexUtils.encodeHexString(bytes, true);
    }

    /**
     * MD5加密（加盐，多加密一次）
     *
     * @param data 加密内容
     * @param salt 盐
     * @return 密文
     */
    public static byte[] md5(byte[] data, byte[] salt) {
        return md5WithSalt(data, salt, 1);
    }

    /**
     * MD5加密（加盐，多加密hashCount次）
     *
     * @param data      加密内容
     * @param salt      盐
     * @param hashCount 哈希次数
     * @return 密文（16进制）
     */
    public static String md5(String data, String salt, int hashCount) {
        byte[] bytes = md5WithSalt(data.getBytes(), salt.getBytes(), hashCount);
        return HexUtils.encodeHexString(bytes, true);
    }

    /**
     * MD5加密（加盐，多加密hashCount次）
     *
     * @param data      加密内容，不为空
     * @param salt      盐
     * @param hashCount 哈希次数
     * @return 密文
     */
    public static byte[] md5(byte[] data, byte[] salt, int hashCount) {
        return md5WithSalt(data, salt, hashCount);
    }

    /**
     * md5加密(加盐)
     *
     * @param data      文明
     * @param salt      盐值
     * @param hashCount hash次数
     * @return 密文
     */
    private static byte[] md5WithSalt(byte[] data, byte[] salt, int hashCount) {
        if (data.length == 0) {
            throw new IllegalArgumentException("data must be not null");
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(Algorithm.MD5.getAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("md5 error", e);
        }
        if (salt != null) {
            digest.reset();
            digest.update(salt);
        }
        byte[] bytes = digest.digest(data);
        for (int i = 0; i < hashCount; i++) {
            digest.reset();
            bytes = digest.digest(bytes);
        }
        return bytes;
    }
}
