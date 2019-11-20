package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Enumeration;
import java.util.WeakHashMap;
import java.util.Objects;
import sun.security.jca.GetInstance;
import sun.security.util.Debug;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Policy {

    public static final PermissionCollection UNSUPPORTED_EMPTY_COLLECTION;

    public static Policy getPolicy();

    public static void setPolicy(Policy p);

    public static Policy getInstance(String type, Policy.Parameters params) throws NoSuchAlgorithmException;

    public static Policy getInstance(String type, Policy.Parameters params, String provider) throws NoSuchProviderException, NoSuchAlgorithmException;

    public static Policy getInstance(String type, Policy.Parameters params, Provider provider) throws NoSuchAlgorithmException;

    public Provider getProvider();

    public String getType();

    public Policy.Parameters getParameters();

    public PermissionCollection getPermissions(CodeSource codesource);

    public PermissionCollection getPermissions(ProtectionDomain domain);

    public boolean implies(ProtectionDomain domain, Permission permission);

    public void refresh();

    public static interface Parameters {
    }
}
