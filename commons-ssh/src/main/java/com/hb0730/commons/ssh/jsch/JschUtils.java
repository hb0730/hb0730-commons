package com.hb0730.commons.ssh.jsch;

import com.hb0730.commons.lang.io.IORuntimeException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;

/**
 * <a href="http://www.jcraft.com/jsch">JSch</a>工具类<br>
 * Jsch是Java Secure Channel的缩写。JSch是一个SSH2的纯Java实现。<br>
 * 它允许你连接到一个SSH服务器，并且可以使用端口转发，X11转发，文件传输等。<br>
 * 在线<a href="http://epaul.github.io/jsch-documentation/javadoc/com/jcraft/jsch/package-summary.html">API</a><br>
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class JschUtils {
    public final static String EXIT = "exit";

    /**
     * 关闭SSH连接会话
     *
     * @param session SSH会话
     */
    public static void close(Session session) {
        if (null != session && session.isConnected()) {
            session.disconnect();
        }
    }

    /**
     * 关闭会话通道
     *
     * @param channel 会话通道
     */
    public static void close(Channel channel) {
        if (null != channel && channel.isConnected()) {
            channel.disconnect();
        }
    }

    /**
     * 流的读取,如果当前Channel并未链接，会自动链接
     *
     * @param channel Channel
     * @return 执行的结果
     */
    public static String read(Channel channel) {
        StringBuilder sb;
        try {
            InputStream in = channel.getInputStream();
            if (!channel.isConnected()) {
                try {
                    channel.connect();
                } catch (JSchException e) {
                    e.printStackTrace();
                }
            }
            sb = new StringBuilder();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    sb.append(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    break;
                }
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } catch (InterruptedException e) {
            throw new JschRuntimeException(e);
        }
        return sb.toString();
    }
}
