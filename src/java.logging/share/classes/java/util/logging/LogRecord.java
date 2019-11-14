package java.util.logging;

import org.checkerframework.checker.initialization.qual.UnderInitialization;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.time.Clock;
import java.util.function.Predicate;
import static jdk.internal.logger.SurrogateLogger.isFilteredFrame;

@AnnotatedFor({ "index", "interning", "nullness" })
@UsesObjectEquals
public class LogRecord implements java.io.Serializable {

    private static final AtomicLong globalSequenceNumber = new AtomicLong(0);

    private static final int MIN_SEQUENTIAL_THREAD_ID = Integer.MAX_VALUE / 2;

    private static final AtomicInteger nextThreadId = new AtomicInteger(MIN_SEQUENTIAL_THREAD_ID);

    private static final ThreadLocal<@Nullable Integer> threadIds = new ThreadLocal<>();

    private Level level;

    private long sequenceNumber;

    @Nullable
    private String sourceClassName;

    @Nullable
    private String sourceMethodName;

    @Nullable
    private String message;

    private int threadID;

    @Nullable
    private Throwable thrown;

    @Nullable
    private String loggerName;

    @Nullable
    private String resourceBundleName;

    private Instant instant;

    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("level", Level.class), new ObjectStreamField("sequenceNumber", long.class), new ObjectStreamField("sourceClassName", String.class), new ObjectStreamField("sourceMethodName", String.class), new ObjectStreamField("message", String.class), new ObjectStreamField("threadID", int.class), new ObjectStreamField("millis", long.class), new ObjectStreamField("nanoAdjustment", int.class), new ObjectStreamField("thrown", Throwable.class), new ObjectStreamField("loggerName", String.class), new ObjectStreamField("resourceBundleName", String.class) };

    private transient boolean needToInferCaller;

    @Nullable
    private transient Object @Nullable [] parameters;

    @Nullable
    private transient ResourceBundle resourceBundle;

    private int defaultThreadID(@UnderInitialization LogRecord this);

    public LogRecord(Level level, @Nullable String msg) {
        this.level = Objects.requireNonNull(level);
        message = msg;
        sequenceNumber = globalSequenceNumber.getAndIncrement();
        threadID = defaultThreadID();
        instant = Instant.now();
        needToInferCaller = true;
    }

    @Nullable
    public String getLoggerName();

    public void setLoggerName(@Nullable String name);

    @Nullable
    public ResourceBundle getResourceBundle();

    public void setResourceBundle(@Nullable ResourceBundle bundle);

    @Nullable
    public String getResourceBundleName();

    public void setResourceBundleName(@Nullable String name);

    public Level getLevel();

    public void setLevel(Level level);

    public long getSequenceNumber();

    public void setSequenceNumber(long seq);

    @Nullable
    public String getSourceClassName();

    public void setSourceClassName(@Nullable String sourceClassName);

    @Nullable
    public String getSourceMethodName();

    public void setSourceMethodName(@Nullable String sourceMethodName);

    @Nullable
    public String getMessage();

    public void setMessage(@Nullable String message);

    @Nullable
    public Object @Nullable [] getParameters();

    public void setParameters(@Nullable Object @Nullable [] parameters);

    public int getThreadID();

    public void setThreadID(int threadID);

    public long getMillis();

    @Deprecated
    public void setMillis(long millis);

    public Instant getInstant();

    public void setInstant(Instant instant);

    @Nullable
    public Throwable getThrown();

    public void setThrown(@Nullable Throwable thrown);

    private static final long serialVersionUID = 5372048053134512534L;

    @CFComment({ "nullness: out.writeInt and out.writeObject do not affect parameters field. http://tinyurl.com/cfissue/984" })
    @SuppressWarnings({ "dereference.of.nullable" })
    private void writeObject(ObjectOutputStream out) throws IOException;

    @CFComment({ "nullness: in.readObject does not affect parameters field. http://tinyurl.com/cfissue/984" })
    @SuppressWarnings({ "dereference.of.nullable" })
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;

    private void inferCaller();

    static final class CallerFinder implements Predicate<StackWalker.StackFrame> {

        private static final StackWalker WALKER;

        static {
            final PrivilegedAction<StackWalker> action = () -> StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
            WALKER = AccessController.doPrivileged(action);
        }

        Optional<StackWalker.StackFrame> get();

        private boolean lookingForLogger = true;

        @Override
        public boolean test(StackWalker.StackFrame t);

        private boolean isLoggerImplFrame(String cname);
    }
}
