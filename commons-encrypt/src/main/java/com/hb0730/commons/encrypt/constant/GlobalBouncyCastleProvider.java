package com.hb0730.commons.encrypt.constant;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;

/**
 * 全局单例的 org.bouncycastle.jce.provider.BouncyCastleProvider 对象
 *
 * @author bing_huang
 * @since 2.1.0
 */
public enum GlobalBouncyCastleProvider {
    /**
     * 实例
     */
    INSTANCE;
    private Provider provider;
    private static boolean useBouncyCastle = true;


    GlobalBouncyCastleProvider() {
        try {
            this.provider = new BouncyCastleProvider();
        } catch (NoClassDefFoundError e) {
            //
        }
    }

    /**
     * 获取{@link Provider}
     *
     * @return {@link Provider}
     */
    public Provider getProvider() {
        return useBouncyCastle ? provider : null;
    }

    /**
     * 设置是否使用Bouncy Castle库<br>
     * 如果设置为false，表示强制关闭Bouncy Castle而使用JDK
     *
     * @param isUseBouncyCastle 是否使用BouncyCastle库
     */
    public static void setUseBouncyCastle(boolean isUseBouncyCastle) {
        useBouncyCastle = isUseBouncyCastle;
    }
}
