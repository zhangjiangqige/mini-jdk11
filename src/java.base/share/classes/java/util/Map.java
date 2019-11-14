package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import org.checkerframework.framework.qual.Covariant;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.io.Serializable;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
@Covariant({ 0 })
public interface Map<K extends @Nullable Object, V extends @Nullable Object> {

    @NonNegative
    @Pure
    int size(@GuardSatisfied Map<K, V> this);

    @Pure
    boolean isEmpty(@GuardSatisfied Map<K, V> this);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    boolean containsKey(@GuardSatisfied Map<K, V> this, @GuardSatisfied @Nullable Object key);

    @Pure
    boolean containsValue(@GuardSatisfied Map<K, V> this, @GuardSatisfied @Nullable Object value);

    @CFComment({ "lock: The parameter is not nullable, because implementations of Map.get and", "Map.put are specifically permitted to throw NullPointerException if", "any of the arguments is a null).  And some implementations do not", "permit nulls (sorted queues PriorityQueue, Hashtable, most concurrent", "collections).  Some other implementation do accept nulls and are so", "annotatied (see ArrayList, LinkedList, HashMap)." })
    @Pure
    @Nullable
    V get(@GuardSatisfied Map<K, V> this, @GuardSatisfied @Nullable Object key);

    @ReleasesNoLocks
    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    V put(@GuardSatisfied Map<K, V> this, K key, V value);

    @Nullable
    V remove(@GuardSatisfied Map<K, V> this, @Nullable Object key);

    void putAll(@GuardSatisfied Map<K, V> this, Map<? extends K, ? extends V> m);

    void clear(@GuardSatisfied Map<K, V> this);

    @SideEffectFree
    Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied Map<K, V> this);

    @SideEffectFree
    Collection<V> values(@GuardSatisfied Map<K, V> this);

    @SideEffectFree
    Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied Map<K, V> this);

    @Covariant({ 0 })
    interface Entry<K extends @Nullable Object, V extends @Nullable Object> {

        @Pure
        K getKey(Map.@GuardSatisfied Entry<K, V> this);

        @Pure
        V getValue(Map.@GuardSatisfied Entry<K, V> this);

        V setValue(Map.@GuardSatisfied Entry<K, V> this, V value);

        @Pure
        boolean equals(Map.@GuardSatisfied Entry<K, V> this, @GuardSatisfied @Nullable Object o);

        @Pure
        int hashCode(Map.@GuardSatisfied Entry<K, V> this);

        public static <K extends Comparable<? super K>, V> Comparator<Map.Entry<K, V>> comparingByKey();

        public static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K, V>> comparingByValue();

        public static <K, V> Comparator<Map.Entry<K, V>> comparingByKey(Comparator<? super K> cmp);

        public static <K, V> Comparator<Map.Entry<K, V>> comparingByValue(Comparator<? super V> cmp);
    }

    boolean equals(@GuardSatisfied Map<K, V> this, @GuardSatisfied Object o);

    int hashCode(@GuardSatisfied Map<K, V> this);

    default V getOrDefault(Object key, V defaultValue);

    default void forEach(BiConsumer<? super K, ? super V> action);

    default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    default V putIfAbsent(K key, V value);

    default boolean remove(Object key, Object value);

    default boolean replace(K key, V oldValue, V newValue);

    default V replace(K key, V value);

    default V computeIfAbsent(K key, Function<? super K, ? extends @Nullable V> mappingFunction);

    default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @Nullable V> remappingFunction);

    default V compute(K key, BiFunction<? super K, ? super @Nullable V, ? extends @Nullable V> remappingFunction);

    default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends @Nullable V> remappingFunction);

    static <K, V> Map<K, V> of();

    static <K, V> Map<K, V> of(K k1, V v1);

    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2);

    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3);

    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4);

    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5);

    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6);

    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7);

    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8);

    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9);

    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10);

    @SafeVarargs
    @SuppressWarnings("varargs")
    static <K, V> Map<K, V> ofEntries(Entry<? extends K, ? extends V>... entries);

    static <K, V> Entry<K, V> entry(K k, V v);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    static <K, V> Map<K, V> copyOf(Map<? extends K, ? extends V> map);
}
