package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public abstract class Dictionary<K, V> {

    public Dictionary() {
    }

    @Pure
    @NonNegative
    public abstract int size(@GuardSatisfied Dictionary<K, V> this);

    @Pure
    public abstract boolean isEmpty(@GuardSatisfied Dictionary<K, V> this);

    public abstract Enumeration<K> keys();

    public abstract Enumeration<V> elements();

    @Pure
    @Nullable
    public abstract V get(@GuardSatisfied Dictionary<K, V> this, @Nullable Object key);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public abstract V put(@GuardSatisfied Dictionary<K, V> this, K key, V value);

    @Nullable
    public abstract V remove(@GuardSatisfied Dictionary<K, V> this, Object key);
}
