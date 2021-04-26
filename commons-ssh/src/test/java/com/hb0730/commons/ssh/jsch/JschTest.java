package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;

@Slf4j
public class JschTest extends SessionTest {
    private Session session;

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

    public void ExcSftpTest() {
        try {
            JschChannelSftp sftp = JschChannelSftp.builder(session);
            sftp.cd("/local/docker");
            after(sftp.channelSftp());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }
}
