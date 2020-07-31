package com.hb0730.commons.lang;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapUtilsTest {

    @Test
    public void isEmptyTest() {
        Map<String, String> map = new HashMap<>();
        System.out.println(MapUtils.isEmpty(map));
        map.put("test", "cccc");
        System.out.println(MapUtils.isEmpty(map));
    }

    @Test
    public void isNotEmptyTest() {
        Map<String, String> map = new HashMap<>();
        System.out.println(MapUtils.isNotEmpty(map));
        map.put("test", "cccc");
        System.out.println(MapUtils.isNotEmpty(map));
    }

    @Test
    public void newHashMapTest() {
        HashMap<Object, Object> map = MapUtils.newHashMap();
        map.put("a", null);
        System.out.println(map);
    }

    @Test
    public void newTreeMapTest() {
        TreeMap<Object, Object> treeMap = MapUtils.newTreeMap(null);
        treeMap.put("c", null);
        treeMap.put("a", null);
        System.out.println(treeMap);

    }

    @Test
    public void testNewTreeMapTest() {
        Map<String, String> map = new HashMap<>();
        map.put("z", null);
        map.put("x", null);
        map.put("y", null);
        map.put("d", null);
        map.put("a", null);
        System.out.println(MapUtils.newTreeMap(map, null));
    }

    @Test
    public void parseMapToUrlStringTest() {
        Map<String, String> map = new HashMap<>();
        map.put("asd", "阿斯頓");
        map.put("ax", "ax");
        map.put("sq", "as阿斯頓");
        map.put("af", "sd");
        System.out.println(MapUtils.parseMapToUrlString(map, false));
        System.out.println(MapUtils.parseMapToUrlString(map, true));
    }

    @Test
    public void forEachTest() {
        Map<String, String> map = new HashMap<>();
        map.put("asd", "阿斯頓");
        map.put("ax", "ax");
        map.put("sq", "as阿斯頓");
        map.put("af", "sd");
        MapUtils.forEach(map, (k, v) -> {
            System.out.println(k + "-" + v);
        });
    }

    @Test
    public void sortTest() {
        Map<String, String> map = new HashMap<>();
        map.put("asd", "阿斯頓");
        map.put("ax", "ax");
        map.put("sq", "as阿斯頓");
        map.put("af", "sd");
        System.out.println(MapUtils.sort(map));
    }

    @Test
    public void testSortTest() {
        Map<String, String> map = new HashMap<>();
        map.put("asd", "阿斯頓");
        map.put("ax", "ax");
        map.put("sq", "as阿斯頓");
        map.put("af", "sd");
        System.out.println(MapUtils.sort(map, null));
    }
}
