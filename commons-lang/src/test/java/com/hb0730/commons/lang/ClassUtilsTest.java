package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class ClassUtilsTest {

    @Test
    public void isPresentTest() {
        Assert.assertTrue("httpClient不能被加载", ClassUtils.isPresent("java.net.http.HttpClient", this.getClass().getClassLoader()));
    }

    @Test
    public void getDefaultValueTest() {
        Object value = ClassUtils.getDefaultValue(int.class);
        log.info("integer 默认值 {}", value);
    }

    @Test
    public void isBasicClassTest() {
        boolean basicClass = ClassUtils.isBasicClass(Integer.class);
        Assert.assertTrue("对象不为包装类型", basicClass);
    }
}
