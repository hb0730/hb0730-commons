package com.hb0730.commons.mail.java;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.constants.Charsets;

import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 邮箱用户信息，通过使用SMTP
 *
 * @author bing_huang
 * @see <a href="https://eclipse-ee4j.github.io/mail/docs/api/com/sun/mail/smtp/package-summary.html">smtp</a>
 */
public class MailAccount implements Account {
    /**
     * 消息传输协议
     *
     * @see <a href="https://eclipse-ee4j.github.io/mail/docs/api/jakarta/mail/package-summary.html">mail</a>
     */
    private static final String MAIL_PROTOCOL = "mail.transport.protocol";

    /**
     * SMTP服务器域名
     */
    private static final String SMTP_HOST = "mail.smtp.host";
    /**
     * 要连接的SMTP服务器端口
     */
    private static final String SMTP_PORT = "mail.smtp.port";
    /**
     * 用户进行身份验证
     */
    private static final String SMTP_AUTH = "mail.smtp.auth";
    /**
     * Socket 套接字读取超时值 毫秒为单位 默认值为无限超时。
     */
    private static final String SMTP_TIMEOUT = "mail.smtp.timeout";
    /**
     * Socket 套接字连接超时值 以毫秒为单位 默认值为无限超时。
     */
    private static final String SMTP_CONNECTION_TIMEOUT = "mail.smtp.connectiontimeout";

    //SSL
    /**
     * 使用SSL连接并使用SSL端口
     */
    private static final String SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";
    /**
     * 指定将为SSL连接启用的SSL协议
     */
    private static final String SMTP_SSL_PROTOCOLS = "mail.smtp.ssl.protocols";

    /**
     * 指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
     */
    private static final String SMTP_SOCKET_FACTORY = "mail.smtp.socketFactory.class";
    /**
     * 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
     */
    private static final String SMTP_SOCKET_FACTORY_FALLBACK = "mail.smtp.socketFactory.fallback";
    /**
     * 定使用指定的套接字工厂时要连接的端口。如果未设置，将使用默认端口。
     */
    private static final String SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";

    /**
     * 初始调试模式。默认为false。
     */
    private static final String MAIL_DEBUG = "mail.debug";
    /**
     * SMTP服务器域名
     */
    private String host;
    /**
     * SMTP服务器端口
     */
    private Integer port;
    /**
     * 对用户进行身份验证
     */
    private boolean auth = true;
    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String password;
    /**
     * 发送方，遵循RFC-822标准
     */
    private String from;
    /**
     * SMTP超时时长，单位毫秒，缺省值不超时
     */
    private long timeout;
    /**
     * Socket连接超时值，单位毫秒，缺省值不超时
     */
    private long connectionTimeout;

    /**
     * 使用 SSL安全连接
     */
    private boolean sslEnable = true;
    /**
     * SSL协议，多个协议用空格分隔
     */
    private String sslProtocols;
    /**
     * 指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
     */
    private String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
    /**
     * 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
     */
    private boolean socketFactoryFallback = true;
    /**
     * 指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口
     */
    private Integer socketFactoryPort = 465;
    /**
     * 是否打开调试模式，调试模式会显示与邮件服务器通信过程，默认不开启
     */
    private boolean debug;
    /**
     * 编码用于编码邮件正文和发送人、收件人等中文
     */
    private Charset charset = Charsets.UTF_8;

    /**
     * 获取SMTP服务器地址
     *
     * @return SMTP服务器地址
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置SMTP服务器地址
     *
     * @param host SMTP服务器地址
     * @return this
     */
    @Override
    public MailAccount host(String host) {
        this.host = host;
        return this;
    }

    /**
     * 获取SMTP服务器端口
     *
     * @return SMTP服务器端口
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 设置SMTP服务器端口
     *
     * @param port SMTP服务器端口
     * @return this
     */
    @Override
    public MailAccount port(Integer port) {
        this.port = port;
        return this;
    }

    /**
     * 是否需要用户名密码验证
     *
     * @return 是否需要用户名密码验证
     */
    public boolean isAuth() {
        return auth;
    }

