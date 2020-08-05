package com.hb0730.commons.http.support.callback;

import okhttp3.Request;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;

import java.io.IOException;

/**
 * 异步请求回调
 *
 * @author bing_huang
 * @date 2020/08/05 10:12
 * @since V1.0
 */
public interface CommonsNetCall {

    /**
     * 请求成功，但不代表响应结果正确
     *
     * @param response 响应
     * @param obj      其余参数,比如Okhttp3的 {@link okhttp3.Call}
     * @param <T>      响应类型 例如:{@link org.apache.hc.core5.http.message.BasicHttpResponse},{@link okhttp3.Response}
     * @throws IOException 异常
     * @see com.hb0730.commons.http.support.okhttp3.OkHttp3AsyncImpl#exec(Request.Builder, CommonsNetCall)
     * @see com.hb0730.commons.http.support.httpclient.HttpClientAsyncImpl#exec(SimpleHttpRequest, CommonsNetCall)
     */
    <T> void success(T response, Object... obj) throws IOException;

    /**
     * 请求参数
     *
     * @param e 异常
     */
    void file(Exception e);

}
