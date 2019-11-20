package java.util.concurrent.atomic;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Objects;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import jdk.internal.misc.Unsafe;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import java.lang.invoke.VarHandle;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AtomicLongFieldUpdater<T> {

    @CallerSensitive
    public static <U> AtomicLongFieldUpdater<U> newUpdater(Class<U> tclass, String fieldName);

    protected AtomicLongFieldUpdater() {
    }

    public abstract boolean compareAndSet(T obj, long expect, long update);

    public abstract boolean weakCompareAndSet(T obj, long expect, long update);

    public abstract void set(T obj, long newValue);

    public abstract void lazySet(T obj, long newValue);

    public abstract long get(T obj);

    public long getAndSet(T obj, long newValue);

    public long getAndIncrement(T obj);

    public long getAndDecrement(T obj);

    public long getAndAdd(T obj, long delta);

    public long incrementAndGet(T obj);

    public long decrementAndGet(T obj);

    public long addAndGet(T obj, long delta);

    public final long getAndUpdate(T obj, LongUnaryOperator updateFunction);

    public final long updateAndGet(T obj, LongUnaryOperator updateFunction);

    public final long getAndAccumulate(T obj, long x, LongBinaryOperator accumulatorFunction);

    public final long accumulateAndGet(T obj, long x, LongBinaryOperator accumulatorFunction);
}
