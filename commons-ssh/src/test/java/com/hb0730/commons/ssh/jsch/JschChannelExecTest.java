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
        result = channelExec.exec("//", Charset.defaultCharset());
        System.out.println(result);
        result = channelExec.exec("exit", Charset.defaultCharset());
        System.out.println(result);

    }
}
