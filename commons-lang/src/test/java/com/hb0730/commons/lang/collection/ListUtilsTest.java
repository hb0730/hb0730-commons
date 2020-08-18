package com.hb0730.commons.lang.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
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
}
