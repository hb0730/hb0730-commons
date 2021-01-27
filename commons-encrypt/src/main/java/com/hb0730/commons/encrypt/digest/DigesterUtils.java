package com.hb0730.commons.encrypt.digest;

import com.hb0730.commons.encrypt.constant.GlobalBouncyCastleProvider;
import com.hb0730.commons.lang.constants.Charsets;

import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.security.Provider;

/**
 * 摘要算法工具类
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class DigesterUtils {
    //-----------------md5

    /**
     * 计算32位MD5摘要值
     *
     * @param data 被摘要数据
     * @return MD5摘要
     */
    public static byte[] md5(byte[] data) {
        return new MD5(getBouncyCastleProvider()).digest(data);
    }

    /**
     * 计算32位MD5摘要值
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return MD5摘要
     */
    public static byte[] md5(String data, String charset) {
        return new MD5(getBouncyCastleProvider()).digest(data, charset);
    }

    /**
     * 计算32位MD5摘要值，使用UTF-8编码
     *
     * @param data 被摘要数据
     * @return MD5摘要
     */
    public static byte[] md5(String data) {
        return md5(data, Charsets.UTF_8_NAME);
    }

    /**
     * 计算32位MD5摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex(String data) {
        return md5Hex(data, Charsets.UTF_8_NAME);
    }

    /**
     * 计算32位MD5摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex(String data, String charset) {
        return new MD5(getBouncyCastleProvider()).digestHex(data, charset);
    }

    /**
     * 计算32位MD5摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex(String data, Charset charset) {
        return new MD5(getBouncyCastleProvider()).digestHex(data, charset);
    }

    /**
     * 计算16位MD5摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex16(String data, Charset charset) {
        return new MD5(getBouncyCastleProvider()).digestHex16(data, charset);
    }

    /**
     * 计算16位MD5摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return MD5摘要的16进制表示
     * @since 4.6.0
     */
    public static String md5Hex16(String data) {
        return md5Hex16(data, Charsets.UTF_8);
    }

    /**
     * 32位MD5转16位MD5
     *
     * @param md5Hex 32位MD5
     * @return 16位MD5
     */
    public static String md5HexTo16(String md5Hex) {
        return md5Hex.substring(8, 24);
    }
    // ------------------------------------------------------------------------------------------- SHA-1

    /**
     * 计算SHA-1摘要值
     *
     * @param data 被摘要数据
     * @return SHA-1摘要
     */
    public static byte[] sha1(byte[] data) {
        return new Digester(DigestAlgorithm.SHA1, getBouncyCastleProvider()).digest(data);
    }

    /**
     * 计算SHA-1摘要值
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return SHA-1摘要
     */
    public static byte[] sha1(String data, String charset) {
        return new Digester(DigestAlgorithm.SHA1, getBouncyCastleProvider()).digest(data, charset);
    }

    /**
     * 计算sha1摘要值，使用UTF-8编码
     *
     * @param data 被摘要数据
     * @return MD5摘要
     */
    public static byte[] sha1(String data) {
        return sha1(data, Charsets.UTF_8_NAME);
    }

    /**
     * 计算SHA-1摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return SHA-1摘要的16进制表示
     */
    public static String sha1Hex(String data, String charset) {
        return new Digester(DigestAlgorithm.SHA1, getBouncyCastleProvider()).digestHex(data, charset);
    }

    /**
     * 计算SHA-1摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return SHA-1摘要的16进制表示
     */
    public static String sha1Hex(String data) {
        return sha1Hex(data, Charsets.UTF_8_NAME);
    }
    // ------------------------------------------------------------------------------------------- SHA-256

    /**
     * 计算SHA-256摘要值
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return SHA-256摘要
     * @since 3.0.8
     */
    public static byte[] sha256(String data, String charset) {
        return new Digester(DigestAlgorithm.SHA256, getBouncyCastleProvider()).digest(data, charset);
    }

    /**
     * 计算sha256摘要值，使用UTF-8编码
     *
     * @param data 被摘要数据
     * @return MD5摘要
     * @since 3.0.8
     */
    public static byte[] sha256(String data) {
        return sha256(data, Charsets.UTF_8_NAME);
    }

    /**
     * 计算SHA-256摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return SHA-256摘要的16进制表示
     * @since 3.0.8
     */
    public static String sha256Hex(String data, String charset) {
        return new Digester(DigestAlgorithm.SHA256, getBouncyCastleProvider()).digestHex(data, charset);
    }

    /**
     * 计算SHA-256摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return SHA-256摘要的16进制表示
     * @since 3.0.8
     */
    public static String sha256Hex(String data) {
        return sha256Hex(data, Charsets.UTF_8_NAME);
    }
    // ------------------------------------------------------------------------------------------- Hmac

    /**
     * 创建HMac对象，调用digest方法可获得hmac值
     *
     * @param algorithm {@link HmacAlgorithm}
     * @param key       密钥，如果为<code>null</code>生成随机密钥
     * @return {@link HMac}
     * @since 3.0.3
     */
    public static HMac hmac(HmacAlgorithm algorithm, byte[] key) {
        return new HMac(algorithm, key, getBouncyCastleProvider());
    }

    /**
     * 创建HMac对象，调用digest方法可获得hmac值
     *
     * @param algorithm {@link HmacAlgorithm}
     * @param key       密钥{@link SecretKey}，如果为<code>null</code>生成随机密钥
     * @return {@link HMac}
     * @since 3.0.3
     */
    public static HMac hmac(HmacAlgorithm algorithm, SecretKey key) {
        return new HMac(algorithm, key, getBouncyCastleProvider());
    }

    /**
     * 新建摘要器
     *
     * @param algorithm 签名算法
     * @return Digester
     */
    public static Digester digester(DigestAlgorithm algorithm) {
        return new Digester(algorithm, getBouncyCastleProvider());
    }

    /**
     * 新建摘要器
     *
     * @param algorithm 签名算法
     * @return Digester
     */
    public static Digester digester(String algorithm) {
        return new Digester(algorithm, getBouncyCastleProvider());
    }

    /**
     * 强制关闭Bouncy Castle库的使用，全局有效
     */
    public static void disableBouncyCastle() {
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
    }

    /**
     * 获取{@link org.bouncycastle.jce.provider.BouncyCastleProvider}
     *
     * @return {@link org.bouncycastle.jce.provider.BouncyCastleProvider}
     */
    private static Provider getBouncyCastleProvider() {
        return GlobalBouncyCastleProvider.INSTANCE.getProvider();
    }


}
