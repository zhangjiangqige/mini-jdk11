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

    private static final long oldSerialVersionUID = -2126464566505527147L;

    private static final long newSerialVersionUID = -6871117877523310399L;

    private static final ObjectStreamField[] oldSerialPersistentFields = { new ObjectStreamField("myNewRoleValue", ArrayList.class), new ObjectStreamField("myOldRoleValue", ArrayList.class), new ObjectStreamField("myRelId", String.class), new ObjectStreamField("myRelObjName", ObjectName.class), new ObjectStreamField("myRelTypeName", String.class), new ObjectStreamField("myRoleName", String.class), new ObjectStreamField("myUnregMBeanList", ArrayList.class) };

    private static final ObjectStreamField[] newSerialPersistentFields = { new ObjectStreamField("newRoleValue", List.class), new ObjectStreamField("oldRoleValue", List.class), new ObjectStreamField("relationId", String.class), new ObjectStreamField("relationObjName", ObjectName.class), new ObjectStreamField("relationTypeName", String.class), new ObjectStreamField("roleName", String.class), new ObjectStreamField("unregisterMBeanList", List.class) };

    private static final long serialVersionUID;

    private static final ObjectStreamField[] serialPersistentFields;

    private static boolean compat = false;

    static {
        try {
            GetPropertyAction act = new GetPropertyAction("jmx.serial.form");
            String form = AccessController.doPrivileged(act);
            compat = (form != null && form.equals("1.0"));
        } catch (Exception e) {
        }
        if (compat) {
            serialPersistentFields = oldSerialPersistentFields;
            serialVersionUID = oldSerialVersionUID;
        } else {
            serialPersistentFields = newSerialPersistentFields;
            serialVersionUID = newSerialVersionUID;
        }
    }

    @Interned
    public static final String RELATION_BASIC_CREATION = "jmx.relation.creation.basic";

    @Interned
    public static final String RELATION_MBEAN_CREATION = "jmx.relation.creation.mbean";

    @Interned
    public static final String RELATION_BASIC_UPDATE = "jmx.relation.update.basic";

    @Interned
    public static final String RELATION_MBEAN_UPDATE = "jmx.relation.update.mbean";

    @Interned
    public static final String RELATION_BASIC_REMOVAL = "jmx.relation.removal.basic";

    @Interned
    public static final String RELATION_MBEAN_REMOVAL = "jmx.relation.removal.mbean";

    private String relationId = null;

    private String relationTypeName = null;

    private ObjectName relationObjName = null;

    private List<ObjectName> unregisterMBeanList = null;

    private String roleName = null;

    private List<ObjectName> oldRoleValue = null;

    private List<ObjectName> newRoleValue = null;

    public RelationNotification(String notifType, Object sourceObj, long sequence, long timeStamp, String message, String id, String typeName, ObjectName objectName, List<ObjectName> unregMBeanList) throws IllegalArgumentException {
        super(notifType, sourceObj, sequence, timeStamp, message);
        if (!isValidBasicStrict(notifType, sourceObj, id, typeName) || !isValidCreate(notifType)) {
            throw new IllegalArgumentException("Invalid parameter.");
        }
        relationId = id;
        relationTypeName = typeName;
        relationObjName = safeGetObjectName(objectName);
        unregisterMBeanList = safeGetObjectNameList(unregMBeanList);
    }

    public RelationNotification(String notifType, Object sourceObj, long sequence, long timeStamp, String message, String id, String typeName, ObjectName objectName, String name, List<ObjectName> newValue, List<ObjectName> oldValue) throws IllegalArgumentException {
        super(notifType, sourceObj, sequence, timeStamp, message);
        if (!isValidBasicStrict(notifType, sourceObj, id, typeName) || !isValidUpdate(notifType, name, newValue, oldValue)) {
            throw new IllegalArgumentException("Invalid parameter.");
        }
        relationId = id;
        relationTypeName = typeName;
        relationObjName = safeGetObjectName(objectName);
        roleName = name;
        oldRoleValue = safeGetObjectNameList(oldValue);
        newRoleValue = safeGetObjectNameList(newValue);
    }

    public String getRelationId();

    public String getRelationTypeName();

    public ObjectName getObjectName();

    public List<ObjectName> getMBeansToUnregister();

    public String getRoleName();

    public List<ObjectName> getOldRoleValue();

    public List<ObjectName> getNewRoleValue();

    private boolean isValidBasicStrict(String notifType, Object sourceObj, String id, String typeName);

    private boolean isValidBasic(String notifType, Object sourceObj, String id, String typeName);

    private boolean isValidCreate(String notifType);

    private boolean isValidUpdate(String notifType, String name, List<ObjectName> newValue, List<ObjectName> oldValue);

    private ArrayList<ObjectName> safeGetObjectNameList(List<ObjectName> src);

    private ObjectName safeGetObjectName(ObjectName src);

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;

    private void writeObject(ObjectOutputStream out) throws IOException;
}
