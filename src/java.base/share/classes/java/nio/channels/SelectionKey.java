package java.nio.channels;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class SelectionKey {

    protected SelectionKey() {
    }

    public abstract SelectableChannel channel();

    public abstract Selector selector();

    public abstract boolean isValid();

    public abstract void cancel();

    public abstract int interestOps();

    public abstract SelectionKey interestOps(int ops);

    public int interestOpsOr(int ops);

    public int interestOpsAnd(int ops);

    public abstract int readyOps();

    public static final int OP_READ;

    public static final int OP_WRITE;

    public static final int OP_CONNECT;

    public static final int OP_ACCEPT;

    public final boolean isReadable();

    public final boolean isWritable();

    public final boolean isConnectable();

    public final boolean isAcceptable();

    public final Object attach(Object ob);

    public final Object attachment();
}
