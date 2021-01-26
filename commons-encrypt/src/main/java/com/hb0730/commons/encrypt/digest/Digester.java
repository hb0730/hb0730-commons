package com.hb0730.commons.encrypt.digest;

import com.hb0730.commons.encrypt.exceptions.EncryptException;
import com.hb0730.commons.lang.codec.HexUtils;
import com.hb0730.commons.lang.collection.ArrayUtils;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/**
 * 摘要算法,(该设计来自<a href="http://www.hutool.cn/">hutool</a>)
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class Digester implements Serializable {
    private static final long serialVersionUID = -1801446861056947483L;
    private MessageDigest messageDigest;
    /**
     * 盐值
     */
    protected byte[] salt;
    /**
     * 加盐位置，即将盐值字符串放置在数据的index数，默认0
     */
    protected int saltPosition;
    /**
     * 散列次数
     */
    protected int digestCount;

    /**
     * 构造,默认使用JDK
     *
     * @param algorithm 算法枚举
     */
    public Digester(DigestAlgorithm algorithm) {
        init(algorithm.getValue(), null);
    }

    /**
     * 构造,默认使用JDK
     *
     * @param algorithm 算法枚举
     */
    public Digester(String algorithm) {
        init(algorithm, null);
    }

    /**
     * 构造
     *
     * @param algorithm 算法
     * @param provider  算法提供者,可以为{@code null},如果为{@code null},则使用JDK默认
     */
    public Digester(String algorithm, Provider provider) {
        init(algorithm, provider);
    }

    /**
     * 构造
     *
     * @param algorithm 算法
     * @param provider  算法提供者,可以为{@code null},如果为{@code null},则使用JDK默认
     */
    public Digester(DigestAlgorithm algorithm, Provider provider) {
        init(algorithm.getValue(), provider);
    }

    /**
     * 获取对应的摘要算法
     *
     * @param algorithm 算法
     * @param provider  算法提供者,可以为{@code null},如果为{@code null},则使用JDK默认
     * @return {@link Digester}
     * @throws EncryptException Cause by IOException
     */
    public Digester init(String algorithm, Provider provider) {
        try {
            if (null == provider) {
                this.messageDigest = MessageDigest.getInstance(algorithm);
            } else {
                this.messageDigest = MessageDigest.getInstance(algorithm, provider);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException(e);
        }
        return this;
    }

    /**
     * 设置加盐内容
     *
     * @param salt 盐值
     * @return this
     */
    public Digester setSalt(byte[] salt) {
        this.salt = salt;
        return this;
    }

    /**
     * 设置加盐的位置，只有盐值存在时有效<br>
     * 加盐的位置指盐位于数据byte数组中的位置，例如：
     *
     * <pre>
     * data: 0123456
     * </pre>
     * <p>
     * 则当saltPosition = 2时，盐位于data的1和2中间，即第二个空隙，即：
     *
     * <pre>
     * data: 01[salt]23456
     * </pre>
     *
     * @param saltPosition 盐的位置
     * @return this
     */
    public Digester setSaltPosition(int saltPosition) {
        this.saltPosition = saltPosition;
        return this;
    }


    /**
     * 设置重复计算摘要值次数
     *
     * @param digestCount 摘要值次数
     * @return this
     */
    public Digester setDigestCount(int digestCount) {
        this.digestCount = digestCount;
        return this;
    }

    /**
     * 重置{@link MessageDigest}
     *
     * @return this
     */
    public Digester reset() {
        this.messageDigest.reset();
        return this;
    }

    /**
     * 生成摘要，考虑加盐和重复摘要次数
     *
     * @param data 数据bytes
     * @return 摘要bytes
     */
    public byte[] digest(byte[] data) {
        byte[] result;
        // 加盐在开头，自动忽略空盐值
        if (this.saltPosition <= 0) {
            result = doDigest(this.salt, data);
            // 加盐在末尾，自动忽略空盐值
        } else if (this.saltPosition > data.length) {
            result = doDigest(data, this.salt);
            // 加盐在中间
        } else if (ArrayUtils.isNotEmpty(this.salt)) {
            this.messageDigest.update(data, 0, this.saltPosition);
            this.messageDigest.update(this.salt);
            this.messageDigest.update(data, this.saltPosition, data.length - this.saltPosition);
            result = this.messageDigest.digest();
            // 无加盐
        } else {
            result = doDigest(data);
        }
        return resetAndRepeatDigest(result);
    }

    //----------------------------digest

    /**
     * 生成摘要,默认UTF-8编码
     *
     * @param data 被摘要数据
     * @return 摘要
     */
    public byte[] digest(String data) {
        return digest(data, StandardCharsets.UTF_8);
    }

    /**
     * 生成摘要
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return 摘要
     */
    public byte[] digest(String data, String charset) {
        return digest(data, Charset.forName(charset));
    }

    /**
     * 生成摘要
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return 摘要
     */
    public byte[] digest(String data, Charset charset) {
        return digest(data.getBytes(charset));
    }

    /**
     * 生成摘要，并转成16进制的字符串,默认小写,UTF-8编码
     *
     * @param data 被摘要数据
     * @return 摘要
     */
    public String digestHex(String data) {
        return digestHex(data, StandardCharsets.UTF_8);
    }

    /**
     * 生成摘要，并转成16进制的字符串,默认小写
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return 摘要
     */
    public String digestHex(String data, String charset) {
        return digestHex(data, Charset.forName(charset));
    }

    /**
     * 生成摘要，并转成16进制的字符串,默认小写
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return 摘要
     */
    public String digestHex(String data, Charset charset) {
        return digestHex(data, charset, true);
    }

    /**
     * 生成摘要，并转成16进制的字符串
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
     * 获得 {@link MessageDigest}
     *
     * @return {@link MessageDigest}
     */
    public MessageDigest getDigest() {
        return messageDigest;
    }

    /**
     * 生成摘要
     *
     * @param datas 数据bytes
     * @return 摘要bytes
     */
    private byte[] doDigest(byte[]... datas) {
        for (byte[] data : datas) {
            if (null != data) {
                this.messageDigest.update(data);
            }
        }
        return this.messageDigest.digest();
    }

    /**
     * 重复计算摘要，取决于{@link #digestCount} 值<br>
     * 每次计算摘要前都会重置{@link #digest}
     *
     * @param digestData 第一次摘要过的数据
     * @return 摘要
     */
    private byte[] resetAndRepeatDigest(byte[] digestData) {
        final int digestCount = Math.max(1, this.digestCount);
        reset();
        for (int i = 0; i < digestCount - 1; i++) {
            digestData = doDigest(digestData);
            reset();
        }
        return digestData;
    }

}
