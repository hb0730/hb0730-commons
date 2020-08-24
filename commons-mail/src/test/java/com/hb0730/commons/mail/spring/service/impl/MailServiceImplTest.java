package com.hb0730.commons.mail.spring.service.impl;

import com.hb0730.commons.mail.spring.properties.MailProperties;
import com.hb0730.commons.mail.spring.service.AbstractMailService;
import org.junit.Before;
import org.junit.Test;

public class MailServiceImplTest {
    private AbstractMailService service;

    @Before
    public void init() {
        MailProperties properties = new MailProperties();
        properties.setEnabled(true);
        properties.setHost("smtp.qq.com");
        properties.setProtocol("smtps");
        properties.setSslPort(465);
        properties.setUsername("xxxx@qq.com");
        properties.setPassword("xxx");
        properties.setFromName("hb0730");
        service = new MailServiceImpl(properties, null);
    }

    @Test
    public void sendTextMailTest() throws InterruptedException {
        service.sendTextMail("xxxx@qq.com", "测试mail", "车擦擦擦错");
        Thread.sleep(1000);
    }

    @Test
    public void testConnectionTest() {
        service.testConnection();
    }
}
