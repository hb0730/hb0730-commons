package com.hb0730.commons.ssh.jsch;

import com.hb0730.commons.lang.io.FileUtils;
import com.hb0730.commons.lang.io.ResourcesUtils;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class JschChannelSftpTest extends SessionTest {
    private Session session;
    JschChannelSftp sftp = null;

    @Before
    public void before() {
        try {
            session = session();
            sftp = JschChannelSftp.builder(session);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @After
    public void after() {
        try {
            sftp.close();
            JschUtils.close(session);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void cdTest() {
        try {
            sftp.cd("/usr/local");
            String pwd = sftp.pwd();
            System.out.println(pwd);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void pwdTest() {
        try {
            String pwd = sftp.pwd();
            System.out.println(pwd);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void mkdirTest() {
        try {
            sftp.mkdir("/usr/local/docker");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void chmodTest() {
        try {
            sftp.chmod(777, "/usr/local/docker");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void lsTest() {
        try {
            sftp.cd("/usr/local/docker");
            List<ChannelSftp.LsEntry> ls = sftp.ls("./");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void rmTest() {
        try {
            //删除文件
            sftp.rm("/root/test/test1/test1.txt");
            //文件夹
            sftp.rm("/root/test/test1");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void rmdirTest() {
        try {
            //空文件夹，不能嵌套
            sftp.rmdir("/root/test/test3");
            //文件夹 有文件
            sftp.rmdir("/root/test/");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void renameTest() {
        try {
            //重命名文件
            sftp.rename("/root/test/test1/test2.txt", "/root/test/test1/test1.txt");
            //重命名空文件夹
            sftp.rename("/root/test/test3/test1", "/root/test/test3/test3");
            //重命名文件夹
            sftp.rename("/root/test", "/root/test.back1");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void putTest() throws IOException {
        try {
            URL url = ResourcesUtils.getResource("test.txt");
            File file = FileUtils.getFile(url.getFile());
            FileInputStream stream = FileUtils.openInputStream(file);
            //存在的目录
            sftp.put(stream, "/root/test.txt");
            //不存在的目录
            sftp.put(stream, "/root/test/test.txt");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void putExtTest() throws IOException {
        try {
            URL url = ResourcesUtils.getResource("test.txt");
            File file = FileUtils.getFile(url.getFile());
            FileInputStream stream = FileUtils.openInputStream(file);
            sftp.putExt(stream, "/root/test/test/test.txt");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void mkdirExtTest() {
        try {
            sftp.mkdirExt("/root/test/test");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void putExt2Test() throws IOException {
        try {
            URL url = ResourcesUtils.getResource("test.txt");
            File file = FileUtils.getFile(url.getFile());
            FileInputStream stream = FileUtils.openInputStream(file);
            sftp.putExt(stream, "/root/test/test", "test.txt");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void getForDirTest() {
        try {
            Map<String, InputStream> dirs = sftp.getForDir("/root/test/test");
            Assert.assertNotNull(dirs);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }
}
