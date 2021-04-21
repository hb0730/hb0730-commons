package com.hb0730.commons.ssh.jsch;

import com.hb0730.commons.lang.io.FileUtils;
import com.hb0730.commons.lang.io.ResourcesUtils;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class JschChannelSftpTest extends SessionTest {
    private Session session;
    JschChannelSftp sftp = null;

    @Before
    public void before() {
        session = session();
        sftp = JschChannelSftp.builder(session);
    }

    @After
    public void after() {
        sftp.close();
        JschUtils.close(session);
    }

    @Test
    public void cdTest() {
        sftp.cd("/usr/local");
        String pwd = sftp.pwd();
        System.out.println(pwd);
    }

    @Test
    public void pwdTest() {
        String pwd = sftp.pwd();
        System.out.println(pwd);
    }

    @Test
    public void mkdirTest() {
        sftp.mkdir("/usr/local/docker");
    }

    @Test
    public void chmodTest() {
        sftp.chmod(777, "/usr/local/docker");
    }

    @Test
    public void lsTest() {
        sftp.cd("/usr/local/docker");
        List<ChannelSftp.LsEntry> ls = sftp.ls("./");
    }

    @Test
    public void rmTest() {
        //删除文件
        sftp.rm("/root/test/test1/test1.txt");
        //文件夹
        sftp.rm("/root/test/test1");
    }

    @Test
    public void rmdirTest() {
        //空文件夹，不能嵌套
        sftp.rmdir("/root/test/test3");
        //文件夹 有文件
        sftp.rmdir("/root/test/");
    }

    @Test
    public void renameTest() {
        //重命名文件
        sftp.rename("/root/test/test1/test2.txt", "/root/test/test1/test1.txt");
        //重命名空文件夹
        sftp.rename("/root/test/test3/test1", "/root/test/test3/test3");
        //重命名文件夹
        sftp.rename("/root/test", "/root/test.back1");
    }

    @Test
    public void putTest() throws IOException {
        URL url = ResourcesUtils.getResource("test.txt");
        File file = FileUtils.getFile(url.getFile());
        FileInputStream stream = FileUtils.openInputStream(file);
        //存在的目录
        sftp.put(stream, "/root/test.txt");
        //不存在的目录
        sftp.put(stream, "/root/test/test.txt");
    }

    @Test
    public void putExtTest() throws IOException {
        URL url = ResourcesUtils.getResource("test.txt");
        File file = FileUtils.getFile(url.getFile());
        FileInputStream stream = FileUtils.openInputStream(file);
        sftp.putExt(stream, "/root/test/test/test.txt");
    }

    @Test
    public void mkdirExtTest() {
        sftp.mkdirExt("/root/test/test");
    }

    @Test
    public void putExt2Test() throws IOException {
        URL url = ResourcesUtils.getResource("test.txt");
        File file = FileUtils.getFile(url.getFile());
        FileInputStream stream = FileUtils.openInputStream(file);
        sftp.putExt(stream, "/root/test/test", "test.txt");
    }

    @Test
    public void getForDirTest() {
        Map<String, InputStream> dirs = sftp.getForDir("/root/test/test");
        Assert.assertNotNull(dirs);
    }
}
