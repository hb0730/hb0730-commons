package com.hb0730.commons.http;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.collection.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * http 请求头
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class HttpHeader {
    private final Map<String, String> header;

    /**
     * 构建{@link HttpHeader}
     *
     * @return {@link HttpHeader}
     * @since 2.0.3
     */
    public static HttpHeader builder() {
        return new HttpHeader();
    }

    public HttpHeader() {
        this.header = new HashMap<>(16);
    }

    public HttpHeader(Map<String, String> header) {
        this.header = header;
    }

    /**
     * 添加请求参数
     *
     * @param key   key
     * @param value value
     * @return 请求头 this{@link HttpHeader}
     */
    public HttpHeader add(String key, String value) {
        if (StringUtils.isEmpty(key)) {
            return this;
        }
        this.header.put(key, value);
        return this;
    }

    /**
     * 添加请求参数
     *
     * @param header 请求头参数
     * @return 请求头 this{@link HttpHeader}
     */
    public HttpHeader addAll(Map<String, String> header) {
        if (CollectionUtils.isEmpty(header)) {
            return this;
        }
        this.header.putAll(header);
        return this;
    }

    /**
     * 获取请求头
     *
     * @return 请求头
     */
    public Map<String, String> getHeaders() {
        return this.header;
    }

    /**
     * 获取请求头
     *
     * @param key key
     * @return 请求参数
     */
    public String getHeader(String key) {
        return this.header.get(key);
    }
}
