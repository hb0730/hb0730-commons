package com.hb0730.commons.http.inter;

import com.hb0730.commons.http.HttpHeader;

import java.util.Map;

/**
 * sync http  接口
 *
 * @author bing_huang
 * @date 2020/07/30 15:13
 * @since V1.0
 */
public interface SyncHttp extends Http {

    /**
     * get 请求
     *
     * @param url 请求地址
     * @return 响应结果
     */
    String get(String url);

    /**
     * get 请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 响应结果
     */
    String get(String url, Map<String, String> params);

    /**
     * get请求
     *
     * @param url    请求地址
     * @param header 请求头
     * @param params 请求参数
     * @return 响应结果
     */
    String get(String url, HttpHeader header, Map<String, String> params);

    /**
     * post请求
     *
     * @param url 请求地址
     * @return 响应结果
     */
    String post(String url);

    /**
     * post请求
     *
     * @param url  请求地址
     * @param data 请求参数
     * @return 响应结果
     */
    String post(String url, String data);

    /**
     * post 请求
     *
     * @param url     请求地址
     * @param data    请求参数
     * @param header 请求头
     * @return 响应结果
     */
    String post(String url, String data, HttpHeader header);

    /**
     * post请求
     *
     * @param url      请求地址
     * @param formdata form表单
     * @return 响应结果
     */
    String post(String url, Map<String, String> formdata);

    /**
     * post 请求
     *
     * @param url      请求地址
     * @param header   请求头
     * @param formdata form表单
     * @return 详情结果
     */
    String post(String url, HttpHeader header, Map<String, String> formdata);

}
