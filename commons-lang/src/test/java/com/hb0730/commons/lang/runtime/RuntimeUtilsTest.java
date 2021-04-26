package com.hb0730.commons.lang.runtime;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;

@Slf4j
public class RuntimeUtilsTest {
    private Process process;

    @After
    public void after() {
        RuntimeUtils.destroy(process);
    }

    @Test
    public void execTest() {
        try {
            String result = RuntimeUtils.exec("java -version");
            Assert.assertNotNull(result);
            log.info(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testExecTest() {
        try {
            String result = RuntimeUtils.exec(Charset.forName("GBK"), "cmd /c java -?");
            Assert.assertNotNull(result);
            log.info(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void execProcessTest() {
        try {
            process = RuntimeUtils.execProcess("cmd /c echo hell world");
            String result = RuntimeUtils.getResult(process, Charset.forName("GBK"));
            log.info(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testExecProcessTest() {
        try {
            process = RuntimeUtils.execProcess(new File("D:/test"), "cmd /c dir");
            String result = RuntimeUtils.getResult(process, Charset.forName("GBK"));
            log.info(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testExecProcess1Test() {
        try {
            process = RuntimeUtils.execProcess(new String[]{"test=hello world"}, "cmd /c echo %test%");
            String result = RuntimeUtils.getResult(process, Charset.forName("GBK"));
            log.info(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testExecProcess2Test() {
        try {
            process = RuntimeUtils.execProcess(new String[]{"test=hello world"}, new File("D:\\test"), "cmd /c echo %test% >> test.txt");
            String result = RuntimeUtils.getResult(process, Charset.forName("GBK"));
            log.info(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void getResultTest() {
        try {
            process = RuntimeUtils.execProcess("cmd /c echo hell world");
            String result = RuntimeUtils.getResult(process);
            log.info(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testGetResultTest() {
        try {
            process = RuntimeUtils.execProcess("cmd /c echo hell world");
            String result = RuntimeUtils.getResult(process, Charset.defaultCharset());
            log.info(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void getErrorResultTest() {
        try {
            process = RuntimeUtils.execProcess("cmd /c echo hell world");
            String result = RuntimeUtils.getErrorResult(process);
            log.info(result);
            process = RuntimeUtils.execProcess("cmd /c ccc");
            result = RuntimeUtils.getErrorResult(process);
            log.info(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testGetErrorResultTest() {
        try {
            process = RuntimeUtils.execProcess("cmd /c ccc");
            String result = RuntimeUtils.getErrorResult(process, Charset.defaultCharset());
            log.info(result);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void getUsableMemoryTest() {
        Assert.assertTrue(RuntimeUtils.getUsableMemory() > 0);
    }
}
