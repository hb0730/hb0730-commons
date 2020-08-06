package com.hb0730.commons.http.support.callback;

import okhttp3.Request;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.nio.support.BasicRequestProducer;

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
     * 响应成功 200<=http code<300
     *
     * @param result 响应结果
     * @throws IOException 异常
     * @see com.hb0730.commons.http.support.okhttp3.OkHttp3AsyncImpl#exec(Request.Builder, CommonsNetCall)
     * @see com.hb0730.commons.http.support.httpclient.HttpClientAsyncImpl#exec(HttpUriRequestBase, BasicRequestProducer, CommonsNetCall)
     */
    void success(String result) throws IOException;

    /**
     * 响应成功, 200<=http code<300
     *
     * @param result 响应结果
     * @throws IOException 异常
     * @see com.hb0730.commons.http.support.okhttp3.OkHttp3AsyncImpl#exec(Request.Builder, CommonsNetCall)
     * @see com.hb0730.commons.http.support.httpclient.HttpClientAsyncImpl#exec(HttpUriRequestBase, BasicRequestProducer, CommonsNetCall)
     */
    void success(byte[] result) throws IOException;

    /**
     * 请求参数
     *
     * @param e 异常
     */
    void file(Exception e);

}
