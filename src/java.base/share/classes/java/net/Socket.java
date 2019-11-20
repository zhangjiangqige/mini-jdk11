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

    public Socket() {
    }

    public Socket(Proxy proxy) {
    }

    protected Socket(SocketImpl impl) throws SocketException {
    }

    public Socket(@Nullable String host, int port) throws UnknownHostException, IOException {
    }

    public Socket(InetAddress address, int port) throws IOException {
    }

    public Socket(@Nullable String host, int port, @Nullable InetAddress localAddr, int localPort) throws IOException {
    }

    public Socket(InetAddress address, int port, @Nullable InetAddress localAddr, int localPort) throws IOException {
    }

    @Deprecated
    public Socket(@Nullable String host, int port, boolean stream) throws IOException {
    }

    @Deprecated
    public Socket(InetAddress host, int port, boolean stream) throws IOException {
    }

    public void connect(SocketAddress endpoint) throws IOException;

    public void connect(SocketAddress endpoint, int timeout) throws IOException;

    public void bind(@Nullable SocketAddress bindpoint) throws IOException;

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

    public static synchronized void setSocketImplFactory(@Nullable SocketImplFactory fac) throws IOException;

    public void setPerformancePreferences(int connectionTime, int latency, int bandwidth);

    public <T> Socket setOption(SocketOption<T> name, T value) throws IOException;

    @SuppressWarnings("unchecked")
    public <T> T getOption(SocketOption<T> name) throws IOException;

    public Set<SocketOption<?>> supportedOptions();
}
