package javax.naming.ldap;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.HostnameVerifier;

@AnnotatedFor({ "interning" })
public abstract class StartTlsResponse implements ExtendedResponse {

    @Interned
    public static final String OID = "1.3.6.1.4.1.1466.20037";

    protected StartTlsResponse() {
    }

    public String getID();

    public byte[] getEncodedValue();

    public abstract void setEnabledCipherSuites(String[] suites);

    public abstract void setHostnameVerifier(HostnameVerifier verifier);

    public abstract SSLSession negotiate() throws IOException;

    public abstract SSLSession negotiate(SSLSocketFactory factory) throws IOException;

    public abstract void close() throws IOException;

    private static final long serialVersionUID = 8372842182579276418L;
}
