package javax.management;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public class AttributeChangeNotification extends javax.management.Notification {

    @Interned
    public static final String ATTRIBUTE_CHANGE;

    public AttributeChangeNotification(Object source, long sequenceNumber, long timeStamp, String msg, String attributeName, String attributeType, Object oldValue, Object newValue) {
    }

    public String getAttributeName();

    public String getAttributeType();

    public Object getOldValue();

    public Object getNewValue();
}
