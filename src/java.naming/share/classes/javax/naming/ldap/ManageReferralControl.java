package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
final public class ManageReferralControl extends BasicControl {

    @Interned
    public static final String OID = "2.16.840.1.113730.3.4.2";

    private static final long serialVersionUID = 3017756160149982566L;

    public ManageReferralControl() {
        super(OID, true, null);
    }

    public ManageReferralControl(boolean criticality) {
        super(OID, criticality, null);
    }
}
