package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.constant.Algorithm;
import com.hb0730.commons.encrypt.constant.Mode;
import com.hb0730.commons.encrypt.constant.Padding;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Clock;
import java.util.Arrays;

@Slf4j
public class DESedeUtilsTest {

    private String content = "12345678";
    private String key = "hello world, hi!";
    private String iv = "01020304";

    @Test
    public void getAlgorithm() {
        log.info(Algorithm.getAlgorithm(Algorithm.DE_SEDE, Mode.CBC, Padding.PKCS7Padding));
        log.info(Algorithm.getAlgorithm(Algorithm.DE_SEDE, Mode.ECB, Padding.NoPadding));
    }

    @Test
    public void generateKey() {
        log.info(DESedeUtils.encrypt(content, key, Mode.ECB, Padding.NoPadding));
        log.info(Arrays.toString(DESedeUtils.generateKey()));
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
        log.info(DESedeUtils.encrypt(content, key));
        log.info(DESedeUtils.decrypt(DESedeUtils.encrypt(content, key), key));

        log.info(DESedeUtils.encrypt(content, key, iv));
        log.info(DESedeUtils.decrypt(DESedeUtils.encrypt(content, key, iv), key, iv));

        log.info(DESedeUtils.encrypt(content, key, Mode.ECB, Padding.NoPadding));
        log.info(DESedeUtils.decrypt(DESedeUtils.encrypt(content, key, Mode.ECB, Padding.NoPadding), key, Mode.ECB, Padding.NoPadding));

        log.info(DESedeUtils.encrypt(content, key, Mode.ECB, Padding.PKCS7Padding));
        log.info(DESedeUtils.decrypt(DESedeUtils.encrypt(content, key, Mode.ECB, Padding.PKCS7Padding), key, Mode.ECB, Padding.PKCS7Padding));

        log.info(DESedeUtils.encrypt(content, key, iv, Mode.CBC, Padding.NoPadding));
        log.info(DESedeUtils.decrypt(DESedeUtils.encrypt(content, key, iv, Mode.CBC, Padding.NoPadding), key, iv, Mode.CBC, Padding.NoPadding));

        log.info(DESedeUtils.encrypt(content, key, iv, Mode.CBC, Padding.PKCS7Padding));
        log.info(DESedeUtils.decrypt(DESedeUtils.encrypt(content, key, iv, Mode.CBC, Padding.PKCS7Padding), key, iv, Mode.CBC, Padding.PKCS7Padding));

        log.info(DESedeUtils.encrypt(content, key, iv, Mode.CFB, Padding.PKCS7Padding));
        log.info(DESedeUtils.decrypt(DESedeUtils.encrypt(content, key, iv, Mode.CFB, Padding.PKCS7Padding), key, iv, Mode.CFB, Padding.PKCS7Padding));

        log.info(DESedeUtils.encrypt(content, key, iv, Mode.CTR, Padding.PKCS7Padding));
        log.info(DESedeUtils.decrypt(DESedeUtils.encrypt(content, key, iv, Mode.CTR, Padding.PKCS7Padding), key, iv, Mode.CTR, Padding.PKCS7Padding));

        log.info(DESedeUtils.encrypt(content, key, iv, Mode.OFB, Padding.PKCS7Padding));
        log.info(DESedeUtils.decrypt(DESedeUtils.encrypt(content, key, iv, Mode.OFB, Padding.PKCS7Padding), key, iv, Mode.OFB, Padding.PKCS7Padding));
    }

}
