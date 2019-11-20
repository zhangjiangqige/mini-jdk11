package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.Enumeration;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Inet6Address extends InetAddress {

    public static Inet6Address getByAddress(String host, byte[] addr, NetworkInterface nif) throws UnknownHostException;

    public static Inet6Address getByAddress(String host, byte[] addr, int scope_id) throws UnknownHostException;

    @Override
    public boolean isMulticastAddress();

    @Override
    public boolean isAnyLocalAddress();

    @Override
    public boolean isLoopbackAddress();

    @Override
    public boolean isLinkLocalAddress();

    @Override
    public boolean isSiteLocalAddress();

    @Override
    public boolean isMCGlobal();

    @Override
    public boolean isMCNodeLocal();

    @Override
    public boolean isMCLinkLocal();

    @Override
    public boolean isMCSiteLocal();

    @Override
    public boolean isMCOrgLocal();

    @Override
    public byte[] getAddress();

    public int getScopeId();

    public NetworkInterface getScopedInterface();

    @Override
    public String getHostAddress();

    @Override
    public int hashCode();

    @Override
    public boolean equals(Object obj);

    public boolean isIPv4CompatibleAddress();
}
