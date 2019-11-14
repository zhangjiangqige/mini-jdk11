package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.net.www.protocol.http.AuthenticatorKeys;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Authenticator {

    private static volatile Authenticator theAuthenticator;

    private String requestingHost;

    private InetAddress requestingSite;

    private int requestingPort;

    private String requestingProtocol;

    private String requestingPrompt;

    private String requestingScheme;

    private URL requestingURL;

    private RequestorType requestingAuthType;

    private final String key = AuthenticatorKeys.computeKey(this);

    public enum RequestorType {

        PROXY, SERVER
    }

    private void reset();

    public static synchronized void setDefault(Authenticator a);

    public static Authenticator getDefault();

    public static PasswordAuthentication requestPasswordAuthentication(InetAddress addr, int port, String protocol, String prompt, String scheme);

    public static PasswordAuthentication requestPasswordAuthentication(String host, InetAddress addr, int port, String protocol, String prompt, String scheme);

    public static PasswordAuthentication requestPasswordAuthentication(String host, InetAddress addr, int port, String protocol, String prompt, String scheme, URL url, RequestorType reqType);

    public static PasswordAuthentication requestPasswordAuthentication(Authenticator authenticator, String host, InetAddress addr, int port, String protocol, String prompt, String scheme, URL url, RequestorType reqType);

    public PasswordAuthentication requestPasswordAuthenticationInstance(String host, InetAddress addr, int port, String protocol, String prompt, String scheme, URL url, RequestorType reqType);

    protected final String getRequestingHost();

    protected final InetAddress getRequestingSite();

    protected final int getRequestingPort();

    protected final String getRequestingProtocol();

    protected final String getRequestingPrompt();

    protected final String getRequestingScheme();

    protected PasswordAuthentication getPasswordAuthentication();

    protected URL getRequestingURL();

    protected RequestorType getRequestorType();

    static String getKey(Authenticator a);

    static {
        AuthenticatorKeys.setAuthenticatorKeyAccess(Authenticator::getKey);
    }
}
