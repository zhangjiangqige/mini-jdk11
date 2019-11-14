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

    private static final Debug kdebug = Debug.getInstance("keystore");

    private static final Debug pdebug = Debug.getInstance("provider", "Provider");

    private static final boolean skipDebug = Debug.isOn("engine=") && !Debug.isOn("keystore");

    private static final String KEYSTORE_TYPE = "keystore.type";

    private String type;

    private Provider provider;

    private KeyStoreSpi keyStoreSpi;

    private boolean initialized = false;

    public static interface LoadStoreParameter {

        public ProtectionParameter getProtectionParameter();
    }

    public static interface ProtectionParameter {
    }

    public static class PasswordProtection implements ProtectionParameter, javax.security.auth.Destroyable {

        private final char[] password;

        private final String protectionAlgorithm;

        private final AlgorithmParameterSpec protectionParameters;

        private volatile boolean destroyed = false;

        public PasswordProtection(char[] password) {
            this.password = (password == null) ? null : password.clone();
            this.protectionAlgorithm = null;
            this.protectionParameters = null;
        }

        public PasswordProtection(char[] password, String protectionAlgorithm, AlgorithmParameterSpec protectionParameters) {
            if (protectionAlgorithm == null) {
                throw new NullPointerException("invalid null input");
            }
            this.password = (password == null) ? null : password.clone();
            this.protectionAlgorithm = protectionAlgorithm;
            this.protectionParameters = protectionParameters;
        }

        public String getProtectionAlgorithm();

        public AlgorithmParameterSpec getProtectionParameters();

        public synchronized char[] getPassword();

        public synchronized void destroy() throws DestroyFailedException;

        public synchronized boolean isDestroyed();
    }

    public static class CallbackHandlerProtection implements ProtectionParameter {

        private final CallbackHandler handler;

        public CallbackHandlerProtection(CallbackHandler handler) {
            if (handler == null) {
                throw new NullPointerException("handler must not be null");
            }
            this.handler = handler;
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

        private final PrivateKey privKey;

        private final Certificate[] chain;

        private final Set<Attribute> attributes;

        public PrivateKeyEntry(PrivateKey privateKey, Certificate[] chain) {
            this(privateKey, chain, Collections.<Attribute>emptySet());
        }

        public PrivateKeyEntry(PrivateKey privateKey, Certificate[] chain, Set<Attribute> attributes) {
            if (privateKey == null || chain == null || attributes == null) {
                throw new NullPointerException("invalid null input");
            }
            if (chain.length == 0) {
                throw new IllegalArgumentException("invalid zero-length input chain");
            }
            Certificate[] clonedChain = chain.clone();
            String certType = clonedChain[0].getType();
            for (int i = 1; i < clonedChain.length; i++) {
                if (!certType.equals(clonedChain[i].getType())) {
                    throw new IllegalArgumentException("chain does not contain certificates " + "of the same type");
                }
            }
            if (!privateKey.getAlgorithm().equals(clonedChain[0].getPublicKey().getAlgorithm())) {
                throw new IllegalArgumentException("private key algorithm does not match " + "algorithm of public key in end entity " + "certificate (at index 0)");
            }
            this.privKey = privateKey;
            if (clonedChain[0] instanceof X509Certificate && !(clonedChain instanceof X509Certificate[])) {
                this.chain = new X509Certificate[clonedChain.length];
                System.arraycopy(clonedChain, 0, this.chain, 0, clonedChain.length);
            } else {
                this.chain = clonedChain;
            }
            this.attributes = Collections.unmodifiableSet(new HashSet<>(attributes));
        }

        public PrivateKey getPrivateKey();

        public Certificate[] getCertificateChain();

        public Certificate getCertificate();

        @Override
        public Set<Attribute> getAttributes();

        public String toString();
    }

    public static final class SecretKeyEntry implements Entry {

        private final SecretKey sKey;

        private final Set<Attribute> attributes;

        public SecretKeyEntry(SecretKey secretKey) {
            if (secretKey == null) {
                throw new NullPointerException("invalid null input");
            }
            this.sKey = secretKey;
            this.attributes = Collections.<Attribute>emptySet();
        }

        public SecretKeyEntry(SecretKey secretKey, Set<Attribute> attributes) {
            if (secretKey == null || attributes == null) {
                throw new NullPointerException("invalid null input");
            }
            this.sKey = secretKey;
            this.attributes = Collections.unmodifiableSet(new HashSet<>(attributes));
        }

        public SecretKey getSecretKey();

        @Override
        public Set<Attribute> getAttributes();

        public String toString();
    }

    public static final class TrustedCertificateEntry implements Entry {

        private final Certificate cert;

        private final Set<Attribute> attributes;

        public TrustedCertificateEntry(Certificate trustedCert) {
            if (trustedCert == null) {
                throw new NullPointerException("invalid null input");
            }
            this.cert = trustedCert;
            this.attributes = Collections.<Attribute>emptySet();
        }

        public TrustedCertificateEntry(Certificate trustedCert, Set<Attribute> attributes) {
            if (trustedCert == null || attributes == null) {
                throw new NullPointerException("invalid null input");
            }
            this.cert = trustedCert;
            this.attributes = Collections.unmodifiableSet(new HashSet<>(attributes));
        }

        public Certificate getTrustedCertificate();

        @Override
        public Set<Attribute> getAttributes();

        public String toString();
    }

    protected KeyStore(KeyStoreSpi keyStoreSpi, Provider provider, String type) {
        this.keyStoreSpi = keyStoreSpi;
        this.provider = provider;
        this.type = type;
        if (!skipDebug && pdebug != null) {
            pdebug.println("KeyStore." + type.toUpperCase() + " type from: " + getProviderName());
        }
    }

    private String getProviderName();

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

    private static final KeyStore getInstance(File file, char[] password, LoadStoreParameter param, boolean hasPassword) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException;

    public abstract static class Builder {

        static final int MAX_CALLBACK_TRIES = 3;

        protected Builder() {
        }

        public abstract KeyStore getKeyStore() throws KeyStoreException;

        public abstract ProtectionParameter getProtectionParameter(String alias) throws KeyStoreException;

        public static Builder newInstance(final KeyStore keyStore, final ProtectionParameter protectionParameter);

        public static Builder newInstance(String type, Provider provider, File file, ProtectionParameter protection);

        public static Builder newInstance(File file, ProtectionParameter protection);

        private static final class FileBuilder extends Builder {

            private final String type;

            private final Provider provider;

            private final File file;

            private ProtectionParameter protection;

            private ProtectionParameter keyProtection;

            private final AccessControlContext context;

            private KeyStore keyStore;

            private Throwable oldException;

            FileBuilder(String type, Provider provider, File file, ProtectionParameter protection, AccessControlContext context) {
                this.type = type;
                this.provider = provider;
                this.file = file;
                this.protection = protection;
                this.context = context;
            }

            public synchronized KeyStore getKeyStore() throws KeyStoreException;

            public synchronized ProtectionParameter getProtectionParameter(String alias);
        }

        public static Builder newInstance(final String type, final Provider provider, final ProtectionParameter protection);
    }

    static class SimpleLoadStoreParameter implements LoadStoreParameter {

        private final ProtectionParameter protection;

        SimpleLoadStoreParameter(ProtectionParameter protection) {
            this.protection = protection;
        }

        public ProtectionParameter getProtectionParameter();
    }
}
