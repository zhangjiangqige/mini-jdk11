package java.util;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.loader.BootLoader;
import jdk.internal.loader.ClassLoaders;
import jdk.internal.misc.JavaLangAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.VM;
import jdk.internal.module.ServicesCatalog;
import jdk.internal.module.ServicesCatalog.ServiceProvider;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;

@AnnotatedFor({ "interning", "lock", "nullness" })
@UsesObjectEquals
public final class ServiceLoader<S> implements Iterable<S> {

    public static interface Provider<S> extends Supplier<S> {
    }

    @SideEffectFree
    public Iterator<S> iterator();

    public Stream<Provider<S>> stream();

    @CallerSensitive
    public static <S> ServiceLoader<S> load(Class<S> service, @Nullable ClassLoader loader);

    @CallerSensitive
    public static <S> ServiceLoader<S> load(Class<S> service);

    @CallerSensitive
    public static <S> ServiceLoader<S> loadInstalled(Class<S> service);

    @CallerSensitive
    public static <S> ServiceLoader<S> load(ModuleLayer layer, Class<S> service);

    public Optional<S> findFirst();

    public void reload();

    @SideEffectFree
    public String toString(@GuardSatisfied ServiceLoader<S> this);
}
