package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class DatagramPacket {

    public DatagramPacket(byte[] buf, int offset, int length) {
    }

    public DatagramPacket(byte[] buf, int length) {
    }

    public DatagramPacket(byte[] buf, int offset, int length, InetAddress address, int port) {
    }

    public DatagramPacket(byte[] buf, int offset, int length, SocketAddress address) {
    }

    public DatagramPacket(byte[] buf, int length, InetAddress address, int port) {
    }

    public DatagramPacket(byte[] buf, int length, SocketAddress address) {
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
}
