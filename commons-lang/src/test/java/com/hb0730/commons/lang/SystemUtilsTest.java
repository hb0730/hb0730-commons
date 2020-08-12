package com.hb0730.commons.lang;

import com.hb0730.commons.lang.constants.SystemConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

@Slf4j
public class SystemUtilsTest {

    @Test
    public void getPropertyTest() {
        String version = SystemUtils.getProperty(SystemConst.VERSION);
        Assert.assertNotNull(version);
        log.info(version);
    }

    @Test
    public void getLineSeparatorTest() {
        String separator = SystemUtils.getLineSeparator();
        Assert.assertNotNull(separator);
        log.info(separator);
    }

    @Test
    public void getRuntimeArgsTest() {
        List<String> args = SystemUtils.getRuntimeArgs();
        Assert.assertNotNull(args);
        log.info(args.toString());
    }
}
