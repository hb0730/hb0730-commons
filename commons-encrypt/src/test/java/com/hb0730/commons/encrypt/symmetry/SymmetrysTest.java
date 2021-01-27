package com.hb0730.commons.encrypt.symmetry;

import org.junit.Assert;
import org.junit.Test;

public class SymmetrysTest {
    private final String content = "12345678";
    private final String key = "asdfghjklqwertyuiopzxcvb";
    private final String SM4_key = "hello world, hi!";
    private final String iv = "01020304";

    @Test
    public void aesTest() {
        AES aes = Symmetrys.aes();
        String hex = aes.encryptHex(content);
        String result = aes.decryptStr(hex);
        Assert.assertEquals(content, result);
    }

    @Test
    public void testAesTest() {
        AES aes = Symmetrys.aes(key.getBytes());
        String hex = aes.encryptHex(content);
        String result = aes.decryptStr(hex);
        Assert.assertEquals(content, result);
    }

    @Test
    public void desTest() {
        DES des = Symmetrys.des();
        String hex = des.encryptHex(content);
        String result = des.decryptStr(hex);
        Assert.assertEquals(result, content);
    }

    @Test
    public void testDesTest() {
        DES des = Symmetrys.des(key.getBytes());
        String hex = des.encryptHex(content);
        String result = des.decryptStr(hex);
        Assert.assertEquals(result, content);
    }

    @Test
    public void desedeTest() {
        DESede desede = Symmetrys.desede();
        String hex = desede.encryptHex(content);
        String result = desede.decryptStr(hex);
        Assert.assertEquals(result, content);
    }

    @Test
    public void testDesedeTest() {
        DESede desede = Symmetrys.desede(key.getBytes());
        String hex = desede.encryptHex(content);
        String result = desede.decryptStr(hex);
        Assert.assertEquals(result, content);
    }

    @Test
    public void sm4Test() {
        SM4 sm4 = Symmetrys.sm4();
        String hex = sm4.encryptHex(content);
        String result = sm4.decryptStr(hex);
        Assert.assertEquals(result, content);
    }

    @Test
    public void testSm4Test() {
        SM4 sm4 = Symmetrys.sm4(SM4_key.getBytes());
        String hex = sm4.encryptHex(content);
        String result = sm4.decryptStr(hex);
        Assert.assertEquals(result, content);
    }

    @Test
    public void disableBouncyCastleTest() {
        Symmetrys.disableBouncyCastle();
        AES aes = Symmetrys.aes();
        String hex = aes.encryptHex(content);
        String result = aes.decryptStr(hex);
        Assert.assertEquals(content, result);
    }
}
