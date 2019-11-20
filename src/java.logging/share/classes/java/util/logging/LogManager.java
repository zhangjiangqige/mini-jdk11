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

    protected LogManager() {
    }

    public static LogManager getLogManager();

    public boolean addLogger(Logger logger);

    public Logger getLogger(String name);

    public Enumeration<String> getLoggerNames();

    public void readConfiguration() throws IOException, SecurityException;

    public void reset() throws SecurityException;

    public void readConfiguration(InputStream ins) throws IOException, SecurityException;

    static enum ConfigProperty {

        LEVEL(".level"), HANDLERS(".handlers"), USEPARENT(".useParentHandlers");

        public boolean handleKey(String key);
    }

    static enum ModType {

        SAME, ADDED, CHANGED, REMOVED
    }

    public void updateConfiguration(Function<String, BiFunction<String, String, String>> mapper) throws IOException;

    public void updateConfiguration(InputStream ins, Function<String, BiFunction<String, String, String>> mapper) throws IOException;

    public String getProperty(String name);

    public void checkAccess() throws SecurityException;

    public final static String LOGGING_MXBEAN_NAME;

    @Deprecated()
    public static synchronized LoggingMXBean getLoggingMXBean();

    public LogManager addConfigurationListener(Runnable listener);

    public void removeConfigurationListener(Runnable listener);
}
