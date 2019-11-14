package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.i18n.qual.LocalizableKey;
import org.checkerframework.checker.i18n.qual.Localized;
import org.checkerframework.checker.i18nformatter.qual.I18nMakeFormat;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.propkey.qual.PropertyKey;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.jar.JarEntry;
import java.util.spi.ResourceBundleControlProvider;
import java.util.spi.ResourceBundleProvider;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jdk.internal.loader.BootLoader;
import jdk.internal.misc.JavaUtilResourceBundleAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import sun.security.action.GetPropertyAction;
import sun.util.locale.BaseLocale;
import sun.util.locale.LocaleObjectCache;
import static sun.security.util.SecurityConstants.GET_CLASSLOADER_PERMISSION;

@AnnotatedFor({ "i18n", "i18nformatter", "index", "lock", "nullness", "propkey", "signature" })
public abstract class ResourceBundle {

    private static final int INITIAL_CACHE_SIZE = 32;

    static {
        SharedSecrets.setJavaUtilResourceBundleAccess(new JavaUtilResourceBundleAccess() {

            @Override
            public void setParent(ResourceBundle bundle, ResourceBundle parent) {
                bundle.setParent(parent);
            }

            @Override
            public ResourceBundle getParent(ResourceBundle bundle) {
                return bundle.parent;
            }

            @Override
            public void setLocale(ResourceBundle bundle, Locale locale) {
                bundle.locale = locale;
            }

            @Override
            public void setName(ResourceBundle bundle, String name) {
                bundle.name = name;
            }

            @Override
            public ResourceBundle getBundle(String baseName, Locale locale, Module module) {
                return getBundleImpl(module, module, baseName, locale, getDefaultControl(module, baseName));
            }

            @Override
            public ResourceBundle newResourceBundle(Class<? extends ResourceBundle> bundleClass) {
                return ResourceBundleProviderHelper.newResourceBundle(bundleClass);
            }
        });
    }

    private static final ResourceBundle NONEXISTENT_BUNDLE = new ResourceBundle() {

        public Enumeration<String> getKeys() {
            return null;
        }

        protected Object handleGetObject(String key) {
            return null;
        }

        public String toString() {
            return "NONEXISTENT_BUNDLE";
        }
    };

    private static final ConcurrentMap<CacheKey, BundleReference> cacheList = new ConcurrentHashMap<>(INITIAL_CACHE_SIZE);

    private static final ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

    public String getBaseBundleName();

    protected ResourceBundle parent = null;

    private Locale locale = null;

    private String name;

    private volatile boolean expired;

    private volatile CacheKey cacheKey;

    private volatile Set<String> keySet;

    public ResourceBundle() {
    }

    @I18nMakeFormat
    @Localized
    public final String getString(@LocalizableKey @PropertyKey String key);

    @Localized
    public final String[] getStringArray(@LocalizableKey @PropertyKey String key);

    @Localized
    public final Object getObject(@LocalizableKey @PropertyKey String key);

    public Locale getLocale();

    private static ClassLoader getLoader(Module module);

    private static ClassLoader getLoaderForControl(Module module);

    protected void setParent(ResourceBundle parent);

    private static final class CacheKey {

        private final String name;

        private volatile Locale locale;

        private final KeyElementReference<Module> moduleRef;

        private final KeyElementReference<Module> callerRef;

        private final int modulesHash;

        private volatile String format;

        private volatile long loadTime;

        private volatile long expirationTime;

        private volatile Throwable cause;

        private volatile ServiceLoader<ResourceBundleProvider> providers;

        private volatile boolean providersChecked;

        private volatile Boolean callerHasProvider;

        CacheKey(String baseName, Locale locale, Module module, Module caller) {
            Objects.requireNonNull(module);
            Objects.requireNonNull(caller);
            this.name = baseName;
            this.locale = locale;
            this.moduleRef = new KeyElementReference<>(module, referenceQueue, this);
            this.callerRef = new KeyElementReference<>(caller, referenceQueue, this);
            this.modulesHash = module.hashCode() ^ caller.hashCode();
        }

        CacheKey(CacheKey src) {
            this.moduleRef = new KeyElementReference<>(Objects.requireNonNull(src.getModule()), referenceQueue, this);
            this.callerRef = new KeyElementReference<>(Objects.requireNonNull(src.getCallerModule()), referenceQueue, this);
            this.name = src.name;
            this.locale = src.locale;
            this.modulesHash = src.modulesHash;
            this.format = src.format;
            this.loadTime = src.loadTime;
            this.expirationTime = src.expirationTime;
        }

