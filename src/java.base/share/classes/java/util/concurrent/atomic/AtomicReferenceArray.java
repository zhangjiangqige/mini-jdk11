package java.util.concurrent.atomic;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class AtomicReferenceArray<E> implements java.io.Serializable {

    public AtomicReferenceArray(int length) {
    }

    public AtomicReferenceArray(E[] array) {
    }

    public final int length();

    @SuppressWarnings("unchecked")
    public final E get(int i);

    public final void set(int i, E newValue);

    public final void lazySet(int i, E newValue);

    @SuppressWarnings("unchecked")
    public final E getAndSet(int i, E newValue);

    public final boolean compareAndSet(int i, E expectedValue, E newValue);

    @Deprecated()
    public final boolean weakCompareAndSet(int i, E expectedValue, E newValue);

    public final boolean weakCompareAndSetPlain(int i, E expectedValue, E newValue);

    public final E getAndUpdate(int i, UnaryOperator<E> updateFunction);

    public final E updateAndGet(int i, UnaryOperator<E> updateFunction);

    public final E getAndAccumulate(int i, E x, BinaryOperator<E> accumulatorFunction);

    public final E accumulateAndGet(int i, E x, BinaryOperator<E> accumulatorFunction);

    public String toString();

    public final E getPlain(int i);

    public final void setPlain(int i, E newValue);

    public final E getOpaque(int i);

    public final void setOpaque(int i, E newValue);

    public final E getAcquire(int i);

    public final void setRelease(int i, E newValue);

    public final E compareAndExchange(int i, E expectedValue, E newValue);

    public final E compareAndExchangeAcquire(int i, E expectedValue, E newValue);

    public final E compareAndExchangeRelease(int i, E expectedValue, E newValue);

    public final boolean weakCompareAndSetVolatile(int i, E expectedValue, E newValue);

    public final boolean weakCompareAndSetAcquire(int i, E expectedValue, E newValue);

    public final boolean weakCompareAndSetRelease(int i, E expectedValue, E newValue);
}
