package com.hb0730.commons.encrypt.digest;

import com.hb0730.commons.encrypt.exceptions.EncryptException;
import com.hb0730.commons.encrypt.utils.KeyUtils;
import com.hb0730.commons.lang.codec.HexUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Provider;

/**
 * HMAC摘要算法<br>
 * HMAC，全称为“Hash Message Authentication Code”，中文名“散列消息鉴别码”<br>
 * 主要是利用哈希算法，以一个密钥和一个消息为输入，生成一个消息摘要作为输出。<br>
 * 一般的，消息鉴别码用于验证传输于两个共 同享有一个密钥的单位之间的消息。<br>
 * HMAC 可以与任何迭代散列函数捆绑使用。MD5 和 SHA-1 就是这种散列函数。HMAC 还可以使用一个用于计算和确认消息鉴别值的密钥。<br>
 * 注意：此对象实例化后为非线程安全！
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class HMac implements Serializable {
    private static final long serialVersionUID = 4332051904125084523L;
    private Mac mac;

    /**
     * 创建HMac对象，调用digest方法可获得hmac值
     *
     * @param algorithm {@link HmacAlgorithm}
     * @return {@link HMac}
     */
    public static HMac hmac(HmacAlgorithm algorithm) {
        return new HMac(algorithm);
    }

    /**
     * 创建HMac对象，调用digest方法可获得hmac值
     *
     * @param algorithm {@link HmacAlgorithm}
     * @param key       密钥{@link SecretKey}，如果为<code>null</code>生成随机密钥
     * @return {@link HMac}
     */
    public static HMac hmac(HmacAlgorithm algorithm, byte[] key) {
        return new HMac(algorithm, key);
    }

    /**
     * 创建HMac对象，调用digest方法可获得hmac值
     *
     * @param algorithm {@link HmacAlgorithm}
     * @param key       密钥{@link SecretKey}，如果为<code>null</code>生成随机密钥
     * @return {@link HMac}
     */
    public static HMac hmac(HmacAlgorithm algorithm, SecretKey key) {
        return new HMac(algorithm, key);
    }

    /**
     * 构造，自动生成密钥
     *
     * @param algorithm 算法 {@link HmacAlgorithm}
     */
    public HMac(HmacAlgorithm algorithm) {
        this(algorithm, (Key) null);
    }

    /**
     * 构造
     *
     * @param algorithm 算法 {@link HmacAlgorithm}
     * @param key       密钥
     */
    public HMac(HmacAlgorithm algorithm, byte[] key) {
        this(algorithm.getValue(), key);
    }

    /**
     * 构造
     *
     * @param algorithm 算法 {@link HmacAlgorithm}
     * @param key       密钥
     */
    public HMac(HmacAlgorithm algorithm, Key key) {
        this(algorithm.getValue(), key);
    }

    /**
     * 构造
     *
     * @param algorithm 算法
     * @param key       密钥
     */
    public HMac(String algorithm, byte[] key) {
        this(algorithm, (key == null) ? null : new SecretKeySpec(key, algorithm));
    }

    /**
     * 构造
     *
     * @param algorithm 算法
     * @param key       密钥{@link javax.crypto.SecretKey}
     */
    public HMac(String algorithm, Key key) {
        this(algorithm, key, null);
    }

    /**
     * 构造
     *
     * @param algorithm 算法
     * @param key       密钥{@link javax.crypto.SecretKey}
     * @param provider  {@link Provider}
     */
    public HMac(String algorithm, Key key, Provider provider) {
        init(algorithm, key, provider);
    }

    /**
     * 初始化
     *
     * @param algorithm 算法
     * @param key       密钥{@link javax.crypto.SecretKey}
     * @param provider  {@link Provider}
     * @return {@link HMac}
     */
    public HMac init(String algorithm, Key key, Provider provider) {
        try {

            if (null == provider) {
                mac = Mac.getInstance(algorithm);
            } else {
                mac = Mac.getInstance(algorithm, provider);
            }
            if (null == key) {
                key = KeyUtils.generateKey(algorithm, provider);
            }
            mac.init(key);
        } catch (Exception e) {
            throw new EncryptException(e);
        }
        return this;
    }

    /**
     * 生成摘要
     *
     * @param data 数据
     * @return 摘要bytes
     */
    public byte[] digest(byte[] data) {
        return mac.doFinal(data);
    }
    // ------------------------------------------------------------------------------------------- Digest

    /**
     * 生成文件摘要
     *
     * @param data 被摘要数据
     * @return 摘要
     */
    public byte[] digest(String data) {
        return digest(data, StandardCharsets.UTF_8);
    }

    /**
     * 生成文件摘要
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return 摘要
     */
    public byte[] digest(String data, String charset) {
        return digest(data, Charset.forName(charset));
    }

    /**
     * 生成文件摘要
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return 摘要
     */
    public byte[] digest(String data, Charset charset) {
        return digest(data.getBytes(charset));
    }

    /**
     * 生成文件摘要
     *
     * @param data 被摘要数据
     * @return 摘要
     */
    public String digestHex(String data) {
        return digestHex(data, true);
    }

    /**
     * 生成文件摘要
     *
     * @param data        被摘要数据
     * @param toLowerCase 是否转小写
     * @return 摘要
     */
    public String digestHex(String data, boolean toLowerCase) {
        return digestHex(data, StandardCharsets.UTF_8, toLowerCase);
    }

    /**
     * 生成文件摘要，并转为16进制字符串
     *
     * @param data        被摘要数据
     * @param charset     编码
     * @param toLowerCase 是否转小写
     * @return 摘要
     */
    public String digestHex(String data, String charset, boolean toLowerCase) {
        return digestHex(data, Charset.forName(charset), toLowerCase);
    }

    /**
     * 生成文件摘要，并转为16进制字符串
     *
     * @param data        被摘要数据
     * @param charset     编码
     * @param toLowerCase 是否转小写
     * @return 摘要
     */
    public String digestHex(String data, Charset charset, boolean toLowerCase) {
        return HexUtils.encodeHexString(digest(data, charset), toLowerCase);
    }

    /**
     * 获得 {@link Mac}
     *
     * @return {@link Mac}
     */
    public Mac getMac() {
        return mac;
    }

    /**
     * 获取MAC算法块大小
     *
     * @return MAC算法块大小
     */
    public int getMacLength() {
        return mac.getMacLength();
    }

    /**
     * 获取当前算法
     *
     * @return 算法
     */
    public String getAlgorithm() {
        return this.mac.getAlgorithm();
    }


}
