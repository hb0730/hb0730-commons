package com.hb0730.commons.http.utils;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.constants.Constants;
import com.hb0730.commons.http.exception.HttpException;
import com.hb0730.commons.http.inter.AbstractSyncHttp;
import com.hb0730.commons.http.inter.SyncHttp;
import com.hb0730.commons.http.support.httpclient.HttpClientSyncImpl;
import com.hb0730.commons.http.support.okhttp3.OkHttp3SyncImpl;
import com.hb0730.commons.lang.ClassUtils;

import java.util.Map;

/**
 * 同步请求 工具类
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class HttpSync implements SyncHttp {
    private static AbstractSyncHttp proxy;

    private void selectHttpProxy() {
        AbstractSyncHttp defaultProxy = null;
        ClassLoader classLoader = HttpSync.class.getClassLoader();
        if (ClassUtils.isPresent("org.apache.http.impl.client.HttpClients", classLoader)) {
            defaultProxy = getHttpProxy(HttpClientSyncImpl.class);
        }
        if (ClassUtils.isPresent("okhttp3.OkHttpClient", classLoader)) {
            defaultProxy = getHttpProxy(OkHttp3SyncImpl.class);
        }
        if (defaultProxy == null) {
            throw new HttpException("Has no HttpImpl defined in environment!");
        }
        proxy = defaultProxy;
    }

    private <T extends AbstractSyncHttp> AbstractSyncHttp getHttpProxy(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable e) {
            return null;
        }
    }

    private synchronized void checkHttpNotNull(SyncHttp proxy) {
        if (null == proxy) {
            selectHttpProxy();
        }
    }

    public HttpSync setHttp(AbstractSyncHttp http) {
        proxy = http;
        return this;
    }


    public HttpSync setHttpConfig(HttpConfig config) {
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
     * @return {@link HttpSync}
     * @since 2.0.3
     */
    public HttpSync setHeader(HttpHeader header) {
        checkHttpNotNull(proxy);
        proxy.setHeader(header);
        return this;
    }

    /**
     * GET 请求
     *
     * @param url URL
     * @return 结果
     */
    @Override
    public String get(String url) {
        checkHttpNotNull(proxy);
        return proxy.get(url);
    }

    /**
     * GET 请求
     *
     * @param url    URL
     * @param params 参数
     * @return 结果
     */
    @Override
    public String get(String url, Map<String, String> params) {
        checkHttpNotNull(proxy);
        return proxy.get(url, params);
    }

    /**
     * POST 请求
     *
     * @param url URL
     * @return 结果
     */
    @Override
    public String post(String url) {
        checkHttpNotNull(proxy);
        return proxy.post(url);
    }

    /**
     * POST 请求
     *
     * @param url  URL
     * @param data JSON 参数
     * @return 结果
     */
    @Override
    public String post(String url, String data) {
        checkHttpNotNull(proxy);
        return proxy.post(url, data);
    }

    /**
     * POST 请求
     *
     * @param url    URL
     * @param params form 参数
     * @return 结果
     */
    @Override
    public String post(String url, Map<String, String> params) {
        checkHttpNotNull(proxy);
        return proxy.post(url, params);
    }

}
