package java.lang.ref;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
class FinalReference<T> extends Reference<T> {

    public FinalReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

    @Override
    public boolean enqueue();
}
