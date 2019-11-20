package java.util.stream;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

@AnnotatedFor({ "lock", "nullness" })
public interface IntStream extends BaseStream<Integer, IntStream> {

    public static Builder builder();

    public static IntStream empty();

    public static IntStream of(int t);

    public static IntStream of(int... values);

    public static IntStream iterate(final int seed, final IntUnaryOperator f);

    public static IntStream iterate(int seed, IntPredicate hasNext, IntUnaryOperator next);

    public static IntStream generate(IntSupplier s);

    public static IntStream range(int startInclusive, int endExclusive);

    public static IntStream rangeClosed(int startInclusive, int endInclusive);

    public static IntStream concat(IntStream a, IntStream b);

    public interface Builder extends IntConsumer {
    }
}
