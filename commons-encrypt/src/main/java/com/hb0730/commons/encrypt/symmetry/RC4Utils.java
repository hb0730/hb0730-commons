package com.hb0730.commons.encrypt.symmetry;

/**
 * RC4工具类
 * RC4：在1987年被RSA三人组中的头号人物罗纳德所创建，密钥长40-1024，块长64，用于保护商业机密和互联网中
 * 优点：算法简单，运行速度快，安全性高，算法的速度可以达到DES加密的10倍左右，且具有很高级别的非线性，
 * 密钥长度是可变的，可变范围为1-256字节(8-2048比特)，但一般为256字节
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class RC4Utils {
    /**
     * S盒长度
     */
    private static final int BOX_LENGTH = 256;

    /**
     * 加密和解密
     *
     * @param content 内容
     * @param key     密钥
     * @return 加密或解密后的值
     */
    public static String encryptOrDecrypt(String content, String key) {
        // S盒
        Integer[] box = new Integer[BOX_LENGTH];
        // 生成的密钥流
        Character[] characters = new Character[content.length()];
        StringBuilder stringBuilder = new StringBuilder();

        ksa(box, key);
        rpga(box, characters, content.length());

        for (int i = 0; i < content.length(); ++i) {
            stringBuilder.append((char) (content.charAt(i) ^ characters[i]));
        }
        return stringBuilder.toString();
    }

    /**
     * 密钥调度算法--利用key来对S盒做一个置换，也就是对S盒重新排列
     *
     * @param sbox 数组
     * @param key  密钥
     */
    private static void ksa(Integer[] sbox, String key) {
        for (int i = 0; i < BOX_LENGTH; ++i) {
            sbox[i] = i;
        }

        int j = 0;
        for (int i = 0; i < BOX_LENGTH; ++i) {
            j = (j + sbox[i] + key.charAt(i % key.length())) % BOX_LENGTH;
            swap(sbox, i, j);
        }
    }

    /**
     * 伪随机生成算法--利用上面重新排列的S盒来产生任意长度的密钥流
     *
     * @param sbox       数组
     * @param characters 密钥流容器
     * @param length     内容长度
     */
    private static void rpga(Integer[] sbox, Character[] characters, int length) {
        int i = 0, j = 0;
        for (int k = 0; k < length; ++k) {
            i = (i + 1) % BOX_LENGTH;
            j = (j + sbox[i]) % BOX_LENGTH;
            swap(sbox, i, j);
            characters[k] = (char) (sbox[(sbox[i] + sbox[j]) % BOX_LENGTH]).intValue();
        }
    }

    /**
     * 交换指定两个位置的值
     *
     * @param sbox 数组
     * @param i    位置1
     * @param j    位置2
     */
    private static void swap(Integer[] sbox, int i, int j) {
        Integer mTemp = sbox[i];
        sbox[i] = sbox[j];
        sbox[j] = mTemp;
    }

}
