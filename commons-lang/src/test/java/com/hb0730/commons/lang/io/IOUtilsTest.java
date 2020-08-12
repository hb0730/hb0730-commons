package com.hb0730.commons.lang.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class IOUtilsTest {

    @Test
    public void writeTest() throws IOException {
        String data = "测试";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.write(bytes, outputStream);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        IOUtils.closeQuietly(outputStream);
        String dataBytes = outputStream.toString();
        log.info(dataBytes);


        IOUtils.write(bytes, null);

        IOUtils.write(null, null);

        IOUtils.write(null, outputStream);

    }

    @Test
    public void testWriteTest() throws IOException {
        String charset = "UTF-8";
        String data = "测试";
        char[] chars = data.toCharArray();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.write(chars, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        String outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        charset = null;
        outputStream = new ByteArrayOutputStream();
        IOUtils.write(chars, outputStream, charset);
        outputStream.flush();
        outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        outputStream = null;
        IOUtils.write(chars, outputStream, charset);

        chars = null;
        outputStream = new ByteArrayOutputStream();
        IOUtils.write(chars, outputStream, charset);
        outputStream.flush();
        outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        chars = null;
        outputStream = null;
        IOUtils.write(chars, outputStream, charset);

    }

    @Test
    public void testWrite1Test() throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        String data = "测试";
        char[] chars = data.toCharArray();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.write(chars, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        String outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        charset = null;
        chars = data.toCharArray();
        outputStream = new ByteArrayOutputStream();
        IOUtils.write(chars, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        charset = StandardCharsets.UTF_8;
        chars = null;
        outputStream = new ByteArrayOutputStream();
        IOUtils.write(chars, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);


        chars = data.toCharArray();
        outputStream = null;
        IOUtils.write(chars, outputStream, charset);


        chars = null;
        outputStream = null;
        IOUtils.write(chars, outputStream, charset);
    }

    @Test
    public void testWrite2Test() throws IOException {
        String data = "测试";
        String charset = "utf-8";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.write(data, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        String outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        data = "测试";
        charset = null;
        outputStream = new ByteArrayOutputStream();
        IOUtils.write(data, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        data = null;
        outputStream = new ByteArrayOutputStream();
        IOUtils.write(data, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        data = "测试";
        outputStream = null;
        IOUtils.write(data, outputStream, charset);

        data = null;
        outputStream = null;
        IOUtils.write(data, outputStream, charset);
    }

    @Test
    public void testWrite3Test() throws IOException {
        StringBuilder data = new StringBuilder("测试");
        Charset charset = StandardCharsets.UTF_8;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.write(data, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        String outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        data = new StringBuilder("测试");
        charset = null;
        outputStream = new ByteArrayOutputStream();
        IOUtils.write(data, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        data = null;
        outputStream = new ByteArrayOutputStream();
        IOUtils.write(data, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        data = new StringBuilder("测试");
        outputStream = null;
        IOUtils.write(data, outputStream, charset);

        data = null;
        outputStream = null;
        IOUtils.write(data, outputStream, charset);
    }

    @Test
    public void testWrite4Test() throws IOException {
        String data = "测试";
        Charset charset = StandardCharsets.UTF_8;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.write(data, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        String outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        data = "测试";
        charset = null;
        outputStream = new ByteArrayOutputStream();
        IOUtils.write(data, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        data = null;
        outputStream = new ByteArrayOutputStream();
        IOUtils.write(data, outputStream, charset);
        Assert.assertNotNull(outputStream);
        outputStream.flush();
        outData = outputStream.toString();
        IOUtils.closeQuietly(outputStream);
        log.info(outData);

        data = "测试";
        outputStream = null;
        IOUtils.write(data, outputStream, charset);

        data = null;
        outputStream = null;
        IOUtils.write(data, outputStream, charset);
    }

    @Test
    public void closeQuietlyTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.closeQuietly(outputStream);
        Assert.assertNotNull(outputStream);
        outputStream = null;
        IOUtils.closeQuietly(outputStream);
    }

    @Test
    public void testCloseQuietlyTest() {
        String data = "测试";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());
        IOUtils.closeQuietly(inputStream);
        Assert.assertNotNull(inputStream);

        inputStream = null;
        IOUtils.closeQuietly(inputStream);
    }
}
