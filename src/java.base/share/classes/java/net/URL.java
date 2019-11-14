package java.net;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.InputStream;
import java.net.spi.URLStreamHandlerProvider;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Hashtable;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.ObjectStreamField;
import java.io.ObjectInputStream.GetField;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import jdk.internal.misc.JavaNetURLAccess;
import jdk.internal.misc.SharedSecrets;
import sun.security.util.SecurityConstants;
import sun.security.action.GetPropertyAction;

@AnnotatedFor("nullness")
public final class URL implements java.io.Serializable {

    static final String BUILTIN_HANDLERS_PREFIX = "sun.net.www.protocol";

    static final long serialVersionUID = -7627629688361524110L;

    private static final String protocolPathProp = "java.protocol.handler.pkgs";

    private String protocol;

    private String host;

    private int port = -1;

    private String file;

    private transient String query;

    private String authority;

    private transient String path;

    private transient String userInfo;

    private String ref;

    @Nullable
    transient InetAddress hostAddress;

    transient URLStreamHandler handler;

    private int hashCode = -1;

    private transient UrlDeserializedState tempState;

    public URL(String protocol, String host, int port, String file) throws MalformedURLException {
        this(protocol, host, port, file, null);
    }

    public URL(String protocol, String host, String file) throws MalformedURLException {
        this(protocol, host, -1, file);
    }

