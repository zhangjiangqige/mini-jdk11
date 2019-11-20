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

    public String getBaseBundleName();

    protected ResourceBundle parent;

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

    protected void setParent(ResourceBundle parent);

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

        public static final List<String> FORMAT_DEFAULT;

        public static final List<String> FORMAT_CLASS;

        public static final List<String> FORMAT_PROPERTIES;

        public static final long TTL_DONT_CACHE;

        public static final long TTL_NO_EXPIRATION_CONTROL;

        protected Control() {
        }

        public static final Control getControl(List<String> formats);

        public static final Control getNoFallbackControl(List<String> formats);

        public List<String> getFormats(String baseName);

        public List<Locale> getCandidateLocales(String baseName, Locale locale);

        public Locale getFallbackLocale(String baseName, Locale locale);

        public ResourceBundle newBundle(@BinaryName String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException;

        @NonNegative
        public long getTimeToLive(String baseName, Locale locale);

        public boolean needsReload(@BinaryName String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle, long loadTime);

        @BinaryName
        public String toBundleName(@BinaryName String baseName, Locale locale);

        public final String toResourceName(String bundleName, String suffix);
    }
}
