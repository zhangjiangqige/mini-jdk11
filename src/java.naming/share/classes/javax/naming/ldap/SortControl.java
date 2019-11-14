package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import com.sun.jndi.ldap.Ber;
import com.sun.jndi.ldap.BerEncoder;

@AnnotatedFor({ "interning" })
final public class SortControl extends BasicControl {

    @Interned
    public static final String OID = "1.2.840.113556.1.4.473";

    private static final long serialVersionUID = -1965961680233330744L;

    public SortControl(String sortBy, boolean criticality) throws IOException {
        super(OID, criticality, null);
        super.value = setEncodedValue(new SortKey[] { new SortKey(sortBy) });
    }

    public SortControl(String[] sortBy, boolean criticality) throws IOException {
        super(OID, criticality, null);
        SortKey[] sortKeys = new SortKey[sortBy.length];
        for (int i = 0; i < sortBy.length; i++) {
            sortKeys[i] = new SortKey(sortBy[i]);
        }
        super.value = setEncodedValue(sortKeys);
    }

    public SortControl(SortKey[] sortBy, boolean criticality) throws IOException {
        super(OID, criticality, null);
        super.value = setEncodedValue(sortBy);
    }

    private byte[] setEncodedValue(SortKey[] sortKeys) throws IOException;
}
