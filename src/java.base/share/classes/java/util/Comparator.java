package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.Serializable;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.ToDoubleFunction;
import java.util.Comparators;

@CFComment({ "lock/nullness: Javadoc says: \"a comparator may optionally permit comparison of null", "arguments, while maintaining the requirements for an equivalence relation.\"" })
@AnnotatedFor({ "lock", "nullness", "index" })
@FunctionalInterface
public interface Comparator<T> {

    int compare(T o1, T o2);

    @Pure
    boolean equals(@GuardSatisfied Comparator<T> this, @GuardSatisfied @Nullable Object obj);

    default Comparator<T> reversed();

    default Comparator<T> thenComparing(Comparator<? super T> other);

    default <U> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator);

    default <U extends Comparable<? super U>> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor);

    default Comparator<T> thenComparingInt(ToIntFunction<? super T> keyExtractor);

    default Comparator<T> thenComparingLong(ToLongFunction<? super T> keyExtractor);

    default Comparator<T> thenComparingDouble(ToDoubleFunction<? super T> keyExtractor);

    public static <T extends Comparable<? super T>> Comparator<T> reverseOrder();

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<@NonNull ? super @NonNull T>> Comparator<T> naturalOrder();

    public static <T> Comparator<@Nullable T> nullsFirst(Comparator<@Nullable ? super T> comparator);

    public static <T> Comparator<@Nullable T> nullsLast(Comparator<@Nullable ? super T> comparator);

    public static <T, U> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator);

    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor);

    public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> keyExtractor);

    public static <T> Comparator<T> comparingLong(ToLongFunction<? super T> keyExtractor);

    public static <T> Comparator<T> comparingDouble(ToDoubleFunction<? super T> keyExtractor);
}
