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

    public static Builder builder();

    public static DoubleStream empty();

    public static DoubleStream of(double t);

    public static DoubleStream of(double... values);

    public static DoubleStream iterate(final double seed, final DoubleUnaryOperator f);

    public static DoubleStream iterate(double seed, DoublePredicate hasNext, DoubleUnaryOperator next);

    public static DoubleStream generate(DoubleSupplier s);

    public static DoubleStream concat(DoubleStream a, DoubleStream b);

    public interface Builder extends DoubleConsumer {
    }
}
