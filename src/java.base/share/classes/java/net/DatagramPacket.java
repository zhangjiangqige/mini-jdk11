package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class DatagramPacket {

    static {
        java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<>() {

            public Void run() {
                System.loadLibrary("net");
                return null;
            }
        });
        init();
    }

    byte[] buf;

    int offset;

    int length;

    int bufLength;

    InetAddress address;

    int port;

    public DatagramPacket(byte[] buf, int offset, int length) {
        setData(buf, offset, length);
        this.address = null;
        this.port = -1;
    }

    public DatagramPacket(byte[] buf, int length) {
        this(buf, 0, length);
    }

    public DatagramPacket(byte[] buf, int offset, int length, InetAddress address, int port) {
        setData(buf, offset, length);
        setAddress(address);
        setPort(port);
    }

    public DatagramPacket(byte[] buf, int offset, int length, SocketAddress address) {
        setData(buf, offset, length);
        setSocketAddress(address);
    }

    public DatagramPacket(byte[] buf, int length, InetAddress address, int port) {
        this(buf, 0, length, address, port);
    }

    public DatagramPacket(byte[] buf, int length, SocketAddress address) {
        this(buf, 0, length, address);
    }

    public synchronized InetAddress getAddress();

    public synchronized int getPort();

    public synchronized byte[] getData();

    public synchronized int getOffset();

    public synchronized int getLength();

    public synchronized void setData(byte[] buf, int offset, int length);

    public synchronized void setAddress(InetAddress iaddr);

    public synchronized void setPort(int iport);

    public synchronized void setSocketAddress(SocketAddress address);

    public synchronized SocketAddress getSocketAddress();

    public synchronized void setData(byte[] buf);

    public synchronized void setLength(int length);

    private static native void init();
}
