package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.security.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "lock", "nullness", "index" })
public final class PropertyPermission extends BasicPermission {

    private static final int READ = 0x1;

    private static final int WRITE = 0x2;

    private static final int ALL = READ | WRITE;

    private static final int NONE = 0x0;

    private transient int mask;

    private String actions;

    private void init(int mask);

    public PropertyPermission(String name, @Nullable String actions) {
        super(name, actions);
        init(getMask(actions));
    }

    PropertyPermission(String name, int mask) {
        super(name, getActions(mask));
        this.mask = mask;
    }

    @Override
    public boolean implies(Permission p);

    @Pure
    @Override
    public boolean equals(@GuardSatisfied PropertyPermission this, @GuardSatisfied @Nullable Object obj);

    @Pure
    @Override
    public int hashCode(@GuardSatisfied PropertyPermission this);

    private static int getMask(String actions);

    static String getActions(int mask);

    @Override
    public String getActions();

    int getMask();

    @Override
    public PermissionCollection newPermissionCollection();

    private static final long serialVersionUID = 885438825399942851L;

    private synchronized void writeObject(java.io.ObjectOutputStream s) throws IOException;

    private synchronized void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException;
}

final class PropertyPermissionCollection extends PermissionCollection implements Serializable {

    private transient ConcurrentHashMap<String, PropertyPermission> perms;

    private boolean all_allowed;

    public PropertyPermissionCollection() {
        perms = new ConcurrentHashMap<>(32);
        all_allowed = false;
    }

    @Override
    public void add(Permission permission);

    @Override
    public boolean implies(Permission permission);

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration<Permission> elements();

    private static final long serialVersionUID = 7015263904581634791L;

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("permissions", Hashtable.class), new ObjectStreamField("all_allowed", Boolean.TYPE) };

    private void writeObject(ObjectOutputStream out) throws IOException;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;
}
