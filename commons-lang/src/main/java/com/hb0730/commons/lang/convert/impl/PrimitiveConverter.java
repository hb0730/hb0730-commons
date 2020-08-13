package com.hb0730.commons.lang.convert.impl;

import com.hb0730.commons.lang.BooleanUtils;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.convert.AbstractConverter;

import java.util.Date;

/**
 * 8种原始类型转换器<br>
 * <ul>
 *     <li>byte</li>
 *     <li>short</li>
 *     <li>int</li>
 *     <li>long</li>
 *     <li>float</li>
 *     <li>double</li>
 *     <li>char</li>
 *     <li>boolean</li>
 * </ul>
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class PrimitiveConverter extends AbstractConverter<Object> {
    private final Class<?> targetType;

    public PrimitiveConverter(Class<?> clazz) {
        if (null == clazz) {
            throw new NullPointerException("PrimitiveConverter not allow null target type!");
        } else if (!clazz.isPrimitive()) {
            throw new IllegalArgumentException("[" + clazz + "] is not a primitive class!");
        }
        this.targetType = clazz;
    }


    @Override
    protected Object convertInternal(Object value) {
        try {
            if (byte.class == this.targetType) {
                if (value instanceof Number) {
                    return ((Number) value).byteValue();
                } else if (value instanceof Boolean) {
                    return BooleanUtils.toByte((boolean) value);
                }
                final String str = toStr(value);
                if (StringUtils.isBlank(str)) {
                    return 0;
                }
                return Byte.parseByte(str);
            } else if (short.class == this.targetType) {
                if (value instanceof Number) {
                    return ((Number) value).shortValue();
                } else if (value instanceof Boolean) {
                    return BooleanUtils.toShort((boolean) value);
                }
                final String str = toStr(value);
                if (StringUtils.isBlank(str)) {
                    return 0;
                }
                return Short.parseShort(str);
            } else if (int.class == this.targetType) {
                if (value instanceof Number) {
                    return ((Number) value).intValue();
                } else if (value instanceof Boolean) {
                    return BooleanUtils.toInt((boolean) value);
                } else if (value instanceof Date) {
                    return ((Date) value).getTime();
                }
                final String str = toStr(value);
                if (StringUtils.isBlank(str)) {
                    return 0;
                }
                return Integer.parseInt(str);
            } else if (long.class == this.targetType) {
                if (value instanceof Number) {
                    return ((Number) value).longValue();
                } else if (value instanceof Boolean) {
                    return BooleanUtils.toLong((boolean) value);
                } else if (value instanceof Date) {
                    return ((Date) value).getTime();
                }
                final String str = toStr(value);
                if (StringUtils.isBlank(str)) {
                    return 0;
                }
                return Long.parseLong(str);
            } else if (float.class == this.targetType) {
                if (value instanceof Number) {
                    return ((Number) value).floatValue();
                } else if (value instanceof Boolean) {
                    return BooleanUtils.toFloat((Boolean) value);
                }
                final String str = toStr(value);
                if (StringUtils.isBlank(str)) {
                    return 0;
                }
                return Float.parseFloat(str);
            } else if (double.class == this.targetType) {
                if (value instanceof Number) {
                    return ((Number) value).doubleValue();
                } else if (value instanceof Boolean) {
                    return BooleanUtils.toDouble((Boolean) value);
                }
                final String str = toStr(value);
                if (StringUtils.isBlank(str)) {
                    return 0;
                }
                return Double.parseDouble(str);

            } else if (char.class == this.targetType) {
                if (value instanceof Character) {
                    //noinspection UnnecessaryUnboxing
                    return ((Character) value).charValue();
                } else if (value instanceof Boolean) {
                    return BooleanUtils.toChar((Boolean) value);
                }
                final String str = toStr(value);
                if (StringUtils.isBlank(str)) {
                    return 0;
                }
                return str.charAt(0);
            } else if (boolean.class == this.targetType) {
                if (value instanceof Boolean) {
                    //noinspection UnnecessaryUnboxing
                    return ((Boolean) value).booleanValue();
                }
                String valueStr = toStr(value);
                return BooleanUtils.toBoolean(valueStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<Object> getTargetType() {
        return (Class<Object>) this.targetType;
    }
}
