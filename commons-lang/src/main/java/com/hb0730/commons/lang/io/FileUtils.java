package com.hb0730.commons.lang.io;

import com.hb0730.commons.lang.Charsets;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.Validate;
import com.hb0730.commons.lang.collection.ListUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * file 工具类
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class FileUtils {

    /**
     * 根据路径获取文件
     *
     * @param filepath 文件路径
     * @return {@link File},如果{@code filepath}为null,返回为<code>null</code>
     * @since 2.1.1
     */
    public static File getFile(String filepath) {
        return StringUtils.isBlank(filepath) ? null : new File(filepath);
    }

    /**
     * 文件是否存在
     *
     * @param file {@link File}
     * @return <code>true</code> 文件存在，否则不存在
     * @since 2.1.1
     */
    public static boolean isFileExists(File file) {
        return null != file && file.exists();
    }

    /**
     * 文件是否存在
     *
     * @param filepath 文件路径
     * @return <code>true</code> 文件存在，否则不存在
     * @since 2.1.1
     */
    public static boolean isFileExists(String filepath) {
        return isFileExists(getFile(filepath));
    }

    /**
     * 重命名
     *
     * @param filepath 文件路径
     * @param newName  新名称
     * @return 是否成功
     * @since 2.1.1
     */
    public static boolean rename(String filepath, String newName) {
        return rename(getFile(filepath), newName);
    }

    /**
     * 重命名
     *
     * @param file    文件
     * @param newName 新名称
     * @return 是否成功
     * @since 2.1.1
     */
    public static boolean rename(File file, String newName) {
        if (!isFileExists(file) || StringUtils.isBlank(newName)) {
            return false;
        }
        if (newName.equals(file.getName())) {
            return true;
        }
        File newFile = new File(file.getParent() + File.separator + newName);
        return !newFile.exists() && file.renameTo(newFile);
    }

    /**
     * 判断是否为目录
     *
     * @param dirPath 目录路径
     * @return 是否为目录
     * @since 2.1.1
     */
    public static boolean isDir(String dirPath) {
        return isDir(getFile(dirPath));
    }

    /**
     * 判断是否为目录
     *
     * @param file 文件
     * @return 是否为目录
     * @since 2.1.1
     */
    public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }

    /**
     * 判断是否为文件
     *
     * @param filepath 文件路径
     * @return 是否为文件
     * @since 2.1.1
     */
    public static boolean isFile(String filepath) {
        return isFile(getFile(filepath));
    }

    /**
     * 判断是否为文件
     *
     * @param file 文件
     * @return 是否为文件
     * @since 2.1.1
     */
    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }

    /**
     * 判断目录是否存在，如果不存在则创建目录
     *
     * @param dirPath 目录路径
     * @return <code>true</code>: 存在或创建成功,<code>false</code>:不存在或者创建是否
     * @since 2.1.1
     */
    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFile(dirPath));
    }

    /**
     * 判断目录是否存在，如果不存在则创建目录
     *
     * @param file 目录
     * @return <code>true</code>: 存在或创建成功,<code>false</code>:不存在或者创建是否
     * @since 2.1.1
     */
    public static boolean createOrExistsDir(File file) {
        return null != file && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param filePath 文件路径
     * @return <code>true</code>: 存在或创建成功,<code>code</code>: 不存在或创建失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean createOrExistsFile(String filePath) throws IOException {
        return createOrExistsFile(getFile(filePath));
    }


    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return <code>true</code>: 存在或创建成功,<code>code</code>: 不存在或创建失败
     * @throws IOException IO异常
     * @since 2.1.1
     */
    public static boolean createOrExistsFile(File file) throws IOException {
        if (null == file) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        return createOrExistsDir(file.getParentFile()) && file.createNewFile();
    }

    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param filePath 文件路径
     * @return <code>true</code>: 创建成功,<code>false</code>: 创建失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean createFileByDeleteOldFile(String filePath) throws IOException {
        return createFileByDeleteOldFile(getFile(filePath));
    }

    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param file 文件
     * @return <code>true</code>: 创建成功,<code>false</code>:创建失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean createFileByDeleteOldFile(File file) throws IOException {
        if (file == null) {
            return false;
        }
        // 文件存在并且删除失败返回false
        if (file.exists() && file.isFile() && !file.delete()) {
            return false;
        }
        // 创建目录失败返回false
        return createOrExistsDir(file.getParentFile()) && file.createNewFile();
    }

    /**
     * 删除目录
     *
     * @param dirPath 目录路径
     * @return <code>true</code>:删除成功<br>
     * <code>false</code>:删除失败
     * @since 2.1.1
     */
    public static boolean deleteDir(String dirPath) {
        return deleteDir(getFile(dirPath));
    }

    /**
     * 删除目录
     *
     * @param dir 目录
     * @return <code>true</code>:删除成功<br>
     * <code>false</code>:删除失败
     * @since 2.1.1
     */
    public static boolean deleteDir(File dir) {
        if (null == dir) {
            return false;
        }
        if (!dir.exists()) {
            return false;
        }
        if (!dir.isDirectory()) {
            return false;
        }
        //删除目录下的所有文件
        deleteFilesInDir(dir);
        return dir.delete();
    }

    /**
     * 删除文件
     *
     * @param filepath 文件路径
     * @return <code>true</code>:删除成功<br>
     * <code>false</code>:删除失败
     * @since 2.1.1
     */
    public static boolean deleteFile(String filepath) {
        return deleteFile(getFile(filepath));
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return <code>true</code>:删除成功<br>
     * <code>false</code>:删除失败
     * @since 2.1.1
     */
    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }

    /**
     * 删除目录下的所有文件
     *
     * @param dirPath 目录路径
     * @return <code>true</code>:删除成功<br>
     * <code>false</code>:删除失败
     * @since 2.1.1
     */
    public static boolean deleteFilesInDir(String dirPath) {
        return deleteFilesInDir(getFile(dirPath));
    }

    /**
     * 删除目录下的所有文件
     *
     * @param dir 文件目录
     * @return <code>true</code>:删除成功<br>
     * <code>false</code>:删除失败
     * @since 2.1.1
     */
    public static boolean deleteFilesInDir(File dir) {
        if (dir == null) {
            return false;
        }
        // 目录不存在返回true
        if (!dir.exists()) {
            return true;
        }
        // 不是目录返回false
        if (!dir.isDirectory()) {
            return false;
        }
        File[] files = dir.listFiles();
        if (null != files && files.length > 0) {
            for (File file : files) {
                if (file.isFile() && !deleteFile(file)) {
                    return false;
                } else if (file.isDirectory() && !deleteDir(file)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获取目录下所有文件
     *
     * @param dirPath     目录路径
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDir(String dirPath, boolean isRecursive) {
        return listFilesInDir(getFile(dirPath), isRecursive);
    }

    /**
     * 获取目录下所有文件
     *
     * @param dir         目录
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(File dir, boolean isRecursive) {
        if (!isDir(dir)) {
            return null;
        }
        if (isRecursive) {
            return listFilesInDir(dir);
        }
        File[] files = dir.listFiles();
        return ListUtils.toArrayList(files);
    }

    /**
     * 获取目录下所有文件包括子目录
     *
     * @param dirPath 目录路径
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDir(String dirPath) {
        return listFilesInDir(getFile(dirPath));
    }

    /**
     * 获取目录下所有文件包括子目录
     *
     * @param dir 目录
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDir(File dir) {
        if (null == dir) {
            return null;
        }
        File[] files = dir.listFiles();
        if (isDir(dir) && null != files) {
            List<File> list = new ArrayList<>(files.length);
            if (files.length > 0) {
                for (File file : files) {
                    list.add(file);
                    if (file.isDirectory()) {
                        List<File> fileList = listFilesInDir(file);
                        if (null != fileList) {
                            list.addAll(fileList);
                        }
                    }
                }
            }
            return list;
        }
        return null;
    }

    /**
     * 获取目录下所有后缀名为suffix的文件 <p>大小写忽略</p>
     *
     * @param dirPath     目录路径
     * @param suffix      后缀名
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDirWithSuffix(String dirPath, String suffix, boolean isRecursive) {
        return listFilesInDirWithSuffix(getFile(dirPath), suffix, isRecursive);
    }

    /**
     * 获取目录下所有后缀名为suffix的文件 <p>大小写忽略</p>
     *
     * @param dir         目录
     * @param suffix      后缀名
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDirWithSuffix(File dir, String suffix, boolean isRecursive) {
        if (isRecursive) {
            return listFilesInDirWithSuffix(dir, suffix);
        }
        if (null == dir || !isDir(dir)) {
            return null;
        }
        File[] files = dir.listFiles();
        if (null != files && files.length > 0) {
            List<File> list = new ArrayList<>(files.length);
            for (File file : files) {
                if (null != suffix && file.getName().toUpperCase().endsWith(suffix.toUpperCase())) {
                    list.add(file);
                }
            }
            return list;
        }
        return null;

    }

    /**
     * 获取目录下所有后缀名为suffix的文件包括子目录 <p>大小写忽略</p>
     *
     * @param dirPath 目录路径
     * @param suffix  后缀名
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDirWithSuffix(String dirPath, String suffix) {
        return listFilesInDirWithSuffix(getFile(dirPath), suffix);
    }

    /**
     * 获取目录下所有后缀名为suffix的文件包括子目录 <p>大小写忽略</p>
     *
     * @param dir    目录
     * @param suffix 后缀名
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDirWithSuffix(File dir, String suffix) {
        if (null == dir || !isDir(dir)) {
            return null;
        }
        List<File> list = ListUtils.list(false);
        File[] files = dir.listFiles();
        if (null != files && files.length > 0) {
            for (File file : files) {
                if (null != suffix && file.getName().toUpperCase().endsWith(suffix.toUpperCase())) {
                    list.add(file);
                }
                if (file.isDirectory()) {
                    list.addAll(listFilesInDirWithSuffix(file, suffix));
                }
            }
        }
        return list;
    }

    /**
     * 获取目录下所有符合filter的文件
     *
     * @param dirPath     目录路径
     * @param filter      过滤器
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, FilenameFilter filter, boolean isRecursive) {
        return listFilesInDirWithFilter(getFile(dirPath), filter, isRecursive);
    }

    /**
     * 获取目录下所有符合filter的文件
     *
     * @param dir         目录
     * @param filter      过滤器
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDirWithFilter(File dir, FilenameFilter filter, boolean isRecursive) {
        if (isRecursive) {
            return listFilesInDirWithFilter(dir, filter);
        }
        if (dir == null || !isDir(dir)) {
            return null;
        }
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            List<File> list = ListUtils.list(false);
            for (File file : files) {
                if (null != filter && filter.accept(file.getParentFile(), file.getName())) {
                    list.add(file);
                }
            }
            return list;
        }
        return null;
    }

    /**
     * 获取目录下所有符合filter的文件包括子目录
     *
     * @param dirPath 目录路径
     * @param filter  过滤器
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, FilenameFilter filter) {
        return listFilesInDirWithFilter(getFile(dirPath), filter);
    }

    /**
     * 获取目录下所有符合filter的文件包括子目录
     *
     * @param dir    目录
     * @param filter 过滤器
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> listFilesInDirWithFilter(File dir, FilenameFilter filter) {
        if (null == dir || !isDir(dir)) {
            return ListUtils.list(false);
        }
        File[] files = dir.listFiles();
        if (null != files && files.length > 0) {
            List<File> list = new ArrayList<>(files.length);
            for (File file : files) {
                if (null != filter && filter.accept(file.getParentFile(), file.getName())) {
                    list.add(file);
                }
                if (file.isDirectory()) {
                    list.addAll(listFilesInDirWithFilter(file, filter));
                }
            }
            return list;
        }
        return ListUtils.list(false);
    }

    /**
     * 获取目录下指定文件名的文件包括子目录 <p>大小写忽略</p>
     *
     * @param dirPath  目录路径
     * @param filename 文件名
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> searchFileInDir(String dirPath, String filename) {
        return searchFileInDir(getFile(dirPath), filename);
    }

    /**
     * 获取目录下指定文件名的文件包括子目录 <p>大小写忽略</p>
     *
     * @param dir      目录
     * @param filename 文件名
     * @return 文件链表
     * @since 2.1.1
     */
    public static List<File> searchFileInDir(File dir, String filename) {
        if (null == dir || !isDir(dir)) {
            return ListUtils.list(false);
        }
        File[] files = dir.listFiles();
        if (null != files && files.length > 0) {
            List<File> list = new ArrayList<>();
            for (File file : files) {
                if (null != filename && file.getName().equalsIgnoreCase(filename)) {
                    list.add(file);
                }
                if (file.isDirectory()) {
                    list.addAll(searchFileInDir(file, filename));
                }
            }
            return list;
        }
        return ListUtils.list(false);
    }

    /**
     * 写字符类型内存
     *
     * @param file     file文件,不允许为<code>null</code>
     * @param content  内容,
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
     * 将输入流写入文件
     *
     * @param filePath 路径
     * @param is       输入流
     * @param append   是否追加在文件末
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean writeByStream(String filePath, InputStream is, boolean append) throws IOException {
        return writeByStream(getFile(filePath), is, append);
    }

    /**
     * 将输入流写入文件
     *
     * @param file   文件
     * @param is     输入流
     * @param append 是否追加在文件末
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean writeByStream(File file, InputStream is, boolean append) throws IOException {
        if (null == file || null == is) {
            return false;
        }
        if (!createOrExistsFile(file)) {
            return false;
        }
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(file, append))) {
            byte[] data = new byte[1024];
            int len;
            while ((len = is.read(data, 0, 1024)) != -1) {
                os.write(data, 0, len);
            }
            return true;
        } finally {
            is.close();
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
        Validate.notNull(file, "file must be not null");
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
     * 指定编码按行读取文件到链表中
     *
     * @param filePath    文件路径
     * @param charsetName 编码格式
     * @return 文件行链表
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static List<String> readFileList(String filePath, String charsetName) throws IOException {
        return readFileList(getFile(filePath), charsetName);
    }

    /**
     * 指定编码按行读取文件到链表中
     *
     * @param file        文件
     * @param charsetName 编码格式
     * @return 文件行链表
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static List<String> readFileList(File file, String charsetName) throws IOException {
        return readFileList(file, 0, 0x7FFFFFFF, charsetName);
    }

    /**
     * 指定编码按行读取文件到链表中
     *
     * @param filePath    文件路径
     * @param st          需要读取的开始行数
     * @param end         需要读取的结束行数
     * @param charsetName 编码格式
     * @return 包含制定行的list
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static List<String> readFileList(String filePath, int st, int end, String
            charsetName) throws IOException {
        return readFileList(getFile(filePath), st, end, charsetName);
    }

    /**
     * 指定编码按行读取文件到链表中
     *
     * @param file        文件
     * @param st          需要读取的开始行数
     * @param end         需要读取的结束行数
     * @param charsetName 编码格式
     * @return 包含从start行到end行的list
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static List<String> readFileList(File file, int st, int end, String charsetName) throws IOException {
        if (null == file) {
            return null;
        }
        if (st > end) {
            return null;
        }
        //如果该编码不支持，就变成默认的utf8
        if (StringUtils.isBlank(charsetName) || !Charset.isSupported(charsetName)) {
            charsetName = com.hb0730.commons.lang.constants.Charsets.UTF_8_NAME;
        }
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charsetName);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            int curLine = 1;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (curLine > end) {
                    break;
                }
//                if (st <= curLine && curLine <= end) {
//                    list.add(line);
//                }
                if (st <= curLine) {
                    list.add(line);
                }
                ++curLine;
            }
            return list;
        }
    }

    /**
     * 指定编码按行读取文件到字符串中
     *
     * @param filePath    文件路径
     * @param charsetName 编码格式,默认UTF-8
     * @return 字符串
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static String readFileString(String filePath, String charsetName) throws IOException {
        return readFileString(getFile(filePath), charsetName);
    }

    /**
     * 指定编码按行读取文件到字符串中
     *
     * @param file        文件
     * @param charsetName 编码格式,默认UTF-8
     * @return 字符串
     * @throws IOException io异常
     * @see Files#readAllBytes(Path)
     * @since 2.1.1
     */
    public static String readFileString(File file, String charsetName) throws IOException {
        if (file == null) {
            return null;
        }
        if (StringUtils.isBlank(charsetName)) {
            charsetName = com.hb0730.commons.lang.constants.Charsets.UTF_8_NAME;
        }
        byte[] content = Files.readAllBytes(file.toPath());
        return new String(content, charsetName);
    }

    /**
     * 读取文件到字符数组中
     *
     * @param filePath 文件路径
     * @return 字符数组
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static byte[] readFileBytes(String filePath) throws IOException {
        return readFileBytes(getFile(filePath));
    }

    /**
     * 获取文件最后修改的毫秒时间戳
     *
     * @param file 文件路径
     * @return 字符数组
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static byte[] readFileBytes(File file) throws IOException {
        if (null == file) {
            return null;
        }
        return Files.readAllBytes(file.toPath());
    }

    /**
     * 复制或移动目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @param isMove      是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean copyOrMoveDir(String srcDirPath, String destDirPath, boolean isMove) throws IOException {
        return copyOrMoveDir(getFile(srcDirPath), getFile(destDirPath), isMove);
    }

    /**
     * 复制或移动目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @param isMove  是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean copyOrMoveDir(File srcDir, File destDir, boolean isMove) throws IOException {
        if (null == srcDir || null == destDir) {
            return false;
        }
        // 如果目标目录在源目录中则返回false，看不懂的话好好想想递归怎么结束
        // 为防止以上这种情况出现出现误判，须分别在后面加个路径分隔符
        String srcPath = srcDir.getPath() + File.separator;
        String destPath = destDir.getPath() + File.separator;
        // 源文件不存在或者不是目录则返回false
        if (destPath.contains(srcPath)) {
            return false;
        }
        if (!srcDir.exists() || !srcDir.isDirectory()) {
            return false;
        }
        // 目标目录不存在返回false
        if (!createOrExistsDir(destDir)) {
            return false;
        }

        File[] files = srcDir.listFiles();
        if (null != files && files.length > 0) {
            for (File file : files) {
                File destFile = new File(destPath + file.getName());
                if (file.isFile() && !copyOrMoveFile(file, destFile, isMove)) {
                    return false;
                } else if (file.isDirectory() && !copyOrMoveDir(file, destFile, isMove)) {
                    return false;
                }
            }
        }
        return !isMove || deleteDir(srcDir);

    }

    /**
     * 复制或移动文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @param isMove       是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean copyOrMoveFile(String srcFilePath, String destFilePath, boolean isMove) throws IOException {
        return copyOrMoveFile(getFile(srcFilePath), getFile(destFilePath), isMove);
    }

    /**
     * 复制或移动文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @param isMove   是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean copyOrMoveFile(File srcFile, File destFile, boolean isMove) throws IOException {
        if (null == srcFile || null == destFile) {
            return false;
        }
        // 源文件不存在或者不是文件则返回false
        if (!srcFile.exists() || !srcFile.isFile()) {
            return false;
        }
        // 目标文件存在且是文件则返回false
        if (srcFile.exists() && destFile.isFile()) {
            return false;
        }
        if (!createOrExistsDir(destFile.getParentFile())) {
            return false;
        }
        return writeByStream(destFile, openInputStream(srcFile), false) && !(isMove && !deleteFile(srcFile));
    }

    /**
     * 复制目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean copyDir(String srcDirPath, String destDirPath) throws IOException {
        return copyDir(getFile(srcDirPath), getFile(destDirPath));
    }

    /**
     * 复制目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean copyDir(File srcDir, File destDir) throws IOException {
        return copyOrMoveDir(srcDir, destDir, false);
    }


    /**
     * 复制文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean copyFile(String srcFilePath, String destFilePath) throws IOException {
        return copyFile(getFile(srcFilePath), getFile(destFilePath));
    }

    /**
     * 复制文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean copyFile(File srcFile, File destFile) throws IOException {
        return copyOrMoveFile(srcFile, destFile, false);
    }

    /**
     * 移动目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean moveDir(String srcDirPath, String destDirPath) throws IOException {
        return moveDir(getFile(srcDirPath), getFile(destDirPath));
    }

    /**
     * 移动目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean moveDir(File srcDir, File destDir) throws IOException {
        return copyOrMoveDir(srcDir, destDir, true);
    }

    /**
     * 移动文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean moveFile(String srcFilePath, String destFilePath) throws IOException {
        return moveFile(getFile(srcFilePath), getFile(destFilePath));
    }

    /**
     * 移动文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static boolean moveFile(File srcFile, File destFile) throws IOException {
        return copyOrMoveFile(srcFile, destFile, true);
    }

    /**
     * 获取文件行数
     *
     * @param filePath 文件路径
     * @return 文件行数
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static int getFileLines(String filePath) throws IOException {
        return getFileLines(getFile(filePath));
    }

    /**
     * 获取文件行数
     *
     * @param file 文件
     * @return 文件行数
     * @throws IOException io异常
     * @since 2.1.1
     */
    public static int getFileLines(File file) throws IOException {
        int count = 1;
        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[1024];
            int readChars;
            while ((readChars = is.read(buffer, 0, 1024)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (buffer[i] == '\n') {
                        ++count;
                    }
                }
            }
        }
        return count;
    }


}
