package java.lang;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.checkerframework.checker.signature.qual.ClassGetName;
import org.checkerframework.checker.signature.qual.ClassGetSimpleName;
import org.checkerframework.checker.signature.qual.DotSeparatedIdentifiers;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import org.checkerframework.framework.qual.Covariant;
import java.lang.annotation.Annotation;
import java.lang.module.ModuleReader;
import java.lang.ref.SoftReference;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamField;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.loader.BootLoader;
import jdk.internal.loader.BuiltinClassLoader;
import jdk.internal.misc.Unsafe;
import jdk.internal.misc.VM;
import jdk.internal.module.Resources;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.ConstantPool;
import jdk.internal.reflect.Reflection;
import jdk.internal.reflect.ReflectionFactory;
import jdk.internal.vm.annotation.ForceInline;
import sun.reflect.generics.factory.CoreReflectionFactory;
import sun.reflect.generics.factory.GenericsFactory;
import sun.reflect.generics.repository.ClassRepository;
import sun.reflect.generics.repository.MethodRepository;
import sun.reflect.generics.repository.ConstructorRepository;
import sun.reflect.generics.scope.ClassScope;
import sun.security.util.SecurityConstants;
import sun.reflect.annotation.*;
import sun.reflect.misc.ReflectUtil;

@CFComment({ "interning: All instances of Class are interned.", "lock: public boolean isTypeAnnotationPresent(@GuardSatisfied Class<T> this,@GuardSatisfied Class<T><? extends java.lang.annotation.Annotation> annotationClass) { throw new RuntimeException(\"skeleton method\"); }", "public <M extends java.lang.annotation.Annotation> M getTypeAnnotation(Class<M> annotationClass) { throw new RuntimeException(\"skeleton method\"); }", "public java.lang.annotation.Annotation[] getTypeAnnotations() { throw new RuntimeException(\"skeleton method\"); }", "public java.lang.annotation.Annotation[] getDeclaredTypeAnnotations() { throw new RuntimeException(\"skeleton method\"); }", "nullness: The type argument to Class is meaningless.", "Class<@NonNull String> and Class<@Nullable String> have the same", "meaning, but are unrelated by the Java type hierarchy.", "@Covariant makes Class<@NonNull String> a subtype of Class<@Nullable String>." })
@AnnotatedFor({ "index", "interning", "lock", "nullness", "signature" })
@Covariant({ 0 })
@Interned
public final class Class<@UnknownKeyFor T> implements java.io.Serializable, GenericDeclaration, Type, AnnotatedElement {

    @SideEffectFree
    public String toString(@GuardSatisfied Class<T> this);

    public String toGenericString();

    @CallerSensitive
    public static Class<?> forName(@ClassGetName String className) throws ClassNotFoundException;

    @CallerSensitive
    public static Class<?> forName(@ClassGetName String name, boolean initialize, @Nullable ClassLoader loader) throws ClassNotFoundException;

    @CallerSensitive
    public static Class<?> forName(Module module, String name);

    @CallerSensitive
    @Deprecated()
    @NonNull
    public T newInstance() throws InstantiationException, IllegalAccessException;

    @EnsuresNonNullIf(expression = { "#1" }, result = true)
    @Pure
    @HotSpotIntrinsicCandidate
    public native boolean isInstance(@GuardSatisfied Class<T> this, @Nullable Object obj);

    @Pure
    @HotSpotIntrinsicCandidate
    public native boolean isAssignableFrom(@GuardSatisfied Class<T> this, Class<?> cls);

    @Pure
    @HotSpotIntrinsicCandidate
    public native boolean isInterface(@GuardSatisfied Class<T> this);

    @EnsuresNonNullIf(expression = { "getComponentType()" }, result = true)
    @Pure
    @HotSpotIntrinsicCandidate
    public native boolean isArray(@GuardSatisfied Class<T> this);

