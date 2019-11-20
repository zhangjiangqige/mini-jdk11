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
    }
}
