package com.hb0730.commons.encrypt.digest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class MD5UtilsTest {

    @Test
    public void md5Test() {
        String str = "测试";
        String md5 = MD5Utils.md5(str);
        Assert.assertNotNull(md5);
        log.info("测试md5 16进制加密: {}", md5);
        str = null;
        md5 = MD5Utils.md5(str);
        Assert.assertNotNull(md5);
        log.info("测试md5 16进制加密: {}", md5);
    }

    @Test
    public void testMd5Test() {
        String str = "测试";
        byte[] md5 = MD5Utils.md5(str.getBytes());
        Assert.assertNotNull(md5);
        log.info("测试md5 加密: {}", md5);
        str = null;
        md5 = MD5Utils.md5(str.getBytes());
        Assert.assertNotNull(md5);
        log.info("测试md5 加密: {}", md5);
    }

    @Test
    public void testMd51Test() {
        String str = "测试";
        String salt = "113123";
        String md5 = MD5Utils.md5(str, salt);
        Assert.assertNotNull(md5);
        log.info("md5加盐 16进制加密: {}", md5);

        str = null;
        md5 = MD5Utils.md5(str, salt);
        Assert.assertNotNull(md5);
        log.info("md5加盐 16进制加密: {}", md5);
    }

    @Test
    public void testMd52Test() {
        String str = "测试";
        String salt = "113123";
        byte[] md5 = MD5Utils.md5(str.getBytes(), salt.getBytes());
        Assert.assertNotNull(md5);
        log.info("md5加盐 加密: {}", md5);

        str = null;
        md5 = MD5Utils.md5(str.getBytes(), salt.getBytes());
        Assert.assertNotNull(md5);
        log.info("md5加盐 加密: {}", md5);
    }

    @Test
    public void testMd53Test() {
        String str = "测试";
        String salt = "113123";
        String md5 = MD5Utils.md5(str, salt, 2);
        Assert.assertNotNull(md5);
        log.info("md5加盐 16进制加密: {}", md5);

        str = null;
        md5 = MD5Utils.md5(str, salt, 2);
        Assert.assertNotNull(md5);
        log.info("md5加盐 16进制加密: {}", md5);
    }

    @Test
    public void testMd54Test() {
        String str = "测试";
        String salt = "113123";
        byte[] md5 = MD5Utils.md5(str.getBytes(), salt.getBytes(), 2);
        Assert.assertNotNull(md5);
        log.info("md5加盐 加密: {}", md5);

        str = null;
        md5 = MD5Utils.md5(str.getBytes(), salt.getBytes(), 2);
        Assert.assertNotNull(md5);
        log.info("md5加盐 加密: {}", md5);
    }
}
