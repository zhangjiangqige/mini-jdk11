package java.lang.ref;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public class WeakReference<T> extends Reference<T> {

    public WeakReference(T referent) {
    }

    public WeakReference(T referent, ReferenceQueue<? super T> q) {
    }
}
