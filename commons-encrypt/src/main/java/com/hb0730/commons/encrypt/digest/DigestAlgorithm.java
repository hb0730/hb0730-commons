package com.hb0730.commons.encrypt.digest;

/**
 * 摘要算法类型
 *
 * @author bing_huang
 * @see <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#MessageDigest">MessageDigest</a>
 * @since 2.1.0
 */
public enum DigestAlgorithm {
    /**
     * md2
     *
     * @see <a href="http://www.ietf.org/rfc/rfc1319.txt">rfc1319</a>
     */
    MD2("MD2"),
    /**
     * md5
     *
     * @see <a href="http://www.ietf.org/rfc/rfc1321.txt">rfc1321</a>
     */
    MD5("MD5"),
    /**
     * SHA-1
     *
     * @see <a href="http://csrc.nist.gov/publications/fips/index.html">FIPS PUB 180-2</a>
     */
    SHA1("SHA-1"),
    /**
     * SHA-256
     *
     * @see <a href="http://csrc.nist.gov/publications/fips/index.html">FIPS PUB 180-2</a>
     */
    SHA256("SHA-256"),
    /**
     * SHA-384
     *
     * @see <a href="http://csrc.nist.gov/publications/fips/index.html">FIPS PUB 180-2</a>
     */
    SHA384("SHA-384"),
    /**
     * SHA-512
     *
     * @see <a href="http://csrc.nist.gov/publications/fips/index.html">FIPS PUB 180-2</a>
     */
    SHA512("SHA-512");

    private final String value;

    /**
     * 构造函数
     *
     * @param value 算法字符串表示
     */
    DigestAlgorithm(String value) {
        this.value = value;
    }

    /**
     * 获取算法字符串表示
     *
     * @return 算法字符串表示
     */
    public String getValue() {
        return value;
    }
}
