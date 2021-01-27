package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.exceptions.EncryptException;
import com.hb0730.commons.encrypt.utils.KeyUtils;
import com.hb0730.commons.encrypt.utils.SecureUtils;
import com.hb0730.commons.lang.Validate;
import com.hb0730.commons.lang.codec.Base64Utils;
import com.hb0730.commons.lang.codec.HexUtils;
import com.hb0730.commons.lang.collection.ArrayUtils;
import com.hb0730.commons.lang.constants.Charsets;
import com.hb0730.commons.lang.nums.RandomUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.Provider;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 对称加密算法<br>
 * 在对称加密算法中，数据发信方将明文（原始数据）和加密密钥一起经过特殊加密算法处理后，使其变成复杂的加密密文发送出去。<br>
 * 收信方收到密文后，若想解读原文，则需要使用加密用过的密钥及相同算法的逆算法对密文进行解密，才能使其恢复成可读明文。<br>
 * 在对称加密算法中，使用的密钥只有一个，发收信双方都使用这个密钥对数据进行加密和解密，这就要求解密方事先必须知道加密密钥。<br>
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class SymmetricCrypto implements Serializable {
    private static final long serialVersionUID = -8853771073013990289L;
    private static final String PBE = "PBE";
    /**
     * SecretKey 负责保存对称密钥
     */
    private SecretKey secretKey;
    /**
     * Cipher负责完成加密或解密工作
     */
    private Cipher cipher;
    /**
     * 加密解密参数
     */
    private AlgorithmParameterSpec params;
    /**
     * 是否0填充
     */
    private boolean isZeroPadding;

    /**
     * 构造，使用随机密钥
     *
     * @param algorithm {@link SymmetricAlgorithm}
     */
    public SymmetricCrypto(SymmetricAlgorithm algorithm) {
        this(algorithm, (Provider) null);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param algorithm {@link SymmetricAlgorithm}
     * @param provider  {@link Provider}
     */
    public SymmetricCrypto(SymmetricAlgorithm algorithm, Provider provider) {
        this(algorithm, (byte[]) null, provider);
    }


    /**
     * 构造，使用随机密钥
     *
     * @param algorithm 算法，可以是"algorithm/mode/padding"或者"algorithm"
     */
    public SymmetricCrypto(String algorithm) {
        this(algorithm, (Provider) null);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param algorithm 算法，可以是"algorithm/mode/padding"或者"algorithm"
     * @param provider  {@link Provider}
     */
    public SymmetricCrypto(String algorithm, Provider provider) {
        this(algorithm, (byte[]) null, provider);
    }


    /**
     * 构造
     *
     * @param algorithm 算法 {@link SymmetricAlgorithm}
     * @param key       自定义KEY
     */
    public SymmetricCrypto(SymmetricAlgorithm algorithm, byte[] key) {
        this(algorithm, key, null);
    }


    /**
     * 构造
     *
     * @param algorithm 算法 {@link SymmetricAlgorithm}
     * @param key       自定义KEY
     * @param provider  {@link Provider}
     */
    public SymmetricCrypto(SymmetricAlgorithm algorithm, byte[] key, Provider provider) {
        this(algorithm.getValue(), key, provider);
    }

    /**
     * 构造
     *
     * @param algorithm 算法 {@link SymmetricAlgorithm}
     * @param key       自定义KEY
     */
    public SymmetricCrypto(SymmetricAlgorithm algorithm, SecretKey key) {
        this(algorithm, key, null);
    }

    /**
     * 构造
     *
     * @param algorithm 算法 {@link SymmetricAlgorithm}
     * @param key       自定义KEY
     * @param provider  {@link Provider}
     */
    public SymmetricCrypto(SymmetricAlgorithm algorithm, SecretKey key, Provider provider) {
        this(algorithm.getValue(), key, provider);
    }

    /**
     * 构造
     *
     * @param algorithm 算法
     * @param key       密钥
     */
    public SymmetricCrypto(String algorithm, byte[] key) {
        this(algorithm, key, null);
    }

    /**
     * 构造
     *
     * @param algorithm 算法
     * @param key       密钥
     * @param provider  {@link Provider}
     */
    public SymmetricCrypto(String algorithm, byte[] key, Provider provider) {
        this(algorithm, KeyUtils.generateKey(algorithm, key, provider), provider);
    }

    /**
     * 构造
     *
     * @param algorithm 算法
     * @param secretKey 密钥
     */
    public SymmetricCrypto(String algorithm, SecretKey secretKey) {
        this(algorithm, secretKey, (AlgorithmParameterSpec) null);

    }

    /**
     * 构造
     *
     * @param algorithm 算法
     * @param secretKey 密钥
     * @param provider  {@link Provider}
     */
    public SymmetricCrypto(String algorithm, SecretKey secretKey, Provider provider) {
        this(algorithm, secretKey, null, provider);

    }

    /**
     * 构造
     *
     * @param algorithm  算法
     * @param secretKey  密钥
     * @param paramsSpec 算法参数，例如加盐等
     */
    public SymmetricCrypto(String algorithm, SecretKey secretKey, AlgorithmParameterSpec paramsSpec) {
        this(algorithm, secretKey, paramsSpec, null);
    }

    /**
     * 构造
     *
     * @param algorithm  算法
     * @param secretKey  密钥
     * @param paramsSpec 算法参数，例如加盐等
     * @param provider   {@link Provider}
     */
    public SymmetricCrypto(String algorithm, SecretKey secretKey, AlgorithmParameterSpec paramsSpec, Provider provider) {
        init(algorithm, secretKey, provider);
        if (null != paramsSpec) {
            setParams(paramsSpec);
        }
    }

    /**
     * 初始化
     *
     * @param algorithm 算法
     * @param secretKey 密钥，如果为<code>null</code>自动生成一个key
     * @param provider  {@link Provider}
     * @return SymmetricCrypto的子对象，即子对象自身
     */
    public SymmetricCrypto init(String algorithm, SecretKey secretKey, Provider provider) {
        Validate.notBlank(algorithm, "'algorithm' must be not blank !");
        this.secretKey = secretKey;
        if (algorithm.startsWith(PBE)) {
            this.params = new PBEParameterSpec(RandomUtils.randomBytes(8), 100);
        }
        // 检查是否为ZeroPadding，是则替换为NoPadding，并标记以便单独处理
        if (algorithm.contains(Padding.ZeroPadding.name())) {
            algorithm = algorithm.replace(Padding.ZeroPadding.name(), Padding.NoPadding.name());
            this.isZeroPadding = true;
        }
        this.cipher = SecureUtils.createCipher(algorithm, provider);
        return this;
    }
    // ------------------------------------------------------------------Encrypt

    /**
     * 加密
     *
     * @param data 被加密的bytes
     * @return 加密后的bytes
     */
    public byte[] encrypt(byte[] data) {
        try {
            if (null == this.params) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, params);
            }
            return cipher.doFinal(paddingDataWithZero(data, cipher.getBlockSize()));
        } catch (Exception e) {
            throw new EncryptException(e);
        }
    }

    /**
     * 加密
     *
     * @param data 数据
     * @return 加密后的Hex
     */
    public String encryptHex(byte[] data) {
        return HexUtils.encodeHexString(encrypt(data));
    }

    /**
     * 加密
     *
     * @param data 数据
     * @return 加密后的Base64
     */
    public String encryptBase64(byte[] data) {
        return Base64Utils.encodeToString(encrypt(data));
    }

    /**
     * 加密
     *
     * @param data    被加密的字符串
     * @param charset 编码
     * @return 加密后的bytes
     */
    public byte[] encrypt(String data, String charset) {
        return encrypt(data, Charset.forName(charset));
    }

    /**
     * 加密
     *
     * @param data    被加密的字符串
     * @param charset 编码
     * @return 加密后的bytes
     */
    public byte[] encrypt(String data, Charset charset) {
        return encrypt(data.getBytes(charset));
    }

    /**
     * 加密
     *
     * @param data    被加密的字符串
     * @param charset 编码
     * @return 加密后的Hex
     */
    public String encryptHex(String data, String charset) {
        return encryptHex(data, Charset.forName(charset));
    }

    /**
     * 加密
     *
     * @param data    被加密的字符串
     * @param charset 编码
     * @return 加密后的Hex
     */
    public String encryptHex(String data, Charset charset) {
        return HexUtils.encodeHexString(encrypt(data, charset));
    }

    /**
     * 加密
     *
     * @param data    被加密的字符串
     * @param charset 编码
     * @return 加密后的Base64
     */
    public String encryptBase64(String data, String charset) {
        return encryptBase64(data, Charset.forName(charset));
    }

    /**
     * 加密
     *
     * @param data    被加密的字符串
     * @param charset 编码
     * @return 加密后的Base64
     */
    public String encryptBase64(String data, Charset charset) {
        return Base64Utils.encodeToString(encrypt(data, charset));
    }

    /**
     * 加密，使用UTF-8编码
     *
     * @param data 被加密的字符串
     * @return 加密后的bytes
     */
    public byte[] encrypt(String data) {
        return encrypt(data.getBytes(Charsets.UTF_8));
    }

    /**
     * 加密，使用UTF-8编码
     *
     * @param data 被加密的字符串
     * @return 加密后的Hex
     */
    public String encryptHex(String data) {
        return HexUtils.encodeHexString(encrypt(data));
    }

    /**
     * 加密，使用UTF-8编码
     *
     * @param data 被加密的字符串
     * @return 加密后的Base64
     */
    public String encryptBase64(String data) {
        return Base64Utils.encodeToString(encrypt(data));
    }

    // --------------------------------------------------------------------------------- Decrypt

    /**
     * 解密
     *
     * @param bytes 被解密的bytes
     * @return 解密后的bytes
     */
    public byte[] decrypt(byte[] bytes) {
        final int blockSize;
        final byte[] decryptData;
        try {
            if (null == this.params) {
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey, params);
            }
            blockSize = cipher.getBlockSize();
            decryptData = cipher.doFinal(bytes);
        } catch (Exception e) {
            throw new EncryptException(e);
        }
        return removePadding(decryptData, blockSize);
    }

    /**
     * 解密为字符串
     *
     * @param bytes   被解密的bytes
     * @param charset 解密后的charset
     * @return 解密后的String
     */
    public String decryptStr(byte[] bytes, Charset charset) {
        return new String(decrypt(bytes), charset);
    }

    /**
     * 解密为字符串，默认UTF-8编码
     *
     * @param bytes 被解密的bytes
     * @return 解密后的String
     */
    public String decryptStr(byte[] bytes) {
        return decryptStr(bytes, Charsets.UTF_8);
    }

    /**
     * 解密Hex（16进制）或Base64表示的字符串
     *
     * @param data 被解密的String，必须为16进制字符串或Base64表示形式
     * @return 解密后的bytes
     */
    public byte[] decrypt(String data) {
        return decrypt(SecureUtils.decode(data));
    }

    /**
     * 解密Hex（16进制）或Base64表示的字符串
     *
     * @param data    被解密的String
     * @param charset 解密后的charset
     * @return 解密后的String
     */
    public String decryptStr(String data, Charset charset) {
        return new String(decrypt(data), charset);
    }

    /**
     * 解密Hex（16进制）或Base64表示的字符串，默认UTF-8编码
     *
     * @param data 被解密的String
     * @return 解密后的String
     */
    public String decryptStr(String data) {
        return decryptStr(data, Charsets.UTF_8);
    }

    /**
     * 设置 {@link AlgorithmParameterSpec}，通常用于加盐或偏移向量
     *
     * @param params {@link AlgorithmParameterSpec}
     * @return 自身
     */
    public SymmetricCrypto setParams(AlgorithmParameterSpec params) {
        this.params = params;
        return this;
    }

    /**
     * 设置偏移向量
     *
     * @param iv {@link IvParameterSpec}偏移向量
     * @return 自身
     */
    public SymmetricCrypto setIv(IvParameterSpec iv) {
        setParams(iv);
        return this;
    }

    /**
     * 设置偏移向量
     *
     * @param iv 偏移向量，加盐
     * @return 自身
     */
    public SymmetricCrypto setIv(byte[] iv) {
        setIv(new IvParameterSpec(iv));
        return this;
    }

    /**
     * 获得对称密钥
     *
     * @return 获得对称密钥
     */
    public SecretKey getSecretKey() {
        return secretKey;
    }

    /**
     * 获得加密或解密器
     *
     * @return 加密或解密
     */
    public Cipher getCipher() {
        return cipher;
    }

    /**
     * 数据按照blockSize的整数倍长度填充填充0
     *
     * <p>
     * 在{@link Padding#ZeroPadding} 模式下，且数据长度不是blockSize的整数倍才有效，否则返回原数据
     *
     * <p>
     * 见：https://blog.csdn.net/OrangeJack/article/details/82913804
     *
     * @param data      数据
     * @param blockSize 块大小
     * @return 填充后的数据，如果isZeroPadding为false或长度刚好，返回原数据
     */
    private byte[] paddingDataWithZero(byte[] data, int blockSize) {
        if (this.isZeroPadding) {
            final int length = data.length;
            // 按照块拆分后的数据中多余的数据
            final int remainLength = length % blockSize;
            if (remainLength > 0) {
                // 新长度为blockSize的整数倍，多余部分填充0
                return (byte[]) ArrayUtils.resize(data, length + blockSize - remainLength);
            }
        }
        return data;
    }

    /**
     * 数据按照blockSize去除填充部分，用于解密
     *
     * <p>
     * 在{@link Padding#ZeroPadding} 模式下，且数据长度不是blockSize的整数倍才有效，否则返回原数据
     *
     * @param data      数据
     * @param blockSize 块大小
     * @return 去除填充后的数据，如果isZeroPadding为false或长度刚好，返回原数据
     */
    private byte[] removePadding(byte[] data, int blockSize) {
        if (this.isZeroPadding) {
            final int length = data.length;
            final int remainLength = length % blockSize;
            if (remainLength == 0) {
                // 解码后的数据正好是块大小的整数倍，说明可能存在补0的情况，去掉末尾所有的0
                int i = length - 1;
                while (i >= 0 && 0 == data[i]) {
                    i--;
                }
                return (byte[]) ArrayUtils.resize(data, i + 1);
            }
        }
        return data;
    }


}
