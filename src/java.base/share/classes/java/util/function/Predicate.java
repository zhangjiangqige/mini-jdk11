package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface Predicate<T> {

    boolean test(T t);

    default Predicate<T> and(Predicate<? super T> other);

    default Predicate<T> negate();

    default Predicate<T> or(Predicate<? super T> other);

    static <T> Predicate<T> isEqual(Object targetRef);

    @SuppressWarnings("unchecked")
    static <T> Predicate<T> not(Predicate<? super T> target);
}
