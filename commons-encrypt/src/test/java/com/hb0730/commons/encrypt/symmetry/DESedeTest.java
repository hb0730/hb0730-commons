package com.hb0730.commons.encrypt.symmetry;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Test;

public class DESedeTest {
    private final String content = "12345678";
    private final String key = "asdfghjklqwertyuiopzxcvb";
    private final String iv = "01020304";

    @Test
    public void encryptDecryptTest() {
        DESede des = new DESede();
        final String encryptHex = des.encryptHex(content);
        final String result = des.decryptStr(encryptHex);
        Assert.assertEquals(content, result);
    }

    @Test
    public void encryptDecryptTest3() {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        String decrypt = DESedeUtils.encrypt(
                content,
                key,
                iv,
                com.hb0730.commons.encrypt.constant.Mode.CBC,
                com.hb0730.commons.encrypt.constant.Padding.PKCS5Padding);
        DESede sede = new DESede(
                Mode.CBC,
                Padding.PKCS5Padding,
                key.getBytes(),
                iv.getBytes(),
                provider);
        String encryptBase64 = sede.encryptBase64(content);
        Assert.assertEquals(encryptBase64, decrypt);
    }

    @Test
    public void encryptDecryptWithCustomTest() {
        String content = "我是一个测试的test字符串123";
        final DESede des = new DESede(
                Mode.CTS,
                Padding.PKCS5Padding,
                key.getBytes(),
                iv.getBytes()
        );

        final String encryptHex = des.encryptHex(content);
        final String result = des.decryptStr(encryptHex);

        Assert.assertEquals(content, result);
    }
}
