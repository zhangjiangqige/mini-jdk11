package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;

class Inet4AddressImpl implements InetAddressImpl {

    public native String getLocalHostName() throws UnknownHostException;

    public native InetAddress[] lookupAllHostAddr(String hostname) throws UnknownHostException;

    public native String getHostByAddr(byte[] addr) throws UnknownHostException;

    private native boolean isReachable0(byte[] addr, int timeout, byte[] ifaddr, int ttl) throws IOException;

    public synchronized InetAddress anyLocalAddress();

    public synchronized InetAddress loopbackAddress();

    public boolean isReachable(InetAddress addr, int timeout, NetworkInterface netif, int ttl) throws IOException;

    private InetAddress anyLocalAddress;

    private InetAddress loopbackAddress;
}
