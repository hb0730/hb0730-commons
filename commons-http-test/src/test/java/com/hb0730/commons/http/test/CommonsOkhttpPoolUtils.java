package com.hb0730.commons.http.test;

import com.hb0730.commons.http.CommonHttps;
import com.hb0730.commons.http.inter.AbstractAsyncHttp;
import com.hb0730.commons.http.support.okhttp3.OkHttp3AsyncImpl;
import com.hb0730.commons.http.utils.HttpAsyncUtils;

/**
 * @author bing_huang
 * @date 2020/08/06 18:00
 * @since V1.0
 */
public class CommonsOkhttpPoolUtils {
    private static AbstractAsyncHttp http;

    static {
        http = new OkHttp3AsyncImpl();
    }

    public static HttpAsyncUtils async() {
        return CommonHttps.async().setHttp(http);
    }
}
