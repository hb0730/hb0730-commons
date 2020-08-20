package com.hb0730.commons.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hb0730.commons.json.exceptions.JsonException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JacksonsTest {

    private Jacksons jacksons;

    @Before
    public void init() {
        jacksons = new Jacksons();
    }

    @Test
    public void jsonToObjectTest() throws JsonException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        Test1 test1 = jacksons.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(simpleModule);
        jacksons.setMapper(mapper);
        test1 = jacksons.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());

        jacksons.setMapper(new Object());
    }

    @Test
    public void testJsonToObjectTest() throws JsonException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        ObjectMapper mapper = new ObjectMapper();
        Test1 test1 = jacksons.jsonToObject(json, Test1.class, mapper);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        Test1 test11 = jacksons.jsonToObject(null, Test1.class, mapper);
        Assert.assertNotNull(test11);
        Test1 test111 = jacksons.jsonToObject(null, null, mapper);
        Assert.assertNotNull(test111);
    }

    @Test
    public void jsonToListTest() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        List<Test1> test1s = jacksons.jsonToList(json, Test1.class);
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
        ObjectMapper mapper = new ObjectMapper();
        jacksons.setMapper(mapper);
        List<Test1> test1s1 = jacksons.jsonToList(json, Test1.class);
        Assert.assertNotNull(test1s1);
        log.info(test1s1.toString());
    }

    @Test
    public void testJsonToListTest() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        ObjectMapper mapper = new ObjectMapper();
        List<Test1> test1s = jacksons.jsonToList(json, Test1.class, mapper);
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
    }

    @Test
    public void jsonToList2Test() throws IOException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        List<Test1> test1s = jacksons.jsonToList2(json, Test1.class);
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
    }

    @Test
    public void testJsonToList2Test() throws IOException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        ObjectMapper mapper = new ObjectMapper();
        List<Test1> test1s = jacksons.jsonToList2(json, Test1.class, mapper);
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
    }

    @Test
    public void objectToJsonTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        String s = jacksons.objectToJson(test1);
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void testObjectToJsonTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        String s = jacksons.objectToJson(test1, new ObjectMapper());
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void mapToObjectTest() throws JsonException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "测试1");
        Test1 test1 = jacksons.mapToObject(map, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void testMapToObjectTest() throws JsonException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "测试1");
        Test1 test1 = jacksons.mapToObject(map, Test1.class, new ObjectMapper());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void objectToMapTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        Map<?, ?> map = jacksons.objectToMap(test1);
        Assert.assertNotNull(map);
        log.info(map.toString());
    }

    @Test
    public void testObjectToMapTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        Map<?, ?> map = jacksons.objectToMap(test1, new ObjectMapper());
        Assert.assertNotNull(map);
        log.info(map.toString());
    }

    @Data
    @Builder
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    static class Test1 implements Serializable {
        private String id;
        private String name;
    }
}
