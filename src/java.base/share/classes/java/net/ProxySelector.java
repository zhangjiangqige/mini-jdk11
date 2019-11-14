package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.util.List;
import sun.security.util.SecurityConstants;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ProxySelector {

    private static ProxySelector theProxySelector;

    static {
        try {
            Class<?> c = Class.forName("sun.net.spi.DefaultProxySelector");
            if (c != null && ProxySelector.class.isAssignableFrom(c)) {
                @SuppressWarnings("deprecation")
                ProxySelector tmp = (ProxySelector) c.newInstance();
                theProxySelector = tmp;
            }
        } catch (Exception e) {
            theProxySelector = null;
        }
    }

    public static ProxySelector getDefault();

    public static void setDefault(ProxySelector ps);

    public abstract List<Proxy> select(URI uri);

    public abstract void connectFailed(URI uri, SocketAddress sa, IOException ioe);

    public static ProxySelector of(InetSocketAddress proxyAddress);

    static class StaticProxySelector extends ProxySelector {

        private static final List<Proxy> NO_PROXY_LIST = List.of(Proxy.NO_PROXY);

        final List<Proxy> list;

        StaticProxySelector(InetSocketAddress address) {
            Proxy p;
            if (address == null) {
                p = Proxy.NO_PROXY;
            } else {
                p = new Proxy(Proxy.Type.HTTP, address);
            }
            list = List.of(p);
        }

        @Override
        public void connectFailed(URI uri, SocketAddress sa, IOException e);

        @Override
        public synchronized List<Proxy> select(URI uri);
    }
}
