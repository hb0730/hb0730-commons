package com.hb0730.commons.encrypt.digest;

/**
 * 摘要算法工具类
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class DigesterUtils {
    /**
     * 32位MD5转16位MD5
     *
     * @param md5Hex 32位MD5
     * @return 16位MD5
     */
    public static String md5HexTo16(String md5Hex) {
        return md5Hex.substring(8, 24);
    }
}
