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

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void sort(List<T> list);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> void sort(List<T> list, @Nullable Comparator<? super T> c);

    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key);

    @SuppressWarnings("unchecked")
    public static <T> int binarySearch(List<? extends T> list, T key, @Nullable Comparator<? super T> c);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void reverse(@GuardSatisfied List<?> list);

    public static void shuffle(@GuardSatisfied List<?> list);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void shuffle(@GuardSatisfied List<?> list, Random rnd);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void swap(@GuardSatisfied List<?> list, int i, int j);

    public static <T> void fill(@GuardSatisfied List<? super T> list, T obj);

    public static <T> void copy(List<? super T> dest, List<? extends T> src);

    public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T min(Collection<? extends T> coll, @Nullable Comparator<? super T> comp);

    public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T max(Collection<? extends T> coll, @Nullable Comparator<? super T> comp);

    public static void rotate(@GuardSatisfied List<?> list, int distance);

    public static <T> boolean replaceAll(List<T> list, @Nullable T oldVal, T newVal);

    @Pure
    @GTENegativeOne
    public static int indexOfSubList(@GuardSatisfied List<?> source, @GuardSatisfied List<?> target);

    @Pure
    @GTENegativeOne
    public static int lastIndexOfSubList(@GuardSatisfied List<?> source, @GuardSatisfied List<?> target);

    public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c);

    public static <T> Set<T> unmodifiableSet(Set<? extends T> s);

    public static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> s);

    public static <T> NavigableSet<T> unmodifiableNavigableSet(NavigableSet<T> s);

    public static <T> List<T> unmodifiableList(List<? extends T> list);

    public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m);

    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> m);

    public static <K, V> NavigableMap<K, V> unmodifiableNavigableMap(NavigableMap<K, ? extends V> m);

    public static <T> Collection<T> synchronizedCollection(Collection<T> c);

    public static <T> Set<T> synchronizedSet(Set<T> s);

    public static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s);

    public static <T> NavigableSet<T> synchronizedNavigableSet(NavigableSet<T> s);

    public static <T> List<T> synchronizedList(List<T> list);

    public static <K, V> Map<K, V> synchronizedMap(Map<K, V> m);

    public static <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> m);

    public static <K, V> NavigableMap<K, V> synchronizedNavigableMap(NavigableMap<K, V> m);

    public static <E> Collection<E> checkedCollection(Collection<E> c, Class<E> type);

    public static <E> Queue<E> checkedQueue(Queue<E> queue, Class<E> type);

    public static <E> Set<E> checkedSet(Set<E> s, Class<E> type);

    public static <E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type);

    public static <E> NavigableSet<E> checkedNavigableSet(NavigableSet<E> s, Class<E> type);

    public static <E> List<E> checkedList(List<E> list, Class<E> type);

    public static <K, V> Map<K, V> checkedMap(Map<K, V> m, Class<K> keyType, Class<V> valueType);

    public static <K, V> SortedMap<K, V> checkedSortedMap(SortedMap<K, V> m, Class<K> keyType, Class<V> valueType);

    public static <K, V> NavigableMap<K, V> checkedNavigableMap(NavigableMap<K, V> m, Class<K> keyType, Class<V> valueType);

    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> emptyIterator();

    @SuppressWarnings("unchecked")
    public static <T> ListIterator<T> emptyListIterator();

    @SuppressWarnings("unchecked")
    public static <T> Enumeration<T> emptyEnumeration();

    @SuppressWarnings("rawtypes")
    public static final Set EMPTY_SET;

    @SuppressWarnings("unchecked")
    public static final <T> Set<T> emptySet();

    @SuppressWarnings("unchecked")
    public static <E> SortedSet<E> emptySortedSet();

    @SuppressWarnings("unchecked")
    public static <E> NavigableSet<E> emptyNavigableSet();

    @SuppressWarnings("rawtypes")
    public static final List EMPTY_LIST;

    @SuppressWarnings("unchecked")
    public static final <T> List<T> emptyList();

    @SuppressWarnings("rawtypes")
    public static final Map EMPTY_MAP;

    @SuppressWarnings("unchecked")
    public static final <K, V> Map<K, V> emptyMap();

    @SuppressWarnings("unchecked")
    public static final <K, V> SortedMap<K, V> emptySortedMap();

    @SuppressWarnings("unchecked")
    public static final <K, V> NavigableMap<K, V> emptyNavigableMap();

    public static <T> Set<T> singleton(T o);

    @ArrayLen(1)
    public static <T> List<T> singletonList(T o);

    public static <K, V> Map<K, V> singletonMap(K key, V value);

    public static <T> List<T> nCopies(@NonNegative int n, T o);

    @SuppressWarnings("unchecked")
    public static <T> Comparator<T> reverseOrder();

    @SuppressWarnings("unchecked")
    public static <T> Comparator<T> reverseOrder(@Nullable Comparator<T> cmp);

    public static <T> Enumeration<T> enumeration(final Collection<T> c);

    public static <T> ArrayList<T> list(Enumeration<T> e);

    @NonNegative
    public static int frequency(Collection<?> c, @Nullable Object o);

    public static boolean disjoint(Collection<?> c1, Collection<?> c2);

    @SafeVarargs
    public static <T> boolean addAll(@GuardSatisfied Collection<? super T> c, T... elements);

    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map);

    public static <T> Queue<T> asLifoQueue(Deque<T> deque);
}
