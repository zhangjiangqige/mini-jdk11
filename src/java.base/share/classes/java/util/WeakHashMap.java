package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.lang.ref.WeakReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@CFComment({ "lock: permits null keys and values" })
@AnnotatedFor({ "lock", "index" })
public class WeakHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {

    public WeakHashMap(@NonNegative int initialCapacity, float loadFactor) {
    }

    public WeakHashMap(@NonNegative int initialCapacity) {
    }

    public WeakHashMap() {
    }

    public WeakHashMap(Map<? extends K, ? extends V> m) {
    }

    @Pure
    @NonNegative
    public int size(@GuardSatisfied WeakHashMap<K, V> this);

    @Pure
    public boolean isEmpty(@GuardSatisfied WeakHashMap<K, V> this);

    @Pure
    @Nullable
    public V get(@GuardSatisfied WeakHashMap<K, V> this, @GuardSatisfied @Nullable Object key);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@GuardSatisfied WeakHashMap<K, V> this, @GuardSatisfied @Nullable Object key);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(@GuardSatisfied WeakHashMap<K, V> this, K key, V value);

    public void putAll(@GuardSatisfied WeakHashMap<K, V> this, Map<? extends K, ? extends V> m);

    @Nullable
    public V remove(@GuardSatisfied WeakHashMap<K, V> this, @Nullable Object key);

    public void clear(@GuardSatisfied WeakHashMap<K, V> this);

    @Pure
    public boolean containsValue(@GuardSatisfied WeakHashMap<K, V> this, @GuardSatisfied @Nullable Object value);

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied WeakHashMap<K, V> this);

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied WeakHashMap<K, V> this);

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied WeakHashMap<K, V> this);

    @SuppressWarnings("unchecked")
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action);

    @SuppressWarnings("unchecked")
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);
}
