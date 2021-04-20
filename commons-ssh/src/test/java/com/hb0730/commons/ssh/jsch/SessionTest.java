package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.Session;

/**
 *
 */
public class SessionTest {

    public Session session() {
        return JschSessionBuilder.builder()
                .sshHost("192.168.52.128")
                .sshUser("root")
                .sshPort(22)
                .sshPass("123456")
                .build();
    }
}