        String getName();

        Locale getLocale();

        CacheKey setLocale(Locale locale);

        Module getModule();

        Module getCallerModule();

        ServiceLoader<ResourceBundleProvider> getProviders();

        boolean hasProviders();

        boolean callerHasProvider();

        @Override
        public boolean equals(Object other);

        @Override
        public int hashCode();

        String getFormat();

        void setFormat(String format);

        private void setCause(Throwable cause);

        private Throwable getCause();

        @Override
        public String toString();
    }

    private static interface CacheKeyReference {

        public CacheKey getCacheKey();
    }

    private static class KeyElementReference<T> extends WeakReference<T> implements CacheKeyReference {

        private final CacheKey cacheKey;

        KeyElementReference(T referent, ReferenceQueue<Object> q, CacheKey key) {
            super(referent, q);
            cacheKey = key;
        }

        @Override
        public CacheKey getCacheKey();
    }

    private static class BundleReference extends SoftReference<ResourceBundle> implements CacheKeyReference {

        private final CacheKey cacheKey;

        BundleReference(ResourceBundle referent, ReferenceQueue<Object> q, CacheKey key) {
            super(referent, q);
            cacheKey = key;
        }

        @Override
        public CacheKey getCacheKey();
    }

    @CallerSensitive
    public static final ResourceBundle getBundle(@BinaryName String baseName);

    @CallerSensitive
    public static final ResourceBundle getBundle(@BinaryName String baseName, Control control);

    @CallerSensitive
    public static final ResourceBundle getBundle(@BinaryName String baseName, Locale locale);

    @CallerSensitive
    public static ResourceBundle getBundle(String baseName, Module module);

    @CallerSensitive
    public static ResourceBundle getBundle(String baseName, Locale targetLocale, Module module);

    @CallerSensitive
    public static final ResourceBundle getBundle(@BinaryName String baseName, Locale targetLocale, Control control);

    @CallerSensitive
    public static ResourceBundle getBundle(@BinaryName String baseName, Locale locale, ClassLoader loader);

    @CallerSensitive
    public static ResourceBundle getBundle(@BinaryName String baseName, Locale targetLocale, ClassLoader loader, Control control);

    private static Control getDefaultControl(Class<?> caller, String baseName);

    private static Control getDefaultControl(Module targetModule, String baseName);

    private static class ResourceBundleControlProviderHolder {

        private static final PrivilegedAction<List<ResourceBundleControlProvider>> pa = () -> {
            return Collections.unmodifiableList(ServiceLoader.load(ResourceBundleControlProvider.class, ClassLoader.getSystemClassLoader()).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList()));
        };

        private static final List<ResourceBundleControlProvider> CONTROL_PROVIDERS = AccessController.doPrivileged(pa);

