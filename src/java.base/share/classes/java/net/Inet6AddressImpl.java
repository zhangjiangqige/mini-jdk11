package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import static java.net.InetAddress.PREFER_IPV6_VALUE;
import static java.net.InetAddress.PREFER_SYSTEM_VALUE;

class Inet6AddressImpl implements InetAddressImpl {

    public native String getLocalHostName() throws UnknownHostException;

    public native InetAddress[] lookupAllHostAddr(String hostname) throws UnknownHostException;

    public native String getHostByAddr(byte[] addr) throws UnknownHostException;

    private native boolean isReachable0(byte[] addr, int scope, int timeout, byte[] inf, int ttl, int if_scope) throws IOException;

    public boolean isReachable(InetAddress addr, int timeout, NetworkInterface netif, int ttl) throws IOException;

    public synchronized InetAddress anyLocalAddress();

    public synchronized InetAddress loopbackAddress();

    private InetAddress anyLocalAddress;

    private InetAddress loopbackAddress;
}
