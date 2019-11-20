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

    public PropertyPermission(String name, @Nullable String actions) {
    }

    @Override
    public boolean implies(Permission p);

    @Pure
    @Override
    public boolean equals(@GuardSatisfied PropertyPermission this, @GuardSatisfied @Nullable Object obj);

    @Pure
    @Override
    public int hashCode(@GuardSatisfied PropertyPermission this);

    @Override
    public String getActions();

    @Override
    public PermissionCollection newPermissionCollection();
}
