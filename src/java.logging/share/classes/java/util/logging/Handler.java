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

    private static final int offValue = Level.OFF.intValue();

    private final LogManager manager = LogManager.getLogManager();

    private volatile Filter filter;

    private volatile Formatter formatter;

    private volatile Level logLevel = Level.ALL;

    private volatile ErrorManager errorManager = new ErrorManager();

    private volatile String encoding;

    protected Handler() {
    }

    Handler(Level defaultLevel, Formatter defaultFormatter, Formatter specifiedFormatter) {
        LogManager manager = LogManager.getLogManager();
        String cname = getClass().getName();
        final Level level = manager.getLevelProperty(cname + ".level", defaultLevel);
        final Filter filter = manager.getFilterProperty(cname + ".filter", null);
        final Formatter formatter = specifiedFormatter == null ? manager.getFormatterProperty(cname + ".formatter", defaultFormatter) : specifiedFormatter;
        final String encoding = manager.getStringProperty(cname + ".encoding", null);
        AccessController.doPrivileged(new PrivilegedAction<Void>() {

            @Override
            public Void run() {
                setLevel(level);
                setFilter(filter);
                setFormatter(formatter);
                try {
                    setEncoding(encoding);
                } catch (Exception ex) {
                    try {
                        setEncoding(null);
                    } catch (Exception ex2) {
                    }
                }
                return null;
            }
        }, null, LogManager.controlPermission);
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

    void checkPermission() throws SecurityException;
}
