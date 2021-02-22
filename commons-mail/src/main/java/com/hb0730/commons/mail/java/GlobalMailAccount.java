package com.hb0730.commons.mail.java;

import java.nio.charset.Charset;

/**
 * 全局邮件帐户
 *
 * @author bing_huang
 * @since 2.1.0
 */
public enum GlobalMailAccount implements Account {
    /**
     * 实例
     */
    INSTANCE;
    private final MailAccount account = new MailAccount();

    public MailAccount getAccount() {
        return account;
    }

    @Override
    public GlobalMailAccount host(String host) {
        account.host(host);
        return this;
    }

    @Override
    public GlobalMailAccount port(Integer port) {
        account.port(port);
        return this;
    }

    @Override
    public GlobalMailAccount auth(boolean auth) {
        account.auth(auth);
        return this;
    }

    @Override
    public GlobalMailAccount user(String user) {
        account.user(user);
        return this;
    }

    @Override
    public GlobalMailAccount password(String password) {
        account.password(password);
        return this;
    }

    @Override
    public GlobalMailAccount from(String from) {
        account.from(from);
        return this;
    }

    @Override
    public GlobalMailAccount timeout(long timeout) {
        account.timeout(timeout);
        return this;
    }

    @Override
    public GlobalMailAccount connectionTimeout(long connectionTimeout) {
        account.connectionTimeout(connectionTimeout);
        return this;
    }

    @Override
    public GlobalMailAccount sslEnable(boolean sslEnable) {
        account.sslEnable(sslEnable);
        return this;
    }

    @Override
    public GlobalMailAccount sslProtocols(String sslProtocols) {
        account.sslProtocols(sslProtocols);
        return this;
    }

    @Override
    public GlobalMailAccount socketFactoryClass(String socketFactoryClass) {
        account.socketFactoryClass(socketFactoryClass);
        return this;
    }

    @Override
    public GlobalMailAccount socketFactoryFallback(boolean socketFactoryFallback) {
        account.socketFactoryFallback(socketFactoryFallback);
        return this;
    }

    @Override
    public GlobalMailAccount socketFactoryPort(Integer socketFactoryPort) {
        account.socketFactoryPort(socketFactoryPort);
        return this;
    }

    @Override
    public GlobalMailAccount debug(boolean debug) {
        account.debug(debug);
        return this;
    }

    @Override
    public GlobalMailAccount charset(Charset charset) {
        account.charset(charset);
        return this;
    }
}
