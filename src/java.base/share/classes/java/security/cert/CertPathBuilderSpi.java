package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.InvalidAlgorithmParameterException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class CertPathBuilderSpi {

    public CertPathBuilderSpi() {
    }

    public abstract CertPathBuilderResult engineBuild(CertPathParameters params) throws CertPathBuilderException, InvalidAlgorithmParameterException;

    public CertPathChecker engineGetRevocationChecker();
}
