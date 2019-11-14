package javax.management.monitor;

import javax.management.ObjectName;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public class MonitorNotification extends javax.management.Notification {

    @Interned
    public static final String OBSERVED_OBJECT_ERROR = "jmx.monitor.error.mbean";

    @Interned
    public static final String OBSERVED_ATTRIBUTE_ERROR = "jmx.monitor.error.attribute";

    @Interned
    public static final String OBSERVED_ATTRIBUTE_TYPE_ERROR = "jmx.monitor.error.type";

    @Interned
    public static final String THRESHOLD_ERROR = "jmx.monitor.error.threshold";

    @Interned
    public static final String RUNTIME_ERROR = "jmx.monitor.error.runtime";

    @Interned
    public static final String THRESHOLD_VALUE_EXCEEDED = "jmx.monitor.counter.threshold";

    @Interned
    public static final String THRESHOLD_HIGH_VALUE_EXCEEDED = "jmx.monitor.gauge.high";

    @Interned
    public static final String THRESHOLD_LOW_VALUE_EXCEEDED = "jmx.monitor.gauge.low";

    @Interned
    public static final String STRING_TO_COMPARE_VALUE_MATCHED = "jmx.monitor.string.matches";

    @Interned
    public static final String STRING_TO_COMPARE_VALUE_DIFFERED = "jmx.monitor.string.differs";

    private static final long serialVersionUID = -4608189663661929204L;

    private ObjectName observedObject = null;

    private String observedAttribute = null;

    private Object derivedGauge = null;

    private Object trigger = null;

    MonitorNotification(String type, Object source, long sequenceNumber, long timeStamp, String msg, ObjectName obsObj, String obsAtt, Object derGauge, Object trigger) {
        super(type, source, sequenceNumber, timeStamp, msg);
        this.observedObject = obsObj;
        this.observedAttribute = obsAtt;
        this.derivedGauge = derGauge;
        this.trigger = trigger;
    }

    public ObjectName getObservedObject();

    public String getObservedAttribute();

    public Object getDerivedGauge();

    public Object getTrigger();
}
