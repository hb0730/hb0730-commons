package com.hb0730.commons.encrypt.digest;

import com.hb0730.commons.lang.constants.Charsets;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class DigestersTest {

    @Test
    public void md5Test() {
        byte[] bytes = Digesters.md5("test".getBytes(StandardCharsets.UTF_8));
        Assert.assertNotNull("摘要失败", bytes);
    }

    @Test
    public void testMd5Test() {
        byte[] bytes = Digesters.md5("test", Charsets.UTF_8_NAME);
        Assert.assertNotNull("摘要失败", bytes);
    }

    @Test
    public void testMd51Test() {
        byte[] bytes = Digesters.md5("test");
        Assert.assertNotNull("摘要失败", bytes);
    }

    @Test
    public void md5HexTest() {
        String test = Digesters.md5Hex("test");
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void testMd5HexTest() {
        String test = Digesters.md5Hex("test", Charsets.UTF_8_NAME);
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void testMd5Hex1Test() {
        String test = Digesters.md5Hex("test", Charsets.UTF_8);
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void md5Hex16Test() {
        String test = Digesters.md5Hex16("test");
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void testMd5Hex16Test() {
        String test = Digesters.md5Hex16("test", Charsets.UTF_8);
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void md5HexTo16Test() {
        String test = Digesters.md5Hex("test");
        String s = Digesters.md5HexTo16(test);
        Assert.assertNotNull("摘要失败", s);
    }

    @Test
    public void sha1Test() {
        byte[] bytes = Digesters.sha1("test".getBytes());
        Assert.assertNotNull("摘要失败", bytes);
    }

    @Test
    public void testSha1Test() {
        byte[] bytes = Digesters.sha1("test", Charsets.UTF_8_NAME);
        Assert.assertNotNull("摘要失败", bytes);
    }

    @Test
    public void testSha11Test() {
        byte[] bytes = Digesters.sha1("test");
        Assert.assertNotNull("摘要失败", bytes);
    }

    @Test
    public void sha1HexTest() {
        String test = Digesters.sha1Hex("test", Charsets.UTF_8_NAME);
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void testSha1HexTest() {
        String test = Digesters.sha1Hex("test");
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void sha256Test() {
        byte[] bytes = Digesters.sha256("test", Charsets.UTF_8_NAME);
        Assert.assertNotNull("摘要失败", bytes);
    }

    @Test
    public void testSha256Test() {
        byte[] bytes = Digesters.sha256("test");
        Assert.assertNotNull("摘要失败", bytes);
    }

    @Test
    public void sha256HexTest() {
        String test = Digesters.sha256Hex("test", Charsets.UTF_8_NAME);
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void testSha256HexTest() {
        String test = Digesters.sha256Hex("test");
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void hmacTest() {
        HMac hmac = Digesters.hmac(HmacAlgorithm.HmacMD5, "test".getBytes(StandardCharsets.UTF_8));
        Assert.assertNotNull("创建失败", hmac);
    }

    @Test
    public void testHmacTest() {
        HMac hmac = Digesters.hmac(HmacAlgorithm.HmacMD5, new SecretKeySpec("test".getBytes(StandardCharsets.UTF_8), HmacAlgorithm.HmacMD5.getValue()));
        Assert.assertNotNull("创建失败", hmac);
    }

    @Test
    public void digesterTest() {
        Digester digester = Digesters.digester(DigestAlgorithm.SHA256.getValue());
        Assert.assertNotNull("创建失败", digester);
    }

    @Test
    public void testDigesterTest() {
        Digester digester = Digesters.digester(DigestAlgorithm.MD2);
        Assert.assertNotNull("创建失败", digester);
    }

    @Test
    public void sm3Test() {
        SM3 sm3 = Digesters.sm3();
        Assert.assertNotNull(sm3);
    }

    @Test
    public void testSm3Test() {
        String test = Digesters.sm3("test");
        Assert.assertNotNull(test);
    }

    @Test
    public void disableBouncyCastleTest() {
        Digesters.disableBouncyCastle();
        String test = Digesters.sha1Hex("test");
        Assert.assertNotNull(test);
    }
}
