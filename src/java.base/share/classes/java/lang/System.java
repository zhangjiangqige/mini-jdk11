package java.lang;

import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.module.ModuleDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.nio.charset.CharacterCodingException;
import java.security.AccessControlContext;
import java.security.ProtectionDomain;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.nio.channels.Channel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.PropertyPermission;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import jdk.internal.util.StaticProperty;
import jdk.internal.module.ModuleBootstrap;
import jdk.internal.module.ServicesCatalog;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.misc.JavaLangAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.VM;
import jdk.internal.logger.LoggerFinderLoader;
import jdk.internal.logger.LazyLoggers;
import jdk.internal.logger.LocalizedLoggerWrapper;
import sun.reflect.annotation.AnnotationType;
import sun.nio.ch.Interruptible;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "index", "interning", "lock", "nullness", "signedness" })
@UsesObjectEquals
public final class System {

    private static native void registerNatives();

    static {
        registerNatives();
    }

    private System() {
    }

    public static final InputStream in = null;

    public static final PrintStream out = null;

    public static final PrintStream err = null;

    private static volatile SecurityManager security;

    public static void setIn(InputStream in);

    public static void setOut(PrintStream out);

    public static void setErr(PrintStream err);

    private static volatile Console cons;

    @Nullable
    public static Console console();

    @Nullable
    public static Channel inheritedChannel() throws IOException;

    private static void checkIO();

    private static native void setIn0(InputStream in);

    private static native void setOut0(PrintStream out);

    private static native void setErr0(PrintStream err);

    public static void setSecurityManager(@Nullable final SecurityManager s);

    private static synchronized void setSecurityManager0(final SecurityManager s);

    @Nullable
    public static SecurityManager getSecurityManager();

    @HotSpotIntrinsicCandidate
    public static native long currentTimeMillis();

    @HotSpotIntrinsicCandidate
    public static native long nanoTime();

    @SideEffectFree
    @HotSpotIntrinsicCandidate
    public static native void arraycopy(@PolySigned @GuardSatisfied Object src, @NonNegative int srcPos, @PolySigned @GuardSatisfied Object dest, @NonNegative int destPos, @LTLengthOf(value = { "#1", "#3" }, offset = { "#2 - 1", "#4 - 1" }) @NonNegative int length);

    @Pure
    @HotSpotIntrinsicCandidate
    public static native int identityHashCode(@GuardSatisfied @Nullable Object x);

    private static Properties props;

    private static native Properties initProperties(Properties props);

    public static Properties getProperties();

    public static String lineSeparator();

    private static String lineSeparator;

    public static void setProperties(@Nullable Properties props);

    @Pure
    @Nullable
    public static String getProperty(String key);

    @Pure
    @PolyNull
    public static String getProperty(String key, @PolyNull String def);

    @Nullable
    public static String setProperty(String key, String value);

    @Nullable
    public static String clearProperty(String key);

    private static void checkKey(String key);

    @Nullable
    public static String getenv(String name);

    public static java.util.Map<String, String> getenv();

    public interface Logger {

        public enum Level {

            ALL(Integer.MIN_VALUE),
            TRACE(400),
            DEBUG(500),
            INFO(800),
            WARNING(900),
            ERROR(1000),
            OFF(Integer.MAX_VALUE);

            private final int severity;

            private Level(int severity) {
                this.severity = severity;
            }

            public final String getName() {
                return name();
            }

            public final int getSeverity() {
                return severity;
            }
        }

        public String getName();

        public boolean isLoggable(Level level);

        public default void log(Level level, String msg);

        public default void log(Level level, Supplier<String> msgSupplier);

        public default void log(Level level, Object obj);

        public default void log(Level level, String msg, Throwable thrown);

        public default void log(Level level, Supplier<String> msgSupplier, Throwable thrown);

        public default void log(Level level, String format, Object... params);

        public void log(Level level, ResourceBundle bundle, String msg, Throwable thrown);

        public void log(Level level, ResourceBundle bundle, String format, Object... params);
    }

    public static abstract class LoggerFinder {

        static final RuntimePermission LOGGERFINDER_PERMISSION = new RuntimePermission("loggerFinder");

        protected LoggerFinder() {
            this(checkPermission());
        }

        private LoggerFinder(Void unused) {
        }

        private static Void checkPermission();

        public abstract Logger getLogger(String name, Module module);

        public Logger getLocalizedLogger(String name, ResourceBundle bundle, Module module);

        public static LoggerFinder getLoggerFinder();

        private static volatile LoggerFinder service;

        static LoggerFinder accessProvider();
    }

    @CallerSensitive
    public static Logger getLogger(String name);

    @CallerSensitive
    public static Logger getLogger(String name, ResourceBundle bundle);

    @TerminatesExecution
    public static void exit(int status);

    public static void gc();

    public static void runFinalization();

    @CallerSensitive
    public static void load(String filename);

    @CallerSensitive
    public static void loadLibrary(String libname);

    public static native String mapLibraryName(String libname);

    private static PrintStream newPrintStream(FileOutputStream fos, String enc);

    private static void logInitException(boolean printToStderr, boolean printStackTrace, String msg, Throwable e);

    private static void initPhase1();

    static ModuleLayer bootLayer;

    private static int initPhase2(boolean printToStderr, boolean printStackTrace);

    private static void initPhase3();

    private static void setJavaLangAccess();
}
