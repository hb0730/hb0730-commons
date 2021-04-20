package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.Session;

/**
 * <a href="http://www.jcraft.com/jsch">JSch</a> {@link com.jcraft.jsch.ChannelSftp}相关操作
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class JschChannelSftp {
    private final Session session;

    public JschChannelSftp(Session session) {
        if (null == session) {
            throw new JschRuntimeException("JSch session is null");
        }
        if (!session.isConnected()) {
            throw new JschRuntimeException("JSch session is Close null");
        }
        this.session = session;
    }

    public static JschChannelSftp builder(Session session) {
        return new JschChannelSftp(session);
    }
}
