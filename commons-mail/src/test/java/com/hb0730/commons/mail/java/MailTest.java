package com.hb0730.commons.mail.java;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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

    @Test
    public void addAttachmentTest() {
        Mail.create()
                .tos("huangbing0730@gmail.com")
                .subject("测试")
                .content("<h2>测试</h2>")
                .html(true)
                .addAttachment("test", new File("d:/haha3.txt"))
                .send();
    }

    @Test
    public void testAddAttachmentTest() throws MalformedURLException {

        Mail.create()
                .tos("huangbing0730@gmail.com")
                .subject("测试")
                .content("<h2>测试</h2>")
                .html(true)
                .addAttachment("test", new URL("https://hb0730-blog-hk.oss-cn-hongkong.aliyuncs.com/wallhaven-o33j29_1600x900_1613641932709.png"))
                .send();
    }

    @Test
    public void ccsTest() throws MalformedURLException {
        Mail.create()
                .tos("huangbing0730@gmail.com")
                .ccs("1278032416@qq.com")
                .subject("测试")
                .content("<h2>测试</h2>", true)
                .addAttachment("test", new URL("https://hb0730-blog-hk.oss-cn-hongkong.aliyuncs.com/wallhaven-o33j29_1600x900_1613641932709.png"))
                .send();
    }

    @Test
    public void bccsTest() throws MalformedURLException {
        Mail.create()
                .tos("huangbing0730@gmail.com")
                .bccs("1278032416@qq.com")
                .subject("测试")
                .content("<h2>测试</h2>", true)
                .addAttachment("test", new URL("https://hb0730-blog-hk.oss-cn-hongkong.aliyuncs.com/wallhaven-o33j29_1600x900_1613641932709.png"))
                .send();
    }

    @Test
    public void charsetTest() throws MalformedURLException {
        Mail.create()
                .tos("huangbing0730@gmail.com")
                .subject("测试")
                .charset(StandardCharsets.UTF_8)
                .content("<h2>测试</h2>", true)
                .addAttachment("test", new URL("https://hb0730-blog-hk.oss-cn-hongkong.aliyuncs.com/wallhaven-o33j29_1600x900_1613641932709.png"))
                .send();
    }
}
