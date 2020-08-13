package com.hb0730.commons.lang.convert;

import org.junit.Assert;
import org.junit.Test;

public class ConvertUtilsTest {

    @Test
    public void toIntTest() {
        Integer convertResult = ConvertUtils.toInt("1");
        Assert.assertEquals("转换不相等", Integer.valueOf(1), convertResult);
        convertResult = ConvertUtils.toInt(1);
        Assert.assertEquals("转换不相等", Integer.valueOf(1), convertResult);
        convertResult = ConvertUtils.toInt("oo");
        Assert.assertEquals("转换不相等oo", "oo", convertResult);
    }

    @Test
    public void testToIntTest() {
        Integer convertResult = ConvertUtils.toInt("1", 0);
        Assert.assertEquals("转换不相等", Integer.valueOf(1), convertResult);
        convertResult = ConvertUtils.toInt(1, 0);
        Assert.assertEquals("转换不相等", Integer.valueOf(1), convertResult);
        convertResult = ConvertUtils.toInt("o", 0);
        Assert.assertEquals("转换不相等", Integer.valueOf(0), convertResult);
    }

    @Test
    public void toStrTest() {
        String result = ConvertUtils.toStr("sss", "hhh");
        Assert.assertEquals("转换不相等", "sss", result);
        result = ConvertUtils.toStr(0, "hh");
        Assert.assertEquals("转换不相等", "0", result);
        result = ConvertUtils.toStr(null, "hh");
        Assert.assertEquals("转换不相等", "hh", result);
    }
}
