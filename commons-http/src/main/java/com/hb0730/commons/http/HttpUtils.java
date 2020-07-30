package com.hb0730.commons.http;

import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.constants.Constants;
import com.hb0730.commons.http.exception.CommonHttpException;
import com.hb0730.commons.http.support.httpclient.HttpClientImpl;
import com.hb0730.commons.http.support.okhttp3.OkHttp3Impl;
import com.hb0730.commons.lang.ClassUtils;

import java.util.Map;

/**
 * 请求工具类
 *
 * @author bing_huang
 * @date 2020/07/30 17:22
 * @since V1.0
 */
public class HttpUtils {
    private AbstractHttp proxy;

    private void selectHttpProxy() {
        AbstractHttp defaultProxy = null;
        ClassLoader classLoader = HttpUtils.class.getClassLoader();
        if (ClassUtils.isPresent("java.net.http.HttpClient", classLoader)) {
            defaultProxy = getHttpProxy(HttpClientImpl.class);
        }
        if (ClassUtils.isPresent("okhttp3.OkHttpClient", classLoader)) {
            defaultProxy = getHttpProxy(OkHttp3Impl.class);
        }
        if (defaultProxy == null) {
            throw new CommonHttpException("Has no HttpImpl defined in environment!");
        }
        proxy = defaultProxy;
    }

    private static <T extends AbstractHttp> AbstractHttp getHttpProxy(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable e) {
            return null;
        }
    }

    private void checkHttpNotNull(Http proxy) {
        if (null == proxy) {
            selectHttpProxy();
        }
    }

    public HttpUtils(AbstractHttp http) {
        this.proxy = http;
    }

    public void setHttpConfig(HttpConfig config) {
        checkHttpNotNull(proxy);
        if (null == config) {
            config = HttpConfig.builder().timeout(Constants.DEFAULT_TIMEOUT).build();
        }
        proxy.setHttpConfig(config);
    }

    /**
     * GET 请求
     *
     * @param url URL
     * @return 结果
     */
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
    public String get(String url, Map<String, String> params) {
        checkHttpNotNull(proxy);
        return proxy.get(url, params);
    }

    /**
     * GET 请求
     *
     * @param url    URL
     * @param header 请求头
     * @param params 参数
     * @return 结果
     */
    public String get(String url, HttpHeader header, Map<String, String> params) {
        checkHttpNotNull(proxy);
        return proxy.get(url, header, params);
    }

    /**
     * POST 请求
     *
     * @param url URL
     * @return 结果
     */
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
    public String post(String url, String data) {
        checkHttpNotNull(proxy);
        return proxy.post(url, data);
    }

    /**
     * POST 请求
     *
     * @param url    URL
     * @param data   JSON 参数
     * @param header 请求头
     * @return 结果
     */
    public String post(String url, String data, HttpHeader header) {
        checkHttpNotNull(proxy);
        return proxy.post(url, data, header);
    }

    /**
     * POST 请求
     *
     * @param url    URL
     * @param params form 参数
     * @return 结果
     */
    public String post(String url, Map<String, String> params) {
        checkHttpNotNull(proxy);
        return proxy.post(url, params);
    }

    /**
     * POST 请求
     *
     * @param url    URL
     * @param header 请求头
     * @param params form 参数
     * @return 结果
     */
    public String post(String url, HttpHeader header, Map<String, String> params) {
        checkHttpNotNull(proxy);
        return proxy.post(url, header, params);
    }

}
