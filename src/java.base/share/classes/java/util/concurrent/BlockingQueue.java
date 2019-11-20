package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Collection;
import java.util.Queue;

@AnnotatedFor({ "nullness" })
public interface BlockingQueue<E extends @NonNull Object> extends Queue<E> {
}
