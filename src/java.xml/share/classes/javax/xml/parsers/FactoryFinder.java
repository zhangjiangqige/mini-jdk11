package javax.xml.parsers;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.function.Supplier;
import jdk.xml.internal.SecuritySupport;

@AnnotatedFor("nullness")
class FactoryFinder {

    private static final String DEFAULT_PACKAGE = "com.sun.org.apache.xerces.internal";

    private static boolean debug = false;

    private static final Properties cacheProps = new Properties();

    static volatile boolean firstTime = true;

    static {
        try {
            String val = SecuritySupport.getSystemProperty("jaxp.debug");
            debug = val != null && !"false".equals(val);
        } catch (SecurityException se) {
            debug = false;
        }
    }

    private static void dPrint(Supplier<String> msgGen);

    static private Class<?> getProviderClass(String className, @Nullable ClassLoader cl, boolean doFallback, boolean useBSClsLoader) throws ClassNotFoundException;

    static <T> T newInstance(Class<T> type, String className, @Nullable ClassLoader cl, boolean doFallback) throws FactoryConfigurationError;

    static <T> T newInstance(Class<T> type, String className, @Nullable ClassLoader cl, boolean doFallback, boolean useBSClsLoader) throws FactoryConfigurationError;

    static <T> T find(Class<T> type, @Nullable String fallbackClassName) throws FactoryConfigurationError;

    @CFComment("nullness: @Nullable is commented out because of https://github.com/typetools/checker-framework/issues/1863")
    @Nullable
    private static <T> T findServiceProvider(final Class<T> type);
}
