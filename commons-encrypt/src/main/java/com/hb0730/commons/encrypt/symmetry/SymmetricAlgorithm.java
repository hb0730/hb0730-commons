package com.hb0730.commons.encrypt.symmetry;

/**
 * 对称算法类型
 *
 * @author bing_huang
 * @see <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyGenerator">KeyGenerator Algorithms</a>
 * @since 2.1.0
 */
public enum SymmetricAlgorithm {
    /**
     * 默认的AES加密方式：AES/ECB/PKCS5Padding
     *
     * @see <a href="http://csrc.nist.gov/publications/fips/index.html">FIPS 197</a>
     */
    AES("AES"),
    ARCFOUR("ARCFOUR"),
    /**
     * Blowfish
     *
     * @see <a href="http://www.schneier.com/blowfish.html">blowfish</a>
     */
    Blowfish("Blowfish"),

    /**
     * 默认的DES加密方式：DES/ECB/PKCS5Padding
     *
     * @see <a href="http://csrc.nist.gov/publications/fips/index.html">FIPS PUB 46-3</a>
     */
    DES("DES"),
    /**
     * 3DES算法，默认实现为：DESede/CBC/PKCS5Padding
     */
    DESede("DESede"),
    RC2("RC2"),

    PBEWithMD5AndDES("PBEWithMD5AndDES"),
    PBEWithSHA1AndDESede("PBEWithSHA1AndDESede"),
    PBEWithSHA1AndRC2_40("PBEWithSHA1AndRC2_40");;


    private final String value;

    SymmetricAlgorithm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
