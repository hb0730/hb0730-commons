package com.hb0730.commons.lang.codec;

import com.hb0730.commons.lang.constants.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class Base64UtilsTest {

    @Test
    public void encodeTest() {
        String test = "测试";
        byte[] bytes = Base64Utils.encode(test.getBytes());
        Assert.assertNotNull(bytes);
        bytes = Base64Utils.encode(null);
        Assert.assertNotNull(bytes);
    }

    @Test
    public void decodeTest() {
        String test = "测试";
        byte[] encode = Base64Utils.encode(test.getBytes());
        byte[] decode = Base64Utils.decode(encode);
        Assert.assertNotNull(decode);
        decode = Base64Utils.decode(null);
        Assert.assertNotNull(decode);
    }

    @Test
    public void encodeUrlSafeTest() {
        String url = "https://www.baidu.com/s?wd=测试";
        byte[] encodeUrlSafe = Base64Utils.encodeUrlSafe(url.getBytes());
        Assert.assertNotNull(encodeUrlSafe);
        encodeUrlSafe = Base64Utils.encodeUrlSafe(null);
        Assert.assertNotNull(encodeUrlSafe);
    }

    @Test
    public void decodeUrlSafeTest() {
        String url = "https://www.baidu.com/s?wd=测试";
        byte[] encodeUrlSafe = Base64Utils.encodeUrlSafe(url.getBytes());
        byte[] decodeUrlSafe = Base64Utils.decodeUrlSafe(encodeUrlSafe);
        Assert.assertNotNull(decodeUrlSafe);
        decodeUrlSafe = Base64Utils.decodeUrlSafe(null);
        Assert.assertNotNull(decodeUrlSafe);
    }

    @Test
    public void encodeToStringTest() {
        String test = "测试";
        String encode = Base64Utils.encodeToString(test.getBytes());
        Assert.assertNotNull(encode);
        String encode1 = Base64Utils.encodeToString(test.getBytes(), "utf-8");
        Assert.assertEquals(encode, encode1);
        String encode2 = Base64Utils.encodeToString(test.getBytes(), Charsets.UTF_8);
        Assert.assertEquals(encode, encode2);
        log.info(encode2);
        String encode3 = Base64Utils.encodeToString(null);
        Assert.assertEquals("编码失败" + encode3, encode, encode3);
    }

    @Test
    public void decodeFromStringTest() {
        String test = "测试";
        String encode = Base64Utils.encodeToString(test.getBytes());
        byte[] decode = Base64Utils.decodeFromString(encode);
        Assert.assertNotNull(decode);
        byte[] decode1 = Base64Utils.decodeFromString(encode, "utf-8");
        Assert.assertEquals(decode, decode1);
        byte[] decode2 = Base64Utils.decodeFromString(encode, Charsets.UTF_8);
        Assert.assertEquals(decode, decode2);
        byte[] decode3 = Base64Utils.decodeFromString(null);
        Assert.assertEquals("编码失败" + decode3, decode, decode3);
    }

    @Test
    public void encodeToUrlSaleStringTest() {
        String url = "https://www.baidu.com/s?wd=测试";
        String encode = Base64Utils.encodeToUrlSafeString(url.getBytes());
        Assert.assertNotNull(encode);
        String encode1 = Base64Utils.encodeToUrlSafeString(url.getBytes(), "utf-8");
        Assert.assertEquals(encode, encode1);
        String encode2 = Base64Utils.encodeToUrlSafeString(url.getBytes(), Charsets.UTF_8);
        Assert.assertEquals(encode, encode2);
        String encode4 = Base64Utils.encodeToUrlSafeString("".getBytes(), Charsets.UTF_8);
        Assert.assertEquals("解码失败" + encode4, encode, encode4);
        String encode3 = Base64Utils.encodeToUrlSafeString(null);
        Assert.assertEquals("解码失败" + encode3, encode, encode3);
    }

    @Test
    public void decodeFromUrlSafeStringTest() {
        String url = "https://www.baidu.com/s?wd=测试";
        String encode = Base64Utils.encodeToUrlSafeString(url.getBytes());
        byte[] decode = Base64Utils.decodeFromUrlSafeString(encode);
        Assert.assertNotNull(decode);
        byte[] decode1 = Base64Utils.decodeFromUrlSafeString(encode, "utf-8");
        Assert.assertNotNull(decode1);
        byte[] decode2 = Base64Utils.decodeFromUrlSafeString(encode, Charsets.UTF_8);
        Assert.assertNotNull(decode2);
        byte[] decode3 = Base64Utils.decodeFromUrlSafeString("");
        Assert.assertNotNull(decode3);
        byte[] decode4 = Base64Utils.decodeFromUrlSafeString(null);
        Assert.assertNotNull(decode4);
    }

}
