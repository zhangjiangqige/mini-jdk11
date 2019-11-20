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

    public TrustAnchor(X509Certificate trustedCert, byte[] nameConstraints) {
    }

    public TrustAnchor(X500Principal caPrincipal, PublicKey pubKey, byte[] nameConstraints) {
    }

    public TrustAnchor(String caName, PublicKey pubKey, byte[] nameConstraints) {
    }

    public final X509Certificate getTrustedCert();

    public final X500Principal getCA();

    public final String getCAName();

    public final PublicKey getCAPublicKey();

    public final byte[] getNameConstraints();

    public String toString();
}
