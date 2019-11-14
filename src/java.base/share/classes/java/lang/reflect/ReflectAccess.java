package java.lang.reflect;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.reflect.MethodAccessor;
import jdk.internal.reflect.ConstructorAccessor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class ReflectAccess implements jdk.internal.reflect.LangReflectAccess {

    public Field newField(Class<?> declaringClass, String name, Class<?> type, int modifiers, int slot, String signature, byte[] annotations);

    public Method newMethod(Class<?> declaringClass, String name, Class<?>[] parameterTypes, Class<?> returnType, Class<?>[] checkedExceptions, int modifiers, int slot, String signature, byte[] annotations, byte[] parameterAnnotations, byte[] annotationDefault);

    public <T> Constructor<T> newConstructor(Class<T> declaringClass, Class<?>[] parameterTypes, Class<?>[] checkedExceptions, int modifiers, int slot, String signature, byte[] annotations, byte[] parameterAnnotations);

    public MethodAccessor getMethodAccessor(Method m);

    public void setMethodAccessor(Method m, MethodAccessor accessor);

    public ConstructorAccessor getConstructorAccessor(Constructor<?> c);

    public void setConstructorAccessor(Constructor<?> c, ConstructorAccessor accessor);

    public int getConstructorSlot(Constructor<?> c);

    public String getConstructorSignature(Constructor<?> c);

    public byte[] getConstructorAnnotations(Constructor<?> c);

    public byte[] getConstructorParameterAnnotations(Constructor<?> c);

    public byte[] getExecutableTypeAnnotationBytes(Executable ex);

    public Class<?>[] getExecutableSharedParameterTypes(Executable ex);

    public Method copyMethod(Method arg);

    public Method leafCopyMethod(Method arg);

    public Field copyField(Field arg);

    public <T> Constructor<T> copyConstructor(Constructor<T> arg);

    @SuppressWarnings("unchecked")
    public <T extends AccessibleObject> T getRoot(T obj);
}
