package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.util.List;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ProxySelector {

    public static ProxySelector getDefault();

    public static void setDefault(ProxySelector ps);

    public abstract List<Proxy> select(URI uri);

    public abstract void connectFailed(URI uri, SocketAddress sa, IOException ioe);

    public static ProxySelector of(InetSocketAddress proxyAddress);
}
