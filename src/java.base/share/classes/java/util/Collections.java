package java.util;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.common.value.qual.MinLen;
import org.checkerframework.common.value.qual.ArrayLen;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@AnnotatedFor({ "lock", "nullness", "index" })
public class Collections {

    private Collections() {
    }

    private static final int BINARYSEARCH_THRESHOLD = 5000;

    private static final int REVERSE_THRESHOLD = 18;

    private static final int SHUFFLE_THRESHOLD = 5;

    private static final int FILL_THRESHOLD = 25;

    private static final int ROTATE_THRESHOLD = 100;

    private static final int COPY_THRESHOLD = 10;

    private static final int REPLACEALL_THRESHOLD = 11;

    private static final int INDEXOFSUBLIST_THRESHOLD = 35;

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void sort(List<T> list);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> void sort(List<T> list, @Nullable Comparator<? super T> c);

    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key);

    private static <T> int indexedBinarySearch(List<? extends Comparable<? super T>> list, T key);

    private static <T> int iteratorBinarySearch(List<? extends Comparable<? super T>> list, T key);

    private static <T> T get(ListIterator<? extends T> i, int index);

    @SuppressWarnings("unchecked")
    public static <T> int binarySearch(List<? extends T> list, T key, @Nullable Comparator<? super T> c);

    private static <T> int indexedBinarySearch(List<? extends T> l, T key, Comparator<? super T> c);

    private static <T> int iteratorBinarySearch(List<? extends T> l, T key, Comparator<? super T> c);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void reverse(@GuardSatisfied List<?> list);

    public static void shuffle(@GuardSatisfied List<?> list);

    private static Random r;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void shuffle(@GuardSatisfied List<?> list, Random rnd);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void swap(@GuardSatisfied List<?> list, int i, int j);

    private static void swap(Object[] arr, int i, int j);

    public static <T> void fill(@GuardSatisfied List<? super T> list, T obj);

    public static <T> void copy(List<? super T> dest, List<? extends T> src);

    public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T min(Collection<? extends T> coll, @Nullable Comparator<? super T> comp);

    public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T max(Collection<? extends T> coll, @Nullable Comparator<? super T> comp);

    public static void rotate(@GuardSatisfied List<?> list, int distance);

    private static <T> void rotate1(List<T> list, int distance);

    private static void rotate2(List<?> list, int distance);

    public static <T> boolean replaceAll(List<T> list, @Nullable T oldVal, T newVal);

    @Pure
    @GTENegativeOne
    public static int indexOfSubList(@GuardSatisfied List<?> source, @GuardSatisfied List<?> target);

    @Pure
    @GTENegativeOne
    public static int lastIndexOfSubList(@GuardSatisfied List<?> source, @GuardSatisfied List<?> target);

    public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c);

    static class UnmodifiableCollection<E> implements Collection<E>, Serializable {

        private static final long serialVersionUID = 1820017752578914078L;

        final Collection<? extends E> c;

        UnmodifiableCollection(Collection<? extends E> c) {
            if (c == null)
                throw new NullPointerException();
            this.c = c;
        }

        @NonNegative
        public int size();

        public boolean isEmpty();

        public boolean contains(Object o);

        @SideEffectFree
        @PolyNull
        public Object[] toArray(Collections.UnmodifiableCollection<@PolyNull E> this);

        @SideEffectFree
        public <T> T[] toArray(T[] a);

        public <T> T[] toArray(IntFunction<T[]> f);

        public String toString();

        @SideEffectFree
        public Iterator<E> iterator();

        public boolean add(E e);

        public boolean remove(Object o);

        public boolean containsAll(Collection<?> coll);

        public boolean addAll(Collection<? extends E> coll);

        public boolean removeAll(Collection<?> coll);

        public boolean retainAll(Collection<?> coll);

        public void clear();

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @SuppressWarnings("unchecked")
        @Override
        public Spliterator<E> spliterator();

        @SuppressWarnings("unchecked")
        @Override
        public Stream<E> stream();

        @SuppressWarnings("unchecked")
        @Override
        public Stream<E> parallelStream();
    }

    public static <T> Set<T> unmodifiableSet(Set<? extends T> s);

    static class UnmodifiableSet<E> extends UnmodifiableCollection<E> implements Set<E>, Serializable {

        private static final long serialVersionUID = -9215047833775013803L;

        UnmodifiableSet(Set<? extends E> s) {
            super(s);
        }

        public boolean equals(Object o);

        public int hashCode();
    }

    public static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> s);

    static class UnmodifiableSortedSet<E> extends UnmodifiableSet<E> implements SortedSet<E>, Serializable {

        private static final long serialVersionUID = -4929149591599911165L;

        private final SortedSet<E> ss;

        UnmodifiableSortedSet(SortedSet<E> s) {
            super(s);
            ss = s;
        }

        public Comparator<? super E> comparator();

        public SortedSet<E> subSet(E fromElement, E toElement);

        public SortedSet<E> headSet(E toElement);

        public SortedSet<E> tailSet(E fromElement);

        public E first();

        public E last();
    }

    public static <T> NavigableSet<T> unmodifiableNavigableSet(NavigableSet<T> s);

    static class UnmodifiableNavigableSet<E> extends UnmodifiableSortedSet<E> implements NavigableSet<E>, Serializable {

        private static final long serialVersionUID = -6027448201786391929L;

        private static class EmptyNavigableSet<E> extends UnmodifiableNavigableSet<E> implements Serializable {

            private static final long serialVersionUID = -6291252904449939134L;

            public EmptyNavigableSet() {
                super(new TreeSet<>());
            }

            private Object readResolve();
        }

        @SuppressWarnings("rawtypes")
        private static final NavigableSet<?> EMPTY_NAVIGABLE_SET = new EmptyNavigableSet<>();

        private final NavigableSet<E> ns;

        UnmodifiableNavigableSet(NavigableSet<E> s) {
            super(s);
            ns = s;
        }

        public E lower(E e);

        public E floor(E e);

        public E ceiling(E e);

        public E higher(E e);

        public E pollFirst();

        public E pollLast();

        public NavigableSet<E> descendingSet();

        public Iterator<E> descendingIterator();

        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

        public NavigableSet<E> headSet(E toElement, boolean inclusive);

        public NavigableSet<E> tailSet(E fromElement, boolean inclusive);
    }

    public static <T> List<T> unmodifiableList(List<? extends T> list);

    static class UnmodifiableList<E> extends UnmodifiableCollection<E> implements List<E> {

        private static final long serialVersionUID = -283967356065247728L;

        final List<? extends E> list;

        UnmodifiableList(List<? extends E> list) {
            super(list);
            this.list = list;
        }

        public boolean equals(Object o);

        public int hashCode();

        public E get(int index);

        public E set(int index, E element);

        public void add(int index, E element);

        public E remove(int index);

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        public boolean addAll(int index, Collection<? extends E> c);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);

        public ListIterator<E> listIterator();

        public ListIterator<E> listIterator(final int index);

        public List<E> subList(int fromIndex, int toIndex);

        private Object readResolve();
    }

    static class UnmodifiableRandomAccessList<E> extends UnmodifiableList<E> implements RandomAccess {

        UnmodifiableRandomAccessList(List<? extends E> list) {
            super(list);
        }

        public List<E> subList(int fromIndex, int toIndex);

        private static final long serialVersionUID = -2542308836966382001L;

        private Object writeReplace();
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m);

    private static class UnmodifiableMap<K, V> implements Map<K, V>, Serializable {

        private static final long serialVersionUID = -1034234728574286014L;

        private final Map<? extends K, ? extends V> m;

        UnmodifiableMap(Map<? extends K, ? extends V> m) {
            if (m == null)
                throw new NullPointerException();
            this.m = m;
        }

        @NonNegative
        public int size();

        public boolean isEmpty();

        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public boolean containsKey(Object key);

        public boolean containsValue(Object val);

        public V get(Object key);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        public V put(K key, V value);

        public V remove(Object key);

        public void putAll(Map<? extends K, ? extends V> m);

        public void clear();

        private transient Set<K> keySet;

        private transient Set<Map.Entry<K, V>> entrySet;

        private transient Collection<V> values;

        public Set<K> keySet();

        public Set<Map.Entry<K, V>> entrySet();

        public Collection<V> values();

        public boolean equals(Object o);

        public int hashCode();

        public String toString();

        @Override
        @SuppressWarnings("unchecked")
        public V getOrDefault(Object k, V defaultValue);

        @Override
        public void forEach(BiConsumer<? super K, ? super V> action);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public boolean remove(Object key, Object value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public V replace(K key, V value);

        @Override
        public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

        @Override
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        @Override
        public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        @Override
        public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

        static class UnmodifiableEntrySet<K, V> extends UnmodifiableSet<Map.Entry<K, V>> {

            private static final long serialVersionUID = 7854390611657943733L;

            @SuppressWarnings({ "unchecked", "rawtypes" })
            UnmodifiableEntrySet(Set<? extends Map.Entry<? extends K, ? extends V>> s) {
                super((Set) s);
            }

            static <K, V> Consumer<Map.Entry<? extends K, ? extends V>> entryConsumer(Consumer<? super Entry<K, V>> action);

            public void forEach(Consumer<? super Entry<K, V>> action);

            static final class UnmodifiableEntrySetSpliterator<K, V> implements Spliterator<Entry<K, V>> {

                final Spliterator<Map.Entry<K, V>> s;

                UnmodifiableEntrySetSpliterator(Spliterator<Entry<K, V>> s) {
                    this.s = s;
                }

                @Override
                public boolean tryAdvance(Consumer<? super Entry<K, V>> action);

                @Override
                public void forEachRemaining(Consumer<? super Entry<K, V>> action);

                @Override
                public Spliterator<Entry<K, V>> trySplit();

                @Override
                public long estimateSize();

                @Override
                public long getExactSizeIfKnown();

                @Override
                public int characteristics();

                @Override
                public boolean hasCharacteristics(int characteristics);

                @Override
                public Comparator<? super Entry<K, V>> getComparator();
            }

            @SuppressWarnings("unchecked")
            public Spliterator<Entry<K, V>> spliterator();

            @Override
            public Stream<Entry<K, V>> stream();

            @Override
            public Stream<Entry<K, V>> parallelStream();

            public Iterator<Map.Entry<K, V>> iterator();

            @SuppressWarnings("unchecked")
            public Object[] toArray();

            @SuppressWarnings("unchecked")
            public <T> T[] toArray(T[] a);

            public boolean contains(Object o);

            public boolean containsAll(Collection<?> coll);

            public boolean equals(Object o);

            private static class UnmodifiableEntry<K, V> implements Map.Entry<K, V> {

                private Map.Entry<? extends K, ? extends V> e;

                UnmodifiableEntry(Map.Entry<? extends K, ? extends V> e) {
                    this.e = Objects.requireNonNull(e);
                }

                public K getKey();

                public V getValue();

                public V setValue(V value);

                public int hashCode();

                public boolean equals(Object o);

                public String toString();
            }
        }
    }

    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> m);

    static class UnmodifiableSortedMap<K, V> extends UnmodifiableMap<K, V> implements SortedMap<K, V>, Serializable {

        private static final long serialVersionUID = -8806743815996713206L;

        private final SortedMap<K, ? extends V> sm;

        UnmodifiableSortedMap(SortedMap<K, ? extends V> m) {
            super(m);
            sm = m;
        }

        public Comparator<? super K> comparator();

        public SortedMap<K, V> subMap(K fromKey, K toKey);

        public SortedMap<K, V> headMap(K toKey);

        public SortedMap<K, V> tailMap(K fromKey);

        public K firstKey();

        public K lastKey();
    }

    public static <K, V> NavigableMap<K, V> unmodifiableNavigableMap(NavigableMap<K, ? extends V> m);

    static class UnmodifiableNavigableMap<K, V> extends UnmodifiableSortedMap<K, V> implements NavigableMap<K, V>, Serializable {

        private static final long serialVersionUID = -4858195264774772197L;

        private static class EmptyNavigableMap<K, V> extends UnmodifiableNavigableMap<K, V> implements Serializable {

            private static final long serialVersionUID = -2239321462712562324L;

            EmptyNavigableMap() {
                super(new TreeMap<>());
            }

            @Override
            public NavigableSet<K> navigableKeySet();

            private Object readResolve();
        }

        private static final EmptyNavigableMap<?, ?> EMPTY_NAVIGABLE_MAP = new EmptyNavigableMap<>();

        private final NavigableMap<K, ? extends V> nm;

        UnmodifiableNavigableMap(NavigableMap<K, ? extends V> m) {
            super(m);
            nm = m;
        }

        public K lowerKey(K key);

        public K floorKey(K key);

        public K ceilingKey(K key);

        public K higherKey(K key);

        @SuppressWarnings("unchecked")
        public Entry<K, V> lowerEntry(K key);

        @SuppressWarnings("unchecked")
        public Entry<K, V> floorEntry(K key);

        @SuppressWarnings("unchecked")
        public Entry<K, V> ceilingEntry(K key);

        @SuppressWarnings("unchecked")
        public Entry<K, V> higherEntry(K key);

        @SuppressWarnings("unchecked")
        public Entry<K, V> firstEntry();

        @SuppressWarnings("unchecked")
        public Entry<K, V> lastEntry();

        public Entry<K, V> pollFirstEntry();

        public Entry<K, V> pollLastEntry();

        public NavigableMap<K, V> descendingMap();

        public NavigableSet<K> navigableKeySet();

        public NavigableSet<K> descendingKeySet();

        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        public NavigableMap<K, V> headMap(K toKey, boolean inclusive);

        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);
    }

    public static <T> Collection<T> synchronizedCollection(Collection<T> c);

    static <T> Collection<T> synchronizedCollection(Collection<T> c, Object mutex);

    static class SynchronizedCollection<E> implements Collection<E>, Serializable {

        private static final long serialVersionUID = 3053995032091335093L;

        final Collection<E> c;

        final Object mutex;

        SynchronizedCollection(Collection<E> c) {
            this.c = Objects.requireNonNull(c);
            mutex = this;
        }

        SynchronizedCollection(Collection<E> c, Object mutex) {
            this.c = Objects.requireNonNull(c);
            this.mutex = Objects.requireNonNull(mutex);
        }

        @NonNegative
        public int size();

        public boolean isEmpty();

        public boolean contains(Object o);

        @SideEffectFree
        @PolyNull
        public Object[] toArray(Collections.SynchronizedCollection<@PolyNull E> this);

        @SideEffectFree
        public <T> T[] toArray(T[] a);

        public <T> T[] toArray(IntFunction<T[]> f);

        @SideEffectFree
        public Iterator<E> iterator();

        public boolean add(E e);

        public boolean remove(Object o);

        public boolean containsAll(Collection<?> coll);

        public boolean addAll(Collection<? extends E> coll);

        public boolean removeAll(Collection<?> coll);

        public boolean retainAll(Collection<?> coll);

        public void clear();

        public String toString();

        @Override
        public void forEach(Consumer<? super E> consumer);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public Stream<E> stream();

        @Override
        public Stream<E> parallelStream();

        private void writeObject(ObjectOutputStream s) throws IOException;
    }

    public static <T> Set<T> synchronizedSet(Set<T> s);

    static <T> Set<T> synchronizedSet(Set<T> s, Object mutex);

    static class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {

        private static final long serialVersionUID = 487447009682186044L;

        SynchronizedSet(Set<E> s) {
            super(s);
        }

        SynchronizedSet(Set<E> s, Object mutex) {
            super(s, mutex);
        }

        public boolean equals(Object o);

        public int hashCode();
    }

    public static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s);

    static class SynchronizedSortedSet<E> extends SynchronizedSet<E> implements SortedSet<E> {

        private static final long serialVersionUID = 8695801310862127406L;

        private final SortedSet<E> ss;

        SynchronizedSortedSet(SortedSet<E> s) {
            super(s);
            ss = s;
        }

        SynchronizedSortedSet(SortedSet<E> s, Object mutex) {
            super(s, mutex);
            ss = s;
        }

        public Comparator<? super E> comparator();

        public SortedSet<E> subSet(E fromElement, E toElement);

        public SortedSet<E> headSet(E toElement);

        public SortedSet<E> tailSet(E fromElement);

        public E first();

        public E last();
    }

    public static <T> NavigableSet<T> synchronizedNavigableSet(NavigableSet<T> s);

    static class SynchronizedNavigableSet<E> extends SynchronizedSortedSet<E> implements NavigableSet<E> {

        private static final long serialVersionUID = -5505529816273629798L;

        private final NavigableSet<E> ns;

        SynchronizedNavigableSet(NavigableSet<E> s) {
            super(s);
            ns = s;
        }

        SynchronizedNavigableSet(NavigableSet<E> s, Object mutex) {
            super(s, mutex);
            ns = s;
        }

        public E lower(E e);

        public E floor(E e);

        public E ceiling(E e);

        public E higher(E e);

        public E pollFirst();

        public E pollLast();

        public NavigableSet<E> descendingSet();

        public Iterator<E> descendingIterator();

        public NavigableSet<E> subSet(E fromElement, E toElement);

        public NavigableSet<E> headSet(E toElement);

        public NavigableSet<E> tailSet(E fromElement);

        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

        public NavigableSet<E> headSet(E toElement, boolean inclusive);

        public NavigableSet<E> tailSet(E fromElement, boolean inclusive);
    }

    public static <T> List<T> synchronizedList(List<T> list);

    static <T> List<T> synchronizedList(List<T> list, Object mutex);

    static class SynchronizedList<E> extends SynchronizedCollection<E> implements List<E> {

        private static final long serialVersionUID = -7754090372962971524L;

        final List<E> list;

        SynchronizedList(List<E> list) {
            super(list);
            this.list = list;
        }

        SynchronizedList(List<E> list, Object mutex) {
            super(list, mutex);
            this.list = list;
        }

        public boolean equals(Object o);

        public int hashCode();

        public E get(int index);

        public E set(int index, E element);

        public void add(int index, E element);

        public E remove(int index);

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        public boolean addAll(int index, Collection<? extends E> c);

        public ListIterator<E> listIterator();

        public ListIterator<E> listIterator(int index);

        public List<E> subList(int fromIndex, int toIndex);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);

        private Object readResolve();
    }

    static class SynchronizedRandomAccessList<E> extends SynchronizedList<E> implements RandomAccess {

        SynchronizedRandomAccessList(List<E> list) {
            super(list);
        }

        SynchronizedRandomAccessList(List<E> list, Object mutex) {
            super(list, mutex);
        }

        public List<E> subList(int fromIndex, int toIndex);

        private static final long serialVersionUID = 1530674583602358482L;

        private Object writeReplace();
    }

    public static <K, V> Map<K, V> synchronizedMap(Map<K, V> m);

    private static class SynchronizedMap<K, V> implements Map<K, V>, Serializable {

        private static final long serialVersionUID = 1978198479659022715L;

        private final Map<K, V> m;

        final Object mutex;

        SynchronizedMap(Map<K, V> m) {
            this.m = Objects.requireNonNull(m);
            mutex = this;
        }

        SynchronizedMap(Map<K, V> m, Object mutex) {
            this.m = m;
            this.mutex = mutex;
        }

        @NonNegative
        public int size();

        public boolean isEmpty();

        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public boolean containsKey(Object key);

        public boolean containsValue(Object value);

        public V get(Object key);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        public V put(K key, V value);

        public V remove(Object key);

        public void putAll(Map<? extends K, ? extends V> map);

        public void clear();

        private transient Set<K> keySet;

        private transient Set<Map.Entry<K, V>> entrySet;

        private transient Collection<V> values;

        public Set<K> keySet();

        public Set<Map.Entry<K, V>> entrySet();

        public Collection<V> values();

        public boolean equals(Object o);

        public int hashCode();

        public String toString();

        @Override
        public V getOrDefault(Object k, V defaultValue);

        @Override
        public void forEach(BiConsumer<? super K, ? super V> action);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public boolean remove(Object key, Object value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public V replace(K key, V value);

        @Override
        public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

        @Override
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        @Override
        public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        @Override
        public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

        private void writeObject(ObjectOutputStream s) throws IOException;
    }

    public static <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> m);

    static class SynchronizedSortedMap<K, V> extends SynchronizedMap<K, V> implements SortedMap<K, V> {

        private static final long serialVersionUID = -8798146769416483793L;

        private final SortedMap<K, V> sm;

        SynchronizedSortedMap(SortedMap<K, V> m) {
            super(m);
            sm = m;
        }

        SynchronizedSortedMap(SortedMap<K, V> m, Object mutex) {
            super(m, mutex);
            sm = m;
        }

        public Comparator<? super K> comparator();

        public SortedMap<K, V> subMap(K fromKey, K toKey);

        public SortedMap<K, V> headMap(K toKey);

        public SortedMap<K, V> tailMap(K fromKey);

        public K firstKey();

        public K lastKey();
    }

    public static <K, V> NavigableMap<K, V> synchronizedNavigableMap(NavigableMap<K, V> m);

    static class SynchronizedNavigableMap<K, V> extends SynchronizedSortedMap<K, V> implements NavigableMap<K, V> {

        private static final long serialVersionUID = 699392247599746807L;

        private final NavigableMap<K, V> nm;

        SynchronizedNavigableMap(NavigableMap<K, V> m) {
            super(m);
            nm = m;
        }

        SynchronizedNavigableMap(NavigableMap<K, V> m, Object mutex) {
            super(m, mutex);
            nm = m;
        }

        public Entry<K, V> lowerEntry(K key);

        public K lowerKey(K key);

        public Entry<K, V> floorEntry(K key);

        public K floorKey(K key);

        public Entry<K, V> ceilingEntry(K key);

        public K ceilingKey(K key);

        public Entry<K, V> higherEntry(K key);

        public K higherKey(K key);

        public Entry<K, V> firstEntry();

        public Entry<K, V> lastEntry();

        public Entry<K, V> pollFirstEntry();

        public Entry<K, V> pollLastEntry();

        public NavigableMap<K, V> descendingMap();

        public NavigableSet<K> keySet();

        public NavigableSet<K> navigableKeySet();

        public NavigableSet<K> descendingKeySet();

        public SortedMap<K, V> subMap(K fromKey, K toKey);

        public SortedMap<K, V> headMap(K toKey);

        public SortedMap<K, V> tailMap(K fromKey);

        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        public NavigableMap<K, V> headMap(K toKey, boolean inclusive);

        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);
    }

    public static <E> Collection<E> checkedCollection(Collection<E> c, Class<E> type);

    @SuppressWarnings("unchecked")
    static <T> T[] zeroLengthArray(Class<T> type);

    static class CheckedCollection<E> implements Collection<E>, Serializable {

        private static final long serialVersionUID = 1578914078182001775L;

        final Collection<E> c;

        final Class<E> type;

        @SuppressWarnings("unchecked")
        E typeCheck(Object o);

        private String badElementMsg(Object o);

        CheckedCollection(Collection<E> c, Class<E> type) {
            this.c = Objects.requireNonNull(c, "c");
            this.type = Objects.requireNonNull(type, "type");
        }

        @NonNegative
        public int size();

        public boolean isEmpty();

        public boolean contains(Object o);

        @SideEffectFree
        @PolyNull
        public Object[] toArray(Collections.CheckedCollection<@PolyNull E> this);

        @SideEffectFree
        public <T> T[] toArray(T[] a);

        public <T> T[] toArray(IntFunction<T[]> f);

        public String toString();

        public boolean remove(Object o);

        public void clear();

        public boolean containsAll(Collection<?> coll);

        public boolean removeAll(Collection<?> coll);

        public boolean retainAll(Collection<?> coll);

        @SideEffectFree
        public Iterator<E> iterator();

        public boolean add(E e);

        private E[] zeroLengthElementArray;

        private E[] zeroLengthElementArray();

        @SuppressWarnings("unchecked")
        Collection<E> checkedCopyOf(Collection<? extends E> coll);

        public boolean addAll(Collection<? extends E> coll);

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public Stream<E> stream();

        @Override
        public Stream<E> parallelStream();
    }

    public static <E> Queue<E> checkedQueue(Queue<E> queue, Class<E> type);

    static class CheckedQueue<E> extends CheckedCollection<E> implements Queue<E>, Serializable {

        private static final long serialVersionUID = 1433151992604707767L;

        final Queue<E> queue;

        CheckedQueue(Queue<E> queue, Class<E> elementType) {
            super(queue, elementType);
            this.queue = queue;
        }

        public E element();

        public boolean equals(Object o);

        public int hashCode();

        public E peek();

        public E poll();

        public E remove();

        public boolean offer(E e);
    }

    public static <E> Set<E> checkedSet(Set<E> s, Class<E> type);

    static class CheckedSet<E> extends CheckedCollection<E> implements Set<E>, Serializable {

        private static final long serialVersionUID = 4694047833775013803L;

        CheckedSet(Set<E> s, Class<E> elementType) {
            super(s, elementType);
        }

        public boolean equals(Object o);

        public int hashCode();
    }

    public static <E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type);

    static class CheckedSortedSet<E> extends CheckedSet<E> implements SortedSet<E>, Serializable {

        private static final long serialVersionUID = 1599911165492914959L;

        private final SortedSet<E> ss;

        CheckedSortedSet(SortedSet<E> s, Class<E> type) {
            super(s, type);
            ss = s;
        }

        public Comparator<? super E> comparator();

        public E first();

        public E last();

        public SortedSet<E> subSet(E fromElement, E toElement);

        public SortedSet<E> headSet(E toElement);

        public SortedSet<E> tailSet(E fromElement);
    }

    public static <E> NavigableSet<E> checkedNavigableSet(NavigableSet<E> s, Class<E> type);

    static class CheckedNavigableSet<E> extends CheckedSortedSet<E> implements NavigableSet<E>, Serializable {

        private static final long serialVersionUID = -5429120189805438922L;

        private final NavigableSet<E> ns;

        CheckedNavigableSet(NavigableSet<E> s, Class<E> type) {
            super(s, type);
            ns = s;
        }

        public E lower(E e);

        public E floor(E e);

        public E ceiling(E e);

        public E higher(E e);

        public E pollFirst();

        public E pollLast();

        public NavigableSet<E> descendingSet();

        public Iterator<E> descendingIterator();

        public NavigableSet<E> subSet(E fromElement, E toElement);

        public NavigableSet<E> headSet(E toElement);

        public NavigableSet<E> tailSet(E fromElement);

        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

        public NavigableSet<E> headSet(E toElement, boolean inclusive);

        public NavigableSet<E> tailSet(E fromElement, boolean inclusive);
    }

    public static <E> List<E> checkedList(List<E> list, Class<E> type);

    static class CheckedList<E> extends CheckedCollection<E> implements List<E> {

        private static final long serialVersionUID = 65247728283967356L;

        final List<E> list;

        CheckedList(List<E> list, Class<E> type) {
            super(list, type);
            this.list = list;
        }

        public boolean equals(Object o);

        public int hashCode();

        public E get(int index);

        public E remove(int index);

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        public E set(int index, E element);

        public void add(int index, E element);

        public boolean addAll(int index, Collection<? extends E> c);

        public ListIterator<E> listIterator();

        public ListIterator<E> listIterator(final int index);

        public List<E> subList(int fromIndex, int toIndex);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);
    }

    static class CheckedRandomAccessList<E> extends CheckedList<E> implements RandomAccess {

        private static final long serialVersionUID = 1638200125423088369L;

        CheckedRandomAccessList(List<E> list, Class<E> type) {
            super(list, type);
        }

        public List<E> subList(int fromIndex, int toIndex);
    }

    public static <K, V> Map<K, V> checkedMap(Map<K, V> m, Class<K> keyType, Class<V> valueType);

    private static class CheckedMap<K, V> implements Map<K, V>, Serializable {

        private static final long serialVersionUID = 5742860141034234728L;

        private final Map<K, V> m;

        final Class<K> keyType;

        final Class<V> valueType;

        private void typeCheck(Object key, Object value);

        private BiFunction<? super K, ? super V, ? extends V> typeCheck(BiFunction<? super K, ? super V, ? extends V> func);

        private String badKeyMsg(Object key);

        private String badValueMsg(Object value);

        CheckedMap(Map<K, V> m, Class<K> keyType, Class<V> valueType) {
            this.m = Objects.requireNonNull(m);
            this.keyType = Objects.requireNonNull(keyType);
            this.valueType = Objects.requireNonNull(valueType);
        }

        @NonNegative
        public int size();

        public boolean isEmpty();

        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public boolean containsKey(Object key);

        public boolean containsValue(Object v);

        public V get(Object key);

        public V remove(Object key);

        public void clear();

        public Set<K> keySet();

        public Collection<V> values();

        public boolean equals(Object o);

        public int hashCode();

        public String toString();

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        public V put(K key, V value);

        @SuppressWarnings("unchecked")
        public void putAll(Map<? extends K, ? extends V> t);

        private transient Set<Map.Entry<K, V>> entrySet;

        public Set<Map.Entry<K, V>> entrySet();

        @Override
        public void forEach(BiConsumer<? super K, ? super V> action);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public boolean remove(Object key, Object value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public V replace(K key, V value);

        @Override
        public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

        @Override
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        @Override
        public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        @Override
        public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

        static class CheckedEntrySet<K, V> implements Set<Map.Entry<K, V>> {

            private final Set<Map.Entry<K, V>> s;

            private final Class<V> valueType;

            CheckedEntrySet(Set<Map.Entry<K, V>> s, Class<V> valueType) {
                this.s = s;
                this.valueType = valueType;
            }

            public int size();

            public boolean isEmpty();

            public String toString();

            public int hashCode();

            public void clear();

            public boolean add(Map.Entry<K, V> e);

            public boolean addAll(Collection<? extends Map.Entry<K, V>> coll);

            public Iterator<Map.Entry<K, V>> iterator();

            @SuppressWarnings("unchecked")
            public Object[] toArray();

            @SuppressWarnings("unchecked")
            public <T> T[] toArray(T[] a);

            public boolean contains(Object o);

            public boolean containsAll(Collection<?> c);

            public boolean remove(Object o);

            public boolean removeAll(Collection<?> c);

            public boolean retainAll(Collection<?> c);

            private boolean batchRemove(Collection<?> c, boolean complement);

            public boolean equals(Object o);

            static <K, V, T> CheckedEntry<K, V, T> checkedEntry(Map.Entry<K, V> e, Class<T> valueType);

            private static class CheckedEntry<K, V, T> implements Map.Entry<K, V> {

                private final Map.Entry<K, V> e;

                private final Class<T> valueType;

                CheckedEntry(Map.Entry<K, V> e, Class<T> valueType) {
                    this.e = Objects.requireNonNull(e);
                    this.valueType = Objects.requireNonNull(valueType);
                }

                public K getKey();

                public V getValue();

                public int hashCode();

                public String toString();

                public V setValue(V value);

                private String badValueMsg(Object value);

                public boolean equals(Object o);
            }
        }
    }

    public static <K, V> SortedMap<K, V> checkedSortedMap(SortedMap<K, V> m, Class<K> keyType, Class<V> valueType);

    static class CheckedSortedMap<K, V> extends CheckedMap<K, V> implements SortedMap<K, V>, Serializable {

        private static final long serialVersionUID = 1599671320688067438L;

        private final SortedMap<K, V> sm;

        CheckedSortedMap(SortedMap<K, V> m, Class<K> keyType, Class<V> valueType) {
            super(m, keyType, valueType);
            sm = m;
        }

        public Comparator<? super K> comparator();

        public K firstKey();

        public K lastKey();

        public SortedMap<K, V> subMap(K fromKey, K toKey);

        public SortedMap<K, V> headMap(K toKey);

        public SortedMap<K, V> tailMap(K fromKey);
    }

    public static <K, V> NavigableMap<K, V> checkedNavigableMap(NavigableMap<K, V> m, Class<K> keyType, Class<V> valueType);

    static class CheckedNavigableMap<K, V> extends CheckedSortedMap<K, V> implements NavigableMap<K, V>, Serializable {

        private static final long serialVersionUID = -4852462692372534096L;

        private final NavigableMap<K, V> nm;

        CheckedNavigableMap(NavigableMap<K, V> m, Class<K> keyType, Class<V> valueType) {
            super(m, keyType, valueType);
            nm = m;
        }

        public Comparator<? super K> comparator();

        public K firstKey();

        public K lastKey();

        public Entry<K, V> lowerEntry(K key);

        public K lowerKey(K key);

        public Entry<K, V> floorEntry(K key);

        public K floorKey(K key);

        public Entry<K, V> ceilingEntry(K key);

        public K ceilingKey(K key);

        public Entry<K, V> higherEntry(K key);

        public K higherKey(K key);

        public Entry<K, V> firstEntry();

        public Entry<K, V> lastEntry();

        public Entry<K, V> pollFirstEntry();

        public Entry<K, V> pollLastEntry();

        public NavigableMap<K, V> descendingMap();

        public NavigableSet<K> keySet();

        public NavigableSet<K> navigableKeySet();

        public NavigableSet<K> descendingKeySet();

        @Override
        public NavigableMap<K, V> subMap(K fromKey, K toKey);

        @Override
        public NavigableMap<K, V> headMap(K toKey);

        @Override
        public NavigableMap<K, V> tailMap(K fromKey);

        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        public NavigableMap<K, V> headMap(K toKey, boolean inclusive);

        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> emptyIterator();

    private static class EmptyIterator<E> implements Iterator<E> {

        static final EmptyIterator<Object> EMPTY_ITERATOR = new EmptyIterator<>();

        public boolean hasNext();

        public E next();

        public void remove();

        @Override
        public void forEachRemaining(Consumer<? super E> action);
    }

    @SuppressWarnings("unchecked")
    public static <T> ListIterator<T> emptyListIterator();

    private static class EmptyListIterator<E> extends EmptyIterator<E> implements ListIterator<E> {

        static final EmptyListIterator<Object> EMPTY_ITERATOR = new EmptyListIterator<>();

        public boolean hasPrevious();

        public E previous();

        public int nextIndex();

        public int previousIndex();

        public void set(E e);

        public void add(E e);
    }

    @SuppressWarnings("unchecked")
    public static <T> Enumeration<T> emptyEnumeration();

    private static class EmptyEnumeration<E> implements Enumeration<E> {

        static final EmptyEnumeration<Object> EMPTY_ENUMERATION = new EmptyEnumeration<>();

        public boolean hasMoreElements();

        public E nextElement();

        public Iterator<E> asIterator();
    }

    @SuppressWarnings("rawtypes")
    public static final Set EMPTY_SET = new EmptySet<>();

    @SuppressWarnings("unchecked")
    public static final <T> Set<T> emptySet();

    private static class EmptySet<E> extends AbstractSet<E> implements Serializable {

        private static final long serialVersionUID = 1582296315990362920L;

        @SideEffectFree
        public Iterator<E> iterator();

        @NonNegative
        public int size();

        public boolean isEmpty();

        public void clear();

        public boolean contains(Object obj);

        public boolean containsAll(Collection<?> c);

        @SideEffectFree
        @PolyNull
        public Object[] toArray(Collections.EmptySet<@PolyNull E> this);

        @SideEffectFree
        public <T> T[] toArray(T[] a);

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        private Object readResolve();

        @Override
        public int hashCode();
    }

    @SuppressWarnings("unchecked")
    public static <E> SortedSet<E> emptySortedSet();

    @SuppressWarnings("unchecked")
    public static <E> NavigableSet<E> emptyNavigableSet();

    @SuppressWarnings("rawtypes")
    public static final List EMPTY_LIST = new EmptyList<>();

    @SuppressWarnings("unchecked")
    public static final <T> List<T> emptyList();

    private static class EmptyList<E> extends AbstractList<E> implements RandomAccess, Serializable {

        private static final long serialVersionUID = 8842843931221139166L;

        @SideEffectFree
        public Iterator<E> iterator();

        public ListIterator<E> listIterator();

        @NonNegative
        public int size();

        public boolean isEmpty();

        public void clear();

        public boolean contains(Object obj);

        public boolean containsAll(Collection<?> c);

        @SideEffectFree
        @PolyNull
        public Object[] toArray(Collections.EmptyList<@PolyNull E> this);

        @SideEffectFree
        public <T> T[] toArray(T[] a);

        public E get(int index);

        public boolean equals(Object o);

        public int hashCode();

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);

        @Override
        public void forEach(Consumer<? super E> action);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        private Object readResolve();
    }

    @SuppressWarnings("rawtypes")
    public static final Map EMPTY_MAP = new EmptyMap<>();

    @SuppressWarnings("unchecked")
    public static final <K, V> Map<K, V> emptyMap();

    @SuppressWarnings("unchecked")
    public static final <K, V> SortedMap<K, V> emptySortedMap();

    @SuppressWarnings("unchecked")
    public static final <K, V> NavigableMap<K, V> emptyNavigableMap();

    private static class EmptyMap<K, V> extends AbstractMap<K, V> implements Serializable {

        private static final long serialVersionUID = 6428348081105594320L;

        @NonNegative
        public int size();

        public boolean isEmpty();

        public void clear();

        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public boolean containsKey(Object key);

        public boolean containsValue(Object value);

        public V get(Object key);

        public Set<K> keySet();

        public Collection<V> values();

        public Set<Map.Entry<K, V>> entrySet();

        public boolean equals(Object o);

        public int hashCode();

        @Override
        @SuppressWarnings("unchecked")
        public V getOrDefault(Object k, V defaultValue);

        @Override
        public void forEach(BiConsumer<? super K, ? super V> action);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public boolean remove(Object key, Object value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public V replace(K key, V value);

        @Override
        public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

        @Override
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        @Override
        public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        @Override
        public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

        private Object readResolve();
    }

    public static <T> Set<T> singleton(T o);

    static <E> Iterator<E> singletonIterator(final E e);

    static <T> Spliterator<T> singletonSpliterator(final T element);

    private static class SingletonSet<E> extends AbstractSet<E> implements Serializable {

        private static final long serialVersionUID = 3193687207550431679L;

        private final E element;

        SingletonSet(E e) {
            element = e;
        }

        @SideEffectFree
        public Iterator<E> iterator();

        @NonNegative
        public int size();

        public boolean contains(Object o);

        @Override
        public void forEach(Consumer<? super E> action);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @Override
        public int hashCode();
    }

    @ArrayLen(1)
    public static <T> List<T> singletonList(T o);

    @ArrayLen(1)
    private static class SingletonList<E> extends AbstractList<E> implements RandomAccess, Serializable {

        private static final long serialVersionUID = 3093736618740652951L;

        private final E element;

        @SuppressWarnings({ "inconsistent.constructor.type", "super.invocation.invalid" })
        @CFComment("index: every SingletonList is @ArrayLen(1)")
        SingletonList(E obj) {
            element = obj;
        }

        @SideEffectFree
        public Iterator<E> iterator();

        @NonNegative
        public int size();

        public boolean contains(Object obj);

        public E get(int index);

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public int hashCode();
    }

    public static <K, V> Map<K, V> singletonMap(K key, V value);

    private static class SingletonMap<K, V> extends AbstractMap<K, V> implements Serializable {

        private static final long serialVersionUID = -6979724477215052911L;

        private final K k;

        private final V v;

        SingletonMap(K key, V value) {
            k = key;
            v = value;
        }

        @NonNegative
        public int size();

        public boolean isEmpty();

        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public boolean containsKey(Object key);

        public boolean containsValue(Object value);

        public V get(Object key);

        private transient Set<K> keySet;

        private transient Set<Map.Entry<K, V>> entrySet;

        private transient Collection<V> values;

        public Set<K> keySet();

        public Set<Map.Entry<K, V>> entrySet();

        public Collection<V> values();

        @Override
        public V getOrDefault(Object key, V defaultValue);

        @Override
        public void forEach(BiConsumer<? super K, ? super V> action);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public boolean remove(Object key, Object value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public V replace(K key, V value);

        @Override
        public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

        @Override
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        @Override
        public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        @Override
        public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

        @Override
        public int hashCode();
    }

    public static <T> List<T> nCopies(@NonNegative int n, T o);

    private static class CopiesList<E> extends AbstractList<E> implements RandomAccess, Serializable {

        private static final long serialVersionUID = 2739099268398711800L;

        final int n;

        final E element;

        CopiesList(int n, E e) {
            assert n >= 0;
            this.n = n;
            element = e;
        }

        @NonNegative
        public int size();

        public boolean contains(Object obj);

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        public E get(int index);

        @SideEffectFree
        @PolyNull
        public Object[] toArray(Collections.CopiesList<@PolyNull E> this);

        @SideEffectFree
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a);

        public List<E> subList(int fromIndex, int toIndex);

        @Override
        public Stream<E> stream();

        @Override
        public Stream<E> parallelStream();

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();
    }

    @SuppressWarnings("unchecked")
    public static <T> Comparator<T> reverseOrder();

    private static class ReverseComparator implements Comparator<Comparable<Object>>, Serializable {

        private static final long serialVersionUID = 7207038068494060240L;

        static final ReverseComparator REVERSE_ORDER = new ReverseComparator();

        public int compare(Comparable<Object> c1, Comparable<Object> c2);

        private Object readResolve();

        @Override
        public Comparator<Comparable<Object>> reversed();
    }

    @SuppressWarnings("unchecked")
    public static <T> Comparator<T> reverseOrder(@Nullable Comparator<T> cmp);

    private static class ReverseComparator2<T> implements Comparator<T>, Serializable {

        private static final long serialVersionUID = 4374092139857L;

        final Comparator<T> cmp;

        ReverseComparator2(Comparator<T> cmp) {
            assert cmp != null;
            this.cmp = cmp;
        }

        public int compare(T t1, T t2);

        public boolean equals(Object o);

        public int hashCode();

        @Override
        public Comparator<T> reversed();
    }

    public static <T> Enumeration<T> enumeration(final Collection<T> c);

    public static <T> ArrayList<T> list(Enumeration<T> e);

    static boolean eq(Object o1, Object o2);

    @NonNegative
    public static int frequency(Collection<?> c, @Nullable Object o);

    public static boolean disjoint(Collection<?> c1, Collection<?> c2);

    @SafeVarargs
    public static <T> boolean addAll(@GuardSatisfied Collection<? super T> c, T... elements);

    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map);

    private static class SetFromMap<E> extends AbstractSet<E> implements Set<E>, Serializable {

        private final Map<E, Boolean> m;

        private transient Set<E> s;

        SetFromMap(Map<E, Boolean> map) {
            if (!map.isEmpty())
                throw new IllegalArgumentException("Map is non-empty");
            m = map;
            s = map.keySet();
        }

        public void clear();

        @NonNegative
        public int size();

        public boolean isEmpty();

        public boolean contains(Object o);

        public boolean remove(Object o);

        public boolean add(E e);

        @SideEffectFree
        public Iterator<E> iterator();

        @SideEffectFree
        @PolyNull
        public Object[] toArray(Collections.SetFromMap<@PolyNull E> this);

        @SideEffectFree
        public <T> T[] toArray(T[] a);

        public String toString();

        public int hashCode();

        public boolean equals(Object o);

        public boolean containsAll(Collection<?> c);

        public boolean removeAll(Collection<?> c);

        public boolean retainAll(Collection<?> c);

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public Stream<E> stream();

        @Override
        public Stream<E> parallelStream();

        private static final long serialVersionUID = 2454657854757543876L;

        private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException;
    }

    public static <T> Queue<T> asLifoQueue(Deque<T> deque);

    static class AsLIFOQueue<E> extends AbstractQueue<E> implements Queue<E>, Serializable {

        private static final long serialVersionUID = 1802017725587941708L;

        private final Deque<E> q;

        AsLIFOQueue(Deque<E> q) {
            this.q = q;
        }

        public boolean add(E e);

        public boolean offer(E e);

        public E poll();

        public E remove();

        public E peek();

        public E element();

        public void clear();

        @NonNegative
        public int size();

        public boolean isEmpty();

        public boolean contains(Object o);

        public boolean remove(Object o);

        @SideEffectFree
        public Iterator<E> iterator();

        @SideEffectFree
        @PolyNull
        public Object[] toArray(Collections.AsLIFOQueue<@PolyNull E> this);

        @SideEffectFree
        public <T> T[] toArray(T[] a);

        public <T> T[] toArray(IntFunction<T[]> f);

        public String toString();

        public boolean containsAll(Collection<?> c);

        public boolean removeAll(Collection<?> c);

        public boolean retainAll(Collection<?> c);

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public Stream<E> stream();

        @Override
        public Stream<E> parallelStream();
    }
}
