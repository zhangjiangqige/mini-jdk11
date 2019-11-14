package javax.net.ssl;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.Principal;

@AnnotatedFor("nullness")
public abstract class X509ExtendedKeyManager implements X509KeyManager {

    @SideEffectFree
    protected X509ExtendedKeyManager() {
    }

    @Nullable
    public String chooseEngineClientAlias(String[] keyType, Principal @Nullable [] issuers, @Nullable SSLEngine engine);

    @Nullable
    public String chooseEngineServerAlias(String keyType, Principal @Nullable [] issuers, @Nullable SSLEngine engine);
}
