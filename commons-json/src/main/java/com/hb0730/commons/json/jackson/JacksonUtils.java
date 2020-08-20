package com.hb0730.commons.json.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.hb0730.commons.lang.Validate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * jackson utilities.
 *
 * @author bing_huang
 * @see JacksonImpl
 * @see com.hb0730.commons.json.utils.Jsons
 * @since 1.0.1
 * @deprecated 2.0.0
 */
public class JacksonUtils {

    public final static ObjectMapper DEFAULT_JSON_MAPPER = createDefaultJsonMapper();

    private JacksonUtils() {
    }


    /**
     * 构建默认mapper的json
     *
     * @return {@link ObjectMapper}实例
     * @see #createDefaultJsonMapper(PropertyNamingStrategy)
     */
    public static ObjectMapper createDefaultJsonMapper() {
        return createDefaultJsonMapper(null);
    }

    /**
     * 创建默认 mapper
     *
     * @param strategy 属性命名策略
     * @return {@link ObjectMapper}实例
     */
    public static ObjectMapper createDefaultJsonMapper(PropertyNamingStrategy strategy) {
        // Create object mapper
        ObjectMapper mapper = new ObjectMapper();
        // Configure
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Set property naming strategy
        if (strategy != null) {
            mapper.setPropertyNamingStrategy(strategy);
        }
        return mapper;
    }

    /**
     * json字符串转对象,默认使用{@link #DEFAULT_JSON_MAPPER}进行转换
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @throws IOException 对象转换异常
     * @see #jsonToObject(String, Class, ObjectMapper)
     */
    public static <T> T jsonToObject(String json, Class<T> type) throws IOException {
        return jsonToObject(json, type, DEFAULT_JSON_MAPPER);
    }

    /**
     * json 字符串转换
     *
     * @param json         json字符串，不为空
     * @param type         需要转换的类型，不为空
     * @param objectMapper {@link ObjectMapper},不为空
     * @param <T>          对象转换类型
     * @return 对象指定类型
     * @throws IOException 对象转换异常
     */
    public static <T> T jsonToObject(String json, Class<T> type, ObjectMapper objectMapper) throws IOException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(objectMapper, "object mapper must be not null");
        return objectMapper.readValue(json, type);
    }

    /**
     * json字符串转换list对象
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @throws IOException 对象转换异常
     */
    public static <T> List<T> jsonToList(String json, Class<T> type) throws IOException {
        return jsonToList(json, type, DEFAULT_JSON_MAPPER);
    }

    /**
     * json字符串转换list对象
     *
     * @param json         json字符串，不为空
     * @param type         需要转换的类型，不为空
     * @param objectMapper {@link ObjectMapper},不为空
     * @param <T>          对象转换类型
     * @return 对象指定类型
     * @throws IOException 对象转换异常
     */
    public static <T> List<T> jsonToList(String json, Class<T> type, ObjectMapper objectMapper) throws IOException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(objectMapper, "object mapper must be not null");
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
        return objectMapper.readValue(json, collectionType);
    }

    /**
     * json字符串转换list对象
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @throws IOException 对象转换异常
     */
    public static <T> List<T> jsonToList2(String json, Class<T> type) throws IOException {
        return jsonToList2(json, type, DEFAULT_JSON_MAPPER);
    }

    /**
     * json字符串转换list对象
     *
     * @param json         json字符串，不为空
     * @param type         需要转换的类型，不为空
     * @param objectMapper {@link ObjectMapper},不为空
     * @param <T>          对象转换类型
     * @return 对象指定类型
     * @throws IOException 对象转换异常
     */
    public static <T> List<T> jsonToList2(String json, Class<T> type, ObjectMapper objectMapper) throws IOException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(objectMapper, "object mapper must be not null");
        return objectMapper.readValue(json, new TypeReference<List<T>>() {
        });
    }

    /**
     * 将对象转换成string json,默认使用{@link #DEFAULT_JSON_MAPPER}
     *
     * @param source 目标对象，不为空
     * @return json string
     * @throws IOException 转换异常
     * @see #objectToJson(Object, ObjectMapper)
     */
    public static String objectToJson(Object source) throws IOException {
        return objectToJson(source, DEFAULT_JSON_MAPPER);
    }

    /**
     * 将对象转换成string json
     *
     * @param source       目标对象，不为空
     * @param objectMapper {@link ObjectMapper},不为空
     * @return json string
     * @throws IOException 转换异常
     */
    public static String objectToJson(Object source, ObjectMapper objectMapper) throws IOException {
        Validate.notNull(source, "source object must be not null");
        Validate.notNull(objectMapper, "object mapper must be not null");
        return objectMapper.writeValueAsString(source);
    }

    /**
     * 将map类型转成对象类型，默认使用{@link #DEFAULT_JSON_MAPPER}
     *
     * @param sourceMap 源map类型对象，不为空
     * @param type      需要转成的对象类型，不为空
     * @param <T>       目标对象类型
     * @return 对象指定类型
     * @throws IOException 转换异常
     * @see #mapToObject(Map, Class, ObjectMapper)
     */
    public static <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type) throws IOException {
        return mapToObject(sourceMap, type, DEFAULT_JSON_MAPPER);
    }

    /**
     * 将map类型转成对象类型
     *
     * @param sourceMap    源map类型对象，不为空
     * @param type         需要转成的对象类型，不为空
     * @param objectMapper {@link ObjectMapper}，不为空
     * @param <T>          目标对象类型
     * @return 对象指定类型
     * @throws IOException 转换异常
     */
    public static <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type, ObjectMapper objectMapper) throws IOException {
        Validate.notNull(sourceMap, "source map must be not null");
        String json = objectToJson(sourceMap, objectMapper);
        return jsonToObject(json, type, objectMapper);
    }

    /**
     * 将 源目标对象转成map类型，默认使用{@link #DEFAULT_JSON_MAPPER}
     *
     * @param source 源目标，不为空
     * @return map实例
     * @throws IOException 转换异常
     * @see #objectToMap(Object, ObjectMapper)
     */
    public static Map<?, ?> objectToMap(Object source) throws IOException {
        return objectToMap(source, DEFAULT_JSON_MAPPER);
    }

    /**
     * 将 源目标对象转成map类型
     *
     * @param source       源目标，不为空
     * @param objectMapper {@link ObjectMapper},不为空
     * @return map实例
     * @throws IOException 转换异常
     * @see #objectToJson(Object, ObjectMapper)
     * @see #jsonToObject(String, Class, ObjectMapper)
     */
    public static Map<?, ?> objectToMap(Object source, ObjectMapper objectMapper) throws IOException {
        String json = objectToJson(source, objectMapper);
        return jsonToObject(json, Map.class, objectMapper);
    }
}
