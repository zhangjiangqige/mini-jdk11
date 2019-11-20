package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.io.IOException;

@AnnotatedFor({ "lock", "nullness", "index" })
public class LinkedHashMap<K, V> extends HashMap<K, V> implements Map<K, V> {

    public LinkedHashMap(@NonNegative int initialCapacity, float loadFactor) {
    }

    public LinkedHashMap(@NonNegative int initialCapacity) {
    }

    public LinkedHashMap() {
    }

    public LinkedHashMap(Map<? extends K, ? extends V> m) {
    }

    public LinkedHashMap(@NonNegative int initialCapacity, float loadFactor, boolean accessOrder) {
    }

    @Pure
    public boolean containsValue(@GuardSatisfied LinkedHashMap<K, V> this, @GuardSatisfied @Nullable Object value);

    @Pure
    @Nullable
    public V get(@GuardSatisfied LinkedHashMap<K, V> this, @GuardSatisfied @Nullable Object key);

    public V getOrDefault(Object key, V defaultValue);

    public void clear(@GuardSatisfied LinkedHashMap<K, V> this);

    protected boolean removeEldestEntry(@GuardSatisfied LinkedHashMap<K, V> this, Map.Entry<K, V> eldest);

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet();

    public Collection<V> values();

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied LinkedHashMap<K, V> this);

    public void forEach(BiConsumer<? super K, ? super V> action);

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);
}
