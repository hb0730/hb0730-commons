package com.hb0730.commons.lang.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListBuilderTest {

    @Test
    public void builderTest() {
        ListBuilder<String> builder = ListBuilder.build(new ArrayList<>());
        Assert.assertNotNull(builder);
    }

    @Test
    public void listBuilderTest() {
        ListBuilder<String> builder = new ListBuilder<>(new ArrayList<>());
        Assert.assertNotNull(builder);
    }

    @Test
    public void addTest() {
        ListBuilder<String> builder = ListBuilder.build(new ArrayList<>());
        List<String> list = builder.add("test").add("test").build();
        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void add2Test() {
        ListBuilder<String> builder = ListBuilder.build(new ArrayList<>());
        List<String> list = builder.add("test").add("test").add(0, "action").build();
        Assert.assertEquals(list.get(0), "action");
    }

    @Test
    public void addAllTest() {
        ListBuilder<String> builder = ListBuilder.build(new ArrayList<>());
        List<String> list = builder.addAll(Arrays.asList("test", "test")).build();
        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void addAll2Test() {
        ListBuilder<String> builder = ListBuilder.build(new ArrayList<>());
        List<String> list = builder.addAll(Arrays.asList("test", "test")).addAll(0, Arrays.asList("c", "c2")).build();
        Assert.assertEquals(list.get(0), "c");
    }

    @Test
    public void removeTest() {
        ListBuilder<String> builder = ListBuilder.build(new ArrayList<>());
        List<String> list = builder
                .addAll(Arrays.asList("test", "test"))
                .addAll(0, Arrays.asList("c", "c2"))
                .remove("c")
                .build();
        Assert.assertEquals(list.size(), 3);
    }

    @Test
    public void remove2Test() {
        ListBuilder<String> builder = ListBuilder.build(new ArrayList<>());
        List<String> list = builder
                .addAll(Arrays.asList("test", "test"))
                .addAll(0, Arrays.asList("c", "c2"))
                .remove(3)
                .build();
        Assert.assertEquals(list.size(), 3);
    }

    @Test
    public void removeAllTest() {
        ListBuilder<String> builder = ListBuilder.build(new ArrayList<>());
        List<String> list = builder
                .addAll(Arrays.asList("test", "test"))
                .addAll(0, Arrays.asList("c", "c2"))
                .removeAll(Arrays.asList("test", "test"))
                .build();
        Assert.assertEquals(list.size(), 2);
    }

}
