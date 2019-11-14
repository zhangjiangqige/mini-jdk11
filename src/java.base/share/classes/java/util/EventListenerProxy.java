package java.util;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index", "interning", "lock", "nullness" })
@UsesObjectEquals
public abstract class EventListenerProxy<T extends EventListener> implements EventListener {

    private final T listener;

    public EventListenerProxy(T listener) {
        this.listener = listener;
    }

    public T getListener(@GuardSatisfied EventListenerProxy<T> this);
}
