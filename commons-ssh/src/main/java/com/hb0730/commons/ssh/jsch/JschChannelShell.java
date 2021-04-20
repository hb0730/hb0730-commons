package com.hb0730.commons.ssh.jsch;

import com.hb0730.commons.lang.io.IORuntimeException;
import com.hb0730.commons.lang.io.IOUtils;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * <a href="http://www.jcraft.com/jsch">JSch</a> {@link ChannelShell}相关操作
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class JschChannelShell {
    private final Session session;

    /**
     * 创建{@link JschChannelShell}
     *
     * @param session {@link Session}
     * @return {@link JschChannelShell}
     */
    public static JschChannelShell builder(Session session) {
        return new JschChannelShell(session);
    }

    /**
     * 构造ChannelShell
     *
     * @param session {@link Session}
     */
    private JschChannelShell(Session session) {
        if (null == session) {
            throw new JschRuntimeException("JSch session is null");
        }
        if (!session.isConnected()) {
            throw new JschRuntimeException("JSch session is Close null");
        }
        this.session = session;
    }


    /**
     * 执行Shell命令
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
        ChannelShell shell = openShell();
        OutputStream out = null;
        try {
            shell.connect();
            out = shell.getOutputStream();
            out.write(bytes);
            out.flush();
            return JschUtils.read(shell);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } catch (JSchException e) {
            throw new JschRuntimeException(e);
        } finally {
            IOUtils.closeQuietly(out);
            JschUtils.close(shell);
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

}
