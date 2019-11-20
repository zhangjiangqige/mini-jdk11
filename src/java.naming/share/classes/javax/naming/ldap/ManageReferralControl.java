package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
final public class ManageReferralControl extends BasicControl {

    @Interned
    public static final String OID;

    public ManageReferralControl() {
    }

    public ManageReferralControl(boolean criticality) {
    }
}
