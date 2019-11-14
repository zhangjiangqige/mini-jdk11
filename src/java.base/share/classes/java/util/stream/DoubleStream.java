package java.util.stream;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

@AnnotatedFor({ "lock", "nullness" })
public interface DoubleStream extends BaseStream<Double, DoubleStream> {

    DoubleStream filter(DoublePredicate predicate);

    DoubleStream map(DoubleUnaryOperator mapper);

    <U> Stream<U> mapToObj(DoubleFunction<? extends U> mapper);

    IntStream mapToInt(DoubleToIntFunction mapper);

    LongStream mapToLong(DoubleToLongFunction mapper);

    DoubleStream flatMap(DoubleFunction<? extends DoubleStream> mapper);

    DoubleStream distinct();

    DoubleStream sorted();

    DoubleStream peek(DoubleConsumer action);

    DoubleStream limit(long maxSize);

    DoubleStream skip(long n);

    default DoubleStream takeWhile(DoublePredicate predicate);

    default DoubleStream dropWhile(DoublePredicate predicate);

    void forEach(DoubleConsumer action);

    void forEachOrdered(DoubleConsumer action);

    @SideEffectFree
    double[] toArray();

    double reduce(double identity, DoubleBinaryOperator op);

    OptionalDouble reduce(DoubleBinaryOperator op);

    <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> accumulator, BiConsumer<R, R> combiner);

    double sum();

    OptionalDouble min();

    OptionalDouble max();

    long count();

    OptionalDouble average();

    DoubleSummaryStatistics summaryStatistics();

    boolean anyMatch(DoublePredicate predicate);

    boolean allMatch(DoublePredicate predicate);

    boolean noneMatch(DoublePredicate predicate);

    OptionalDouble findFirst();

    OptionalDouble findAny();

    Stream<Double> boxed();

    @Override
    DoubleStream sequential();

    @Override
    DoubleStream parallel();

    @SideEffectFree
    @Override
    PrimitiveIterator.OfDouble iterator();

    @SideEffectFree
    @Override
    Spliterator.OfDouble spliterator();

    public static Builder builder();

    public static DoubleStream empty();

    public static DoubleStream of(double t);

    public static DoubleStream of(double... values);

    public static DoubleStream iterate(final double seed, final DoubleUnaryOperator f);

    public static DoubleStream iterate(double seed, DoublePredicate hasNext, DoubleUnaryOperator next);

    public static DoubleStream generate(DoubleSupplier s);

    public static DoubleStream concat(DoubleStream a, DoubleStream b);

    public interface Builder extends DoubleConsumer {

        @Override
        void accept(double t);

        default Builder add(DoubleStream.@GuardSatisfied Builder this, double t);

        DoubleStream build();
    }
}
