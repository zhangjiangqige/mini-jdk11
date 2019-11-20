package com.sun.net.ssl;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.KeyManagementException;
import java.security.PrivateKey;
import java.security.Principal;
import java.security.cert.X509Certificate;

@AnnotatedFor("nullness")
@Deprecated()
public interface X509KeyManager extends KeyManager {

    public String[] getClientAliases(String keyType, Principal[] issuers);

    public String chooseClientAlias(String keyType, Principal[] issuers);

    public String[] getServerAliases(String keyType, Principal[] issuers);

    public String chooseServerAlias(String keyType, Principal[] issuers);

    public X509Certificate[] getCertificateChain(String alias);

    public PrivateKey getPrivateKey(String alias);
}
