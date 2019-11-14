package javax.management;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.concurrent.CopyOnWriteArrayList;

@AnnotatedFor("nullness")
public interface NotificationBroadcaster {

    public void addNotificationListener(NotificationListener listener, @Nullable NotificationFilter filter, @Nullable Object handback) throws java.lang.IllegalArgumentException;

    public void removeNotificationListener(NotificationListener listener) throws ListenerNotFoundException;

    public MBeanNotificationInfo[] getNotificationInfo();
}
