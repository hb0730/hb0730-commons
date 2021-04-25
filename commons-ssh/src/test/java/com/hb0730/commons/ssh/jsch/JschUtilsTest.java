package com.hb0730.commons.ssh.jsch;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

public class JschUtilsTest extends JschChannelTest {
    ChannelShell shell = null;

    @Before
    public void before() {
        shell = openShellTest();
    }

    @After
    public void after() {
        JschUtils.close(shell);
        JschUtils.close(session);
    }


    @Test
    public void testRead1Test() throws JSchException {
        JschUtils.connect(shell);
        JschUtils.write(shell, "cd /root\nls\n");
        //阻塞
        JschUtils.read(shell, new Consumer<byte[]>() {
            @Override
            public void accept(byte[] bytes) {
                System.out.println(new String(bytes));
                System.exit(0);
            }
        });
    }

}
