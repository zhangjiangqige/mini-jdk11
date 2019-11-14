package javax.net.ssl;

import org.checkerframework.checker.nullness.qual.Nullable;
import java.security.PrivateKey;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.net.Socket;

public interface X509KeyManager extends KeyManager {

    public String @Nullable [] getClientAliases(String keyType, Principal @Nullable [] issuers);

    @Nullable
    public String chooseClientAlias(String[] keyType, Principal @Nullable [] issuers, @Nullable Socket socket);

    public String @Nullable [] getServerAliases(String keyType, Principal @Nullable [] issuers);

    @Nullable
    public String chooseServerAlias(String keyType, Principal @Nullable [] issuers, @Nullable Socket socket);

    public X509Certificate @Nullable [] getCertificateChain(String alias);

    @Nullable
    public PrivateKey getPrivateKey(String alias);
}
