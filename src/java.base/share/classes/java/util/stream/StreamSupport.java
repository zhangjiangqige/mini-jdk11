package java.util.stream;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Supplier;

@AnnotatedFor({ "nullness" })
public final class StreamSupport {

    public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean parallel);

    public static <T> Stream<T> stream(Supplier<? extends Spliterator<T>> supplier, int characteristics, boolean parallel);

    public static IntStream intStream(Spliterator.OfInt spliterator, boolean parallel);

    public static IntStream intStream(Supplier<? extends Spliterator.OfInt> supplier, int characteristics, boolean parallel);

    public static LongStream longStream(Spliterator.OfLong spliterator, boolean parallel);

    public static LongStream longStream(Supplier<? extends Spliterator.OfLong> supplier, int characteristics, boolean parallel);

    public static DoubleStream doubleStream(Spliterator.OfDouble spliterator, boolean parallel);

    public static DoubleStream doubleStream(Supplier<? extends Spliterator.OfDouble> supplier, int characteristics, boolean parallel);
}
