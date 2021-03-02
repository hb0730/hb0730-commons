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

    @Test
    public void copyTest() throws IOException {
        String message = "测试";
        ByteArrayInputStream in = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        long copy = IOUtils.copy(in, out);
        String result = out.toString(StandardCharsets.UTF_8.name());
        Assert.assertEquals(message, result);
    }

    @Test
    public void copy2Test() throws IOException {
        String message = "测试";
        ByteArrayInputStream in = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        long copy = IOUtils.copy(in, null);
    }

    @Test
    public void copy3Test() throws IOException {
        IOUtils.copy(null, null);
    }

    @Test
    public void testCopyTest() throws IOException {
        String message = "测试";
        ByteArrayInputStream in = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(in, out, 2048);
        String result = out.toString(StandardCharsets.UTF_8.name());
        Assert.assertEquals(message, result);
    }

    @Test
    public void testCopy2Test() throws IOException {
        String message = "测试";
        ByteArrayInputStream in = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(in, out, 0);
        String result = out.toString(StandardCharsets.UTF_8.name());
        Assert.assertEquals(message, result);
    }

    @Test
    public void testCopy3Test() throws IOException {
        String message = "测试";
        ByteArrayInputStream in = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        IOUtils.copy(in, null, 0);
    }

    @Test
    public void testCopy4Test() throws IOException {
        String message = "测试";
        ByteArrayInputStream in = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        IOUtils.copy(null, null, 0);
    }

    @Test
    public void readTest() throws IOException {
        String message = "测试";
        ByteArrayInputStream in = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        String result = IOUtils.read(in);
        Assert.assertEquals(message, result);

    }

    @Test
    public void testReadTest() throws IOException {
        String message = "测试";
        ByteArrayInputStream in = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        String result = IOUtils.read(in, Charset.defaultCharset());
        Assert.assertEquals(message, result);
        result = IOUtils.read(in, null);
        Assert.assertEquals(message, result);
        result = IOUtils.read(null, null);
        Assert.assertEquals(message, result);
    }

    @Test
    public void readBytesTest() throws IOException {
        String message = "测试";
        ByteArrayInputStream in = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = IOUtils.readBytes(in);
        Assert.assertNotNull(bytes);
        bytes = IOUtils.readBytes(null);
        Assert.assertNotNull(bytes);
    }
}
