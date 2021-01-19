package com.hb0730.commons.lang.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MapUtilsTest {

    @Test
    public void isEmptyTest() {
        Map<String, String> map = MapUtils.newHashMap();
        boolean isNull = MapUtils.isEmpty(map);
        Assert.assertTrue("为空", isNull);
        map = null;
        isNull = MapUtils.isEmpty(map);
        Assert.assertTrue("为空", isNull);
        map = MapUtils.newHashMap(1);
        map.put("test", "测试");
        isNull = MapUtils.isEmpty(map);
        Assert.assertTrue("不为空", isNull);
    }

    @Test
    public void isNotEmptyTest() {
        Map<String, String> map = MapUtils.newHashMap(1);
        map.put("test", "测试");
        boolean isNull = MapUtils.isEmpty(map);
        Assert.assertTrue("不为空", isNull);
        map = null;
        isNull = MapUtils.isEmpty(map);
        Assert.assertTrue("为空", isNull);
        map = MapUtils.newHashMap();
        isNull = MapUtils.isEmpty(map);
        Assert.assertTrue("为空", isNull);


    }

    @Test
    public void defaultIfEmptyTest() {
        Map<String, String> map = MapUtils.newHashMap(1);
        map.put("test", "test");
        Map<String, String> map2 = MapUtils.defaultIfEmpty(map);
        Assert.assertEquals("map不为空", map2, map);
        map = MapUtils.newHashMap(1);
        map2 = MapUtils.defaultIfEmpty(map);
        Assert.assertEquals("map不为空", map2, map);
        map = null;
        map2 = MapUtils.defaultIfEmpty(map);
        Assert.assertNotNull("map为空", map2);

    }

    @Test
    public void testDefaultIfEmptyTest() {
        Map<String, String> defaultMap = MapBuilder
                .build(new HashMap<String, String>())
                .put("test", "test")
                .builder();

        Map<String, String> map = MapUtils.newHashMap();
        map.put("test", "test1");
        Map<String, String> resultMap = MapUtils.defaultIfEmpty(map, defaultMap);
        Assert.assertEquals("map不为空", resultMap, map);
        map = MapUtils.newHashMap();
        resultMap = MapUtils.defaultIfEmpty(map, defaultMap);
        Assert.assertEquals("map为空", resultMap, map);
        map = null;
        resultMap = MapUtils.defaultIfEmpty(map, defaultMap);
        Assert.assertEquals("map为空", resultMap, map);
    }

    @Test
    public void newHashMapTest() {
        HashMap<Object, Object> hashMap = MapUtils.newHashMap();
        Assert.assertNotNull("map为空", hashMap);
    }

    @Test
    public void testNewHashMapTest() {
        HashMap<String, String> defaultMap = MapUtils.newHashMap(5, true);
        Assert.assertNotNull("map为空", defaultMap);
        Assert.assertTrue("map非LinkedHashMap", defaultMap instanceof LinkedHashMap);
        defaultMap = MapUtils.newHashMap(4, false);
        Assert.assertNotNull("map为空", defaultMap);
    }

    @Test
    public void testNewHashMap1Test() {
        HashMap<Object, Object> resultMap = MapUtils.newHashMap(6);
        Assert.assertNotNull("map为空", resultMap);
    }

    @Test
    public void newTreeMapTest() {
        TreeMap<String, String> resultMap = MapUtils.newTreeMap(Comparator.reverseOrder());
        Assert.assertNotNull("map不为空", resultMap);
    }

    @Test
    public void testNewTreeMapTest() {
        Map<Integer, String> defaultMap = MapUtils.newHashMap();
        defaultMap.put(1, "测试1");
        defaultMap.put(3, "测试3");
        defaultMap.put(4, "测试4");
        defaultMap.put(6, "测试6");
        defaultMap.put(2, "测试2");
        TreeMap<Integer, String> resultMap = MapUtils.newTreeMap(defaultMap, Comparator.comparing(Integer::intValue));
        Assert.assertNotNull("map为空", resultMap);
        defaultMap = null;
        resultMap = MapUtils.newTreeMap(defaultMap, Comparator.comparing(Integer::intValue));
        Assert.assertNotNull("map为空", resultMap);
    }

    @Test
    public void parseMapToUrlStringTest() {
        Map<String, String> defaultMap = MapUtils.newHashMap();
        defaultMap.put("1", "测试1");
        defaultMap.put("3", "测试3");
        defaultMap.put("4", "测试4");
        defaultMap.put("6", "测试6");
        defaultMap.put("2", "测试2");
        String resultParams = MapUtils.parseMapToUrlString(defaultMap, true);
        Assert.assertNotNull("返回值为空", resultParams);
        resultParams = MapUtils.parseMapToUrlString(defaultMap, false);
        Assert.assertNotNull("返回值为空", resultParams);
        resultParams = MapUtils.parseMapToUrlString(null, false);
        Assert.assertNotNull("返回值为空", resultParams);
    }

    @Test
    public void forEachTest() {
        Map<String, String> defaultMap = MapUtils.newHashMap();
        defaultMap.put("1", "测试1");
        defaultMap.put("3", "测试3");
        defaultMap.put("4", "测试4");
        defaultMap.put("6", "测试6");
        defaultMap.put("2", "测试2");
        Map<String, String> resultMap = MapUtils.newHashMap();
        MapUtils.forEach(defaultMap, resultMap::put);
        Assert.assertNotNull("操作失败", resultMap);
        MapUtils.forEach(null, resultMap::put);
        Assert.assertNotNull("操作失败", resultMap);
        MapUtils.forEach(defaultMap, null);
        Assert.assertNotNull("操作失败", resultMap);
    }

    @Test
    public void sortTest() {
        Map<String, String> defaultMap = MapUtils.newHashMap();
        defaultMap.put("1", "测试1");
        defaultMap.put("3", "测试3");
        defaultMap.put("4", "测试4");
        defaultMap.put("6", "测试6");
        defaultMap.put("2", "测试2");
        TreeMap<String, String> sort = MapUtils.sort(defaultMap);
        Assert.assertNotNull(sort);
        sort = MapUtils.sort(null);
        Assert.assertNotNull(sort);
    }

    @Test
    public void testSortTest() {
        Map<String, String> defaultMap = MapUtils.newHashMap();
        defaultMap.put("1", "测试1");
        defaultMap.put("3", "测试3");
        defaultMap.put("4", "测试4");
        defaultMap.put("6", "测试6");
        defaultMap.put("2", "测试2");
        TreeMap<String, String> sort = MapUtils.sort(defaultMap, Comparator.comparing(Integer::parseInt));
        Assert.assertNotNull(sort);
        sort = MapUtils.sort(defaultMap, null);
        Assert.assertNotNull(sort);
        sort = MapUtils.sort(null, Comparator.comparing(Integer::parseInt));
        Assert.assertNotNull(sort);

    }
}
