package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.security.Provider;
import java.security.Security;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import sun.security.jca.*;
import sun.security.jca.GetInstance.Instance;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CertificateFactory {

    protected CertificateFactory(CertificateFactorySpi certFacSpi, Provider provider, String type) {
    }

    public static final CertificateFactory getInstance(String type) throws CertificateException;

    public static final CertificateFactory getInstance(String type, String provider) throws CertificateException, NoSuchProviderException;

    public static final CertificateFactory getInstance(String type, Provider provider) throws CertificateException;

    public final Provider getProvider();

    public final String getType();

    public final Certificate generateCertificate(InputStream inStream) throws CertificateException;

    public final Iterator<String> getCertPathEncodings();

    public final CertPath generateCertPath(InputStream inStream) throws CertificateException;

    public final CertPath generateCertPath(InputStream inStream, String encoding) throws CertificateException;

    public final CertPath generateCertPath(List<? extends Certificate> certificates) throws CertificateException;

    public final Collection<? extends Certificate> generateCertificates(InputStream inStream) throws CertificateException;

    public final CRL generateCRL(InputStream inStream) throws CRLException;

    public final Collection<? extends CRL> generateCRLs(InputStream inStream) throws CRLException;
}
