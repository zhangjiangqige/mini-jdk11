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

    public URL(String protocol, String host, int port, String file) throws MalformedURLException {
    }

    public URL(String protocol, String host, String file) throws MalformedURLException {
    }

    public URL(String protocol, String host, int port, String file, @Nullable URLStreamHandler handler) throws MalformedURLException {
    }

    public URL(String spec) throws MalformedURLException {
    }

    public URL(@Nullable URL context, String spec) throws MalformedURLException {
    }

    public URL(@Nullable URL context, String spec, @Nullable URLStreamHandler handler) throws MalformedURLException {
    }

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

    public static void setURLStreamHandlerFactory(URLStreamHandlerFactory fac);
}
