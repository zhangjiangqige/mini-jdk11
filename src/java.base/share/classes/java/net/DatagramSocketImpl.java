package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Set;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class DatagramSocketImpl implements SocketOptions {

    protected int localPort;

    protected FileDescriptor fd;

    protected abstract void create() throws SocketException;

    protected abstract void bind(int lport, InetAddress laddr) throws SocketException;

    protected abstract void send(DatagramPacket p) throws IOException;

    protected void connect(InetAddress address, int port) throws SocketException;

    protected void disconnect();

    protected abstract int peek(InetAddress i) throws IOException;

    protected abstract int peekData(DatagramPacket p) throws IOException;

    protected abstract void receive(DatagramPacket p) throws IOException;

    @Deprecated
    protected abstract void setTTL(byte ttl) throws IOException;

    @Deprecated
    protected abstract byte getTTL() throws IOException;

    protected abstract void setTimeToLive(int ttl) throws IOException;

    protected abstract int getTimeToLive() throws IOException;

    protected abstract void join(InetAddress inetaddr) throws IOException;

    protected abstract void leave(InetAddress inetaddr) throws IOException;

    protected abstract void joinGroup(SocketAddress mcastaddr, NetworkInterface netIf) throws IOException;

    protected abstract void leaveGroup(SocketAddress mcastaddr, NetworkInterface netIf) throws IOException;

    protected abstract void close();

    protected int getLocalPort();

    protected FileDescriptor getFileDescriptor();

    protected <T> void setOption(SocketOption<T> name, T value) throws IOException;

    @SuppressWarnings("unchecked")
    protected <T> T getOption(SocketOption<T> name) throws IOException;

    protected Set<SocketOption<?>> supportedOptions();
}
