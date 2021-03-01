package com.hb0730.commons.lang.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
    public void getFileTest() {
        File file = FileUtils.getFile(PATH);
        Assert.assertNotNull(file);
    }

    @Test
    public void isFileExistsTest() {
        FileUtils.isFileExists(PATH);
    }

    @Test
    public void testIsFileExistsTest() {
        FileUtils.isFile(FileUtils.getFile(PATH));
    }

    @Test
    public void renameTest() {
        FileUtils.rename(PATH, "hahah.txt");
    }

    @Test
    public void testRenameTest() {
        FileUtils.rename(FileUtils.getFile(PATH), "test.txt");
    }

    @Test
    public void isDirTest() {
        boolean dir = FileUtils.isDir("d:/");
        FileUtils.isDir("d:/haha3.txt");
    }

    @Test
    public void testIsDirTest() {
        FileUtils.isDir(FileUtils.getFile("d:/"));
        FileUtils.isDir(FileUtils.getFile("d:/haha3.txt"));
    }

    @Test
    public void isFileTest() {
        FileUtils.isFile("d:/");
        FileUtils.isFile("d:/haha3.txt");
    }

    @Test
    public void testIsFileTest() {
        FileUtils.isFile(FileUtils.getFile("d:/"));
        FileUtils.isFile(FileUtils.getFile("d:/haha3.txt"));
    }

    @Test
    public void createOrExistsDirTest() {
        FileUtils.createOrExistsDir("d:/test");
    }

    @Test
    public void testCreateOrExistsDirTest() {
        FileUtils.createOrExistsDir(FileUtils.getFile("d:/test"));
    }

    @Test
    public void createOrExistsFileTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test.txt");

    }

    @Test
    public void testCreateOrExistsFileTest() throws IOException {
        FileUtils.createOrExistsFile(FileUtils.getFile("d:/test.txt"));

    }

    @Test
    public void createFileByDeleteOldFileTest() throws IOException {
        FileUtils.createFileByDeleteOldFile("d:/test.txt");

    }

    @Test
    public void testCreateFileByDeleteOldFileTest() throws IOException {
        FileUtils.createFileByDeleteOldFile(FileUtils.getFile("d:/test.txt"));

    }

    @Test
    public void deleteDirTest() {
        FileUtils.createOrExistsDir("d:/text");
        boolean b = FileUtils.deleteDir("d:/text");
        Assert.assertTrue(b);
    }

    @Test
    public void testDeleteDirTest() {
        File file = FileUtils.getFile("d:/test");
        FileUtils.createOrExistsDir(file);
        boolean b = FileUtils.deleteDir(file);
        Assert.assertTrue(b);
    }

    @Test
    public void deleteFileTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test.txt");
        boolean b = FileUtils.deleteFile("d:/test.txt");
        Assert.assertTrue(b);
    }

    @Test
    public void testDeleteFileTest() throws IOException {
        File file = FileUtils.getFile("d:/test.txt");
        FileUtils.createOrExistsFile(file);
        boolean b = FileUtils.deleteFile(file);
        Assert.assertTrue(b);
    }

    @Test
    public void deleteFilesInDirTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test.txt");
        boolean b = FileUtils.deleteFilesInDir("d:/test");
        Assert.assertTrue(b);
    }

    @Test
    public void testDeleteFilesInDirTest() throws IOException {
        File file = FileUtils.getFile("d:/test/test.txt");
        FileUtils.createOrExistsFile(file);
        file = FileUtils.getFile("d:/test");
        boolean b = FileUtils.deleteFilesInDir(file);
        Assert.assertTrue(b);
    }

    @Test
    public void listFilesInDirTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDir("d:/test");
        Assert.assertNotNull(list);
    }

    @Test
    public void testListFilesInDirTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDir(FileUtils.getFile("d:/test"));
        Assert.assertNotNull(list);
    }

    @Test
    public void testListFilesInDir1Test() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDir("d:/test", false);
        Assert.assertNotNull(list);
    }

    @Test
    public void testListFilesInDir2Test() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDir(FileUtils.getFile("d:/test"), false);
        Assert.assertNotNull(list);
    }

    @Test
    public void listFilesInDirWithSuffixTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDirWithSuffix("d:/test", "txt");
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithSuffix("d:/test", null);
        Assert.assertNotNull(list);
    }

    @Test
    public void testListFilesInDirWithSuffixTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDirWithSuffix("d:/test", "txt", false);
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithSuffix("d:/test", null, false);
        Assert.assertNotNull(list);
    }

    @Test
    public void testListFilesInDirWithSuffix1Test() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDirWithSuffix(FileUtils.getFile("d:/test"), "txt");
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithSuffix(FileUtils.getFile("d:/test"), null);
        Assert.assertNotNull(list);
    }

    @Test
    public void testListFilesInDirWithSuffix2Test() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDirWithSuffix(FileUtils.getFile("d:/test"), "txt", false);
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithSuffix(FileUtils.getFile("d:/test"), null, false);
        Assert.assertNotNull(list);
    }

    @Test
    public void listFilesInDirWithFilterTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDirWithFilter("d:/test", new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return null != name && name.toUpperCase().endsWith("txt".toUpperCase());
            }
        });
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithFilter("d:/test", null);
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithFilter((String) null, null);
        Assert.assertNotNull(list);
    }

    @Test
    public void testListFilesInDirWithFilterTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDirWithFilter("d:/test", new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return null != name && name.toUpperCase().endsWith("txt".toUpperCase());
            }
        }, false);
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithFilter("d:/test", null, false);
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithFilter((String) null, null, false);
        Assert.assertNotNull(list);
    }

    @Test
    public void testListFilesInDirWithFilter1Test() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDirWithFilter(FileUtils.getFile("d:/test"), new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return null != name && name.toUpperCase().endsWith("txt".toUpperCase());
            }
        });
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithFilter(FileUtils.getFile("d:/test"), null);
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithFilter((File) null, null);
        Assert.assertNotNull(list);
    }

    @Test
    public void testListFilesInDirWithFilter2Test() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.listFilesInDirWithFilter(FileUtils.getFile("d:/test"), new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return null != name && name.toUpperCase().endsWith("txt".toUpperCase());
            }
        }, false);
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithFilter(FileUtils.getFile("d:/test"), null, false);
        Assert.assertNotNull(list);
        list = FileUtils.listFilesInDirWithFilter((File) null, null, false);
        Assert.assertNotNull(list);
    }

    @Test
    public void searchFileInDirTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.searchFileInDir("d:/test", "test1.txt");
        Assert.assertNotNull(list);
        list = FileUtils.searchFileInDir("d:/test", "test1");
        Assert.assertNotNull(list);
        list = FileUtils.searchFileInDir("d:/test", null);
        Assert.assertNotNull(list);
        list = FileUtils.searchFileInDir((String) null, null);
        Assert.assertNotNull(list);
    }

    @Test
    public void testSearchFileInDirTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.createOrExistsFile("d:/test/test.txt");
        List<File> list = FileUtils.searchFileInDir(FileUtils.getFile("d:/test"), "test1.txt");
        Assert.assertNotNull(list);
        list = FileUtils.searchFileInDir(FileUtils.getFile("d:/test"), "test1");
        Assert.assertNotNull(list);
        list = FileUtils.searchFileInDir(FileUtils.getFile("d:/test"), null);
        Assert.assertNotNull(list);
        list = FileUtils.searchFileInDir((File) null, null);
        Assert.assertNotNull(list);
    }

    @Test
    public void readFileListTest() throws IOException {
        FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
        List<String> list = FileUtils.readFileList("d:/test/test.txt", Charset.defaultCharset().name());
        Assert.assertNotNull(list);
        list = FileUtils.readFileList("d:/test/test.txt", null);
        Assert.assertNotNull(list);
        list = FileUtils.readFileList((String) null, null);
        Assert.assertNotNull(list);
    }

    @Test
    public void testReadFileListTest() throws IOException {
        FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
        List<String> list = FileUtils.readFileList("d:/test/test.txt", 0, 4, Charset.defaultCharset().name());
        Assert.assertNotNull(list);
        list = FileUtils.readFileList("d:/test/test.txt", 3, 4, Charset.defaultCharset().name());
        Assert.assertNotNull(list);
        list = FileUtils.readFileList("d:/test/test.txt", 5, 4, Charset.defaultCharset().name());
        Assert.assertNotNull(list);
        list = FileUtils.readFileList("d:/test/test.txt", 0, 4, null);
        Assert.assertNotNull(list);
        list = FileUtils.readFileList((String) null, 0, 4, null);
        Assert.assertNotNull(list);
    }

    @Test
    public void testReadFileList1Test() throws IOException {
        FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
        List<String> list = FileUtils.readFileList(FileUtils.getFile("d:/test/test.txt"), Charset.defaultCharset().name());
        Assert.assertNotNull(list);
        list = FileUtils.readFileList(FileUtils.getFile("d:/test/test.txt"), null);
        Assert.assertNotNull(list);
        list = FileUtils.readFileList((File) null, null);
        Assert.assertNotNull(list);
    }

    @Test
    public void testReadFileList2Test() throws IOException {
        FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
        List<String> list = FileUtils.readFileList(FileUtils.getFile("d:/test/test.txt"), 0, 4, Charset.defaultCharset().name());
        Assert.assertNotNull(list);
        list = FileUtils.readFileList(FileUtils.getFile("d:/test/test.txt"), 3, 4, Charset.defaultCharset().name());
        Assert.assertNotNull(list);
        list = FileUtils.readFileList(FileUtils.getFile("d:/test/test.txt"), 5, 4, Charset.defaultCharset().name());
        Assert.assertNotNull(list);
        list = FileUtils.readFileList(FileUtils.getFile("d:/test/test.txt"), 0, 4, null);
        Assert.assertNotNull(list);
        list = FileUtils.readFileList((File) null, 0, 4, null);
        Assert.assertNotNull(list);
    }

    @Test
    public void readFileStringTest() throws IOException {
        FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
        String s = FileUtils.readFileString("d:/test/test.txt", Charset.defaultCharset().name());
        Assert.assertNotNull(s);
        s = FileUtils.readFileString("d:/test/test.txt", null);
        Assert.assertNotNull(s);
        s = FileUtils.readFileString((String) null, null);
        Assert.assertNotNull(s);
    }

    @Test
    public void testReadFileStringTest() throws IOException {
        FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
        String s = FileUtils.readFileString(FileUtils.getFile("d:/test/test.txt"), Charset.defaultCharset().name());
        Assert.assertNotNull(s);
        s = FileUtils.readFileString(FileUtils.getFile("d:/test/test.txt"), null);
        Assert.assertNotNull(s);
        s = FileUtils.readFileString((File) null, null);
        Assert.assertNotNull(s);
    }

    @Test
    public void readFileBytesTest() throws IOException {
        FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
        byte[] bytes = FileUtils.readFileBytes("d:/test/test.txt");
        Assert.assertNotNull(bytes);
        bytes = FileUtils.readFileBytes((String) null);
        Assert.assertNotNull(bytes);
    }

    @Test
    public void testReadFileBytesTest() throws IOException {
        FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
        byte[] bytes = FileUtils.readFileBytes(FileUtils.getFile("d:/test/test.txt"));
        Assert.assertNotNull(bytes);
        bytes = FileUtils.readFileBytes((File) null);
        Assert.assertNotNull(bytes);
    }

    @Test
    public void getFileLinesTest() throws IOException {
        FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
        int fileLines = FileUtils.getFileLines("d:/test/test.txt");
        fileLines = FileUtils.getFileLines((String) null);
    }

    @Test
    public void testGetFileLinesTest() throws IOException {
        FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
        int fileLines = FileUtils.getFileLines(FileUtils.getFile("d:/test/test.txt"));
        fileLines = FileUtils.getFileLines((File) null);
    }

    @Test
    public void writeByStreamTest() throws IOException {
        FileInputStream inputStream = FileUtils.openInputStream(new File("d:/haha3.txt"));
        boolean b = FileUtils.writeByStream("d:/test/test.txt", inputStream, false);
        Assert.assertTrue(b);
        b = FileUtils.writeByStream("d:/test/test.txt", null, false);
        Assert.assertTrue(b);
        b = FileUtils.writeByStream((String) null, null, false);
        Assert.assertTrue(b);
    }

    @Test
    public void testWriteByteStreamTest() throws IOException {
        FileInputStream inputStream = FileUtils.openInputStream(new File("d:/haha3.txt"));
        boolean b = FileUtils.writeByStream(FileUtils.getFile("d:/test/test.txt"), inputStream, false);
        Assert.assertTrue(b);
        b = FileUtils.writeByStream("d:/test/test.txt", null, false);
        Assert.assertTrue(b);
        b = FileUtils.writeByStream((String) null, null, false);
        Assert.assertTrue(b);
    }

    @Test
    public void copyDirTest() throws IOException {
        boolean b = FileUtils.copyDir("d:/test/test1", "d:/test/test2");
        Assert.assertTrue(b);
        b = FileUtils.copyDir("d:/test/test1", null);
        Assert.assertTrue(b);
        b = FileUtils.copyDir((String) null, null);
        Assert.assertTrue(b);
    }

    @Test
    public void testCopyDirTest() throws IOException {
        boolean b = FileUtils.copyDir(FileUtils.getFile("d:/test/test1"), FileUtils.getFile("d:/test/test2"));
        Assert.assertTrue(b);
        b = FileUtils.copyDir(FileUtils.getFile("d:/test/test1"), null);
        Assert.assertTrue(b);
        b = FileUtils.copyDir((File) null, null);
        Assert.assertTrue(b);
    }

    @Test
    public void copyFileTest() throws IOException {
        boolean b = FileUtils.copyFile("d:/test/test1/test1.txt", "d:/test/test2/test2.txt");
        Assert.assertTrue(b);
        b = FileUtils.copyFile("d:/test/test1/test1.txt", null);
        Assert.assertTrue(b);
        b = FileUtils.copyFile((String) null, null);
        Assert.assertTrue(b);
    }

    @Test
    public void testCopyFileTest() throws IOException {
        boolean b = FileUtils.copyFile(FileUtils.getFile("d:/test/test1/test1.txt"), FileUtils.getFile("d:/test/test2/test2.txt"));
        Assert.assertTrue(b);
        b = FileUtils.copyFile(FileUtils.getFile("d:/test/test1/test1.txt"), null);
        Assert.assertTrue(b);
        b = FileUtils.copyFile((File) null, null);
        Assert.assertTrue(b);
    }

    @Test
    public void moveDirTest() throws IOException {
        FileUtils.createOrExistsDir("d:/test/test2");
        boolean b = FileUtils.moveDir("d:/test/test2", "d:/test/test1/test2");
        Assert.assertTrue(b);
        b = FileUtils.moveDir("d:/test/test2", null);
        Assert.assertTrue(b);
        b = FileUtils.moveDir((String) null, null);
        Assert.assertTrue(b);
    }

    @Test
    public void testMoveDirTest() throws IOException {
        boolean b = FileUtils.moveDir(FileUtils.getFile("d:/test/test1/test2"), FileUtils.getFile("d:/test/test2"));
        Assert.assertTrue(b);
        b = FileUtils.moveDir(FileUtils.getFile("d:/test/test2"), null);
        Assert.assertTrue(b);
        b = FileUtils.moveDir((File) null, null);
        Assert.assertTrue(b);
    }

    @Test
    public void moveFileTest() throws IOException {
        FileUtils.createOrExistsFile("d:/test/test2/test2.txt");
        boolean b = FileUtils.moveFile("d:/test/test2/test2.txt", "d:/test/test1/test2.txt");
        Assert.assertTrue(b);
        b = FileUtils.moveFile("d:/test/test2/test2.txt", null);
        Assert.assertTrue(b);
        b = FileUtils.moveFile((String) null, null);
        Assert.assertTrue(b);
    }

    @Test
    public void testMoveFileTest() throws IOException {
        boolean b = FileUtils.moveFile(FileUtils.getFile("d:/test/test1/test2.txt"), FileUtils.getFile("d:/test/test2/test2.txt"));
        Assert.assertTrue(b);
        b = FileUtils.moveFile(FileUtils.getFile("d:/test/test1/test2.txt"), null);
        Assert.assertTrue(b);
        b = FileUtils.moveFile((File) null, null);
        Assert.assertTrue(b);
    }

    @Test
    public void copyOrMoveDirTest() throws IOException {
        FileUtils.deleteDir("d:/test");
        FileUtils.createOrExistsDir("d:/test/test1/test1-1");
        boolean b = FileUtils.copyOrMoveDir("d:/test/test1", "d:/test/test2", false);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveDir("d:/test/test2", "d:/test", true);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveDir("d:/test/test2", null, true);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveDir((String) null, null, true);
        Assert.assertTrue(b);
    }

    @Test
    public void testCopyOrMoveDirTest() throws IOException {
        FileUtils.deleteDir("d:/test");
        FileUtils.createOrExistsDir("d:/test/test1/test1-1");
        boolean b = FileUtils.copyOrMoveDir(FileUtils.getFile("d:/test/test1"), FileUtils.getFile("d:/test/test2"), false);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveDir(FileUtils.getFile("d:/test/test2"), FileUtils.getFile("d:/test"), true);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveDir(FileUtils.getFile("d:/test/test2"), null, true);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveDir((File) null, null, true);
        Assert.assertTrue(b);
    }

    @Test
    public void copyOrMoveFileTest() throws IOException {
        FileUtils.deleteDir("d:/test");
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.write(FileUtils.getFile("d:/test/test1/test.txt"), "text,\r\ntest,\r\ntest", Charset.defaultCharset());
        boolean b = FileUtils.copyOrMoveFile("d:/test/test1/test.txt", "d:/test/test2/test2.txt", false);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveFile("d:/test/test2/test2.txt", "d:/test/test.txt", true);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveFile("d:/test/test2/test2.txt", null, true);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveFile((String) null, null, true);
        Assert.assertTrue(b);
    }

    @Test
    public void testCopyOrMoveFileTest() throws IOException {
        FileUtils.deleteDir("d:/test");
        FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
        FileUtils.write(FileUtils.getFile("d:/test/test1/test.txt"), "text,\r\ntest,\r\ntest", Charset.defaultCharset());
        boolean b = FileUtils.copyOrMoveFile(FileUtils.getFile("d:/test/test1/test.txt"), FileUtils.getFile("d:/test/test2/test2.txt"), false);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveFile(FileUtils.getFile("d:/test/test2/test2.txt"), FileUtils.getFile("d:/test/test.txt"), true);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveFile(FileUtils.getFile("d:/test/test2/test2.txt"), null, true);
        Assert.assertTrue(b);
        b = FileUtils.copyOrMoveFile((File) null, null, true);
        Assert.assertTrue(b);
    }
}
