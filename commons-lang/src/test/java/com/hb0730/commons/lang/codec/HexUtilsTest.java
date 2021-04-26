package com.hb0730.commons.lang.codec;

import com.hb0730.commons.lang.constants.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class HexUtilsTest {

    @Test
    public void encodeHexStringTest() {
        String mess = "test";
        String encodeHexString = HexUtils.encodeHexString(mess);
        Assert.assertNotNull("转换错误", encodeHexString);
        log.debug(encodeHexString);
    }

    @Test
    public void testEncodeHexStringTest() {
        String msg = "测试";
        String hexString = HexUtils.encodeHexString(msg, Charsets.UTF_8_NAME);
        Assert.assertNotNull("转换错误", hexString);
    }

    @Test
    public void testEncodeHexString1Test() {
        String msg = "测试";
        String hexString = HexUtils.encodeHexString(msg, Charsets.UTF_8);
        Assert.assertNotNull("转换错误", hexString);
    }

    @Test
    public void testEncodeHexString2Test() {
        String msg = "测试";
        String hexString = HexUtils.encodeHexString(msg, Charsets.UTF_8, false);
        Assert.assertNotNull("转换错误", hexString);
    }

    @Test
    public void testEncodeHexString3Test() {
        String msg = "测试";
        String hexString = HexUtils.encodeHexString(msg.getBytes(Charsets.UTF_8), false);
        Assert.assertNotNull("转换错误", hexString);
    }

    @Test
    public void encodeHexTest() {
        String msg = "测试";
        char[] hex = HexUtils.encodeHex(msg);
        Assert.assertNotNull("转换错误", hex);
    }

    @Test
    public void testEncodeHexTest() {
        String msg = "测试";
        char[] hex = HexUtils.encodeHex(msg, Charsets.UTF_8);
        Assert.assertNotNull("转换错误", hex);
    }

    @Test
    public void testEncodeHex1Test() {
        String msg = "测试";
        char[] hex = HexUtils.encodeHex(msg, Charsets.UTF_8, false);
        Assert.assertNotNull("转换错误", hex);
    }

    @Test
    public void testEncodeHex2Test() {
        String msg = "测试";
        char[] hex = HexUtils.encodeHex(msg.getBytes(Charsets.UTF_8));
        Assert.assertNotNull("转换错误", hex);
    }

    @Test
    public void testEncodeHex3Test() {
        String msg = "测试";
        char[] hex = HexUtils.encodeHex(msg.getBytes(Charsets.UTF_8), false);
        Assert.assertNotNull("转换错误", hex);
    }

    @Test
    public void decodeHexStrTest() {
        String msg = "测试";
        String hexString = HexUtils.encodeHexString(msg);
        String hexStr = HexUtils.decodeHexStr(hexString);
        Assert.assertNotNull("逆转出差", hexStr);
    }

    @Test
    public void testDecodeHexStrTest() {
        String msg = "测试";
        String hexString = HexUtils.encodeHexString(msg);
        String hexStr = HexUtils.decodeHexStr(hexString, Charsets.UTF_8);
        Assert.assertNotNull("逆转出差", hexStr);
    }

    @Test
    public void testDecodeHexStr1Test() {
        String msg = "测试";
        char[] chars = HexUtils.encodeHex(msg);
        String hexStr = HexUtils.decodeHexStr(chars);
        Assert.assertNotNull("逆转出差", hexStr);
    }

    @Test
    public void testDecodeHexStr2Test() {
        String msg = "测试";
        char[] chars = HexUtils.encodeHex(msg);
        String hexStr = HexUtils.decodeHexStr(chars, Charsets.UTF_8);
        Assert.assertNotNull("逆转出差", hexStr);
    }

    @Test
    public void decodeHexTest() {
        String msg = "测试";
        try {
            byte[] bytes = HexUtils.decodeHex(msg);
            Assert.assertNotNull("逆转出差", bytes);
        } catch (Exception e) {
            log.error("解码失败");
        }
    }

    @Test
    public void testDecodeHexTest() {
        String msg = "测试";
        try {
            byte[] bytes = HexUtils.decodeHex(msg.toCharArray());
            Assert.assertNotNull("逆转出差", bytes);
        } catch (Exception e) {
            log.error("解码失败");
        }
    }
}
