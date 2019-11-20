package javax.management.monitor;

import javax.management.ObjectName;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public class MonitorNotification extends javax.management.Notification {

    @Interned
    public static final String OBSERVED_OBJECT_ERROR;

    @Interned
    public static final String OBSERVED_ATTRIBUTE_ERROR;

    @Interned
    public static final String OBSERVED_ATTRIBUTE_TYPE_ERROR;

    @Interned
    public static final String THRESHOLD_ERROR;

    @Interned
    public static final String RUNTIME_ERROR;

    @Interned
    public static final String THRESHOLD_VALUE_EXCEEDED;

    @Interned
    public static final String THRESHOLD_HIGH_VALUE_EXCEEDED;

    @Interned
    public static final String THRESHOLD_LOW_VALUE_EXCEEDED;

    @Interned
    public static final String STRING_TO_COMPARE_VALUE_MATCHED;

    @Interned
    public static final String STRING_TO_COMPARE_VALUE_DIFFERED;

    public ObjectName getObservedObject();

    public String getObservedAttribute();

    public Object getDerivedGauge();

    public Object getTrigger();
}
