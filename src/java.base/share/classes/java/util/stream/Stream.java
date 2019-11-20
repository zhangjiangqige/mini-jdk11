package java.util.stream;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

@AnnotatedFor({ "lock", "nullness" })
public interface Stream<T> extends BaseStream<T, Stream<T>> {

    public static <T> Builder<T> builder();

    public static <T> Stream<T> empty();

    public static <T> Stream<T> of(T t);

    public static <T> Stream<T> ofNullable(T t);

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> Stream<T> of(T... values);

    public static <T> Stream<T> iterate(final T seed, final UnaryOperator<T> f);

    public static <T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next);

    public static <T> Stream<T> generate(Supplier<? extends T> s);

    public static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b);

    public interface Builder<T> extends Consumer<T> {
    }
}
