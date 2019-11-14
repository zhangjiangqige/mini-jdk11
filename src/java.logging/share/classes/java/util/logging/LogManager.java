package java.util.logging;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.util.*;
import java.security.*;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.nio.file.Paths;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jdk.internal.misc.JavaAWTAccess;
import jdk.internal.misc.SharedSecrets;
import sun.util.logging.internal.LoggingProviderImpl;
import static jdk.internal.logger.DefaultLoggerFinder.isSystem;

@AnnotatedFor({ "index", "interning", "signature" })
@UsesObjectEquals
public class LogManager {

    private static final LogManager manager;

    private volatile Properties props = new Properties();

    private final static Level defaultLevel = Level.INFO;

    private final LoggerContext systemContext = new SystemLoggerContext();

    private final LoggerContext userContext = new LoggerContext();

    private volatile Logger rootLogger;

    private volatile boolean readPrimordialConfiguration;

    private static final int STATE_INITIALIZED = 0, STATE_INITIALIZING = 1, STATE_READING_CONFIG = 2, STATE_UNINITIALIZED = 3, STATE_SHUTDOWN = 4;

    private volatile int globalHandlersState;

    private final ReentrantLock configurationLock = new ReentrantLock();

    private static final class CloseOnReset {

        private final Logger logger;

        private CloseOnReset(Logger ref) {
            this.logger = Objects.requireNonNull(ref);
        }

        @Override
        public boolean equals(Object other);

        @Override
        public int hashCode();

        public Logger get();

        public static CloseOnReset create(Logger logger);
    }

    private final CopyOnWriteArrayList<CloseOnReset> closeOnResetLoggers = new CopyOnWriteArrayList<>();

    private final Map<Object, Runnable> listeners = Collections.synchronizedMap(new IdentityHashMap<>());

