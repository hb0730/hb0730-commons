package com.hb0730.commons.json.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.hb0730.commons.json.AbstractJson;
import com.hb0730.commons.json.exceptions.JsonException;
import com.hb0730.commons.lang.Validate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * json 封装
 *
 * @author bing_huang
 * @since 2.0.0
 */
public class JacksonImpl extends AbstractJson {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void setMapper(Object mapper) throws JsonException {
        if (mapper instanceof ObjectMapper) {
            super.mapper = mapper;
        } else {
            throw new JsonException("Illegal type");
        }
    }

    @Override
    public Object getMapper() {
        if (null == super.mapper) {
            return MAPPER;
        }
        return super.mapper;
    }

    @Override
    public <T> T jsonToObject(String json, Class<T> type) throws JsonException {
        return jsonToObject(json, type, getMapper());
    }

    @Override
    public <T> T jsonToObject(String json, Class<T> type, Object mapper) throws JsonException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(mapper, "ObjectMapper must be not null");
        if (mapper instanceof ObjectMapper) {
            try {

                return ((ObjectMapper) mapper).readValue(json, type);
            } catch (IOException e) {
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
    public <T> List<T> jsonToList(String json, Class<T> type, Object mapper) throws JsonException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(mapper, "object mapper must be not null");
        if (mapper instanceof ObjectMapper) {
            try {

                CollectionType collectionType = ((ObjectMapper) mapper).getTypeFactory().constructCollectionType(List.class, type);
                return ((ObjectMapper) mapper).readValue(json, collectionType);
            } catch (IOException e) {
                throw new JsonException(e);
            }
        }
        return null;
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
    public <T> List<T> jsonToList2(String json, Class<T> type) throws IOException {
        return jsonToList2(json, type, (ObjectMapper) getMapper());
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
    public <T> List<T> jsonToList2(String json, Class<T> type, ObjectMapper objectMapper) throws IOException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target type must be not null");
        Validate.notNull(objectMapper, "object mapper must be not null");
        return objectMapper.readValue(json, new TypeReference<List<T>>() {
        });
    }

    @Override
    public String objectToJson(Object source) throws JsonException {
        return objectToJson(source, getMapper());
    }

    @Override
    public String objectToJson(Object source, Object mapper) throws JsonException {
        Validate.notNull(source, "source object must be not null");
        Validate.notNull(mapper, "object mapper must be not null");
        if (mapper instanceof ObjectMapper) {
            try {
                return ((ObjectMapper) mapper).writeValueAsString(source);
            } catch (IOException e) {
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
    public <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type, Object mapper) throws JsonException {
        Validate.notNull(sourceMap, "source map must be not null");
        String json = objectToJson(sourceMap, mapper);
        return jsonToObject(json, type, mapper);
    }

    @Override
    public Map<?, ?> objectToMap(Object source) throws JsonException {
        return objectToMap(source, getMapper());
    }

    @Override
    public Map<?, ?> objectToMap(Object source, Object mapper) throws JsonException {
        String json = objectToJson(source, mapper);
        return jsonToObject(json, Map.class, mapper);
    }
}
