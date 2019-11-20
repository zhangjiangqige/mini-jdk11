package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectStreamException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Inet4Address extends InetAddress {

    public boolean isMulticastAddress();

    public boolean isAnyLocalAddress();

    public boolean isLoopbackAddress();

    public boolean isLinkLocalAddress();

    public boolean isSiteLocalAddress();

    public boolean isMCGlobal();

    public boolean isMCNodeLocal();

    public boolean isMCLinkLocal();

    public boolean isMCSiteLocal();

    public boolean isMCOrgLocal();

    public byte[] getAddress();

    public String getHostAddress();

    public int hashCode();

    public boolean equals(Object obj);
}
