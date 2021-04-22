package com.hb0730.commons.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.hb0730.commons.json.exceptions.JsonException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JacksonImplTest {

    private JacksonImpl jacksons;

    @Before
    public void init() {
        jacksons = new JacksonImpl();
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
        Test1 test111 = jacksons.jsonToObject(null, (Class<? extends Test1>) null, mapper);
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

    //------------------------泛型擦除问题
    @Test
    public void test() throws JsonProcessingException {
        Test2 test1 = new Test2();
        test1.setId("1");
        test1.setName("测试1");
        Test2 test2 = new Test2();
        test2.setId("2");
        test2.setName("测试2");
        Page<Test2> page1 = new Page<>();
        page1.setData(Arrays.asList(test1, test2));
        page1.setName("test");

        String jsonStr = jacksons.objectToJson(page1);
        Page<Test2> page2 = jacksons.jsonToObject(jsonStr, Page.class);

        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(Page.class, Test2.class);
        Page<Test2> page3 = jacksons.jsonObject(jsonStr, javaType);
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

    @Data
    public static class Test2 implements Serializable {
        private String id;
        private String name;
    }

    @Data
    public static class Page<T> implements Serializable {
        private String name;
        private List<T> data;
    }
}
