package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

@Slf4j
public class JschUtilsTest extends JschChannelTest {
    ChannelShell shell = null;

    @Before
    public void before() {
        try {
            shell = openShellTest();
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @After
    public void after() {
        JschUtils.close(shell);
        JschUtils.close(session);
    }


    @Test
    public void testRead1Test() throws JSchException {
        try {
            JschUtils.connect(shell);
            JschUtils.write(shell, "cd /root\nls\n");
            //阻塞
            JschUtils.read(shell, new Consumer<byte[]>() {
                @Override
                public void accept(byte[] bytes) {
                    System.out.println(new String(bytes));
                    return;
                }
            });
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

}
