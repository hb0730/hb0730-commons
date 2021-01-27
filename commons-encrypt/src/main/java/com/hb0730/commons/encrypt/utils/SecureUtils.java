package com.hb0730.commons.encrypt.utils;

import com.hb0730.commons.encrypt.exceptions.EncryptException;
import com.hb0730.commons.lang.RegexUtils;
import com.hb0730.commons.lang.codec.Base64Utils;
import com.hb0730.commons.lang.codec.HexUtils;

import javax.crypto.Cipher;
import java.security.Provider;

/**
 * 安全相关工具类<br>
 * 加密分为三种：<br>
 * 1、对称加密（symmetric），例如：AES、DES等<br>
 * 2、非对称加密（asymmetric），例如：RSA、DSA等<br>
 * 3、摘要加密（digest），例如：MD5、SHA-1、SHA-256、HMAC等<br>
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class SecureUtils {
    /**
     * 创建{@link Cipher}
     *
     * @param algorithm 算法
     * @param provider  {@link Provider}
     * @return {@link Cipher}
     */
    public static Cipher createCipher(String algorithm, Provider provider) {
        try {
            return (null == provider) ? Cipher.getInstance(algorithm) : Cipher.getInstance(algorithm, provider);
        } catch (Exception e) {
            throw new EncryptException(e);
        }
    }

    /**
     * 解码字符串密钥，可支持的编码如下：
     *
     * <pre>
     * 1. Hex（16进制）编码
     * 1. Base64编码
     * </pre>
     *
     * @param key 被解码的密钥字符串
     * @return 密钥
     * @since 4.3.3
     */
    public static byte[] decode(String key) {
        return RegexUtils.isHex(key) ? HexUtils.decodeHex(key) : Base64Utils.decodeFromString(key);
    }

}
