package com.hb0730.commons.ssh.jsch;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.constants.PathConst;
import com.hb0730.commons.lang.constants.PunctuationConst;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * <a href="http://www.jcraft.com/jsch">JSch</a> {@link com.jcraft.jsch.ChannelSftp}相关操作
 *
 * @author bing_huang
 * @see <a href="http://epaul.github.io/jsch-documentation/javadoc/com/jcraft/jsch/ChannelSftp.html">API</a>
 * @since 2.1.2
 */
public class JschChannelSftp {
    private final ChannelSftp sftp;

    /**
     * 有参构造,自动链接{@link ChannelSftp#connect()}<br>
     * 1. {@link Session}不为空 <br>
     * 2. {@link Session}已链接 <br>
     *
     * @param session {@link Session}
     * @throws JschRuntimeException session为空或者链接关闭
     * @see JschChannel#openSftp()
     */
    public JschChannelSftp(Session session) {
        if (null == session) {
            throw new JschRuntimeException("JSch session is null");
        }
        if (!session.isConnected()) {
            throw new JschRuntimeException("JSch session is Close null");
        }
        sftp = JschChannel.builder(session).openSftp();
        try {
            sftp.connect();
        } catch (JSchException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 创建{@link JschChannelSftp}
     *
     * @param session {@link Session}
     * @return {@link JschChannelSftp}
     */
    public static JschChannelSftp builder(Session session) {
        return new JschChannelSftp(session);
    }

    /**
     * 获取当前{@link Session}的{@link ChannelSftp}
     *
     * @return {@link ChannelSftp}
     */
    public ChannelSftp channelSftp() {
        return sftp;
    }

    /**
     * 更改当前的远程目录
     *
     * @param path 目录
     */
    public void cd(String path) {
        try {

            sftp.cd(path);
        } catch (SftpException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 以绝对形式返回当前的远程目录。
     *
     * @return 当前远程目录
     */
    public String pwd() {
        try {
            return sftp.pwd();
        } catch (SftpException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 创建一个新的远程目录
     *
     * @param path path
     */
    public void mkdir(String path) {
        try {
            sftp.mkdir(path);
        } catch (SftpException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 创建远程目录
     *
     * @param path 目录，如果不存在则创建
     */
    public void mkdirExt(final String path) {
        final String[] paths = path.split(PathConst.ROOT_PATH);
        if (paths.length <= 0) {
            return;
        }
        StringJoiner joiner = new StringJoiner(PathConst.ROOT_PATH);
        for (String dir : paths) {
            if (StringUtils.isBlank(dir)) {
                joiner.add(PunctuationConst.EMPTY);
                continue;
            }
            joiner.add(dir);
            String newPath = joiner.toString();
            try {
                cd(newPath);
            } catch (JschRuntimeException e) {
                mkdir(newPath);
            }
        }
    }

    /**
     * 删除一个或几个文件。
     *
     * @param path 路径
     */
    public void rm(String path) {
        try {
            sftp.rm(path);
        } catch (SftpException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 删除一个或几个远程目录, <b>不太理想,不太建议使用</b>
     *
     * @param path 目录
     */
    public void rmdir(String path) {
        try {
            sftp.rmdir(path);
        } catch (SftpException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 重命名文件或目录
     *
     * @param oldPath 旧路径
     * @param newPath 新路径
     */
    public void rename(String oldPath, String newPath) {
        try {
            sftp.rename(oldPath, newPath);
        } catch (SftpException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 更改一个或几个远程文件的权限。
     *
     * @param permissions 权限
     * @param path        文件路径
     */
    public void chmod(int permissions, String path) {
        try {
            sftp.chmod(permissions, path);
        } catch (SftpException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 列出远程目录的内容。
     *
     * @param path 路径
     */
    public List<ChannelSftp.LsEntry> ls(String path) {
        final List<ChannelSftp.LsEntry> rptFiles = new LinkedList<>();
        ChannelSftp.LsEntrySelector selector = entry -> {
            rptFiles.add(entry);
            return ChannelSftp.LsEntrySelector.CONTINUE;
        };
        try {
            sftp.ls(path, selector);
            return rptFiles;
        } catch (SftpException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 上传文件,覆盖模式
     *
     * @param src 文件input流
     * @param dst 上传的<p>全路径</p>，列如：<code>/root/test.txt</code>
     */
    public void put(InputStream src, final String dst) {
        try {
            sftp.put(src, dst, ChannelSftp.OVERWRITE);
        } catch (SftpException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 上传文件，覆盖式
     *
     * @param src  文件input流
     * @param path 上传的<b>全路径</b>，列如：<code>/root/test.txt</code>，如果不存在，自动创建
     */
    public void putExt(InputStream src, final String path) {
        final String dst = path.substring(0, path.lastIndexOf(PathConst.ROOT_PATH));
        final String filename = path.substring(path.lastIndexOf(PathConst.ROOT_PATH));
        putExt(src, dst, filename);

    }

    /**
     * 上传文件，覆盖式,上传路径为{@code dst+"/"+filename}
     *
     * @param src      文件input流
     * @param dst      文件路径，<b>不存在，默认自动创建</b>
     * @param filename 文件名称
     */
    public void putExt(InputStream src, final String dst, final String filename) {
        mkdirExt(dst);
        String newPath = dst + PathConst.ROOT_PATH + filename;
        put(src, newPath);
    }

    /**
     * 开始下载单个文件作为InputStream。
     *
     * @param src 文件路径
     * @return InputStream
     */
    public InputStream get(String src) {
        try {
            return sftp.get(src);
        } catch (SftpException e) {
            throw new JschRuntimeException(e);
        }
    }

    /**
     * 下载当前文件夹下所有文件
     *
     * @param dir 目录
     * @return 文件名对应的输入流
     */
    public Map<String, InputStream> getForDir(String dir) {
        List<ChannelSftp.LsEntry> dirList = ls(dir);
        List<String> filePaths = new ArrayList<>();
        for (ChannelSftp.LsEntry lsEntry : dirList) {
            SftpATTRS attrs = lsEntry.getAttrs();
            if (!attrs.isDir()) {
                filePaths.add(lsEntry.getFilename());
            }
        }
        Map<String, InputStream> fileMap = new HashMap<>(filePaths.size());
        for (String filename : filePaths) {
            InputStream inputStream = get(dir + PathConst.ROOT_PATH + filename);
            fileMap.put(filename, inputStream);
        }
        return fileMap;
    }


    /**
     * 关闭{@link ChannelSftp}链接
     *
     * @see ChannelSftp#disconnect()
     */
    public void close() {
        JschUtils.close(sftp);
    }
}
