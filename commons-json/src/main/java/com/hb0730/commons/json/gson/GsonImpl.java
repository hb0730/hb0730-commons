package com.hb0730.commons.json.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.hb0730.commons.json.AbstractJson;
import com.hb0730.commons.json.exceptions.JsonException;
import com.hb0730.commons.lang.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * gson封装
 *
 * @author bing_huang
 * @since 2.0.0
 */
public class GsonImpl extends AbstractJson {
    private static final Gson GSON = new Gson();

    @Override
    public void setMapper(Object mapper) throws JsonException {
        if (mapper instanceof Gson) {
            super.mapper = mapper;
        } else {
            throw new JsonException("Illegal type");
        }
    }

    @Override
    public Object getMapper() {
        if (null == super.mapper) {
            return GSON;
        }
        return super.mapper;
    }

    @Override
    public <T> T jsonToObject(String json, Class<T> type) throws JsonException {
        return jsonToObject(json, type, getMapper());
    }

    @Override
    public <T> T jsonToObject(String json, Class<T> type, Object gson) throws JsonException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(gson, "gson must be not null");
        if (gson instanceof Gson) {
            try {
                return ((Gson) gson).fromJson(json, type);
            } catch (JsonParseException e) {
                throw new JsonException(e);
            }
        }
        return null;
    }

    @Override
    public <T> List<T> jsonToList(String json, Class<T> type) throws JsonException {
        return jsonToList(json, type, getMapper());
    }

    @Override
    public <T> List<T> jsonToList(String json, Class<T> type, Object gson) throws JsonException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(gson, "gson must be not null");
        if (gson instanceof Gson) {
            try {
                JsonArray arrayJson = JsonParser.parseString(json).getAsJsonArray();
                List<T> results = new ArrayList<>();
                for (JsonElement element : arrayJson) {
                    results.add(((Gson) gson).fromJson(element, type));
                }
                return results;
            } catch (JsonParseException e) {
                throw new JsonException(e);
            }
        }
        return null;
    }

    /**
     * json字符串转list对象
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     */
    public <T> List<T> jsonToList2(String json, Class<T> type) throws JsonParseException {
        return jsonToList2(json, type, (Gson) getMapper());
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
    public <T> List<T> jsonToList2(String json, Class<T> type, Gson gson) throws JsonParseException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(gson, "gson must be not null");
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    @Override
    public String objectToJson(Object source) throws JsonException {
        return objectToJson(source, getMapper());
    }

    @Override
    public String objectToJson(Object source, Object gson) throws JsonException {
        Validate.notNull(source, "source object must be not null");
        Validate.notNull(gson, "gson must be not null");
        if (gson instanceof Gson) {
            try {
                return ((Gson) gson).toJson(source);
            } catch (JsonParseException e) {
                throw new JsonException(e);
            }
        }
        return null;
    }

    @Override
    public <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type) throws JsonException {
        return mapToObject(sourceMap, type, getMapper());
    }

    @Override
    public <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type, Object gson) throws JsonException {
        Validate.notNull(sourceMap, "source map must be not null");
        String json = objectToJson(sourceMap, gson);
        return jsonToObject(json, type, gson);
    }

    @Override
    public <T> Map<String, T> objectToMap(Object source) throws JsonException {
        return objectToMap(source, getMapper());
    }

    @Override
    public <T> Map<String, T> objectToMap(Object source, Object gson) throws JsonException {
        String json = objectToJson(source, gson);
        if (gson instanceof Gson) {
            try {
                return ((Gson) gson).fromJson(json, new TypeToken<Map<String, T>>() {
                }.getType());
            } catch (JsonParseException e) {
                throw new JsonException(e);
            }
        }
        return null;
    }
}
