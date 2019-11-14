package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.JavaNetSocketAccess;
import jdk.internal.misc.SharedSecrets;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.ServerSocketChannel;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Set;
import java.util.Collections;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class ServerSocket implements java.io.Closeable {

    private boolean created = false;

    private boolean bound = false;

    private boolean closed = false;

    private Object closeLock = new Object();

    private SocketImpl impl;

    private boolean oldImpl = false;

    ServerSocket(SocketImpl impl) {
        this.impl = impl;
        impl.setServerSocket(this);
    }

    public ServerSocket() throws IOException {
        setImpl();
    }

    public ServerSocket(int port) throws IOException {
        this(port, 50, null);
    }

    public ServerSocket(int port, int backlog) throws IOException {
        this(port, backlog, null);
    }

    public ServerSocket(int port, int backlog, InetAddress bindAddr) throws IOException {
        setImpl();
        if (port < 0 || port > 0xFFFF)
            throw new IllegalArgumentException("Port value out of range: " + port);
        if (backlog < 1)
            backlog = 50;
        try {
            bind(new InetSocketAddress(bindAddr, port), backlog);
        } catch (SecurityException e) {
            close();
            throw e;
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    SocketImpl getImpl() throws SocketException;

    private void checkOldImpl();

    private void setImpl();

    void createImpl() throws SocketException;

    public void bind(SocketAddress endpoint) throws IOException;

    public void bind(SocketAddress endpoint, int backlog) throws IOException;

    public InetAddress getInetAddress();

    public int getLocalPort();

    public SocketAddress getLocalSocketAddress();

    public Socket accept() throws IOException;

    protected final void implAccept(Socket s) throws IOException;

    public void close() throws IOException;

    public ServerSocketChannel getChannel();

    public boolean isBound();

    public boolean isClosed();

    public synchronized void setSoTimeout(int timeout) throws SocketException;

    public synchronized int getSoTimeout() throws IOException;

    public void setReuseAddress(boolean on) throws SocketException;

    public boolean getReuseAddress() throws SocketException;

    public String toString();

    void setBound();

    void setCreated();

    private static SocketImplFactory factory = null;

    public static synchronized void setSocketFactory(SocketImplFactory fac) throws IOException;

    public synchronized void setReceiveBufferSize(int size) throws SocketException;

    public synchronized int getReceiveBufferSize() throws SocketException;

    public void setPerformancePreferences(int connectionTime, int latency, int bandwidth);

    public <T> ServerSocket setOption(SocketOption<T> name, T value) throws IOException;

    public <T> T getOption(SocketOption<T> name) throws IOException;

    private static Set<SocketOption<?>> options;

    private static boolean optionsSet = false;

    public Set<SocketOption<?>> supportedOptions();

    static {
        SharedSecrets.setJavaNetSocketAccess(new JavaNetSocketAccess() {

            @Override
            public ServerSocket newServerSocket(SocketImpl impl) {
                return new ServerSocket(impl);
            }

            @Override
            public SocketImpl newSocketImpl(Class<? extends SocketImpl> implClass) {
                try {
                    Constructor<? extends SocketImpl> ctor = implClass.getDeclaredConstructor();
                    return ctor.newInstance();
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new AssertionError(e);
                }
            }
        });
    }
}
