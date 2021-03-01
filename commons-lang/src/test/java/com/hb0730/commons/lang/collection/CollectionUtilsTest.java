package com.hb0730.commons.lang.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class CollectionUtilsTest {

    @Test
    public void isEmptyTest() {
        List<String> list = new ArrayList<>();
        boolean empty = CollectionUtils.isEmpty(list);
        Assert.assertTrue("集合为空", empty);

        list.add("sss");
        empty = CollectionUtils.isEmpty(list);
        Assert.assertTrue("集合不为空", empty);
    }

    @Test
    public void testIsEmptyTest() {
        Map<String, String> maps = new HashMap<>();
        boolean empty = CollectionUtils.isEmpty(maps);
        Assert.assertTrue("map为空", empty);
        maps.put("ss", "121");
        empty = CollectionUtils.isEmpty(maps);
        Assert.assertTrue("map不为空", empty);
    }

    @Test
    public void emptyIfNullTest() {
        List<String> list = CollectionUtils.emptyIfNull((List<String>) null);
        Assert.assertNotNull(list);
        log.info(list.size() + "");
        List<String> list1 = ListUtils.toArrayList("1");
        list = CollectionUtils.emptyIfNull(list1);
        Assert.assertNotNull(list);
        log.info(list.size() + "");
    }

    @Test
    public void testEmptyIfNullTest() {
        Set<String> set = CollectionUtils.emptyIfNull((Set<String>) null);
        Assert.assertNotNull(set);
        log.info("" + set.size());
        Set<String> set1 = CollectionUtils.newHashSet("1");
        set = CollectionUtils.emptyIfNull(set1);
        Assert.assertNotNull(set);
        log.info("" + set.size());
    }

    @Test
    public void newHashMapTest() {
        HashMap<Object, Object> hashMap = CollectionUtils.newHashMap();
        Assert.assertNotNull(hashMap);
    }

    @Test
    public void testNewHashMapTest() {
        HashMap<Object, Object> hashMap = CollectionUtils.newHashMap(10);
        Assert.assertNotNull(hashMap);
    }

    @Test
    public void testNewHashMap1Test() {
        HashMap<Object, Object> hashMap = CollectionUtils.newHashMap(10, false);
        Assert.assertTrue("非 HashMap", (hashMap instanceof HashMap));
        HashMap<Object, Object> hashMap1 = CollectionUtils.newHashMap(10, true);
        Assert.assertTrue("非 LinkedHashMap", (hashMap1 instanceof LinkedHashMap));
    }

    @Test
    public void setTest() {
        HashSet<String> set = CollectionUtils.set(false, "1");
        Assert.assertTrue("非 HashSet", (set instanceof HashSet));
        HashSet<String> set1 = CollectionUtils.set(true, "1");
        Assert.assertTrue("非 LinkedHashSet", (set1 instanceof LinkedHashSet));
        HashSet<Object> set2 = CollectionUtils.set(false);
        Assert.assertNotNull(set2);
    }

    @Test
    public void newHashSetTest() {
        HashSet<String> set = CollectionUtils.newHashSet("1");
        Assert.assertNotNull(set);
        HashSet<Object> hashSet = CollectionUtils.newHashSet();
        Assert.assertNotNull(hashSet);
    }

    @Test
    public void newLinkedHashSetTest() {
        LinkedHashSet<Object> hashSet = CollectionUtils.newLinkedHashSet();
        Assert.assertNotNull(hashSet);
        LinkedHashSet<String> strings = CollectionUtils.newLinkedHashSet("1");
        Assert.assertNotNull(strings);
    }

    @Test
    public void testNewHashSetTest() {
        HashSet<Object> objects = CollectionUtils.newHashSet(Collections.emptyList());
        Assert.assertNotNull(objects);
        HashSet<String> strings = CollectionUtils.newHashSet(ListUtils.toArrayList("1"));
        Assert.assertNotNull(strings);
        HashSet hashSet = CollectionUtils.newHashSet((Collection) null);
        Assert.assertNotNull(hashSet);
    }

    @Test
    public void testNewHashSet1Test() {
        HashSet<String> strings = CollectionUtils.newHashSet(false, ListUtils.toArrayList("1"));
        Assert.assertTrue("非HashSet", (strings instanceof HashSet));
        HashSet<String> strings1 = CollectionUtils.newHashSet(true, ListUtils.toArrayList("1", "3", "5"));
        Assert.assertTrue("非 LinkedHashSet", (strings1 instanceof LinkedHashSet));
        log.info(strings.toString());
        HashSet hashSet = CollectionUtils.newHashSet(false, (Collection) null);
        Assert.assertNotNull(hashSet);
    }

    @Test
    public void testNewHashSet2Test() {
        HashSet<String> strings = CollectionUtils.newHashSet(false, ListUtils.toArrayList("1").iterator());
        Assert.assertTrue("非HashSet", (strings instanceof HashSet));
        HashSet<String> strings1 = CollectionUtils.newHashSet(true, ListUtils.toArrayList("1", "3", "5").iterator());
        Assert.assertTrue("非 LinkedHashSet", (strings1 instanceof LinkedHashSet));
        log.info(strings.toString());
        HashSet hashSet = CollectionUtils.newHashSet(false, (Collection) null);
        Assert.assertNotNull(hashSet);
    }

    @Test
    public void listTest() {
        List<Object> list = CollectionUtils.list(false);
        Assert.assertTrue("非 ArrayList", (list instanceof ArrayList));
        List<Object> list1 = CollectionUtils.list(true);
        Assert.assertTrue("非 LinkedList", (list1 instanceof LinkedList));
    }

    @Test
    public void testListTest() {
        List<String> list = CollectionUtils.list(false, "1");
        Assert.assertNotNull(list);
        log.info(list.size() + "");
        List<String> list1 = CollectionUtils.list(true, "1");
        Assert.assertNotNull(list1);
        log.info(list1.size() + "");
        List<Object> list2 = CollectionUtils.list(false);
        Assert.assertNotNull(list2);
        List<Object> list3 = CollectionUtils.list(true);
        Assert.assertNotNull(list3);
    }

    @Test
    public void testList1Test() {
        List<Object> list = CollectionUtils.list(false, ListUtils.empty());
        Assert.assertNotNull(list);
        List<Object> list1 = CollectionUtils.list(true, ListUtils.empty());
        Assert.assertNotNull(list1);
        List list2 = CollectionUtils.list(false, (Collection) null);
        Assert.assertNotNull(list2);
        List list3 = CollectionUtils.list(true, (Collection) null);
        Assert.assertNotNull(list3);
    }

    @Test
    public void testList2Test() {
        List<Object> list = CollectionUtils.list(false, ListUtils.empty().iterator());
        Assert.assertNotNull(list);
        List<Object> list1 = CollectionUtils.list(true, ListUtils.empty().iterator());
        Assert.assertNotNull(list1);
        List list2 = CollectionUtils.list(false, (Iterator) null);
        Assert.assertNotNull(list2);
        List list3 = CollectionUtils.list(true, (Iterator) null);
        Assert.assertNotNull(list3);
    }

    @Test
    public void testList3Test() {
        List list = CollectionUtils.list(false, (Iterable) ListUtils.empty());
        Assert.assertNotNull(list);
        List list1 = CollectionUtils.list(true, (Iterable) ListUtils.empty());
        Assert.assertNotNull(list1);
        List list2 = CollectionUtils.list(false, (Iterable) null);
        Assert.assertNotNull(list2);
        List list3 = CollectionUtils.list(true, (Iterable) null);
        Assert.assertNotNull(list3);
    }

    @Test
    public void toArrayListTest() {
        ArrayList<Object> objects = CollectionUtils.toArrayList();
        Assert.assertNotNull(objects);
        ArrayList<String> strings = CollectionUtils.toArrayList("1");
        Assert.assertNotNull(strings);
    }

    @Test
    public void testToArrayListTest() {
        ArrayList<Object> objects = CollectionUtils.toArrayList(ListUtils.empty());
        Assert.assertNotNull(objects);
        ArrayList arrayList = CollectionUtils.toArrayList((Collection) null);
        Assert.assertNotNull(arrayList);
    }

    @Test
    public void testToArrayList1Test() {
        ArrayList<Object> objects = CollectionUtils.toArrayList(ListUtils.empty().iterator());
        Assert.assertNotNull(objects);
        ArrayList arrayList = CollectionUtils.toArrayList((Iterator) null);
        Assert.assertNotNull(arrayList);
    }

    @Test
    public void testToArrayList2Test() {
        ArrayList arrayList = CollectionUtils.toArrayList((Iterable) ListUtils.empty());
        Assert.assertNotNull(arrayList);
        ArrayList arrayList1 = CollectionUtils.toArrayList((Iterable) null);
        Assert.assertNotNull(arrayList1);
    }

    @Test
    public void toLinkedListTest() {
        LinkedList<Object> objects = CollectionUtils.toLinkedList();
        Assert.assertNotNull(objects);
        LinkedList<String> strings = CollectionUtils.toLinkedList("1");
        Assert.assertNotNull(strings);
    }

    @Test
    public void toBlockingQueueTest() {
        BlockingQueue<Object> objects = CollectionUtils.toBlockingQueue(10, false);
        Assert.assertTrue("非ArrayBlockingQueue", (objects instanceof ArrayBlockingQueue));
        BlockingQueue<Object> objects1 = CollectionUtils.toBlockingQueue(10, true);
        Assert.assertTrue("非LinkedBlockingQueue", (objects1 instanceof LinkedBlockingQueue));
    }

    @Test
    public void testToBlockingQueueTest() {
        BlockingQueue<Object> objects = CollectionUtils.toBlockingQueue(false);
        Assert.assertTrue("非ArrayBlockingQueue", (objects instanceof ArrayBlockingQueue));
        BlockingQueue<Object> objects1 = CollectionUtils.toBlockingQueue(true);
        Assert.assertTrue("非LinkedBlockingQueue", (objects1 instanceof LinkedBlockingQueue));
    }

    @Test
    public void toArrayBlockingQueueTest() {
        BlockingQueue<Object> objects = CollectionUtils.toArrayBlockingQueue();
        Assert.assertNotNull(objects);
    }

    @Test
    public void addAllTest() {
        List<String> list = new ArrayList<>();
        list = (List<String>) CollectionUtils.addAll(list, "a", "b", "c");
        Assert.assertNotNull("集合为空", list);
        list = (List<String>) CollectionUtils.addAll(list, (String) null);
        Assert.assertNotNull("集合为空", list);
        list = (List<String>) CollectionUtils.addAll(null, (String) null);
        Assert.assertNotNull("集合为空", list);
    }

    @Test
    public void testAddAllTest() {
        List<String> list = new ArrayList<>();
        list = (List<String>) CollectionUtils.addAll(list, Arrays.asList("a", "b", "c"));
        Assert.assertNotNull("集合为空", list);
        list = (List<String>) CollectionUtils.addAll(list, (Collection<String>) null);
        Assert.assertNotNull("集合为空", list);
        list = (List<String>) CollectionUtils.addAll(null, (Collection<String>) null);
        Assert.assertNotNull("集合为空", list);
    }
}
