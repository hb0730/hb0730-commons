package com.hb0730.commons.encrypt.symmetry;

import com.hb0730.commons.encrypt.constant.GlobalBouncyCastleProvider;
import com.hb0730.commons.encrypt.utils.KeyUtils;
import com.hb0730.commons.lang.constants.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class SymmetricCryptoTest {
    private final static String KEY = "1234567890123456";
    private final static String CONTENT = "我是一个测试的test字符串123";

    @Test
    public void symmetricCryptoTest() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES);
        Assert.assertNotNull(crypto);
    }

    @Test
    public void symmetricCryptoTest2() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, GlobalBouncyCastleProvider.INSTANCE.getProvider());
        Assert.assertNotNull(crypto);
    }

    @Test
    public void symmetricCryptoTest3() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES.getValue());
        Assert.assertNotNull(crypto);
    }

    @Test
    public void symmetricCryptoTest4() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES.getValue(), GlobalBouncyCastleProvider.INSTANCE.getProvider());
        Assert.assertNotNull(crypto);
    }

    @Test
    public void symmetricCryptoTest5() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, KEY.getBytes());
        Assert.assertNotNull(crypto);
    }

    @Test
    public void symmetricCryptoTest6() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, KEY.getBytes(), GlobalBouncyCastleProvider.INSTANCE.getProvider());
        Assert.assertNotNull(crypto);
    }

    @Test
    public void symmetricCryptoTest7() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, KeyUtils.generateKey(SymmetricAlgorithm.AES.getValue(), KEY.getBytes(), null));
        Assert.assertNotNull(crypto);
    }

    @Test
    public void symmetricCryptoTest8() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES.getValue(), KEY.getBytes());
        Assert.assertNotNull(crypto);
    }

    @Test
    public void symmetricCryptoTest9() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES.getValue(), KeyUtils.generateKey(SymmetricAlgorithm.AES.getValue(), KEY.getBytes(), null));
        Assert.assertNotNull(crypto);
    }

    @Test
    public void encryptHexTest() {
        try {
            SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, GlobalBouncyCastleProvider.INSTANCE.getProvider());
            String hex = crypto.encryptHex(CONTENT.getBytes());
            String str = crypto.decryptStr(hex);
            Assert.assertEquals(CONTENT, str);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }


    }

    @Test
    public void encryptBase64Test() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, GlobalBouncyCastleProvider.INSTANCE.getProvider());
        String base64 = crypto.encryptBase64(CONTENT.getBytes());
        String s = crypto.decryptStr(base64);
        try {

            Assert.assertEquals(s, CONTENT);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void encryptTest() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, GlobalBouncyCastleProvider.INSTANCE.getProvider());
        byte[] encrypt = crypto.encrypt(CONTENT, Charsets.UTF_8_NAME);
        Assert.assertNotNull(encrypt);
    }

    @Test
    public void encryptTest2() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, GlobalBouncyCastleProvider.INSTANCE.getProvider());
        byte[] encrypt = crypto.encrypt(CONTENT, Charsets.UTF_8);
        Assert.assertNotNull(encrypt);
    }

    @Test
    public void encryptHexTest2() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, GlobalBouncyCastleProvider.INSTANCE.getProvider());
        String s = crypto.encryptHex(CONTENT, Charsets.UTF_8_NAME);
        Assert.assertNotNull(s);
    }

    @Test
    public void encryptHexTest3() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, GlobalBouncyCastleProvider.INSTANCE.getProvider());
        String hex = crypto.encryptHex(CONTENT, Charsets.UTF_8);
        Assert.assertNotNull(hex);
    }

    @Test
    public void encryptBase64Test2() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, GlobalBouncyCastleProvider.INSTANCE.getProvider());
        String base64 = crypto.encryptBase64(CONTENT, Charsets.UTF_8_NAME);
        Assert.assertNotNull(base64);
    }

    @Test
    public void encryptBase64Test3() {
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.AES, GlobalBouncyCastleProvider.INSTANCE.getProvider());
        String base64 = crypto.encryptBase64(CONTENT, Charsets.UTF_8);
        Assert.assertNotNull(base64);
    }

}
