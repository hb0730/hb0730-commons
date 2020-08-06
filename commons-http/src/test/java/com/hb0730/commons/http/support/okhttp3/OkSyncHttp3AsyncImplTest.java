package com.hb0730.commons.http.support.okhttp3;

import com.hb0730.commons.http.support.callback.CommonsNetCall;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkSyncHttp3AsyncImplTest {

    @Test
    public void postTest() throws InterruptedException {

        OkHttp3AsyncImpl http3Async = new OkHttp3AsyncImpl();
        http3Async.post("https://baidu.com", new CommonsNetCall() {
            @Override
            public void success(String result) throws IOException {
                System.out.println(result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {

            }
        });
        Thread.sleep(TimeUnit.SECONDS.toMillis(30));
    }
}
