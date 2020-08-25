package com.hb0730.commons.http.support.httpclient;

import com.hb0730.commons.http.support.callback.CommonsNetCall;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SyncHttpClientAsyncImplTest {

    @Test
    public void postTest() throws InterruptedException {
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
        async.post("https://baidu.com", new CommonsNetCall() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull(result);
                System.out.println(result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(TimeUnit.SECONDS.toMillis(30));
    }
}
