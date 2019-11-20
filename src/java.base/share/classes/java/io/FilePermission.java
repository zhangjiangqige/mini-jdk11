package java.io;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.net.URI;
import java.nio.file.*;
import java.security.*;
import java.util.Enumeration;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import jdk.internal.misc.JavaIOFilePermissionAccess;
import jdk.internal.misc.SharedSecrets;
import sun.nio.fs.DefaultFileSystemProvider;
import sun.security.action.GetPropertyAction;
import sun.security.util.FilePermCompat;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "lock", "nullness", "index" })
public final class FilePermission extends Permission implements Serializable {

    public FilePermission(String path, String actions) {
    }

    @Override
    public boolean implies(@Nullable Permission p);

    @Pure
    @Override
    public boolean equals(@GuardSatisfied FilePermission this, @GuardSatisfied @Nullable Object obj);

    @Pure
    @Override
    public int hashCode(@GuardSatisfied FilePermission this);

    @Override
    public String getActions();

    @Override
    public PermissionCollection newPermissionCollection();
}
