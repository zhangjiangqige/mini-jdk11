package java.util.logging;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import jdk.internal.misc.JavaUtilResourceBundleAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import static jdk.internal.logger.DefaultLoggerFinder.isSystem;

@CFComment({ "lock: inherited methods", "public boolean isEmpty(@GuardSatisfied LinkedList<E> this) { throw new RuntimeException(\"skeleton method\"); }", "public boolean containsAll(@GuardSatisfied LinkedList<E> this, Collection<?> c);", "public int hashCode(@GuardSatisfied LinkedList<E> this);", "public boolean equals(@GuardSatisfied LinkedList<E> this, Object o);" })
@AnnotatedFor({ "index", "interning", "lock", "signature" })
@UsesObjectEquals
public class Logger {

    private static final Handler[] emptyHandlers = new Handler[0];

    private static final int offValue = Level.OFF.intValue();

    static final String SYSTEM_LOGGER_RB_NAME = "sun.util.logging.resources.logging";

    private static final class LoggerBundle {

        final String resourceBundleName;

        final ResourceBundle userBundle;

        private LoggerBundle(String resourceBundleName, ResourceBundle bundle) {
            this.resourceBundleName = resourceBundleName;
            this.userBundle = bundle;
        }

        boolean isSystemBundle();

        static LoggerBundle get(String name, ResourceBundle bundle);
    }

    private static final LoggerBundle SYSTEM_BUNDLE = new LoggerBundle(SYSTEM_LOGGER_RB_NAME, null);

    private static final LoggerBundle NO_RESOURCE_BUNDLE = new LoggerBundle(null, null);

    private static final class RbAccess {

        static final JavaUtilResourceBundleAccess RB_ACCESS = SharedSecrets.getJavaUtilResourceBundleAccess();
    }

    private static final class ConfigurationData {

        private volatile ConfigurationData delegate;

        volatile boolean useParentHandlers;

        volatile Filter filter;

        volatile Level levelObject;

        volatile int levelValue;

        final CopyOnWriteArrayList<Handler> handlers = new CopyOnWriteArrayList<>();

        ConfigurationData() {
            delegate = this;
            useParentHandlers = true;
            levelValue = Level.INFO.intValue();
        }

        void setUseParentHandlers(boolean flag);

        void setFilter(Filter f);

        void setLevelObject(Level l);

        void setLevelValue(int v);

        void addHandler(Handler h);

        void removeHandler(Handler h);

        ConfigurationData merge(Logger systemPeer);
    }

    private volatile ConfigurationData config;

    @Nullable
    private volatile LogManager manager;

    @Nullable
    private String name;

    private volatile LoggerBundle loggerBundle = NO_RESOURCE_BUNDLE;

    private boolean anonymous;

    @Nullable
    private ResourceBundle catalog;

    @Nullable
    private String catalogName;

    @Nullable
    private Locale catalogLocale;

    private static final Object treeLock = new Object();

    @Nullable
    private volatile Logger parent;

    @Nullable
    private ArrayList<LogManager.LoggerWeakRef> kids;

    private WeakReference<Module> callerModuleRef;

    private final boolean isSystemLogger;

    @Interned
    public static final String GLOBAL_LOGGER_NAME = "global";

    @Pure
    public static final Logger getGlobal();

    @Deprecated
    public static final Logger global = new Logger(GLOBAL_LOGGER_NAME);

    protected Logger(@Nullable String name, @Nullable @BinaryName String resourceBundleName) {
        this(name, resourceBundleName, null, LogManager.getLogManager(), false);
    }

    Logger(String name, String resourceBundleName, Module caller, LogManager manager, boolean isSystemLogger) {
        this.manager = manager;
        this.isSystemLogger = isSystemLogger;
        this.config = new ConfigurationData();
        this.name = name;
        setupResourceInfo(resourceBundleName, caller);
    }

    final void mergeWithSystemLogger(Logger system);

    private void setCallerModuleRef(Module callerModule);

    private Module getCallerModule();

    private Logger(String name) {
        this.name = name;
        this.isSystemLogger = true;
        config = new ConfigurationData();
    }

    void setLogManager(@GuardSatisfied Logger this, LogManager manager);

    private void checkPermission() throws SecurityException;

    private static class SystemLoggerHelper {

        static boolean disableCallerCheck = getBooleanProperty("sun.util.logging.disableCallerCheck");

        private static boolean getBooleanProperty(final String key);
    }

    private static Logger demandLogger(String name, @Nullable String resourceBundleName, Class<?> caller);

    @Pure
    @CallerSensitive
    public static Logger getLogger(String name);

    private static Logger getLogger(String name, Class<?> callerClass);

