package java.nio.channels;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.channels.spi.*;
import java.net.SocketOption;
import java.net.SocketAddress;
import java.util.concurrent.Future;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AsynchronousServerSocketChannel implements AsynchronousChannel, NetworkChannel {

    protected AsynchronousServerSocketChannel(AsynchronousChannelProvider provider) {
    }

    public final AsynchronousChannelProvider provider();

    public static AsynchronousServerSocketChannel open(AsynchronousChannelGroup group) throws IOException;

    public static AsynchronousServerSocketChannel open() throws IOException;

    public final AsynchronousServerSocketChannel bind(SocketAddress local) throws IOException;

    public abstract AsynchronousServerSocketChannel bind(SocketAddress local, int backlog) throws IOException;

    public abstract <T> AsynchronousServerSocketChannel setOption(SocketOption<T> name, T value) throws IOException;

    public abstract <A> void accept(A attachment, CompletionHandler<AsynchronousSocketChannel, ? super A> handler);

    public abstract Future<AsynchronousSocketChannel> accept();

    @Override
    public abstract SocketAddress getLocalAddress() throws IOException;
}
