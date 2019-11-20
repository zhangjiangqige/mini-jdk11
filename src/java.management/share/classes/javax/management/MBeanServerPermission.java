package javax.management;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.BasicPermission;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;
import java.util.StringTokenizer;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
public class MBeanServerPermission extends BasicPermission {

    public MBeanServerPermission(String name) {
    }

    public MBeanServerPermission(String name, String actions) {
    }

    public int hashCode();

    public boolean implies(Permission p);

    public boolean equals(Object obj);

    public PermissionCollection newPermissionCollection();
}
