package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.AccessController;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.Security;
import java.util.Objects;
import sun.security.jca.*;
import sun.security.jca.GetInstance.Instance;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CertPathValidator {

    private static final String CPV_TYPE = "certpathvalidator.type";

    private final CertPathValidatorSpi validatorSpi;

    private final Provider provider;

    private final String algorithm;

    protected CertPathValidator(CertPathValidatorSpi validatorSpi, Provider provider, String algorithm) {
        this.validatorSpi = validatorSpi;
        this.provider = provider;
        this.algorithm = algorithm;
    }

    public static CertPathValidator getInstance(String algorithm) throws NoSuchAlgorithmException;

    public static CertPathValidator getInstance(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static CertPathValidator getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException;

    public final Provider getProvider();

    public final String getAlgorithm();

    public final CertPathValidatorResult validate(CertPath certPath, CertPathParameters params) throws CertPathValidatorException, InvalidAlgorithmParameterException;

    public static final String getDefaultType();

    public final CertPathChecker getRevocationChecker();
}
