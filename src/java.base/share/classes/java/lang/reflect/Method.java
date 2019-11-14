package java.lang.reflect;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.MethodAccessor;
import jdk.internal.reflect.Reflection;
import jdk.internal.vm.annotation.ForceInline;
import sun.reflect.annotation.ExceptionProxy;
import sun.reflect.annotation.TypeNotPresentExceptionProxy;
import sun.reflect.generics.repository.MethodRepository;
import sun.reflect.generics.factory.CoreReflectionFactory;
import sun.reflect.generics.factory.GenericsFactory;
import sun.reflect.generics.scope.MethodScope;
import sun.reflect.annotation.AnnotationType;
import sun.reflect.annotation.AnnotationParser;
import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;
import java.nio.ByteBuffer;
import java.util.StringJoiner;

@AnnotatedFor({ "interning", "lock", "nullness" })
@SuppressWarnings({ "rawtypes" })
public final class Method extends Executable {

    private Class<?> clazz;

    private int slot;

    private String name;

    private Class<?> returnType;

    private Class<?>[] parameterTypes;

    private Class<?>[] exceptionTypes;

    private int modifiers;

    private transient String signature;

    private transient MethodRepository genericInfo;

    private byte[] annotations;

    private byte[] parameterAnnotations;

    private byte[] annotationDefault;

    private volatile MethodAccessor methodAccessor;

    private Method root;

    private String getGenericSignature();

    private GenericsFactory getFactory();

    @Override
    MethodRepository getGenericInfo();

    Method(Class<?> declaringClass, String name, Class<?>[] parameterTypes, Class<?> returnType, Class<?>[] checkedExceptions, int modifiers, int slot, String signature, byte[] annotations, byte[] parameterAnnotations, byte[] annotationDefault) {
        this.clazz = declaringClass;
        this.name = name;
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;
        this.exceptionTypes = checkedExceptions;
        this.modifiers = modifiers;
        this.slot = slot;
        this.signature = signature;
        this.annotations = annotations;
        this.parameterAnnotations = parameterAnnotations;
        this.annotationDefault = annotationDefault;
    }

    Method copy();

    Method leafCopy();

    @Override
    @CallerSensitive
    public void setAccessible(boolean flag);

    @Override
    void checkCanSetAccessible(Class<?> caller);

    @Override
    Method getRoot();

    @Override
    boolean hasGenericInformation();

    @Override
    byte[] getAnnotationBytes();

    @Override
    public Class<?> getDeclaringClass();

    @Override
    @Interned
    public String getName();

    @Override
    public int getModifiers();

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public TypeVariable<Method>[] getTypeParameters();

    @CFComment("lock/nullness: never returns null; returns Void instead")
    public Class<?> getReturnType();

    @CFComment("lock/nullness: never returns null; returns Void instead")
    public Type getGenericReturnType();

    @Override
    Class<?>[] getSharedParameterTypes();

    @Override
    Class<?>[] getSharedExceptionTypes();

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
    public boolean equals(@GuardSatisfied Method this, @GuardSatisfied @Nullable Object obj);

    @Pure
    public int hashCode(@GuardSatisfied Method this);

    @SideEffectFree
    public String toString(@GuardSatisfied Method this);

    @Override
    void specificToStringHeader(StringBuilder sb);

    @Override
    String toShortString();

    @Override
    public String toGenericString();

    @Override
    void specificToGenericStringHeader(StringBuilder sb);

    @CFComment({ "lock/nullness: The method being invoked might be one that requires non-null", "arguments, or might be one that permits null.  We don't know which.", "Therefore, the Nullness Checker should conservatively issue a", "warning whenever null is passed, in order to give a guarantee that", "no nullness-related exception will be thrown by the invoked method." })
    @CallerSensitive
    @ForceInline
    @HotSpotIntrinsicCandidate
    @Nullable
    public Object invoke(Object obj, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    @Pure
    public boolean isBridge(@GuardSatisfied Method this);

    @Pure
    @Override
    public boolean isVarArgs(@GuardSatisfied Method this);

    @Pure
    @Override
    public boolean isSynthetic(@GuardSatisfied Method this);

    public boolean isDefault();

    private MethodAccessor acquireMethodAccessor();

    MethodAccessor getMethodAccessor();

    void setMethodAccessor(MethodAccessor accessor);

    @Nullable
    public Object getDefaultValue();

    @Nullable
    public <T extends @Nullable Annotation> T getAnnotation(Class<T> annotationClass);

    public Annotation[] getDeclaredAnnotations();

    @Override
    public Annotation[][] getParameterAnnotations();

    @Override
    public AnnotatedType getAnnotatedReturnType();

    @Override
    boolean handleParameterNumberMismatch(int resultLength, int numParameters);
}
