package com.hb0730.commons.json.utils;

import com.alibaba.fastjson.parser.ParserConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hb0730.commons.json.IJson;
import com.hb0730.commons.json.exceptions.JsonException;
import com.hb0730.commons.json.fastjson.FastJsonImpl;
import com.hb0730.commons.json.gson.GsonImpl;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonsTest {

    @Test
    public void jsonToObjectTest() throws JsonException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        Jsons jsons = Jsons.JSONS;
        Test1 test1 = jsons.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        jsons = jsons.setJson(new FastJsonImpl());
        test1 = jsons.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        jsons = jsons.setJson(GsonImpl.class);
        test1 = jsons.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());

    }

    @Test
    public void getCurrentTest() {
        IJson json = Jsons.JSONS.getCurrentJson();
        Assert.assertNull("json为空", json);
    }

    @Test
    public void testJsonToObjectTest() throws JsonException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        Jsons jsons = Jsons.JSONS;
        Test1 test1 = jsons.jsonToObject(json, Test1.class, new ObjectMapper());
        Assert.assertNotNull(test1);
        log.info(test1.toString());

        jsons = jsons.setJson(FastJsonImpl.class);
        test1 = jsons.jsonToObject(json, Test1.class, new ParserConfig());
        Assert.assertNotNull(test1);
        log.info(test1.toString());

        jsons = jsons.setJson(GsonImpl.class);
        test1 = jsons.jsonToObject(json, Test1.class, new Gson());
        Assert.assertNotNull(test1);
        log.info(test1.toString());

        test1 = jsons.jsonToObject(json, Test1.class, new ObjectMapper());
        Assert.assertNotNull(test1);
        log.info(test1.toString());

    }

    @Test
    public void jsonToListTest() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        Jsons jsons = Jsons.JSONS;
        List<Test1> test1 = jsons.jsonToList(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void testJsonToListTest() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        Jsons jsons = Jsons.JSONS;
        List<Test1> test1 = jsons.jsonToList(json, Test1.class, new ObjectMapper());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void objectToJsonTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        Jsons jsons = Jsons.JSONS;
        String s = jsons.objectToJson(test1);
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void testObjectToJsonTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        Jsons jsons = Jsons.JSONS;
        String s = jsons.objectToJson(test1, new ObjectMapper());
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void mapToObjectTest() throws JsonException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "测试1");
        Jsons jsons = Jsons.JSONS;
        Test1 test1 = jsons.mapToObject(map, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void testMapToObjectTest() throws JsonException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "测试1");
        Jsons jsons = Jsons.JSONS;
        Test1 test1 = jsons.mapToObject(map, Test1.class, new ObjectMapper());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void objectToMapTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        Jsons jsons = Jsons.JSONS;
        Map<?, ?> map = jsons.objectToMap(test1);
        Assert.assertNotNull(map);
        log.info(map.toString());
    }

    @Test
    public void testObjectToMapTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        Jsons jsons = Jsons.JSONS;
        Map<?, ?> map = jsons.objectToMap(test1, new ObjectMapper());
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
