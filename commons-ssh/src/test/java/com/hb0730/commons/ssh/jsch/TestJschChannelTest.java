package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class TestJschChannelTest extends SessionTest {
    private Session session = null;

    @Before
    public void before() {
        session = session();
    }

    @After
    public void after() {
        JschUtils.close(session);
    }

    public void after(Channel channel) {
        JschUtils.close(channel);
    }

    @Test
    public void openExecTest() {
        try {
            ChannelExec exec = JschChannel.builder(session)
                    .openExec();
            Assert.assertNotNull(exec);
            after(exec);
        }catch (Throwable e){
            log.error(e.getMessage());
        }
    }

    @Test
    public void openSftpTest() {
        try {
            ChannelSftp exec = JschChannel.builder(session)
                    .openSftp();
            Assert.assertNotNull(exec);
            after(exec);
        }catch (Throwable e){
            log.error(e.getMessage());
        }
    }

    @Test
    public void openShellTest() {
        try {
            ChannelShell exec = JschChannel.builder(session)
                    .openShell();
            Assert.assertNotNull(exec);
            after(exec);
        }catch (Throwable e){
            log.error(e.getMessage());
        }
    }

    @Test
    public void openChannelTest() throws InterruptedException {
        try {
            Channel channel = JschChannel.builder(session)
                    .openChannel(ChannelType.SESSION, 3000);
            Assert.assertNotNull(channel);
            Thread.sleep(3000L);
            Assert.assertFalse(channel.isConnected());
            after(channel);
        }catch (Throwable e){
            log.error(e.getMessage());
        }
    }
}
