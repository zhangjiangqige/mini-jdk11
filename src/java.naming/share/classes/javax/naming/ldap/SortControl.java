package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import com.sun.jndi.ldap.Ber;
import com.sun.jndi.ldap.BerEncoder;

@AnnotatedFor({ "interning" })
final public class SortControl extends BasicControl {

    @Interned
    public static final String OID;

    public SortControl(String sortBy, boolean criticality) throws IOException {
    }

    public SortControl(String[] sortBy, boolean criticality) throws IOException {
    }

    public SortControl(SortKey[] sortBy, boolean criticality) throws IOException {
    }
}
