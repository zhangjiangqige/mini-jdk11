package java.beans;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import static sun.reflect.misc.ReflectUtil.isPackageAccessible;

@AnnotatedFor({ "nullness" })
final class MethodRef {

    @Nullable
    private String signature;

    @Nullable
    private SoftReference<Method> methodRef;

    @Nullable
    private WeakReference<Class<?>> typeRef;

    void set(@Nullable Method method);

    boolean isSet();

    @Nullable
    Method get();

    @Nullable
    private static Method find(@Nullable Class<?> type, String signature);
}
