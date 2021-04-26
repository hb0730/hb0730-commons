package com.hb0730.commons.lang.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class IpUtilsTest {

    @Test
    public void getIpTest() {
        String ip = IpUtils.getIp(IpUtils.getIp());
        Assert.assertNotNull(ip);
        log.info("ip:{}", ip);
        ip = IpUtils.getIp("203.0.113.195,70.41.3.18,150.172.238.178");
        Assert.assertNotNull(ip);
        log.info("ip:{}", ip);
        ip = IpUtils.getIp("unknown,203.0.113.195,70.41.3.18,150.172.238.178");
        Assert.assertNotNull(ip);
        log.info("ip:{}", ip);
        ip = IpUtils.getIp("unknown,,70.41.3.18,150.172.238.178");
        Assert.assertNotNull(ip);
        log.info("ip:{}", ip);
        ip = IpUtils.getIp("unknown");
        Assert.assertNotNull(ip);
        log.info("ip:{}", ip);
        ip = IpUtils.getIp(null);
        Assert.assertNull(ip);
        log.info("ip:{}", ip);
    }

    @Test
    public void testGetIpTest() {
        String ip = IpUtils.getIp();
        Assert.assertNotNull(ip);
        log.info("ip:{}", ip);
    }

    @Test
    public void getIpPortTest() {
        String ipPort = IpUtils.getIpPort(8088);
        Assert.assertNotNull(ipPort);
        log.info(ipPort);
    }

    @Test
    public void isUnknownTest() {
        String unknown = "unknown";
        boolean b = IpUtils.isUnknown(unknown);
        Assert.assertTrue(b);
        b = IpUtils.isUnknown("");
        Assert.assertTrue(b);
        b = IpUtils.isUnknown("127.0.0.1");
        Assert.assertFalse("为unknown", b);
    }

    @Test
    public void getAddressTest() {
        String address = IpUtils.getAddress();
        Assert.assertNotNull(address);
        log.info(address);
    }

    @Test
    public void isValidPortTest() {
        boolean validPort = IpUtils.isValidPort(0);
        Assert.assertTrue(validPort);
        validPort = IpUtils.isValidPort(65534);
        Assert.assertTrue("false", validPort);
        validPort = IpUtils.isValidPort(65543);
        Assert.assertFalse("true", validPort);
        validPort = IpUtils.isValidPort(-1);
        Assert.assertFalse("true", validPort);
    }
}
