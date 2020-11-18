package com.hb0730.commons.http.test;

import com.hb0730.commons.http.Https;
import com.hb0730.commons.http.inter.AbstractAsyncHttp;
import com.hb0730.commons.http.support.okhttp3.OkHttp3AsyncImpl;
import com.hb0730.commons.http.utils.HttpAsync;

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

    public static HttpAsync async() {
        return ((HttpAsync) Https.ASYNC.getHttp()).setHttp(http);
    }
}
