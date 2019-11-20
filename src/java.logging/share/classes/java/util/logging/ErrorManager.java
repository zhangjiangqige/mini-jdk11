package java.util.logging;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ErrorManager {

    public final static int GENERIC_FAILURE;

    public final static int WRITE_FAILURE;

    public final static int FLUSH_FAILURE;

    public final static int CLOSE_FAILURE;

    public final static int OPEN_FAILURE;

    public final static int FORMAT_FAILURE;

    public synchronized void error(String msg, Exception ex, int code);
}
