package com.hb0730.commons.lang.convert.impl;

import com.hb0730.commons.lang.BooleanUtils;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.convert.AbstractConverter;

import java.util.Date;

/**
 * 数字转换器<br>
 * 支持:
 * <pre>
 * 1.java.lang.Byte
 * 2.java.lang.Short
 * 3.java.lang.Integer
 * 4.java.lang.Long
 * 5.java.lang.Float
 * 6.java.lang.Double
 * </pre>
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class NumberConverter extends AbstractConverter<Number> {
    private final Class<? extends Number> targetType;

    public NumberConverter() {
        this.targetType = Number.class;
    }

    /**
     * 构造<br>
     *
     * @param clazz 需要转换的数字类型，默认 {@link Number}
     */
    public NumberConverter(Class<? extends Number> clazz) {
        this.targetType = (null == clazz) ? Number.class : clazz;
    }

    @Override
    protected Number convertInternal(Object value) {
        return convertInternal(value, this.targetType);
    }

    private Number convertInternal(Object value, Class<?> targetType) {
        if (Byte.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).byteValue();
            } else if (value instanceof Boolean) {
                return BooleanUtils.toByteObj((Boolean) value);
            }
            final String valueStr = toStr(value);
            return StringUtils.isBlank(valueStr) ? null : Byte.valueOf(valueStr);
        } else if (Short.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).shortValue();
            } else if (value instanceof Boolean) {
                return BooleanUtils.toShortObj((Boolean) value);
            }
            final String valueStr = toStr(value);
            return StringUtils.isBlank(valueStr) ? null : Short.valueOf(valueStr);
        } else if (Integer.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).intValue();
            } else if (value instanceof Boolean) {
                return BooleanUtils.toInteger((Boolean) value);
            }
            final String valueStr = toStr(value);
            return StringUtils.isBlank(valueStr) ? null : Integer.valueOf(valueStr);
        } else if (Long.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).longValue();
            } else if (value instanceof Boolean) {
                return BooleanUtils.toLongObj((Boolean) value);
            } else if (value instanceof Date) {
                return ((Date) value).getTime();
            }
            final String valueStr = toStr(value);
            return StringUtils.isBlank(valueStr) ? null : Long.valueOf(valueStr);
        } else if (Float.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).floatValue();
            } else if (value instanceof Boolean) {
                return BooleanUtils.toFloatObj((Boolean) value);
            }
            final String valueStr = toStr(value);
            return StringUtils.isBlank(valueStr) ? null : Float.valueOf(valueStr);
        } else if (Double.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            } else if (value instanceof Boolean) {
                return BooleanUtils.toDoubleObj((Boolean) value);
            }
            final String valueStr = toStr(value);
            return StringUtils.isBlank(valueStr) ? null : Double.valueOf(valueStr);
        }
        throw new UnsupportedOperationException(String.format("Unsupport Number type: %s", this.targetType.getName()));
    }
}
