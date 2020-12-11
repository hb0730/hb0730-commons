package com.hb0730.commons.http.utils;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.constants.Constants;
import com.hb0730.commons.http.exception.HttpException;
import com.hb0730.commons.http.inter.AbstractAsyncHttp;
import com.hb0730.commons.http.inter.AsyncHttp;
import com.hb0730.commons.http.support.callback.HttpCallback;
import com.hb0730.commons.http.support.httpclient.HttpClientAsyncImpl;
import com.hb0730.commons.http.support.okhttp3.OkHttp3AsyncImpl;
import com.hb0730.commons.lang.ClassUtils;

import java.util.Map;

/**
 * 异步utils
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class HttpAsync implements AsyncHttp {
    private AbstractAsyncHttp proxy;

    private void selectHttpProxy() {
        AbstractAsyncHttp defaultProxy = null;
        ClassLoader classLoader = HttpSync.class.getClassLoader();
        if (ClassUtils.isPresent("org.apache.hc.client5.http.impl.async.HttpAsyncClients", classLoader)) {
            defaultProxy = getHttpProxy(HttpClientAsyncImpl.class);
        }
        if (ClassUtils.isPresent("okhttp3.OkHttpClient", classLoader)) {
            defaultProxy = getHttpProxy(OkHttp3AsyncImpl.class);
        }
        if (defaultProxy == null) {
            throw new HttpException("Has no HttpImpl defined in environment!");
        }
        proxy = defaultProxy;
    }

    private <T extends AbstractAsyncHttp> AbstractAsyncHttp getHttpProxy(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable e) {
            return null;
        }
    }

    private synchronized void checkHttpNotNull(AsyncHttp proxy) {
        if (null == proxy) {
            selectHttpProxy();
        }
    }

    public HttpAsync setHttp(AbstractAsyncHttp http) {
        proxy = http;
        return this;
    }

    public HttpAsync setHttpConfig(HttpConfig config) {
        checkHttpNotNull(proxy);
        if (null == config) {
            config = HttpConfig.builder().timeout(Constants.DEFAULT_TIMEOUT).build();
        }
        proxy.setHttpConfig(config);
        return this;
    }

    /**
     * 设置请求头
     *
     * @param header {@link HttpHeader}
     * @return {@link HttpAsync}
     * @since 2.0.3
     */
    public HttpAsync setHeader(HttpHeader header) {
        checkHttpNotNull(proxy);
        proxy.setHeader(header);
        return this;
    }

    /**
     * GET 请求
     *
     * @param url          URL
     * @param httpCallback 回调
     */
    @Override
    public void get(String url, HttpCallback httpCallback) {
        checkHttpNotNull(proxy);
        proxy.get(url, httpCallback);
    }

    /**
     * GET 请求
     *
     * @param url          URL
     * @param params       参数
     * @param httpCallback 回调
     */
    @Override
    public void get(String url, Map<String, String> params, HttpCallback httpCallback) {
        checkHttpNotNull(proxy);
        proxy.get(url, params, httpCallback);
    }

    /**
     * POST 请求
     *
     * @param url          URL
     * @param httpCallback 回调
     */
    @Override
    public void post(String url, HttpCallback httpCallback) {
        checkHttpNotNull(proxy);
        proxy.post(url, httpCallback);
    }

    /**
     * POST 请求
     *
     * @param url          URL
     * @param data         JSON 参数
     * @param httpCallback 回调
     */
    @Override
    public void post(String url, String data, HttpCallback httpCallback) {
        checkHttpNotNull(proxy);
        proxy.post(url, data, httpCallback);
    }

    /**
     * POST 请求
     *
     * @param url          URL
     * @param params       form 参数
     * @param httpCallback 回调
     */
    @Override
    public void post(String url, Map<String, String> params, HttpCallback httpCallback) {
        checkHttpNotNull(proxy);
        proxy.post(url, params, httpCallback);
    }
}
