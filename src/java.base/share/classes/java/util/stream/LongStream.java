package java.util.stream;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Arrays;
import java.util.LongSummaryStatistics;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

@AnnotatedFor({ "lock", "nullness" })
public interface LongStream extends BaseStream<Long, LongStream> {

    LongStream filter(LongPredicate predicate);

    LongStream map(LongUnaryOperator mapper);

    <U> Stream<U> mapToObj(LongFunction<? extends U> mapper);

    IntStream mapToInt(LongToIntFunction mapper);

    DoubleStream mapToDouble(LongToDoubleFunction mapper);

    LongStream flatMap(LongFunction<? extends LongStream> mapper);

    LongStream distinct();

    LongStream sorted();

    LongStream peek(LongConsumer action);

    LongStream limit(long maxSize);

    LongStream skip(long n);

    default LongStream takeWhile(LongPredicate predicate);

    default LongStream dropWhile(LongPredicate predicate);

    void forEach(LongConsumer action);

    void forEachOrdered(LongConsumer action);

    @SideEffectFree
    long[] toArray();

    long reduce(long identity, LongBinaryOperator op);

    OptionalLong reduce(LongBinaryOperator op);

    <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> accumulator, BiConsumer<R, R> combiner);

    long sum();

    OptionalLong min();

    OptionalLong max();

    long count();

    OptionalDouble average();

    LongSummaryStatistics summaryStatistics();

    boolean anyMatch(LongPredicate predicate);

    boolean allMatch(LongPredicate predicate);

    boolean noneMatch(LongPredicate predicate);

    OptionalLong findFirst();

    OptionalLong findAny();

    DoubleStream asDoubleStream();

    Stream<Long> boxed();

    @Override
    LongStream sequential();

    @Override
    LongStream parallel();

    @SideEffectFree
    @Override
    PrimitiveIterator.OfLong iterator();

    @SideEffectFree
    @Override
    Spliterator.OfLong spliterator();

    public static Builder builder();

    public static LongStream empty();

    public static LongStream of(long t);

    public static LongStream of(long... values);

    public static LongStream iterate(final long seed, final LongUnaryOperator f);

    public static LongStream iterate(long seed, LongPredicate hasNext, LongUnaryOperator next);

    public static LongStream generate(LongSupplier s);

    public static LongStream range(long startInclusive, final long endExclusive);

    public static LongStream rangeClosed(long startInclusive, final long endInclusive);

    public static LongStream concat(LongStream a, LongStream b);

    public interface Builder extends LongConsumer {

        @Override
        void accept(long t);

        default Builder add(LongStream.@GuardSatisfied Builder this, long t);

        LongStream build();
    }
}
