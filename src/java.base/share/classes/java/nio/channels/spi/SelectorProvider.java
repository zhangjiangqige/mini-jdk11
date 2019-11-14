package java.nio.channels.spi;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.net.ProtocolFamily;
import java.nio.channels.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.ServiceConfigurationError;
import sun.security.action.GetPropertyAction;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class SelectorProvider {

    private static final Object lock = new Object();

    private static SelectorProvider provider = null;

    private static Void checkPermission();

    private SelectorProvider(Void ignore) {
    }

    protected SelectorProvider() {
        this(checkPermission());
    }

    private static boolean loadProviderFromProperty();

    private static boolean loadProviderAsService();

    public static SelectorProvider provider();

    public abstract DatagramChannel openDatagramChannel() throws IOException;

    public abstract DatagramChannel openDatagramChannel(ProtocolFamily family) throws IOException;

    public abstract Pipe openPipe() throws IOException;

    public abstract AbstractSelector openSelector() throws IOException;

    public abstract ServerSocketChannel openServerSocketChannel() throws IOException;

    public abstract SocketChannel openSocketChannel() throws IOException;

    public Channel inheritedChannel() throws IOException;
}
