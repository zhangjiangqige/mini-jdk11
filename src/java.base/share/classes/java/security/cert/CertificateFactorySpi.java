package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.security.Provider;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class CertificateFactorySpi {

    public abstract Certificate engineGenerateCertificate(InputStream inStream) throws CertificateException;

    public CertPath engineGenerateCertPath(InputStream inStream) throws CertificateException;

    public CertPath engineGenerateCertPath(InputStream inStream, String encoding) throws CertificateException;

    public CertPath engineGenerateCertPath(List<? extends Certificate> certificates) throws CertificateException;

    public Iterator<String> engineGetCertPathEncodings();

    public abstract Collection<? extends Certificate> engineGenerateCertificates(InputStream inStream) throws CertificateException;

    public abstract CRL engineGenerateCRL(InputStream inStream) throws CRLException;

    public abstract Collection<? extends CRL> engineGenerateCRLs(InputStream inStream) throws CRLException;
}
