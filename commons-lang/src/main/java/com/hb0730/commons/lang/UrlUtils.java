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
