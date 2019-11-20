package java.lang.ref;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
public class SoftReference<T> extends Reference<T> {

    public SoftReference(T referent) {
    }

    public SoftReference(T referent, ReferenceQueue<? super T> q) {
    }

    @SideEffectFree
    public T get(@GuardSatisfied SoftReference<T> this);
}
