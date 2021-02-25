package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.constant.Algorithm;
import com.hb0730.commons.encrypt.constant.Mode;
import com.hb0730.commons.encrypt.constant.Padding;
import com.hb0730.commons.encrypt.exceptions.EncryptException;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.codec.Base64Utils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * DES工具类
 * 美国数据加密标准(Data Encryption Standard)，密钥长64，块长64，常用的场景是银行业
 * 优点：高安全性，除了用穷举搜索法对DES算法进行攻击外，还没有发现更有效的办法
 * 缺点：分组短、密钥短、密码生命周期短、运算速度较慢
 *
 * @author bing_huang
 * @see DES
 * @see Symmetrys#des()
 * @since 1.0.2
 * @deprecated 2.1.0
 */
public class DESUtils {

    static {
        //导入Provider，BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * DES加密（最常用方式之一，使用DES/ECB/PKCS5Padding方式，无偏移量）
     *
     * @param data 密文（Base64编码）
     * @param key  密钥（Base64编码），长度必须是8位
     * @return 密文（Base64编码）
     */
    public static String encrypt(String data, String key) {
        return encrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * DES加密（最常用方式之一，使用DES/ECB/PKCS5Padding方式，无偏移量）
     *
     * @param data 密文
     * @param key  密钥，长度必须是8位
     * @return 密文
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        return encrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * DES加密（最常用方式之一，使用DES/CBC/PKCS7Padding方式）
     *
     * @param data 密文（Base64编码）
     * @param key  密钥（Base64编码），长度必须是8位
     * @param iv   偏移量，长度必须是8位
     * @return 密文
     */
    public static String encrypt(String data, String key, String iv) {
        return encrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * DES加密（最常用方式之一，使用DES/CBC/PKCS7Padding方式）
     *
     * @param data 密文
     * @param key  密钥，长度必须是8位
     * @param iv   偏移量，长度必须是8位
     * @return 密文
     */
    public static byte[] encrypt(byte[] data, byte[] key, String iv) {
        return encrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * DES加密（不带偏移量）
     *
     * @param data    密文（Base64编码）
     * @param key     密钥（Base64编码），长度必须是8位
     * @param mode    密码块工作模式
     * @param padding 填充方式
     * @return 密文(Base64编码)
     */
    public static String encrypt(String data, String key, Mode mode, Padding padding) {
        return encrypt(data, key, null, mode, padding);
    }

    /**
     * DES加密（不带偏移量）
     *
     * @param data    密文
     * @param key     密钥，长度必须是8位
     * @param mode    密码块工作模式
     * @param padding 填充方式
     * @return 密文（Base64编码）
     */
    public static byte[] encrypt(byte[] data, byte[] key, Mode mode, Padding padding) {
        return encrypt(data, key, null, mode, padding);
    }

    /**
     * DES加密
     *
     * @param data    明文（Base64编码）
     * @param key     密钥（Base64编码），长度必须是8位
     * @param iv      偏移量，长度必须是8位
     * @param mode    密码块工作模式
     * @param padding 填充方式
     * @return 密文（Base64编码）
     */
    public static String encrypt(String data, String key, String iv, Mode mode, Padding padding) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        byte[] encrypt = encrypt(data.getBytes(), key.getBytes(), iv, mode, padding);
        return Base64Utils.encodeToString(encrypt);
    }

    /**
     * DES加密
     *
     * @param data    明文
     * @param key     密钥，长度必须是8位
     * @param iv      偏移量，长度必须是8位
     * @param mode    密码块工作模式
     * @param padding 填充方式
     * @return 密文
     */
    public static byte[] encrypt(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        check(data, key, iv, mode, padding);
        try {
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            String algorithm = Algorithm.getAlgorithm(Algorithm.DES, mode, padding);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(algorithm);
            // 初始化
            if (StringUtils.isNotBlank(iv)) {
                AlgorithmParameters parameters = AlgorithmParameters.getInstance(Algorithm.DES.getAlgorithm());
                parameters.init(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, parameters);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            }
            //加密
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new EncryptException("DES encrypt error", e);
        }
    }

    /**
     * DES解密（最常用方式之一，使用DES/ECB/PKCS5Padding方式，无偏移量）
     *
     * @param data 密文（Base64编码）
     * @param key  密钥（Base64编码），长度必须是8位
     * @return 明文
     */
    public static String decrypt(String data, String key) {
        return decrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * DES解密（最常用方式之一，使用DES/ECB/PKCS5Padding方式，无偏移量）
     *
     * @param data 密文
     * @param key  密钥，长度必须是8位
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        return decrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * DES解密（最常用方式之一，使用DES/CBC/PKCS7Padding方式）
     *
     * @param data 密文（Base64编码）
     * @param key  密钥（Base64编码），长度必须是8位
     * @param iv   偏移量，长度必须是8位
     * @return 明文
     */
    public static String decrypt(String data, String key, String iv) {
        return decrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * DES解密（最常用方式之一，使用DES/CBC/PKCS7Padding方式）
     *
     * @param data 密文
     * @param key  密钥，长度必须是8位
     * @param iv   偏移量，长度必须是8位
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, String iv) {
        return decrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * DES解密（不带偏移量）
     *
     * @param data    密文（Base64编码）
     * @param key     密钥（Base64编码），长度必须是8位
     * @param mode    密码块工作模式
     * @param padding 填充方式
     * @return 明文
     */
    public static String decrypt(String data, String key, Mode mode, Padding padding) {
        return decrypt(data, key, null, mode, padding);
    }

    /**
     * DES解密（不带偏移量）
     *
     * @param data    密文
     * @param key     密钥，长度必须是8位
     * @param mode    密码块工作模式
     * @param padding 填充方式
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, Mode mode, Padding padding) {
        return decrypt(data, key, null, mode, padding);
    }

    /**
     * DES解密
     *
     * @param data    密文（Base64编码）
     * @param key     密钥（Base64编码），长度必须是8位
     * @param iv      偏移量，长度必须是8位
     * @param mode    密码块工作模式
     * @param padding 填充方式
     * @return 明文
     */
    public static String decrypt(String data, String key, String iv, Mode mode, Padding padding) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        byte[] decrypt = decrypt(Base64Utils.decodeFromString(data), key.getBytes(), iv, mode, padding);
        return new String(decrypt);
    }

    /**
     * DES解密
     *
     * @param data    密文
     * @param key     密钥，长度必须是8位
     * @param iv      偏移量，长度必须是8位
     * @param mode    密码块工作模式
     * @param padding 填充方式
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        check(data, key, iv, mode, padding);
        try {
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            String algorithm = Algorithm.getAlgorithm(Algorithm.DES, mode, padding);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(algorithm);
            // 初始化
            if (StringUtils.isNotBlank(iv)) {
                AlgorithmParameters parameters = AlgorithmParameters.getInstance(Algorithm.DES.getAlgorithm());
                parameters.init(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameters);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            }
            //解密
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new EncryptException("DES decrypt error", e);
        }
    }

    /**
     * 生成DES的Key（64位）
     *
     * @return 密钥
     */
    public static byte[] generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(Algorithm.DES.getAlgorithm());
            keyGenerator.init(64);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException("DES key generate error", e);
        }
    }

    private static SecretKeySpec getSecretKeySpec(byte[] key) {
        return new SecretKeySpec(key, Algorithm.DES.getAlgorithm());
    }

    private static void check(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        checkKey(key);
        checkModeAndPadding(data, mode, padding);
        if (StringUtils.isNotBlank(iv)) {
            checkIv(iv);
            if (mode == Mode.ECB) {
                throw new EncryptException("DES ECB mode does not use an IV");
            }
        }
    }

    /**
     * 校验DES密码块工作模式和填充模式
     *
     * @param data    文明
     * @param mode    工作模式{@link Mode}
     * @param padding 填充模式{@link Padding}
     */
    private static void checkModeAndPadding(byte[] data, Mode mode, Padding padding) {
        if (mode == Mode.NONE) {
            throw new EncryptException("invalid DES mode");
        }
        if (padding == Padding.SSL3Padding || padding == Padding.PKCS1Padding) {
            throw new EncryptException("invalid DES padding");
        }
        boolean is8NotSupport = padding == Padding.NoPadding && (mode == Mode.ECB || mode == Mode.CBC) && data.length % 8 != 0;
        if (is8NotSupport) {
            throw new EncryptException("data length must be multiple of 8 bytes on ECB/NoPadding or CBC/NoPadding mode");
        }
    }

    /**
     * 校验DES密钥，长度必须是8位
     *
     * @param key 密钥
     */
    private static void checkKey(byte[] key) {
        if (key == null) {
            throw new EncryptException("DES key cannot be null");
        }
        if (key.length != 8) {
            throw new EncryptException("DES key not 8 bytes long");
        }
    }

    /**
     * 校验DES偏移量，长度必须是8位
     *
     * @param iv 偏移量
     */
    private static void checkIv(String iv) {
        if (iv.length() != 8) {
            throw new EncryptException("DES iv not 8 bytes long");
        }
    }
}
