package com.hb0730.commons.lang.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileUtilsTest {
    private static final String PATH = "E:\\IdeaWork\\customer-work\\hb0730-commons\\commons-lang\\src\\test\\java\\com\\hb0730\\commons\\lang\\io\\test.txt";

    @Test
    public void writeTest() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        StringBuilder content = null;
        String encoding = null;
        FileUtils.write(file, content, encoding);

        content = null;
        FileUtils.write(file, content, encoding);


        encoding = "utf-8";
        content = new StringBuilder("测试 cccc");
        FileUtils.write(file, content, encoding);

        file = null;
        FileUtils.write(file, content, encoding);
    }

    @Test
    public void testWriteTest() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        String encoding = "utf-8";
        StringBuilder content = new StringBuilder("cccesasdas很好");
        FileUtils.write(file, content, encoding, true);
    }

    @Test
    public void testWrite1Test() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        Charset encoding = StandardCharsets.UTF_8;
        StringBuilder content = new StringBuilder("cccesasdas很好");
        FileUtils.write(file, content, encoding);
    }

    @Test
    public void testWrite2Test() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        Charset encoding = StandardCharsets.UTF_8;
        StringBuilder content = new StringBuilder("cccesasdas很好");
        FileUtils.write(file, content, encoding, true);
    }

    @Test
    public void writeStringTest() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        String content = "测试";
        String encoding = "utf-8";
        FileUtils.writeString(file, content, encoding);
    }

    @Test
    public void testWriteStringTest() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        String content = "测gggg试";
        String encoding = "utf-8";
        FileUtils.writeString(file, content, encoding, true);
    }

    @Test
    public void testWriteString1Test() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        String content = "测gggg试";
        Charset encoding = StandardCharsets.UTF_8;
        FileUtils.writeString(file, content, encoding, false);
    }

    @Test
    public void writeByteArrayTest() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        String content = "测gggg试";
        FileUtils.writeByteArray(file, content.getBytes());
    }

    @Test
    public void testWriteByteArrayTest() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        String content = "asdas好";
        FileUtils.writeByteArray(file, content.getBytes(), true);
    }

    @Test
    public void testWriteByteArray1Test() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        String content = "asdas";
        FileUtils.writeByteArray(file, content.getBytes(), 0, content.length());
    }

    @Test
    public void testWriteByteArray2Test() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);

        String content = "aaaaa";
        FileUtils.writeByteArray(file, content.getBytes(), 0, content.length(), true);
    }

    @Test
    public void openInputStreamTest() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);
        FileInputStream inputStream = FileUtils.openInputStream(file);
        Assert.assertNotNull(inputStream);
        IOUtils.closeQuietly(inputStream);
    }

    @Test
    public void openOutputStreamTest() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);
        FileOutputStream outputStream = FileUtils.openOutputStream(file);
        String content = "asd都是";
        outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        IOUtils.closeQuietly(outputStream);
    }

    @Test
    public void testOpenOutputStreamTest() throws IOException {
        File file = new File(PATH);
        Assert.assertNotNull(file);
        FileOutputStream outputStream = FileUtils.openOutputStream(file, true);
        String content = "sasdasd";
        outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        IOUtils.closeQuietly(outputStream);
    }

    @Test
    public void forceDeletePathTest() {
        File file = new File("test");
        Assert.assertNotNull(file);
        FileUtils.forceDeletePath(file);
    }
}
