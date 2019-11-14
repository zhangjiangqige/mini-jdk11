package java.lang.ref;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
public class PhantomReference<T> extends Reference<T> {

    @SideEffectFree
    public T get(@GuardSatisfied PhantomReference<T> this);

    public PhantomReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }
}