        private static Control getControl(String baseName);
    }

    private static void checkNamedModule(Class<?> caller);

    private static ResourceBundle getBundleImpl(String baseName, Locale locale, Class<?> caller, Control control);

    private static ResourceBundle getBundleImpl(String baseName, Locale locale, Class<?> caller, ClassLoader loader, Control control);

    private static ResourceBundle getBundleFromModule(Class<?> caller, Module module, String baseName, Locale locale, Control control);

    private static ResourceBundle getBundleImpl(Module callerModule, Module module, String baseName, Locale locale, Control control);

    private static boolean checkList(List<?> a);

    private static ResourceBundle findBundle(Module callerModule, Module module, CacheKey cacheKey, List<Locale> candidateLocales, List<String> formats, int index, Control control, ResourceBundle baseBundle);

    private static final String UNKNOWN_FORMAT = "";

    private static ResourceBundle loadBundle(CacheKey cacheKey, List<String> formats, Control control, Module module, Module callerModule);

    private static ServiceLoader<ResourceBundleProvider> getServiceLoader(Module module, String baseName);

    private static Class<ResourceBundleProvider> getResourceBundleProviderType(String baseName, ClassLoader loader);

    private static ResourceBundle loadBundleFromProviders(String baseName, Locale locale, ServiceLoader<ResourceBundleProvider> providers, CacheKey cacheKey);

    private static ResourceBundle loadBundle(CacheKey cacheKey, List<String> formats, Control control, boolean reload);

    private static boolean isValidBundle(ResourceBundle bundle);

    private static boolean hasValidParentChain(ResourceBundle bundle);

    private static void throwMissingResourceException(String baseName, Locale locale, Throwable cause);

    private static ResourceBundle findBundleInCache(CacheKey cacheKey, Control control);

    private static ResourceBundle putBundleInCache(CacheKey cacheKey, ResourceBundle bundle, Control control);

    private static void setExpirationTime(CacheKey cacheKey, Control control);

    @CallerSensitive
    public static final void clearCache();

    public static final void clearCache(ClassLoader loader);

    protected abstract Object handleGetObject(String key);

    @SideEffectFree
    public abstract Enumeration<String> getKeys(@GuardSatisfied ResourceBundle this);

    @Pure
    @EnsuresKeyForIf(result = true, expression = "#1", map = "this")
    public boolean containsKey(@GuardSatisfied ResourceBundle this, String key);

    @SideEffectFree
    public Set<@KeyFor("this") @LocalizableKey @PropertyKey String> keySet(@GuardSatisfied ResourceBundle this);

    protected Set<String> handleKeySet();

    public static class Control {

        public static final List<String> FORMAT_DEFAULT = List.of("java.class", "java.properties");

        public static final List<String> FORMAT_CLASS = List.of("java.class");

        public static final List<String> FORMAT_PROPERTIES = List.of("java.properties");

        public static final long TTL_DONT_CACHE = -1;

        public static final long TTL_NO_EXPIRATION_CONTROL = -2;

        private static final Control INSTANCE = new Control();

        protected Control() {
        }

        public static final Control getControl(List<String> formats);

        public static final Control getNoFallbackControl(List<String> formats);

        public List<String> getFormats(String baseName);

        public List<Locale> getCandidateLocales(String baseName, Locale locale);

        private static final CandidateListCache CANDIDATES_CACHE = new CandidateListCache();

        private static class CandidateListCache extends LocaleObjectCache<BaseLocale, List<Locale>> {

            protected List<Locale> createObject(BaseLocale base);

            private static List<Locale> getDefaultList(String language, String script, String region, String variant);
        }

        public Locale getFallbackLocale(String baseName, Locale locale);

        public ResourceBundle newBundle(@BinaryName String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException;

        @NonNegative
        public long getTimeToLive(String baseName, Locale locale);

        public boolean needsReload(@BinaryName String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle, long loadTime);

        @BinaryName
        public String toBundleName(@BinaryName String baseName, Locale locale);

        public final String toResourceName(String bundleName, String suffix);

        private String toResourceName0(String bundleName, String suffix);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void uncheckedThrow(Throwable t) throws T;

    private static class SingleFormatControl extends Control {

        private static final Control PROPERTIES_ONLY = new SingleFormatControl(FORMAT_PROPERTIES);

        private static final Control CLASS_ONLY = new SingleFormatControl(FORMAT_CLASS);

        private final List<String> formats;

        protected SingleFormatControl(List<String> formats) {
            this.formats = formats;
        }

        public List<String> getFormats(String baseName);
    }

    private static final class NoFallbackControl extends SingleFormatControl {

        private static final Control NO_FALLBACK = new NoFallbackControl(FORMAT_DEFAULT);

        private static final Control PROPERTIES_ONLY_NO_FALLBACK = new NoFallbackControl(FORMAT_PROPERTIES);

        private static final Control CLASS_ONLY_NO_FALLBACK = new NoFallbackControl(FORMAT_CLASS);

        protected NoFallbackControl(List<String> formats) {
            super(formats);
        }

        public Locale getFallbackLocale(String baseName, Locale locale);
    }

    private static class ResourceBundleProviderHelper {

        static ResourceBundle newResourceBundle(Class<? extends ResourceBundle> bundleClass);

        static ResourceBundle loadResourceBundle(Module callerModule, Module module, String baseName, Locale locale);

        static boolean isAccessible(Module callerModule, Module module, String pn);

        static ResourceBundle loadPropertyResourceBundle(Module callerModule, Module module, String baseName, Locale locale) throws IOException;

        private static String toPackageName(String bundleName);
    }

    private static final boolean TRACE_ON = Boolean.valueOf(GetPropertyAction.privilegedGetProperty("resource.bundle.debug", "false"));

    private static void trace(String format, Object... params);
}
