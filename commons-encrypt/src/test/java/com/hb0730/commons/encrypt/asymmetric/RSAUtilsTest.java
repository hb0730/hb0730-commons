package com.hb0730.commons.encrypt.asymmetric;

import com.hb0730.commons.encrypt.constant.Mode;
import com.hb0730.commons.encrypt.constant.Padding;
import com.hb0730.commons.encrypt.constant.RSASignType;
import com.hb0730.commons.encrypt.pojo.RSAKeyPair;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Clock;

@Slf4j
public class RSAUtilsTest {
    private String content = "hello world";

    private String modules = "AMC4hLGE4ksIH2vfLCXV8Z199pmKB1QqmY6dQRx41IYLj1btbblZO9x5gE6pdiyubIZxLKpMsrjc28DjbVcqymOpkdkjIuMm80ERNpDRBA92QAnA1axh0LXDtEPjkWUdYqi2MZeeTAFyQQr6OECRGOIou51oEgKFuxd7kFkgAnin";

    private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAuISxhOJLCB9r3ywl1fGdffaZigdUKpmOnUEceNSGC49W7W25WTvceYBOqXYsrmyGcSyqTLK43NvA421XKspjqZHZIyLjJvNBETaQ0QQPdkAJwNWsYdC1w7RD45FlHWKotjGXnkwBckEK+jhAkRjiKLudaBIChbsXe5BZIAJ4pwIDAQAB";

    private String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMC4hLGE4ksIH2vfLCXV8Z199pmKB1QqmY6dQRx41IYLj1btbblZO9x5gE6pdiyubIZxLKpMsrjc28DjbVcqymOpkdkjIuMm80ERNpDRBA92QAnA1axh0LXDtEPjkWUdYqi2MZeeTAFyQQr6OECRGOIou51oEgKFuxd7kFkgAninAgMBAAECgYBvy1nmJGClB9w6Viak+BuFsalYXdJsh522NhCsNIeMDq6izW5GA7aO6ch9WR5dQv1fa81uKLnQNQYhOYyW8RKfhbcWMXY87PGqmgovp95fIDPZg4JPdjOtYa0jWCkPwTJtCxd7tRUpgEQwL+OrBThUeIcbitGFnFN0Dh9Fd8eXmQJBAOdmqQC1rO41QcJaUXb5BYs0JBTsn8XzRubYJrA2/nG1J890ZflMgbiy0+J551DxxpQdzBjw9zJ7Nv81bzxuacMCQQDVNTce2Rk+qEAFqkA2mlCBSP0Fo8rBWeyWcojCD3lsLumixV0IlCvU9tzTQvA3kk4/L+2CyKqkUwuowXxv3SNNAkEAw8kOkTUl/d49p013w+vqjt8s8C9M99VgVgzonwvIuTiHWHWpmgbrcvSLZgGyf8AxPjz/5NJstN+fpsr1NPJOtQJAFLn2oM4UES5EAwj48xXvS5Iv3rN8i21VfY6m0s60TBsHZWZwU9wroqlY8ESqm4xihOwA19zwEcds87vTgABsZQJAQelvWjab1VsavHm6uoRhVSB8rcsEwvUz+lqY9qGREt0m4G+4USMdCsn+pfG8hSV5kQGcHFBa0cCM40S1Hh9mZA==";

    @Test
    public void generateKey() {
        RSAKeyPair rsaKeyPair = RSAUtils.generateKey();
        log.info(rsaKeyPair.getModules().toString());
        log.info(rsaKeyPair.getPublicKey());
        log.info(rsaKeyPair.getPrivateKey());
    }

    @Test
    public void testTime() {
        log.info("" + Clock.systemUTC().millis());
        test1();
        log.info("" + Clock.systemUTC().millis());
        test2();
        log.info("" + Clock.systemUTC().millis());
    }

    @Test
    public void testSign() {
        String sign = RSAUtils.sign(RSASignType.SHA256withRSA, content, privateKey);
        log.info(sign);
        log.info(RSAUtils.verifySign(RSASignType.SHA256withRSA, content, publicKey, sign) + "");
    }