    /**
     * 设置是否需要用户名密码验证
     *
     * @param auth 是否需要用户名密码验证
     * @return this
     */
    @Override
    public MailAccount auth(boolean auth) {
        this.auth = auth;
        return this;
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUser() {
        return user;
    }

    /**
     * 设置用户名
     *
     * @param user 用户名
     * @return this
     */
    @Override
    public MailAccount user(String user) {
        this.user = user;
        return this;
    }

    /**
     * 获取密码
     *
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     * @return this
     */
    @Override
    public MailAccount password(String password) {
        this.password = password;
        return this;
    }

    /**
     * 获取发送方，遵循RFC-822标准
     *
     * @return 发送方，遵循RFC-822标准
     */
    public String getFrom() {
        return from;
    }

    /**
     * 设置发送方，遵循RFC-822标准<br>
     * 发件人可以是以下形式：
     *
     * <pre>
     * 1. user@xxx.xx
     * 2.  name &lt;user@xxx.xx&gt;
     * </pre>
     *
     * @param from 发送方，遵循RFC-822标准
     * @return this
     */
    @Override
    public MailAccount from(String from) {
        this.from = from;
        return this;
    }

    /**
     * 获取SMTP超时时长
     *
     * @return SMTP超时时长
     */
    public long getTimeout() {
        return timeout;
    }

    /**
     * 设置SMTP超时时长，单位毫秒，缺省值不超时
     *
     * @param timeout SMTP超时时长，单位毫秒，缺省值不超时
     * @return this
     */
    @Override
    public MailAccount timeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * 获取Socket连接超时值，单位毫秒，缺省值不超时
     *
     * @return Socket连接超时值
     */
    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * 设置Socket连接超时值，单位毫秒，缺省值不超时
     *
     * @param connectionTimeout Socket连接超时值，单位毫秒，缺省值不超时
     * @return this
     */
    @Override
    public MailAccount connectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    /**
     * 是否使用 SSL安全连接
     *
     * @return 是否使用 SSL安全连接
     */
    public boolean isSslEnabled() {
        return sslEnable;
    }

    /**
     * 设置是否使用SSL安全连接
     *
     * @param sslEnable 是否使用SSL安全连接
     * @return this
     */
    @Override
    public MailAccount sslEnable(boolean sslEnable) {
        this.sslEnable = sslEnable;
        return this;
    }

    /**
     * 获取SSL协议，多个协议用空格分隔
     *
     * @return SSL协议，多个协议用空格分隔
     */
    public String getSslProtocols() {
        return sslProtocols;
    }

    /**
     * 设置SSL协议，多个协议用空格分隔
     *
     * @param sslProtocols SSL协议，多个协议用空格分隔
     * @return this
     */
    @Override
    public MailAccount sslProtocols(String sslProtocols) {
        this.sslProtocols = sslProtocols;
        return this;
    }

    /**
     * 获取指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
     *
     * @return 指定实现javax.net.SocketFactory接口的类的名称, 这个类将被用于创建SMTP的套接字
     */
    public String getSocketFactoryClass() {
        return socketFactoryClass;
    }

    /**
     * 设置指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
     *
     * @param socketFactoryClass 指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
     * @return this
     */
    @Override
    public MailAccount socketFactoryClass(String socketFactoryClass) {
        this.socketFactoryClass = socketFactoryClass;
        return this;
    }

    /**
     * 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
     *
     * @return 如果设置为true, 未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
     */
    public boolean isSocketFactoryFallback() {
        return socketFactoryFallback;
    }

    /**
     * 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
     *
     * @param socketFactoryFallback 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
     * @return this
     */
    @Override
    public MailAccount socketFactoryFallback(boolean socketFactoryFallback) {
        this.socketFactoryFallback = socketFactoryFallback;
        return this;
    }

    /**
     * 获取指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口
     *
     * @return 指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口
     */
    public Integer getSocketFactoryPort() {
        return socketFactoryPort;
    }

    /**
     * 指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口
     *
     * @param socketFactoryPort 指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口
     * @return this
     */
    @Override
    public MailAccount socketFactoryPort(Integer socketFactoryPort) {
        this.socketFactoryPort = socketFactoryPort;
        return this;
    }

    /**
     * 是否打开调试模式，调试模式会显示与邮件服务器通信过程，默认不开启
     *
     * @return 是否打开调试模式，调试模式会显示与邮件服务器通信过程，默认不开启
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * 设置是否打开调试模式，调试模式会显示与邮件服务器通信过程，默认不开启
     *
     * @param debug 是否打开调试模式，调试模式会显示与邮件服务器通信过程，默认不开启
     * @return this
     */
    @Override
    public MailAccount debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * 获取字符集编码
     *
     * @return 编码
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * 设置字符集编码
     *
     * @param charset 字符集编码
     * @return this
     */
    @Override
    public MailAccount charset(Charset charset) {
        this.charset = charset;
        return this;
    }

    /**
     * 获得SMTP相关信息
     *
     * @return {@link Properties}
     */
    public Properties getSmtpProps() {
        final Properties props = new Properties();
        props.put(MAIL_PROTOCOL, "smtp");
        props.put(SMTP_HOST, this.host);
        props.put(SMTP_PORT, String.valueOf(this.port));
        props.put(SMTP_AUTH, String.valueOf(this.auth));
        if (this.timeout > 0) {
            props.put(SMTP_TIMEOUT, String.valueOf(this.timeout));
        }
        if (this.connectionTimeout > 0) {
            props.put(SMTP_CONNECTION_TIMEOUT, String.valueOf(this.connectionTimeout));
        }
        props.put(MAIL_DEBUG, String.valueOf(this.debug));

        // SSL
        if (this.sslEnable) {
            props.put(SMTP_SSL_ENABLE, "true");
            props.put(SMTP_SOCKET_FACTORY, socketFactoryClass);
            props.put(SMTP_SOCKET_FACTORY_FALLBACK, String.valueOf(this.socketFactoryFallback));
            props.put(SMTP_SOCKET_FACTORY_PORT, String.valueOf(this.socketFactoryPort));
            // issue#IZN95@Gitee，在Linux下需自定义SSL协议版本
            if (StringUtils.isNotBlank(this.sslProtocols)) {
                props.put(SMTP_SSL_PROTOCOLS, this.sslProtocols);
            }
        }
        return props;
    }

    @Override
    public String toString() {
        return "MailAccount [host=" + host + ", port=" + port + ", auth=" + auth + ", user=" + user + ", password=" + (StringUtils.isEmpty(this.password) ? "" : "******") + ", from=" + from + ", socketFactoryClass=" + socketFactoryClass + ", socketFactoryFallback=" + socketFactoryFallback + ", socketFactoryPort=" + socketFactoryPort + "]";
    }
}
