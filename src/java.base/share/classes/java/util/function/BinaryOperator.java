package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;
import java.util.Comparator;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {

    public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator);

    public static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator);
}
