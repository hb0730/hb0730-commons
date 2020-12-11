package com.hb0730.commons.http.inter;

import com.hb0730.commons.http.support.callback.HttpCallback;

import java.util.Map;

/**
 * async http interface
 *
 * @author bing_huang
 * @since 1.0.1
 */
public interface AsyncHttp extends Http {

    /**
     * get 请求
     *
     * @param url          请求地址,不为空
     * @param httpCallback 回调
     */
    void get(String url, HttpCallback httpCallback);

    /**
     * get 请求
     *
     * @param url          请求地址,不为空
     * @param params       请求参数
     * @param httpCallback 回调
     */
    void get(String url, Map<String, String> params, HttpCallback httpCallback);

    /**
     * post请求
     *
     * @param url          请求地址,不为空
     * @param httpCallback 回调
     */
    void post(String url, HttpCallback httpCallback);

    /**
     * post请求
     *
     * @param url          请求地址
     * @param dataJson     json参数
     * @param httpCallback 回调
     */
    void post(String url, String dataJson, HttpCallback httpCallback);

    /**
     * post请求
     *
     * @param url          请求地址,不为空
     * @param formdata     form 参数
     * @param httpCallback 回调
     */
    void post(String url, Map<String, String> formdata, HttpCallback httpCallback);
}
