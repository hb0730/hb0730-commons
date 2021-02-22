package com.hb0730.commons.mail.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;

public class GlobalMailAccountTest {
    GlobalMailAccount global;

    @Before
    public void before() {
        global = GlobalMailAccount.INSTANCE;
    }

    @Test
    public void hostTest() {
        global.host("smtp.qq.com");
        MailAccount account = global.getAccount();
        String host = account.getHost();
        Assert.assertNotNull(host);
    }

    @Test
    public void portTest() {
        global.port(465);
        MailAccount account = global.getAccount();
        Integer port = account.getPort();
        Assert.assertNotNull(port);
    }

    @Test
    public void authTest() {
        global.auth(false);
        MailAccount account = global.getAccount();
        boolean auth = account.isAuth();
        Assert.assertFalse(auth);
    }

    @Test
    public void userTest() {
        global.user("huangbing0730@gmail.com");
        MailAccount account = global.getAccount();
        String user = account.getUser();
        Assert.assertNotNull(user);
    }

    @Test
    public void passwordTest() {
        global.password("123456");
        MailAccount account = global.getAccount();
        String password = account.getPassword();
        Assert.assertEquals("123456", password);
    }

    @Test
    public void fromTest() {
        global.from("test");
        MailAccount account = global.getAccount();
        String from = account.getFrom();
        Assert.assertNotNull(from);
    }

    @Test
    public void timeoutTest() {
        global.timeout(200);
        MailAccount account = global.getAccount();
        long timeout = account.getTimeout();
        Assert.assertNotEquals(0, timeout);
    }

    @Test
    public void connectionTimeoutTest() {
        global.connectionTimeout(200);
        MailAccount account = global.getAccount();
        long connectionTimeout = account.getConnectionTimeout();
        Assert.assertNotEquals(0, connectionTimeout);
    }

    @Test
    public void sslEnableTest() {
        global.sslEnable(false);
        MailAccount account = global.getAccount();
        boolean sslEnabled = account.isSslEnabled();
        Assert.assertFalse(sslEnabled);
    }

    @Test
    public void sslProtocolsTest() {
        global.sslProtocols("smtp");
        MailAccount account = global.getAccount();
        String sslProtocols = account.getSslProtocols();
        Assert.assertNotNull(sslProtocols);
    }

    @Test
    public void socketFactoryClassTest() {
        global.socketFactoryClass("test");
        MailAccount account = global.getAccount();
        String socketFactoryClass = account.getSocketFactoryClass();
        Assert.assertEquals("test", socketFactoryClass);
    }

    @Test
    public void socketFactoryFallbackTest() {
        global.socketFactoryFallback(false);
        MailAccount account = global.getAccount();
        boolean socketFactoryFallback = account.isSocketFactoryFallback();
        Assert.assertFalse(socketFactoryFallback);
    }

    @Test
    public void socketFactoryPortTest() {
        global.socketFactoryPort(465);
        MailAccount account = global.getAccount();
        Integer socketFactoryPort = account.getSocketFactoryPort();
        Assert.assertNotNull(socketFactoryPort);
    }

    @Test
    public void debugTest() {
        global.debug(true);
        MailAccount account = global.getAccount();
        boolean debug = account.isDebug();
        Assert.assertTrue(debug);
    }

    @Test
    public void charsetTest() {
        global.charset(Charset.defaultCharset());
        MailAccount account = global.getAccount();
        Charset charset = account.getCharset();
        Assert.assertEquals(charset, Charset.defaultCharset());
    }
}
