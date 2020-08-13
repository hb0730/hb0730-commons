package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.constant.Algorithm;
import com.hb0730.commons.encrypt.constant.Mode;
import com.hb0730.commons.encrypt.constant.Padding;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Clock;
import java.util.Arrays;

@Slf4j
public class AESUtilsTest {
    private String content = "0123456789ABCDEF";
    private String key = "hello world, hi!";
    private String iv = "0102030405060708";

    @Test
    public void getAlgorithm() {
        String encrypt = Algorithm.getAlgorithm(Algorithm.AES, Mode.CBC, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = Algorithm.getAlgorithm(Algorithm.AES, Mode.ECB, Padding.NoPadding);
        log.info(encrypt);

    }

    @Test
    public void generateKey() {
        String encrypt = Arrays.toString(AESUtils.generateKey(256));
        log.info(encrypt);
    }

    @Test
    public void testTime() {
        long millis = Clock.systemUTC().millis();
        for (int i = 0; i < 10000; i++) {
            test();
        }
        log.info("" + (Clock.systemUTC().millis() - millis));
    }

    @Test
    public void tes1t() {
        String encrypt = AESUtils.encrypt("testtesttesttestt", key, Mode.ECB, Padding.NoPadding);
        log.info(encrypt);
    }

    @Test
    public void test() {
        String encrypt = AESUtils.encrypt(content, key);
        log.info(encrypt);
        encrypt = AESUtils.decrypt(AESUtils.encrypt(content, key), key);
        log.info(encrypt);

        encrypt = AESUtils.encrypt(content, key, iv);
        log.info(encrypt);
        encrypt = AESUtils.decrypt(AESUtils.encrypt(content, key, iv), key, iv);
        log.info(encrypt);

        encrypt = AESUtils.encrypt(content, key, Mode.ECB, Padding.NoPadding);
        log.info(encrypt);
        encrypt = AESUtils.decrypt(AESUtils.encrypt(content, key, Mode.ECB, Padding.NoPadding), key, Mode.ECB, Padding.NoPadding);
        log.info(encrypt);

        encrypt = AESUtils.encrypt(content, key, Mode.ECB, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = AESUtils.decrypt(AESUtils.encrypt(content, key, Mode.ECB, Padding.PKCS7Padding), key, Mode.ECB, Padding.PKCS7Padding);
        log.info(encrypt);

        encrypt = AESUtils.encrypt(content, key, iv, Mode.CBC, Padding.NoPadding);
        log.info(encrypt);
        encrypt = AESUtils.decrypt(AESUtils.encrypt(content, key, iv, Mode.CBC, Padding.NoPadding), key, iv, Mode.CBC, Padding.NoPadding);
        log.info(encrypt);

        encrypt = AESUtils.encrypt(content, key, iv, Mode.CBC, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = AESUtils.decrypt(AESUtils.encrypt(content, key, iv, Mode.CBC, Padding.PKCS7Padding), key, iv, Mode.CBC, Padding.PKCS7Padding);
        log.info(encrypt);

        encrypt = AESUtils.encrypt(content, key, iv, Mode.CFB, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = AESUtils.decrypt(AESUtils.encrypt(content, key, iv, Mode.CFB, Padding.PKCS7Padding), key, iv, Mode.CFB, Padding.PKCS7Padding);
        log.info(encrypt);

        encrypt = AESUtils.encrypt(content, key, iv, Mode.CTR, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = AESUtils.decrypt(AESUtils.encrypt(content, key, iv, Mode.CTR, Padding.PKCS7Padding), key, iv, Mode.CTR, Padding.PKCS7Padding);
        log.info(encrypt);

        encrypt = AESUtils.encrypt(content, key, iv, Mode.OFB, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = AESUtils.decrypt(AESUtils.encrypt(content, key, iv, Mode.OFB, Padding.PKCS7Padding), key, iv, Mode.OFB, Padding.PKCS7Padding);
        log.info(encrypt);


    }
}
