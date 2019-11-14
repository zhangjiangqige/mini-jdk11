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

    static final int INADDRSZ = 16;

    private transient int cached_scope_id;

    private class Inet6AddressHolder {

        private Inet6AddressHolder() {
            ipaddress = new byte[INADDRSZ];
        }

        private Inet6AddressHolder(byte[] ipaddress, int scope_id, boolean scope_id_set, NetworkInterface ifname, boolean scope_ifname_set) {
            this.ipaddress = ipaddress;
            this.scope_id = scope_id;
            this.scope_id_set = scope_id_set;
            this.scope_ifname_set = scope_ifname_set;
            this.scope_ifname = ifname;
        }

        byte[] ipaddress;

        int scope_id;

        boolean scope_id_set;

        NetworkInterface scope_ifname;

        boolean scope_ifname_set;

        void setAddr(byte[] addr);

        void init(byte[] addr, int scope_id);

        void init(byte[] addr, NetworkInterface nif) throws UnknownHostException;

        String getHostAddress();

        public boolean equals(Object o);

        public int hashCode();

        boolean isIPv4CompatibleAddress();

        boolean isMulticastAddress();

        boolean isAnyLocalAddress();

        boolean isLoopbackAddress();

        boolean isLinkLocalAddress();

        boolean isSiteLocalAddress();

        boolean isMCGlobal();

        boolean isMCNodeLocal();

        boolean isMCLinkLocal();

        boolean isMCSiteLocal();

        boolean isMCOrgLocal();
    }

    private final transient Inet6AddressHolder holder6;

    private static final long serialVersionUID = 6880410070516793377L;

    static {
        init();
    }

    Inet6Address() {
        super();
        holder.init(null, IPv6);
        holder6 = new Inet6AddressHolder();
    }

    Inet6Address(String hostName, byte[] addr, int scope_id) {
        holder.init(hostName, IPv6);
        holder6 = new Inet6AddressHolder();
        holder6.init(addr, scope_id);
    }

    Inet6Address(String hostName, byte[] addr) {
        holder6 = new Inet6AddressHolder();
        try {
            initif(hostName, addr, null);
        } catch (UnknownHostException e) {
        }
    }

    Inet6Address(String hostName, byte[] addr, NetworkInterface nif) throws UnknownHostException {
        holder6 = new Inet6AddressHolder();
        initif(hostName, addr, nif);
    }

    Inet6Address(String hostName, byte[] addr, String ifname) throws UnknownHostException {
        holder6 = new Inet6AddressHolder();
        initstr(hostName, addr, ifname);
    }

    public static Inet6Address getByAddress(String host, byte[] addr, NetworkInterface nif) throws UnknownHostException;

    public static Inet6Address getByAddress(String host, byte[] addr, int scope_id) throws UnknownHostException;

    private void initstr(String hostName, byte[] addr, String ifname) throws UnknownHostException;

    private void initif(String hostName, byte[] addr, NetworkInterface nif) throws UnknownHostException;

    private static boolean isDifferentLocalAddressType(byte[] thisAddr, byte[] otherAddr);

    private static int deriveNumericScope(byte[] thisAddr, NetworkInterface ifc) throws UnknownHostException;

    private int deriveNumericScope(String ifname) throws UnknownHostException;

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("ipaddress", byte[].class), new ObjectStreamField("scope_id", int.class), new ObjectStreamField("scope_id_set", boolean.class), new ObjectStreamField("scope_ifname_set", boolean.class), new ObjectStreamField("ifname", String.class) };

    private static final jdk.internal.misc.Unsafe UNSAFE = jdk.internal.misc.Unsafe.getUnsafe();

    private static final long FIELDS_OFFSET = UNSAFE.objectFieldOffset(Inet6Address.class, "holder6");

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException;

    private synchronized void writeObject(ObjectOutputStream s) throws IOException;

    @Override
    public boolean isMulticastAddress();

    @Override
    public boolean isAnyLocalAddress();

    @Override
    public boolean isLoopbackAddress();

    @Override
    public boolean isLinkLocalAddress();

    static boolean isLinkLocalAddress(byte[] ipaddress);

    @Override
    public boolean isSiteLocalAddress();

    static boolean isSiteLocalAddress(byte[] ipaddress);

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

    private static final int INT16SZ = 2;

    static String numericToTextFormat(byte[] src);

    private static native void init();
}
