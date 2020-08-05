package com.hb0730.commons.http.support.httpclient;

import com.hb0730.commons.http.support.callback.CommonsNetCall;
import org.apache.hc.client5.http.async.methods.SimpleBody;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.core5.http.message.BasicHttpResponse;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SyncHttpClientAsyncImplTest {

    @Test
    public void postTest() throws InterruptedException, ExecutionException {
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
//        async.post("https://baidu.com", new CommonsNetCall<BasicHttpResponse>() {
//            @Override
//            public void success(BasicHttpResponse response, Object... obj) {
//                if (response instanceof SimpleHttpResponse) {
//                    SimpleHttpResponse simpleHttpResponse = (SimpleHttpResponse) response;
//                    SimpleBody body = simpleHttpResponse.getBody();
//                    String text = body.getBodyText();
//                    System.out.println("请求成功:[" + text + "]");
//                }
//            }
//
//            @Override
//            public void file(Exception e) {
//                e.printStackTrace();
//            }
//        });
        Thread.sleep(TimeUnit.SECONDS.toMillis(30));
    }
}
