package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import com.sun.jndi.ldap.Ber;
import com.sun.jndi.ldap.BerEncoder;

@AnnotatedFor({ "interning" })
final public class PagedResultsControl extends BasicControl {

    @Interned
    public static final String OID;

    public PagedResultsControl(int pageSize, boolean criticality) throws IOException {
    }

    public PagedResultsControl(int pageSize, byte[] cookie, boolean criticality) throws IOException {
    }
}
