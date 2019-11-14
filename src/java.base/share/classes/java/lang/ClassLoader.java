package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.checker.signature.qual.FullyQualifiedName;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.InputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.AccessControlContext;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.loader.BuiltinClassLoader;
import jdk.internal.perf.PerfCounter;
import jdk.internal.loader.BootLoader;
import jdk.internal.loader.ClassLoaders;
import jdk.internal.misc.Unsafe;
import jdk.internal.misc.VM;
import jdk.internal.ref.CleanerFactory;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "interning", "lock", "nullness", "signature" })
@UsesObjectEquals
public abstract class ClassLoader {

    private static native void registerNatives();

    static {
        registerNatives();
    }

    @Nullable
    private final ClassLoader parent;

    private final String name;

    private final Module unnamedModule;

    private final String nameAndId;

    private static class ParallelLoaders {

        private ParallelLoaders() {
        }

        private static final Set<Class<? extends ClassLoader>> loaderTypes = Collections.newSetFromMap(new WeakHashMap<>());

        static {
            synchronized (loaderTypes) {
                loaderTypes.add(ClassLoader.class);
            }
        }

        static boolean register(Class<? extends ClassLoader> c);

        static boolean isRegistered(Class<? extends ClassLoader> c);
    }

    @Nullable
    private final ConcurrentHashMap<String, Object> parallelLockMap;

    private final Map<String, Certificate[]> package2certs;

    private static final Certificate[] nocerts = new Certificate[0];

    private final Vector<Class<?>> classes = new Vector<>();

    private final ProtectionDomain defaultDomain = new ProtectionDomain(new CodeSource(null, (Certificate[]) null), null, this, null);

    void addClass(Class<?> c);

    private final ConcurrentHashMap<String, NamedPackage> packages = new ConcurrentHashMap<>();

    private NamedPackage getNamedPackage(String pn, Module m);

    @Nullable
    private static Void checkCreateClassLoader();

    private static Void checkCreateClassLoader(String name);

    private ClassLoader(Void unused, String name, ClassLoader parent) {
        this.name = name;
        this.parent = parent;
        this.unnamedModule = new Module(this);
        if (ParallelLoaders.isRegistered(this.getClass())) {
            parallelLockMap = new ConcurrentHashMap<>();
            package2certs = new ConcurrentHashMap<>();
            assertionLock = new Object();
        } else {
            parallelLockMap = null;
            package2certs = new Hashtable<>();
            assertionLock = this;
        }
        this.nameAndId = nameAndId(this);
    }

    private static String nameAndId(ClassLoader ld);

    protected ClassLoader(String name, ClassLoader parent) {
        this(checkCreateClassLoader(name), name, parent);
    }

    protected ClassLoader(@Nullable ClassLoader parent) {
        this(checkCreateClassLoader(), null, parent);
    }

    protected ClassLoader() {
        this(checkCreateClassLoader(), null, getSystemClassLoader());
    }

    public String getName();

    final String name();

    public Class<?> loadClass(@BinaryName String name) throws ClassNotFoundException;

    protected Class<?> loadClass(@BinaryName String name, boolean resolve) throws ClassNotFoundException;

    final Class<?> loadClass(Module module, String name);

    protected Object getClassLoadingLock(String className);

    private void checkPackageAccess(Class<?> cls, ProtectionDomain pd);

    protected Class<?> findClass(@BinaryName String name) throws ClassNotFoundException;

    protected Class<?> findClass(String moduleName, String name);

    @Deprecated(since = "1.1")
    @SuppressWarnings("signature")
    protected final Class<?> defineClass(byte[] b, int off, int len) throws ClassFormatError;

    protected final Class<?> defineClass(@Nullable @BinaryName String name, byte[] b, int off, int len) throws ClassFormatError;

    private ProtectionDomain preDefineClass(@Nullable String name, @Nullable ProtectionDomain pd);

    @Nullable
    private String defineClassSourceLocation(ProtectionDomain pd);

    private void postDefineClass(Class<?> c, ProtectionDomain pd);

    protected final Class<?> defineClass(@Nullable @BinaryName String name, byte[] b, int off, int len, @Nullable ProtectionDomain protectionDomain) throws ClassFormatError;

    protected final Class<?> defineClass(@Nullable String name, java.nio.ByteBuffer b, @Nullable ProtectionDomain protectionDomain) throws ClassFormatError;

    static native Class<?> defineClass1(ClassLoader loader, String name, byte[] b, int off, int len, ProtectionDomain pd, String source);

    static native Class<?> defineClass2(ClassLoader loader, String name, java.nio.ByteBuffer b, int off, int len, ProtectionDomain pd, String source);

    private boolean checkName(@Nullable String name);

    private void checkCerts(String name, @Nullable CodeSource cs);

    private boolean compareCerts(Certificate[] pcerts, Certificate @Nullable [] certs);

    protected final void resolveClass(Class<?> c);

    protected final Class<?> findSystemClass(@BinaryName String name) throws ClassNotFoundException;

    @Nullable
    Class<?> findBootstrapClassOrNull(String name);

    @Nullable
    private native Class<?> findBootstrapClass(String name);

    @Nullable
    protected final Class<?> findLoadedClass(@BinaryName String name);

    private final native Class<?> findLoadedClass0(String name);

    protected final void setSigners(Class<?> c, Object[] signers);

    protected URL findResource(String moduleName, String name) throws IOException;

    @Nullable
    public URL getResource(String name);

    public Enumeration<URL> getResources(String name) throws IOException;

    public Stream<URL> resources(String name);

    @Nullable
    protected URL findResource(String name);

    protected Enumeration<URL> findResources(String name) throws IOException;

