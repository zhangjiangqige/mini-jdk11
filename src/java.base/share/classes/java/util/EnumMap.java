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

    private final Class<K> keyType;

    private transient K[] keyUniverse;

    @Nullable
    private transient Object[] vals;

    private transient int size = 0;

    private static final Object NULL = new Object() {

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "java.util.EnumMap.NULL";
        }
    };

    private Object maskNull(@Nullable Object value);

    @SuppressWarnings("unchecked")
    @Nullable
    private V unmaskNull(@Nullable Object value);

    public EnumMap(Class<K> keyType) {
        this.keyType = keyType;
        keyUniverse = getKeyUniverse(keyType);
        vals = new Object[keyUniverse.length];
    }

    public EnumMap(EnumMap<K, ? extends V> m) {
        keyType = m.keyType;
        keyUniverse = m.keyUniverse;
        vals = m.vals.clone();
        size = m.size;
    }

    public EnumMap(Map<K, ? extends V> m) {
        if (m instanceof EnumMap) {
            EnumMap<K, ? extends V> em = (EnumMap<K, ? extends V>) m;
            keyType = em.keyType;
            keyUniverse = em.keyUniverse;
            vals = em.vals.clone();
            size = em.size;
        } else {
            if (m.isEmpty())
                throw new IllegalArgumentException("Specified map is empty");
            keyType = m.keySet().iterator().next().getDeclaringClass();
            keyUniverse = getKeyUniverse(keyType);
            vals = new Object[keyUniverse.length];
            putAll(m);
        }
    }

    @Pure
    @NonNegative
    public int size();

    @Pure
    public boolean containsValue(@Nullable Object value);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@Nullable Object key);

    private boolean containsMapping(@Nullable Object key, @Nullable Object value);

    @Nullable
    public V get(@Nullable Object key);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(K key, V value);

    @Nullable
    public V remove(@Nullable Object key);

    private boolean removeMapping(@Nullable Object key, @Nullable Object value);

    @EnsuresNonNullIf(expression = { "#1" }, result = true)
    private boolean isValidKey(@Nullable Object key);

    @CFComment({ "nullness: Variables keyUniverse", "and vals are private class members for EnumMap and are absent in AbstractMap." })
    @SuppressWarnings({ "nullness:contracts.precondition.override.invalid" })
    @RequiresNonNull({ "keyUniverse", "vals" })
    public void putAll(@UnknownInitialization EnumMap<K, V> this, Map<? extends K, ? extends V> m);

    public void clear();

    @Nullable
    private transient Set<Map.Entry<K, V>> entrySet;

    public Set<K> keySet();

    private class KeySet extends AbstractSet<K> {

        @SideEffectFree
        public Iterator<K> iterator();

        @Pure
        @NonNegative
        public int size();

        @Pure
        public boolean contains(@Nullable Object o);

        public boolean remove(@Nullable Object o);

        public void clear();
    }

    public Collection<V> values();

    private class Values extends AbstractCollection<V> {

        @SideEffectFree
        public Iterator<V> iterator();

        @Pure
        @NonNegative
        public int size();

        @Pure
        public boolean contains(@Nullable Object o);

        public boolean remove(@Nullable Object o);

        public void clear();
    }

    public Set<Map.Entry<K, V>> entrySet();

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @SideEffectFree
        public Iterator<Map.Entry<K, V>> iterator();

        @Pure
        public boolean contains(@Nullable Object o);

        public boolean remove(@Nullable Object o);

        @Pure
        @NonNegative
        public int size();

        public void clear();

        @SideEffectFree
        public Object[] toArray();

        @CFComment({ "nullness: 'a' is known to be of array class type", "Annotation for toArray are technically incorrect. Refer to note on toArray in Collection.java" })
        @SideEffectFree
        @SuppressWarnings({ "unchecked", "nullness:argument.type.incompatible", "nullness:override.param.invalid" })
        @Nullable
        public <T> T[] toArray(@Nullable T[] a);

        @CFComment({ "nullness: Value returned by unmaskNull", "will be of type V (not @Nullable V) for mapped value" })
        @SuppressWarnings({ "nullness:argument.type.incompatible" })
        private Object[] fillEntryArray(Object[] a);
    }

    private abstract class EnumMapIterator<T> implements Iterator<T> {

        int index = 0;

        int lastReturnedIndex = -1;

        public boolean hasNext();

        public void remove();

        private void checkLastReturnedIndex();
    }

    private class KeyIterator extends EnumMapIterator<K> {

        public K next();
    }

    private class ValueIterator extends EnumMapIterator<V> {

        @CFComment({ "nullness: Value returned by unmaskNull", "will be of type V (not @Nullable V) for mapped value" })
        @SuppressWarnings({ "nullness:return.type.incompatible" })
        public V next();
    }

    private class EntryIterator extends EnumMapIterator<Map.Entry<K, V>> {

        private Entry lastReturnedEntry;

        public Map.Entry<K, V> next();

        public void remove();

        private class Entry implements Map.Entry<K, V> {

            private int index;

            private Entry(int index) {
                this.index = index;
            }

            public K getKey();

            @CFComment({ "nullness: Value returned by unmaskNull", "will be of type V (not @Nullable V) for mapped value" })
            @SuppressWarnings("nullness:return.type.incompatible")
            public V getValue();

            @CFComment({ "nullness: Value returned by unmaskNull", "will be of type V (not @Nullable V) for mapped value" })
            @SuppressWarnings("nullness:return.type.incompatible")
            public V setValue(V value);

            public boolean equals(Object o);

            public int hashCode();

            public String toString();

            private void checkIndexForEntryUse();
        }
    }

    public boolean equals(@Nullable Object o);

    private boolean equals(EnumMap<?, ?> em);

    public int hashCode();

    @CFComment({ "nullness: Private method; Called only for indices with mapped value" })
    @SuppressWarnings({ "nullness:dereference.of.nullable" })
    private int entryHashCode(int index);

    @SuppressWarnings("unchecked")
    public EnumMap<K, V> clone();

    private void typeCheck(K key);

    private static <K extends Enum<K>> K[] getKeyUniverse(Class<K> keyType);

    private static final long serialVersionUID = 458661240069192865L;

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;
}
