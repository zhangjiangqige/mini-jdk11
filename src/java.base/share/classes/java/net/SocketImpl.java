package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileDescriptor;
import java.util.Set;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class SocketImpl implements SocketOptions {

    Socket socket = null;

    ServerSocket serverSocket = null;

    protected FileDescriptor fd;

    protected InetAddress address;

    protected int port;

    protected int localport;

    protected abstract void create(boolean stream) throws IOException;

    protected abstract void connect(String host, int port) throws IOException;

    protected abstract void connect(InetAddress address, int port) throws IOException;

    protected abstract void connect(SocketAddress address, int timeout) throws IOException;

    protected abstract void bind(InetAddress host, int port) throws IOException;

    protected abstract void listen(int backlog) throws IOException;

    protected abstract void accept(SocketImpl s) throws IOException;

    protected abstract InputStream getInputStream() throws IOException;

    protected abstract OutputStream getOutputStream() throws IOException;

    protected abstract int available() throws IOException;

    protected abstract void close() throws IOException;

    protected void shutdownInput() throws IOException;

    protected void shutdownOutput() throws IOException;

    protected FileDescriptor getFileDescriptor();

    protected InetAddress getInetAddress();

    protected int getPort();

    protected boolean supportsUrgentData();

    protected abstract void sendUrgentData(int data) throws IOException;

    protected int getLocalPort();

    void setSocket(Socket soc);

    Socket getSocket();

    void setServerSocket(ServerSocket soc);

    ServerSocket getServerSocket();

    public String toString();

    void reset() throws IOException;

    protected void setPerformancePreferences(int connectionTime, int latency, int bandwidth);

    protected <T> void setOption(SocketOption<T> name, T value) throws IOException;

    @SuppressWarnings("unchecked")
    protected <T> T getOption(SocketOption<T> name) throws IOException;

    private static final Set<SocketOption<?>> socketOptions;

    private static final Set<SocketOption<?>> serverSocketOptions;

    static {
        socketOptions = Set.of(StandardSocketOptions.SO_KEEPALIVE, StandardSocketOptions.SO_SNDBUF, StandardSocketOptions.SO_RCVBUF, StandardSocketOptions.SO_REUSEADDR, StandardSocketOptions.SO_LINGER, StandardSocketOptions.IP_TOS, StandardSocketOptions.TCP_NODELAY);
        serverSocketOptions = Set.of(StandardSocketOptions.SO_RCVBUF, StandardSocketOptions.SO_REUSEADDR, StandardSocketOptions.IP_TOS);
    }

    protected Set<SocketOption<?>> supportedOptions();
}
