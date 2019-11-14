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

    public static final PermissionCollection UNSUPPORTED_EMPTY_COLLECTION = new UnsupportedEmptyCollection();

    private static class PolicyInfo {

        final Policy policy;

        final boolean initialized;

        PolicyInfo(Policy policy, boolean initialized) {
            this.policy = policy;
            this.initialized = initialized;
        }
    }

    private static volatile PolicyInfo policyInfo = new PolicyInfo(null, false);

    private static final Debug debug = Debug.getInstance("policy");

    private static final String DEFAULT_POLICY = "sun.security.provider.PolicyFile";

    private WeakHashMap<ProtectionDomain.Key, PermissionCollection> pdMapping;

    static boolean isSet();

    private static void checkPermission(String type);

    public static Policy getPolicy();

    static Policy getPolicyNoCheck();

    private static Policy loadPolicyProvider();

    public static void setPolicy(Policy p);

    private static void initPolicy(final Policy p);

    public static Policy getInstance(String type, Policy.Parameters params) throws NoSuchAlgorithmException;

    public static Policy getInstance(String type, Policy.Parameters params, String provider) throws NoSuchProviderException, NoSuchAlgorithmException;

    public static Policy getInstance(String type, Policy.Parameters params, Provider provider) throws NoSuchAlgorithmException;

    private static Policy handleException(NoSuchAlgorithmException nsae) throws NoSuchAlgorithmException;

    public Provider getProvider();

    public String getType();

    public Policy.Parameters getParameters();

    public PermissionCollection getPermissions(CodeSource codesource);

    public PermissionCollection getPermissions(ProtectionDomain domain);

    private void addStaticPerms(PermissionCollection perms, PermissionCollection statics);

    public boolean implies(ProtectionDomain domain, Permission permission);

    public void refresh();

    private static class PolicyDelegate extends Policy {

        private PolicySpi spi;

        private Provider p;

        private String type;

        private Policy.Parameters params;

        private PolicyDelegate(PolicySpi spi, Provider p, String type, Policy.Parameters params) {
            this.spi = spi;
            this.p = p;
            this.type = type;
            this.params = params;
        }

        @Override
        public String getType();

        @Override
        public Policy.Parameters getParameters();

        @Override
        public Provider getProvider();

        @Override
        public PermissionCollection getPermissions(CodeSource codesource);

        @Override
        public PermissionCollection getPermissions(ProtectionDomain domain);

        @Override
        public boolean implies(ProtectionDomain domain, Permission perm);

        @Override
        public void refresh();
    }

    public static interface Parameters {
    }

    private static class UnsupportedEmptyCollection extends PermissionCollection {

        private static final long serialVersionUID = -8492269157353014774L;

        private Permissions perms;

        public UnsupportedEmptyCollection() {
            this.perms = new Permissions();
            perms.setReadOnly();
        }

        @Override
        public void add(Permission permission);

        @Override
        public boolean implies(Permission permission);

        @Override
        public Enumeration<Permission> elements();
    }
}
