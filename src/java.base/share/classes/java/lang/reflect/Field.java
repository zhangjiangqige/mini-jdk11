package java.lang.reflect;

import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.FieldAccessor;
import jdk.internal.reflect.Reflection;
import jdk.internal.vm.annotation.ForceInline;
import sun.reflect.generics.repository.FieldRepository;
import sun.reflect.generics.factory.CoreReflectionFactory;
import sun.reflect.generics.factory.GenericsFactory;
import sun.reflect.generics.scope.ClassScope;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Objects;
import sun.reflect.annotation.AnnotationParser;
import sun.reflect.annotation.AnnotationSupport;
import sun.reflect.annotation.TypeAnnotation;
import sun.reflect.annotation.TypeAnnotationParser;

@AnnotatedFor({ "interning", "lock", "nullness" })
public final class Field extends AccessibleObject implements Member {

    @Override
    @CallerSensitive
    public void setAccessible(boolean flag);

    @SideEffectFree
    @Override
    public Class<?> getDeclaringClass(@GuardSatisfied Field this);

    @SideEffectFree
    @Interned
    public String getName(@GuardSatisfied Field this);

    @Pure
    public int getModifiers(@GuardSatisfied Field this);

    @Pure
    public boolean isEnumConstant(@GuardSatisfied Field this);

    @Pure
    public boolean isSynthetic(@GuardSatisfied Field this);

    @SideEffectFree
    public Class<?> getType(@GuardSatisfied Field this);

    @SideEffectFree
    public Type getGenericType(@GuardSatisfied Field this);

    @Pure
    public boolean equals(@GuardSatisfied Field this, @GuardSatisfied @Nullable Object obj);

    @Pure
    public int hashCode(@GuardSatisfied Field this);

    @SideEffectFree
    public String toString(@GuardSatisfied Field this);

    @SideEffectFree
    public String toGenericString(@GuardSatisfied Field this);

    @SideEffectFree
    @CallerSensitive
    @ForceInline
    @Nullable
    public Object get(@GuardSatisfied Field this, @UnknownInitialization @GuardSatisfied @Nullable Object obj) throws IllegalArgumentException, IllegalAccessException;

    @Pure
    @CallerSensitive
    @ForceInline
    public boolean getBoolean(@GuardSatisfied Field this, @UnknownInitialization @GuardSatisfied @Nullable Object obj) throws IllegalArgumentException, IllegalAccessException;

    @Pure
    @CallerSensitive
    @ForceInline
    public byte getByte(@GuardSatisfied Field this, @UnknownInitialization @GuardSatisfied @Nullable Object obj) throws IllegalArgumentException, IllegalAccessException;

    @Pure
    @CallerSensitive
    @ForceInline
    public char getChar(@GuardSatisfied Field this, @UnknownInitialization @GuardSatisfied @Nullable Object obj) throws IllegalArgumentException, IllegalAccessException;

    @Pure
    @CallerSensitive
    @ForceInline
    public short getShort(@GuardSatisfied Field this, @UnknownInitialization @GuardSatisfied @Nullable Object obj) throws IllegalArgumentException, IllegalAccessException;

    @Pure
    @CallerSensitive
    @ForceInline
    public int getInt(@GuardSatisfied Field this, @UnknownInitialization @GuardSatisfied @Nullable Object obj) throws IllegalArgumentException, IllegalAccessException;

    @Pure
    @CallerSensitive
    @ForceInline
    public long getLong(@GuardSatisfied Field this, @UnknownInitialization @GuardSatisfied @Nullable Object obj) throws IllegalArgumentException, IllegalAccessException;

    @Pure
    @CallerSensitive
    @ForceInline
    public float getFloat(@GuardSatisfied Field this, @UnknownInitialization @GuardSatisfied @Nullable Object obj) throws IllegalArgumentException, IllegalAccessException;

    @Pure
    @CallerSensitive
    @ForceInline
    public double getDouble(@GuardSatisfied Field this, @UnknownInitialization @GuardSatisfied @Nullable Object obj) throws IllegalArgumentException, IllegalAccessException;

    @CallerSensitive
    @ForceInline
    public void set(@UnknownInitialization @Nullable Object obj, @Nullable Object value) throws IllegalArgumentException, IllegalAccessException;

    @CallerSensitive
    @ForceInline
    public void setBoolean(@UnknownInitialization @Nullable Object obj, boolean z) throws IllegalArgumentException, IllegalAccessException;

    @CallerSensitive
    @ForceInline
    public void setByte(@UnknownInitialization @Nullable Object obj, byte b) throws IllegalArgumentException, IllegalAccessException;

    @CallerSensitive
    @ForceInline
    public void setChar(@UnknownInitialization @Nullable Object obj, char c) throws IllegalArgumentException, IllegalAccessException;

    @CallerSensitive
    @ForceInline
    public void setShort(@UnknownInitialization @Nullable Object obj, short s) throws IllegalArgumentException, IllegalAccessException;

    @CallerSensitive
    @ForceInline
    public void setInt(@UnknownInitialization @Nullable Object obj, int i) throws IllegalArgumentException, IllegalAccessException;

    @CallerSensitive
    @ForceInline
    public void setLong(@UnknownInitialization @Nullable Object obj, long l) throws IllegalArgumentException, IllegalAccessException;

    @CallerSensitive
    @ForceInline
    public void setFloat(@UnknownInitialization @Nullable Object obj, float f) throws IllegalArgumentException, IllegalAccessException;

    @CallerSensitive
    @ForceInline
    public void setDouble(@UnknownInitialization @Nullable Object obj, double d) throws IllegalArgumentException, IllegalAccessException;

    @SideEffectFree
    @Nullable
    public <T extends @Nullable Annotation> T getAnnotation(@GuardSatisfied Field this, Class<@NonNull T> annotationClass);

    @Override
    public <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass);

    @SideEffectFree
    public Annotation[] getDeclaredAnnotations(@GuardSatisfied Field this);

    public AnnotatedType getAnnotatedType();
}
