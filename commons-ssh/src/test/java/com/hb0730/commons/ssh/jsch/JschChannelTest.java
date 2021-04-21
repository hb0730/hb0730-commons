package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;

public class JschChannelTest extends SessionTest {
    protected Session session;

    public ChannelExec openExecTest() {
        session = session();
        return JschChannel.builder(session)
                .openExec();
    }

    public ChannelSftp openSftpTest() {
        session = session();
        return JschChannel.builder(session)
                .openSftp();
    }

    public ChannelShell openShellTest() {
        session = session();
        return JschChannel.builder(session)
                .openShell();
    }

}
