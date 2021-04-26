package com.hb0730.commons.json.fastjson;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.hb0730.commons.json.exceptions.JsonException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FastJsonImplTest {

    @Test
    public void jsonToObjectTest() throws JsonException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        FastJsonImpl fastjsons = new FastJsonImpl();
        Test1 test1 = fastjsons.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        fastjsons.setParserConfig(new ParserConfig());
        test1 = fastjsons.jsonToObject(json, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        fastjsons.setParserConfig(new ParserConfig());
        try {
            test1 = fastjsons.jsonToObject(null, Test1.class);
            Assert.assertNotNull(test1);
            log.info(test1.toString());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        try {
            fastjsons.setParserConfig(new ParserConfig());
            test1 = fastjsons.jsonToObject(json, null);
            Assert.assertNotNull(test1);
            log.info(test1.toString());
            fastjsons.setParserConfig(new ParserConfig());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testJsonToObjectTest() throws JsonException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        FastJsonImpl fastjsons = new FastJsonImpl();
        Test1 test1 = fastjsons.jsonToObject(json, Test1.class, new ParserConfig());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
    }

    @Test
    public void jsonToListTest() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        FastJsonImpl fastJsons = new FastJsonImpl();
        List<Test1> test1s = fastJsons.jsonToList(json, Test1.class);
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
        fastJsons.setParserConfig(new ParserConfig());
        test1s = fastJsons.jsonToList(json, Test1.class);
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
        try {
            test1s = fastJsons.jsonToList(null, Test1.class);
            Assert.assertNotNull(test1s);
            log.info(test1s.toString());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        try {
            test1s = fastJsons.jsonToList(null, null);
            Assert.assertNotNull(test1s);
            log.info(test1s.toString());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testJsonToListTest() throws JsonException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        FastJsonImpl fastJsons = new FastJsonImpl();
        List<Test1> test1s = fastJsons.jsonToList(json, Test1.class, new ParserConfig());
        Assert.assertNotNull(test1s);
        log.info(test1s.toString());
    }

    @Test
    public void objectToJsonTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        FastJsonImpl fastJsons = new FastJsonImpl();
        String s = fastJsons.objectToJson(test1);
        Assert.assertNotNull(s);
        log.info(s);
        fastJsons.setSerializeConfig(SerializeConfig.getGlobalInstance());
        s = fastJsons.objectToJson(test1);
        Assert.assertNotNull(s);
        log.info(s);
        try {
            s = fastJsons.objectToJson(null);
            Assert.assertNotNull(s);
            log.info(s);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testObjectToJsonTest() throws JsonException {
        Test1 test1 = Test1.builder().name("测试").id("1").build();
        FastJsonImpl fastJsons = new FastJsonImpl();
        String s = fastJsons.objectToJson(test1, SerializeConfig.getGlobalInstance());
        Assert.assertNotNull(s);
        log.info(s);
        try {
            s = fastJsons.objectToJson(null, null);
            Assert.assertNotNull(s);
            log.info(s);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        try {
            s = fastJsons.objectToJson(null, null);
            Assert.assertNotNull(s);
            log.info(s);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void mapToObjectTest() throws JsonException {
        Map<String, String> map = new HashMap<>();
        map.put("name", "测试");
        map.put("id", "1");
        FastJsonImpl fastJsons = new FastJsonImpl();
        Test1 test1 = fastJsons.mapToObject(map, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        fastJsons.setSerializeConfig(SerializeConfig.getGlobalInstance());
        test1 = fastJsons.mapToObject(map, Test1.class);
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        try {
            test1 = fastJsons.mapToObject(null, Test1.class);
            Assert.assertNotNull(test1);
            log.info(test1.toString());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        try {
            test1 = fastJsons.mapToObject(null, null);
            Assert.assertNotNull(test1);
            log.info(test1.toString());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void testMapToObjectTest() throws JsonException {
        Map<String, String> map = new HashMap<>();
        map.put("name", "测试");
        map.put("id", "1");
        FastJsonImpl fastJsons = new FastJsonImpl();
        Test1 test1 = fastJsons.mapToObject(map, Test1.class, SerializeConfig.getGlobalInstance());
        Assert.assertNotNull(test1);
        log.info(test1.toString());
        try {
            test1 = fastJsons.mapToObject(null, Test1.class, SerializeConfig.getGlobalInstance());
            Assert.assertNotNull(test1);
            log.info(test1.toString());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        try {
            test1 = fastJsons.mapToObject(null, null, SerializeConfig.getGlobalInstance());
            Assert.assertNotNull(test1);
            log.info(test1.toString());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        try {
            test1 = fastJsons.mapToObject(null, null, null);
            Assert.assertNotNull(test1);
            log.info(test1.toString());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void objectToMapTest() throws JsonException {
        Test1 test1 = Test1.builder().id("1").name("name").build();
        FastJsonImpl fastJsons = new FastJsonImpl();
        Map<?, ?> map = fastJsons.objectToMap(test1);
        Assert.assertNotNull(map);
        log.info(map.toString());
        try {
            map = fastJsons.objectToMap(null);
            Assert.assertNotNull(map);
            log.info(map.toString());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testObjectToMapTest() throws JsonException {
        Test1 test1 = Test1.builder().id("1").name("name").build();
        FastJsonImpl fastJsons = new FastJsonImpl();
        Map<?, ?> map = fastJsons.objectToMap(test1, SerializeConfig.getGlobalInstance());
        Assert.assertNotNull(map);
        log.info(map.toString());
        try {
            map = fastJsons.objectToMap(null, SerializeConfig.getGlobalInstance());
            log.info(map.toString());
            Assert.assertNotNull(map);
        } catch (Throwable e) {
            log.error("参数为空");
        }
        try {
            map = fastJsons.objectToMap(null, null);
            Assert.assertNotNull(map);
            log.info(map.toString());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
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
