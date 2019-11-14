package java.lang.ref;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.Consumer;
import jdk.internal.misc.VM;

@AnnotatedFor({ "interning", "nullness" })
@SuppressWarnings({ "rawtypes" })
@UsesObjectEquals
public class ReferenceQueue<T> {

    public ReferenceQueue() {
    }

    private static class Null extends ReferenceQueue<Object> {

        boolean enqueue(Reference<?> r);
    }

    static final ReferenceQueue<Object> NULL = new Null();

    static final ReferenceQueue<Object> ENQUEUED = new Null();

    private static class Lock {
    }

    private final Lock lock = new Lock();

    private volatile Reference<? extends T> head;

    private long queueLength = 0;

    @SuppressWarnings({ "unchecked" })
    boolean enqueue(Reference<? extends T> r);

    @SuppressWarnings({ "unchecked" })
    private Reference<? extends T> reallyPoll();

    public Reference<? extends T> poll();

    public Reference<? extends T> remove(long timeout) throws IllegalArgumentException, InterruptedException;

    public Reference<? extends T> remove() throws InterruptedException;

    void forEach(Consumer<? super Reference<? extends T>> action);
}
