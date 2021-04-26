package com.hb0730.commons.encrypt.digest;

import com.hb0730.commons.lang.constants.Charsets;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class MD5Test {
    // testHex16
    private static final String testHex16 = "1e24cf708a14ce81";
    private static final String testHex32 = "098f6bcd4621d373cade4e832627b4f6";

    @Test
    public void createTest() {
        MD5 md5 = MD5.create();
        Assert.assertNotNull("创建失败", md5);
    }

    @Test
    public void digestTest() {
        byte[] tests = MD5.create().digest("test");
        Assert.assertNotNull("加密失败", tests);
    }

    @Test
    public void digestTest2() {
        byte[] tests = MD5.create().digest("test", "utf-8");
        Assert.assertNotNull("加密失败", tests);
    }

    @Test
    public void digestTest3() {
        byte[] tests = MD5.create().digest("test", Charsets.UTF_8);
        Assert.assertNotNull("加密失败", tests);
    }

    @Test
    public void digestHex() {
        String test = MD5.create().digestHex("test");
        String test1 = MD5Utils.md5("test");
        Assert.assertEquals("摘要失败,不相等", test, test1);
    }

    @Test
    public void digestHex2() {
        String test = MD5.create().digestHex("test", "utf-8");
        String test1 = MD5Utils.md5("test");
        Assert.assertEquals("摘要失败,不相等", test, test1);
    }

    @Test
    public void digestHex3() {
        String test = MD5.create().digestHex("test", Charsets.UTF_8);
        String test1 = MD5Utils.md5("test");
        Assert.assertEquals("摘要失败,不相等", test, test1);
    }

    @Test
    public void digestHexUpper() {
        String test = MD5.create().digestHex("test", Charsets.UTF_8, false);
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void digestSaltHex() {
        Digester digester = MD5.create().setSalt("test".getBytes(StandardCharsets.UTF_8));
        String test = digester.digestHex("test");
        String test2 = MD5Utils.md5("test", "test");
        Assert.assertEquals("摘要失败，不相等", test, test2);
    }

    @Test
    public void digestSaltRest() {
        Digester digester = MD5.create().setSalt("test".getBytes(StandardCharsets.UTF_8)).setDigestCount(3);
        String test = digester.digestHex("test");
        String test1 = MD5Utils.md5("test", "test", 3);
        Assert.assertEquals("摘要失败，不相等", test, test1);
    }

    @Test
    public void digestSaltPosition() {
        Digester digester = MD5.create().setSalt("test".getBytes(StandardCharsets.UTF_8)).setSaltPosition(3);
        String test = digester.digestHex("test");
        Assert.assertNotNull("摘要失败", test);
        digester = MD5.create().setSalt("test".getBytes(StandardCharsets.UTF_8)).setSaltPosition(10);
        test = digester.digestHex("test");
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void digestHex16Test() {
        String test = MD5.create().digestHex16("test");
        String test1 = MD5Utils.md5("test").substring(8, 24);
        Assert.assertEquals("摘要失败，不相等", test, test1);
    }

    @Test
    public void testDigestHex16Test() {
        String test = MD5.create().digestHex16("test", "UTF-8");
        String test1 = MD5Utils.md5("test").substring(8, 24);
        Assert.assertEquals("摘要失败，不相等", test, test1);
    }

    @Test
    public void testDigestHex161Test() {
        String test = MD5.create().digestHex16("test", StandardCharsets.UTF_8);
        String test1 = MD5Utils.md5("test").substring(8, 24);
        Assert.assertEquals("摘要失败，不相等", test, test1);
    }

    @Test
    public void testDigestHex162Test() {
        String test = MD5.create().digestHex16("test", StandardCharsets.UTF_8, true);
        String test1 = MD5Utils.md5("test").substring(8, 24);
        Assert.assertEquals("摘要失败，不相等", test, test1);
        test = MD5.create().digestHex16("test", StandardCharsets.UTF_8, false);
        Assert.assertNotEquals("摘要成功，相等", test, test1);
    }
}
