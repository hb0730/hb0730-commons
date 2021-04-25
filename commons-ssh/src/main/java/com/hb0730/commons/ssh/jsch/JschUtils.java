package com.hb0730.commons.ssh.jsch;

import com.hb0730.commons.lang.Validate;
import com.hb0730.commons.lang.constants.Charsets;
import com.hb0730.commons.lang.io.IORuntimeException;
import com.hb0730.commons.lang.io.IOUtils;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Consumer;

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
     * Connect
     *
     * @param channel {@link Channel}
     * @since 2.1.3
     */
    public static void connect(Channel channel) {
        connect(channel, 0);
    }

    /**
     * Connect
     *
     * @param channel {@link Channel}
     * @param timeout 超时时间,timeout==0,永不超时
     * @since 2.1.3
     */
    public static void connect(Channel channel, int timeout) {
        if (null == channel) {
            return;
        }
        try {
            if (0 == timeout) {
                channel.connect();
            } else {
                channel.connect(timeout);
            }
        } catch (JSchException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 流的读取,如果当前Channel并未链接，会自动链接
     *
     * @param channel Channel
     * @return 执行的结果
     */
    public static String read(Channel channel) {
        return read(channel, Charsets.UTF_8);
    }

    /**
     * 流的读取,如果当前Channel并未链接，会自动链接
     *
     * @param channel Channel
     * @param charset 编码，if charset==null,Charset.defaultCharset()
     * @return 执行结果
     * @since 2.1.3
     */
    public static String read(final Channel channel, final Charset charset) {
        Validate.notNull(channel, "Channel must be not null");
        Charset c = (charset == null ? Charset.defaultCharset() : charset);
        StringBuilder sb;
        InputStream in = null;
        try {
            in = channel.getInputStream();
            if (!channel.isConnected()) {
                JschUtils.connect(channel);
            }
            sb = new StringBuilder();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    sb.append(new String(tmp, 0, i, c));
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
        } finally {
            IOUtils.closeQuietly(in);
        }
        return sb.toString();
    }

    /**
     * 阻塞读取
     *
     * @param channel  {@link Channel}
     * @param consumer 回调函数
     * @since 2.1.3
     */
    public static void read(final Channel channel, Consumer<byte[]> consumer) {
        Validate.notNull(channel, "channel must be not null");
        if (!channel.isConnected()) {
            JschUtils.connect(channel);
        }
        InputStream in = null;
        try {

            in = channel.getInputStream();
            //循环读取
            byte[] buffer = new byte[1024];
            int i = 0;
            //如果没有数据来，线程会一直阻塞在这个地方等待数据。
            while ((i = in.read(buffer)) != -1) {
                consumer.accept(Arrays.copyOfRange(buffer, 0, i));
            }
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 往{@link Channel}写,默认UTF-8编码
     *
     * @param channel channel
     * @param cmd     命令
     * @since 2.1.3
     */
    public static void write(final Channel channel, String cmd) {
        write(channel, cmd, StandardCharsets.UTF_8);
    }

    /**
     * 往{@link Channel}写，默认UTF-8编码
     *
     * @param channel channel
     * @param cmd     命令
     * @param charset 编码,默认UTF-8编码
     * @since 2.1.3
     */
    public static void write(final Channel channel, final String cmd, Charset charset) {
        charset = (charset == null ? StandardCharsets.UTF_8 : charset);
        write(channel, cmd.getBytes(charset));
    }

    /**
     * 往channel写
     *
     * @param channel  channel
     * @param commands 命令
     * @since 2.1.3
     */
    public static void write(final Channel channel, byte[] commands) {
        Validate.notNull(channel, "Channel must be not null");
        OutputStream out = null;
        try {
            out = channel.getOutputStream();
            out.write(commands);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

}
