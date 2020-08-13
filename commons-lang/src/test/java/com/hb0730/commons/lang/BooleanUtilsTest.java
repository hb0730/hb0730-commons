package com.hb0730.commons.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class BooleanUtilsTest {

    @Test
    public void toBooleanTest() {
        boolean b = BooleanUtils.toBoolean("true");
        Assert.assertTrue("转换失败", b);
        b = BooleanUtils.toBoolean("yes");
        Assert.assertTrue("转换失败", b);
        b = BooleanUtils.toBoolean("on");
        Assert.assertTrue("转换失败", b);
        b = BooleanUtils.toBoolean("y");
        Assert.assertTrue("转换失败", b);
        b = BooleanUtils.toBoolean("1");
        Assert.assertTrue("转换失败", b);
        b = BooleanUtils.toBoolean("ok");
        Assert.assertTrue("转换失败", b);
        b = BooleanUtils.toBoolean(null);
        Assert.assertTrue("转换失败", b);
        b = BooleanUtils.toBoolean("asd");
        Assert.assertTrue("转换失败", b);

    }

    @Test
    public void toIntTest() {
        boolean b = true;
        int i = BooleanUtils.toInt(b);
        Assert.assertNotNull(i);
        log.info("true to int {}", i);
        b = false;
        i = BooleanUtils.toInt(b);
        Assert.assertNotNull(i);

        log.info("false to int {}", i);
    }

    @Test
    public void toIntegerTest() {
        Boolean b = Boolean.TRUE;
        Integer i = BooleanUtils.toInteger(b);
        Assert.assertNotNull(i);
        log.info("true to int {}", i);
        b = Boolean.FALSE;

        i = BooleanUtils.toInt(b);
        Assert.assertNotNull(i);

        log.info("false to int {}", i);
    }

    @Test
    public void toCharTest() {
        boolean b = true;
        char c = BooleanUtils.toChar(b);
        Assert.assertNotNull(c);
        log.info("true to char {}", c);
        b = false;
        c = BooleanUtils.toChar(b);
        Assert.assertNotNull(c);
        log.info("false to char {}", c);
    }

    @Test
    public void toCharacterTest() {
        Boolean b = Boolean.TRUE;
        Character c = BooleanUtils.toCharacter(b);
        Assert.assertNotNull(c);
        log.info("true to char {}", c);
        b = Boolean.FALSE;
        c = BooleanUtils.toChar(b);
        Assert.assertNotNull(c);
        log.info("false to char {}", c);
    }

    @Test
    public void toByteTest() {
        boolean b = true;
        byte result = BooleanUtils.toByte(b);
        Assert.assertNotNull(result);
        log.info("true to byte {}", result);
        b = false;
        result = BooleanUtils.toByte(b);
        Assert.assertNotNull(result);
        log.info("false to byte {}", result);
    }

    @Test
    public void toByteObjTest() {
        Boolean b = Boolean.TRUE;
        Byte result = BooleanUtils.toByteObj(b);
        Assert.assertNotNull(result);
        log.info("true to byte {}", result);
        b = Boolean.FALSE;
        result = BooleanUtils.toByteObj(b);
        Assert.assertNotNull(result);
        log.info("false to byte {}", result);
    }

    @Test
    public void toLongTest() {
        boolean b = true;
        long result = BooleanUtils.toLong(b);
        Assert.assertNotNull(result);
        log.info("true to long {}", result);

        b = false;
        result = BooleanUtils.toLong(b);
        log.info("false to long {}", result);
    }

    @Test
    public void toLongObjTest() {
        Boolean b = Boolean.TRUE;
        Long result = BooleanUtils.toLongObj(b);
        Assert.assertNotNull(result);
        log.info("true to long {}", result);
        b = Boolean.FALSE;
        result = BooleanUtils.toLongObj(b);
        Assert.assertNotNull(result);
        log.info("false to long {}", result);
    }

    @Test
    public void toShortTest() {
        boolean b = true;
        short result = BooleanUtils.toShort(b);
        Assert.assertNotNull(result);
        log.info("true to short {}", result);

        b = false;
        result = BooleanUtils.toShort(b);
        log.info("false to short {}", result);
    }

    @Test
    public void toShortObjTest() {
        Boolean b = Boolean.TRUE;
        Short result = BooleanUtils.toShort(b);
        Assert.assertNotNull(result);
        log.info("true to short {}", result);
        b = Boolean.FALSE;
        result = BooleanUtils.toShort(b);
        Assert.assertNotNull(result);
        log.info("false to short {}", result);
    }

    @Test
    public void toFloatTest() {
        boolean b = true;
        float result = BooleanUtils.toFloat(b);
        Assert.assertNotNull(result);
        log.info("true to float {}", result);

        b = false;
        result = BooleanUtils.toFloat(b);
        log.info("false to float {}", result);
    }

    @Test
    public void toFloatObjTest() {
        Boolean b = Boolean.TRUE;
        Float result = BooleanUtils.toFloatObj(b);
        Assert.assertNotNull(result);
        log.info("true to float {}", result);
        b = Boolean.FALSE;
        result = BooleanUtils.toFloatObj(b);
        Assert.assertNotNull(result);
        log.info("false to float {}", result);
    }

    @Test
    public void toDoubleTest() {
        boolean b = true;
        double result = BooleanUtils.toDouble(b);
        Assert.assertNotNull(result);
        log.info("true to double {}", result);

        b = false;
        result = BooleanUtils.toDouble(b);
        log.info("false to double {}", result);
    }

    @Test
    public void toDoubleObjTest() {
        Boolean b = Boolean.TRUE;
        Double result = BooleanUtils.toDoubleObj(b);
        Assert.assertNotNull(result);
        log.info("true to double {}", result);
        b = Boolean.FALSE;
        result = BooleanUtils.toDoubleObj(b);
        Assert.assertNotNull(result);
        log.info("false to double {}", result);
    }
}
