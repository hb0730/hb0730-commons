package com.hb0730.commons.ssh.jsch;

import com.hb0730.commons.lang.Validate;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <a href="http://www.jcraft.com/jsch">JSch</a> {@link ChannelExec}相关操作
 *
 * @author bing_huang
 * @see <a href="http://epaul.github.io/jsch-documentation/javadoc/com/jcraft/jsch/ChannelExec.html">API</a>
 * @since 2.1.2
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
     * 执行，会自动关闭{@link ChannelExec},默认UTF-8编码
     *
     * @param cmd 命令
     * @return 执行结果
     * @since 2.1.3
     */
    public String exec(String cmd) {
        return exec(cmd, StandardCharsets.UTF_8);
    }

    /**
     * 执行，会自动关闭{@link ChannelExec}
     *
     * @param cmd     命令
     * @param charset 编码
     * @return 执行结果
     */
    public String exec(final String cmd, final Charset charset) {
        Validate.notBlank(cmd, "commands must be not blank");
        Validate.notNull("Charset must be not null");
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
