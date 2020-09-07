package com.hb0730.commons.lang.convert;

import org.junit.Assert;
import org.junit.Test;

public class ConverterRegistryTest {

    @Test
    public void convertTest() {
        Boolean v = ConverterRegistry.getInstance().convert(Boolean.TYPE, "true");
        Assert.assertTrue("转换异常", v);
    }
}
