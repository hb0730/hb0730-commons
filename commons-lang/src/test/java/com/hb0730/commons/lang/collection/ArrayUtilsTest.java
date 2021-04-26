package com.hb0730.commons.lang.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

@Slf4j
public class ArrayUtilsTest {

    @Test
    public void isEmptyTest() {
        String[] s1 = ArrayUtils.newArray(String.class, 0);
        boolean empty = ArrayUtils.isEmpty(s1);
        Assert.assertTrue("集合为空", empty);
        empty = ArrayUtils.isEmpty((Object[]) null);
        Assert.assertTrue("集合为空", empty);
        s1 = ArrayUtils.newArray(String.class, 10);
        s1[0] = "测试";
        empty = ArrayUtils.isEmpty(s1);
        Assert.assertFalse("集合为空", empty);
    }

    @Test
    public void isArrayTest() {
        String[] s1 = ArrayUtils.newArray(String.class, 0);
        boolean array = ArrayUtils.isArray(s1);
        Assert.assertTrue("非数组", array);
        Object obj = new Object();
        array = ArrayUtils.isArray(obj);
        Assert.assertFalse("为数组", array);
        obj = null;
        array = ArrayUtils.isArray(obj);
        Assert.assertFalse("为数组", array);
    }

    @Test
    public void defaultIfEmptyTest() {
        String[] s1 = ArrayUtils.newArray(String.class, 0);
        String[] strings = ArrayUtils.defaultIfEmpty(s1, new String[]{"1", "2"});
        Assert.assertNotNull(strings);
        log.info(strings.length + "");
        s1 = ArrayUtils.newArray(String.class, 1);
        strings = ArrayUtils.defaultIfEmpty(s1, new String[]{"1", "2"});
        Assert.assertNotNull(strings);
        log.info(strings.length + "");
        s1 = null;
        strings = ArrayUtils.defaultIfEmpty(s1, new String[]{"1", "2"});
        Assert.assertNotNull(strings);
        log.info(strings.length + "");
    }

    @Test
    public void testIsEmptyTest() {
        Object obj = ArrayUtils.newArray(String.class, 1);
        boolean array = ArrayUtils.isEmpty(obj);
        Assert.assertFalse("数组", array);
        obj = ArrayUtils.newArray(String.class, 0);
        array = ArrayUtils.isEmpty(obj);
        Assert.assertTrue("非数组", array);
        obj = new String();
        array = ArrayUtils.isEmpty(obj);
        Assert.assertTrue("非数组", array);
        obj = null;
        array = ArrayUtils.isEmpty(obj);
        Assert.assertTrue("非数组", array);
    }

    @Test
    public void isArraySameLengthTest() {
        Object[] array1 = null;
        Object[] array2 = null;
        boolean length = ArrayUtils.isArraySameLength(array1, array2);
        Assert.assertTrue("长度不相等", length);
        array1 = ArrayUtils.newArray(String.class, 0);
        array2 = ArrayUtils.newArray(String.class, 0);
        length = ArrayUtils.isArraySameLength(array1, array2);
        Assert.assertTrue("长度不相等", length);
        array1 = ArrayUtils.newArray(String.class, 0);
        array2 = ArrayUtils.newArray(String.class, 1);
        length = ArrayUtils.isArraySameLength(array1, array2);
        Assert.assertFalse("长度相等", length);


    }

    @Test
    public void isNotEmptyTest() {
        Object[] array = ArrayUtils.newArray(String.class, 1);
        boolean notEmpty = ArrayUtils.isNotEmpty(array);
        Assert.assertTrue("数组为空", notEmpty);
        array = ArrayUtils.newArray(String.class, 0);
        notEmpty = ArrayUtils.isNotEmpty(array);
        Assert.assertFalse("数组不为空", notEmpty);
        array = null;
        notEmpty = ArrayUtils.isNotEmpty(array);
        Assert.assertFalse("数组不为空", notEmpty);
    }

    @Test
    public void arrayReverseTest() {
        String[] s1 = new String[]{"1", "2", "3"};
        ArrayUtils.arrayReverse(s1);
        Assert.assertNotNull(s1);
        log.info(s1.toString());
        s1 = new String[]{};
        ArrayUtils.arrayReverse(s1);
        log.info(s1.toString());
    }

    @Test
    public void hashNullTest() {

        boolean b1 = ArrayUtils.hashNull("1", "2", null);
        Assert.assertTrue("不存在null元素", b1);
        boolean b = ArrayUtils.hashNull(1, 3, 5, 4);
        Assert.assertFalse("存在null元素", b);
    }

