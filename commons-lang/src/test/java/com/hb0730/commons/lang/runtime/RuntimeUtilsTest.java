package com.hb0730.commons.lang.runtime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;

public class RuntimeUtilsTest {
    private Process process;

    @After
    public void after() {
        RuntimeUtils.destroy(process);
    }

    @Test
    public void execTest() {
        String result = RuntimeUtils.exec("java -version");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void testExecTest() {
        String result = RuntimeUtils.exec(Charset.forName("GBK"), "cmd /c java -?");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void execProcessTest() {
        process = RuntimeUtils.execProcess("cmd /c echo hell world");
        String result = RuntimeUtils.getResult(process, Charset.forName("GBK"));
        System.out.println(result);
    }

    @Test
    public void testExecProcessTest() {
        process = RuntimeUtils.execProcess(new File("D:/test"), "cmd /c dir");
        String result = RuntimeUtils.getResult(process, Charset.forName("GBK"));
        System.out.println(result);

    }

    @Test
    public void testExecProcess1Test() {
        process = RuntimeUtils.execProcess(new String[]{"test=hello world"}, "cmd /c echo %test%");
        String result = RuntimeUtils.getResult(process, Charset.forName("GBK"));
        System.out.println(result);

    }

    @Test
    public void testExecProcess2Test() {
        process = RuntimeUtils.execProcess(new String[]{"test=hello world"}, new File("D:\\test"), "cmd /c echo %test% >> test.txt");
        String result = RuntimeUtils.getResult(process, Charset.forName("GBK"));
        System.out.println(result);
    }

    @Test
    public void getResultTest() {
        process = RuntimeUtils.execProcess("cmd /c echo hell world");
        String result = RuntimeUtils.getResult(process);
        System.out.println(result);
    }

    @Test
    public void testGetResultTest() {
        process = RuntimeUtils.execProcess("cmd /c echo hell world");
        String result = RuntimeUtils.getResult(process, Charset.defaultCharset());
        System.out.println(result);
    }

    @Test
    public void getErrorResultTest() {
        process = RuntimeUtils.execProcess("cmd /c echo hell world");
        String result = RuntimeUtils.getErrorResult(process);
        System.out.println(result);
        process = RuntimeUtils.execProcess("cmd /c ccc");
        result = RuntimeUtils.getErrorResult(process);
        System.out.println(result);
    }

    @Test
    public void testGetErrorResultTest() {
        process = RuntimeUtils.execProcess("cmd /c ccc");
        String result = RuntimeUtils.getErrorResult(process, Charset.defaultCharset());
        System.out.println(result);
    }

    @Test
    public void getUsableMemoryTest() {
        Assert.assertTrue(RuntimeUtils.getUsableMemory() > 0);
    }
}
