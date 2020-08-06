package com.hb0730.commons.http.utils;

import com.hb0730.commons.http.CommonHttps;
import com.hb0730.commons.http.support.callback.CommonsNetCall;
import com.hb0730.commons.http.support.okhttp3.OkHttp3SyncImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommonHttpsTest {

    @Test
    public void testSync() {
        HttpSyncUtils sync = CommonHttps.sync();
        String s = sync.get("https://baidu.com");
        System.out.println(s);
        s = CommonHttps.sync().setHttp(new OkHttp3SyncImpl())
                .get("https://baidu.com");
        System.out.println(s);
    }

    @Test
    public void testAsync() throws InterruptedException {
        CommonHttps.async()
                .get("https://baidu.com", new CommonsNetCall() {
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
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
    }
}
