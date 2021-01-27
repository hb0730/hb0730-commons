package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.utils.KeyUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.Provider;

/**
 * DESede是由DES对称加密算法改进后的一种对称加密算法，又名3DES、TripleDES。<br>
 * 使用 168 位的密钥对资料进行三次加密的一种机制；它通常（但非始终）提供极其强大的安全性。<br>
 * 如果三个 56 位的子元素都相同，则三重 DES 向后兼容 DES。<br>
 * Java中默认实现为：DESede/ECB/PKCS5Padding
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class DESede extends SymmetricCrypto {
    /**
     * 构造，默认DESede/ECB/PKCS5Padding，使用随机密钥
     */
    public DESede() {
        this((Provider) null);
    }

    /**
     * 构造，默认DESede/ECB/PKCS5Padding，使用随机密钥
     *
     * @param provider {@link Provider}
     */
    public DESede(Provider provider) {
        super(SymmetricAlgorithm.DESede, provider);
    }

    /**
     * 构造，使用默认的DESede/ECB/PKCS5Padding
     *
     * @param key 密钥
     */
    public DESede(byte[] key) {
        this(key, null);
    }

    /**
     * 构造，使用默认的DESede/ECB/PKCS5Padding
     *
     * @param key      密钥
     * @param provider {@link Provider}
     */
    public DESede(byte[] key, Provider provider) {
        super(SymmetricAlgorithm.DESede, key, provider);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     */
    public DESede(Mode mode, Padding padding) {
        this(mode, padding, (Provider) null);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param provider {@link Provider}
     */
    public DESede(Mode mode, Padding padding, Provider provider) {
        this(mode.name(), padding.name(), provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，长度24位
     */
    public DESede(Mode mode, Padding padding, byte[] key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，长度24位
     * @param provider {@link Provider}
     */
    public DESede(Mode mode, Padding padding, byte[] key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，长度24位
     * @param iv      偏移向量，加盐
     */
    public DESede(Mode mode, Padding padding, byte[] key, byte[] iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，长度24位
     * @param iv       偏移向量，加盐
     * @param provider {@link Provider}
     */
    public DESede(Mode mode, Padding padding, byte[] key, byte[] iv, Provider provider) {
        this(mode.name(), padding.name(), key, iv, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，长度24位
     */
    public DESede(Mode mode, Padding padding, SecretKey key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，长度24位
     * @param provider {@link Provider}
     */
    public DESede(Mode mode, Padding padding, SecretKey key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，长度24位
     * @param iv      偏移向量，加盐
     */
    public DESede(Mode mode, Padding padding, SecretKey key, IvParameterSpec iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，长度24位
     * @param iv       偏移向量，加盐
     * @param provider {@link Provider}
     */
    public DESede(Mode mode, Padding padding, SecretKey key, IvParameterSpec iv, Provider provider) {
        this(mode.name(), padding.name(), key, iv, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     */
    public DESede(String mode, String padding) {
        this(mode, padding, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param provider {@link Provider}
     */
    public DESede(String mode, String padding, Provider provider) {
        this(mode, padding, (byte[]) null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，长度24位
     */
    public DESede(String mode, String padding, byte[] key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param key      密钥，长度24位
     * @param provider {@link Provider}
     */
    public DESede(String mode, String padding, byte[] key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，长度24位
     * @param iv      加盐
     */
    public DESede(String mode, String padding, byte[] key, byte[] iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param key      密钥，长度24位
     * @param iv       加盐
     * @param provider {@link Provider}
     */
    public DESede(String mode, String padding, byte[] key, byte[] iv, Provider provider) {
        this(mode,
                padding,
                KeyUtils.generateKey(SymmetricAlgorithm.DESede.getValue(), key, provider),
                null == iv ? null : new IvParameterSpec(iv), provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥
     */
    public DESede(String mode, String padding, SecretKey key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param key      密钥
     * @param provider {@link Provider}
     */
    public DESede(String mode, String padding, SecretKey key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode      模式
     * @param padding   补码方式
     * @param secretKey 密钥
     * @param iv        加盐
     */
    public DESede(String mode, String padding, SecretKey secretKey, IvParameterSpec iv) {
        this(mode, padding, secretKey, iv, null);
    }

    /**
     * 构造
     *
     * @param mode      模式
     * @param padding   补码方式
     * @param secretKey 密钥
     * @param iv        加盐
     * @param provider  {@link Provider}
     */
    public DESede(String mode, String padding, SecretKey secretKey, IvParameterSpec iv, Provider provider) {
        super(String.format("%s/%s/%s", SymmetricAlgorithm.DESede.getValue(), mode, padding), secretKey, iv, provider);
    }
}
