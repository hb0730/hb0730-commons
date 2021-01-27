package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.utils.KeyUtils;
import com.hb0730.commons.lang.collection.ArrayUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.Provider;

/**
 * AES加密算法实现<br>
 * 高级加密标准（英语：Advanced Encryption Standard，缩写：AES），在密码学中又称Rijndael加密法<br>
 * 对于Java中AES的默认模式是：AES/ECB/PKCS5Padding，如果使用CryptoJS，请调整为：padding: CryptoJS.pad.Pkcs7
 *
 * <p>
 * 相关概念说明：
 * <pre>
 * mode:    加密算法模式，是用来描述加密算法（此处特指分组密码，不包括流密码，）在加密时对明文分组的模式，它代表了不同的分组方式
 * padding: 补码方式是在分组密码中，当明文长度不是分组长度的整数倍时，需要在最后一个分组中填充一些数据使其凑满一个分组的长度。
 * iv:      在对明文分组加密时，会将明文分组与前一个密文分组进行XOR运算（即异或运算），但是加密第一个明文分组时不存在“前一个密文分组”，
 *          因此需要事先准备一个与分组长度相等的比特序列来代替，这个比特序列就是偏移量。
 * </pre>
 * <p>
 * 相关概念见：https://blog.csdn.net/OrangeJack/article/details/82913804
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class AES extends SymmetricCrypto {

    /**
     * 构造，默认AES/ECB/PKCS5Padding，使用随机密钥
     */
    public AES() {
        this((Provider) null);
    }

    /**
     * 构造，默认AES/ECB/PKCS5Padding，使用随机密钥
     *
     * @param provider {@link Provider}
     */
    public AES(Provider provider) {
        super(SymmetricAlgorithm.AES, provider);
    }

    /**
     * 构造，使用默认的AES/ECB/PKCS5Padding
     *
     * @param key 密钥
     */
    public AES(byte[] key) {
        this(key, null);
    }

    /**
     * 构造，使用默认的AES/ECB/PKCS5Padding
     *
     * @param key      密钥
     * @param provider {@link Provider}
     */
    public AES(byte[] key, Provider provider) {
        super(SymmetricAlgorithm.AES, key, provider);
    }

    /**
     * 构造，使用默认的AES/ECB/PKCS5Padding
     *
     * @param key 密钥
     */
    public AES(SecretKey key) {
        this(key, null);
    }

    /**
     * 构造，使用默认的AES/ECB/PKCS5Padding
     *
     * @param key      密钥
     * @param provider {@link Provider}
     */
    public AES(SecretKey key, Provider provider) {
        super(SymmetricAlgorithm.AES, key, provider);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     */
    public AES(Mode mode, Padding padding) {
        this(mode.name(), padding.name(), (Provider) null);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param provider {@link Provider}
     */
    public AES(Mode mode, Padding padding, Provider provider) {
        this(mode.name(), padding.name(), provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public AES(Mode mode, Padding padding, byte[] key) {
        this(mode, padding, key, null, null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param provider {@link Provider}
     */
    public AES(Mode mode, Padding padding, byte[] key, Provider provider) {
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
    public AES(Mode mode, Padding padding, byte[] key, byte[] iv) {
        this(mode.name(), padding.name(), key, iv, null);
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
    public AES(Mode mode, Padding padding, byte[] key, byte[] iv, Provider provider) {
        this(mode.name(), padding.name(), key, iv, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link Mode}
     * @param padding {@link Padding}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public AES(Mode mode, Padding padding, SecretKey key) {
        this(mode, padding, key, (IvParameterSpec) null, null);
    }

    /**
     * 构造
     *
     * @param mode     模式{@link Mode}
     * @param padding  {@link Padding}补码方式
     * @param key      密钥，支持三种密钥长度：128、192、256位
     * @param provider {@link Provider}
     */
    public AES(Mode mode, Padding padding, SecretKey key, Provider provider) {
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
    public AES(Mode mode, Padding padding, SecretKey key, byte[] iv) {
        this(mode, padding, key, ArrayUtils.isEmpty(iv) ? null : new IvParameterSpec(iv), null);
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
    public AES(Mode mode, Padding padding, SecretKey key, byte[] iv, Provider provider) {
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
    public AES(Mode mode, Padding padding, SecretKey key, IvParameterSpec iv) {
        this(mode.name(), padding.name(), key, iv, null);
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
    public AES(Mode mode, Padding padding, SecretKey key, IvParameterSpec iv, Provider provider) {
        this(mode.name(), padding.name(), key, iv, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     */
    public AES(String mode, String padding) {
        this(mode, padding, (Provider) null);
    }

    /**
     * 构造
     *
     * @param mode     模式
     * @param padding  补码方式
     * @param provider {@link Provider}
     */
    public AES(String mode, String padding, Provider provider) {
        this(mode, padding, (byte[]) null, provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public AES(String mode, String padding, byte[] key) {
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
    public AES(String mode, String padding, byte[] key, Provider provider) {
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
    public AES(String mode, String padding, byte[] key, byte[] iv) {
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
    public AES(String mode, String padding, byte[] key, byte[] iv, Provider provider) {
        this(mode, padding,//
                KeyUtils.generateKey(SymmetricAlgorithm.AES.getValue(), key, provider),//
                ArrayUtils.isEmpty(iv) ? null : new IvParameterSpec(iv), provider);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public AES(String mode, String padding, SecretKey key) {
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
    public AES(String mode, String padding, SecretKey key, Provider provider) {
        this(mode, padding, key, null, provider);
    }

    /**
     * 构造
     *
     * @param mode      模式
     * @param padding   补码方式
     * @param secretKey 密钥，支持三种密钥长度：128、192、256位
     * @param iv        加盐
     */
    public AES(String mode, String padding, SecretKey secretKey, IvParameterSpec iv) {
        this(mode, padding, secretKey, iv, null);
    }

    /**
     * 构造
     *
     * @param mode      模式
     * @param padding   补码方式
     * @param secretKey 密钥，支持三种密钥长度：128、192、256位
     * @param iv        加盐
     * @param provider  {@link Provider}
     */
    public AES(String mode, String padding, SecretKey secretKey, IvParameterSpec iv, Provider provider) {
        super(String.format("AES/%s/%s", mode, padding), secretKey, iv, provider);
    }
}
