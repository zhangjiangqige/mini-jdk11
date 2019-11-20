package java.util.concurrent.atomic;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Objects;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import jdk.internal.misc.Unsafe;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import java.lang.invoke.VarHandle;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AtomicIntegerFieldUpdater<T> {

    @CallerSensitive
    public static <U> AtomicIntegerFieldUpdater<U> newUpdater(Class<U> tclass, String fieldName);

    protected AtomicIntegerFieldUpdater() {
    }

    public abstract boolean compareAndSet(T obj, int expect, int update);

    public abstract boolean weakCompareAndSet(T obj, int expect, int update);

    public abstract void set(T obj, int newValue);

    public abstract void lazySet(T obj, int newValue);

    public abstract int get(T obj);

    public int getAndSet(T obj, int newValue);

    public int getAndIncrement(T obj);

    public int getAndDecrement(T obj);

    public int getAndAdd(T obj, int delta);

    public int incrementAndGet(T obj);

    public int decrementAndGet(T obj);

    public int addAndGet(T obj, int delta);

    public final int getAndUpdate(T obj, IntUnaryOperator updateFunction);

    public final int updateAndGet(T obj, IntUnaryOperator updateFunction);

    public final int getAndAccumulate(T obj, int x, IntBinaryOperator accumulatorFunction);

    public final int accumulateAndGet(T obj, int x, IntBinaryOperator accumulatorFunction);
}
