package com.hb0730.commons.lang.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionUtilsTest {

    @Test
    public void isEmptyTest() {
        List<String> list = new ArrayList<>();
        System.out.println("empty:" + CollectionUtils.isEmpty(list));
        list.add("sss");
        System.out.println("empty:" + CollectionUtils.isEmpty(list));
    }

    @Test
    public void testIsEmptyTest() {
        Map<String, String> maps = new HashMap<>();
        System.out.println("empty:" + CollectionUtils.isEmpty(maps));
        maps.put("ss", "121");
        System.out.println("empty:" + CollectionUtils.isEmpty(maps));
    }
}
