package com.hb0730.commons.lang.runtime;

import com.hb0730.commons.lang.constants.SystemConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

@Slf4j
public class SystemUtilsTest {

    @Test
    public void getPidTest() {
        int pid = SystemUtils.getPid();
        Assert.assertTrue("pid不存在", (pid > -1));
        log.info(pid + "");
    }

    @Test
    public void getPropertyTest() {
        String property = SystemUtils.getProperty(SystemConst.VERSION);
        Assert.assertNotNull(property);
        log.info(property);
    }

    @Test
    public void getLineSeparatorTest() {
        String lineSeparator = SystemUtils.getLineSeparator();
        Assert.assertNotNull(lineSeparator);
        log.info(lineSeparator);
    }

    @Test
    public void getRuntimeArgsTest() {
        List<String> runtimeArgs = SystemUtils.getRuntimeArgs();
        Assert.assertNotNull(runtimeArgs);
        log.info(runtimeArgs.toString());
    }

    @Test
    public void getUpTimeTest() {
        long upTime = SystemUtils.getUpTime();
        Assert.assertNotNull(upTime);
        log.info(upTime + "");
    }

    @Test
    public void getCoresTest() {
        int cores = SystemUtils.getCores();
        Assert.assertNotNull(cores);
        log.info("" + cores);
    }

    @Test
    public void addShutdownHookTest() {
        SystemUtils.addShutdownHook(new Runnable() {
            @Override
            public void run() {
                log.info("即将关闭");
            }
        });
    }

    @Test
    public void getPathSeparatorTest() {
        String separator = SystemUtils.getPathSeparator();
        Assert.assertNotNull(separator);
    }
}
