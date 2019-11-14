package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

@AnnotatedFor({ "lock", "nullness" })
public interface PrimitiveIterator<T, T_CONS> extends Iterator<T> {

    @SuppressWarnings("overloads")
    void forEachRemaining(T_CONS action);

    public static interface OfInt extends PrimitiveIterator<Integer, IntConsumer> {

        int nextInt();

        @Override
        default void forEachRemaining(IntConsumer action);

        @Override
        default Integer next(PrimitiveIterator.@GuardSatisfied OfInt this);

        @Override
        default void forEachRemaining(Consumer<? super Integer> action);
    }

    public static interface OfLong extends PrimitiveIterator<Long, LongConsumer> {

        long nextLong();

        @Override
        default void forEachRemaining(LongConsumer action);

        @Override
        default Long next(PrimitiveIterator.@GuardSatisfied OfLong this);

        @Override
        default void forEachRemaining(Consumer<? super Long> action);
    }

    public static interface OfDouble extends PrimitiveIterator<Double, DoubleConsumer> {

        double nextDouble();

        @Override
        default void forEachRemaining(DoubleConsumer action);

        @Override
        default Double next(PrimitiveIterator.@GuardSatisfied OfDouble this);

        @Override
        default void forEachRemaining(Consumer<? super Double> action);
    }
}
