package com.hb0730.commons.ssh.jsch;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.builder.Builder;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * <a href="http://www.jcraft.com/jsch">JSch</a> {@link Session} 构建
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class JschSessionBuilder implements Builder<Session> {
    public static final int DEFAULT_PORT = 22;
    public static final String DEFAULT_USER = "root";
    /**
     * 主机
     */
    private String host;
    /**
     * 端口,默认22
     */
    private int port = DEFAULT_PORT;
    /**
     * 用户,默认user
     */
    private String username = DEFAULT_USER;
    /**
     * 密码
     */
    private String pass;
    /**
     * 私钥的路径
     */
    private String privateKeyPath;
    /**
     * 私钥文件的密码
     */
    private byte[] passphrase;

    /**
     * Session过期时间
     */
    private int sessionTimeout;

    public static JschSessionBuilder builder() {
        return new JschSessionBuilder();
    }

    /**
     * ssh HOST
     *
     * @param host 主机
     * @return this
     */
    public JschSessionBuilder sshHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * SSH PORT,默认端口为22
     *
     * @param port port
     * @return this
     */
    public JschSessionBuilder sshPort(int port) {
        this.port = port <= 0 ? DEFAULT_PORT : port;
        return this;
    }

    /**
     * SSH username,默认用户为<code>root</code>
     *
     * @param user 用户名
     * @return this
     */
    public JschSessionBuilder sshUser(String user) {
        this.username = StringUtils.isBlank(user) ? DEFAULT_USER : user;
        return this;
    }

    /**
     * SSH password
     *
     * @param password 密码
     * @return this
     */
    public JschSessionBuilder sshPass(String password) {
        this.pass = password;
        return this;
    }

    /**
     * 私钥的路径
     *
     * @param privateKeyPath 私钥的路径
     * @return this
     */
    public JschSessionBuilder privateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
        return this;
    }

    /**
     * 私钥文件的密码
     *
     * @param passphrase 私钥文件的密码
     * @return this
     */
    public JschSessionBuilder passphrase(byte[] passphrase) {
        this.passphrase = passphrase;
        return this;
    }

    /**
     * session过期时间，默认不过期，
     *
     * @param milliseconds 过期时间,毫秒
     * @return this
     */
    public JschSessionBuilder sessionTimeout(int milliseconds) {
        this.sessionTimeout = milliseconds;
        return this;
    }

    @Override
    public Session build() {
        final JSch jsch = new JSch();
        Session session;
        try {
            if (StringUtils.isNotBlank(privateKeyPath)) {
                jsch.addIdentity(privateKeyPath, passphrase);
            }
            session = jsch.getSession(username, host, port);
            if (StringUtils.isNotBlank(pass)) {
                session.setPassword(pass);
            }
            // 设置第一次登录的时候提示，可选值：(ask | yes | no)
            session.setConfig("StrictHostKeyChecking", "no");
            if (0 != sessionTimeout) {
                session.setTimeout(sessionTimeout);
            }
            session.connect(sessionTimeout);
        } catch (JSchException e) {
            throw new JschRuntimeException(e);
        }
        return session;
    }
}
