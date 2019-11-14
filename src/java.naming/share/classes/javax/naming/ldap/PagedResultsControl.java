package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import com.sun.jndi.ldap.Ber;
import com.sun.jndi.ldap.BerEncoder;

@AnnotatedFor({ "interning" })
final public class PagedResultsControl extends BasicControl {

    @Interned
    public static final String OID = "1.2.840.113556.1.4.319";

    private static final byte[] EMPTY_COOKIE = new byte[0];

    private static final long serialVersionUID = 6684806685736844298L;

    public PagedResultsControl(int pageSize, boolean criticality) throws IOException {
        super(OID, criticality, null);
        value = setEncodedValue(pageSize, EMPTY_COOKIE);
    }

    public PagedResultsControl(int pageSize, byte[] cookie, boolean criticality) throws IOException {
        super(OID, criticality, null);
        if (cookie == null) {
            cookie = EMPTY_COOKIE;
        }
        value = setEncodedValue(pageSize, cookie);
    }

    private byte[] setEncodedValue(int pageSize, byte[] cookie) throws IOException;
}
