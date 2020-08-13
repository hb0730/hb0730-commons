package com.hb0730.commons.encrypt.pojo;

import lombok.Data;

import java.math.BigInteger;

/**
 * RSA公私钥
 *
 * @author bing_huang
 * @since 1.0.2
 */
@Data
public class RSAKeyPair {
    /**
     * 公钥（Base64编码）
     */
    private String publicKey;

    /**
     * 私钥（Base64编码）
     */
    private String privateKey;

    /**
     * 模数
     */
    private BigInteger modules;
}
