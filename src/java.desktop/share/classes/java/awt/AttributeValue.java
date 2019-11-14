package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.util.logging.PlatformLogger;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
abstract class AttributeValue {

    private static final PlatformLogger log = PlatformLogger.getLogger("java.awt.AttributeValue");

    private final int value;

    private final String[] names;

    protected AttributeValue(int value, String[] names) {
        if (log.isLoggable(PlatformLogger.Level.FINEST)) {
            log.finest("value = " + value + ", names = " + names);
        }
        if (log.isLoggable(PlatformLogger.Level.FINER)) {
            if ((value < 0) || (names == null) || (value >= names.length)) {
                log.finer("Assertion failed");
            }
        }
        this.value = value;
        this.names = names;
    }

    public int hashCode();

    public String toString();
}