    @Pure
    @CallerSensitive
    public static Logger getLogger(String name, @Nullable String resourceBundleName);

    private static Logger getLogger(String name, String resourceBundleName, Class<?> callerClass);

    static Logger getPlatformLogger(String name);

    @Pure
    public static Logger getAnonymousLogger();

    @Pure
    @CallerSensitive
    public static Logger getAnonymousLogger(@Nullable @BinaryName String resourceBundleName);

    @Pure
    @Nullable
    public ResourceBundle getResourceBundle(@GuardSatisfied Logger this);

    @Pure
    @Nullable
    @BinaryName
    public String getResourceBundleName(@GuardSatisfied Logger this);

    public void setFilter(@GuardSatisfied Logger this, @Nullable Filter newFilter) throws SecurityException;

    @Pure
    @Nullable
    public Filter getFilter(@GuardSatisfied Logger this);

    @SideEffectFree
    public void log(@GuardSatisfied Logger this, LogRecord record);

    @SideEffectFree
    private void doLog(@GuardSatisfied Logger this, LogRecord lr);

    @SideEffectFree
    public void log(@GuardSatisfied Logger this, @GuardSatisfied Level level, @Nullable String msg);

    @SideEffectFree
    public void log(@GuardSatisfied Logger this, @GuardSatisfied Level level, @GuardSatisfied Supplier<String> msgSupplier);

    @SideEffectFree
    public void log(@GuardSatisfied Logger this, @GuardSatisfied Level level, @Nullable String msg, @GuardSatisfied @Nullable Object param1);

    @SideEffectFree
    public void log(@GuardSatisfied Logger this, @GuardSatisfied Level level, @Nullable String msg, @Nullable Object @GuardSatisfied @Nullable [] params);

    @SideEffectFree
    public void log(@GuardSatisfied Logger this, @GuardSatisfied Level level, @Nullable String msg, @GuardSatisfied @Nullable Throwable thrown);

    @SideEffectFree
    public void log(@GuardSatisfied Logger this, @GuardSatisfied Level level, @GuardSatisfied @Nullable Throwable thrown, @GuardSatisfied Supplier<String> msgSupplier);

    @SideEffectFree
    public void logp(@GuardSatisfied Logger this, @GuardSatisfied Level level, @Nullable String sourceClass, @Nullable String sourceMethod, @Nullable String msg);

    @SideEffectFree
    public void logp(@GuardSatisfied Logger this, Level level, @Nullable String sourceClass, @Nullable String sourceMethod, Supplier<String> msgSupplier);

    @SideEffectFree
    public void logp(@GuardSatisfied Logger this, Level level, @Nullable String sourceClass, @Nullable String sourceMethod, @Nullable String msg, @Nullable Object param1);

    @SideEffectFree
    public void logp(@GuardSatisfied Logger this, Level level, @Nullable String sourceClass, @Nullable String sourceMethod, @Nullable String msg, @Nullable Object @Nullable [] params);

    @SideEffectFree
    public void logp(@GuardSatisfied Logger this, Level level, @Nullable String sourceClass, @Nullable String sourceMethod, @Nullable String msg, @Nullable Throwable thrown);

    @SideEffectFree
    public void logp(@GuardSatisfied Logger this, Level level, @Nullable String sourceClass, @Nullable String sourceMethod, @Nullable Throwable thrown, Supplier<String> msgSupplier);

    @SideEffectFree
    private void doLog(@GuardSatisfied Logger this, @GuardSatisfied LogRecord lr, @Nullable String rbname);

    private void doLog(LogRecord lr, ResourceBundle rb);

    @SideEffectFree
    @Deprecated
    public void logrb(@GuardSatisfied Logger this, Level level, @Nullable String sourceClass, @Nullable String sourceMethod, @Nullable @BinaryName String bundleName, @Nullable String msg);

    @SideEffectFree
    @Deprecated
    public void logrb(@GuardSatisfied Logger this, Level level, @Nullable String sourceClass, @Nullable String sourceMethod, @Nullable @BinaryName String bundleName, @Nullable String msg, @Nullable Object param1);

    @SideEffectFree
    @Deprecated
    public void logrb(@GuardSatisfied Logger this, Level level, @Nullable String sourceClass, @Nullable String sourceMethod, @Nullable @BinaryName String bundleName, @Nullable String msg, @Nullable Object @Nullable [] params);

    public void logrb(Level level, String sourceClass, String sourceMethod, ResourceBundle bundle, String msg, Object... params);

    public void logrb(Level level, ResourceBundle bundle, String msg, Object... params);

    @SideEffectFree
    @Deprecated
    public void logrb(@GuardSatisfied Logger this, Level level, @Nullable String sourceClass, @Nullable String sourceMethod, @Nullable @BinaryName String bundleName, @Nullable String msg, @Nullable Throwable thrown);

