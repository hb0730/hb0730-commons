package com.hb0730.commons.encrypt.symmetry;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Test;

public class DESTest {
    @Test
    public void encryptDecryptTest() {
        String content = "我是一个测试的test字符串123";
        DES des = new DES();
        final String encryptHex = des.encryptHex(content);
        final String result = des.decryptStr(encryptHex);
        Assert.assertEquals(content, result);
    }

    @Test
    public void encryptDecryptTest2() {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        String content = "我是一个测试的test字符串123";
        DES des = new DES("12345678".getBytes(), provider);
        final String encryptHex = des.encryptBase64(content);
        String result = DESUtils.encrypt(content, "12345678");
        Assert.assertEquals(encryptHex, result);
    }

    @Test
    public void encryptDecryptWithCustomTest() {
        String content = "我是一个测试的test字符串123";
        final DES des = new DES(
                Mode.CTS,
                Padding.PKCS5Padding,
                "12345678".getBytes(),
                "11223344".getBytes()
        );

        final String encryptHex = des.encryptHex(content);
        final String result = des.decryptStr(encryptHex);

        Assert.assertEquals(content, result);
    }

}
