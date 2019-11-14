package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;
import sun.net.util.IPAddressUtil;
import sun.net.www.ParseUtil;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class URLStreamHandler {

    protected abstract URLConnection openConnection(URL u) throws IOException;

    protected URLConnection openConnection(URL u, Proxy p) throws IOException;

    protected void parseURL(URL u, String spec, int start, int limit);

    protected int getDefaultPort();

    protected boolean equals(URL u1, URL u2);

    protected int hashCode(URL u);

    protected boolean sameFile(URL u1, URL u2);

    protected synchronized InetAddress getHostAddress(URL u);

    protected boolean hostsEqual(URL u1, URL u2);

    protected String toExternalForm(URL u);

    protected void setURL(URL u, String protocol, String host, int port, String authority, String userInfo, String path, String query, String ref);

    @Deprecated
    protected void setURL(URL u, String protocol, String host, int port, String file, String ref);
}
