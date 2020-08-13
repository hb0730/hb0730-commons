package com.hb0730.commons.encrypt.constant;

/**
 * SM2数字签名类型
 * 详见{@link org.bouncycastle.jcajce.provider.asymmetric.ec.GMSignatureSpi}
 *
 * @author bing_huang
 * @since 1.0.2
 */
public enum SM2SignType {
    SHA256withSM2("SHA256withSM2"),
    sm3WithSM2("sm3WithSM2"),
    ;

    private String type;

    SM2SignType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
