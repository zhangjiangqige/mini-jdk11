package javax.net.ssl;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.*;
import java.util.Objects;
import sun.security.jca.GetInstance;

@AnnotatedFor("nullness")
public class SSLContext {

    protected SSLContext(SSLContextSpi contextSpi, Provider provider, String protocol) {
    }

    public static synchronized SSLContext getDefault() throws NoSuchAlgorithmException;

    public static synchronized void setDefault(SSLContext context);

    public static SSLContext getInstance(String protocol) throws NoSuchAlgorithmException;

    public static SSLContext getInstance(String protocol, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static SSLContext getInstance(String protocol, Provider provider) throws NoSuchAlgorithmException;

    public final String getProtocol();

    public final Provider getProvider();

    public final void init(KeyManager @Nullable [] km, TrustManager @Nullable [] tm, @Nullable SecureRandom random) throws KeyManagementException;

    public final SSLSocketFactory getSocketFactory();

    public final SSLServerSocketFactory getServerSocketFactory();

    public final SSLEngine createSSLEngine();

    public final SSLEngine createSSLEngine(String peerHost, int peerPort);

    @Nullable
    public final SSLSessionContext getServerSessionContext();

    @Nullable
    public final SSLSessionContext getClientSessionContext();

    public final SSLParameters getDefaultSSLParameters();

    public final SSLParameters getSupportedSSLParameters();
}
