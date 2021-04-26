package com.hb0730.commons.lang.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class FileUtilsTest {
    private static final String PATH = "E:\\IdeaWork\\customer-work\\hb0730-commons\\commons-lang\\src\\test\\java\\com\\hb0730\\commons\\lang\\io\\test.txt";

    @Test
    public void writeTest() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testWriteTest() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);

            String encoding = "utf-8";
            StringBuilder content = new StringBuilder("cccesasdas很好");
            FileUtils.write(file, content, encoding, true);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testWrite1Test() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);

            Charset encoding = StandardCharsets.UTF_8;
            StringBuilder content = new StringBuilder("cccesasdas很好");
            FileUtils.write(file, content, encoding);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testWrite2Test() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);

            Charset encoding = StandardCharsets.UTF_8;
            StringBuilder content = new StringBuilder("cccesasdas很好");
            FileUtils.write(file, content, encoding, true);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void writeStringTest() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);

            String content = "测试";
            String encoding = "utf-8";
            FileUtils.writeString(file, content, encoding);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testWriteStringTest() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);

            String content = "测gggg试";
            String encoding = "utf-8";
            FileUtils.writeString(file, content, encoding, true);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testWriteString1Test() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);

            String content = "测gggg试";
            Charset encoding = StandardCharsets.UTF_8;
            FileUtils.writeString(file, content, encoding, false);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void writeByteArrayTest() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);

            String content = "测gggg试";
            FileUtils.writeByteArray(file, content.getBytes());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testWriteByteArrayTest() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);

            String content = "asdas好";
            FileUtils.writeByteArray(file, content.getBytes(), true);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testWriteByteArray1Test() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);

            String content = "asdas";
            FileUtils.writeByteArray(file, content.getBytes(), 0, content.length());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testWriteByteArray2Test() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);

            String content = "aaaaa";
            FileUtils.writeByteArray(file, content.getBytes(), 0, content.length(), true);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void openInputStreamTest() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);
            FileInputStream inputStream = FileUtils.openInputStream(file);
            Assert.assertNotNull(inputStream);
            IOUtils.closeQuietly(inputStream);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void openOutputStreamTest() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);
            FileOutputStream outputStream = FileUtils.openOutputStream(file);
            String content = "asd都是";
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            IOUtils.closeQuietly(outputStream);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testOpenOutputStreamTest() throws IOException {
        try {
            File file = new File(PATH);
            Assert.assertNotNull(file);
            FileOutputStream outputStream = FileUtils.openOutputStream(file, true);
            String content = "sasdasd";
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            IOUtils.closeQuietly(outputStream);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void getFileTest() {
        try {
            File file = FileUtils.getFile(PATH);
            Assert.assertNotNull(file);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void isFileExistsTest() {
        try {
            FileUtils.isFileExists(PATH);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testIsFileExistsTest() {
        try {
            FileUtils.isFile(FileUtils.getFile(PATH));
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void renameTest() {
        try {
            FileUtils.rename(PATH, "hahah.txt");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testRenameTest() {
        try {
            FileUtils.rename(FileUtils.getFile(PATH), "test.txt");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void isDirTest() {
        try {
            boolean dir = FileUtils.isDir("d:/");
            FileUtils.isDir("d:/haha3.txt");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testIsDirTest() {
        try {
            FileUtils.isDir(FileUtils.getFile("d:/"));
            FileUtils.isDir(FileUtils.getFile("d:/haha3.txt"));

        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void isFileTest() {
        try {
            FileUtils.isFile("d:/");
            FileUtils.isFile("d:/haha3.txt");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testIsFileTest() {
        try {
            FileUtils.isFile(FileUtils.getFile("d:/"));
            FileUtils.isFile(FileUtils.getFile("d:/haha3.txt"));
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void createOrExistsDirTest() {
        try {
            FileUtils.createOrExistsDir("d:/test");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testCreateOrExistsDirTest() {
        try {
            FileUtils.createOrExistsDir(FileUtils.getFile("d:/test"));
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void createOrExistsFileTest() {
        try {
            FileUtils.createOrExistsFile("d:/test.txt");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testCreateOrExistsFileTest() {
        try {
            FileUtils.createOrExistsFile(FileUtils.getFile("d:/test.txt"));
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void createFileByDeleteOldFileTest() {
        try {
            FileUtils.createFileByDeleteOldFile("d:/test.txt");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testCreateFileByDeleteOldFileTest() {
        try {
            FileUtils.createFileByDeleteOldFile(FileUtils.getFile("d:/test.txt"));
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void deleteDirTest() {
        try {
            FileUtils.createOrExistsDir("d:/text");
            boolean b = FileUtils.deleteDir("d:/text");
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testDeleteDirTest() {
        try {
            File file = FileUtils.getFile("d:/test");
            FileUtils.createOrExistsDir(file);
            boolean b = FileUtils.deleteDir(file);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void deleteFileTest() {
        try {
            FileUtils.createOrExistsFile("d:/test.txt");
            boolean b = FileUtils.deleteFile("d:/test.txt");
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testDeleteFileTest() {
        try {
            File file = FileUtils.getFile("d:/test.txt");
            FileUtils.createOrExistsFile(file);
            boolean b = FileUtils.deleteFile(file);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void deleteFilesInDirTest() {
        try {
            FileUtils.createOrExistsFile("d:/test/test.txt");
            boolean b = FileUtils.deleteFilesInDir("d:/test");
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testDeleteFilesInDirTest() {
        try {
            File file = FileUtils.getFile("d:/test/test.txt");
            FileUtils.createOrExistsFile(file);
            file = FileUtils.getFile("d:/test");
            boolean b = FileUtils.deleteFilesInDir(file);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void listFilesInDirTest() {
        try {
            FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
            FileUtils.createOrExistsFile("d:/test/test.txt");
            List<File> list = FileUtils.listFilesInDir("d:/test");
            Assert.assertNotNull(list);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testListFilesInDirTest() {
        try {
            FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
            FileUtils.createOrExistsFile("d:/test/test.txt");
            List<File> list = FileUtils.listFilesInDir(FileUtils.getFile("d:/test"));
            Assert.assertNotNull(list);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testListFilesInDir1Test() throws IOException {
        try {
            FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
            FileUtils.createOrExistsFile("d:/test/test.txt");
            List<File> list = FileUtils.listFilesInDir("d:/test", false);
            Assert.assertNotNull(list);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testListFilesInDir2Test() throws IOException {
        try {
            FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
            FileUtils.createOrExistsFile("d:/test/test.txt");
            List<File> list = FileUtils.listFilesInDir(FileUtils.getFile("d:/test"), false);
            Assert.assertNotNull(list);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void listFilesInDirWithSuffixTest() throws IOException {
        try {
            FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
            FileUtils.createOrExistsFile("d:/test/test.txt");
            List<File> list = FileUtils.listFilesInDirWithSuffix("d:/test", "txt");
            Assert.assertNotNull(list);
            list = FileUtils.listFilesInDirWithSuffix("d:/test", null);
            Assert.assertNotNull(list);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testListFilesInDirWithSuffixTest() throws IOException {
        try {
            FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
            FileUtils.createOrExistsFile("d:/test/test.txt");
            List<File> list = FileUtils.listFilesInDirWithSuffix("d:/test", "txt", false);
            Assert.assertNotNull(list);
            list = FileUtils.listFilesInDirWithSuffix("d:/test", null, false);
            Assert.assertNotNull(list);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testListFilesInDirWithSuffix1Test() throws IOException {
        try {
            FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
            FileUtils.createOrExistsFile("d:/test/test.txt");
            List<File> list = FileUtils.listFilesInDirWithSuffix(FileUtils.getFile("d:/test"), "txt");
            Assert.assertNotNull(list);
            list = FileUtils.listFilesInDirWithSuffix(FileUtils.getFile("d:/test"), null);
            Assert.assertNotNull(list);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testListFilesInDirWithSuffix2Test() throws IOException {
        try {
            FileUtils.createOrExistsFile("d:/test/test1/test1.txt");
            FileUtils.createOrExistsFile("d:/test/test.txt");
            List<File> list = FileUtils.listFilesInDirWithSuffix(FileUtils.getFile("d:/test"), "txt", false);
            Assert.assertNotNull(list);
            list = FileUtils.listFilesInDirWithSuffix(FileUtils.getFile("d:/test"), null, false);
            Assert.assertNotNull(list);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void listFilesInDirWithFilterTest() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testListFilesInDirWithFilterTest() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testListFilesInDirWithFilter1Test() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testListFilesInDirWithFilter2Test() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void searchFileInDirTest() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testSearchFileInDirTest() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void readFileListTest() throws IOException {
        try {
            FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
            List<String> list = FileUtils.readFileList("d:/test/test.txt", Charset.defaultCharset().name());
            Assert.assertNotNull(list);
            list = FileUtils.readFileList("d:/test/test.txt", null);
            Assert.assertNotNull(list);
            list = FileUtils.readFileList((String) null, null);
            Assert.assertNotNull(list);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testReadFileListTest() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testReadFileList1Test() throws IOException {
        try {
            FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
            List<String> list = FileUtils.readFileList(FileUtils.getFile("d:/test/test.txt"), Charset.defaultCharset().name());
            Assert.assertNotNull(list);
            list = FileUtils.readFileList(FileUtils.getFile("d:/test/test.txt"), null);
            Assert.assertNotNull(list);
            list = FileUtils.readFileList((File) null, null);
            Assert.assertNotNull(list);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testReadFileList2Test() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void readFileStringTest() throws IOException {
        try {
            FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
            String s = FileUtils.readFileString("d:/test/test.txt", Charset.defaultCharset().name());
            Assert.assertNotNull(s);
            s = FileUtils.readFileString("d:/test/test.txt", null);
            Assert.assertNotNull(s);
            s = FileUtils.readFileString((String) null, null);
            Assert.assertNotNull(s);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testReadFileStringTest() throws IOException {
        try {
            FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
            String s = FileUtils.readFileString(FileUtils.getFile("d:/test/test.txt"), Charset.defaultCharset().name());
            Assert.assertNotNull(s);
            s = FileUtils.readFileString(FileUtils.getFile("d:/test/test.txt"), null);
            Assert.assertNotNull(s);
            s = FileUtils.readFileString((File) null, null);
            Assert.assertNotNull(s);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void readFileBytesTest() throws IOException {
        try {
            FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
            byte[] bytes = FileUtils.readFileBytes("d:/test/test.txt");
            Assert.assertNotNull(bytes);
            bytes = FileUtils.readFileBytes((String) null);
            Assert.assertNotNull(bytes);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testReadFileBytesTest() throws IOException {
        try {
            FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
            byte[] bytes = FileUtils.readFileBytes(FileUtils.getFile("d:/test/test.txt"));
            Assert.assertNotNull(bytes);
            bytes = FileUtils.readFileBytes((File) null);
            Assert.assertNotNull(bytes);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void getFileLinesTest() throws IOException {
        try {
            FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
            int fileLines = FileUtils.getFileLines("d:/test/test.txt");
            fileLines = FileUtils.getFileLines((String) null);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testGetFileLinesTest() throws IOException {
        try {
            FileUtils.write(FileUtils.getFile("d:/test/test.txt"), "test \r\n,test \r\n,test \r\n,test \r\n", Charset.defaultCharset());
            int fileLines = FileUtils.getFileLines(FileUtils.getFile("d:/test/test.txt"));
            fileLines = FileUtils.getFileLines((File) null);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void writeByStreamTest() throws IOException {
        try {
            FileInputStream inputStream = FileUtils.openInputStream(new File("d:/haha3.txt"));
            boolean b = FileUtils.writeByStream("d:/test/test.txt", inputStream, false);
            Assert.assertTrue(b);
            b = FileUtils.writeByStream("d:/test/test.txt", null, false);
            Assert.assertTrue(b);
            b = FileUtils.writeByStream((String) null, null, false);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testWriteByteStreamTest() throws IOException {
        try {
            FileInputStream inputStream = FileUtils.openInputStream(new File("d:/haha3.txt"));
            boolean b = FileUtils.writeByStream(FileUtils.getFile("d:/test/test.txt"), inputStream, false);
            Assert.assertTrue(b);
            b = FileUtils.writeByStream("d:/test/test.txt", null, false);
            Assert.assertTrue(b);
            b = FileUtils.writeByStream((String) null, null, false);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void copyDirTest() throws IOException {
        try {
            boolean b = FileUtils.copyDir("d:/test/test1", "d:/test/test2");
            Assert.assertTrue(b);
            b = FileUtils.copyDir("d:/test/test1", null);
            Assert.assertTrue(b);
            b = FileUtils.copyDir((String) null, null);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testCopyDirTest() throws IOException {
        try {
            boolean b = FileUtils.copyDir(FileUtils.getFile("d:/test/test1"), FileUtils.getFile("d:/test/test2"));
            Assert.assertTrue(b);
            b = FileUtils.copyDir(FileUtils.getFile("d:/test/test1"), null);
            Assert.assertTrue(b);
            b = FileUtils.copyDir((File) null, null);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void copyFileTest() throws IOException {
        try {
            boolean b = FileUtils.copyFile("d:/test/test1/test1.txt", "d:/test/test2/test2.txt");
            Assert.assertTrue(b);
            b = FileUtils.copyFile("d:/test/test1/test1.txt", null);
            Assert.assertTrue(b);
            b = FileUtils.copyFile((String) null, null);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testCopyFileTest() throws IOException {
        try {
            boolean b = FileUtils.copyFile(FileUtils.getFile("d:/test/test1/test1.txt"), FileUtils.getFile("d:/test/test2/test2.txt"));
            Assert.assertTrue(b);
            b = FileUtils.copyFile(FileUtils.getFile("d:/test/test1/test1.txt"), null);
            Assert.assertTrue(b);
            b = FileUtils.copyFile((File) null, null);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void moveDirTest() throws IOException {
        try {
            FileUtils.createOrExistsDir("d:/test/test2");
            boolean b = FileUtils.moveDir("d:/test/test2", "d:/test/test1/test2");
            Assert.assertTrue(b);
            b = FileUtils.moveDir("d:/test/test2", null);
            Assert.assertTrue(b);
            b = FileUtils.moveDir((String) null, null);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testMoveDirTest() throws IOException {
        try {
            boolean b = FileUtils.moveDir(FileUtils.getFile("d:/test/test1/test2"), FileUtils.getFile("d:/test/test2"));
            Assert.assertTrue(b);
            b = FileUtils.moveDir(FileUtils.getFile("d:/test/test2"), null);
            Assert.assertTrue(b);
            b = FileUtils.moveDir((File) null, null);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void moveFileTest() throws IOException {
        try {
            FileUtils.createOrExistsFile("d:/test/test2/test2.txt");
            boolean b = FileUtils.moveFile("d:/test/test2/test2.txt", "d:/test/test1/test2.txt");
            Assert.assertTrue(b);
            b = FileUtils.moveFile("d:/test/test2/test2.txt", null);
            Assert.assertTrue(b);
            b = FileUtils.moveFile((String) null, null);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testMoveFileTest() throws IOException {
        try {
            boolean b = FileUtils.moveFile(FileUtils.getFile("d:/test/test1/test2.txt"), FileUtils.getFile("d:/test/test2/test2.txt"));
            Assert.assertTrue(b);
            b = FileUtils.moveFile(FileUtils.getFile("d:/test/test1/test2.txt"), null);
            Assert.assertTrue(b);
            b = FileUtils.moveFile((File) null, null);
            Assert.assertTrue(b);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void copyOrMoveDirTest() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testCopyOrMoveDirTest() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void copyOrMoveFileTest() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testCopyOrMoveFileTest() throws IOException {
        try {
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
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

}
