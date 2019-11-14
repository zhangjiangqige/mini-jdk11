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
public class CertPathBuilder {

    private static final String CPB_TYPE = "certpathbuilder.type";

    private final CertPathBuilderSpi builderSpi;

    private final Provider provider;

    private final String algorithm;

    protected CertPathBuilder(CertPathBuilderSpi builderSpi, Provider provider, String algorithm) {
        this.builderSpi = builderSpi;
        this.provider = provider;
        this.algorithm = algorithm;
    }

    public static CertPathBuilder getInstance(String algorithm) throws NoSuchAlgorithmException;

    public static CertPathBuilder getInstance(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static CertPathBuilder getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException;

    public final Provider getProvider();

    public final String getAlgorithm();

    public final CertPathBuilderResult build(CertPathParameters params) throws CertPathBuilderException, InvalidAlgorithmParameterException;

    public static final String getDefaultType();

    public final CertPathChecker getRevocationChecker();
}
