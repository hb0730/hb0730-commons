package com.hb0730.commons.lang.convert.impl;

import com.hb0730.commons.lang.convert.AbstractConverter;

import java.util.Optional;

/**
 * {@link Optional}转换器
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class OptionalConverter extends AbstractConverter<Optional<?>> {
    @Override
    protected Optional<?> convertInternal(Object value) {
        return Optional.ofNullable(value);
    }
}