    @Test
    public void test1() {
        //私钥加密，公钥解密
        String encrypt = RSAUtils.encryptByPrivateKey(content, privateKey);
        log.info(encrypt);
        encrypt = RSAUtils.decryptByPublicKey(encrypt, publicKey);
        log.info(encrypt);
    }

    @Test
    public void test2() {
        //公钥加密，私钥解密
        String encrypt1 = RSAUtils.encryptByPublicKey(content, publicKey);
        log.info(encrypt1);
        encrypt1 = RSAUtils.decryptByPrivateKey(encrypt1, privateKey);
        log.info(encrypt1);
    }

    @Test
    public void test21() {
        //公钥加密，私钥解密
        String encrypt = RSAUtils.encryptByPublicKey(content, publicKey);
        log.info(encrypt);
        encrypt = RSAUtils.decryptByPrivateKey(RSAUtils.encryptByPublicKey(content, publicKey), privateKey);
        log.info(encrypt);

        encrypt = RSAUtils.encryptByPublicKey(content, publicKey, Mode.NONE, Padding.PKCS1Padding);
        log.info(encrypt);
        encrypt = RSAUtils.decryptByPrivateKey(RSAUtils.encryptByPublicKey(content, publicKey, Mode.NONE, Padding.PKCS1Padding), privateKey);
        log.info(encrypt);

        encrypt = RSAUtils.encryptByPublicKey(content, publicKey, Mode.ECB, Padding.NoPadding);
        log.info(encrypt);
        encrypt = RSAUtils.decryptByPrivateKey(RSAUtils.encryptByPublicKey(content, publicKey, Mode.ECB, Padding.PKCS1Padding), privateKey);
        log.info(encrypt);

        encrypt = RSAUtils.encryptByPublicKey(content, publicKey, Mode.ECB, Padding.PKCS1Padding);
        log.info(encrypt);
        encrypt = RSAUtils.decryptByPrivateKey(RSAUtils.encryptByPublicKey(content, publicKey, Mode.ECB, Padding.PKCS1Padding), privateKey);
        log.info(encrypt);

        //私钥加密，公钥解密
        encrypt = RSAUtils.encryptByPrivateKey(content, privateKey);
        log.info(encrypt);
        encrypt = RSAUtils.decryptByPublicKey(RSAUtils.encryptByPrivateKey(content, privateKey), publicKey);
        log.info(encrypt);

        encrypt = RSAUtils.encryptByPrivateKey(content, privateKey, Mode.NONE, Padding.PKCS1Padding);
        log.info(encrypt);
        encrypt = RSAUtils.decryptByPublicKey(RSAUtils.encryptByPrivateKey(content, privateKey, Mode.NONE, Padding.PKCS1Padding), publicKey);
        log.info(encrypt);

        encrypt = RSAUtils.encryptByPrivateKey(content, privateKey, Mode.ECB, Padding.NoPadding);
        log.info(encrypt);
        encrypt = RSAUtils.decryptByPublicKey(RSAUtils.encryptByPrivateKey(content, privateKey, Mode.ECB, Padding.PKCS1Padding), publicKey);
        log.info(encrypt);

        encrypt = RSAUtils.encryptByPrivateKey(content, privateKey, Mode.ECB, Padding.PKCS1Padding);
        log.info(encrypt);
        encrypt = RSAUtils.decryptByPublicKey(RSAUtils.encryptByPrivateKey(content, privateKey, Mode.ECB, Padding.PKCS1Padding), publicKey);
        log.info(encrypt);
    }

    @Test
    public void testProperty() {
        RSAKeyPair rsaKeyPair = RSAUtils.generateKey();

        int count = 10000;

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            RSAUtils.encryptByPublicKey(content, rsaKeyPair.getPublicKey());
        }
        log.info("加密" + count + "次，耗时" + (System.currentTimeMillis() - startTime) + "毫秒");

        //加密
        String encryptedData = RSAUtils.encryptByPublicKey(content, rsaKeyPair.getPublicKey());
        log.info(encryptedData);

        //解密
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            RSAUtils.decryptByPrivateKey(encryptedData, rsaKeyPair.getPrivateKey());
        }
        log.info("解密" + count + "次，耗时" + (System.currentTimeMillis() - startTime) + "毫秒");
    }
}