    public void logrb(Level level, String sourceClass, String sourceMethod, ResourceBundle bundle, String msg, Throwable thrown);

    public void logrb(Level level, ResourceBundle bundle, String msg, Throwable thrown);

    @SideEffectFree
    public void entering(@GuardSatisfied Logger this, @Nullable String sourceClass, @Nullable String sourceMethod);

    @SideEffectFree
    public void entering(@GuardSatisfied Logger this, @Nullable String sourceClass, @Nullable String sourceMethod, @GuardSatisfied @Nullable Object param1);

    @SideEffectFree
    public void entering(@GuardSatisfied Logger this, @Nullable String sourceClass, @Nullable String sourceMethod, @Nullable Object @GuardSatisfied @Nullable [] params);

    @SideEffectFree
    public void exiting(@GuardSatisfied Logger this, @Nullable String sourceClass, @Nullable String sourceMethod);

    @SideEffectFree
    public void exiting(@GuardSatisfied Logger this, @Nullable String sourceClass, @Nullable String sourceMethod, @GuardSatisfied @Nullable Object result);

    @SideEffectFree
    public void throwing(@GuardSatisfied Logger this, @Nullable String sourceClass, @Nullable String sourceMethod, @GuardSatisfied @Nullable Throwable thrown);

    @SideEffectFree
    public void severe(@GuardSatisfied Logger this, @Nullable String msg);

    @SideEffectFree
    public void warning(@GuardSatisfied Logger this, @Nullable String msg);

    @SideEffectFree
    public void info(@GuardSatisfied Logger this, @Nullable String msg);

    @SideEffectFree
    public void config(@GuardSatisfied Logger this, @Nullable String msg);

    @SideEffectFree
    public void fine(@GuardSatisfied Logger this, @Nullable String msg);

    @SideEffectFree
    public void finer(@GuardSatisfied Logger this, @Nullable String msg);

    @SideEffectFree
    public void finest(@GuardSatisfied Logger this, @Nullable String msg);

    @SideEffectFree
    public void severe(@GuardSatisfied Logger this, Supplier<String> msgSupplier);

    @SideEffectFree
    public void warning(@GuardSatisfied Logger this, Supplier<String> msgSupplier);

    @SideEffectFree
    public void info(@GuardSatisfied Logger this, Supplier<String> msgSupplier);

    @SideEffectFree
    public void config(@GuardSatisfied Logger this, Supplier<String> msgSupplier);

    @SideEffectFree
    public void fine(@GuardSatisfied Logger this, Supplier<String> msgSupplier);

    @SideEffectFree
    public void finer(@GuardSatisfied Logger this, Supplier<String> msgSupplier);

    @SideEffectFree
    public void finest(@GuardSatisfied Logger this, Supplier<String> msgSupplier);

    public void setLevel(@GuardSatisfied Logger this, @Nullable Level newLevel) throws SecurityException;

    final boolean isLevelInitialized();

    @Pure
    @Nullable
    public Level getLevel(@GuardSatisfied Logger this);

    @Pure
    public boolean isLoggable(Level level);

    @Pure
    @Nullable
    public String getName(@GuardSatisfied Logger this);

    public void addHandler(@GuardSatisfied Logger this, Handler handler) throws SecurityException;

    public void removeHandler(@GuardSatisfied Logger this, @Nullable Handler handler) throws SecurityException;

    @SideEffectFree
    public Handler[] getHandlers(@GuardSatisfied Logger this);

    Handler[] accessCheckedHandlers();

    public void setUseParentHandlers(@GuardSatisfied Logger this, boolean useParentHandlers);

    @Pure
    public boolean getUseParentHandlers(@GuardSatisfied Logger this);

    @Nullable
    private synchronized ResourceBundle findResourceBundle(@Nullable String name, boolean useCallersModule);

    private void setupResourceInfo(@GuardSatisfied Logger this, @Nullable String name, @Nullable Class<?> caller);

    private synchronized void setupResourceInfo(String name, Module callerModule);

    public void setResourceBundle(ResourceBundle bundle);

    @Pure
    @Nullable
    public Logger getParent(@GuardSatisfied Logger this);

    public void setParent(@GuardSatisfied Logger this, @GuardSatisfied Logger parent);

    private void doSetParent(@GuardSatisfied Logger this, @GuardSatisfied Logger newParent);

    final void removeChildLogger(@GuardSatisfied Logger this, LogManager.LoggerWeakRef child);

    private void updateEffectiveLevel(@GuardSatisfied Logger this);

    private LoggerBundle getEffectiveLoggerBundle();
}
