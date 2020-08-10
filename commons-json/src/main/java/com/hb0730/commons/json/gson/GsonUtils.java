package com.hb0730.commons.json.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.hb0730.commons.lang.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * google json utils
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class GsonUtils {
    private final static Gson DEFAULT_GSON = new Gson();

    private GsonUtils() {

    }

    /**
     * json 字符串转换
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @throws JsonParseException 对象转换异常
     */
    public static <T> T jsonToObject(String json, Class<T> type) throws JsonParseException {
        return jsonToObject(json, type, DEFAULT_GSON);
    }

    /**
     * json 字符串转换
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param gson gson实例,不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @throws JsonParseException 对象转换异常
     */
    public static <T> T jsonToObject(String json, Class<T> type, Gson gson) {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(gson, "gson must be not null");
        return gson.fromJson(json, type);
    }

    /**
     * json字符串转list对象
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     */
    public static <T> List<T> jsonToList(String json, Class<T> type) {
        return jsonToList(json, type, DEFAULT_GSON);
    }

    /**
     * json字符串转list对象
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param gson gson实例,不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     */
    public static <T> List<T> jsonToList(String json, Class<T> type, Gson gson) {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(gson, "gson must be not null");
        JsonArray arrayJson = JsonParser.parseString(json).getAsJsonArray();
        List<T> results = new ArrayList<>();
        for (final JsonElement element : arrayJson) {
            results.add(gson.fromJson(element, type));
        }
        return results;
    }

    /**
     * json字符串转list对象
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     */
    public static <T> List<T> jsonToList2(String json, Class<T> type) {
        return jsonToList2(json, type, DEFAULT_GSON);
    }

    /**
     * json字符串转list对象
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param gson gson实例,不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     */
    public static <T> List<T> jsonToList2(String json, Class<T> type, Gson gson) {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(gson, "gson must be not null");
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * 将对象转换成string json
     *
     * @param source 目标对象，不为空
     * @return json string
     */
    public static String objectToJson(Object source) {
        return objectToJson(source, DEFAULT_GSON);
    }

    /**
     * 将对象转换成string json
     *
     * @param source 目标对象，不为空
     * @param gson   gson 不为空
     * @return json string
     */
    public static String objectToJson(Object source, Gson gson) {
        Validate.notNull(source, "source object must be not null");
        Validate.notNull(gson, "gson must be not null");
        return gson.toJson(source);
    }

    /**
     * 将map类型转成对象类型
     *
     * @param sourceMap 源map类型对象，不为空
     * @param type      需要转成的对象类型，不为空
     * @param <T>       目标对象类型
     * @return 对象指定类型
     */
    public static <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type) {
        return mapToObject(sourceMap, type, DEFAULT_GSON);
    }

    /**
     * 将map类型转成对象类型
     *
     * @param sourceMap 源map类型对象，不为空
     * @param type      需要转成的对象类型，不为空
     * @param gson      gson，不为空
     * @param <T>       目标对象类型
     * @return 对象指定类型
     */
    public static <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type, Gson gson) {
        Validate.notNull(sourceMap, "source map must be not null");
        String json = objectToJson(sourceMap, gson);
        return jsonToObject(json, type, gson);
    }

    /**
     * 将 源目标对象转成map类型
     *
     * @param source 源目标，不为空
     * @return map实例
     */
    public static Map<?, ?> objectToMap(Object source) {
        return objectToMap(source, DEFAULT_GSON);
    }

    /**
     * 将 源目标对象转成map类型
     *
     * @param source 源目标，不为空
     * @param gson   gson，不为空
     * @return map实例
     */
    public static Map<?, ?> objectToMap(Object source, Gson gson) {
        String json = objectToJson(source, gson);
        return jsonToObject(json, Map.class, gson);
    }
}
