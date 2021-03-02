package com.hb0730.commons.lang.io;

import com.hb0730.commons.lang.Charsets;
import com.hb0730.commons.lang.Validate;

import java.io.*;
import java.nio.charset.Charset;

/**
 * io 工具
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class IOUtils {
    public static final int EOF = -1;
    public static final int DEFAULT_BUFFER_SIZE = 1024;

    /**
     * 拷贝流，使用默认{@link #DEFAULT_BUFFER_SIZE}大小，拷贝后不关闭流
     *
     * @param is 输入流
     * @param os 输出流
     * @return 传输的byte数
     * @throws IOException IO异常
     * @since 2.1.1
     */
    public static long copy(InputStream is, OutputStream os) throws IOException {
        return copy(is, os, DEFAULT_BUFFER_SIZE);
    }

    /**
     * 拷贝流，拷贝后不关闭流
     *
     * @param is         输入流
     * @param os         输出流
     * @param bufferSize 缓存大小
     * @return 传输的byte数
     * @throws IOException IO异常
     * @since 2.1.1
     */
    public static long copy(InputStream is, OutputStream os, int bufferSize) throws IOException {
        Validate.notNull(is, "InputStream  is null");
        Validate.notNull(os, "OutputStream  is null");
        if (bufferSize <= 0) {
            bufferSize = DEFAULT_BUFFER_SIZE;
        }
        byte[] buffer = new byte[bufferSize];
        long size = 0;
        for (int len; (len = is.read(buffer)) != EOF; ) {
            os.write(buffer, 0, len);
            size += len;
            os.flush();
        }
        return size;

    }

    /**
     * 读取字符串，默认 {@code UTF-8}编码,不关闭流
     *
     * @param is 输入流
     * @return 字符串
     * @throws IOException IO异常
     * @since 2.1.1
     */
    public static String read(InputStream is) throws IOException {
        return read(is, com.hb0730.commons.lang.constants.Charsets.UTF_8);
    }

    /**
     * 读取字符串,不关闭流
     *
     * @param is      输入流
     * @param charset 编码
     * @return 字符串
     * @throws IOException IO异常
     * @since 2.1.1
     */
    public static String read(InputStream is, Charset charset) throws IOException {
        if (null == is) {
            return null;
        }
        if (null == charset || !Charset.isSupported(charset.name())) {
            charset = com.hb0730.commons.lang.constants.Charsets.UTF_8;
        }
        byte[] bytes = readBytes(is);
        return new String(bytes, charset);
    }

    /**
     * 读取字节数组,不关闭流
     *
     * @param is 输入流
     * @return 字节数组
     * @throws IOException IO异常
     * @since 2.1.1
     */
    public static byte[] readBytes(InputStream is) throws IOException {
        if (null == is) {
            return null;
        }
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            copy(is, os);
            return os.toByteArray();
        } finally {
            closeQuietly(os);
        }
    }

    /**
     * 写入
     *
     * @param data   内容,不为<code>null</code>
     * @param output out流,不为<code>null</code>
     * @throws IOException io异常
     */
    public static void write(final byte[] data, final OutputStream output)
            throws IOException {
        if (null != data && null != output) {
            output.write(data);
        }
    }

    /**
     * 写入
     *
     * @param data     内容,不为<code>null</code>
     * @param output   out流,不为<code>null</code>
     * @param encoding 编码
     * @throws IOException io异常
     */
    public static void write(final char[] data, final OutputStream output, final String encoding)
            throws IOException {
        write(data, output, Charsets.toCharset(encoding));
    }

    /**
     * 写入
     *
     * @param data     内容,不为<code>null</code>
     * @param output   out流,不为<code>null</code>
     * @param encoding 编码
     * @throws IOException io异常
     */
    public static void write(final char[] data, final OutputStream output, final Charset encoding) throws IOException {
        if (null != data && null != output) {
            output.write(new String(data).getBytes(Charsets.toCharset(encoding)));
        }
    }

    /**
     * 写入
     *
     * @param data     内容
     * @param output   out流
     * @param encoding 编码
     * @throws IOException io异常
     */
    public static void write(final CharSequence data, final OutputStream output, final String encoding)
            throws IOException {
        write(data, output, Charsets.toCharset(encoding));
    }

    /**
     * 写入
     *
     * @param data     内容
     * @param output   out流
     * @param encoding 编码
     * @throws IOException io异常
     */
    public static void write(final CharSequence data, final OutputStream output, final Charset encoding)
            throws IOException {
        if (null != data && null != output) {
            write(data.toString(), output, encoding);
        }
    }

    /**
     * output流写
     *
     * @param data    内存，
     * @param out     outputStream,
     * @param charset 编码
     * @throws IOException io异常
     */
    public static void write(final String data, final OutputStream out, final Charset charset) throws IOException {
        if (null != data && null != out) {
            out.write(data.getBytes(Charsets.toCharset(charset)));
        }
    }


    /**
     * 关闭stream流
     *
     * @param output 可以为<code>null</code>或者已关闭
     */
    public static void closeQuietly(final OutputStream output) {
        closeQuietly((Closeable) output);
    }

    /**
     * 关闭流
     *
     * @param closeable 要关闭的对象，可能关闭或者为<code>null</code>
     */
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }

}
