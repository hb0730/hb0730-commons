package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import java.nio.charset.Charset;

/**
 * <a href="http://www.jcraft.com/jsch">JSch</a> {@link ChannelExec}相关操作
 *
 * @author bing_huang
 * @since 2.1.2
 * @see <a href="http://epaul.github.io/jsch-documentation/javadoc/com/jcraft/jsch/ChannelExec.html">API</a>
 */
public class JschChannelExec {
    private final Session session;

    public JschChannelExec(Session session) {
        if (null == session) {
            throw new JschRuntimeException("JSch session is null");
        }
        if (!session.isConnected()) {
            throw new JschRuntimeException("JSch session is Close null");
        }
        this.session = session;
    }

    /**
     * 构建Jsch ChannelExec
     *
     * @param session JSch Session ，该Session必须以链接{@link Session#connect()}
     * @return this
     */
    public static JschChannelExec builder(Session session) {
        return new JschChannelExec(session);
    }

    /**
     * 执行，会自动关闭{@link ChannelExec}
     *
     * @param cmd     命令
     * @param charset 编码
     * @return 执行结果
     */
    public String exec(String cmd, Charset charset) {
        ChannelExec exec = channelExec();
        exec.setCommand(cmd.getBytes(charset));
        exec.setInputStream(null);
        exec.setErrStream(System.err);
        return read(exec);
    }

    private String read(ChannelExec exec) {
        String result = JschUtils.read(exec);
        JschUtils.close(exec);
        return result;
    }

    private ChannelExec channelExec() {
        return JschChannel.builder(session)
                .openExec();
    }
}
