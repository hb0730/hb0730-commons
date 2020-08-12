package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

@Slf4j
public class ExceptionUtilsTest {

    @Test
    public void getExceptionMessageTest() {
        try {
            Objects.requireNonNull(null, "测试异常");
        } catch (Exception e) {
            String message = ExceptionUtils.getExceptionMessage(e);
            log.info("异常信息,[ {} ]", message);
            Assert.assertNotNull(message);
        }
    }
}
