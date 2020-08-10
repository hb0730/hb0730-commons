package com.hb0730.commons.http.test;

import com.hb0730.commons.http.support.callback.CommonsNetCall;
import com.hb0730.commons.http.utils.HttpAsyncUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommonsHttpClientPoolUtilsTest {

    @Test
    public void asyncTest() throws InterruptedException {
        HttpAsyncUtils async = CommonsHttpClientPoolUtils.async();
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
        async = CommonsHttpClientPoolUtils.async();
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