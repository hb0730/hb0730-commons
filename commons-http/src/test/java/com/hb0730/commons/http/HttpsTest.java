package com.hb0730.commons.http;

import com.hb0730.commons.http.support.callback.HttpCallback;
import com.hb0730.commons.http.support.httpclient.HttpClientSyncImpl;
import com.hb0730.commons.http.support.okhttp3.OkHttp3SyncImpl;
import com.hb0730.commons.http.utils.HttpAsync;
import com.hb0730.commons.http.utils.HttpSync;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class HttpsTest {

    @Test
    public void syncClientTest() {
        HttpSync http = Https.SYNC.getHttp();
        http.setHttp(new HttpClientSyncImpl());
        //http://poetry.apiopen.top/getTime
        String result = http.get("");
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void syncOkHttp3Test() {
        HttpSync http = Https.SYNC.getHttp();
        http.setHttp(new OkHttp3SyncImpl());
        //http://poetry.apiopen.top/getTime
        String result = http.get("");
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void asyncTest() {
        try {
            HttpAsync async = Https.ASYNC.getHttp();
            //http://poetry.apiopen.top/getTime
            async.get("", new HttpCallback() {
                @Override
                public void success(String result) throws IOException {

                    Assert.assertNotNull("获取失败", result);
                }

                @Override
                public void failure(Exception e) {

                }
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
