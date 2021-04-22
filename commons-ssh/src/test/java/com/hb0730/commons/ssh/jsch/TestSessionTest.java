package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class TestSessionTest {
    Session session;

    @After
    public void after() {
        JschUtils.close(session);
    }

    @Test
    public void passwordTest() {
        session = JschSessionBuilder.builder()
                .sshHost("192.168.52.128")
                .sshUser("root")
                .sshPort(22)
                .sshPass("123456")
                .build();
        Assert.assertNotNull(session);
    }

    @Test
    public void sshKeyTest() {
        session = JschSessionBuilder.builder()
                .sshHost("192.168.52.128")
                .sshUser("root")
                .sshPort(22)
                .privateKeyPath("~/.ssh/id_rsa")
                .build();
        Assert.assertNotNull(session);
    }


}
