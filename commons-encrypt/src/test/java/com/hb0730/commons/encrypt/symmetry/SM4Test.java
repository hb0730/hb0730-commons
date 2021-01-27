package com.hb0730.commons.encrypt.symmetry;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Test;

public class SM4Test {
    private final String content = "0123456789ABCDEF";
    private final String key = "hello world, hi!";
    private final String iv = "01020304";

    @Test
    public void encryptDecryptTest() {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        SM4 sm4 = new SM4(provider);
        String s = sm4.encryptHex(content);
        String result = sm4.decryptStr(s);
        Assert.assertEquals(content, result);
    }

    @Test
    public void encryptDecryptTest2() {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        SM4 sm4 = new SM4(key.getBytes(), provider);
        String base64 = sm4.encryptBase64(content);
        String encrypt = SM4Utils.encrypt(content, key);
        Assert.assertEquals(base64, encrypt);
    }

    @Test
    public void encryptDecryptWithCustomTest() {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        final DES des = new DES(
                Mode.CTS,
                Padding.PKCS5Padding,
                key.getBytes(),
                iv.getBytes(),
                provider
        );

        final String encryptHex = des.encryptHex(content);
        final String result = des.decryptStr(encryptHex);
        Assert.assertEquals(content, result);
    }
}
