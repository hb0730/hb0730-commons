package com.hb0730.commons.lang.convert;

import org.junit.Test;

public class ConvertUtilsTest {

    @Test
    public void toIntTest() {
        System.out.println("convert String \"1\" to :" + ConvertUtils.toInt("1"));
        System.out.println("convert Number 1 to :" + ConvertUtils.toInt(1));
        System.out.println("convert Non Number to :" + ConvertUtils.toInt("oo"));
    }

    @Test
    public void testToIntTest() {
        System.out.println("convert String \"1\" to:" + ConvertUtils.toInt("1", 0));
        System.out.println("convert Number 1 to :" + ConvertUtils.toInt(1, 0));
        System.out.println("convert Non Number to :" + ConvertUtils.toInt("oo", 0));
    }

    @Test
    public void toStrTest() {
        System.out.println(ConvertUtils.toStr("sss", "hh"));
        System.out.println(ConvertUtils.toStr(null, "hh"));
    }
}
