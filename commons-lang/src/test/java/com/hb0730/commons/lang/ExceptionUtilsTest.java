package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ExceptionUtilsTest {

    @Test
    public void getExceptionMessageTest() {
        try {

            throw new NullPointerException("异常测试");
        } catch (Exception e) {
            String message = ExceptionUtils.getExceptionMessage(e);
            log.info("异常信息,[ {} ]", message);
        }
    }
}
