package com.hb0730.commons.encrypt.symmetry;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
@Slf4j
public class RC4UtilsTest {
    private String content = "hello world";
    private String key = "58e5d06e89260420";

    @Test
    public void test() {
        String ciphertext = RC4Utils.encryptOrDecrypt(content, key);
        log.info(ciphertext);
        String decryptText = RC4Utils.encryptOrDecrypt(ciphertext, key);
        log.info(decryptText);
    }

}
