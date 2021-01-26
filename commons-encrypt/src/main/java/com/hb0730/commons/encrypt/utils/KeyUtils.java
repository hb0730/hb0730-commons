package com.hb0730.commons.encrypt.utils;

import com.hb0730.commons.encrypt.exceptions.EncryptException;
import com.hb0730.commons.encrypt.symmetry.SymmetricAlgorithm;
import com.hb0730.commons.lang.CharUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;

/**
 * 密钥工具类
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class KeyUtils {
    /**
     * 生成 {@link SecretKey}，仅用于对称加密和摘要算法密钥生成
     *
     * @param algorithm 算法，支持PBE算法
     * @param provider  {@link Provider}
     * @return {@link SecretKey}
     */
    public static SecretKey generateKey(String algorithm, Provider provider) {
        return generateKey(algorithm, -1, provider);
    }

    /**
     * 生成 {@link SecretKey}，仅用于对称加密和摘要算法密钥生成<br>
     * 当指定keySize&lt;0时，AES默认长度为128，其它算法不指定。
     *
     * @param algorithm 算法，支持PBE算法
     * @param keySize   密钥长度，&lt;0表示不设定密钥长度，即使用默认长度
     * @param provider  {@link Provider}
     * @return {@link SecretKey}
     * @since 3.1.2
     */
    public static SecretKey generateKey(String algorithm, int keySize, Provider provider) {
        return generateKey(algorithm, keySize, null, provider);
    }


    /**
     * 生成 {@link SecretKey}，仅用于对称加密和摘要算法密钥生成<br>
     * 当指定keySize&lt;0时，AES默认长度为128，其它算法不指定。
     *
     * @param algorithm 算法，支持PBE算法
     * @param keySize   密钥长度，&lt;0表示不设定密钥长度，即使用默认长度
     * @param random    随机数生成器，null表示默认
     * @param provider  {@link Provider}
     * @return {@link SecretKey}
     */
    public static SecretKey generateKey(String algorithm, int keySize, SecureRandom random, Provider provider) {
        algorithm = getMainAlgorithm(algorithm);

        final KeyGenerator keyGenerator = getKeyGenerator(algorithm, provider);
        if (keySize <= 0 && SymmetricAlgorithm.AES.getValue().equals(algorithm)) {
            // 对于AES的密钥，除非指定，否则强制使用128位
            keySize = 128;
        }

        if (keySize > 0) {
            if (null == random) {
                keyGenerator.init(keySize);
            } else {
                keyGenerator.init(keySize, random);
            }
        }
        return keyGenerator.generateKey();
    }

    /**
     * 获取{@link KeyGenerator}
     *
     * @param algorithm 加密算法
     * @param provider  {@link Provider}
     * @return {@link KeyGenerator}
     */
    private static KeyGenerator getKeyGenerator(String algorithm, Provider provider) {
        KeyGenerator generator;
        try {
            if (null == provider) {
                generator = KeyGenerator.getInstance(KeyUtils.getMainAlgorithm(algorithm));
            } else {
                generator = KeyGenerator.getInstance(KeyUtils.getMainAlgorithm(algorithm), provider);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException(e);
        }
        return generator;

    }

    /**
     * 获取主体算法名，例如RSA/ECB/PKCS1Padding的主体算法是RSA
     *
     * @param algorithm XXXwithXXX算法
     * @return 主体算法名
     */
    public static String getMainAlgorithm(String algorithm) {
        final int slashIndex = algorithm.indexOf(CharUtils.SLASH);
        if (slashIndex > 0) {
            return algorithm.substring(0, slashIndex);
        }
        return algorithm;
    }

}
