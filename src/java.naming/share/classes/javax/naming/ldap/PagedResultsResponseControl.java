package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import com.sun.jndi.ldap.Ber;
import com.sun.jndi.ldap.BerDecoder;

@AnnotatedFor({ "interning" })
final public class PagedResultsResponseControl extends BasicControl {

    @Interned
    public static final String OID = "1.2.840.113556.1.4.319";

    private static final long serialVersionUID = -8819778744844514666L;

    private int resultSize;

    private byte[] cookie;

    public PagedResultsResponseControl(String id, boolean criticality, byte[] value) throws IOException {
        super(id, criticality, value);
        BerDecoder ber = new BerDecoder(value, 0, value.length);
        ber.parseSeq(null);
        resultSize = ber.parseInt();
        cookie = ber.parseOctetString(Ber.ASN_OCTET_STR, null);
    }

    public int getResultSize();

    public byte[] getCookie();
}
