package java.util.concurrent.atomic;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class AtomicMarkableReference<V> {

    private static class Pair<T> {

        final T reference;

        final boolean mark;

        private Pair(T reference, boolean mark) {
            this.reference = reference;
            this.mark = mark;
        }

        static <T> Pair<T> of(T reference, boolean mark);
    }

    private volatile Pair<V> pair;

    public AtomicMarkableReference(V initialRef, boolean initialMark) {
        pair = Pair.of(initialRef, initialMark);
    }

    public V getReference();

    public boolean isMarked();

    public V get(boolean[] markHolder);

    public boolean weakCompareAndSet(V expectedReference, V newReference, boolean expectedMark, boolean newMark);

    public boolean compareAndSet(V expectedReference, V newReference, boolean expectedMark, boolean newMark);

    public void set(V newReference, boolean newMark);

    public boolean attemptMark(V expectedReference, boolean newMark);

    private static final VarHandle PAIR;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            PAIR = l.findVarHandle(AtomicMarkableReference.class, "pair", Pair.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private boolean casPair(Pair<V> cmp, Pair<V> val);
}
