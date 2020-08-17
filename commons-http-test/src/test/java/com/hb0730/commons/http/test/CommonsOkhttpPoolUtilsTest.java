package com.hb0730.commons.http.test;

import com.hb0730.commons.http.support.callback.CommonsNetCall;
import com.hb0730.commons.http.utils.HttpAsync;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommonsOkhttpPoolUtilsTest {

    @Test
    public void asyncTest() throws InterruptedException {
        HttpAsync async = CommonsOkhttpPoolUtils.async();
        async.get("http://localhost:10000/", new CommonsNetCall() {
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
        async.get("http://localhost:10000/", new CommonsNetCall() {
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
        async = CommonsOkhttpPoolUtils.async();
        async.get("http://localhost:10000/", new CommonsNetCall() {
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
        async.get("http://localhost:10000/", new CommonsNetCall() {
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

        Thread.sleep(TimeUnit.SECONDS.toMillis(10));
    }
}
