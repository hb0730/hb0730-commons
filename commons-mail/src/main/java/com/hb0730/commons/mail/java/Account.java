package com.hb0730.commons.mail.java;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 账号信息
 *
 * @author bing_huang
 * @since 2.1.0
 */
public interface Account extends Serializable {
    /**
     * 设置SMTP服务器地址
     *
     * @param host SMTP服务器地址
     * @return this
     */
    Account host(String host);

    /**
     * 设置SMTP服务器端口
     *
     * @param port SMTP服务器端口
     * @return this
     */
    Account port(Integer port);

    /**
     * 设置是否需要用户名密码验证
     *
     * @param auth 是否需要用户名密码验证
     * @return this
     */
    Account auth(boolean auth);

    /**
     * 设置用户名
     *
     * @param user 用户名
     * @return this
     */
    Account user(String user);

    /**
     * 设置密码
     *
     * @param password 密码
     * @return this
     */
    Account password(String password);

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
    Account from(String from);

    /**
     * 设置SMTP超时时长，单位毫秒，缺省值不超时
     *
     * @param timeout SMTP超时时长，单位毫秒，缺省值不超时
     * @return this
     */
    Account timeout(long timeout);

    /**
     * 设置Socket连接超时值，单位毫秒，缺省值不超时
     *
     * @param connectionTimeout Socket连接超时值，单位毫秒，缺省值不超时
     * @return this
     */
    Account connectionTimeout(long connectionTimeout);

    /**
     * 设置是否使用SSL安全连接
     *
     * @param sslEnable 是否使用SSL安全连接
     * @return this
     */
    Account sslEnable(boolean sslEnable);

    /**
     * 设置SSL协议，多个协议用空格分隔
     *
     * @param sslProtocols SSL协议，多个协议用空格分隔
     * @return this
     */
    Account sslProtocols(String sslProtocols);

    /**
     * 设置指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
     *
     * @param socketFactoryClass 指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
     * @return this
     */
    Account socketFactoryClass(String socketFactoryClass);

    /**
     * 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
     *
     * @param socketFactoryFallback 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
     * @return this
     */
    Account socketFactoryFallback(boolean socketFactoryFallback);

    /**
     * 指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口
     *
     * @param socketFactoryPort 指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口
     * @return this
     */
    Account socketFactoryPort(Integer socketFactoryPort);

    /**
     * 设置是否打开调试模式，调试模式会显示与邮件服务器通信过程，默认不开启
     *
     * @param debug 是否打开调试模式，调试模式会显示与邮件服务器通信过程，默认不开启
     * @return this
     */
    Account debug(boolean debug);

    /**
     * 设置字符集编码
     *
     * @param charset 字符集编码
     * @return this
     */
    Account charset(Charset charset);

    /**
     * Set Other JavaMail properties for the Session.
     *
     * @param javaMailProperties JavaMail properties.
     * @return this
     */
    Account javaMailProperties(Properties javaMailProperties);

}
