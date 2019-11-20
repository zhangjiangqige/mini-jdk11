package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import javax.naming.*;
import javax.naming.directory.*;
import com.sun.jndi.ldap.Ber;
import com.sun.jndi.ldap.BerDecoder;
import com.sun.jndi.ldap.LdapCtx;

@AnnotatedFor({ "interning" })
final public class SortResponseControl extends BasicControl {

    @Interned
    public static final String OID;

    public SortResponseControl(String id, boolean criticality, byte[] value) throws IOException {
    }

    public boolean isSorted();

    public int getResultCode();

    public String getAttributeID();

    public NamingException getException();
}
