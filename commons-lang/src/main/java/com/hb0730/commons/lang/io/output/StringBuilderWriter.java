package com.hb0730.commons.lang.io.output;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

/**
 * @author bing_huang
 * @since 1.0.1
 */
public class StringBuilderWriter extends Writer implements Serializable {
    private final StringBuilder builder;

    public StringBuilderWriter() {
        this.builder = new StringBuilder();
    }

    public StringBuilderWriter(StringBuilder builder) {
        this.builder = builder == null ? new StringBuilder() : builder;
    }

    public StringBuilderWriter(int capacity) {
        this.builder = new StringBuilder(capacity);
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    /**
     * 将字符追加到{@link StringBuilder}
     *
     * @param value 需要追加的字符
     * @return 当前实例
     */
    @Override
    public Writer append(final char value) {
        builder.append(value);
        return this;
    }

    /**
     * 将字符追加到{@link StringBuilder}
     *
     * @param value 需要追加的字符
     * @return 当前实例
     */
    @Override
    public Writer append(final CharSequence value) {
        builder.append(value);
        return this;
    }

    /**
     * 将字符追加到{@link StringBuilder}
     *
     * @param value 需要追加的字符
     * @param start 开始节点
     * @param end   结束节点
     * @return 当前实例
     */
    @Override
    public Writer append(final CharSequence value, final int start, final int end) {
        builder.append(value, start, end);
        return this;
    }

    /**
     * 将字符串写入{@link StringBuilder}。
     *
     * @param value 被写入的字符串
     */
    @Override
    public void write(final String value) {
        if (value != null) {
            builder.append(value);
        }
    }

    @Override
    public void write(char[] value, int off, int len) throws IOException {
        if (value != null) {
            builder.append(value, off, len);
        }
    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public String toString() {
        return this.builder.toString();
    }
}
