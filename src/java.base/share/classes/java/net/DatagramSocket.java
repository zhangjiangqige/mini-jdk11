package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Set;
import java.util.Collections;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class DatagramSocket implements java.io.Closeable {

    private boolean created = false;

    private boolean bound = false;

    private boolean closed = false;

    private Object closeLock = new Object();

    DatagramSocketImpl impl;

    boolean oldImpl = false;

    private boolean explicitFilter = false;

    private int bytesLeftToFilter;

    static final int ST_NOT_CONNECTED = 0;

    static final int ST_CONNECTED = 1;

    static final int ST_CONNECTED_NO_IMPL = 2;

    int connectState = ST_NOT_CONNECTED;

    InetAddress connectedAddress = null;

    int connectedPort = -1;

    private synchronized void connectInternal(InetAddress address, int port) throws SocketException;

    public DatagramSocket() throws SocketException {
        this(new InetSocketAddress(0));
    }

    protected DatagramSocket(DatagramSocketImpl impl) {
        if (impl == null)
            throw new NullPointerException();
        this.impl = impl;
        checkOldImpl();
    }

    public DatagramSocket(SocketAddress bindaddr) throws SocketException {
        createImpl();
        if (bindaddr != null) {
            try {
                bind(bindaddr);
            } finally {
                if (!isBound())
                    close();
            }
        }
    }

    public DatagramSocket(int port) throws SocketException {
        this(port, null);
    }

    public DatagramSocket(int port, InetAddress laddr) throws SocketException {
        this(new InetSocketAddress(laddr, port));
    }

    private void checkOldImpl();

    static Class<?> implClass = null;

    void createImpl() throws SocketException;

    DatagramSocketImpl getImpl() throws SocketException;

    public synchronized void bind(SocketAddress addr) throws SocketException;

    void checkAddress(InetAddress addr, String op);

    public void connect(InetAddress address, int port);

    public void connect(SocketAddress addr) throws SocketException;

    public void disconnect();

    public boolean isBound();

    public boolean isConnected();

    public InetAddress getInetAddress();

    public int getPort();

    public SocketAddress getRemoteSocketAddress();

    public SocketAddress getLocalSocketAddress();

    public void send(DatagramPacket p) throws IOException;

    public synchronized void receive(DatagramPacket p) throws IOException;

    private boolean checkFiltering(DatagramPacket p) throws SocketException;

    public InetAddress getLocalAddress();

    public int getLocalPort();

    public synchronized void setSoTimeout(int timeout) throws SocketException;

    public synchronized int getSoTimeout() throws SocketException;

    public synchronized void setSendBufferSize(int size) throws SocketException;

    public synchronized int getSendBufferSize() throws SocketException;

    public synchronized void setReceiveBufferSize(int size) throws SocketException;

    public synchronized int getReceiveBufferSize() throws SocketException;

    public synchronized void setReuseAddress(boolean on) throws SocketException;

    public synchronized boolean getReuseAddress() throws SocketException;

    public synchronized void setBroadcast(boolean on) throws SocketException;

    public synchronized boolean getBroadcast() throws SocketException;

    public synchronized void setTrafficClass(int tc) throws SocketException;

    public synchronized int getTrafficClass() throws SocketException;

    public void close();

    public boolean isClosed();

    public DatagramChannel getChannel();

    static DatagramSocketImplFactory factory;

    public static synchronized void setDatagramSocketImplFactory(DatagramSocketImplFactory fac) throws IOException;

    public <T> DatagramSocket setOption(SocketOption<T> name, T value) throws IOException;

    public <T> T getOption(SocketOption<T> name) throws IOException;

    private static Set<SocketOption<?>> options;

    private static boolean optionsSet = false;

    public Set<SocketOption<?>> supportedOptions();
}
