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

    protected CertStore(CertStoreSpi storeSpi, Provider provider, String type, CertStoreParameters params) {
    }

    public final Collection<? extends Certificate> getCertificates(CertSelector selector) throws CertStoreException;

    public final Collection<? extends CRL> getCRLs(CRLSelector selector) throws CertStoreException;

    public static CertStore getInstance(String type, CertStoreParameters params) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException;

    public static CertStore getInstance(String type, CertStoreParameters params, String provider) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException;

    public static CertStore getInstance(String type, CertStoreParameters params, Provider provider) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException;

    public final CertStoreParameters getCertStoreParameters();

    public final String getType();

    public final Provider getProvider();

    public static final String getDefaultType();
}
