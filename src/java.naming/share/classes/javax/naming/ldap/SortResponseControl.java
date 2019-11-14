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
    public static final String OID = "1.2.840.113556.1.4.474";

    private static final long serialVersionUID = 5142939176006310877L;

    private int resultCode = 0;

    private String badAttrId = null;

    public SortResponseControl(String id, boolean criticality, byte[] value) throws IOException {
        super(id, criticality, value);
        BerDecoder ber = new BerDecoder(value, 0, value.length);
        ber.parseSeq(null);
        resultCode = ber.parseEnumeration();
        if ((ber.bytesLeft() > 0) && (ber.peekByte() == Ber.ASN_CONTEXT)) {
            badAttrId = ber.parseStringWithTag(Ber.ASN_CONTEXT, true, null);
        }
    }

    public boolean isSorted();

    public int getResultCode();

    public String getAttributeID();

    public NamingException getException();
}
