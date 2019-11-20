package javax.net.ssl;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.Security;
import java.security.*;
import java.util.Objects;
import sun.security.jca.GetInstance;

@AnnotatedFor("nullness")
public class TrustManagerFactory {

    public static final String getDefaultAlgorithm();

    protected TrustManagerFactory(TrustManagerFactorySpi factorySpi, Provider provider, String algorithm) {
    }

    public final String getAlgorithm();

    public static final TrustManagerFactory getInstance(String algorithm) throws NoSuchAlgorithmException;

    public static final TrustManagerFactory getInstance(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static final TrustManagerFactory getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException;

    public final Provider getProvider();

    public final void init(@Nullable KeyStore ks) throws KeyStoreException;

    public final void init(ManagerFactoryParameters spec) throws InvalidAlgorithmParameterException;

    public final TrustManager[] getTrustManagers();
}
