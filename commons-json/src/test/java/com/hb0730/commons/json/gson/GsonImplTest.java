package com.hb0730.commons.json.gson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hb0730.commons.json.exceptions.JsonException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GsonImplTest {

    @Test
    public void jsonToObjectTest() throws JsonException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        GsonImpl gson = new GsonImpl();
        Test1 test1 = gson.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        Gson gson1 = new Gson();
        gson.setMapper(gson1);
        Test1 test11 = gson.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test11);
        log.info(test11.toString());
        gson.setMapper(new ObjectMapper());
        gson.jsonToObject(null, Test1.class);
        gson.jsonToObject(null, null);
    }

    @Test
    public void testJsonToObjectTest() throws JsonException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        GsonImpl gsons = new GsonImpl();
        Test1 test1 = gsons.jsonToObject(json, Test1.class, new Gson());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void jsonToListTest() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        GsonImpl gson = new GsonImpl();
        List<Test1> test1 = gson.jsonToList(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        Gson gson1 = new Gson();
        gson.setMapper(gson1);
        List<Test1> test11 = gson.jsonToList(json, Test1.class);
        Assert.assertNotNull(test11);
        log.info(test11.toString());
        gson.setMapper(new ObjectMapper());
        gson.jsonToObject(null, Test1.class);
        gson.jsonToObject(null, null);
    }

    @Test
    public void testJsonToListTest() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        GsonImpl gson = new GsonImpl();
        List<Test1> test1 = gson.jsonToList(json, Test1.class, new Gson());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void jsonToList2Test() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        GsonImpl gson = new GsonImpl();
        List<Test1> test1 = gson.jsonToList2(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        Gson gson1 = new Gson();
        gson.setMapper(gson1);
        List<Test1> test11 = gson.jsonToList2(json, Test1.class);
        Assert.assertNotNull(test11);
        log.info(test11.toString());
        gson.setMapper(new ObjectMapper());
        gson.jsonToObject(null, Test1.class);
        gson.jsonToObject(null, null);
    }

    @Test
    public void testJsonToList2Test() {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        GsonImpl gson = new GsonImpl();
        List<Test1> test1 = gson.jsonToList2(json, Test1.class, new Gson());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void objectToJsonTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        GsonImpl gsons = new GsonImpl();
        String s = gsons.objectToJson(test1);
        Assert.assertNotNull(s);
        log.info(s);
        gsons.setMapper(new Gson());
        String s1 = gsons.objectToJson(test1);
        Assert.assertNotNull(s1);
        log.info(s1);
        gsons.setMapper(new ObjectMapper());
        String s2 = gsons.objectToJson(test1);
        Assert.assertNotNull(s2);
        log.info(s2);
        String s3 = gsons.objectToJson(null);
        Assert.assertNotNull(s3);
        log.info(s3);
    }

    @Test
    public void testObjectToJsonTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        GsonImpl gsons = new GsonImpl();
        String s = gsons.objectToJson(test1, new Gson());
        Assert.assertNotNull(s);
        log.info(s);
        s = gsons.objectToJson(null, new Gson());
        Assert.assertNotNull(s);
        log.info(s);
        s = gsons.objectToJson(null, null);
        Assert.assertNotNull(s);
        log.info(s);

    }

    @Test
    public void mapToObjectTest() throws JsonException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "测试1");
        GsonImpl gsons = new GsonImpl();
        Test1 test1 = gsons.mapToObject(map, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void testMapToObjectTest() throws JsonException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "测试1");
        GsonImpl gsons = new GsonImpl();
        Test1 test1 = gsons.mapToObject(map, Test1.class, new GsonBuilder().create());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        test1 = gsons.mapToObject(null, Test1.class, new GsonBuilder().create());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        test1 = gsons.mapToObject(null, null, new GsonBuilder().create());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        test1 = gsons.mapToObject(null, null, null);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void objectToMapTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        GsonImpl gsons = new GsonImpl();
        Map<?, ?> map = gsons.objectToMap(test1);
        Assert.assertNotNull(map);
        log.info(map.toString());
        Map<?, ?> map1 = gsons.objectToMap(null);
        Assert.assertNotNull(map1);
        log.info(map1.toString());
    }

    @Test
    public void testObjectToMapTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        GsonImpl gsons = new GsonImpl();
        Map<?, ?> map = gsons.objectToMap(test1, new GsonBuilder().create());
        Assert.assertNotNull(map);
        log.info(map.toString());
        map = gsons.objectToMap(null, new GsonBuilder().create());
        Assert.assertNotNull(map);
        log.info(map.toString());
        map = gsons.objectToMap(null, null);
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
