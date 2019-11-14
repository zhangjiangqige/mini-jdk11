package java.util.logging;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Formatter {

    protected Formatter() {
    }

    public abstract String format(LogRecord record);

    public String getHead(Handler h);

    public String getTail(Handler h);

    public String formatMessage(LogRecord record);
}
