package com.hb0730.commons.mail.spring.service.impl;

import com.hb0730.commons.mail.spring.properties.MailProperties;
import com.hb0730.commons.mail.spring.service.AbstractMailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Slf4j
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
      try {
          service.sendTextMail("xxxx@qq.com", "测试mail", "车擦擦擦错");
          Assert.fail("成功");
      }catch (Throwable e){
          log.error(e.getMessage());
      }
    }

    @Test
    public void testConnectionTest() {
        try {
            service.testConnection();
            Assert.fail("成功");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }
}
