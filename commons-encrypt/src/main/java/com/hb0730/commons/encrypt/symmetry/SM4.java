package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.utils.KeyUtils;
import com.hb0730.commons.lang.collection.ArrayUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.Provider;

/**
 * SM4实现
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class SM4 extends SymmetricCrypto {
    /**
     * 构造，使用随机密钥
     */
    public SM4() {
        this((Provider) null);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param provider {@link Provider}
     */
    public SM4(Provider provider) {
        super(SymmetricAlgorithm.SM4.getValue(), provider);
    }

    /**
     * 构造
     *
     * @param key 密钥
     */
    public SM4(byte[] key) {
        this(key, null);
    }

    /**
     * 构造
     *
     * @param key      密钥
     * @param provider {@link Provider}
     */
    public SM4(byte[] key, Provider provider) {
        super(SymmetricAlgorithm.SM4.getValue(), key, provider);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     */
    public SM4(Mode mode, Padding padding) {
        this(mode, padding, (Provider) null);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param provider {@link Provider}
     */
    public SM4(Mode mode, Padding padding, Provider provider) {
        this(mode.name(), padding.name(), provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public SM4(Mode mode, Padding padding, byte[] key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param provider {@link Provider}
     */
    public SM4(Mode mode, Padding padding, byte[] key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     * @param iv      偏移向量，加盐
     */
    public SM4(Mode mode, Padding padding, byte[] key, byte[] iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param iv       偏移向量，加盐
     * @param provider {@link Provider}
     */
    public SM4(Mode mode, Padding padding, byte[] key, byte[] iv, Provider provider) {
        this(mode.name(), padding.name(), key, iv, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public SM4(Mode mode, Padding padding, SecretKey key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param provider {@link Provider}
     */
    public SM4(Mode mode, Padding padding, SecretKey key, Provider provider) {
        this(mode, padding, key, (IvParameterSpec) null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     * @param iv      偏移向量，加盐
     */
    public SM4(Mode mode, Padding padding, SecretKey key, byte[] iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param iv       偏移向量，加盐
     * @param provider {@link Provider}
     */
    public SM4(Mode mode, Padding padding, SecretKey key, byte[] iv, Provider provider) {
        this(mode, padding, key, ArrayUtils.isEmpty(iv) ? null : new IvParameterSpec(iv), provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     * @param iv      偏移向量，加盐
     */
    public SM4(Mode mode, Padding padding, SecretKey key, IvParameterSpec iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param iv       偏移向量，加盐
     * @param provider {@link Provider}
     */
    public SM4(Mode mode, Padding padding, SecretKey key, IvParameterSpec iv, Provider provider) {
        this(mode.name(), padding.name(), key, iv, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     */
    public SM4(String mode, String padding) {
        this(mode, padding, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param provider {@link Provider}
     */
    public SM4(String mode, String padding, Provider provider) {
        this(mode, padding, (byte[]) null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public SM4(String mode, String padding, byte[] key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param provider {@link Provider}
     */
    public SM4(String mode, String padding, byte[] key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     * @param iv      加盐
     */
    public SM4(String mode, String padding, byte[] key, byte[] iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param iv       加盐
     * @param provider {@link Provider}
     */
    public SM4(String mode, String padding, byte[] key, byte[] iv, Provider provider) {
        this(mode, padding,//
                KeyUtils.generateKey(SymmetricAlgorithm.SM4.getValue(), key, provider),//
                ArrayUtils.isEmpty(iv) ? null : new IvParameterSpec(iv), provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public SM4(String mode, String padding, SecretKey key) {
        this(mode, padding, key, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param provider {@link Provider}
     */
    public SM4(String mode, String padding, SecretKey key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     * @param iv      加盐
     */
    public SM4(String mode, String padding, SecretKey key, IvParameterSpec iv) {
        this(mode, padding, key, iv, null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param iv       加盐
     * @param provider {@link Provider}
     */
    public SM4(String mode, String padding, SecretKey key, IvParameterSpec iv, Provider provider) {
        super(String.format("%s/%s/%s", SymmetricAlgorithm.SM4.getValue(), mode, padding), key, iv, provider);
    }
}
