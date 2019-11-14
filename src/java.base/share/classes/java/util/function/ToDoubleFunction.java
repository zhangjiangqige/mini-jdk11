package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface ToDoubleFunction<T> {

    double applyAsDouble(T value);
}