package java.util.concurrent.atomic;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class AtomicMarkableReference<V> {

    public AtomicMarkableReference(V initialRef, boolean initialMark) {
    }

    public V getReference();

    public boolean isMarked();

    public V get(boolean[] markHolder);

    public boolean weakCompareAndSet(V expectedReference, V newReference, boolean expectedMark, boolean newMark);

    public boolean compareAndSet(V expectedReference, V newReference, boolean expectedMark, boolean newMark);

    public void set(V newReference, boolean newMark);

    public boolean attemptMark(V expectedReference, boolean newMark);
}