    static {
        manager = AccessController.doPrivileged(new PrivilegedAction<LogManager>() {

            @Override
            public LogManager run() {
                LogManager mgr = null;
                @BinaryName
                String cname = null;
                try {
                    cname = System.getProperty("java.util.logging.manager");
                    if (cname != null) {
                        try {
                            @SuppressWarnings("deprecation")
                            Object tmp = ClassLoader.getSystemClassLoader().loadClass(cname).newInstance();
                            mgr = (LogManager) tmp;
                        } catch (ClassNotFoundException ex) {
                            @SuppressWarnings("deprecation")
                            Object tmp = Thread.currentThread().getContextClassLoader().loadClass(cname).newInstance();
                            mgr = (LogManager) tmp;
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("Could not load Logmanager \"" + cname + "\"");
                    ex.printStackTrace();
                }
                if (mgr == null) {
                    mgr = new LogManager();
                }
                return mgr;
            }
        });
    }

    private class Cleaner extends Thread {

        private Cleaner() {
            super(null, null, "Logging-Cleaner", 0, false);
            this.setContextClassLoader(null);
        }

        @Override
        public void run();
    }

    protected LogManager() {
        this(checkSubclassPermissions());
    }

    private LogManager(Void checked) {
        try {
            Runtime.getRuntime().addShutdownHook(new Cleaner());
        } catch (IllegalStateException e) {
        }
    }

    private static Void checkSubclassPermissions();

    private boolean initializedCalled = false;

    private volatile boolean initializationDone = false;

    final void ensureLogManagerInitialized();

    public static LogManager getLogManager();

    private void readPrimordialConfiguration();

    private WeakHashMap<Object, LoggerContext> contextsMap = null;

    private LoggerContext getUserContext();

    final LoggerContext getSystemContext();

    private List<LoggerContext> contexts();

    Logger demandLogger(String name, String resourceBundleName, Class<?> caller);

    Logger demandLogger(String name, String resourceBundleName, Module module);

    Logger demandSystemLogger(String name, String resourceBundleName, Class<?> caller);

    Logger demandSystemLogger(String name, String resourceBundleName, Module module);

    class LoggerContext {

        private final ConcurrentHashMap<String, LoggerWeakRef> namedLoggers = new ConcurrentHashMap<>();

        private final LogNode root;

        private LoggerContext() {
            this.root = new LogNode(null, this);
        }

        final boolean requiresDefaultLoggers();

        final LogManager getOwner();

        final Logger getRootLogger();

        final Logger getGlobalLogger();

        Logger demandLogger(String name, String resourceBundleName, Module module);

        private void ensureInitialized();

        Logger findLogger(String name);

        private void ensureAllDefaultLoggers(Logger logger);

        private void ensureDefaultLogger(Logger logger);

        boolean addLocalLogger(Logger logger);

        synchronized boolean addLocalLogger(Logger logger, boolean addDefaultLoggersIfNeeded);

        void removeLoggerRef(String name, LoggerWeakRef ref);

        synchronized Enumeration<String> getLoggerNames();

        private void processParentHandlers(final Logger logger, final String name, Predicate<Logger> visited);

        LogNode getNode(String name);
    }

    final class SystemLoggerContext extends LoggerContext {

        @Override
        Logger demandLogger(String name, String resourceBundleName, Module module);
    }

    private void loadLoggerHandlers(final Logger logger, final String name, final String handlersPropertyName);

    private void setLoggerHandlers(final Logger logger, final String name, final String handlersPropertyName, List<Handler> handlers);

    private List<Handler> createLoggerHandlers(final String name, final String handlersPropertyName);

    private final ReferenceQueue<Logger> loggerRefQueue = new ReferenceQueue<>();

    final class LoggerWeakRef extends WeakReference<Logger> {

        private String name;

        private LogNode node;

        private WeakReference<Logger> parentRef;

        private boolean disposed = false;

        LoggerWeakRef(Logger logger) {
            super(logger, loggerRefQueue);
            name = logger.getName();
        }

        void dispose();

        void setNode(LogNode node);

        void setParentRef(WeakReference<Logger> parentRef);
    }

    private final static int MAX_ITERATIONS = 400;

    final void drainLoggerRefQueueBounded();

    public boolean addLogger(Logger logger);

    @SuppressWarnings("deprecation")
    private boolean forceLoadHandlers(Logger logger);

    private static void doSetLevel(final Logger logger, final Level level);

    private static void doSetParent(final Logger logger, final Logger parent);

    public Logger getLogger(String name);

    public Enumeration<String> getLoggerNames();

    public void readConfiguration() throws IOException, SecurityException;

    String getConfigurationFileName() throws IOException;

    public void reset() throws SecurityException;

    private void resetLoggerContext(LoggerContext cx);

    private void closeHandlers(Logger logger);

    private void resetLogger(Logger logger);

    private String[] parseClassNames(String propertyName);

    public void readConfiguration(InputStream ins) throws IOException, SecurityException;

    static enum ConfigProperty {

        LEVEL(".level"), HANDLERS(".handlers"), USEPARENT(".useParentHandlers");

        final String suffix;

        final int length;

        private ConfigProperty(String suffix) {
            this.suffix = Objects.requireNonNull(suffix);
            length = suffix.length();
        }

        public boolean handleKey(String key) {
            if (this == HANDLERS && suffix.substring(1).equals(key))
                return true;
            if (this == HANDLERS && suffix.equals(key))
                return false;
            return key.endsWith(suffix);
        }

        String key(String loggerName) {
            if (this == HANDLERS && (loggerName == null || loggerName.isEmpty())) {
                return suffix.substring(1);
            }
            return loggerName + suffix;
        }

        String loggerName(String key) {
            assert key.equals(suffix.substring(1)) && this == HANDLERS || key.endsWith(suffix);
            if (this == HANDLERS && suffix.substring(1).equals(key))
                return "";
            return key.substring(0, key.length() - length);
        }

        static String getLoggerName(String property) {
            for (ConfigProperty p : ConfigProperty.ALL) {
                if (p.handleKey(property)) {
                    return p.loggerName(property);
                }
            }
            return null;
        }

        static Optional<ConfigProperty> find(String property) {
            return ConfigProperty.ALL.stream().filter(p -> p.handleKey(property)).findFirst();
        }

        static boolean matches(String property) {
            return find(property).isPresent();
        }

        static boolean needsUpdating(String k, Properties previous, Properties next) {
            final String p = trim(previous.getProperty(k, null));
            final String n = trim(next.getProperty(k, null));
            return !Objects.equals(p, n);
        }

        static void merge(String k, Properties previous, Properties next, BiFunction<String, String, String> mappingFunction) {
            String p = trim(previous.getProperty(k, null));
            String n = trim(next.getProperty(k, null));
            String mapped = trim(mappingFunction.apply(p, n));
            if (!Objects.equals(n, mapped)) {
                if (mapped == null) {
                    next.remove(k);
                } else {
                    next.setProperty(k, mapped);
                }
            }
        }

        private static final EnumSet<ConfigProperty> ALL = EnumSet.allOf(ConfigProperty.class);
    }

    private static String trim(String value);

    static final class VisitedLoggers implements Predicate<Logger> {

        final IdentityHashMap<Logger, Boolean> visited;

        private VisitedLoggers(IdentityHashMap<Logger, Boolean> visited) {
            this.visited = visited;
        }

        VisitedLoggers() {
            this(new IdentityHashMap<>());
        }

        @Override
        public boolean test(Logger logger);

        public void clear();

        static final VisitedLoggers NEVER = new VisitedLoggers(null);
    }

    static enum ModType {

        SAME, ADDED, CHANGED, REMOVED;

        static ModType of(String previous, String next) {
            if (previous == null && next != null) {
                return ADDED;
            }
            if (next == null && previous != null) {
                return REMOVED;
            }
            if (!Objects.equals(trim(previous), trim(next))) {
                return CHANGED;
            }
            return SAME;
        }
    }

    public void updateConfiguration(Function<String, BiFunction<String, String, String>> mapper) throws IOException;

    public void updateConfiguration(InputStream ins, Function<String, BiFunction<String, String, String>> mapper) throws IOException;

    public String getProperty(String name);

    String getStringProperty(String name, String defaultValue);

    int getIntProperty(String name, int defaultValue);

    long getLongProperty(String name, long defaultValue);

    boolean getBooleanProperty(String name, boolean defaultValue);

    Level getLevelProperty(String name, Level defaultValue);

    @SuppressWarnings("signature")
    Filter getFilterProperty(String name, Filter defaultValue);

    Formatter getFormatterProperty(String name, Formatter defaultValue);

    private void initializeGlobalHandlers();

    static final Permission controlPermission = new LoggingPermission("control", null);

    void checkPermission();

    public void checkAccess() throws SecurityException;

    private static class LogNode {

        HashMap<String, LogNode> children;

        LoggerWeakRef loggerRef;

        LogNode parent;

        final LoggerContext context;

        LogNode(LogNode parent, LoggerContext context) {
            this.parent = parent;
            this.context = context;
        }

        void walkAndSetParent(Logger parent);
    }

    private final class RootLogger extends Logger {

        private RootLogger() {
            super("", null, null, LogManager.this, true);
        }

        @Override
        public void log(LogRecord record);

        @Override
        public void addHandler(Handler h);

        @Override
        public void removeHandler(Handler h);

        @Override
        Handler[] accessCheckedHandlers();
    }

    private void setLevelsOnExistingLoggers();

    public final static String LOGGING_MXBEAN_NAME = "java.util.logging:type=Logging";

    @Deprecated(since = "9")
    public static synchronized LoggingMXBean getLoggingMXBean();

    public LogManager addConfigurationListener(Runnable listener);

    public void removeConfigurationListener(Runnable listener);

    private void invokeConfigurationListeners();

    private static final class LoggingProviderAccess implements LoggingProviderImpl.LogManagerAccess, PrivilegedAction<Void> {

        private LoggingProviderAccess() {
        }

        @Override
        public Logger demandLoggerFor(LogManager manager, String name, Module module);

        @Override
        public Void run();

        static final LoggingProviderAccess INSTANCE = new LoggingProviderAccess();
    }

    static {
        AccessController.doPrivileged(LoggingProviderAccess.INSTANCE, null, controlPermission);
    }
}
