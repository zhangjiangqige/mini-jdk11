package java.net;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AnnotatedFor("nullness")
class HttpConnectSocketImpl extends PlainSocketImpl {

    private static final String httpURLClazzStr = "sun.net.www.protocol.http.HttpURLConnection";

    private static final String netClientClazzStr = "sun.net.NetworkClient";

    private static final String doTunnelingStr = "doTunneling";

    private static final Field httpField;

    private static final Field serverSocketField;

    private static final Method doTunneling;

    private final String server;

    private InetSocketAddress external_address;

    private HashMap<Integer, Object> optionsMap = new HashMap<>();

    static {
        try {
            Class<?> httpClazz = Class.forName(httpURLClazzStr, true, null);
            httpField = httpClazz.getDeclaredField("http");
            doTunneling = httpClazz.getDeclaredMethod(doTunnelingStr);
            Class<?> netClientClazz = Class.forName(netClientClazzStr, true, null);
            serverSocketField = netClientClazz.getDeclaredField("serverSocket");
            java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<>() {

                public Void run() {
                    httpField.setAccessible(true);
                    serverSocketField.setAccessible(true);
                    return null;
                }
            });
        } catch (ReflectiveOperationException x) {
            throw new InternalError("Should not reach here", x);
        }
    }

    HttpConnectSocketImpl(String server, int port) {
        this.server = server;
        this.port = port;
    }

    HttpConnectSocketImpl(Proxy proxy) {
        SocketAddress a = proxy.address();
        if (!(a instanceof InetSocketAddress))
            throw new IllegalArgumentException("Unsupported address type");
        InetSocketAddress ad = (InetSocketAddress) a;
        server = ad.getHostString();
        port = ad.getPort();
    }

    @Override
    protected void connect(SocketAddress endpoint, int timeout) throws IOException;

    @Override
    public void setOption(int opt, Object val) throws SocketException;

    private Socket privilegedDoTunnel(final String urlString, final int timeout) throws IOException;

    private Socket doTunnel(String urlString, int connectTimeout) throws IOException;

    private void doTunneling(HttpURLConnection conn);

    @Override
    protected InetAddress getInetAddress();

    @Override
    protected int getPort();

    @Override
    protected int getLocalPort();
}
