package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Iterator;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.naming.ConfigurationException;
import javax.naming.NamingException;
import com.sun.naming.internal.VersionHelper;
import java.util.ServiceLoader;
import java.util.ServiceConfigurationError;

@AnnotatedFor({ "interning" })
public class StartTlsRequest implements ExtendedRequest {

    @Interned
    public static final String OID;

    public StartTlsRequest() {
    }

    public String getID();

    public byte[] getEncodedValue();

    public ExtendedResponse createExtendedResponse(String id, byte[] berValue, int offset, int length) throws NamingException;
}
