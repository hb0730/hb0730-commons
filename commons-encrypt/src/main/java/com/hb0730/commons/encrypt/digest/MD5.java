package com.hb0730.commons.encrypt.digest;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Provider;

/**
 * MD5算法
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class MD5 extends Digester {
    private static final long serialVersionUID = -770574049010257911L;

    /**
     * 创建md5
     *
     * @return {@link MD5}
     */
    public static MD5 create() {
        return new MD5();
    }

    /**
     * 构造
     */
    public MD5() {
        this((Provider) null);
    }

    /**
     * 构造
     *
     * @param provider {@link Provider}
     */
    public MD5(Provider provider) {
        super(DigestAlgorithm.MD5, provider);
    }


    /**
     * 构造
     *
     * @param salt 盐值
     */
    public MD5(byte[] salt) {
        this(salt, null);
    }

    /**
     * 构造
     *
     * @param salt     盐值
     * @param provider {@link Provider}
     */
    public MD5(byte[] salt, Provider provider) {
        this(salt, 0, 1, provider);
    }

    /**
     * 构造
     *
     * @param salt        盐值
     * @param digestCount 摘要次数，当此值小于等于1,默认为1。
     */
    public MD5(byte[] salt, int digestCount) {
        this(salt, digestCount, null);
    }

    /**
     * 构造
     *
     * @param salt        盐值
     * @param digestCount 摘要次数，当此值小于等于1,默认为1。
     * @param provider    {@link Provider}
     */
    public MD5(byte[] salt, int digestCount, Provider provider) {
        this(salt, 0, digestCount, provider);
    }

    /**
     * 构造
     *
     * @param salt         盐值
     * @param saltPosition 加盐位置，即将盐值字符串放置在数据的index数，默认0
     * @param digestCount  摘要次数，当此值小于等于1,默认为1。
     */
    public MD5(byte[] salt, int saltPosition, int digestCount) {
        this(salt, saltPosition, digestCount, null);
    }

    /**
     * 构造
     *
     * @param salt         盐值
     * @param saltPosition 加盐位置，即将盐值字符串放置在数据的index数，默认0
     * @param digestCount  摘要次数，当此值小于等于1,默认为1。
     * @param provider     {@link Provider}
     */
    public MD5(byte[] salt, int saltPosition, int digestCount, Provider provider) {
        this(provider);
        this.salt = salt;
        this.saltPosition = saltPosition;
        this.digestCount = digestCount;
    }

    /**
     * 生成16位MD5摘要,默认为小写,UTF-8编码
     *
     * @param data 数据
     * @return 16位MD5摘要
     */
    public String digestHex16(String data) {
        return digestHex16(data, StandardCharsets.UTF_8);
    }

    /**
     * 生成16位MD5摘要,默认为小写
     *
     * @param data    数据
     * @param charset 编码
     * @return 16位MD5摘要
     */
    public String digestHex16(String data, String charset) {
        return digestHex16(data, Charset.forName(charset));
    }

    /**
     * 生成16位MD5摘要,默认为小写
     *
     * @param data    数据
     * @param charset 编码
     * @return 16位MD5摘要
     */
    public String digestHex16(String data, Charset charset) {
        return digestHex16(data, charset, true);
    }

    /**
     * 生成16位MD5摘要
     *
     * @param data        数据
     * @param charset     编码
     * @param toLowerCase 是否为小写
     * @return 16位MD5摘要
     */
    public String digestHex16(String data, Charset charset, boolean toLowerCase) {
        return DigesterUtils.md5HexTo16(digestHex(data, charset, toLowerCase));
    }
}
