package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.utils.KeyUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.Provider;

/**
 * DES加密算法实现<br>
 * DES全称为Data Encryption Standard，即数据加密标准，是一种使用密钥加密的块算法<br>
 * Java中默认实现为：DES/CBC/PKCS5Padding
 *
 * @author bing_huang
 */
public class DES extends SymmetricCrypto {
    /**
     * 构造，默认DES/CBC/PKCS5Padding，使用随机密钥
     */
    public DES() {
        this((Provider) null);
    }

    /**
     * 构造，默认DES/CBC/PKCS5Padding，使用随机密钥
     *
     * @param provider {@link Provider}
     */
    public DES(Provider provider) {
        super(SymmetricAlgorithm.DES, provider);
    }

    /**
     * 构造，使用默认的DES/CBC/PKCS5Padding
     *
     * @param key 密钥
     */
    public DES(byte[] key) {
        this(key, null);
    }

    /**
     * 构造，使用默认的DES/CBC/PKCS5Padding
     *
     * @param key      密钥
     * @param provider {@link Provider}
     */
    public DES(byte[] key, Provider provider) {
        super(SymmetricAlgorithm.DES, key, provider);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     */
    public DES(Mode mode, Padding padding) {
        this(mode, padding, (Provider) null);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param provider {@link Provider}
     */
    public DES(Mode mode, Padding padding, Provider provider) {
        this(mode.name(), padding.name(), provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，长度：8的倍数
     */
    public DES(Mode mode, Padding padding, byte[] key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，长度：8的倍数
     * @param provider {@link Provider}
     */
    public DES(Mode mode, Padding padding, byte[] key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，长度：8的倍数
     * @param iv      偏移向量，加盐
     */
    public DES(Mode mode, Padding padding, byte[] key, byte[] iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，长度：8的倍数
     * @param iv       偏移向量，加盐
     * @param provider {@link Provider}
     */
    public DES(Mode mode, Padding padding, byte[] key, byte[] iv, Provider provider) {
        this(mode.name(), padding.name(), key, iv, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，长度：8的倍数
     */
    public DES(Mode mode, Padding padding, SecretKey key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，长度：8的倍数
     * @param provider {@link Provider}
     */
    public DES(Mode mode, Padding padding, SecretKey key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，长度：8的倍数
     * @param iv      偏移向量，加盐
     */
    public DES(Mode mode, Padding padding, SecretKey key, IvParameterSpec iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，长度：8的倍数
     * @param iv       偏移向量，加盐
     * @param provider {@link Provider}
     */
    public DES(Mode mode, Padding padding, SecretKey key, IvParameterSpec iv, Provider provider) {
        this(mode.name(), padding.name(), key, iv, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     */
    public DES(String mode, String padding) {
        this(mode, padding, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param provider {@link Provider}
     */
    public DES(String mode, String padding, Provider provider) {
        this(mode, padding, (byte[]) null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，长度：8的倍数
     */
    public DES(String mode, String padding, byte[] key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param key      密钥，长度：8的倍数
     * @param provider {@link Provider}
     */
    public DES(String mode, String padding, byte[] key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，长度：8的倍数
     * @param iv      加盐
     */
    public DES(String mode, String padding, byte[] key, byte[] iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param key      密钥，长度：8的倍数
     * @param iv       加盐
     * @param provider {@link Provider}
     */
    public DES(String mode, String padding, byte[] key, byte[] iv, Provider provider) {
        this(mode,
                padding,
                KeyUtils.generateKey("DES", key, provider),
                null == iv ? null : new IvParameterSpec(iv), provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，长度：8的倍数
     */
    public DES(String mode, String padding, SecretKey key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param key      密钥，长度：8的倍数
     * @param provider {@link Provider}
     */
    public DES(String mode, String padding, SecretKey key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode      模式
     * @param padding   补码方式
     * @param secretKey 密钥，长度：8的倍数
     * @param iv        加盐
     */
    public DES(String mode, String padding, SecretKey secretKey, IvParameterSpec iv) {
        this(mode, padding, secretKey, iv, null);
    }

    /**
     * 构造
     *
     * @param mode      模式
     * @param padding   补码方式
     * @param secretKey 密钥，长度：8的倍数
     * @param provider  {@link Provider}
     * @param iv        加盐
     */
    public DES(String mode, String padding, SecretKey secretKey, IvParameterSpec iv, Provider provider) {
        super(String.format("DES/%s/%s", mode, padding), secretKey, iv, provider);
    }
}
