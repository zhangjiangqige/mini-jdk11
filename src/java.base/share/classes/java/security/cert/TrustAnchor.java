package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;
import sun.security.x509.NameConstraintsExtension;
import sun.security.x509.X500Name;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class TrustAnchor {

    private final PublicKey pubKey;

    private final String caName;

    private final X500Principal caPrincipal;

    private final X509Certificate trustedCert;

    private byte[] ncBytes;

    private NameConstraintsExtension nc;

    public TrustAnchor(X509Certificate trustedCert, byte[] nameConstraints) {
        if (trustedCert == null)
            throw new NullPointerException("the trustedCert parameter must " + "be non-null");
        this.trustedCert = trustedCert;
        this.pubKey = null;
        this.caName = null;
        this.caPrincipal = null;
        setNameConstraints(nameConstraints);
    }

    public TrustAnchor(X500Principal caPrincipal, PublicKey pubKey, byte[] nameConstraints) {
        if ((caPrincipal == null) || (pubKey == null)) {
            throw new NullPointerException();
        }
        this.trustedCert = null;
        this.caPrincipal = caPrincipal;
        this.caName = caPrincipal.getName();
        this.pubKey = pubKey;
        setNameConstraints(nameConstraints);
    }

    public TrustAnchor(String caName, PublicKey pubKey, byte[] nameConstraints) {
        if (pubKey == null)
            throw new NullPointerException("the pubKey parameter must be " + "non-null");
        if (caName == null)
            throw new NullPointerException("the caName parameter must be " + "non-null");
        if (caName.length() == 0)
            throw new IllegalArgumentException("the caName " + "parameter must be a non-empty String");
        this.caPrincipal = new X500Principal(caName);
        this.pubKey = pubKey;
        this.caName = caName;
        this.trustedCert = null;
        setNameConstraints(nameConstraints);
    }

    public final X509Certificate getTrustedCert();

    public final X500Principal getCA();

    public final String getCAName();

    public final PublicKey getCAPublicKey();

    private void setNameConstraints(byte[] bytes);

    public final byte[] getNameConstraints();

    public String toString();
}
