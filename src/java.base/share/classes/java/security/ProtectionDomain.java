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

    private static final boolean filePermCompatInPD = "true".equals(GetPropertyAction.privilegedGetProperty("jdk.security.filePermCompat"));

    private static class JavaSecurityAccessImpl implements JavaSecurityAccess {

        private JavaSecurityAccessImpl() {
        }

        @Override
        public <T> T doIntersectionPrivilege(PrivilegedAction<T> action, final AccessControlContext stack, final AccessControlContext context);

        @Override
        public <T> T doIntersectionPrivilege(PrivilegedAction<T> action, AccessControlContext context);

        @Override
        public ProtectionDomain[] getProtectDomains(AccessControlContext context);

        private static AccessControlContext getCombinedACC(AccessControlContext context, AccessControlContext stack);

        @Override
        public ProtectionDomainCache getProtectionDomainCache();
    }

    static {
        SharedSecrets.setJavaSecurityAccess(new JavaSecurityAccessImpl());
    }

    @Nullable
    private CodeSource codesource;

    @Nullable
    private ClassLoader classloader;

    private Principal[] principals;

    @Nullable
    private PermissionCollection permissions;

    private boolean hasAllPerm = false;

    private final boolean staticPermissions;

    final Key key = new Key();

    public ProtectionDomain(@Nullable CodeSource codesource, @Nullable PermissionCollection permissions) {
        this.codesource = codesource;
        if (permissions != null) {
            this.permissions = permissions;
            this.permissions.setReadOnly();
            if (permissions instanceof Permissions && ((Permissions) permissions).allPermission != null) {
                hasAllPerm = true;
            }
        }
        this.classloader = null;
        this.principals = new Principal[0];
        staticPermissions = true;
    }

    public ProtectionDomain(@Nullable CodeSource codesource, @Nullable PermissionCollection permissions, @Nullable ClassLoader classloader, Principal[] principals) {
        this.codesource = codesource;
        if (permissions != null) {
            this.permissions = permissions;
            this.permissions.setReadOnly();
            if (permissions instanceof Permissions && ((Permissions) permissions).allPermission != null) {
                hasAllPerm = true;
            }
        }
        this.classloader = classloader;
        this.principals = (principals != null ? principals.clone() : new Principal[0]);
        staticPermissions = false;
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

    boolean impliesWithAltFilePerm(Permission perm);

    boolean impliesCreateAccessControlContext();

    @Override
    public String toString();

    private static class DebugHolder {

        private static final Debug debug = Debug.getInstance("domain");
    }

    private static boolean seeAllp();

    private PermissionCollection mergePermissions();

    final class Key {
    }
}
