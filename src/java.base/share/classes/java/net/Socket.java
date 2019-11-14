package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.security.PrivilegedAction;
import java.util.Set;
import java.util.Collections;

@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public class Socket implements java.io.Closeable {

    private boolean created = false;

    private boolean bound = false;

    private boolean connected = false;

    private boolean closed = false;

    private Object closeLock = new Object();

    private boolean shutIn = false;

    private boolean shutOut = false;

    SocketImpl impl;

    private boolean oldImpl = false;

    public Socket() {
        setImpl();
    }

    public Socket(Proxy proxy) {
        if (proxy == null) {
            throw new IllegalArgumentException("Invalid Proxy");
        }
        Proxy p = proxy == Proxy.NO_PROXY ? Proxy.NO_PROXY : sun.net.ApplicationProxy.create(proxy);
        Proxy.Type type = p.type();
        if (type == Proxy.Type.SOCKS || type == Proxy.Type.HTTP) {
            SecurityManager security = System.getSecurityManager();
            InetSocketAddress epoint = (InetSocketAddress) p.address();
            if (epoint.getAddress() != null) {
                checkAddress(epoint.getAddress(), "Socket");
            }
            if (security != null) {
                if (epoint.isUnresolved())
                    epoint = new InetSocketAddress(epoint.getHostName(), epoint.getPort());
                if (epoint.isUnresolved())
                    security.checkConnect(epoint.getHostName(), epoint.getPort());
                else
                    security.checkConnect(epoint.getAddress().getHostAddress(), epoint.getPort());
            }
            impl = type == Proxy.Type.SOCKS ? new SocksSocketImpl(p) : new HttpConnectSocketImpl(p);
            impl.setSocket(this);
        } else {
            if (p == Proxy.NO_PROXY) {
                if (factory == null) {
                    impl = new PlainSocketImpl();
                    impl.setSocket(this);
                } else
                    setImpl();
            } else
                throw new IllegalArgumentException("Invalid Proxy");
        }
    }

    protected Socket(SocketImpl impl) throws SocketException {
        this.impl = impl;
        if (impl != null) {
            checkOldImpl();
            this.impl.setSocket(this);
        }
    }

    public Socket(@Nullable String host, int port) throws UnknownHostException, IOException {
        this(host != null ? new InetSocketAddress(host, port) : new InetSocketAddress(InetAddress.getByName(null), port), (SocketAddress) null, true);
    }

    public Socket(InetAddress address, int port) throws IOException {
        this(address != null ? new InetSocketAddress(address, port) : null, (SocketAddress) null, true);
    }

    public Socket(@Nullable String host, int port, @Nullable InetAddress localAddr, int localPort) throws IOException {
        this(host != null ? new InetSocketAddress(host, port) : new InetSocketAddress(InetAddress.getByName(null), port), new InetSocketAddress(localAddr, localPort), true);
    }

    public Socket(InetAddress address, int port, @Nullable InetAddress localAddr, int localPort) throws IOException {
        this(address != null ? new InetSocketAddress(address, port) : null, new InetSocketAddress(localAddr, localPort), true);
    }

    @Deprecated
    public Socket(@Nullable String host, int port, boolean stream) throws IOException {
        this(host != null ? new InetSocketAddress(host, port) : new InetSocketAddress(InetAddress.getByName(null), port), (SocketAddress) null, stream);
    }

    @Deprecated
    public Socket(InetAddress host, int port, boolean stream) throws IOException {
        this(host != null ? new InetSocketAddress(host, port) : null, new InetSocketAddress(0), stream);
    }

    private Socket(SocketAddress address, SocketAddress localAddr, boolean stream) throws IOException {
        setImpl();
        if (address == null)
            throw new NullPointerException();
        try {
            createImpl(stream);
            if (localAddr != null)
                bind(localAddr);
            connect(address);
        } catch (IOException | IllegalArgumentException | SecurityException e) {
            try {
                close();
            } catch (IOException ce) {
                e.addSuppressed(ce);
            }
            throw e;
        }
    }

    void createImpl(boolean stream) throws SocketException;

    private void checkOldImpl();

    void setImpl();

    SocketImpl getImpl() throws SocketException;

    public void connect(SocketAddress endpoint) throws IOException;

    public void connect(SocketAddress endpoint, int timeout) throws IOException;

    public void bind(@Nullable SocketAddress bindpoint) throws IOException;

    private void checkAddress(InetAddress addr, String op);

    final void postAccept();

    void setCreated();

    void setBound();

    void setConnected();

    @Nullable
    public InetAddress getInetAddress();

    public InetAddress getLocalAddress();

    public int getPort();

    public int getLocalPort();

    @Nullable
    public SocketAddress getRemoteSocketAddress();

    @Nullable
    public SocketAddress getLocalSocketAddress();

    @Nullable
    public SocketChannel getChannel();

    public InputStream getInputStream() throws IOException;

    public OutputStream getOutputStream() throws IOException;

    public void setTcpNoDelay(boolean on) throws SocketException;

    public boolean getTcpNoDelay() throws SocketException;

    public void setSoLinger(boolean on, int linger) throws SocketException;

    public int getSoLinger() throws SocketException;

    public void sendUrgentData(int data) throws IOException;

    public void setOOBInline(boolean on) throws SocketException;

    public boolean getOOBInline() throws SocketException;

    public synchronized void setSoTimeout(int timeout) throws SocketException;

    public synchronized int getSoTimeout() throws SocketException;

    public synchronized void setSendBufferSize(int size) throws SocketException;

    public synchronized int getSendBufferSize() throws SocketException;

    public synchronized void setReceiveBufferSize(int size) throws SocketException;

    public synchronized int getReceiveBufferSize() throws SocketException;

    public void setKeepAlive(boolean on) throws SocketException;

    public boolean getKeepAlive() throws SocketException;

    public void setTrafficClass(int tc) throws SocketException;

    public int getTrafficClass() throws SocketException;

    public void setReuseAddress(boolean on) throws SocketException;

    public boolean getReuseAddress() throws SocketException;

    public synchronized void close() throws IOException;

    public void shutdownInput() throws IOException;

    public void shutdownOutput() throws IOException;

    public String toString();

    public boolean isConnected();

    public boolean isBound();

    public boolean isClosed();

    public boolean isInputShutdown();

    public boolean isOutputShutdown();

    private static SocketImplFactory factory = null;

    public static synchronized void setSocketImplFactory(@Nullable SocketImplFactory fac) throws IOException;

    public void setPerformancePreferences(int connectionTime, int latency, int bandwidth);

    public <T> Socket setOption(SocketOption<T> name, T value) throws IOException;

    @SuppressWarnings("unchecked")
    public <T> T getOption(SocketOption<T> name) throws IOException;

    private static Set<SocketOption<?>> options;

    private static boolean optionsSet = false;

    public Set<SocketOption<?>> supportedOptions();
}
