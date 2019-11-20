package java.lang.reflect;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import org.checkerframework.framework.qual.Covariant;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.ConstructorAccessor;
import jdk.internal.reflect.Reflection;
import jdk.internal.vm.annotation.ForceInline;
import sun.reflect.annotation.TypeAnnotation;
import sun.reflect.annotation.TypeAnnotationParser;
import sun.reflect.generics.repository.ConstructorRepository;
import sun.reflect.generics.factory.CoreReflectionFactory;
import sun.reflect.generics.factory.GenericsFactory;
import sun.reflect.generics.scope.ConstructorScope;
import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;
import java.util.StringJoiner;

@CFComment({ "nullness: The type argument to Constructor is meaningless.", "Constructor<@NonNull String> and Constructor<@Nullable String> have the same", "meaning, but are unrelated by the Java type hierarchy.", "@Covariant makes Constructor<@NonNull String> a subtype of Constructor<@Nullable String>." })
@AnnotatedFor({ "lock", "nullness" })
@Covariant({ 0 })
public final class Constructor<T> extends Executable {

    @Override
    @CallerSensitive
    public void setAccessible(boolean flag);

    @Override
    public Class<T> getDeclaringClass();

    @Override
    public String getName();

    @Override
    public int getModifiers();

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public TypeVariable<Constructor<T>>[] getTypeParameters();

    @Override
    public Class<?>[] getParameterTypes();

    public int getParameterCount();

    @Override
    public Type[] getGenericParameterTypes();

    @Override
    public Class<?>[] getExceptionTypes();

    @Override
    public Type[] getGenericExceptionTypes();

    @Pure
    public boolean equals(@GuardSatisfied Constructor<T> this, @GuardSatisfied @Nullable Object obj);

    @Pure
    public int hashCode(@GuardSatisfied Constructor<T> this);

    @SideEffectFree
    public String toString(@GuardSatisfied Constructor<T> this);

    @Override
    public String toGenericString();

    @CallerSensitive
    @ForceInline
    @NonNull
    public T newInstance(Object... initargs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    @Pure
    @Override
    public boolean isVarArgs(@GuardSatisfied Constructor<T> this);

    @Pure
    @Override
    public boolean isSynthetic(@GuardSatisfied Constructor<T> this);

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass);

    public Annotation[] getDeclaredAnnotations();

    @Override
    public Annotation[][] getParameterAnnotations();

    @Override
    public AnnotatedType getAnnotatedReturnType();

    @Override
    public AnnotatedType getAnnotatedReceiverType();
}
