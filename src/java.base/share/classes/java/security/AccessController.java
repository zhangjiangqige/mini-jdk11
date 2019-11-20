package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.security.util.Debug;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class AccessController {

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

    @CallerSensitive
    public static native <T> T doPrivileged(PrivilegedExceptionAction<T> action, AccessControlContext context) throws PrivilegedActionException;

    @CallerSensitive
    public static <T> T doPrivileged(PrivilegedExceptionAction<T> action, AccessControlContext context, Permission... perms) throws PrivilegedActionException;

    @CallerSensitive
    public static <T> T doPrivilegedWithCombiner(PrivilegedExceptionAction<T> action, AccessControlContext context, Permission... perms) throws PrivilegedActionException;

    public static AccessControlContext getContext();

    public static void checkPermission(Permission perm) throws AccessControlException;
}
