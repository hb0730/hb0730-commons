package com.hb0730.commons.mail.java;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class MailTest {
    @Before
    public void before() {
        GlobalMailAccount.INSTANCE
                .user("1278032416@qq.com")
                .password("")
                .host("smtp.qq.com")
                .port(465)
                .debug(true)
                .auth(true)
                .sslEnable(true)
                .from("hb0730");
    }

    @Test
    public void sendWithFileTest() {
        Mail.create()
                .tos("huangbing0730@gmail.com")
                .subject("测试")
                .content("<h2>测试</h2>")
                .html(true)
                .addAttachments(new File("d:/haha3.txt"))
                .send();
    }

    @Test
    public void sendByAccountTest() {
        MailAccount account = new MailAccount()
                .host("smtp.qq.com")
                .port(465)
                .user("1278032416@qq.com")
                .password("")
                .sslEnable(true)
                .from("hb0730");
        Mail.create(account)
                .tos("huangbing0730@gmail.com")
                .subject("测试")
                .content("<h2>测试</h2>")
                .html(true)
                .send();
    }
}
