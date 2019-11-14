package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.security.util.Debug;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class AccessController {

    private AccessController() {
    }

    @CallerSensitive
    public static native <T> T doPrivileged(PrivilegedAction<T> action);

    @CallerSensitive
    public static <T> T doPrivilegedWithCombiner(PrivilegedAction<T> action);

    @CallerSensitive
    public static native <T> T doPrivileged(PrivilegedAction<T> action, AccessControlContext context);

    @CallerSensitive
    public static <T> T doPrivileged(PrivilegedAction<T> action, AccessControlContext context, Permission... perms);

    @CallerSensitive
    public static <T> T doPrivilegedWithCombiner(PrivilegedAction<T> action, AccessControlContext context, Permission... perms);

    @CallerSensitive
    public static native <T> T doPrivileged(PrivilegedExceptionAction<T> action) throws PrivilegedActionException;

    @CallerSensitive
    public static <T> T doPrivilegedWithCombiner(PrivilegedExceptionAction<T> action) throws PrivilegedActionException;

    private static AccessControlContext preserveCombiner(DomainCombiner combiner, Class<?> caller);

    private static AccessControlContext createWrapper(DomainCombiner combiner, Class<?> caller, AccessControlContext parent, AccessControlContext context, Permission[] perms);

    private static class AccHolder {

        static final AccessControlContext innocuousAcc = new AccessControlContext(new ProtectionDomain[] { new ProtectionDomain(null, null) });
    }

    private static AccessControlContext getInnocuousAcc();

    private static ProtectionDomain getCallerPD(final Class<?> caller);

    @CallerSensitive
    public static native <T> T doPrivileged(PrivilegedExceptionAction<T> action, AccessControlContext context) throws PrivilegedActionException;

    @CallerSensitive
    public static <T> T doPrivileged(PrivilegedExceptionAction<T> action, AccessControlContext context, Permission... perms) throws PrivilegedActionException;

    @CallerSensitive
    public static <T> T doPrivilegedWithCombiner(PrivilegedExceptionAction<T> action, AccessControlContext context, Permission... perms) throws PrivilegedActionException;

    private static native AccessControlContext getStackAccessControlContext();

    static native AccessControlContext getInheritedAccessControlContext();

    public static AccessControlContext getContext();

    public static void checkPermission(Permission perm) throws AccessControlException;
}
