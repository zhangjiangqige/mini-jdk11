package java.util.logging;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;
import java.io.UnsupportedEncodingException;
import java.security.AccessController;
import java.security.PrivilegedAction;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Handler {

    protected Handler() {
    }

    public abstract void publish(LogRecord record);

    public abstract void flush();

    public abstract void close() throws SecurityException;

    public synchronized void setFormatter(Formatter newFormatter) throws SecurityException;

    public Formatter getFormatter();

    public synchronized void setEncoding(String encoding) throws SecurityException, java.io.UnsupportedEncodingException;

    public String getEncoding();

    public synchronized void setFilter(Filter newFilter) throws SecurityException;

    public Filter getFilter();

    public synchronized void setErrorManager(ErrorManager em);

    public ErrorManager getErrorManager();

    protected void reportError(String msg, Exception ex, int code);

    public synchronized void setLevel(Level newLevel) throws SecurityException;

    public Level getLevel();

    public boolean isLoggable(LogRecord record);
}
