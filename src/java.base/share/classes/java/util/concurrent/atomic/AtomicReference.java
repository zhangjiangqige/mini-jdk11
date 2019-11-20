package java.util.concurrent.atomic;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class AtomicReference<V> implements java.io.Serializable {

    public AtomicReference(V initialValue) {
    }

    public AtomicReference() {
    }

    public final V get();

    public final void set(V newValue);

    public final void lazySet(V newValue);

    public final boolean compareAndSet(V expectedValue, V newValue);

    @Deprecated()
    public final boolean weakCompareAndSet(V expectedValue, V newValue);

    public final boolean weakCompareAndSetPlain(V expectedValue, V newValue);

    @SuppressWarnings("unchecked")
    public final V getAndSet(V newValue);

    public final V getAndUpdate(UnaryOperator<V> updateFunction);

    public final V updateAndGet(UnaryOperator<V> updateFunction);

    public final V getAndAccumulate(V x, BinaryOperator<V> accumulatorFunction);

    public final V accumulateAndGet(V x, BinaryOperator<V> accumulatorFunction);

    public String toString();

    public final V getPlain();

    public final void setPlain(V newValue);

    public final V getOpaque();

    public final void setOpaque(V newValue);

    public final V getAcquire();

    public final void setRelease(V newValue);

    public final V compareAndExchange(V expectedValue, V newValue);

    public final V compareAndExchangeAcquire(V expectedValue, V newValue);

    public final V compareAndExchangeRelease(V expectedValue, V newValue);

    public final boolean weakCompareAndSetVolatile(V expectedValue, V newValue);

    public final boolean weakCompareAndSetAcquire(V expectedValue, V newValue);

    public final boolean weakCompareAndSetRelease(V expectedValue, V newValue);
}
