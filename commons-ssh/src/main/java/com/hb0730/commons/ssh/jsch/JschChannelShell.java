package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <a href="http://www.jcraft.com/jsch">JSch</a> {@link ChannelShell}相关操作
 *
 * @author bing_huang
 * @see <a href="http://epaul.github.io/jsch-documentation/javadoc/com/jcraft/jsch/ChannelShell.html">API</a>
 * @since 2.1.2
 */
public class JschChannelShell {
    private ChannelShell shell;
    private Session session;

    /**
     * 创建{@link JschChannelShell},可以使用同一个实例多次调用{@link #exec(String)}
     *
     * @param session {@link Session}
     * @return {@link JschChannelShell}
     */
    public static JschChannelShell builder(Session session) {
        return new JschChannelShell(session);
    }

    /**
     * 创建{@link JschChannelShell},如果通过这，无法多次调用 {@link #exec(String)}
     *
     * @param shell {@link ChannelShell}
     * @return {@link JschChannelShell}
     * @since 2.1.3
     */
    public static JschChannelShell builder(ChannelShell shell) {
        return new JschChannelShell(shell);
    }

    /**
     * 构造ChannelShell
     *
     * @param session {@link Session}
     */
    public JschChannelShell(Session session) {
        if (null == session) {
            throw new JschRuntimeException("JSch session is null");
        }
        if (!session.isConnected()) {
            throw new JschRuntimeException("JSch session is Close null");
        }
        this.session = session;
        this.shell = openShell();
    }

    /**
     * 构造ChannelShell
     *
     * @param shell {@link ChannelShell}
     * @since 2.1.3
     */
    public JschChannelShell(ChannelShell shell) {
        if (null == shell) {
            throw new JschRuntimeException("JSch ChannelShell is null");
        }
        this.shell = shell;
    }

    /**
     * 执行Shell命令,UTF-8编码
     * <p>
     * 此方法单次发送一个命令到服务端，自动读取环境变量，执行结束后自动关闭channel，不会产生阻塞。
     * </p>
     *
     * @param cmd 命令
     * @return String
     * @since 2.1.3
     */
    public String exec(final String cmd) {
        return exec(cmd, StandardCharsets.UTF_8);
    }

    /**
     * 执行Shell命令,包含一个<code>exit</code>命令
     * <p>
     * 此方法单次发送一个命令到服务端，自动读取环境变量，执行结束后自动关闭channel，不会产生阻塞。
     * </p>
     *
     * @param cmd     命令
     * @param charset 发送和读取内容的编码
     * @return String
     */
    public String exec(final String cmd, Charset charset) {
        byte[] bytes;
        if (!cmd.contains(JschUtils.EXIT)) {
            bytes = (cmd + "exit\n").getBytes(charset);
        } else {
            bytes = cmd.getBytes(charset);
        }
        if (null == shell) {
            this.shell = openShell();
        }
        try {
            if (!shell.isConnected()) {
                shell.connect();
            }
            JschUtils.write(shell, bytes);
            return JschUtils.read(shell, charset);
        } catch (JSchException e) {
            throw new JschRuntimeException(e);
        } finally {
            JschUtils.close(shell);
            //使其可以多次调用生效
            shell = null;
        }
    }

    /**
     * 创建ChannelShell
     *
     * @return ChannelShell
     * @see JschChannel#openShell()
     */
    public ChannelShell openShell() {
        return JschChannel.builder(session)
                .openShell();
    }

    /**
     * 创建ChannelShell,并Connect
     *
     * @param timeout 超时时间，如果为timeout==0,永不超时
     * @return {@link ChannelShell}
     * @since 2.1.3
     */
    public ChannelShell openShell(int timeout) {
        ChannelShell channelShell = openShell();
        JschUtils.connect(channelShell, timeout);
        return channelShell;
    }

}
