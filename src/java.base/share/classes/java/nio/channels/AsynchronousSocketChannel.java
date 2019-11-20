package java.nio.channels;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.channels.spi.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.io.IOException;
import java.net.SocketOption;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AsynchronousSocketChannel implements AsynchronousByteChannel, NetworkChannel {

    protected AsynchronousSocketChannel(AsynchronousChannelProvider provider) {
    }

    public final AsynchronousChannelProvider provider();

    public static AsynchronousSocketChannel open(AsynchronousChannelGroup group) throws IOException;

    public static AsynchronousSocketChannel open() throws IOException;

    @Override
    public abstract AsynchronousSocketChannel bind(SocketAddress local) throws IOException;

    @Override
    public abstract <T> AsynchronousSocketChannel setOption(SocketOption<T> name, T value) throws IOException;

    public abstract AsynchronousSocketChannel shutdownInput() throws IOException;

    public abstract AsynchronousSocketChannel shutdownOutput() throws IOException;

    public abstract SocketAddress getRemoteAddress() throws IOException;

    public abstract <A> void connect(SocketAddress remote, A attachment, CompletionHandler<Void, ? super A> handler);

    public abstract Future<Void> connect(SocketAddress remote);

    public abstract <A> void read(ByteBuffer dst, long timeout, TimeUnit unit, A attachment, CompletionHandler<Integer, ? super A> handler);

    @Override
    public final <A> void read(ByteBuffer dst, A attachment, CompletionHandler<Integer, ? super A> handler);

    @Override
    public abstract Future<Integer> read(ByteBuffer dst);

    public abstract <A> void read(ByteBuffer[] dsts, int offset, int length, long timeout, TimeUnit unit, A attachment, CompletionHandler<Long, ? super A> handler);

    public abstract <A> void write(ByteBuffer src, long timeout, TimeUnit unit, A attachment, CompletionHandler<Integer, ? super A> handler);

    @Override
    public final <A> void write(ByteBuffer src, A attachment, CompletionHandler<Integer, ? super A> handler);

    @Override
    public abstract Future<Integer> write(ByteBuffer src);

    public abstract <A> void write(ByteBuffer[] srcs, int offset, int length, long timeout, TimeUnit unit, A attachment, CompletionHandler<Long, ? super A> handler);

    public abstract SocketAddress getLocalAddress() throws IOException;
}
