package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

@AnnotatedFor({ "lock", "nullness" })
public interface PrimitiveIterator<T, T_CONS> extends Iterator<T> {

    public static interface OfInt extends PrimitiveIterator<Integer, IntConsumer> {
    }

    public static interface OfLong extends PrimitiveIterator<Long, LongConsumer> {
    }

    public static interface OfDouble extends PrimitiveIterator<Double, DoubleConsumer> {
    }
}
