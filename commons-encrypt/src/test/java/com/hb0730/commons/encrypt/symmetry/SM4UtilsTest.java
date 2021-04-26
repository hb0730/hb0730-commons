package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.constant.Algorithm;
import com.hb0730.commons.encrypt.constant.Mode;
import com.hb0730.commons.encrypt.constant.Padding;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Clock;
import java.util.Arrays;

@Slf4j
public class SM4UtilsTest {
    private String content = "0123456789ABCDEF";
    private String key = "hello world, hi!";
    private String iv = "0102030405060708";

    @Test
    public void getAlgorithm() {
        log.info(Algorithm.getAlgorithm(Algorithm.SM4, Mode.CBC, Padding.PKCS7Padding));
        log.info(Algorithm.getAlgorithm(Algorithm.SM4, Mode.ECB, Padding.NoPadding));
    }

    @Test
    public void generateKey() {
        log.info(SM4Utils.encrypt(content, key, Mode.ECB, Padding.NoPadding));
        log.info(Arrays.toString(SM4Utils.generateKey()));
    }

    @Test
    public void testTime() {
        long millis = Clock.systemUTC().millis();
        for (int i = 0; i < 100; i++) {
            test();
        }
        log.info("" + (Clock.systemUTC().millis() - millis));
    }

    @Test
    public void test() {
        log.info(SM4Utils.encrypt(content, key));
        log.info(SM4Utils.decrypt(SM4Utils.encrypt(content, key), key));

        log.info(SM4Utils.encrypt(content, key, iv));
        log.info(SM4Utils.decrypt(SM4Utils.encrypt(content, key, iv), key, iv));

        log.info(SM4Utils.encrypt(content, key, Mode.ECB, Padding.NoPadding));
        log.info(SM4Utils.decrypt(SM4Utils.encrypt(content, key, Mode.ECB, Padding.NoPadding), key, Mode.ECB, Padding.NoPadding));

        log.info(SM4Utils.encrypt(content, key, Mode.ECB, Padding.PKCS7Padding));
        log.info(SM4Utils.decrypt(SM4Utils.encrypt(content, key, Mode.ECB, Padding.PKCS7Padding), key, Mode.ECB, Padding.PKCS7Padding));

        log.info(SM4Utils.encrypt(content, key, iv, Mode.CBC, Padding.NoPadding));
        log.info(SM4Utils.decrypt(SM4Utils.encrypt(content, key, iv, Mode.CBC, Padding.NoPadding), key, iv, Mode.CBC, Padding.NoPadding));

        log.info(SM4Utils.encrypt(content, key, iv, Mode.CBC, Padding.PKCS7Padding));
        log.info(SM4Utils.decrypt(SM4Utils.encrypt(content, key, iv, Mode.CBC, Padding.PKCS7Padding), key, iv, Mode.CBC, Padding.PKCS7Padding));

        log.info(SM4Utils.encrypt(content, key, iv, Mode.CFB, Padding.PKCS7Padding));
        log.info(SM4Utils.decrypt(SM4Utils.encrypt(content, key, iv, Mode.CFB, Padding.PKCS7Padding), key, iv, Mode.CFB, Padding.PKCS7Padding));

        log.info(SM4Utils.encrypt(content, key, iv, Mode.CTR, Padding.PKCS7Padding));
        log.info(SM4Utils.decrypt(SM4Utils.encrypt(content, key, iv, Mode.CTR, Padding.PKCS7Padding), key, iv, Mode.CTR, Padding.PKCS7Padding));

        log.info(SM4Utils.encrypt(content, key, iv, Mode.OFB, Padding.PKCS7Padding));
        log.info(SM4Utils.decrypt(SM4Utils.encrypt(content, key, iv, Mode.OFB, Padding.PKCS7Padding), key, iv, Mode.OFB, Padding.PKCS7Padding));

    }
}
