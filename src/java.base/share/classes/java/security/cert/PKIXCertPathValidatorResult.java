package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.PublicKey;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PKIXCertPathValidatorResult implements CertPathValidatorResult {

    private TrustAnchor trustAnchor;

    private PolicyNode policyTree;

    private PublicKey subjectPublicKey;

    public PKIXCertPathValidatorResult(TrustAnchor trustAnchor, PolicyNode policyTree, PublicKey subjectPublicKey) {
        if (subjectPublicKey == null)
            throw new NullPointerException("subjectPublicKey must be non-null");
        if (trustAnchor == null)
            throw new NullPointerException("trustAnchor must be non-null");
        this.trustAnchor = trustAnchor;
        this.policyTree = policyTree;
        this.subjectPublicKey = subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor();

    public PolicyNode getPolicyTree();

    public PublicKey getPublicKey();

    public Object clone();

    public String toString();
}
