package com.hb0730.commons.encrypt.digest;

/**
 * HMAC算法类型
 *
 * @author bing_huang
 * @see <a href=" https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Mac">MAC</a>
 * @since 2.1.0
 */
public enum HmacAlgorithm {
    /**
     * Hmac-MD5
     *
     * @see <a href="http://www.ietf.org/rfc/rfc2104.txt">rfc2104</a>
     */
    HmacMD5("HmacMD5"),
    /**
     * Hmac-SHA1
     *
     * @see <a href="http://www.ietf.org/rfc/rfc2104.txt">rfc2104</a>
     */
    HmacSHA1("HmacSHA1"),
    /**
     * Hmac-SHA256
     *
     * @see <a href="http://www.ietf.org/rfc/rfc2104.txt">rfc2104</a>
     */
    HmacSHA256("HmacSHA256"),
    /**
     * Hmac-SHA384
     *
     * @see <a href="http://www.ietf.org/rfc/rfc2104.txt">rfc2104</a>
     */
    HmacSHA384("HmacSHA384"),
    /**
     * Hmac-SHA512
     *
     * @see <a href="http://www.ietf.org/rfc/rfc2104.txt">rfc2104</a>
     */
    HmacSHA512("HmacSHA512"),
    /**
     * HmacSM3算法实现，需要BouncyCastle库支持
     */
    HmacSM3("HmacSM3");
    private final String value;

    HmacAlgorithm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
