package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.PublicKey;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PKIXCertPathValidatorResult implements CertPathValidatorResult {

    public PKIXCertPathValidatorResult(TrustAnchor trustAnchor, PolicyNode policyTree, PublicKey subjectPublicKey) {
    }

    public TrustAnchor getTrustAnchor();

    public PolicyNode getPolicyTree();

    public PublicKey getPublicKey();

    public Object clone();

    public String toString();
}
