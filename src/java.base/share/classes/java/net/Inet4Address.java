package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectStreamException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Inet4Address extends InetAddress {

    static final int INADDRSZ = 4;

    private static final long serialVersionUID = 3286316764910316507L;

    static {
        init();
    }

    Inet4Address() {
        super();
        holder().hostName = null;
        holder().address = 0;
        holder().family = IPv4;
    }

    Inet4Address(String hostName, byte[] addr) {
        holder().hostName = hostName;
        holder().family = IPv4;
        if (addr != null) {
            if (addr.length == INADDRSZ) {
                int address = addr[3] & 0xFF;
                address |= ((addr[2] << 8) & 0xFF00);
                address |= ((addr[1] << 16) & 0xFF0000);
                address |= ((addr[0] << 24) & 0xFF000000);
                holder().address = address;
            }
        }
        holder().originalHostName = hostName;
    }

    Inet4Address(String hostName, int address) {
        holder().hostName = hostName;
        holder().family = IPv4;
        holder().address = address;
        holder().originalHostName = hostName;
    }

    private Object writeReplace() throws ObjectStreamException;

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

    static String numericToTextFormat(byte[] src);

    private static native void init();
}
