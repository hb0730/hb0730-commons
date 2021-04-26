package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Slf4j
public class SessionTest {

    public Session session() {

        try {
            return JschSessionBuilder.builder()
                    .sshHost("192.168.245.128")
                    .sshUser("root")
                    .sshPort(22)
                    .sshPass("123456")
                    .build();
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
