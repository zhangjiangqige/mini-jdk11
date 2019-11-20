package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Deterministic;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import jdk.internal.misc.JavaSecurityAccess;
import jdk.internal.misc.SharedSecrets;
import sun.security.action.GetPropertyAction;
import sun.security.provider.PolicyFile;
import sun.security.util.Debug;
import sun.security.util.FilePermCompat;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public class ProtectionDomain {

    public ProtectionDomain(@Nullable CodeSource codesource, @Nullable PermissionCollection permissions) {
    }

    public ProtectionDomain(@Nullable CodeSource codesource, @Nullable PermissionCollection permissions, @Nullable ClassLoader classloader, Principal[] principals) {
    }

    @Deterministic
    @Nullable
    public final CodeSource getCodeSource();

    @Nullable
    public final ClassLoader getClassLoader();

    public final Principal[] getPrincipals();

    @Nullable
    public final PermissionCollection getPermissions();

    public final boolean staticPermissionsOnly();

    public boolean implies(Permission perm);

    @Override
    public String toString();
}
