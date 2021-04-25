package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;

public class JschChannelExecTest extends JschChannelTest {
    private Session session;

    @Before
    public void before() {
        session = session();
    }

    @After
    public void after() {
        JschUtils.close(session);
    }

    @Test
    public void execTest() throws JSchException {
        JschChannelExec channelExec = JschChannelExec.builder(session);
        String result = channelExec.exec("echo 'hello word'", Charset.defaultCharset());
        System.out.println(result);
        result = channelExec.exec("ps -ef | grep ssh", Charset.defaultCharset());
        System.out.println(result);
        result = channelExec.exec("ls", Charset.defaultCharset());
        System.out.println(result);
        result = channelExec.exec("mkdir test", Charset.defaultCharset());
        System.out.println(result);
        result = channelExec.exec("ls", Charset.defaultCharset());
        System.out.println(result);
        result = channelExec.exec("rm -rf test", Charset.defaultCharset());
        System.out.println(result);
        result = channelExec.exec("ls", Charset.defaultCharset());
        System.out.println(result);
        result = channelExec.exec("exit", Charset.defaultCharset());
        System.out.println(result);

    }

    @Test
    public void execTest2() {
        JschChannelExec channelExec = JschChannelExec.builder(session);
        String result = channelExec.exec("echo 'hello word'");
        System.out.println(result);
        result = channelExec.exec("ps -ef | grep ssh");
        System.out.println(result);
        result = channelExec.exec("ls");
        System.out.println(result);
        result = channelExec.exec("mkdir test");
        System.out.println(result);
        result = channelExec.exec("ls");
        System.out.println(result);
        result = channelExec.exec("rm -rf test");
        System.out.println(result);
        result = channelExec.exec("ls");
        System.out.println(result);
        result = channelExec.exec("exit");
        System.out.println(result);
    }
}
