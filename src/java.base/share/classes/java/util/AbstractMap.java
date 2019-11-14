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
import java.util.Map.Entry;

@CFComment("lock: Subclasses of this interface/class may opt to prohibit null elements")
@AnnotatedFor({ "lock", "nullness", "index" })
public abstract class AbstractMap<K, V> implements Map<K, V> {

    protected AbstractMap() {
    }

    @Pure
    @NonNegative
    public int size(@GuardSatisfied AbstractMap<K, V> this);

    @Pure
    public boolean isEmpty(@GuardSatisfied AbstractMap<K, V> this);

    @Pure
    public boolean containsValue(@GuardSatisfied AbstractMap<K, V> this, @GuardSatisfied @Nullable Object value);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@GuardSatisfied AbstractMap<K, V> this, @GuardSatisfied @Nullable Object key);

    @Pure
    @Nullable
    public V get(@GuardSatisfied AbstractMap<K, V> this, @GuardSatisfied @Nullable Object key);

    @ReleasesNoLocks
    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(@GuardSatisfied AbstractMap<K, V> this, K key, V value);

    @Nullable
    public V remove(@GuardSatisfied AbstractMap<K, V> this, @Nullable Object key);

    public void putAll(@GuardSatisfied AbstractMap<K, V> this, Map<? extends K, ? extends V> m);

    public void clear(@GuardSatisfied AbstractMap<K, V> this);

    transient Set<K> keySet;

    transient Collection<V> values;

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied AbstractMap<K, V> this);

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied AbstractMap<K, V> this);

    @SideEffectFree
    public abstract Set<Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied AbstractMap<K, V> this);

    @Pure
    public boolean equals(@GuardSatisfied AbstractMap<K, V> this, @GuardSatisfied @Nullable Object o);

    @Pure
    public int hashCode(@GuardSatisfied AbstractMap<K, V> this);

    @SideEffectFree
    public String toString(@GuardSatisfied AbstractMap<K, V> this);

    protected Object clone() throws CloneNotSupportedException;

    private static boolean eq(Object o1, Object o2);

    public static class SimpleEntry<K, V> implements Entry<K, V>, java.io.Serializable {

        private static final long serialVersionUID = -8499721149061103585L;

        private final K key;

        private V value;

        public SimpleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public SimpleEntry(Entry<? extends K, ? extends V> entry) {
            this.key = entry.getKey();
            this.value = entry.getValue();
        }

        @Pure
        public K getKey(AbstractMap.@GuardSatisfied SimpleEntry<K, V> this);

        @Pure
        public V getValue(AbstractMap.@GuardSatisfied SimpleEntry<K, V> this);

        public V setValue(AbstractMap.@GuardSatisfied SimpleEntry<K, V> this, V value);

        @Pure
        public boolean equals(AbstractMap.@GuardSatisfied SimpleEntry<K, V> this, @GuardSatisfied @Nullable Object o);

        @Pure
        public int hashCode(AbstractMap.@GuardSatisfied SimpleEntry<K, V> this);

        @SideEffectFree
        public String toString(AbstractMap.@GuardSatisfied SimpleEntry<K, V> this);
    }

    public static class SimpleImmutableEntry<K, V> implements Entry<K, V>, java.io.Serializable {

        private static final long serialVersionUID = 7138329143949025153L;

        private final K key;

        private final V value;

        public SimpleImmutableEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public SimpleImmutableEntry(Entry<? extends K, ? extends V> entry) {
            this.key = entry.getKey();
            this.value = entry.getValue();
        }

        @Pure
        public K getKey(AbstractMap.@GuardSatisfied SimpleImmutableEntry<K, V> this);

        @Pure
        public V getValue(AbstractMap.@GuardSatisfied SimpleImmutableEntry<K, V> this);

        public V setValue(AbstractMap.@GuardSatisfied SimpleImmutableEntry<K, V> this, V value);

        @Pure
        public boolean equals(AbstractMap.@GuardSatisfied SimpleImmutableEntry<K, V> this, @GuardSatisfied @Nullable Object o);

        @Pure
        public int hashCode(AbstractMap.@GuardSatisfied SimpleImmutableEntry<K, V> this);

        @SideEffectFree
        public String toString(AbstractMap.@GuardSatisfied SimpleImmutableEntry<K, V> this);
    }
}
