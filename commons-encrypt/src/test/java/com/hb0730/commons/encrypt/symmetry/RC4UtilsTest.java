package com.hb0730.commons.encrypt.symmetry;

import org.junit.Test;

public class RC4UtilsTest {
    private String content = "hello world";
    private String key = "58e5d06e89260420";

    @Test
    public void test() {
        String ciphertext = RC4Utils.encryptOrDecrypt(content, key);
        System.out.println(ciphertext);
        String decryptText = RC4Utils.encryptOrDecrypt(ciphertext, key);
        System.out.println(decryptText);
    }

}
