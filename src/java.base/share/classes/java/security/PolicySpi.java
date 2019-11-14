package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class PolicySpi {

    protected abstract boolean engineImplies(ProtectionDomain domain, Permission permission);

    protected void engineRefresh();

    protected PermissionCollection engineGetPermissions(CodeSource codesource);

    protected PermissionCollection engineGetPermissions(ProtectionDomain domain);
}
