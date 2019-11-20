package javax.management.remote;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.management.Notification;
import javax.management.ObjectName;

@AnnotatedFor({ "interning" })
public class JMXConnectionNotification extends Notification {

    @Interned
    public static final String OPENED;

    @Interned
    public static final String CLOSED;

    @Interned
    public static final String FAILED;

    public static final String NOTIFS_LOST;

    public JMXConnectionNotification(String type, Object source, String connectionId, long sequenceNumber, String message, Object userData) {
    }

    public String getConnectionId();
}
