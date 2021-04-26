package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
@Slf4j
public class TestSessionTest {
    Session session;

    @After
    public void after() {
        JschUtils.close(session);
    }

    @Test
    public void passwordTest() {
        try {
            session = JschSessionBuilder.builder()
                    .sshHost("192.168.52.128")
                    .sshUser("root")
                    .sshPort(22)
                    .sshPass("123456")
                    .sessionTimeout(100)
                    .build();
            Assert.assertNotNull(session);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void sshKeyTest() {
        try {
            session = JschSessionBuilder.builder()
                    .sshHost("192.168.52.128")
                    .sshUser("root")
                    .sshPort(22)
                    .privateKeyPath("~/.ssh/id_rsa")
                    .sessionTimeout(100)
                    .build();
            Assert.assertNotNull(session);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }


}
