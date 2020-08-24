package com.hb0730.commons.http.utils;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.constants.Constants;
import com.hb0730.commons.http.exception.CommonHttpException;
import com.hb0730.commons.http.inter.AbstractAsyncHttp;
import com.hb0730.commons.http.inter.AsyncHttp;
import com.hb0730.commons.http.support.callback.CommonsNetCall;
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
            throw new CommonHttpException("Has no HttpImpl defined in environment!");
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
     * GET 请求
     *
     * @param url            URL
     * @param commonsNetCall 回调
     */
    @Override
    public void get(String url, CommonsNetCall commonsNetCall) {
        checkHttpNotNull(proxy);
        proxy.get(url, commonsNetCall);
    }

    /**
     * GET 请求
     *
     * @param url            URL
     * @param commonsNetCall 回调
     * @param params         参数
     */
    @Override
    public void get(String url, CommonsNetCall commonsNetCall, Map<String, String> params) {
        checkHttpNotNull(proxy);
        proxy.get(url, commonsNetCall, params);
    }

    /**
     * GET 请求
     *
     * @param url            URL
     * @param header         请求头
     * @param commonsNetCall 回调
     * @param params         参数
     */
    @Override
    public void get(String url, HttpHeader header, CommonsNetCall commonsNetCall, Map<String, String> params) {
        checkHttpNotNull(proxy);
        proxy.get(url, header, commonsNetCall, params);
    }

    /**
     * POST 请求
     *
     * @param url            URL
     * @param commonsNetCall 回调
     */
    @Override
    public void post(String url, CommonsNetCall commonsNetCall) {
        checkHttpNotNull(proxy);
        proxy.post(url, commonsNetCall);
    }

    /**
     * POST 请求
     *
     * @param url            URL
     * @param data           JSON 参数
     * @param commonsNetCall 回调
     */
    @Override
    public void post(String url, String data, CommonsNetCall commonsNetCall) {
        checkHttpNotNull(proxy);
        proxy.post(url, data, commonsNetCall);
    }

    /**
     * POST 请求
     *
     * @param url            URL
     * @param data           JSON 参数
     * @param header         请求头
     * @param commonsNetCall 回调
     */
    @Override
    public void post(String url, String data, HttpHeader header, CommonsNetCall commonsNetCall) {
        checkHttpNotNull(proxy);
        proxy.post(url, data, header, commonsNetCall);
    }

    /**
     * POST 请求
     *
     * @param url            URL
     * @param commonsNetCall 回调
     * @param params         form 参数
     */
    @Override
    public void post(String url, CommonsNetCall commonsNetCall, Map<String, String> params) {
        checkHttpNotNull(proxy);
        proxy.post(url, commonsNetCall, params);
    }

    /**
     * POST 请求
     *
     * @param url            URL
     * @param header         请求头
     * @param commonsNetCall 回调
     * @param params         form 参数
     */
    @Override
    public void post(String url, HttpHeader header, CommonsNetCall commonsNetCall, Map<String, String> params) {
        checkHttpNotNull(proxy);
        proxy.post(url, header, commonsNetCall, params);
    }
}
