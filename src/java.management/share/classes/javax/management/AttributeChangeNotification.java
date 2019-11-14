package javax.management;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public class AttributeChangeNotification extends javax.management.Notification {

    private static final long serialVersionUID = 535176054565814134L;

    @Interned
    public static final String ATTRIBUTE_CHANGE = "jmx.attribute.change";

    private String attributeName = null;

    private String attributeType = null;

    private Object oldValue = null;

    private Object newValue = null;

    public AttributeChangeNotification(Object source, long sequenceNumber, long timeStamp, String msg, String attributeName, String attributeType, Object oldValue, Object newValue) {
        super(AttributeChangeNotification.ATTRIBUTE_CHANGE, source, sequenceNumber, timeStamp, msg);
        this.attributeName = attributeName;
        this.attributeType = attributeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getAttributeName();

    public String getAttributeType();

    public Object getOldValue();

    public Object getNewValue();
}
