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

    IntStream filter(IntPredicate predicate);

    IntStream map(IntUnaryOperator mapper);

    <U> Stream<U> mapToObj(IntFunction<? extends U> mapper);

    LongStream mapToLong(IntToLongFunction mapper);

    DoubleStream mapToDouble(IntToDoubleFunction mapper);

    IntStream flatMap(IntFunction<? extends IntStream> mapper);

    IntStream distinct();

    IntStream sorted();

    IntStream peek(IntConsumer action);

    IntStream limit(long maxSize);

    IntStream skip(long n);

    default IntStream takeWhile(IntPredicate predicate);

    default IntStream dropWhile(IntPredicate predicate);

    void forEach(IntConsumer action);

    void forEachOrdered(IntConsumer action);

    @SideEffectFree
    int[] toArray();

    int reduce(int identity, IntBinaryOperator op);

    OptionalInt reduce(IntBinaryOperator op);

    <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R, R> combiner);

    int sum();

    OptionalInt min();

    OptionalInt max();

    long count();

    OptionalDouble average();

    IntSummaryStatistics summaryStatistics();

    boolean anyMatch(IntPredicate predicate);

    boolean allMatch(IntPredicate predicate);

    boolean noneMatch(IntPredicate predicate);

    OptionalInt findFirst();

    OptionalInt findAny();

    LongStream asLongStream();

    DoubleStream asDoubleStream();

    Stream<Integer> boxed();

    @Override
    IntStream sequential();

    @Override
    IntStream parallel();

    @SideEffectFree
    @Override
    PrimitiveIterator.OfInt iterator();

    @SideEffectFree
    @Override
    Spliterator.OfInt spliterator();

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

        @Override
        void accept(int t);

        default Builder add(IntStream.@GuardSatisfied Builder this, int t);

        IntStream build();
    }
}
