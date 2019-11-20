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

    public DatagramSocket() throws SocketException {
    }

    protected DatagramSocket(DatagramSocketImpl impl) {
    }

    public DatagramSocket(SocketAddress bindaddr) throws SocketException {
    }

    public DatagramSocket(int port) throws SocketException {
    }

    public DatagramSocket(int port, InetAddress laddr) throws SocketException {
    }

    public synchronized void bind(SocketAddress addr) throws SocketException;

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

    public static synchronized void setDatagramSocketImplFactory(DatagramSocketImplFactory fac) throws IOException;

    public <T> DatagramSocket setOption(SocketOption<T> name, T value) throws IOException;

    public <T> T getOption(SocketOption<T> name) throws IOException;

    public Set<SocketOption<?>> supportedOptions();
}
