package com.hb0730.commons.lang.io;

import com.hb0730.commons.lang.codec.HexUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件类型判断工具类
 *
 * @author bing_huang
 * @since 2.1.1
 */
public class FileTypeUtil {
    private static final Map<String, String> FILE_TYPE_MAP;

    static {
        FILE_TYPE_MAP = new ConcurrentHashMap<>();
        //JPEG(jpg)
        FILE_TYPE_MAP.put("FFD8FF", "jpg");
        //PNG (png)
        FILE_TYPE_MAP.put("89504E47", "png");
        //GIF (gif)
        FILE_TYPE_MAP.put("47494638", "gif");
        //ZIP Archive (zip)
        FILE_TYPE_MAP.put("504B0304", "zip");
        //TIFF (tif)
        FILE_TYPE_MAP.put("49492A00", "tif");
        //Windows Bitmap (bmp)
        FILE_TYPE_MAP.put("424D", "bmp");
        //CAD (dwg)
        FILE_TYPE_MAP.put("41433130", "dwg");
        //Adobe Photoshop (psd)
        FILE_TYPE_MAP.put("38425053", "psd");
        //Rich Text Format (rtf)
        FILE_TYPE_MAP.put("7B5C727466", "rtf");
        //XML (xml)
        FILE_TYPE_MAP.put("3C3F786D6C", "xml");
        //HTML (html)
        FILE_TYPE_MAP.put("68746D6C3E", "html");
        //Email [thorough only] (eml)
        FILE_TYPE_MAP.put("44656C69766572792D646174653A", "eml");
        //Outlook Express (dbx)
        FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx");
        //Outlook (pst)
        FILE_TYPE_MAP.put("2142444E", "pst");
        //MS Word/Excel (xls.or.doc)
        FILE_TYPE_MAP.put("D0CF11E0", "xls");
        //MS Access (mdb)
        FILE_TYPE_MAP.put("5374616E64617264204A", "mdb");
        //WordPerfect (wpd)
        FILE_TYPE_MAP.put("FF575043", "wpd");
        //Adobe Acrobat (pdf)
        FILE_TYPE_MAP.put("255044462D312E", "pdf");
        //Quicken (qdf)
        FILE_TYPE_MAP.put("AC9EBD8F", "qdf");
        //Windows Password (pwl)
        FILE_TYPE_MAP.put("E3828596", "pwl");
        //RAR Archive (rar)
        FILE_TYPE_MAP.put("52617221", "rar");
        //Wave (wav)
        FILE_TYPE_MAP.put("57415645", "wav");
        //AVI (avi)
        FILE_TYPE_MAP.put("41564920", "avi");
        //Real Audio (ram)
        FILE_TYPE_MAP.put("2E7261FD", "ram");
        //Real Media (rm)
        FILE_TYPE_MAP.put("2E524D46", "rm");
        //MPEG (mpg)
        FILE_TYPE_MAP.put("000001BA", "mpg");
        //MPEG (mpg)
        FILE_TYPE_MAP.put("000001B3", "mpg");
        //Quicktime (mov)
        FILE_TYPE_MAP.put("6D6F6F76", "mov");
        //Windows Media (asf)
        FILE_TYPE_MAP.put("3026B2758E66CF11", "asf");
        //MIDI (mid)
        FILE_TYPE_MAP.put("4D546864", "mid");
    }

    /**
     * 增加文件类型映射<br>
     * 如果已经存在将覆盖之前的映射
     *
     * @param fileStreamHexHead 文件流头部Hex信息
     * @param extName           文件扩展名
     * @return 之前已经存在的文件扩展名
     */
    public static String putFileType(String fileStreamHexHead, String extName) {
        return FILE_TYPE_MAP.put(fileStreamHexHead, extName);
    }

    /**
     * 移除文件类型映射
     *
     * @param fileStreamHexHead 文件流头部Hex信息
     * @return 移除的文件扩展名
     */
    public static String removeFileType(String fileStreamHexHead) {
        return FILE_TYPE_MAP.remove(fileStreamHexHead.toLowerCase());
    }

    /**
     * 根据文件流的头部信息获得文件类型
     *
     * @param fileStreamHexHead 文件流头部16进制字符串
     * @return 文件类型，未找到为<code>null</code>
     */
    public static String getType(String fileStreamHexHead) {
        for (Map.Entry<String, String> fileTypeEntry : FILE_TYPE_MAP.entrySet()) {
            if (fileStreamHexHead.toUpperCase().startsWith(fileTypeEntry.getKey().toUpperCase())) {
                return fileTypeEntry.getValue();
            }
        }
        return null;
    }

    /**
     * 根据文件流的头部信息获得文件类型
     *
     * @param fileStreamBytes 文件流头部字节
     * @return 文件类型，未找到为<code>null</code>
     */
    public static String getType(byte[] fileStreamBytes) {
        String hexHead = bytesToHexString(fileStreamBytes);
        return getType(hexHead);
    }

    /**
     * 将byte字节转16进制字符串
     *
     * @param src byte字节
     * @return 16进制的字符串
     */
    private static String bytesToHexString(byte[] src) {
        return HexUtils.encodeHexString(src, true);
    }

}
