package java.nio.channels;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AsynchronousChannelGroup {

    protected AsynchronousChannelGroup(AsynchronousChannelProvider provider) {
    }

    public final AsynchronousChannelProvider provider();

    public static AsynchronousChannelGroup withFixedThreadPool(int nThreads, ThreadFactory threadFactory) throws IOException;

    public static AsynchronousChannelGroup withCachedThreadPool(ExecutorService executor, int initialSize) throws IOException;

    public static AsynchronousChannelGroup withThreadPool(ExecutorService executor) throws IOException;

    public abstract boolean isShutdown();

    public abstract boolean isTerminated();

    public abstract void shutdown();

    public abstract void shutdownNow() throws IOException;

    public abstract boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;
}
