package com.hb0730.commons.lang.collection;

import com.hb0730.commons.lang.ObjectUtils;
import com.hb0730.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * array Util
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class ArrayUtils {
    /**
     * 数组中元素未找到的下标，值为-1
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * 校验array是否为空
     *
     * @param array 需校验数组
     * @param <T>   数组类型
     * @return true: 数组为空或者长度length为0
     */
    public static <T> boolean isEmpty(T[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断当前对象是否为数组
     *
     * @param obj 需要判断的对象
     * @return true:当前对象为数组
     */
    public static boolean isArray(Object obj) {
        if (null == obj) {
            return false;
        }
        return obj.getClass().isArray();
    }

    /**
     * 校验数组是否为空，否则返回默认数组
     *
     * @param array        数组
     * @param defaultArray 默认数组
     * @param <T>          数组类型
     * @return 如果数组为空，返回默认数组，否则返回当前数组
     */
    public static <T> T[] defaultIfEmpty(T[] array, T[] defaultArray) {
        return isEmpty(array) ? defaultArray : array;
    }

    /**
     * 校验数组是否为空<br>
     * 1.校验当前对象为<code>null</code>,返回true<br>
     * 2.校验当前对象非数组,返回true<br>
     * 3.此对象为数组，判断length为0,返回false
     *
     * @param obj 校验对象
     * @return true: 为空
     */
    public static boolean isEmpty(Object obj) {
        if (null != obj) {
            if (isArray(obj)) {
                return 0 != Array.getLength(obj);
            }
        }
        return true;
    }

    /**
     * 判断两数组长度是否相同。当数组为<code>null</code>时，长度为0
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return true: 数组1长度等于数组2长度，
     */
    public static boolean isArraySameLength(Object[] array1, Object[] array2) {
        int len1 = isEmpty(array1) ? 0 : Array.getLength(array1);
        int len2 = isEmpty(array2) ? 0 : Array.getLength(array2);
        return len1 == len2;
    }

    /**
     * 数组不为空
     *
     * @param array 数组
     * @param <T>   数组类型
     * @return 是否非空
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return (null != array && array.length > 0);
    }

    /**
     * 反转数组的元素顺序。如果数组为<code>null</code>，则什么也不做。
     *
     * @param array 要反转的数组
     * @param <T>   数组类型
     */
    public static <T> void arrayReverse(T[] array) {
        if (isEmpty(array)) {
            return;
        }
        T temp;
        for (int i = 0, j = array.length - 1; j > i; i++, j--) {
            temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
    }

    /**
     * 校验是否包含<code>null</code>元素
     *
     * @param array 数组
     * @param <T>   数组类型
     * @return true:数组包含<code>null</code>元素
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean hashNull(T... array) {
        if (isNotEmpty(array)) {
            for (final T element : array) {
                if (null == element) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取第一个不为<code>null</code>的元素
     *
     * @param array 数组
     * @param <T>   数组元素
     * @return 素组第一个不为<code>null</code>的元素,如果素组为<code>null</code>,则返回<code>null</code>
     */
    @SuppressWarnings("unchecked")
    public static <T> T fistNonNullEl(T... array) {
        if (isNotEmpty(array)) {
            for (final T element : array) {
                if (null != element) {
                    return element;
                }
            }
        }
        return null;
    }

    /**
     * 数组是否包含元素
     *
     * @param array 数组
     * @param value 被检测元素
     * @param <T>   数组类型
     * @return 是否包含
     * @see #arrayIndexOf(Object[], Object, int)
     * @see #arrayIndexOf(Object[], Object)
     */
    public static <T> boolean contains(T[] array, T value) {
        return arrayIndexOf(array, value, 0) > INDEX_NOT_FOUND;
    }

    /**
     * 数组是否包含指定元素，忽略大小写
     *
     * @param array 数组
     * @param value 被检测的元素
     * @return 是否包含
     * @see #arrayIndexOfIgnoreCase(CharSequence[], CharSequence)
     * @see #arrayIndexOfIgnoreCase(CharSequence[], CharSequence, int)
     */
    public static boolean containsIgnoreCase(CharSequence[] array, CharSequence value) {
        return arrayIndexOfIgnoreCase(array, value) > INDEX_NOT_FOUND;
    }

    /**
     * 数组中是否包含指定元素中的任意一个
     *
     * @param array  数组
     * @param values 被检查的多个元素
     * @param <T>    数组类型
     * @return 是否包含指定元素中的任意一个
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean containsAny(T[] array, T... values) {
        for (T value : values) {
            if (contains(array, value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询数组中指定元素第一次的位置<br>
     * 当数组为<code>null</code>,则返回{@link #INDEX_NOT_FOUND}<br>
     * 当起止位置小于<code>0</code>,默认从<code>0</code>开始,如果起始位置大于数组位置则返回{@link #INDEX_NOT_FOUND}
     *
     * @param array 数组
     * @param value 查询查找的元素，可以为空
     * @param <T>   数组类型
     * @return 元素在数组第一次出现的位置
     * @see #arrayIndexOf(Object[], Object, int)
     */
    public static <T> int arrayIndexOf(T[] array, Object value) {
        return arrayIndexOf(array, value, 0);
    }

    /**
     * 查询数组中指定元素第一次的位置<br>
     * 当数组为<code>null</code>,则返回{@link #INDEX_NOT_FOUND}<br>
     * 当起止位置小于<code>0</code>,默认从<code>0</code>开始,如果起始位置大于数组位置则返回{@link #INDEX_NOT_FOUND}
     *
     * @param array      数组
     * @param value      查询查找的元素，可以为空
     * @param startIndex 起止位置，
     * @param <T>        数组类型
     * @return 元素在数组第一次出现的位置
     */
    public static <T> int arrayIndexOf(T[] array, Object value, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        } else if (startIndex > array.length) {
            return INDEX_NOT_FOUND;
        }
        if (value == null) {
            for (int i = 0; i < array.length; i++) {
                if (null == array[i]) {
                    return i;
                }
            }
        } else {
            for (int i = startIndex; i < array.length; i++) {
                if (ObjectUtils.equal(array[i], value)) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 返回数组中指定元素所在位置，忽略大小写，<br>
     * 当数组为<code>null</code>,则返回{@link #INDEX_NOT_FOUND}<br>
     * 当起止位置小于<code>0</code>,默认从<code>0</code>开始,如果起始位置大于数组位置则返回{@link #INDEX_NOT_FOUND}
     *
     * @param array 数组
     * @param value 指定元素
     * @return 数组中指定元素所在位置，未找到返回{@link #INDEX_NOT_FOUND}
     * @see #arrayIndexOfIgnoreCase(CharSequence[], CharSequence, int)
     */
    public static int arrayIndexOfIgnoreCase(CharSequence[] array, CharSequence value) {
        return arrayIndexOfIgnoreCase(array, value, 0);
    }

    /**
     * 返回数组中指定元素所在位置，忽略大小写，<br>
     * 当数组为<code>null</code>,则返回{@link #INDEX_NOT_FOUND}<br>
     * 当起止位置小于<code>0</code>,默认从<code>0</code>开始,如果起始位置大于数组位置则返回{@link #INDEX_NOT_FOUND}
     *
     * @param array      数组
     * @param value      指定元素
     * @param startIndex 开始位置
     * @return 数组中指定元素所在位置，未找到返回{@link #INDEX_NOT_FOUND}
     */
    public static int arrayIndexOfIgnoreCase(CharSequence[] array, CharSequence value, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        } else if (startIndex > array.length) {
            return INDEX_NOT_FOUND;
        }
        if (value == null) {
            for (int i = 0; i < array.length; i++) {
                if (null == array[i]) {
                    return i;
                }
            }
        } else {
            for (int i = startIndex; i < array.length; i++) {
                if (StringUtils.equals(value, array[i], true)) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 查询数组中指定元素最后的位置<br>
     * 如果未找到或数组为<code>null</code>则返回{@link #INDEX_NOT_FOUND}<br>
     * 起始索引小于<code>0</code>则返回<code>-1</code>，超出数组长度的起始索引则从数组末尾开始找。
     *
     * @param array 要扫描的数组
     * @param value 要查找的元素
     * @param <T>   数组类型
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回{@link #INDEX_NOT_FOUND}
     * @see #arrayLastIndexOf(Object[], Object, int)
     */
    public static <T> int arrayLastIndexOf(T[] array, Object value) {
        return arrayLastIndexOf(array, value, Integer.MAX_VALUE);
    }

    /**
     * 查询数组中指定元素最后的位置<br>
     * 如果未找到或数组为<code>null</code>则返回{@link #INDEX_NOT_FOUND}<br>
     * 起始索引小于<code>0</code>则返回{@link #INDEX_NOT_FOUND}，超出数组长度的起始索引则从数组末尾开始找。
     *
     * @param array      要扫描的数组
     * @param value      要查找的元素
     * @param startIndex 开始索引
     * @param <T>        数组类型
     * @return 该元素在数组中的序号，如果数组为<code>null</code>或未找到，则返回{@link #INDEX_NOT_FOUND}
     */
    public static <T> int arrayLastIndexOf(T[] array, Object value, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        if (value == null) {
            for (int i = array.length - 1; i >= startIndex; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = array.length - 1; i >= startIndex; i--) {
                if (ObjectUtils.equal(array[i], value)) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 获取指定位置的元素<br>
     * 当索引小于0，则index为0，当索引大于数组长度，则index为数组长度
     *
     * @param array 数组
     * @param index 索引
     * @param <T>   数组类型
     * @return 指定位置的元素
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T get(Object[] array, int index) {
        if (null == array) {
            return null;
        }
        if (index < 0) {
            index = 0;
        } else if (index >= array.length) {
            index = array.length - 1;
        }
        return (T) Array.get(array, index);
    }

    /**
     * 获取指定位置的元素
     *
     * @param array   数组
     * @param indexes 索引
     * @param <T>     数组元素类型
     * @return 指定位置的元素
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] get(Object[] array, int... indexes) {
        if (null == array) {
            return null;
        }
        final T[] result = (T[]) Array.newInstance(array.getClass().getComponentType(), indexes.length);
        for (int i = 0; i < indexes.length; i++) {
            result[i] = get(array, indexes[i]);
        }
        return result;
    }

    /**
     * 移除指定位置元素
     *
     * @param array 数组
     * @param index 索引
     * @param <T>   数组类型
     * @return 移除后的数组
     * @see #remove(Object, int)
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T[] remove(T[] array, int index) {
        return (T[]) remove((Object) array, index);
    }

    /**
     * 移除指定位置元素
     *
     * @param array 数组
     * @param index 索引
     * @return 移除后的数组
     */
    @SuppressWarnings("SuspiciousSystemArraycopy")
    public static Object remove(Object array, int index) {
        if (!isArray(array)) {
            return array;
        }
        int len = Array.getLength(array);
        if (index < 0 || index >= len) {
            return array;
        }
        final Object result = Array.newInstance(array.getClass().getComponentType(), len - 1);
        System.arraycopy(array, 0, result, 0, index);
        if (index < len - 1) {
            // 后半部分
            System.arraycopy(array, index + 1, result, index, len - index - 1);
        }
        return result;
    }

    /**
     * 移除指定元素，只会移除第一个元素
     *
     * @param array   数组对象
     * @param element 要移除的元素
     * @param <T>     数组元素类型
     * @return 去掉指定元素后的新数组或原数组
     */
    public static <T> T[] removeEl(T[] array, T element) {
        return remove(array, arrayIndexOf(array, element));
    }

    /**
     * 除去重复元素
     *
     * @param array 数组
     * @param <T>   数组类型
     * @return 去重后的数组
     */
    public static <T> T[] distinct(T[] array) {
        if (isEmpty(array)) {
            return array;
        }

        final Set<T> set = new LinkedHashSet<>(array.length, 1);
        Collections.addAll(set, array);
        return set.toArray(newArray(array.getClass().getComponentType(), 0));
    }

    /**
     * 新建一个空数组
     *
     * @param <T>           数组元素类型
     * @param componentType 元素类型
     * @param newSize       大小
     * @return 空数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<?> componentType, int newSize) {
        return (T[]) Array.newInstance(componentType, newSize);
    }

    /**
     * 新建一个数组
     *
     * @param col           集合
     * @param componentType 数组类型
     * @param <T>           元素类型
     * @return 数组
     * @since 1.0.2
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Collection<? extends T> col, Class<T> componentType) {
        return col.toArray((T[]) Array.newInstance(componentType, 0));
    }

    /**
     * 两下标位置互换
     *
     * @param array 数组
     * @param i     index1
     * @param j     index2
     * @since 1.0.2
     */
    public static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /**
     * 转string
     *
     * @param value 数组或者集合
     * @return 字符串
     * @since 1.0.2
     */
    public static String toString(Object value) {
        if (null == value) {
            return null;
        }
        if (value instanceof byte[]) {
            return Arrays.toString((byte[]) value);
        } else if (value instanceof short[]) {
            return Arrays.toString((short[]) value);
        } else if (value instanceof int[]) {
            return Arrays.toString((int[]) value);
        } else if (value instanceof char[]) {
            return Arrays.toString((char[]) value);
        } else if (value instanceof long[]) {
            return Arrays.toString((long[]) value);
        } else if (value instanceof boolean[]) {
            return Arrays.toString((boolean[]) value);
        } else if (value instanceof float[]) {
            return Arrays.toString((float[]) value);
        } else if (value instanceof double[]) {
            return Arrays.toString((double[]) value);
        } else if (ArrayUtils.isArray(value)) {
            return Arrays.deepToString((Object[]) value);
        }
        return value.toString();
    }

}
