package javax.management.relation;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.management.Notification;
import javax.management.ObjectName;
import java.io.InvalidObjectException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.sun.jmx.mbeanserver.GetPropertyAction;
import static com.sun.jmx.mbeanserver.Util.cast;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class RelationNotification extends Notification {

    @Interned
    public static final String RELATION_BASIC_CREATION;

    @Interned
    public static final String RELATION_MBEAN_CREATION;

    @Interned
    public static final String RELATION_BASIC_UPDATE;

    @Interned
    public static final String RELATION_MBEAN_UPDATE;

    @Interned
    public static final String RELATION_BASIC_REMOVAL;

    @Interned
    public static final String RELATION_MBEAN_REMOVAL;

    public RelationNotification(String notifType, Object sourceObj, long sequence, long timeStamp, String message, String id, String typeName, ObjectName objectName, List<ObjectName> unregMBeanList) throws IllegalArgumentException {
    }

    public RelationNotification(String notifType, Object sourceObj, long sequence, long timeStamp, String message, String id, String typeName, ObjectName objectName, String name, List<ObjectName> newValue, List<ObjectName> oldValue) throws IllegalArgumentException {
    }

    public String getRelationId();

    public String getRelationTypeName();

    public ObjectName getObjectName();

    public List<ObjectName> getMBeansToUnregister();

    public String getRoleName();

    public List<ObjectName> getOldRoleValue();

    public List<ObjectName> getNewRoleValue();
}
