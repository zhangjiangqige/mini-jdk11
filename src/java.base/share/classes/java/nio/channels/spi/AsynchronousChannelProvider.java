package java.nio.channels.spi;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.channels.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.ServiceConfigurationError;
import java.util.concurrent.*;
import java.security.AccessController;
import java.security.PrivilegedAction;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AsynchronousChannelProvider {

    private static Void checkPermission();

    private AsynchronousChannelProvider(Void ignore) {
    }

    protected AsynchronousChannelProvider() {
        this(checkPermission());
    }

    private static class ProviderHolder {

        static final AsynchronousChannelProvider provider = load();

        private static AsynchronousChannelProvider load();

        private static AsynchronousChannelProvider loadProviderFromProperty();

        private static AsynchronousChannelProvider loadProviderAsService();
    }

    public static AsynchronousChannelProvider provider();

    public abstract AsynchronousChannelGroup openAsynchronousChannelGroup(int nThreads, ThreadFactory threadFactory) throws IOException;

    public abstract AsynchronousChannelGroup openAsynchronousChannelGroup(ExecutorService executor, int initialSize) throws IOException;

    public abstract AsynchronousServerSocketChannel openAsynchronousServerSocketChannel(AsynchronousChannelGroup group) throws IOException;

    public abstract AsynchronousSocketChannel openAsynchronousSocketChannel(AsynchronousChannelGroup group) throws IOException;
}
