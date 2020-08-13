package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.constant.Algorithm;
import com.hb0730.commons.encrypt.constant.Mode;
import com.hb0730.commons.encrypt.constant.Padding;
import com.hb0730.commons.encrypt.exceptions.EncryptException;
import com.hb0730.commons.lang.StringUtils;
import org.apache.commons.codec.binary.Base64;
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
 * 三重DES工具类
 * DESede：密钥长 112/168，块长64，速度很慢，中等安全, 适合加密较小的数据
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class DESedeUtils {
    static {
        //导入Provider，BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * DESede加密（最常用方式之一，使用DESede/ECB/PKCS5Padding方式，无偏移量）
     *
     * @param data 密文（Base64编码）
     * @param key  密钥（Base64编码），长度必须是24位
     */
    public static String encrypt(String data, String key) {
        return encrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * DESede加密（最常用方式之一，使用DESede/ECB/PKCS5Padding方式，无偏移量）
     *
     * @param data 密文
     * @param key  密钥，长度必须是24位
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        return encrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * DESede加密（最常用方式之一，使用DESede/CBC/PKCS7Padding方式）
     *
     * @param data 密文（Base64编码）
     * @param key  密钥（Base64编码），长度必须是24位
     * @param iv   偏移量，长度必须是8位
     */
    public static String encrypt(String data, String key, String iv) {
        return encrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * DESede加密（最常用方式之一，使用DESede/CBC/PKCS7Padding方式）
     *
     * @param data 密文
     * @param key  密钥，长度必须是24位
     * @param iv   偏移量，长度必须是8位
     */
    public static byte[] encrypt(byte[] data, byte[] key, String iv) {
        return encrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * DESede加密（不带偏移量）
     *
     * @param data    密文（Base64编码）
     * @param key     密钥（Base64编码），长度必须是24位
     * @param mode    工作模式{@link Mode}
     * @param padding 填充模式{@link Padding}
     */
    public static String encrypt(String data, String key, Mode mode, Padding padding) {
        return encrypt(data, key, null, mode, padding);
    }

    /**
     * DESede加密（不带偏移量）
     *
     * @param data    密文
     * @param key     密钥，长度必须是24位
     * @param mode    工作模式{@link Mode}
     * @param padding 填充模式{@link Padding}
     * @return 密文（Base64编码）
     */
    public static byte[] encrypt(byte[] data, byte[] key, Mode mode, Padding padding) {
        return encrypt(data, key, null, mode, padding);
    }

    /**
     * DESede加密
     *
     * @param data    明文（Base64编码）
     * @param key     密钥（Base64编码），长度必须是24位
     * @param iv      偏移量，长度必须是8位
     * @param mode    工作模式{@link Mode}
     * @param padding 填充模式{@link Padding}
     * @return 密文（Base64编码）
     */
    public static String encrypt(String data, String key, String iv, Mode mode, Padding padding) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        byte[] encrypt = encrypt(data.getBytes(), key.getBytes(), iv, mode, padding);
        return Base64.encodeBase64String(encrypt);
    }

    /**
     * DESede加密
     *
     * @param data    明文
     * @param key     密钥，长度必须是24位
     * @param iv      偏移量，长度必须是8位
     * @param mode    工作模式{@link Mode}
     * @param padding 填充模式{@link Padding}
     * @return 密文
     */
    public static byte[] encrypt(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        check(data, key, iv, mode, padding);
        try {
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            String algorithm = Algorithm.getAlgorithm(Algorithm.DE_SEDE, mode, padding);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(algorithm);
            // 初始化
            if (StringUtils.isNotBlank(iv)) {
                AlgorithmParameters parameters = AlgorithmParameters.getInstance(Algorithm.DE_SEDE.getAlgorithm());
                parameters.init(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, parameters);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            }
            //加密
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new EncryptException("DESede encrypt error", e);
        }
    }

    /**
     * DESede解密（最常用方式之一，使用DESede/ECB/PKCS5Padding方式，无偏移量）
     *
     * @param data 密文（Base64编码）
     * @param key  密钥（Base64编码），长度必须是24位
     * @return 明文
     */
    public static String decrypt(String data, String key) {
        return decrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * DESede解密（最常用方式之一，使用DESede/ECB/PKCS5Padding方式，无偏移量）
     *
     * @param data 密文
     * @param key  密钥，长度必须是24位
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        return decrypt(data, key, null, Mode.ECB, Padding.PKCS5Padding);
    }

    /**
     * DESede解密（最常用方式之一，使用DESede/CBC/PKCS7Padding方式）
     *
     * @param data 密文（Base64编码）
     * @param key  密钥（Base64编码），长度必须是24位
     * @param iv   偏移量，长度必须是8位
     * @return 明文
     */
    public static String decrypt(String data, String key, String iv) {
        return decrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * DESede解密（最常用方式之一，使用DESede/CBC/PKCS7Padding方式）
     *
     * @param data 密文
     * @param key  密钥，长度必须是24位
     * @param iv   偏移量，长度必须是8位
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, String iv) {
        return decrypt(data, key, iv, Mode.CBC, Padding.PKCS7Padding);
    }

    /**
     * DESede解密（不带偏移量）
     *
     * @param data    密文（Base64编码）
     * @param key     密钥（Base64编码），长度必须是24位
     * @param mode    工作模式{@link Mode}
     * @param padding 填充模式{@link Padding}
     * @return 明文
     */
    public static String decrypt(String data, String key, Mode mode, Padding padding) {
        return decrypt(data, key, null, mode, padding);
    }

    /**
     * DESede解密（不带偏移量）
     *
     * @param data    密文
     * @param key     密钥，长度必须是24位
     * @param mode    工作模式{@link Mode}
     * @param padding 填充模式{@link Padding}
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, Mode mode, Padding padding) {
        return decrypt(data, key, null, mode, padding);
    }

    /**
     * DESede解密
     *
     * @param data    密文（Base64编码）
     * @param key     密钥（Base64编码），长度必须是24位
     * @param iv      偏移量，长度必须是8位
     * @param mode    密码块工作模式
     * @param padding 填充方式
     * @return 明文
     */
    public static String decrypt(String data, String key, String iv, Mode mode, Padding padding) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        byte[] decrypt = decrypt(Base64.decodeBase64(data), key.getBytes(), iv, mode, padding);
        return new String(decrypt);
    }

    /**
     * DESede解密
     *
     * @param data    密文
     * @param key     密钥，长度必须是24位
     * @param iv      偏移量，长度必须是8位
     * @param mode    密码块工作模式
     * @param padding 填充方式
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        check(data, key, iv, mode, padding);
        try {
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            String algorithm = Algorithm.getAlgorithm(Algorithm.DE_SEDE, mode, padding);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(algorithm);
            // 初始化
            if (StringUtils.isNotBlank(iv)) {
                AlgorithmParameters parameters = AlgorithmParameters.getInstance(Algorithm.DE_SEDE.getAlgorithm());
                parameters.init(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameters);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            }
            //解密
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new EncryptException("DESede decrypt error", e);
        }
    }

    /**
     * 生成DESede的Key（112位）
     *
     * @return 密钥
     */
    public static byte[] generateKey() {
        return generateKey(112);
    }

    /**
     * 生成DESede的Key，长度可以选112、168，生成的密钥长度为24位
     *
     * @param length key的长度
     * @return 密钥
     */
    public static byte[] generateKey(int length) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(Algorithm.DE_SEDE.getAlgorithm());
            keyGenerator.init(length);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException("DESede key generate error", e);
        }
    }

    private static SecretKeySpec getSecretKeySpec(byte[] key) {
        return new SecretKeySpec(key, Algorithm.DE_SEDE.getAlgorithm());
    }

    private static void check(byte[] data, byte[] key, String iv, Mode mode, Padding padding) {
        checkKey(key);
        checkModeAndPadding(data, mode, padding);
        if (StringUtils.isNotBlank(iv)) {
            checkIv(iv);
            if (mode == Mode.ECB) {
                throw new EncryptException("DESede ECB mode does not use an IV");
            }
        }
    }

    /**
     * 校验DESede密码块工作模式和填充模式
     */
    private static void checkModeAndPadding(byte[] data, Mode mode, Padding padding) {
        if (mode == Mode.NONE) {
            throw new EncryptException("invalid DESede mode");
        }
        if (padding == Padding.SSL3Padding || padding == Padding.PKCS1Padding) {
            throw new EncryptException("invalid DESede padding");
        }
        boolean is8NotSupport = padding == Padding.NoPadding && (mode == Mode.ECB || mode == Mode.CBC) && data.length % 8 != 0;
        if (is8NotSupport) {
            throw new EncryptException("data length must be multiple of 8 bytes on ECB/NoPadding or CBC/NoPadding mode");
        }
    }

    /**
     * 校验DESede密钥，长度必须是24位
     */
    private static void checkKey(byte[] key) {
        if (key == null) {
            throw new EncryptException("DESede key cannot be null");
        }
        if (key.length != 16 && key.length != 24) {
            throw new EncryptException("DESede key not 16/24 bytes long");
        }
    }

    /**
     * 校验DESede偏移量，长度必须是8位
     */
    private static void checkIv(String iv) {
        if (iv.length() != 8) {
            throw new EncryptException("DESede iv not 8 bytes long");
        }
    }
}
