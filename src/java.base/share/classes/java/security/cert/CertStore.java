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
import java.util.Collection;
import java.util.Objects;
import sun.security.jca.*;
import sun.security.jca.GetInstance.Instance;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CertStore {

    private static final String CERTSTORE_TYPE = "certstore.type";

    private CertStoreSpi storeSpi;

    private Provider provider;

    private String type;

    private CertStoreParameters params;

    protected CertStore(CertStoreSpi storeSpi, Provider provider, String type, CertStoreParameters params) {
        this.storeSpi = storeSpi;
        this.provider = provider;
        this.type = type;
        if (params != null)
            this.params = (CertStoreParameters) params.clone();
    }

    public final Collection<? extends Certificate> getCertificates(CertSelector selector) throws CertStoreException;

    public final Collection<? extends CRL> getCRLs(CRLSelector selector) throws CertStoreException;

    public static CertStore getInstance(String type, CertStoreParameters params) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException;

    private static CertStore handleException(NoSuchAlgorithmException e) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException;

    public static CertStore getInstance(String type, CertStoreParameters params, String provider) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException;

    public static CertStore getInstance(String type, CertStoreParameters params, Provider provider) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException;

    public final CertStoreParameters getCertStoreParameters();

    public final String getType();

    public final Provider getProvider();

    public static final String getDefaultType();
}
