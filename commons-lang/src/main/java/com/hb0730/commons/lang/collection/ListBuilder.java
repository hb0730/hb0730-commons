package com.hb0730.commons.lang.collection;

import com.hb0730.commons.lang.builder.Builder;

import java.util.Collection;
import java.util.List;

/**
 * 链式List
 * <pre>
 * ListBuilder&lt;String&gt; builder = ListBuilder.build(new ArrayList<>());
 * builder.add("test").add("test1");
 * List&lt;String&gt; list = builder.build();
 * </pre>
 *
 * @author bing_huang
 * @since 2.1.1
 */
public class ListBuilder<E> implements Builder<List<E>> {
    private final List<E> list;

    /**
     * 构建链式List
     *
     * @param list List
     * @param <E>  list元素类型
     * @return 链式List
     */
    public static <E> ListBuilder<E> build(List<E> list) {
        return new ListBuilder<>(list);
    }

    /**
     * 构造
     *
     * @param list List
     */
    public ListBuilder(List<E> list) {
        this.list = list;
    }

    /**
     * 添加元素
     *
     * @param e 元素值
     * @return this
     * @see List#add(Object)
     */
    public ListBuilder<E> add(E e) {
        this.list.add(e);
        return this;
    }

    /**
     * 添加指定下标元素值
     *
     * @param index 下标
     * @param e     元素值
     * @return this
     * @see List#add(int, Object)
     */
    public ListBuilder<E> add(int index, E e) {
        this.list.add(index, e);
        return this;
    }

    /**
     * 批量添加
     *
     * @param elements 值列表
     * @return this
     * @see List#addAll(Collection)
     */
    public ListBuilder<E> addAll(Collection<? extends E> elements) {
        this.list.addAll(elements);
        return this;
    }

    /**
     * 批量添加
     *
     * @param index    下标
     * @param elements 值列表
     * @return this
     * @see List#add(int, Object)
     */
    public ListBuilder<E> addAll(int index, Collection<? extends E> elements) {
        this.list.addAll(index, elements);
        return this;
    }

    /**
     * 移除
     *
     * @param e 元素
     * @return this
     * @see List#remove(Object)
     */
    public ListBuilder<E> remove(E e) {
        this.list.remove(e);
        return this;
    }

    /**
     * 移除
     *
     * @param index 下标
     * @return this
     * @see List#remove(int)
     */
    public ListBuilder<E> remove(int index) {
        this.list.remove(index);
        return this;
    }

    /**
     * 批量移除
     *
     * @param elements 元素列表
     * @return this
     * @see List#removeAll(Collection)
     */
    public ListBuilder<E> removeAll(Collection<? extends E> elements) {
        this.list.removeAll(elements);
        return this;
    }


    @Override
    public List<E> build() {
        return this.list;
    }
}
