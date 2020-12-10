package com.hb0730.commons.encrypt.digest;

import com.hb0730.commons.encrypt.constant.Algorithm;
import com.hb0730.commons.lang.codec.HexUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Security;

/**
 * SM3工具类
 * SM3密码杂凑算法，类似MD5
 *
 * @author bing_huang
 * @since 1.0.3
 */
public class SM3Utils {

    static {
        //导入Provider，BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * SM3加密
     *
     * @param data 加密内容
     * @return 密文（16进制字符串）
     */
    public static String sm3(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance(Algorithm.SM3.getAlgorithm());
            byte[] bytes = md.digest(data.getBytes());
            return HexUtils.encodeHexString(bytes, true);
        } catch (Exception e) {
            throw new IllegalArgumentException("sm3 error", e);
        }
    }
}