    @Test
    public void fistNonNullElTest() {
        Integer integer = ArrayUtils.fistNonNullEl(1, 3, 5, 4);
        Assert.assertNotNull(integer);
        log.info(integer + "");
        String s = ArrayUtils.fistNonNullEl(null, "1", "3", "6");
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void containsTest() {
        boolean contains = ArrayUtils.contains(new Object[]{"1", 2, "5", "6"}, "6");
        Assert.assertTrue("不包含特定元素", contains);
        boolean contains1 = ArrayUtils.contains(null, "7");
        Assert.assertFalse("包含特定元素", contains1);
    }

    @Test
    public void containsIgnoreCaseTest() {
        boolean a = ArrayUtils.containsIgnoreCase(new String[]{"1", "4", "5", "9", "A", "c"}, "A");
        Assert.assertTrue("不包含特定元素", a);
        a = ArrayUtils.containsIgnoreCase(new String[]{"1", "4", "5", "9", "A", "c"}, "a");
        Assert.assertTrue("不包含特定元素", a);
        a = ArrayUtils.containsIgnoreCase(new String[]{"1", "4", "5", "9", "A", "c"}, "C");
        Assert.assertTrue("不包含特定元素", a);
        a = ArrayUtils.containsIgnoreCase(new String[]{"1", "4", "5", "9", "A", "c"}, "d");
        Assert.assertFalse("包含特定元素", a);
        a = ArrayUtils.containsIgnoreCase(null, "C");
        Assert.assertFalse("包含特定元素", a);
    }

    @Test
    public void containsAnyTest() {
        boolean b = ArrayUtils.containsAny(new String[]{"1", "3", "5", "9", "a", "c", "D", "B"}, "D", "B", "1");
        Assert.assertTrue("不包含特定元素", b);
        b = ArrayUtils.containsAny(new String[]{"1", "3", "5", "9", "a", "c", "D", "B"}, "D", "1");
        Assert.assertTrue("不包含特定元素", b);
        b = ArrayUtils.containsAny(new String[]{"1", "3", "5", "9", "a", "c", "D", "B"}, "1");
        Assert.assertTrue("不包含特定元素", b);
        b = ArrayUtils.containsAny(new String[]{"1", "3", "5", "9", "a", "c", "D", "B"}, 1, 4);
        Assert.assertFalse("包含特定元素", b);
        b = ArrayUtils.containsAny(null, "1");
        Assert.assertFalse("包含特定元素", b);
    }

    @Test
    public void arrayIndexOfTest() {
        int c = ArrayUtils.arrayIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, "c");
        Assert.assertTrue("元素不存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, "B");
        Assert.assertTrue("元素不存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, 5);
        Assert.assertTrue("元素不存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayIndexOf(null, "B");
        Assert.assertFalse("元素存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, 7);
        Assert.assertFalse("元素存在", c > -1);
        log.info(c + "");

    }

    @Test
    public void testArrayIndexOfTest() {
        int c = ArrayUtils.arrayIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, "c", 1);
        Assert.assertTrue("元素不存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, "B", 1);
        Assert.assertTrue("元素不存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, 5, 1);
        Assert.assertTrue("元素不存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, "1", 1);
        Assert.assertFalse("元素存在", c > -1);
        c = ArrayUtils.arrayIndexOf(null, "1", 1);
        Assert.assertFalse("元素存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, 7, 1);
        Assert.assertFalse("元素存在", c > -1);
        log.info(c + "");
    }

    @Test
    public void arrayIndexOfIgnoreCaseTest() {
        int d = ArrayUtils.arrayIndexOfIgnoreCase(new String[]{"a", "C", "D", "b"}, "D");
        Assert.assertTrue("元素不存在", d > -1);
        log.info(d + "");
        d = ArrayUtils.arrayIndexOfIgnoreCase(new String[]{"a", "C", "D", "b"}, "B");
        Assert.assertTrue("元素不存在", d > -1);
        log.info(d + "");
        d = ArrayUtils.arrayIndexOfIgnoreCase(new String[]{"a", "C", "D", "b"}, "c");
        Assert.assertTrue("元素不存在", d > -1);
        log.info(d + "");
        d = ArrayUtils.arrayIndexOfIgnoreCase(null, "D");
        Assert.assertFalse("元素存在", d > -1);
        log.info(d + "");
        d = ArrayUtils.arrayIndexOfIgnoreCase(new String[]{"a", "C", "D", "b"}, "1");
        Assert.assertFalse("元素存在", d > -1);
        log.info(d + "");
    }

    @Test
    public void testArrayIndexOfIgnoreCaseTest() {
        int d = ArrayUtils.arrayIndexOfIgnoreCase(new String[]{"a", "C", "D", "b"}, "D", 1);
        Assert.assertTrue("元素不存在", d > -1);
        log.info(d + "");
        d = ArrayUtils.arrayIndexOfIgnoreCase(new String[]{"a", "C", "D", "b"}, "B", 1);
        Assert.assertTrue("元素不存在", d > -1);
        log.info(d + "");
        d = ArrayUtils.arrayIndexOfIgnoreCase(new String[]{"a", "C", "D", "b"}, "c", 1);
        Assert.assertTrue("元素不存在", d > -1);
        log.info(d + "");
        d = ArrayUtils.arrayIndexOfIgnoreCase(new String[]{"a", "C", "D", "b"}, "A", 1);
        Assert.assertFalse("元素存在", d > -1);
        log.info(d + "");
        d = ArrayUtils.arrayIndexOfIgnoreCase(null, "D");
        Assert.assertFalse("元素存在", d > -1);
        log.info(d + "");
        d = ArrayUtils.arrayIndexOfIgnoreCase(new String[]{"a", "C", "D", "b"}, "1");
        Assert.assertFalse("元素存在", d > -1);
        log.info(d + "");
    }

    @Test
    public void arrayLastIndexOfTest() {
        int c = ArrayUtils.arrayLastIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, "c");
        Assert.assertFalse("元素存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayLastIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, "B");
        Assert.assertTrue("元素不存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayLastIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, 5);
        Assert.assertFalse("元素存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayLastIndexOf(null, "B");
        Assert.assertFalse("元素存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayLastIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, 7);
        Assert.assertFalse("元素存在", c > -1);
        log.info(c + "");
    }

    @Test
    public void testArrayLastIndexOfTest() {
        int c = ArrayUtils.arrayLastIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, "c", 1);
        Assert.assertTrue("元素不存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayLastIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, "B", 1);
        Assert.assertTrue("元素不存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayLastIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, 5, 1);
        Assert.assertTrue("元素不存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayLastIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, "1", 1);
        Assert.assertFalse("元素存在", c > -1);
        c = ArrayUtils.arrayLastIndexOf(null, "1", 1);
        Assert.assertFalse("元素存在", c > -1);
        log.info(c + "");
        c = ArrayUtils.arrayLastIndexOf(new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"}, 7, 1);
        Assert.assertFalse("元素存在", c > -1);
        log.info(c + "");
    }

    @Test
    public void getTest() {
        Object[] obj = new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"};
        Object o = ArrayUtils.get(obj, 0);
        Assert.assertNotNull(o);
        log.info("" + o.toString());
        o = ArrayUtils.get(obj, obj.length + 1);
        Assert.assertNotNull(o);
        log.info("" + o.toString());
        o = ArrayUtils.get(obj, -1);
        Assert.assertNotNull(o);
        log.info("" + o.toString());
        o = ArrayUtils.get(null, 0);
        Assert.assertNull(o);
        log.info("为null");
    }

    @Test
    public void testGetTest() {
        Object[] obj = new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"};
        Object[] objects = ArrayUtils.get(obj, 1, 3, 5);
        Assert.assertNotNull(objects);
        log.info(objects.length + "");
        Object[] objects1 = ArrayUtils.get(obj, -1, 0, 1, obj.length);
        Assert.assertNotNull(objects1);
        log.info(objects1.length + "");
        Object[] o = ArrayUtils.get(null, 0);
        Assert.assertNull(o);
        log.info("为null");
    }

    @Test
    public void removeTest() {
        Object[] obj = new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"};
        Object[] remove = ArrayUtils.remove(obj, 1);
        Assert.assertNotNull(remove);
        Object[] remove1 = ArrayUtils.remove(obj, obj.length);
        Assert.assertNotNull(remove1);
        Object[] remove2 = ArrayUtils.remove(obj, -1);
        Assert.assertNotNull(remove2);
        Object[] remove3 = ArrayUtils.remove(null, 1);
        Assert.assertNull(remove3);
    }

    @Test
    public void testRemoveTest() {
        Object obj = new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"};
        Object remove = ArrayUtils.remove(obj, 1);
        Assert.assertNotNull(remove);
        Object remove1 = ArrayUtils.remove(obj, 8);
        Assert.assertNotNull(remove1);
        Object remove2 = ArrayUtils.remove(obj, -1);
        Assert.assertNotNull(remove2);
        Object obj1 = new Object();
        Object remove4 = ArrayUtils.remove(obj1, 1);
        Assert.assertNotNull(remove4);
        Object remove3 = ArrayUtils.remove(null, 1);
        Assert.assertNull(remove3);
    }

    @Test
    public void removeElTest() {
        Object[] obj = new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"};
        Object[] objects = ArrayUtils.removeEl(obj, "B");
        Assert.assertNotNull(objects);
        log.info(objects.length + "");
        Object[] objects1 = ArrayUtils.removeEl(obj, new Object[]{"B"});
        Assert.assertNotNull(objects1);
        log.info(objects1.length + "");
        Object[] objects2 = ArrayUtils.removeEl(obj, null);
        Assert.assertNotNull(objects2);
        log.info(objects2.length + "");
        String[] strings = ArrayUtils.removeEl(null, "1");
        Assert.assertNull(strings);
        log.info(strings + "");
    }

    @Test
    public void distinctTest() {
        Object[] obj = new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"};
        Object[] distinct = ArrayUtils.distinct(obj);
        Assert.assertNotNull(distinct);
        log.info(distinct.length + "");
        obj = ArrayUtils.newArray(String.class, 0);
        distinct = ArrayUtils.distinct(obj);
        Assert.assertNotNull(distinct);
        log.info(distinct.length + "");
        obj = null;
        distinct = ArrayUtils.distinct(obj);
        Assert.assertNull(distinct);

    }

    @Test
    public void newArrayTest() {
        Object[] objects = ArrayUtils.newArray(String.class, 0);
        Assert.assertNotNull(objects);
        Object[] objects1 = ArrayUtils.newArray(String.class, 1);
        Assert.assertNotNull(objects1);
    }

    @Test
    public void testNewArrayTest() {
        String[] strings = ArrayUtils.newArray(ListUtils.empty(), String.class);
        Assert.assertNotNull(strings);
        String[] strings1 = ArrayUtils.newArray(ListUtils.toArrayList("1", "3"), String.class);
        Assert.assertNotNull(strings1);
    }

    @Test
    public void swapTest() {
        Object[] obj = new Object[]{"1", "c", "B", "4", 5, "c", "D", "B"};
        ArrayUtils.swap(obj, 4, 1);
        String s = ArrayUtils.toString(obj);
        Assert.assertNotNull(s);
        log.info(s);
    }


    @Test
    public void testToStringTest() {
        String[] s = new String[]{"1", "2"};
        String s1 = ArrayUtils.toString(s);
        Assert.assertNotNull(s1);
        log.info(s1);
    }

    @Test
    public void lengthTest() {
        String[] s = new String[]{"1", "2"};
        int length = ArrayUtils.length(s);
        log.debug(length + "");
    }

    @Test
    public void resizeTest() {
        String[] s = new String[]{"1", "2"};
        String[] resize = ArrayUtils.resize(s, 4);
        Assert.assertNotNull(resize);
        log.debug(ArrayUtils.toString(resize));
    }

    @Test
    public void testResizeTest() {
        String[] s = new String[]{"1", "2"};
        String[] resize = ArrayUtils.resize(s, 4, String.class);
        Assert.assertNotNull(resize);
        log.debug(ArrayUtils.toString(resize));
    }

    @Test
    public void testResize1Test() {
        int[] s = new int[]{1, 2};
        int[] resize = (int[]) ArrayUtils.resize(s, 4);
        Assert.assertNotNull(resize);
        log.debug(ArrayUtils.toString(resize));
    }

    @Test
    public void toListTest() {
        String[] s = new String[]{"1", "2", "3"};
        List<String> list1 = ArrayUtils.toList(s);
        Assert.assertNotNull(list1);
        s = null;
        list1 = ArrayUtils.toList(s);
        Assert.assertNull(list1);
    }

    @Test
    public void joinTest() {
        String[] s = new String[]{"1", "2", "3"};
        String s1 = ArrayUtils.join(s, ",");
        Assert.assertEquals("1,2,3", s1);
        s1 = ArrayUtils.join(s, "");
        Assert.assertEquals("123", s1);
        s1 = ArrayUtils.join(s, null);
        Assert.assertEquals("1null2null3", s1);
    }

    @Test
    public void testJoinTest() {
        String[] s = new String[]{"1", "2", "3"};
        String result = ArrayUtils.join(s, ",", "test", ".txt", 2);
        Assert.assertEquals("test1.txt,test2.txt", result);
        result = ArrayUtils.join(s, ",", "test", ".txt", 0);
        Assert.assertEquals("test1.txt,test2.txt,test3.txt", result);
        result = ArrayUtils.join(s, ",", "test", ".txt", 5);
        Assert.assertEquals("test1.txt,test2.txt,test3.txt", result);
        result = ArrayUtils.join(s, ",", "test", ".txt", 5);
        Assert.assertEquals("test1.txt,test2.txt,test3.txt", result);
        result = ArrayUtils.join(s, ",", "test", null, 5);
        Assert.assertEquals("test1,test2,test3", result);
        result = ArrayUtils.join(s, ",", null, null, 5);
        Assert.assertEquals("1,2,3", result);
    }
}
