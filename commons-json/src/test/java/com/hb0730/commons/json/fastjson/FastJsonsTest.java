package com.hb0730.commons.json.fastjson;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
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
public class FastJsonsTest {

    @Test
    public void jsonToObjectTest() throws JsonException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        FastJsons fastjsons = new FastJsons();
        Test1 test1 = fastjsons.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        fastjsons.setParserConfig(new ParserConfig());
        test1 = fastjsons.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        fastjsons.setParserConfig(new ParserConfig());
        test1 = fastjsons.jsonToObject(null, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        fastjsons.setParserConfig(new ParserConfig());
        test1 = fastjsons.jsonToObject(json, null);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        fastjsons.setParserConfig(new ParserConfig());
    }

    @Test
    public void testJsonToObjectTest() throws JsonException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        FastJsons fastjsons = new FastJsons();
        Test1 test1 = fastjsons.jsonToObject(json, Test1.class, new ParserConfig());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void jsonToListTest() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        FastJsons fastJsons = new FastJsons();
        List<Test1> test1s = fastJsons.jsonToList(json, Test1.class);
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
        fastJsons.setParserConfig(new ParserConfig());
        test1s = fastJsons.jsonToList(json, Test1.class);
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
        test1s = fastJsons.jsonToList(null, Test1.class);
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
        test1s = fastJsons.jsonToList(null, null);
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
    }

    @Test
    public void testJsonToListTest() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        FastJsons fastJsons = new FastJsons();
        List<Test1> test1s = fastJsons.jsonToList(json, Test1.class, new ParserConfig());
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
    }

    @Test
    public void objectToJsonTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        FastJsons fastJsons = new FastJsons();
        String s = fastJsons.objectToJson(test1);
        Assert.assertNotNull(s);
        log.info(s);
        fastJsons.setSerializeConfig(SerializeConfig.getGlobalInstance());
        s = fastJsons.objectToJson(test1);
        Assert.assertNotNull(s);
        log.info(s);
        s = fastJsons.objectToJson(null);
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void testObjectToJsonTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        FastJsons fastJsons = new FastJsons();
        String s = fastJsons.objectToJson(test1, SerializeConfig.getGlobalInstance());
        Assert.assertNotNull(s);
        log.info(s);
        s = fastJsons.objectToJson(null, null);
        Assert.assertNotNull(s);
        log.info(s);
        s = fastJsons.objectToJson(null, null);
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void mapToObjectTest() throws JsonException {
        Map<String, String> map = new HashMap<>();
        map.put("name", "测试");
        map.put("id", "1");
        FastJsons fastJsons = new FastJsons();
        Test1 test1 = fastJsons.mapToObject(map, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        fastJsons.setSerializeConfig(SerializeConfig.getGlobalInstance());
        test1 = fastJsons.mapToObject(map, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        test1 = fastJsons.mapToObject(null, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        test1 = fastJsons.mapToObject(null, null);
        Assert.assertNotNull(test1);
        log.info(test1.toString());

    }

    @Test
    public void testMapToObjectTest() throws JsonException {
        Map<String, String> map = new HashMap<>();
        map.put("name", "测试");
        map.put("id", "1");
        FastJsons fastJsons = new FastJsons();
        Test1 test1 = fastJsons.mapToObject(map, Test1.class, SerializeConfig.getGlobalInstance());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        test1 = fastJsons.mapToObject(null, Test1.class, SerializeConfig.getGlobalInstance());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        test1 = fastJsons.mapToObject(null, null, SerializeConfig.getGlobalInstance());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        test1 = fastJsons.mapToObject(null, null, null);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void objectToMapTest() throws JsonException {
        Test1 test1 = Test1.builder().id("1").name("name").build();
        FastJsons fastJsons = new FastJsons();
        Map<?, ?> map = fastJsons.objectToMap(test1);
        Assert.assertNotNull(map);
        log.info(map.toString());
        map = fastJsons.objectToMap(null);
        Assert.assertNotNull(map);
        log.info(map.toString());
    }

    @Test
    public void testObjectToMapTest() throws JsonException {
        Test1 test1 = Test1.builder().id("1").name("name").build();
        FastJsons fastJsons = new FastJsons();
        Map<?, ?> map = fastJsons.objectToMap(test1, SerializeConfig.getGlobalInstance());
        Assert.assertNotNull(map);
        log.info(map.toString());
        map = fastJsons.objectToMap(null, SerializeConfig.getGlobalInstance());
        Assert.assertNotNull(map);
        log.info(map.toString());
        map = fastJsons.objectToMap(null, null);
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
