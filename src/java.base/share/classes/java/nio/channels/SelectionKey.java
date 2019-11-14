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

    public static final int OP_READ = 1 << 0;

    public static final int OP_WRITE = 1 << 2;

    public static final int OP_CONNECT = 1 << 3;

    public static final int OP_ACCEPT = 1 << 4;

    public final boolean isReadable();

    public final boolean isWritable();

    public final boolean isConnectable();

    public final boolean isAcceptable();

    private volatile Object attachment;

    private static final AtomicReferenceFieldUpdater<SelectionKey, Object> attachmentUpdater = AtomicReferenceFieldUpdater.newUpdater(SelectionKey.class, Object.class, "attachment");

    public final Object attach(Object ob);

    public final Object attachment();
}
