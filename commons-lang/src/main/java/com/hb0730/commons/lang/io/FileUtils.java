package com.hb0730.commons.lang.io;

import com.hb0730.commons.lang.Charsets;

import java.io.*;
import java.nio.charset.Charset;

/**
 * file 工具类
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class FileUtils {


    /**
     * 写字符类型内存
     *
     * @param file     file文件
     * @param content  内容
     * @param encoding 编码
     * @throws IOException io异常
     */
    public static void write(final File file, final CharSequence content, final String encoding) throws IOException {
        write(file, content, encoding, false);
    }

    /**
     * 写字符类型内存
     *
     * @param file     file文件
     * @param content  内容
     * @param encoding 编码
     * @param append   是否追加
     * @throws IOException io异常
     */
    public static void write(final File file, final CharSequence content, final String encoding, final boolean append)
            throws IOException {
        write(file, content, Charsets.toCharset(encoding), append);
    }

    /**
     * 写字符类型内存
     *
     * @param file     file文件
     * @param content  内容
     * @param encoding 编码
     * @throws IOException io异常
     */
    public static void write(final File file, final CharSequence content, final Charset encoding) throws IOException {
        write(file, content, encoding, false);
    }

    /**
     * 写字符类型内存
     *
     * @param file     file文件
     * @param content  内容
     * @param encoding 编码
     * @param append   是否追加
     * @throws IOException io异常
     */
    public static void write(final File file, final CharSequence content, final Charset encoding, final boolean append)
            throws IOException {
        final String str = content == null ? null : content.toString();
        writeString(file, str, encoding, append);
    }

    /**
     * 写String类型的内存
     *
     * @param file     file文件
     * @param content  内容
     * @param encoding 编码
     * @throws IOException io异常
     */
    public static void writeString(final File file, final String content, final String encoding) throws IOException {
        writeString(file, content, encoding, false);
    }

    /**
     * 写String类型的内存
     *
     * @param file     file文件
     * @param content  内容
     * @param encoding 编码
     * @param append   是否追加
     * @throws IOException io异常
     */
    public static void writeString(final File file, final String content, final String encoding,
                                   final boolean append) throws IOException {
        writeString(file, content, Charsets.toCharset(encoding), append);
    }

    /**
     * 写String类型的内存
     *
     * @param file     file文件
     * @param content  内容
     * @param encoding 编码
     * @param append   是否追加
     * @throws IOException io异常
     */
    public static void writeString(final File file, final String content, final Charset encoding, final boolean
            append) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(file, append);
            IOUtils.write(content, out, encoding);
            out.close();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 写byte类型内存
     *
     * @param file    file文件
     * @param content 内容
     * @throws IOException io异常
     */
    public static void writeByteArray(final File file, final byte[] content) throws IOException {
        writeByteArray(file, content, false);
    }

    /**
     * 写byte类型内存
     *
     * @param file    file文件
     * @param content 内容
     * @param append  是否追加
     * @throws IOException io异常
     */
    public static void writeByteArray(final File file, final byte[] content, final boolean append)
            throws IOException {
        writeByteArray(file, content, 0, content.length, append);
    }

    /**
     * 写byte类型内存
     *
     * @param file    file文件
     * @param content 内容
     * @param off     内容起始量
     * @param len     写入长度
     * @throws IOException io异常
     */
    public static void writeByteArray(final File file, final byte[] content, final int off, final int len)
            throws IOException {
        writeByteArray(file, content, off, len, false);
    }

    /**
     * 写byte类型内存
     *
     * @param file    file文件
     * @param content 内容
     * @param off     内容起始量
     * @param len     写入长度
     * @param append  是否追加
     * @throws IOException io异常
     */
    public static void writeByteArray(final File file, final byte[] content, final int off, final int len,
                                      final boolean append) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(file, append);
            out.write(content, off, len);
            out.close(); // don't swallow close Exception if copy completes normally
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 获取input流
     *
     * @param file file
     * @return {@link FileInputStream}
     * @throws IOException io异常
     */
    public static FileInputStream openInputStream(final File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }

    /**
     * 获取outputSteam
     *
     * @param file file
     * @return FileOutputSteam
     * @throws IOException io异常
     * @see #openOutputStream(File, boolean)
     */
    public static FileOutputStream openOutputStream(final File file) throws IOException {
        return openOutputStream(file, false);
    }

    /**
     * 获取outputSteam
     *
     * @param file   file
     * @param append 是否追加
     * @return FileOutputSteam
     * @throws IOException io异常
     */
    public static FileOutputStream openOutputStream(final File file, final boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            final File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new IOException("Directory '" + parent + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file, append);
    }

    /**
     * 递归删除
     *
     * @param path file路径
     * @return true: 如果全部删除,false:如果未删除或者部分删除
     */
    public static boolean forceDeletePath(File path) {
        if (null == path) {
            return false;
        }
        if (path.exists() && path.isDirectory()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    forceDeletePath(file);
                } else {
                    file.delete();
                }
            }
        }
        return path.delete();
    }
}
