package com.hb0730.commons.http.utils;

import com.hb0730.commons.http.CommonHttps;
import com.hb0730.commons.http.support.callback.CommonsNetCall;
import com.hb0730.commons.http.support.okhttp3.OkHttp3SyncImpl;
import okhttp3.Response;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;
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
                    public <T> void success(T response, Object... obj) throws IOException {
                        if (response instanceof SimpleHttpResponse) {
                            System.out.println(((SimpleHttpResponse) response).getBody().getBodyText());
                        } else if (response instanceof Response) {
                            System.out.println(Objects.requireNonNull(((Response) response).body()).string());
                        }
                    }

                    @Override
                    public void file(Exception e) {

                    }
                });
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
    }
}
