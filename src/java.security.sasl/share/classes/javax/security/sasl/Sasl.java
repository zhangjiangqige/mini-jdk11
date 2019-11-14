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

    private Sasl() {
    }

    @Interned
    public static final String QOP = "javax.security.sasl.qop";

    @Interned
    public static final String STRENGTH = "javax.security.sasl.strength";

    public static final String SERVER_AUTH = "javax.security.sasl.server.authentication";

    public static final String BOUND_SERVER_NAME = "javax.security.sasl.bound.server.name";

    @Interned
    public static final String MAX_BUFFER = "javax.security.sasl.maxbuffer";

    @Interned
    public static final String RAW_SEND_SIZE = "javax.security.sasl.rawsendsize";

    @Interned
    public static final String REUSE = "javax.security.sasl.reuse";

    public static final String POLICY_NOPLAINTEXT = "javax.security.sasl.policy.noplaintext";

    public static final String POLICY_NOACTIVE = "javax.security.sasl.policy.noactive";

    public static final String POLICY_NODICTIONARY = "javax.security.sasl.policy.nodictionary";

    public static final String POLICY_NOANONYMOUS = "javax.security.sasl.policy.noanonymous";

    public static final String POLICY_FORWARD_SECRECY = "javax.security.sasl.policy.forward";

    public static final String POLICY_PASS_CREDENTIALS = "javax.security.sasl.policy.credentials";

    @Interned
    public static final String CREDENTIALS = "javax.security.sasl.credentials";

    public static SaslClient createSaslClient(String[] mechanisms, String authorizationId, String protocol, String serverName, Map<String, ?> props, CallbackHandler cbh) throws SaslException;

    private static Object loadFactory(Service service) throws SaslException;

    public static SaslServer createSaslServer(String mechanism, String protocol, String serverName, Map<String, ?> props, javax.security.auth.callback.CallbackHandler cbh) throws SaslException;

    public static Enumeration<SaslClientFactory> getSaslClientFactories();

    public static Enumeration<SaslServerFactory> getSaslServerFactories();

    private static Set<Object> getFactories(String serviceName);
}
