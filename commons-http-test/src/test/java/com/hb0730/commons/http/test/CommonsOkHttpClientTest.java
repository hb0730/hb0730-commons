package com.hb0730.commons.http.test;

import com.hb0730.commons.http.HttpUtils;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.support.okhttp3.OkHttp3Impl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author bing_huang
 * @date 2020/07/31 11:29
 * @since V1.0
 */
public class CommonsOkHttpClientTest {
    @Before
    public void init() {
        HttpUtils.setHttp(new OkHttp3Impl());
    }

    @Test
    public void okhttpClientTest() {
        HttpUtils.setHttpConfig(new HttpConfig());
        System.out.println(HttpUtils.get("http://baidu.com"));

    }
}
