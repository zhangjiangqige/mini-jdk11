package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

@AnnotatedFor({ "lock", "nullness" })
public interface Spliterator<T> {

    boolean tryAdvance(Consumer<? super T> action);

    default void forEachRemaining(Consumer<? super T> action);

    Spliterator<T> trySplit();

    long estimateSize();

    default long getExactSizeIfKnown();

    int characteristics();

    default boolean hasCharacteristics(int characteristics);

    default Comparator<? super T> getComparator();

    public static final int ORDERED = 0x00000010;

    public static final int DISTINCT = 0x00000001;

    public static final int SORTED = 0x00000004;

    public static final int SIZED = 0x00000040;

    public static final int NONNULL = 0x00000100;

    public static final int IMMUTABLE = 0x00000400;

    public static final int CONCURRENT = 0x00001000;

    public static final int SUBSIZED = 0x00004000;

    public interface OfPrimitive<T, T_CONS, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>> extends Spliterator<T> {

        @Override
        T_SPLITR trySplit();

        @SuppressWarnings("overloads")
        boolean tryAdvance(T_CONS action);

        @SuppressWarnings("overloads")
        default void forEachRemaining(T_CONS action);
    }

    public interface OfInt extends OfPrimitive<Integer, IntConsumer, OfInt> {

        @Override
        OfInt trySplit();

        @Override
        boolean tryAdvance(IntConsumer action);

        @Override
        default void forEachRemaining(IntConsumer action);

        @Override
        default boolean tryAdvance(Consumer<? super Integer> action);

        @Override
        default void forEachRemaining(Consumer<? super Integer> action);
    }

    public interface OfLong extends OfPrimitive<Long, LongConsumer, OfLong> {

        @Override
        OfLong trySplit();

        @Override
        boolean tryAdvance(LongConsumer action);

        @Override
        default void forEachRemaining(LongConsumer action);

        @Override
        default boolean tryAdvance(Consumer<? super Long> action);

        @Override
        default void forEachRemaining(Consumer<? super Long> action);
    }

    public interface OfDouble extends OfPrimitive<Double, DoubleConsumer, OfDouble> {

        @Override
        OfDouble trySplit();

        @Override
        boolean tryAdvance(DoubleConsumer action);

        @Override
        default void forEachRemaining(DoubleConsumer action);

        @Override
        default boolean tryAdvance(Consumer<? super Double> action);

        @Override
        default void forEachRemaining(Consumer<? super Double> action);
    }
}
