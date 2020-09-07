package com.hb0730.commons.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.hb0730.commons.json.AbstractJson;
import com.hb0730.commons.json.exceptions.JsonException;
import com.hb0730.commons.lang.Validate;

import java.util.List;
import java.util.Map;

/**
 * fastJson封装
 *
 * @author bing_huang
 */
public class FastJsonImpl extends AbstractJson {
    public static final ParserConfig PARSER_CONFIG = ParserConfig.getGlobalInstance();
    public static final SerializeConfig SERIALIZE_CONFIG = SerializeConfig.getGlobalInstance();
    private ParserConfig parserConfig;
    private SerializeConfig serializeConfig;

    @Override
    @Deprecated
    public void setMapper(Object mapper) throws JsonException {

    }

    @Override
    @Deprecated
    public Object getMapper() {
        return null;
    }

    public ParserConfig getParserConfig() {
        if (null == this.parserConfig) {
            return PARSER_CONFIG;
        }
        return parserConfig;
    }

    public void setParserConfig(ParserConfig parserConfig) {
        this.parserConfig = parserConfig;
    }

    public SerializeConfig getSerializeConfig() {
        if (null == this.serializeConfig) {
            return SERIALIZE_CONFIG;
        }
        return serializeConfig;
    }

    public void setSerializeConfig(SerializeConfig serializeConfig) {
        this.serializeConfig = serializeConfig;
    }

    @Override
    public <T> T jsonToObject(String json, Class<T> type) throws JsonException {
        return jsonToObject(json, type, getParserConfig());
    }

    @Override
    public <T> T jsonToObject(String json, Class<T> type, Object parserConfig) throws JsonException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target class type must be not null");
        Validate.notNull(parserConfig, "parserConfig must be not null");
        if (parserConfig instanceof ParserConfig) {
            try {
                return JSON.parseObject(json, type, (ParserConfig) parserConfig);
            } catch (JSONException e) {
                throw new JsonException(e);
            }

        }
        return null;
    }

    @Override
    public <T> List<T> jsonToList(String json, Class<T> type) throws JsonException {
        return jsonToList(json, type, getParserConfig());
    }

    @Override
    public <T> List<T> jsonToList(String json, Class<T> type, Object parserConfig) throws JsonException {
        Validate.notBlank(json, "json content must be not null");
        Validate.notNull(type, "target class type must be not null");
        Validate.notNull(parserConfig, "parserConfig must be not null");
        if (parserConfig instanceof ParserConfig) {
            try {
                return JSONArray.parseArray(json, type, (ParserConfig) parserConfig);
            } catch (JSONException e) {
                throw new JsonException(e);
            }
        }
        return null;
    }

    @Override
    public String objectToJson(Object source) throws JsonException {
        return objectToJson(source, getSerializeConfig());
    }

    @Override
    public String objectToJson(Object source, Object serializeConfig) throws JsonException {
        Validate.notNull(source, "source object must be not null");
        Validate.notNull(serializeConfig, "serializeConfig config must be not null");
        if (serializeConfig instanceof SerializeConfig) {
            try {
                return JSON.toJSONString(source, (SerializeConfig) serializeConfig);
            } catch (JSONException e) {
                throw new JsonException(e);
            }
        }
        return null;
    }

    @Override
    public <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type) throws JsonException {
        return mapToObject(sourceMap, type, getSerializeConfig());
    }

    @Override
    public <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type, Object serializeConfig) throws JsonException {
        Validate.notNull(sourceMap, "source map must be not null");
        String json = objectToJson(sourceMap, serializeConfig);
        return jsonToObject(json, type);
    }

    @Override
    public <T> Map<String, T> objectToMap(Object source) throws JsonException {
        return objectToMap(source, getSerializeConfig());
    }

    @Override
    public <T> Map<String, T> objectToMap(Object source, Object serializeConfig) throws JsonException {
        String json = objectToJson(source, serializeConfig);
        try {
            return JSON.parseObject(json, Map.class, getParserConfig());
        } catch (JSONException e) {
            throw new JsonException(e);
        }
    }
}
