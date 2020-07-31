package com.hb0730.commons.lang;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * url util
 *
 * @author bing_huang
 * @date 2020/07/30 15:55
 * @since V1.0
 */
public class UrlUtils {
    /**
     * 针对ClassPath路径的伪协议前缀（兼容Spring）: "classpath:"
     */
    public static final String CLASSPATH_URL_PREFIX = "classpath:";
    /**
     * URL 前缀表示文件: "file:"
     */
    public static final String FILE_URL_PREFIX = "file:";
    /**
     * URL 前缀表示jar: "jar:"
     */
    public static final String JAR_URL_PREFIX = "jar:";
    /**
     * URL 前缀表示war: "war:"
     */
    public static final String WAR_URL_PREFIX = "war:";
    /**
     * URL 协议表示文件: "file"
     */
    public static final String URL_PROTOCOL_FILE = "file";
    /**
     * URL 协议表示Jar文件: "jar"
     */
    public static final String URL_PROTOCOL_JAR = "jar";
    /**
     * URL 协议表示zip文件: "zip"
     */
    public static final String URL_PROTOCOL_ZIP = "zip";
    /**
     * URL 协议表示WebSphere文件: "wsjar"
     */
    public static final String URL_PROTOCOL_WSJAR = "wsjar";
    /**
     * URL 协议表示JBoss zip文件: "vfszip"
     */
    public static final String URL_PROTOCOL_VFSZIP = "vfszip";
    /**
     * URL 协议表示JBoss文件: "vfsfile"
     */
    public static final String URL_PROTOCOL_VFSFILE = "vfsfile";
    /**
     * URL 协议表示JBoss VFS资源: "vfs"
     */
    public static final String URL_PROTOCOL_VFS = "vfs";
    /**
     * Jar路径以及内部文件路径的分界符: "!/"
     */
    public static final String JAR_URL_SEPARATOR = "!/";
    /**
     * WAR路径及内部文件路径分界符
     */
    public static final String WAR_URL_SEPARATOR = "*/";


    /**
     * url 编码, 默认utf-8
     *
     * @param params str
     * @return str
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     * @see UrlUtils#urlEncode(String, String)
     */
    public static String urlEncode(String params) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(params)) {
            return "";
        }
        return urlEncode(params, StandardCharsets.UTF_8.displayName());
    }

    /**
     * url 编码
     *
     * @param params url
     * @param enc    编码规则
     * @return str
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    public static String urlEncode(String params, String enc) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(params)) {
            return "";
        }
        String encode = URLEncoder.encode(params, StringUtils.isEmpty(enc) ? StandardCharsets.UTF_8.displayName() : enc);
        return encode.replace("+", "%20")
                .replace("*", "%2A")
                .replace("~", "%7E")
                .replace("/", "%2F");
    }

    /**
     * url 解码
     *
     * @param params str
     * @return str
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    public static String urlDecoder(String params) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(params)) {
            return "";
        }
        return urlEncode(params, StandardCharsets.UTF_8.displayName());
    }

    /**
     * url 解码 默认 utf8
     *
     * @param params str
     * @param enc    解码规则
     * @return str
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    public static String urlDecoder(String params, String enc) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(params)) {
            return "";
        }
        return URLDecoder.decode(params, StringUtils.isEmpty(enc) ? StandardCharsets.UTF_8.displayName() : enc);
    }

}
