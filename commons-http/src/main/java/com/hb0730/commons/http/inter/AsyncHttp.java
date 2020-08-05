package com.hb0730.commons.http.inter;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.support.callback.CommonsNetCall;

import java.util.Map;

/**
 * async http
 *
 * @author bing_huang
 * @date 2020/08/05 13:43
 * @since V1.0
 */
public interface AsyncHttp extends Http {

    /**
     * get 请求
     *
     * @param url            请求地址
     * @param commonsNetCall 回调
     */
    void get(String url, CommonsNetCall commonsNetCall);

    /**
     * get 请求
     *
     * @param url            请求地址
     * @param commonsNetCall 回调
     * @param params         请求参数
     */
    void get(String url, CommonsNetCall commonsNetCall, Map<String, String> params);

    /**
     * get请求
     *
     * @param url            请求地址
     * @param header         请求头
     * @param commonsNetCall 回调
     * @param params         请求参数
     */
    void get(String url, HttpHeader header, CommonsNetCall commonsNetCall, Map<String, String> params);

    /**
     * post请求
     *
     * @param url            请求地址
     * @param commonsNetCall 回调
     */
    void post(String url, CommonsNetCall commonsNetCall);

    /**
     * post请求
     *
     * @param url            请求地址
     * @param data           请求参数
     * @param commonsNetCall 回调
     */
    void post(String url, String data, CommonsNetCall commonsNetCall);

    /**
     * post 请求
     *
     * @param url            请求地址
     * @param data           请求参数
     * @param header         请求头
     * @param commonsNetCall 回调
     */
    void post(String url, String data, HttpHeader header, CommonsNetCall commonsNetCall);

    /**
     * post请求
     *
     * @param url            请求地址
     * @param commonsNetCall 回调
     * @param formdata       form表单
     */
    void post(String url, CommonsNetCall commonsNetCall, Map<String, String> formdata);

    /**
     * post 请求
     *
     * @param url            请求地址
     * @param header         请求头
     * @param commonsNetCall 回调
     * @param formdata       form表单
     */
    void post(String url, HttpHeader header, CommonsNetCall commonsNetCall, Map<String, String> formdata);
}
