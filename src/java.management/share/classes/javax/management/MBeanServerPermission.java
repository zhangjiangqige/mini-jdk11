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

    private static final long serialVersionUID = -5661980843569388590L;

    private final static int CREATE = 0, FIND = 1, NEW = 2, RELEASE = 3, N_NAMES = 4;

    private final static String[] names = { "createMBeanServer", "findMBeanServer", "newMBeanServer", "releaseMBeanServer" };

    private final static int CREATE_MASK = 1 << CREATE, FIND_MASK = 1 << FIND, NEW_MASK = 1 << NEW, RELEASE_MASK = 1 << RELEASE, ALL_MASK = CREATE_MASK | FIND_MASK | NEW_MASK | RELEASE_MASK;

    private final static String[] canonicalNames = new String[1 << N_NAMES];

    transient int mask;

    public MBeanServerPermission(String name) {
        this(name, null);
    }

    public MBeanServerPermission(String name, String actions) {
        super(getCanonicalName(parseMask(name)), actions);
        mask = parseMask(name);
        if (actions != null && actions.length() > 0)
            throw new IllegalArgumentException("MBeanServerPermission " + "actions must be null: " + actions);
    }

    MBeanServerPermission(int mask) {
        super(getCanonicalName(mask));
        this.mask = impliedMask(mask);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;

    static int simplifyMask(int mask);

    static int impliedMask(int mask);

    @Interned
    static String getCanonicalName(int mask);

    private static String makeCanonicalName(int mask);

    private static int parseMask(String name);

    private static int nameIndex(String name) throws IllegalArgumentException;

    public int hashCode();

    public boolean implies(Permission p);

    public boolean equals(Object obj);

    public PermissionCollection newPermissionCollection();
}

class MBeanServerPermissionCollection extends PermissionCollection {

    private MBeanServerPermission collectionPermission;

    private static final long serialVersionUID = -5661980843569388590L;

    public synchronized void add(Permission permission);

    public synchronized boolean implies(Permission permission);

    public synchronized Enumeration<Permission> elements();
}
