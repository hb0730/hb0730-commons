package com.hb0730.commons.lang.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ListUtilsTest {

    @Test
    public void sortTest() {
        List<Integer> list = Arrays.asList(2, 1, 4, 7);
        ListUtils.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        Assert.assertNotNull(list);
        log.info(list.toString());
    }

    @Test
    public void reverseTest() {
        List<String> list = Arrays.asList("1", "2", "3");
        ListUtils.reverse(list);
        Assert.assertNotNull(list);
        log.info(list.toString());
    }

    @Test
    public void listEnumerationTest() {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("test1", "test1");
        hashtable.put("test2", "test2");
        Enumeration<String> elements = hashtable.elements();
        List<String> list = ListUtils.list(elements);
        Assert.assertNotNull(list);
        elements = null;
        list = ListUtils.list(elements);
        Assert.assertNotNull(list);
    }

    @Test
    public void testListEnumerationTest() {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("test1", "test1");
        hashtable.put("test2", "test2");
        Enumeration<String> elements = hashtable.elements();
        List<String> list = ListUtils.list(true, elements);
        Assert.assertTrue(list instanceof LinkedList);
        list = ListUtils.list(false, elements);
        Assert.assertTrue(list instanceof ArrayList);
        list = ListUtils.list(false, (Enumeration<String>) null);
        Assert.assertNotNull(list);
    }
}
