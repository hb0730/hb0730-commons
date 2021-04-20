package com.hb0730.commons.ssh.jsch;

/**
 * JSch Channel TYpe
 *
 * @author bing_huang
 * @see com.jcraft.jsch.Channel
 * @since 2.1.2
 */
public enum ChannelType {
    /**
     * session
     */
    SESSION("session"),
    /**
     * shell
     */
    SHELL("shell"),
    /**
     * exec
     */
    EXEC("exec"),
    /**
     * x11
     */
    X11("x11"),
    /**
     * agent forwarding
     */
    AGENT_FORWARDING("auth-agent@openssh.com"),
    /**
     * direct tcpip
     */
    DIRECT_TCPIP("direct-tcpip"),
    /**
     * forwarded tcpip
     */
    FORWARDED_TCPIP("forwarded-tcpip"),
    /**
     * sftp
     */
    SFTP("sftp"),
    /**
     * subsystem
     */
    SUBSYSTEM("subsystem"),
    ;
    private final String value;

    ChannelType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
