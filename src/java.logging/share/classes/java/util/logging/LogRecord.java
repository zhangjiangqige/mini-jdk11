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

    public LogRecord(Level level, @Nullable String msg) {
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
}
