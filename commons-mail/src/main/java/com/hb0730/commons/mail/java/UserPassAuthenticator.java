package com.hb0730.commons.mail.java;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 用户名密码验证器
 *
 * @author bing_huang
 */
public class UserPassAuthenticator extends Authenticator {
    private final String username;
    private final String password;

    /**
     * 构造
     *
     * @param username 用户名
     * @param password 密码
     */
    public UserPassAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
