package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

@AnnotatedFor({ "nullness" })
public interface BlockingDeque<E extends @NonNull Object> extends BlockingQueue<E>, Deque<E> {
}
