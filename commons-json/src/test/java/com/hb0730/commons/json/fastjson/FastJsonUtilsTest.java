package com.hb0730.commons.json.fastjson;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import lombok.*;
import org.junit.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FastJsonUtilsTest {

    @Test
    public void jsonToObjectTest() {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        Person person = FastJsonUtils.jsonToObject(json, Person.class);
        System.out.println(person);
    }

    @Test
    public void objectToJsonTest() {
        Person person = Person.builder().name("测试").id("1").build();
        System.out.println(FastJsonUtils.objectToJson(person));
    }

    @Test
    public void testObjectToJsonTest() {
        Person person = Person.builder().name("测试").id("1").build();
        System.out.println(FastJsonUtils.objectToJson(person, SerializeConfig.getGlobalInstance()));

    }

    @Test
    public void mapToObjectTest() {
        Map<String, String> personMap = new HashMap<>();
        personMap.put("name", "测试");
        personMap.put("id", "1");
        System.out.println(FastJsonUtils.mapToObject(personMap, Person.class));
    }

    @Test
    public void testMapToObjectTest() {
        Map<String, String> personMap = new HashMap<>();
        personMap.put("name", "测试");
        personMap.put("id", "1");
        System.out.println(FastJsonUtils.mapToObject(personMap, Person.class, SerializeConfig.getGlobalInstance()));
    }

    @Test
    public void objectToMapTest() {
        Person person = Person.builder().id("1").name("name").build();
        System.out.println(FastJsonUtils.objectToMap(person));
    }

    @Test
    public void testObjectToMapTest() {
        Person person = Person.builder().id("1").name("name").build();
        System.out.println(FastJsonUtils.objectToMap(person, SerializeConfig.getGlobalInstance()));
    }

    @Test
    public void jsonToListTest() {

        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        System.out.println(FastJsonUtils.jsonToList(json, Person.class));
    }

    @Test
    public void testJsonToListTest() {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        System.out.println(FastJsonUtils.jsonToList(json, Person.class, ParserConfig.getGlobalInstance()));
    }


    @Data
    @Builder
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person implements Serializable {
        private String id;
        private String name;
    }
}
