package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.BiFunction;
import jdk.internal.misc.SharedSecrets;

@CFComment({ "lock: This collection can only contain nonnull values" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class Hashtable<K extends @NonNull Object, V extends @NonNull Object> extends Dictionary<K, V> implements Map<K, V>, Cloneable, java.io.Serializable {

    public Hashtable(@NonNegative int initialCapacity, float loadFactor) {
    }

    public Hashtable(@NonNegative int initialCapacity) {
    }

    public Hashtable() {
    }

    public Hashtable(Map<? extends K, ? extends V> t) {
    }

    @Pure
    @NonNegative
    public synchronized int size(@GuardSatisfied Hashtable<K, V> this);

    @Pure
    public synchronized boolean isEmpty(@GuardSatisfied Hashtable<K, V> this);

    public synchronized Enumeration<@KeyFor({ "this" }) K> keys();

    public synchronized Enumeration<V> elements();

    @Pure
    public synchronized boolean contains(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied Object value);

    @Pure
    public boolean containsValue(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @Nullable Object value);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public synchronized boolean containsKey(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @Nullable Object key);

    @Pure
    @SuppressWarnings("unchecked")
    @Nullable
    public synchronized V get(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied Object key);

    @SuppressWarnings("unchecked")
    protected void rehash();

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public synchronized V put(@GuardSatisfied Hashtable<K, V> this, K key, V value);

    @Nullable
    public synchronized V remove(@GuardSatisfied Hashtable<K, V> this, Object key);

    public synchronized void putAll(@GuardSatisfied Hashtable<K, V> this, Map<? extends K, ? extends V> t);

    public synchronized void clear(@GuardSatisfied Hashtable<K, V> this);

    @SideEffectFree
    public synchronized Object clone(@GuardSatisfied Hashtable<K, V> this);

    public synchronized String toString(@GuardSatisfied Hashtable<K, V> this);

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied Hashtable<K, V> this);

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied Hashtable<K, V> this);

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied Hashtable<K, V> this);

    @Pure
    public synchronized boolean equals(@GuardSatisfied Hashtable<K, V> this, @GuardSatisfied @Nullable Object o);

    @Pure
    public synchronized int hashCode(@GuardSatisfied Hashtable<K, V> this);

    @Override
    public synchronized V getOrDefault(Object key, V defaultValue);

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void forEach(BiConsumer<? super K, ? super V> action);

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Override
    public synchronized V putIfAbsent(K key, V value);

    @Override
    public synchronized boolean remove(Object key, Object value);

    @Override
    public synchronized boolean replace(K key, V oldValue, V newValue);

    @Override
    public synchronized V replace(K key, V value);

    @Override
    public synchronized V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

    @Override
    public synchronized V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    @Override
    public synchronized V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    @Override
    public synchronized V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);
}
