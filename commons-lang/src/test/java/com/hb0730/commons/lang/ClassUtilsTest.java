package com.hb0730.commons.lang;

import org.junit.Test;

public class ClassUtilsTest {


    @Test
    public void testIsPresentTest() {
        System.out.println("httpClient:" + ClassUtils.isPresent("java.net.http.HttpClient", this.getClass().getClassLoader()));
        System.out.println("StringUtils:" + ClassUtils.isPresent("com.hb0730.commons.lang.StringUtils", this.getClass().getClassLoader()));
    }
}
