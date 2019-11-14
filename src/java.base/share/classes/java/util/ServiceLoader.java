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

    private final Class<S> service;

    private final String serviceName;

    private final ModuleLayer layer;

    private final ClassLoader loader;

    private final AccessControlContext acc;

    private Iterator<Provider<S>> lookupIterator1;

    private final List<S> instantiatedProviders = new ArrayList<>();

    private Iterator<Provider<S>> lookupIterator2;

    private final List<Provider<S>> loadedProviders = new ArrayList<>();

    private boolean loadedAllProviders;

    private int reloadCount;

    private static JavaLangAccess LANG_ACCESS;

    static {
        LANG_ACCESS = SharedSecrets.getJavaLangAccess();
    }

    public static interface Provider<S> extends Supplier<S> {

        Class<? extends S> type();

        @Override
        S get();
    }

    private ServiceLoader(Class<?> caller, ModuleLayer layer, Class<S> svc) {
        Objects.requireNonNull(caller);
        Objects.requireNonNull(layer);
        Objects.requireNonNull(svc);
        checkCaller(caller, svc);
        this.service = svc;
        this.serviceName = svc.getName();
        this.layer = layer;
        this.loader = null;
        this.acc = (System.getSecurityManager() != null) ? AccessController.getContext() : null;
    }

    private ServiceLoader(Class<?> caller, Class<S> svc, ClassLoader cl) {
        Objects.requireNonNull(svc);
        if (VM.isBooted()) {
            checkCaller(caller, svc);
            if (cl == null) {
                cl = ClassLoader.getSystemClassLoader();
            }
        } else {
            Module callerModule = caller.getModule();
            Module base = Object.class.getModule();
            Module svcModule = svc.getModule();
            if (callerModule != base || svcModule != base) {
                fail(svc, "not accessible to " + callerModule + " during VM init");
            }
            cl = null;
        }
        this.service = svc;
        this.serviceName = svc.getName();
        this.layer = null;
        this.loader = cl;
        this.acc = (System.getSecurityManager() != null) ? AccessController.getContext() : null;
    }

    private ServiceLoader(Module callerModule, Class<S> svc, ClassLoader cl) {
        if (!callerModule.canUse(svc)) {
            fail(svc, callerModule + " does not declare `uses`");
        }
        this.service = Objects.requireNonNull(svc);
        this.serviceName = svc.getName();
        this.layer = null;
        this.loader = cl;
        this.acc = (System.getSecurityManager() != null) ? AccessController.getContext() : null;
    }

    private static void checkCaller(Class<?> caller, Class<?> svc);

    private static void fail(Class<?> service, String msg, Throwable cause) throws ServiceConfigurationError;

    private static void fail(Class<?> service, String msg) throws ServiceConfigurationError;

    private static void fail(Class<?> service, URL u, int line, String msg) throws ServiceConfigurationError;

    private boolean inExplicitModule(Class<?> clazz);

    private Method findStaticProviderMethod(Class<?> clazz);

    private Constructor<?> getConstructor(Class<?> clazz);

    private static class ProviderImpl<S> implements Provider<S> {

        final Class<S> service;

        final Class<? extends S> type;

        final Method factoryMethod;

        final Constructor<? extends S> ctor;

        final AccessControlContext acc;

        ProviderImpl(Class<S> service, Class<? extends S> type, Method factoryMethod, AccessControlContext acc) {
            this.service = service;
            this.type = type;
            this.factoryMethod = factoryMethod;
            this.ctor = null;
            this.acc = acc;
        }

        ProviderImpl(Class<S> service, Class<? extends S> type, Constructor<? extends S> ctor, AccessControlContext acc) {
            this.service = service;
            this.type = type;
            this.factoryMethod = null;
            this.ctor = ctor;
            this.acc = acc;
        }

        @Override
        public Class<? extends S> type();

        @Override
        public S get();

        private S invokeFactoryMethod();

        private S newInstance();

        @Override
        public int hashCode();

        @Override
        public boolean equals(Object ob);
    }

    private Provider<S> loadProvider(ServiceProvider provider);

    private final class LayerLookupIterator<T> implements Iterator<Provider<T>> {

        Deque<ModuleLayer> stack = new ArrayDeque<>();

        Set<ModuleLayer> visited = new HashSet<>();

        Iterator<ServiceProvider> iterator;

        Provider<T> nextProvider;

        ServiceConfigurationError nextError;

        LayerLookupIterator() {
            visited.add(layer);
            stack.push(layer);
        }

        private Iterator<ServiceProvider> providers(ModuleLayer layer);

        @Override
        public boolean hasNext();

        @Override
        public Provider<T> next();
    }

    private final class ModuleServicesLookupIterator<T> implements Iterator<Provider<T>> {

        ClassLoader currentLoader;

        Iterator<ServiceProvider> iterator;

        Provider<T> nextProvider;

        ServiceConfigurationError nextError;

        ModuleServicesLookupIterator() {
            this.currentLoader = loader;
            this.iterator = iteratorFor(loader);
        }

        private List<ServiceProvider> providers(ModuleLayer layer);

        private ClassLoader loaderFor(Module module);

        private Iterator<ServiceProvider> iteratorFor(ClassLoader loader);

        @Override
        public boolean hasNext();

        @Override
        public Provider<T> next();
    }

    private final class LazyClassPathLookupIterator<T> implements Iterator<Provider<T>> {

        static final String PREFIX = "META-INF/services/";

        Set<String> providerNames = new HashSet<>();

        Enumeration<URL> configs;

        Iterator<String> pending;

        Provider<T> nextProvider;

        ServiceConfigurationError nextError;

        LazyClassPathLookupIterator() {
        }

        private int parseLine(URL u, BufferedReader r, int lc, Set<String> names) throws IOException;

        private Iterator<String> parse(URL u);

        private Class<?> nextProviderClass();

        @SuppressWarnings("unchecked")
        private boolean hasNextService();

        private Provider<T> nextService();

        @Override
        public boolean hasNext();

        @Override
        public Provider<T> next();
    }

    private Iterator<Provider<S>> newLookupIterator();

    @SideEffectFree
    public Iterator<S> iterator();

    public Stream<Provider<S>> stream();

    private class ProviderSpliterator<T> implements Spliterator<Provider<T>> {

        final int expectedReloadCount = ServiceLoader.this.reloadCount;

        final Iterator<Provider<T>> iterator;

        int index;

        ProviderSpliterator(Iterator<Provider<T>> iterator) {
            this.iterator = iterator;
        }

        @Override
        public Spliterator<Provider<T>> trySplit();

        @Override
        @SuppressWarnings("unchecked")
        public boolean tryAdvance(Consumer<? super Provider<T>> action);

        @Override
        public int characteristics();

        @Override
        public long estimateSize();
    }

    static <S> ServiceLoader<S> load(Class<S> service, ClassLoader loader, Module callerModule);

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
