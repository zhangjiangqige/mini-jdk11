package java.util.concurrent.atomic;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class AtomicLongArray implements java.io.Serializable {

    private static final long serialVersionUID = -2308431214976778248L;

    private static final VarHandle AA = MethodHandles.arrayElementVarHandle(long[].class);

    private final long[] array;

    public AtomicLongArray(int length) {
        array = new long[length];
    }

    public AtomicLongArray(long[] array) {
        this.array = array.clone();
    }

    public final int length();

    public final long get(int i);

    public final void set(int i, long newValue);

    public final void lazySet(int i, long newValue);

    public final long getAndSet(int i, long newValue);

    public final boolean compareAndSet(int i, long expectedValue, long newValue);

    @Deprecated(since = "9")
    public final boolean weakCompareAndSet(int i, long expectedValue, long newValue);

    public final boolean weakCompareAndSetPlain(int i, long expectedValue, long newValue);

    public final long getAndIncrement(int i);

    public final long getAndDecrement(int i);

    public final long getAndAdd(int i, long delta);

    public final long incrementAndGet(int i);

    public final long decrementAndGet(int i);

    public long addAndGet(int i, long delta);

    public final long getAndUpdate(int i, LongUnaryOperator updateFunction);

    public final long updateAndGet(int i, LongUnaryOperator updateFunction);

    public final long getAndAccumulate(int i, long x, LongBinaryOperator accumulatorFunction);

    public final long accumulateAndGet(int i, long x, LongBinaryOperator accumulatorFunction);

    public String toString();

    public final long getPlain(int i);

    public final void setPlain(int i, long newValue);

    public final long getOpaque(int i);

    public final void setOpaque(int i, long newValue);

    public final long getAcquire(int i);

    public final void setRelease(int i, long newValue);

    public final long compareAndExchange(int i, long expectedValue, long newValue);

    public final long compareAndExchangeAcquire(int i, long expectedValue, long newValue);

    public final long compareAndExchangeRelease(int i, long expectedValue, long newValue);

    public final boolean weakCompareAndSetVolatile(int i, long expectedValue, long newValue);

    public final boolean weakCompareAndSetAcquire(int i, long expectedValue, long newValue);

    public final boolean weakCompareAndSetRelease(int i, long expectedValue, long newValue);
}
