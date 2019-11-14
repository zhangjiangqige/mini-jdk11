package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface BiFunction<T, U, R> {

    R apply(T t, U u);

    default <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after);
}
