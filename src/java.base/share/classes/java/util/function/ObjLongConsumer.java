package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface ObjLongConsumer<T> {

    void accept(T t, long value);
}
