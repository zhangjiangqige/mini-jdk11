package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

@AnnotatedFor({ "lock", "nullness" })
public interface Spliterator<T> {

    public static final int ORDERED;

    public static final int DISTINCT;

    public static final int SORTED;

    public static final int SIZED;

    public static final int NONNULL;

    public static final int IMMUTABLE;

    public static final int CONCURRENT;

    public static final int SUBSIZED;

    public interface OfPrimitive<T, T_CONS, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>> extends Spliterator<T> {
    }

    public interface OfInt extends OfPrimitive<Integer, IntConsumer, OfInt> {
    }

    public interface OfLong extends OfPrimitive<Long, LongConsumer, OfLong> {
    }

    public interface OfDouble extends OfPrimitive<Double, DoubleConsumer, OfDouble> {
    }
}
