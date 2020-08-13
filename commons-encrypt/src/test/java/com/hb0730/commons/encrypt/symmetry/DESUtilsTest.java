package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.constant.Algorithm;
import com.hb0730.commons.encrypt.constant.Mode;
import com.hb0730.commons.encrypt.constant.Padding;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Clock;
import java.util.Arrays;

@Slf4j
public class DESUtilsTest {
    private String content = "12345678";
    private String key = "12345678";
    private String iv = "01020304";

    @Test
    public void getAlgorithm() {
        String encrypt = Algorithm.getAlgorithm(Algorithm.DES, Mode.CBC, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = Algorithm.getAlgorithm(Algorithm.DES, Mode.ECB, Padding.NoPadding);
        log.info(encrypt);
    }

    @Test
    public void generateKey() {
        String encrypt = DESUtils.encrypt(content, key, Mode.ECB, Padding.NoPadding);
        log.info(encrypt);
        encrypt = Arrays.toString(DESUtils.generateKey());
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
    public void test() {
        String encrypt = DESUtils.encrypt(content, key);
        log.info(encrypt);
        encrypt = DESUtils.decrypt(DESUtils.encrypt(content, key), key);
        log.info(encrypt);

        encrypt = DESUtils.encrypt(content, key, iv);
        log.info(encrypt);
        encrypt = DESUtils.decrypt(DESUtils.encrypt(content, key, iv), key, iv);
        log.info(encrypt);

        encrypt = DESUtils.encrypt(content, key, Mode.ECB, Padding.NoPadding);
        log.info(encrypt);
        encrypt = DESUtils.decrypt(DESUtils.encrypt(content, key, Mode.ECB, Padding.NoPadding), key, Mode.ECB, Padding.NoPadding);
        log.info(encrypt);

        encrypt = DESUtils.encrypt(content, key, Mode.ECB, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = DESUtils.decrypt(DESUtils.encrypt(content, key, Mode.ECB, Padding.PKCS7Padding), key, Mode.ECB, Padding.PKCS7Padding);
        log.info(encrypt);

        encrypt = DESUtils.encrypt(content, key, iv, Mode.CBC, Padding.NoPadding);
        log.info(encrypt);
        encrypt = DESUtils.decrypt(DESUtils.encrypt(content, key, iv, Mode.CBC, Padding.NoPadding), key, iv, Mode.CBC, Padding.NoPadding);
        log.info(encrypt);

        encrypt = DESUtils.encrypt(content, key, iv, Mode.CBC, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = DESUtils.decrypt(DESUtils.encrypt(content, key, iv, Mode.CBC, Padding.PKCS7Padding), key, iv, Mode.CBC, Padding.PKCS7Padding);
        log.info(encrypt);

        encrypt = DESUtils.encrypt(content, key, iv, Mode.CFB, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = DESUtils.decrypt(DESUtils.encrypt(content, key, iv, Mode.CFB, Padding.PKCS7Padding), key, iv, Mode.CFB, Padding.PKCS7Padding);
        log.info(encrypt);

        encrypt = DESUtils.encrypt(content, key, iv, Mode.CTR, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = DESUtils.decrypt(DESUtils.encrypt(content, key, iv, Mode.CTR, Padding.PKCS7Padding), key, iv, Mode.CTR, Padding.PKCS7Padding);
        log.info(encrypt);

        encrypt = DESUtils.encrypt(content, key, iv, Mode.OFB, Padding.PKCS7Padding);
        log.info(encrypt);
        encrypt = DESUtils.decrypt(DESUtils.encrypt(content, key, iv, Mode.OFB, Padding.PKCS7Padding), key, iv, Mode.OFB, Padding.PKCS7Padding);
        log.info(encrypt);
    }

}
