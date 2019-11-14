package java.util.concurrent.atomic;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import jdk.internal.misc.Unsafe;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import java.lang.invoke.VarHandle;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AtomicReferenceFieldUpdater<T, V> {

    @CallerSensitive
    public static <U, W> AtomicReferenceFieldUpdater<U, W> newUpdater(Class<U> tclass, Class<W> vclass, String fieldName);

    protected AtomicReferenceFieldUpdater() {
    }

    public abstract boolean compareAndSet(T obj, V expect, V update);

    public abstract boolean weakCompareAndSet(T obj, V expect, V update);

    public abstract void set(T obj, V newValue);

    public abstract void lazySet(T obj, V newValue);

    public abstract V get(T obj);

    public V getAndSet(T obj, V newValue);

    public final V getAndUpdate(T obj, UnaryOperator<V> updateFunction);

    public final V updateAndGet(T obj, UnaryOperator<V> updateFunction);

    public final V getAndAccumulate(T obj, V x, BinaryOperator<V> accumulatorFunction);

    public final V accumulateAndGet(T obj, V x, BinaryOperator<V> accumulatorFunction);

    private static final class AtomicReferenceFieldUpdaterImpl<T, V> extends AtomicReferenceFieldUpdater<T, V> {

        private static final Unsafe U = Unsafe.getUnsafe();

        private final long offset;

        private final Class<?> cclass;

        private final Class<T> tclass;

        private final Class<V> vclass;

        AtomicReferenceFieldUpdaterImpl(final Class<T> tclass, final Class<V> vclass, final String fieldName, final Class<?> caller) {
            final Field field;
            final Class<?> fieldClass;
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
                fieldClass = field.getType();
            } catch (PrivilegedActionException pae) {
                throw new RuntimeException(pae.getException());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            if (vclass != fieldClass)
                throw new ClassCastException();
            if (vclass.isPrimitive())
                throw new IllegalArgumentException("Must be reference type");
            if (!Modifier.isVolatile(modifiers))
                throw new IllegalArgumentException("Must be volatile type");
            this.cclass = (Modifier.isProtected(modifiers) && tclass.isAssignableFrom(caller) && !isSamePackage(tclass, caller)) ? caller : tclass;
            this.tclass = tclass;
            this.vclass = vclass;
            this.offset = U.objectFieldOffset(field);
        }

        private static boolean isAncestor(ClassLoader first, ClassLoader second);

        private static boolean isSamePackage(Class<?> class1, Class<?> class2);

        private final void accessCheck(T obj);

        private final void throwAccessCheckException(T obj);

        private final void valueCheck(V v);

        static void throwCCE();

        public final boolean compareAndSet(T obj, V expect, V update);

        public final boolean weakCompareAndSet(T obj, V expect, V update);

        public final void set(T obj, V newValue);

        public final void lazySet(T obj, V newValue);

        @SuppressWarnings("unchecked")
        public final V get(T obj);

        @SuppressWarnings("unchecked")
        public final V getAndSet(T obj, V newValue);
    }
}
