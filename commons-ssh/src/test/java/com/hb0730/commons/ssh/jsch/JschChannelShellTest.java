package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

@Slf4j
public class JschChannelShellTest extends JschChannelTest {
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
    public void sessionConstructorTest() throws InterruptedException {
        try {
            JschChannelShell shell = JschChannelShell.builder(session);
            String result = "";
            result = shell.exec("cd /root\nls\n", StandardCharsets.UTF_8);
            Assert.assertNotNull(result);
            System.out.println(result);

            Thread.sleep(3000L);
            result = shell.exec("cd /usr/local/docker\nmkdir test\nls\n", StandardCharsets.UTF_8);
            Assert.assertNotNull(result);
            System.out.println(result);

            Thread.sleep(3000L);
            result = shell.exec("cd /usr/local/docker/test\nmkdir test1\ncd /usr/local/docker/test/test1\nls\n", StandardCharsets.UTF_8);
            Assert.assertNotNull(result);
            System.out.println(result);

            Thread.sleep(3000L);
            result = shell.exec("cd /usr/local/docker\nrm -rf test\nls\n", StandardCharsets.UTF_8);
            Assert.assertNotNull(result);
            System.out.println(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void shellConstructorTest() throws InterruptedException {
        try {
            //无法多次调用exec
            JschChannelShell shell = JschChannelShell.builder(session);
            JschChannelShell channelShell = JschChannelShell.builder(shell.openShell());
            String result = "";
            result = channelShell.exec("cd /root\nls\n");
            Assert.assertNotNull(result);
            System.out.println(result);

            Thread.sleep(3000L);
            result = channelShell.exec("cd /usr/local/docker\nls\n");
            Assert.assertNotNull(result);
            System.out.println(result);
        }catch (Throwable e){
            log.error(e.getMessage());
        }
    }

    @Test
    public void testOpenShellTest() {
        try {
            ChannelShell shell = JschChannelShell.builder(session).openShell(0);
            Assert.assertNotNull(shell);
            JschUtils.close(shell);
        }catch (Throwable e){
            log.error(e.getMessage());
        }
    }
}
