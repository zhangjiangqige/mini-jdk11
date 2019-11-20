package java.util.concurrent;

import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "nullness: Make upper bound explicit for clarity." })
@AnnotatedFor({ "nullness" })
@FunctionalInterface
public interface Callable<V> {
}
