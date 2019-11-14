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

    private static final int ANNOTATION = 0x00002000;

    private static final int ENUM = 0x00004000;

    private static final int SYNTHETIC = 0x00001000;

    private static native void registerNatives();

    static {
        registerNatives();
    }

    private Class(ClassLoader loader, Class<?> arrayComponentType) {
        classLoader = loader;
        componentType = arrayComponentType;
    }

    @SideEffectFree
    public String toString(@GuardSatisfied Class<T> this);

    public String toGenericString();

    @CallerSensitive
    public static Class<?> forName(@ClassGetName String className) throws ClassNotFoundException;

    @CallerSensitive
    public static Class<?> forName(@ClassGetName String name, boolean initialize, @Nullable ClassLoader loader) throws ClassNotFoundException;

    private static native Class<?> forName0(String name, boolean initialize, ClassLoader loader, Class<?> caller) throws ClassNotFoundException;

    @CallerSensitive
    public static Class<?> forName(Module module, String name);

    @CallerSensitive
    @Deprecated(since = "9")
    @NonNull
    public T newInstance() throws InstantiationException, IllegalAccessException;

    private transient volatile Constructor<T> cachedConstructor;

    private transient volatile Class<?> newInstanceCallerCache;

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

    @ClassGetName
    private transient String name;

    private native String getName0();

    @CallerSensitive
    @ForceInline
    @Nullable
    public ClassLoader getClassLoader();

    ClassLoader getClassLoader0();

    public Module getModule();

    private transient Module module;

    private final ClassLoader classLoader;

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

    private transient String packageName;

    @SideEffectFree
    public Class<?>[] getInterfaces(@GuardSatisfied Class<T> this);

    private Class<?>[] getInterfaces(boolean cloneArray);

    private native Class<?>[] getInterfaces0();

    public Type[] getGenericInterfaces();

    @Pure
    @Nullable
    public Class<?> getComponentType(@GuardSatisfied Class<T> this);

    private final Class<?> componentType;

    @Pure
    @HotSpotIntrinsicCandidate
    public native int getModifiers(@GuardSatisfied Class<T> this);

    public native Object @Nullable [] getSigners();

    native void setSigners(Object[] signers);

    @CallerSensitive
    @Nullable
    public Method getEnclosingMethod() throws SecurityException;

    private native Object[] getEnclosingMethod0();

    private EnclosingMethodInfo getEnclosingMethodInfo();

    private static final class EnclosingMethodInfo {

        private final Class<?> enclosingClass;

        private final String name;

        private final String descriptor;

        static void validate(Object[] enclosingInfo);

        EnclosingMethodInfo(Object[] enclosingInfo) {
            validate(enclosingInfo);
            this.enclosingClass = (Class<?>) enclosingInfo[0];
            this.name = (String) enclosingInfo[1];
            this.descriptor = (String) enclosingInfo[2];
        }

        boolean isPartial();

        boolean isConstructor();

        boolean isMethod();

        Class<?> getEnclosingClass();

        String getName();

        String getDescriptor();
    }

    private static Class<?> toClass(Type o);

    @CallerSensitive
    @Nullable
    public Constructor<?> getEnclosingConstructor() throws SecurityException;

    @CallerSensitive
    @Nullable
    public Class<?> getDeclaringClass() throws SecurityException;

    private native Class<?> getDeclaringClass0();

    @Pure
    @CallerSensitive
    @Nullable
    public Class<?> getEnclosingClass() throws SecurityException;

    @ClassGetSimpleName
    public String getSimpleName();

    private String getSimpleName0();

    public String getTypeName();

    @Nullable
    @ClassGetSimpleName
    public String getCanonicalName();

    private String getCanonicalName0();

    @Pure
    public boolean isAnonymousClass(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isLocalClass(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isMemberClass(@GuardSatisfied Class<T> this);

    private String getSimpleBinaryName();

    private native String getSimpleBinaryName0();

    private boolean isTopLevelClass();

    private boolean isLocalOrAnonymousClass();

    private boolean hasEnclosingMethodInfo();

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

    List<Method> getDeclaredPublicMethods(String name, Class<?>... parameterTypes);

    @CallerSensitive
    public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException;

    @CallerSensitive
    @Nullable
    public InputStream getResourceAsStream(String name);

    @CallerSensitive
    @Nullable
    public URL getResource(String name);

    private boolean isOpenToCaller(String name, Class<?> caller);

    private static java.security.ProtectionDomain allPermDomain;

    public java.security.ProtectionDomain getProtectionDomain();

    private native java.security.ProtectionDomain getProtectionDomain0();

    static native Class<?> getPrimitiveClass(String name);

    private void checkMemberAccess(SecurityManager sm, int which, Class<?> caller, boolean checkProxyInterfaces);

    private void checkPackageAccess(SecurityManager sm, final ClassLoader ccl, boolean checkProxyInterfaces);

    private String resolveName(String name);

    private static class Atomic {

        private static final Unsafe unsafe = Unsafe.getUnsafe();

        private static final long reflectionDataOffset = unsafe.objectFieldOffset(Class.class, "reflectionData");

        private static final long annotationTypeOffset = unsafe.objectFieldOffset(Class.class, "annotationType");

        private static final long annotationDataOffset = unsafe.objectFieldOffset(Class.class, "annotationData");

        static <T> boolean casReflectionData(Class<?> clazz, SoftReference<ReflectionData<T>> oldData, SoftReference<ReflectionData<T>> newData);

        static <T> boolean casAnnotationType(Class<?> clazz, AnnotationType oldType, AnnotationType newType);

        static <T> boolean casAnnotationData(Class<?> clazz, AnnotationData oldData, AnnotationData newData);
    }

    private static class ReflectionData<T> {

        volatile Field[] declaredFields;

        volatile Field[] publicFields;

        volatile Method[] declaredMethods;

        volatile Method[] publicMethods;

        volatile Constructor<T>[] declaredConstructors;

        volatile Constructor<T>[] publicConstructors;

        volatile Field[] declaredPublicFields;

        volatile Method[] declaredPublicMethods;

        volatile Class<?>[] interfaces;

        String simpleName;

        String canonicalName;

        static final String NULL_SENTINEL = new String();

        final int redefinedCount;

        ReflectionData(int redefinedCount) {
            this.redefinedCount = redefinedCount;
        }
    }

    private transient volatile SoftReference<ReflectionData<T>> reflectionData;

    private transient volatile int classRedefinedCount;

    private ReflectionData<T> reflectionData();

    private ReflectionData<T> newReflectionData(SoftReference<ReflectionData<T>> oldReflectionData, int classRedefinedCount);

    private native String getGenericSignature0();

    private transient volatile ClassRepository genericInfo;

    private GenericsFactory getFactory();

    private ClassRepository getGenericInfo();

    native byte[] getRawAnnotations();

    native byte[] getRawTypeAnnotations();

    static byte[] getExecutableTypeAnnotationBytes(Executable ex);

    native ConstantPool getConstantPool();

    private Field[] privateGetDeclaredFields(boolean publicOnly);

    private Field[] privateGetPublicFields();

    private static void addAll(Collection<Field> c, Field[] o);

    private Constructor<T>[] privateGetDeclaredConstructors(boolean publicOnly);

    private Method[] privateGetDeclaredMethods(boolean publicOnly);

    private Method[] privateGetPublicMethods();

    private static Field searchFields(Field[] fields, String name);

    private Field getField0(String name);

    private static Method searchMethods(Method[] methods, String name, Class<?>[] parameterTypes);

    private static final Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[0];

    private Method getMethod0(String name, Class<?>[] parameterTypes);

    private PublicMethods.MethodList getMethodsRecursive(String name, Class<?>[] parameterTypes, boolean includeStatic);

    private Constructor<T> getConstructor0(Class<?>[] parameterTypes, int which) throws NoSuchMethodException;

    private static boolean arrayContentsEq(Object[] a1, Object[] a2);

    private static Field[] copyFields(Field[] arg);

    private static Method[] copyMethods(Method[] arg);

    private static <U> Constructor<U>[] copyConstructors(Constructor<U>[] arg);

    private native Field[] getDeclaredFields0(boolean publicOnly);

    private native Method[] getDeclaredMethods0(boolean publicOnly);

    private native Constructor<T>[] getDeclaredConstructors0(boolean publicOnly);

    private native Class<?>[] getDeclaredClasses0();

    private String methodToString(String name, Class<?>[] argTypes);

    private static final long serialVersionUID = 3206093459760846163L;

    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];

    public boolean desiredAssertionStatus();

    private static native boolean desiredAssertionStatus0(Class<?> clazz);

    @Pure
    public boolean isEnum(@GuardSatisfied Class<T> this);

    private static ReflectionFactory getReflectionFactory();

    private static ReflectionFactory reflectionFactory;

    @NonNull
    public T @Nullable [] getEnumConstants();

    T[] getEnumConstantsShared();

    private transient volatile T[] enumConstants;

    Map<String, @NonNull T> enumConstantDirectory();

    private transient volatile Map<String, T> enumConstantDirectory;

    @SuppressWarnings("unchecked")
    @HotSpotIntrinsicCandidate
    @PolyNull
    public T cast(@PolyNull Object obj);

    private String cannotCastMsg(Object obj);

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

    private static class AnnotationData {

        final Map<Class<? extends Annotation>, Annotation> annotations;

        final Map<Class<? extends Annotation>, Annotation> declaredAnnotations;

        final int redefinedCount;

        AnnotationData(Map<Class<? extends Annotation>, Annotation> annotations, Map<Class<? extends Annotation>, Annotation> declaredAnnotations, int redefinedCount) {
            this.annotations = annotations;
            this.declaredAnnotations = declaredAnnotations;
            this.redefinedCount = redefinedCount;
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    private transient volatile AnnotationData annotationData;

    private AnnotationData annotationData();

    private AnnotationData createAnnotationData(int classRedefinedCount);

    @SuppressWarnings("UnusedDeclaration")
    private transient volatile AnnotationType annotationType;

    boolean casAnnotationType(AnnotationType oldType, AnnotationType newType);

    AnnotationType getAnnotationType();

    Map<Class<? extends Annotation>, Annotation> getDeclaredAnnotationMap();

    transient ClassValue.ClassValueMap classValueMap;

    public AnnotatedType getAnnotatedSuperclass();

    public AnnotatedType[] getAnnotatedInterfaces();

    private native Class<?> getNestHost0();

    @CallerSensitive
    public Class<?> getNestHost();

    public boolean isNestmateOf(Class<?> c);

    private native Class<?>[] getNestMembers0();

    @CallerSensitive
    public Class<?>[] getNestMembers();
}