    public URL(String protocol, String host, int port, String file, @Nullable URLStreamHandler handler) throws MalformedURLException {
        if (handler != null) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                checkSpecifyHandler(sm);
            }
        }
        protocol = toLowerCase(protocol);
        this.protocol = protocol;
        if (host != null) {
            if (host.indexOf(':') >= 0 && !host.startsWith("[")) {
                host = "[" + host + "]";
            }
            this.host = host;
            if (port < -1) {
                throw new MalformedURLException("Invalid port number :" + port);
            }
            this.port = port;
            authority = (port == -1) ? host : host + ":" + port;
        }
        int index = file.indexOf('#');
        this.ref = index < 0 ? null : file.substring(index + 1);
        file = index < 0 ? file : file.substring(0, index);
        int q = file.lastIndexOf('?');
        if (q != -1) {
            this.query = file.substring(q + 1);
            this.path = file.substring(0, q);
            this.file = path + "?" + query;
        } else {
            this.path = file;
            this.file = path;
        }
        if (handler == null && (handler = getURLStreamHandler(protocol)) == null) {
            throw new MalformedURLException("unknown protocol: " + protocol);
        }
        this.handler = handler;
    }

    public URL(String spec) throws MalformedURLException {
        this(null, spec);
    }

    public URL(@Nullable URL context, String spec) throws MalformedURLException {
        this(context, spec, null);
    }

    public URL(@Nullable URL context, String spec, @Nullable URLStreamHandler handler) throws MalformedURLException {
        String original = spec;
        int i, limit, c;
        int start = 0;
        String newProtocol = null;
        boolean aRef = false;
        boolean isRelative = false;
        if (handler != null) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                checkSpecifyHandler(sm);
            }
        }
        try {
            limit = spec.length();
            while ((limit > 0) && (spec.charAt(limit - 1) <= ' ')) {
                limit--;
            }
            while ((start < limit) && (spec.charAt(start) <= ' ')) {
                start++;
            }
            if (spec.regionMatches(true, start, "url:", 0, 4)) {
                start += 4;
            }
            if (start < spec.length() && spec.charAt(start) == '#') {
                aRef = true;
            }
            for (i = start; !aRef && (i < limit) && ((c = spec.charAt(i)) != '/'); i++) {
                if (c == ':') {
                    String s = toLowerCase(spec.substring(start, i));
                    if (isValidProtocol(s)) {
                        newProtocol = s;
                        start = i + 1;
                    }
                    break;
                }
            }
            protocol = newProtocol;
            if ((context != null) && ((newProtocol == null) || newProtocol.equalsIgnoreCase(context.protocol))) {
                if (handler == null) {
                    handler = context.handler;
                }
                if (context.path != null && context.path.startsWith("/"))
                    newProtocol = null;
                if (newProtocol == null) {
                    protocol = context.protocol;
                    authority = context.authority;
                    userInfo = context.userInfo;
                    host = context.host;
                    port = context.port;
                    file = context.file;
                    path = context.path;
                    isRelative = true;
                }
            }
            if (protocol == null) {
                throw new MalformedURLException("no protocol: " + original);
            }
            if (handler == null && (handler = getURLStreamHandler(protocol)) == null) {
                throw new MalformedURLException("unknown protocol: " + protocol);
            }
            this.handler = handler;
            i = spec.indexOf('#', start);
            if (i >= 0) {
                ref = spec.substring(i + 1, limit);
                limit = i;
            }
            if (isRelative && start == limit) {
                query = context.query;
                if (ref == null) {
                    ref = context.ref;
                }
            }
            handler.parseURL(this, spec, start, limit);
        } catch (MalformedURLException e) {
            throw e;
        } catch (Exception e) {
            MalformedURLException exception = new MalformedURLException(e.getMessage());
            exception.initCause(e);
            throw exception;
        }
    }

    static URL fromURI(URI uri) throws MalformedURLException;

    private boolean isValidProtocol(String protocol);

    private void checkSpecifyHandler(SecurityManager sm);

    void set(String protocol, String host, int port, String file, String ref);

    void set(String protocol, String host, int port, String authority, String userInfo, String path, String query, String ref);

    public String getQuery();

    public String getPath();

    public String getUserInfo();

    public String getAuthority();

    public int getPort();

    public int getDefaultPort();

    public String getProtocol();

    public String getHost();

    public String getFile();

    public String getRef();

    public boolean equals(Object obj);

    public synchronized int hashCode();

    public boolean sameFile(URL other);

    public String toString();

    public String toExternalForm();

    public URI toURI() throws URISyntaxException;

    public URLConnection openConnection() throws java.io.IOException;

    public URLConnection openConnection(Proxy proxy) throws java.io.IOException;

    public final InputStream openStream() throws java.io.IOException;

    public final Object getContent() throws java.io.IOException;

    @Nullable
    public final Object getContent(Class<?>[] classes) throws java.io.IOException;

    @MonotonicNonNull
    private static volatile URLStreamHandlerFactory factory;

    public static void setURLStreamHandlerFactory(URLStreamHandlerFactory fac);

    private static final URLStreamHandlerFactory defaultFactory = new DefaultFactory();

    private static class DefaultFactory implements URLStreamHandlerFactory {

        private static String PREFIX = "sun.net.www.protocol";

        public URLStreamHandler createURLStreamHandler(String protocol);
    }

    private static URLStreamHandler lookupViaProperty(String protocol);

    private static Iterator<URLStreamHandlerProvider> providers();

    private static ThreadLocal<Object> gate = new ThreadLocal<>();

    private static URLStreamHandler lookupViaProviders(final String protocol);

    static String toLowerCase(String protocol);

    static boolean isOverrideable(String protocol);

    static Hashtable<String, URLStreamHandler> handlers = new Hashtable<>();

    private static final Object streamHandlerLock = new Object();

    static URLStreamHandler getURLStreamHandler(String protocol);

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("protocol", String.class), new ObjectStreamField("host", String.class), new ObjectStreamField("port", int.class), new ObjectStreamField("authority", String.class), new ObjectStreamField("file", String.class), new ObjectStreamField("ref", String.class), new ObjectStreamField("hashCode", int.class) };

    private synchronized void writeObject(java.io.ObjectOutputStream s) throws IOException;

    private synchronized void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException;

    private Object readResolve() throws ObjectStreamException;

    private URL setDeserializedFields(URLStreamHandler handler);

    private URL fabricateNewURL() throws InvalidObjectException;

    private boolean isBuiltinStreamHandler(String handlerClassName);

    private void resetState();

    private void setSerializedHashCode(int hc);

    static {
        SharedSecrets.setJavaNetURLAccess(new JavaNetURLAccess() {

            @Override
            public URLStreamHandler getHandler(URL u) {
                return u.handler;
            }
        });
    }
}

final class UrlDeserializedState {

    private final String protocol;

    private final String host;

    private final int port;

    private final String authority;

    private final String file;

    private final String ref;

    private final int hashCode;

    public UrlDeserializedState(String protocol, String host, int port, String authority, String file, String ref, int hashCode) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.authority = authority;
        this.file = file;
        this.ref = ref;
        this.hashCode = hashCode;
    }

    String getProtocol();

    String getHost();

    String getAuthority();

    int getPort();

    String getFile();

    String getRef();

    int getHashCode();

    String reconstituteUrlString();
}
