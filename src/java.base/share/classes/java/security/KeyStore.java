package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.*;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.callback.*;
import sun.security.util.Debug;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class KeyStore {

    public static interface LoadStoreParameter {

        public ProtectionParameter getProtectionParameter();
    }

    public static interface ProtectionParameter {
    }

    public static class PasswordProtection implements ProtectionParameter, javax.security.auth.Destroyable {

        public PasswordProtection(char[] password) {
        }

        public PasswordProtection(char[] password, String protectionAlgorithm, AlgorithmParameterSpec protectionParameters) {
        }

        public String getProtectionAlgorithm();

        public AlgorithmParameterSpec getProtectionParameters();

        public synchronized char[] getPassword();

        public synchronized void destroy() throws DestroyFailedException;

        public synchronized boolean isDestroyed();
    }

    public static class CallbackHandlerProtection implements ProtectionParameter {

        public CallbackHandlerProtection(CallbackHandler handler) {
        }

        public CallbackHandler getCallbackHandler();
    }

    public static interface Entry {

        public default Set<Attribute> getAttributes();

        public interface Attribute {

            public String getName();

            public String getValue();
        }
    }

    public static final class PrivateKeyEntry implements Entry {

        public PrivateKeyEntry(PrivateKey privateKey, Certificate[] chain) {
        }

        public PrivateKeyEntry(PrivateKey privateKey, Certificate[] chain, Set<Attribute> attributes) {
        }

        public PrivateKey getPrivateKey();

        public Certificate[] getCertificateChain();

        public Certificate getCertificate();

        @Override
        public Set<Attribute> getAttributes();

        public String toString();
    }

    public static final class SecretKeyEntry implements Entry {

        public SecretKeyEntry(SecretKey secretKey) {
        }

        public SecretKeyEntry(SecretKey secretKey, Set<Attribute> attributes) {
        }

        public SecretKey getSecretKey();

        @Override
        public Set<Attribute> getAttributes();

        public String toString();
    }

    public static final class TrustedCertificateEntry implements Entry {

        public TrustedCertificateEntry(Certificate trustedCert) {
        }

        public TrustedCertificateEntry(Certificate trustedCert, Set<Attribute> attributes) {
        }

        public Certificate getTrustedCertificate();

        @Override
        public Set<Attribute> getAttributes();

        public String toString();
    }

    protected KeyStore(KeyStoreSpi keyStoreSpi, Provider provider, String type) {
    }

    public static KeyStore getInstance(String type) throws KeyStoreException;

    public static KeyStore getInstance(String type, String provider) throws KeyStoreException, NoSuchProviderException;

    public static KeyStore getInstance(String type, Provider provider) throws KeyStoreException;

    public static final String getDefaultType();

    public final Provider getProvider();

    public final String getType();

    public final Key getKey(String alias, char[] password) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException;

    public final Certificate[] getCertificateChain(String alias) throws KeyStoreException;

    public final Certificate getCertificate(String alias) throws KeyStoreException;

    public final Date getCreationDate(String alias) throws KeyStoreException;

    public final void setKeyEntry(String alias, Key key, char[] password, Certificate[] chain) throws KeyStoreException;

    public final void setKeyEntry(String alias, byte[] key, Certificate[] chain) throws KeyStoreException;

    public final void setCertificateEntry(String alias, Certificate cert) throws KeyStoreException;

    public final void deleteEntry(String alias) throws KeyStoreException;

    public final Enumeration<String> aliases() throws KeyStoreException;

    public final boolean containsAlias(String alias) throws KeyStoreException;

    public final int size() throws KeyStoreException;

    public final boolean isKeyEntry(String alias) throws KeyStoreException;

    public final boolean isCertificateEntry(String alias) throws KeyStoreException;

    public final String getCertificateAlias(Certificate cert) throws KeyStoreException;

    public final void store(OutputStream stream, char[] password) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException;

    public final void store(LoadStoreParameter param) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException;

    public final void load(InputStream stream, char[] password) throws IOException, NoSuchAlgorithmException, CertificateException;

    public final void load(LoadStoreParameter param) throws IOException, NoSuchAlgorithmException, CertificateException;

    public final Entry getEntry(String alias, ProtectionParameter protParam) throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException;

    public final void setEntry(String alias, Entry entry, ProtectionParameter protParam) throws KeyStoreException;

    public final boolean entryInstanceOf(String alias, Class<? extends KeyStore.Entry> entryClass) throws KeyStoreException;

    public static final KeyStore getInstance(File file, char[] password) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException;

    public static final KeyStore getInstance(File file, LoadStoreParameter param) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException;

    public abstract static class Builder {

        protected Builder() {
        }

        public abstract KeyStore getKeyStore() throws KeyStoreException;

        public abstract ProtectionParameter getProtectionParameter(String alias) throws KeyStoreException;

        public static Builder newInstance(final KeyStore keyStore, final ProtectionParameter protectionParameter);

        public static Builder newInstance(String type, Provider provider, File file, ProtectionParameter protection);

        public static Builder newInstance(File file, ProtectionParameter protection);

        public static Builder newInstance(final String type, final Provider provider, final ProtectionParameter protection);
    }
}
