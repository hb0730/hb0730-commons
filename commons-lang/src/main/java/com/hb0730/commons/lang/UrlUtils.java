package com.hb0730.commons.lang;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * url util
 *
 * @author bing_huang
 * @since 1.0.0
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
        Validate.notNull(params, "params must be not null");
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
        Validate.notNull(params, "params must be not null");
        String encode = URLEncoder.encode(params, StringUtils.isBlank(enc) ? StandardCharsets.UTF_8.displayName() : enc);
        return encode.replace("+", "%20")
                .replace("*", "%2A")
                .replace("~", "%7E")
                .replace("/", "%2F");
    }

    /**
     * url 解码,默认UTF-8
     *
     * @param params str 需要编码的参数
     * @return str 已经完成编码的参数
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     * @see #urlDecoder(String, String)
     */
    public static String urlDecoder(String params) throws UnsupportedEncodingException {
        Validate.notNull(params, "params must be not null");

        return urlDecoder(params, StandardCharsets.UTF_8.displayName());
    }

    /**
     * url 解码 默认 utf8
     *
     * @param params str,不为null
     * @param enc    解码规则
     * @return str 已解码字符串
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    public static String urlDecoder(String params, String enc) throws UnsupportedEncodingException {
        Validate.notNull(params, "params must be not null");
        return URLDecoder.decode(params, StringUtils.isBlank(enc) ? StandardCharsets.UTF_8.displayName() : enc);
    }

}
