package com.hb0730.commons.ssh.jsch;

import com.hb0730.commons.lang.Validate;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * <a href="http://www.jcraft.com/jsch">JSch</a> {@link com.jcraft.jsch.Channel}操作
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class JschChannel {
    public final Session session;

    public JschChannel(Session session) {
        Validate.notNull(session, "Session must be not null!");
        this.session = session;
    }

    /**
     * 构建{@link JschChannel}
     *
     * @param session {@link Session}
     * @return {@link JschChannel}
     */
    public static JschChannel builder(Session session) {
        return new JschChannel(session);
    }

    /**
     * 创建{@link ChannelExec},并未{@link Channel#connect()}
     *
     * @return {@link ChannelExec}
     */
    public ChannelExec openExec() {
        return (ChannelExec) openChannel(ChannelType.EXEC);
    }

    /**
     * 创建Sftp,并未{@link Channel#connect()}
     *
     * @return {@link ChannelSftp}
     */
    public ChannelSftp openSftp() {
        return (ChannelSftp) openChannel(ChannelType.SFTP);
    }

    /**
     * 创建{@link ChannelExec},并未{@link Channel#connect()}
     *
     * @return {@link ChannelShell}
     */
    public ChannelShell openShell() {
        return (ChannelShell) openChannel(ChannelType.SHELL);
    }

    /**
     * 打开Channel连接
     *
     * @param channelType 通道类型，可以是shell或sftp等，见{@link ChannelType}
     * @return {@link Channel}
     */
    public Channel openChannel(ChannelType channelType) {
        return openChannel(channelType, 0);
    }

    /**
     * 打开Channel连接，如果存在超时，则会链接
     *
     * @param channelType 通道类型，可以是shell或sftp等，见{@link ChannelType}
     * @param timeout     连接超时时长，单位毫秒
     * @return {@link Channel}
     */
    public Channel openChannel(ChannelType channelType, int timeout) {
        Channel channel = createChannel(channelType);
        if (0 != timeout) {
            JschUtils.connect(channel, timeout);
        }
        return channel;
    }

    /**
     * 创建Channel连接
     *
     * @param channelType 通道类型，可以是shell或sftp等，见{@link ChannelType}
     * @return {@link Channel}
     */
    public Channel createChannel(ChannelType channelType) {
        Validate.notNull(channelType, "Channel type must be not null!");
        Channel channel;
        try {
            if (!session.isConnected()) {
                session.connect();
            }
            channel = session.openChannel(channelType.getValue());
        } catch (JSchException e) {
            throw new JschRuntimeException(e);
        }
        return channel;
    }

}
