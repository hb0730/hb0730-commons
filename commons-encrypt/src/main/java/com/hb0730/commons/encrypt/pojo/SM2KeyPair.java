package com.hb0730.commons.encrypt.pojo;

import lombok.Data;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/**
 * SM2公私钥
 *
 * @author bing_huang
 * @since 1.0.2
 */
@Data
public class SM2KeyPair {
    /**
     * 公钥（X509格式）
     */
    private String publicKey;

    /**
     * 私钥（PKCS8格式）
     */
    private String privateKey;

    /**
     * 公钥
     */
    private ECPublicKey ecPublicKey;

    /**
     * 私钥
     */
    private ECPrivateKey ecPrivateKey;

}
