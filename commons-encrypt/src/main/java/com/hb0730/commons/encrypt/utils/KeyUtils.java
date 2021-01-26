package com.hb0730.commons.encrypt.utils;

import com.hb0730.commons.encrypt.exceptions.EncryptException;
import com.hb0730.commons.encrypt.symmetry.SymmetricAlgorithm;
import com.hb0730.commons.lang.CharUtils;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.nums.RandomUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * 密钥工具类
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class KeyUtils {
    private static final String PBE = "PBE";
    private static final String DES = "DES";
    private static final String DES_EDE = "DESede";

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
     * 生成 {@link SecretKey}，仅用于对称加密和摘要算法
     *
     * @param algorithm 算法
     * @param keySpec   {@link KeySpec}
     * @param provider  {@link Provider}
     * @return {@link SecretKey}
     */
    public static SecretKey generateKey(String algorithm, KeySpec keySpec, Provider provider) {
        SecretKeyFactory keyFactory = getSecretKeyFactory(algorithm, provider);
        try {
            return keyFactory.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new EncryptException(e);
        }
    }

    /**
     * 生成 {@link SecretKey}，仅用于对称加密和摘要算法密钥生成
     *
     * @param algorithm 算法
     * @param key       密钥，如果为{@code null} 自动生成随机密钥
     * @param provider  {@link Provider}
     * @return {@link SecretKey}
     */
    public static SecretKey generateKey(String algorithm, byte[] key, Provider provider) {
        if (StringUtils.isBlank(algorithm)) {
            throw new EncryptException("Algorithm is blank!");
        }
        SecretKey secretKey;
        if (algorithm.startsWith(PBE)) {
            secretKey = generatePBEKey(algorithm, (null == key) ? null : new String(key, StandardCharsets.UTF_8).toCharArray(), provider);
        } else if (algorithm.startsWith(DES)) {
            secretKey = generateDESKey(algorithm, key, provider);
        } else {
            // 其它算法密钥
            secretKey = (null == key) ? generateKey(algorithm, provider) : new SecretKeySpec(key, algorithm);
        }
        return secretKey;
    }

    /**
     * 生成 {@link SecretKey}
     *
     * @param algorithm DES算法，包括DES、DESede等
     * @param key       密钥
     * @param provider  {@link Provider}
     * @return {@link SecretKey}
     */
    public static SecretKey generateDESKey(String algorithm, byte[] key, Provider provider) {
        if (StringUtils.isBlank(algorithm) || !algorithm.startsWith(DES)) {
            throw new EncryptException("Algorithm [{}] is not a DES algorithm!");
        }
        SecretKey secretKey;
        if (null == key) {
            secretKey = generateKey(algorithm, provider);
        } else {
            KeySpec keySpec;
            try {
                if (algorithm.startsWith(DES_EDE)) {
                    // DESede兼容
                    keySpec = new DESedeKeySpec(key);
                } else {
                    keySpec = new DESKeySpec(key);
                }
            } catch (InvalidKeyException e) {
                throw new EncryptException(e);
            }
            secretKey = generateKey(algorithm, keySpec, provider);
        }
        return secretKey;
    }

    /**
     * 生成PBE {@link SecretKey}
     *
     * @param algorithm PBE算法，包括：PBEWithMD5AndDES、PBEWithSHA1AndDESede、PBEWithSHA1AndRC2_40等
     * @param key       密钥
     * @return {@link SecretKey}
     */
    public static SecretKey generatePBEKey(String algorithm, char[] key, Provider provider) {
        if (StringUtils.isBlank(algorithm) || !algorithm.startsWith(PBE)) {
            throw new EncryptException("Algorithm [{}] is not a PBE algorithm!");
        }
        if (null == key) {
            key = RandomUtils.randomString(32, true).toCharArray();
        }
        PBEKeySpec spec = new PBEKeySpec(key);
        return generateKey(algorithm, spec, provider);
    }

    /**
     * 获取{@link SecretKeyFactory}
     *
     * @param algorithm 对称加密算法
     * @param provider  {@link Provider}
     * @return {@link java.security.KeyFactory}
     * @since 4.5.2
     */
    public static SecretKeyFactory getSecretKeyFactory(String algorithm, Provider provider) {

        SecretKeyFactory keyFactory;
        try {
            keyFactory = (null == provider) //
                    ? SecretKeyFactory.getInstance(getMainAlgorithm(algorithm)) //
                    : SecretKeyFactory.getInstance(getMainAlgorithm(algorithm), provider);
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException(e);
        }
        return keyFactory;
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
