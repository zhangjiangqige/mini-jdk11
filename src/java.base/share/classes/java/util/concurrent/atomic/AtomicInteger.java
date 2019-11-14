package java.util.concurrent.atomic;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.VarHandle;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class AtomicInteger extends Number implements java.io.Serializable {

    private static final long serialVersionUID = 6214790243416807050L;

    private static final jdk.internal.misc.Unsafe U = jdk.internal.misc.Unsafe.getUnsafe();

    private static final long VALUE = U.objectFieldOffset(AtomicInteger.class, "value");

    private volatile int value;

    public AtomicInteger(int initialValue) {
        value = initialValue;
    }

    public AtomicInteger() {
    }

    public final int get();

    public final void set(int newValue);

    public final void lazySet(int newValue);

    public final int getAndSet(int newValue);

    public final boolean compareAndSet(int expectedValue, int newValue);

    @Deprecated(since = "9")
    public final boolean weakCompareAndSet(int expectedValue, int newValue);

    public final boolean weakCompareAndSetPlain(int expectedValue, int newValue);

    public final int getAndIncrement();

    public final int getAndDecrement();

    public final int getAndAdd(int delta);

    public final int incrementAndGet();

    public final int decrementAndGet();

    public final int addAndGet(int delta);

    public final int getAndUpdate(IntUnaryOperator updateFunction);

    public final int updateAndGet(IntUnaryOperator updateFunction);

    public final int getAndAccumulate(int x, IntBinaryOperator accumulatorFunction);

    public final int accumulateAndGet(int x, IntBinaryOperator accumulatorFunction);

    public String toString();

    public int intValue();

    public long longValue();

    public float floatValue();

    public double doubleValue();

    public final int getPlain();

    public final void setPlain(int newValue);

    public final int getOpaque();

    public final void setOpaque(int newValue);

    public final int getAcquire();

    public final void setRelease(int newValue);

    public final int compareAndExchange(int expectedValue, int newValue);

    public final int compareAndExchangeAcquire(int expectedValue, int newValue);

    public final int compareAndExchangeRelease(int expectedValue, int newValue);

    public final boolean weakCompareAndSetVolatile(int expectedValue, int newValue);

    public final boolean weakCompareAndSetAcquire(int expectedValue, int newValue);

    public final boolean weakCompareAndSetRelease(int expectedValue, int newValue);
}
