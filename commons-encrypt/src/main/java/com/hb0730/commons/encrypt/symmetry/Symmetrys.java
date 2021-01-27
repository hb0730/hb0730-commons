package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.constant.GlobalBouncyCastleProvider;

import java.security.Provider;

/**
 * 对称加密集合
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class Symmetrys {
    /**
     * AES加密，生成随机KEY。注意解密时必须使用相同 {@link AES}对象或者使用相同KEY<br>
     * 例：
     *
     * <pre>
     * AES加密：aes().encrypt(data)
     * AES解密：aes().decrypt(data)
     * </pre>
     *
     * @return {@link AES}
     */
    public static AES aes() {
        return new AES(getBouncyCastleProvider());
    }

    /**
     * AES加密<br>
     * 例：
     *
     * <pre>
     * AES加密：aes(key).encrypt(data)
     * AES解密：aes(key).decrypt(data)
     * </pre>
     *
     * @param key 密钥
     * @return {@link SymmetricCrypto}
     */
    public static AES aes(byte[] key) {
        return new AES(key, getBouncyCastleProvider());
    }

    /**
     * DES加密，生成随机KEY。注意解密时必须使用相同 {@link DES}对象或者使用相同KEY<br>
     * 例：
     *
     * <pre>
     * DES加密：des().encrypt(data)
     * DES解密：des().decrypt(data)
     * </pre>
     *
     * @return {@link DES}
     */
    public static DES des() {
        return new DES(getBouncyCastleProvider());
    }

    /**
     * DES加密<br>
     * 例：
     *
     * <pre>
     * DES加密：des(key).encrypt(data)
     * DES解密：des(key).decrypt(data)
     * </pre>
     *
     * @param key 密钥
     * @return {@link DES}
     */
    public static DES des(byte[] key) {
        return new DES(key, getBouncyCastleProvider());
    }

    /**
     * DESede加密（又名3DES、TripleDES），生成随机KEY。注意解密时必须使用相同 {@link DESede}对象或者使用相同KEY<br>
     * Java中默认实现为：DESede/ECB/PKCS5Padding<br>
     * 例：
     *
     * <pre>
     * DESede加密：desede().encrypt(data)
     * DESede解密：desede().decrypt(data)
     * </pre>
     *
     * @return {@link DESede}
     */
    public static DESede desede() {
        return new DESede(getBouncyCastleProvider());
    }

    /**
     * DESede加密（又名3DES、TripleDES）<br>
     * Java中默认实现为：DESede/ECB/PKCS5Padding<br>
     * 例：
     *
     * <pre>
     * DESede加密：desede(key).encrypt(data)
     * DESede解密：desede(key).decrypt(data)
     * </pre>
     *
     * @param key 密钥
     * @return {@link DESede}
     */
    public static DESede desede(byte[] key) {
        return new DESede(key, getBouncyCastleProvider());
    }

    /**
     * SM4加密
     * <pre>
     * SM4加密：desede().encrypt(data)
     * SM4解密：desede().decrypt(data)
     * </pre>
     *
     * @return {@link SM4}
     */
    public static SM4 sm4() {
        return new SM4(getBouncyCastleProvider());
    }

    /**
     * SM4加密
     *
     * @param key 密钥
     * @return {@link SM4}
     */
    public static SM4 sm4(byte[] key) {
        return new SM4(key, getBouncyCastleProvider());
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