    @Pure
    @HotSpotIntrinsicCandidate
    public native boolean isPrimitive(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isAnnotation(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isSynthetic(@GuardSatisfied Class<T> this);

    @CFComment("interning: In the Oracle JDK, the result of getName is interned")
    @Pure
    @ClassGetName
    @Interned
    public String getName();

    @CallerSensitive
    @ForceInline
    @Nullable
    public ClassLoader getClassLoader();

    public Module getModule();

    @SuppressWarnings("unchecked")
    public TypeVariable<Class<T>>[] getTypeParameters();

    @Pure
    @HotSpotIntrinsicCandidate
    @Nullable
    public native Class<? super T> getSuperclass(@GuardSatisfied Class<T> this);

    @Nullable
    public Type getGenericSuperclass();

    @Pure
    @Nullable
    public Package getPackage(@GuardSatisfied Class<T> this);

    @DotSeparatedIdentifiers
    public String getPackageName();

    @SideEffectFree
    public Class<?>[] getInterfaces(@GuardSatisfied Class<T> this);

    public Type[] getGenericInterfaces();

    @Pure
    @Nullable
    public Class<?> getComponentType(@GuardSatisfied Class<T> this);

    @Pure
    @HotSpotIntrinsicCandidate
    public native int getModifiers(@GuardSatisfied Class<T> this);

    public native Object @Nullable [] getSigners();

    @CallerSensitive
    @Nullable
    public Method getEnclosingMethod() throws SecurityException;

    @CallerSensitive
    @Nullable
    public Constructor<?> getEnclosingConstructor() throws SecurityException;

    @CallerSensitive
    @Nullable
    public Class<?> getDeclaringClass() throws SecurityException;

    @Pure
    @CallerSensitive
    @Nullable
    public Class<?> getEnclosingClass() throws SecurityException;

    @ClassGetSimpleName
    public String getSimpleName();

    public String getTypeName();

    @Nullable
    @ClassGetSimpleName
    public String getCanonicalName();

    @Pure
    public boolean isAnonymousClass(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isLocalClass(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isMemberClass(@GuardSatisfied Class<T> this);

    @CallerSensitive
    public Class<?>[] getClasses();

    @CallerSensitive
    public Field[] getFields() throws SecurityException;

    @CallerSensitive
    public Method[] getMethods() throws SecurityException;

    @CallerSensitive
    public Constructor<?>[] getConstructors() throws SecurityException;

    @CallerSensitive
    public Field getField(String name) throws NoSuchFieldException, SecurityException;

    @Pure
    @CallerSensitive
    public Method getMethod(String name, Class<?>@Nullable ... parameterTypes) throws NoSuchMethodException, SecurityException;

    @Pure
    @CallerSensitive
    public Constructor<T> getConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException;

    @CallerSensitive
    public Class<?>[] getDeclaredClasses() throws SecurityException;

    @CallerSensitive
    public Field[] getDeclaredFields() throws SecurityException;

    @CallerSensitive
    public Method[] getDeclaredMethods() throws SecurityException;

    @CallerSensitive
    public Constructor<?>[] getDeclaredConstructors() throws SecurityException;

    @CallerSensitive
    public Field getDeclaredField(String name) throws NoSuchFieldException, SecurityException;

    @CallerSensitive
    public Method getDeclaredMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException;

    @CallerSensitive
    public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException;

    @CallerSensitive
    @Nullable
    public InputStream getResourceAsStream(String name);

    @CallerSensitive
    @Nullable
    public URL getResource(String name);

    public java.security.ProtectionDomain getProtectionDomain();

    public boolean desiredAssertionStatus();

    @Pure
    public boolean isEnum(@GuardSatisfied Class<T> this);

    @NonNull
    public T @Nullable [] getEnumConstants();

    @SuppressWarnings("unchecked")
    @HotSpotIntrinsicCandidate
    @PolyNull
    public T cast(@PolyNull Object obj);

    @SuppressWarnings("unchecked")
    public <U> Class<? extends U> asSubclass(Class<U> clazz);

    @SuppressWarnings("unchecked")
    @Nullable
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass);

    @Pure
    @Override
    public boolean isAnnotationPresent(@GuardSatisfied Class<T> this, @GuardSatisfied Class<? extends Annotation> annotationClass);

    @Override
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationClass);

    public Annotation[] getAnnotations();

    @Override
    @SuppressWarnings("unchecked")
    public <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationClass);

    @Override
    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationClass);

    public Annotation[] getDeclaredAnnotations();

    public AnnotatedType getAnnotatedSuperclass();

    public AnnotatedType[] getAnnotatedInterfaces();

    @CallerSensitive
    public Class<?> getNestHost();

    public boolean isNestmateOf(Class<?> c);

    @CallerSensitive
    public Class<?>[] getNestMembers();
}
