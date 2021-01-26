package com.hb0730.commons.encrypt.digest;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class HMacTest {

    @Test
    public void hmacTest() {
        String test = HMac.hmac(HmacAlgorithm.HmacMD5, "test".getBytes(StandardCharsets.UTF_8)).digestHex("test");
        String test1 = HmacUtils.hmacMD5("test", "test");
        Assert.assertEquals("摘要失败，不相等", test1, test);
    }

    @Test
    public void createHmacMd5Test() {
        HMac hmac = HMac.hmac(HmacAlgorithm.HmacMD5);
        String test = hmac.digestHex("test");
        Assert.assertNotNull("摘要失败", test);
    }

    @Test
    public void hmacHas1Test() {
        HMac hmac = HMac.hmac(HmacAlgorithm.HmacSHA1, "test".getBytes(StandardCharsets.UTF_8));
        String test = hmac.digestHex("test");
        String test1 = HmacUtils.hmacSHA1("test", "test");
        Assert.assertEquals("摘要失败，不相等", test1, test);
    }
}
