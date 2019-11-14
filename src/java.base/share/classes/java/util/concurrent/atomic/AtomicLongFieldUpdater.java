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

    private static final class CASUpdater<T> extends AtomicLongFieldUpdater<T> {

        private static final Unsafe U = Unsafe.getUnsafe();

        private final long offset;

        private final Class<?> cclass;

        private final Class<T> tclass;

        CASUpdater(final Class<T> tclass, final String fieldName, final Class<?> caller) {
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
            if (field.getType() != long.class)
                throw new IllegalArgumentException("Must be long type");
            if (!Modifier.isVolatile(modifiers))
                throw new IllegalArgumentException("Must be volatile type");
            this.cclass = (Modifier.isProtected(modifiers) && tclass.isAssignableFrom(caller) && !isSamePackage(tclass, caller)) ? caller : tclass;
            this.tclass = tclass;
            this.offset = U.objectFieldOffset(field);
        }

        private final void accessCheck(T obj);

        private final void throwAccessCheckException(T obj);

        public final boolean compareAndSet(T obj, long expect, long update);

        public final boolean weakCompareAndSet(T obj, long expect, long update);

        public final void set(T obj, long newValue);

        public final void lazySet(T obj, long newValue);

        public final long get(T obj);

        public final long getAndSet(T obj, long newValue);

        public final long getAndAdd(T obj, long delta);

        public final long getAndIncrement(T obj);

        public final long getAndDecrement(T obj);

        public final long incrementAndGet(T obj);

        public final long decrementAndGet(T obj);

        public final long addAndGet(T obj, long delta);
    }

    private static final class LockedUpdater<T> extends AtomicLongFieldUpdater<T> {

        private static final Unsafe U = Unsafe.getUnsafe();

        private final long offset;

        private final Class<?> cclass;

        private final Class<T> tclass;

        LockedUpdater(final Class<T> tclass, final String fieldName, final Class<?> caller) {
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
            if (field.getType() != long.class)
                throw new IllegalArgumentException("Must be long type");
            if (!Modifier.isVolatile(modifiers))
                throw new IllegalArgumentException("Must be volatile type");
            this.cclass = (Modifier.isProtected(modifiers) && tclass.isAssignableFrom(caller) && !isSamePackage(tclass, caller)) ? caller : tclass;
            this.tclass = tclass;
            this.offset = U.objectFieldOffset(field);
        }

        private final void accessCheck(T obj);

        private final RuntimeException accessCheckException(T obj);

        public final boolean compareAndSet(T obj, long expect, long update);

        public final boolean weakCompareAndSet(T obj, long expect, long update);

        public final void set(T obj, long newValue);

        public final void lazySet(T obj, long newValue);

        public final long get(T obj);
    }

    static boolean isAncestor(ClassLoader first, ClassLoader second);

    static boolean isSamePackage(Class<?> class1, Class<?> class2);
}
