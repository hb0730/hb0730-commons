package com.hb0730.commons.encrypt.constant;

/**
 * Hmac类型
 *
 * @author bing_huang
 * @since 1.0.2
 */
public enum HmacType {
    HmacMD5("HmacMD5"),
    HmacSHA1("HmacSHA1"),
    HmacSHA224("HmacSHA224"),
    HmacSHA256("HmacSHA256"),
    HmacSHA384("HmacSHA384"),
    HmacSHA512("HmacSHA512"),
    ;

    private String type;

    HmacType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
