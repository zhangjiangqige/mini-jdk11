package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.misc.SharedSecrets;

@AnnotatedFor({ "nullness", "index" })
public class EnumMap<K extends Enum<K>, V> extends AbstractMap<K, V> implements java.io.Serializable, Cloneable {

    public EnumMap(Class<K> keyType) {
    }

    public EnumMap(EnumMap<K, ? extends V> m) {
    }

    public EnumMap(Map<K, ? extends V> m) {
    }

    @Pure
    @NonNegative
    public int size();

    @Pure
    public boolean containsValue(@Nullable Object value);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@Nullable Object key);

    @Nullable
    public V get(@Nullable Object key);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(K key, V value);

    @Nullable
    public V remove(@Nullable Object key);

    @CFComment({ "nullness: Variables keyUniverse", "and vals are private class members for EnumMap and are absent in AbstractMap." })
    @SuppressWarnings({ "nullness:contracts.precondition.override.invalid" })
    @RequiresNonNull({ "keyUniverse", "vals" })
    public void putAll(@UnknownInitialization EnumMap<K, V> this, Map<? extends K, ? extends V> m);

    public void clear();

    public Set<K> keySet();

    public Collection<V> values();

    public Set<Map.Entry<K, V>> entrySet();

    public boolean equals(@Nullable Object o);

    public int hashCode();

    @SuppressWarnings("unchecked")
    public EnumMap<K, V> clone();
}
