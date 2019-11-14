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

    private static final class AtomicIntegerFieldUpdaterImpl<T> extends AtomicIntegerFieldUpdater<T> {

        private static final Unsafe U = Unsafe.getUnsafe();

        private final long offset;

        private final Class<?> cclass;

        private final Class<T> tclass;

        AtomicIntegerFieldUpdaterImpl(final Class<T> tclass, final String fieldName, final Class<?> caller) {
            final Field field;
            final int modifiers;
            try {
                field = AccessController.doPrivileged(new PrivilegedExceptionAction<Field>() {

                    public Field run() throws NoSuchFieldException {
                        return tclass.getDeclaredField(fieldName);
                    }
                });
                modifiers = field.getModifiers();
                sun.reflect.misc.ReflectUtil.ensureMemberAccess(caller, tclass, null, modifiers);
                ClassLoader cl = tclass.getClassLoader();
                ClassLoader ccl = caller.getClassLoader();
                if ((ccl != null) && (ccl != cl) && ((cl == null) || !isAncestor(cl, ccl))) {
                    sun.reflect.misc.ReflectUtil.checkPackageAccess(tclass);
                }
            } catch (PrivilegedActionException pae) {
                throw new RuntimeException(pae.getException());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            if (field.getType() != int.class)
                throw new IllegalArgumentException("Must be integer type");
            if (!Modifier.isVolatile(modifiers))
                throw new IllegalArgumentException("Must be volatile type");
            this.cclass = (Modifier.isProtected(modifiers) && tclass.isAssignableFrom(caller) && !isSamePackage(tclass, caller)) ? caller : tclass;
            this.tclass = tclass;
            this.offset = U.objectFieldOffset(field);
        }

        private static boolean isAncestor(ClassLoader first, ClassLoader second);

        private static boolean isSamePackage(Class<?> class1, Class<?> class2);

        private final void accessCheck(T obj);

        private final void throwAccessCheckException(T obj);

        public final boolean compareAndSet(T obj, int expect, int update);

        public final boolean weakCompareAndSet(T obj, int expect, int update);

        public final void set(T obj, int newValue);

        public final void lazySet(T obj, int newValue);

        public final int get(T obj);

        public final int getAndSet(T obj, int newValue);

        public final int getAndAdd(T obj, int delta);

        public final int getAndIncrement(T obj);

        public final int getAndDecrement(T obj);

        public final int incrementAndGet(T obj);

        public final int decrementAndGet(T obj);

        public final int addAndGet(T obj, int delta);
    }
}
