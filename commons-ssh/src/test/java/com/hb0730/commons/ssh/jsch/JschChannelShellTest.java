package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;

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
    public void execTest() {
        String result = JschChannelShell.builder(session)
                .exec("cd /root\nls\nexit\n", Charset.defaultCharset());
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 交互
     */
    @Test
    public void shellTest() throws InterruptedException {
    }
}
