package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.InvalidAlgorithmParameterException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class CertPathValidatorSpi {

    public CertPathValidatorSpi() {
    }

    public abstract CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters params) throws CertPathValidatorException, InvalidAlgorithmParameterException;

    public CertPathChecker engineGetRevocationChecker();
}
