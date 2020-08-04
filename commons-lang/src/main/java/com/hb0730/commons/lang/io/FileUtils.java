package com.hb0730.commons.lang.io;

import java.io.File;

/**
 * file 工具类
 *
 * @author bing_huang
 * @date 2020/08/04 13:56
 * @since V1.0
 */
public class FileUtils {

    /**
     * 递归删除
     *
     * @param path file路径
     * @return true: 如果全部删除,false:如果未删除或者部分删除
     */
    public static boolean forceDeletePath(File path) {
        if (null == path) {
            return false;
        }
        if (path.exists() && path.isDirectory()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    forceDeletePath(file);
                } else {
                    file.delete();
                }
            }
        }
        return path.delete();
    }
}
