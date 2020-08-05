package com.hb0730.commons.http.support.okhttp3;

import com.hb0730.commons.http.support.callback.CommonsNetCall;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkSyncHttp3AsyncImplTest {

    @Test
    public void postTest() throws InterruptedException {

        OkHttp3AsyncImpl http3Async = new OkHttp3AsyncImpl();
        http3Async.post("https://baidu.com", new CommonsNetCall() {
            @Override
            public <T> void success(T response, Object... obj) throws IOException {
                if (response instanceof Response) {
                    ResponseBody body = ((Response) response).body();
                    assert body != null;
                    String string = body.string();
                    System.out.println("响应数据:[" + string + "]");
                }
            }

            @Override
            public void file(Exception e) {

            }
        });
        Thread.sleep(TimeUnit.SECONDS.toMillis(30));
    }
}