    @CallerSensitive
    protected static boolean registerAsParallelCapable();

    public final boolean isRegisteredAsParallelCapable();

    @Nullable
    public static URL getSystemResource(String name);

    public static Enumeration<URL> getSystemResources(String name) throws IOException;

    @Nullable
    public InputStream getResourceAsStream(String name);

    @Nullable
    public static InputStream getSystemResourceAsStream(String name);

    @CallerSensitive
    @Nullable
    public final ClassLoader getParent();

    public final Module getUnnamedModule();

    @CallerSensitive
    public static ClassLoader getPlatformClassLoader();

    @CallerSensitive
    public static ClassLoader getSystemClassLoader();

    static ClassLoader getBuiltinPlatformClassLoader();

    static ClassLoader getBuiltinAppClassLoader();

    static synchronized ClassLoader initSystemClassLoader();

    boolean isAncestor(ClassLoader cl);

    private static boolean needsClassLoaderPermissionCheck(@Nullable ClassLoader from, ClassLoader to);

    @Nullable
    static ClassLoader getClassLoader(Class<?> caller);

    static void checkClassLoaderPermission(ClassLoader cl, Class<?> caller);

    @Nullable
    private static volatile ClassLoader scl;

    Package definePackage(Class<?> c);

    Package definePackage(String name, Module m);

    private Package toPackage(String name, NamedPackage p, Module m);

    protected Package definePackage(@FullyQualifiedName String name, @Nullable String specTitle, @Nullable String specVersion, @Nullable String specVendor, @Nullable String implTitle, @Nullable String implVersion, @Nullable String implVendor, @Nullable URL sealBase);

    public final Package getDefinedPackage(String name);

    public final Package[] getDefinedPackages();

    @Deprecated(since = "9")
    @Nullable
    protected Package getPackage(String name);

    @CFComment({ "nullness: The size of array passed to toArray", "method is of exact same size as of the map for which toArray method is invoked" })
    @SuppressWarnings({ "nullness:return.type.incompatible" })
    protected Package[] getPackages();

    Stream<Package> packages();

    @Nullable
    protected String findLibrary(String libname);

    static class NativeLibrary {

        final Class<?> fromClass;

        final String name;

        final boolean isBuiltin;

        long handle;

        int jniVersion;

        native boolean load0(String name, boolean isBuiltin);

        native long findEntry(String name);

        NativeLibrary(Class<?> fromClass, String name, boolean isBuiltin) {
            this.name = name;
            this.fromClass = fromClass;
            this.isBuiltin = isBuiltin;
        }

        boolean load();

        static boolean loadLibrary(Class<?> fromClass, String name, boolean isBuiltin);

        static Class<?> getFromClass();

        static Deque<NativeLibrary> nativeLibraryContext = new ArrayDeque<>(8);

        static class Unloader implements Runnable {

            static final NativeLibrary UNLOADER = new NativeLibrary(null, "dummy", false);

            final String name;

            final long handle;

            final boolean isBuiltin;

            Unloader(String name, long handle, boolean isBuiltin) {
                if (handle == 0) {
                    throw new IllegalArgumentException("Invalid handle for native library " + name);
                }
                this.name = name;
                this.handle = handle;
                this.isBuiltin = isBuiltin;
            }

            @Override
            public void run();
        }

        static native void unload(String name, boolean isBuiltin, long handle);
    }

    private static String @Nullable [] usr_paths;

    private static String @Nullable [] sys_paths;

    private static String[] initializePath(String propName);

    @CFComment({ "nulness: usr_paths and sys_paths are initialized", "by intializePath method if they are null" })
    @SuppressWarnings({ "nullness:dereference.of.nullable" })
    static void loadLibrary(Class<?> fromClass, String name, boolean isAbsolute);

    private static native String findBuiltinLib(String name);

    private static boolean loadLibrary0(Class<?> fromClass, final File file);

    private static long findNative(@Nullable ClassLoader loader, String entryName);

    private static final Set<String> loadedLibraryNames = new HashSet<>();

    private static volatile Map<String, NativeLibrary> systemNativeLibraries;

    private volatile Map<String, NativeLibrary> nativeLibraries;

    private static Map<String, NativeLibrary> systemNativeLibraries();

    private Map<String, NativeLibrary> nativeLibraries();

    final Object assertionLock;

    private boolean defaultAssertionStatus = false;

    @Nullable
    private Map<@Nullable String, Boolean> packageAssertionStatus = null;

    @Nullable
    Map<String, Boolean> classAssertionStatus = null;

    public void setDefaultAssertionStatus(boolean enabled);

    public void setPackageAssertionStatus(@Nullable String packageName, boolean enabled);

    public void setClassAssertionStatus(String className, boolean enabled);

    public void clearAssertionStatus();

    @RequiresNonNull({ "classAssertionStatus", "packageAssertionStatus" })
    boolean desiredAssertionStatus(String className);

    @SuppressWarnings({ "contracts.postcondition.not.satisfied" })
    @EnsuresNonNull({ "classAssertionStatus", "packageAssertionStatus" })
    private void initializeJavaAssertionMaps();

    private static native AssertionStatusDirectives retrieveDirectives();

    ConcurrentHashMap<?, ?> createOrGetClassLoaderValueMap();

    private volatile ConcurrentHashMap<?, ?> classLoaderValueMap;

    private boolean trySetObjectField(String name, Object obj);
}

final class CompoundEnumeration<E> implements Enumeration<E> {

    private final Enumeration<E>[] enums;

    private int index;

    public CompoundEnumeration(Enumeration<E>[] enums) {
        this.enums = enums;
    }

    private boolean next();

    public boolean hasMoreElements();

    public E nextElement();
}
