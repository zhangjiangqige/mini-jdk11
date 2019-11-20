package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import com.sun.jndi.ldap.Ber;
import com.sun.jndi.ldap.BerDecoder;

@AnnotatedFor({ "interning" })
final public class PagedResultsResponseControl extends BasicControl {

    @Interned
    public static final String OID;

    public PagedResultsResponseControl(String id, boolean criticality, byte[] value) throws IOException {
    }

    public int getResultSize();

    public byte[] getCookie();
}
