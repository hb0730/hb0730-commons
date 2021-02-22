package com.hb0730.commons.mail.java;

import org.junit.Test;

public class MailTest {

    @Test
    public void sendTest() {
        MailAccount account = new MailAccount().
                user("1278032416@qq.com").
                password("npzmbnxrdhlyhaja").
                host("smtp.qq.com").
                port(465).
                sslEnable(true).
                auth(true).
                from("hb0730");
        String send = Mail.create(account).
                title("测试").
                tos("1278032416@qq.com").
                setContent("测试").
                send();
    }

}
