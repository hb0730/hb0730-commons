package com.hb0730.commons.http.test;

import com.hb0730.commons.http.CommonHttps;
import com.hb0730.commons.http.support.httpclient.HttpClientSyncImpl;
import com.hb0730.commons.http.utils.HttpSyncUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bing_huang
 * @date 2020/08/05 16:22
 * @since V1.0
 */
public class CommHttpUtilsTest {
    @Test
    public void httpSyncTest() {

    }

    @Test
    public void httpAsyncTest() {

    }

    @Test
    public void httpClientSyncTest() {
        HttpSyncUtils utils = CommonHttps.sync().setHttp(new HttpClientSyncImpl());
//        HttpClientTest.getSyncTest(utils);
//        HttpClientTest.getSyncParamsTest(utils);
        HttpClientTest.postSyncTest(utils);
//        HttpClientTest.postSyncParamsTest(utils);

    }

    @Test
    public void httpClientAsyncTest() throws InterruptedException {

    }

    public static class HttpClientTest {
        public static void getSyncTest(HttpSyncUtils utils) {
            System.out.println(utils.get("https://baidu.com"));
        }

        public static void getSyncParamsTest(HttpSyncUtils utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "手机");
            System.out.println(utils.get("http://localhost/", params));
        }

        public static void postSyncTest(HttpSyncUtils utils) {
            System.out.println(utils.post("https://baidu.com"));
        }

        public static void postSyncParamsTest(HttpSyncUtils utils) {
            Map<String, String> params = new HashMap<>();
            params.put("ie", "utf-8");
            params.put("f", "8");
            params.put("rsv_bp", "1");
            params.put("ch", "");
            params.put("tn", "baiduerr");
            params.put("bar", "");
            params.put("wd", "gg");
            System.out.println(utils.post("https://baidu.com", params));
        }
    }
}
