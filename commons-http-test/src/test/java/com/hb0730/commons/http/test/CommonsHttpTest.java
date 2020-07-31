package com.hb0730.commons.http.test;

import com.hb0730.commons.http.HttpUtils;
import org.junit.Test;

/**
 * @author bing_huang
 * @date 2020/07/31 11:33
 * @since V1.0
 */
public class CommonsHttpTest {

    @Test
    public void test() {
        System.out.println(HttpUtils.get("http://www.baidu.com"));
    }
}
