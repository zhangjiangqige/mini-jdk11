package javax.security.sasl;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.security.auth.callback.CallbackHandler;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;

@AnnotatedFor({ "interning" })
public class Sasl {

    @Interned
    public static final String QOP;

    @Interned
    public static final String STRENGTH;

    public static final String SERVER_AUTH;

    public static final String BOUND_SERVER_NAME;

    @Interned
    public static final String MAX_BUFFER;

    @Interned
    public static final String RAW_SEND_SIZE;

    @Interned
    public static final String REUSE;

    public static final String POLICY_NOPLAINTEXT;

    public static final String POLICY_NOACTIVE;

    public static final String POLICY_NODICTIONARY;

    public static final String POLICY_NOANONYMOUS;

    public static final String POLICY_FORWARD_SECRECY;

    public static final String POLICY_PASS_CREDENTIALS;

    @Interned
    public static final String CREDENTIALS;

    public static SaslClient createSaslClient(String[] mechanisms, String authorizationId, String protocol, String serverName, Map<String, ?> props, CallbackHandler cbh) throws SaslException;

    public static SaslServer createSaslServer(String mechanism, String protocol, String serverName, Map<String, ?> props, javax.security.auth.callback.CallbackHandler cbh) throws SaslException;

    public static Enumeration<SaslClientFactory> getSaslClientFactories();

    public static Enumeration<SaslServerFactory> getSaslServerFactories();
}
