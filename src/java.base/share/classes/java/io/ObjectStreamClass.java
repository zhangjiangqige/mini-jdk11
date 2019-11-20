package java.io;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import jdk.internal.misc.Unsafe;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import jdk.internal.reflect.ReflectionFactory;
import sun.reflect.misc.ReflectUtil;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.JavaSecurityAccess;
import static java.io.ObjectStreamField.*;

@AnnotatedFor({ "index", "lock", "nullness", "signature" })
public class ObjectStreamClass implements Serializable {

    public static final ObjectStreamField[] NO_FIELDS;

    @Nullable
    public static ObjectStreamClass lookup(Class<?> cl);

    public static ObjectStreamClass lookupAny(Class<?> cl);

    @BinaryName
    public String getName();

    public long getSerialVersionUID();

    @CallerSensitive
    @Nullable
    public Class<?> forClass();

    public ObjectStreamField[] getFields();

    @Nullable
    public ObjectStreamField getField(String name);

    @SideEffectFree
    public String toString(@GuardSatisfied ObjectStreamClass this);
}
