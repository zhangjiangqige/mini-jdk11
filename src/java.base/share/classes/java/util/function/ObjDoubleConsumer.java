package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface ObjDoubleConsumer<T> {

    void accept(T t, double value);
}
