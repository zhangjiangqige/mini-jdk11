package java.nio.channels;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.spi.SelectorProvider;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Selector implements Closeable {

    protected Selector() {
    }

    public static Selector open() throws IOException;

    public abstract boolean isOpen();

    public abstract SelectorProvider provider();

    public abstract Set<SelectionKey> keys();

    public abstract Set<SelectionKey> selectedKeys();

    public abstract int selectNow() throws IOException;

    public abstract int select(long timeout) throws IOException;

    public abstract int select() throws IOException;

    public int select(Consumer<SelectionKey> action, long timeout) throws IOException;

    public int select(Consumer<SelectionKey> action) throws IOException;

    public int selectNow(Consumer<SelectionKey> action) throws IOException;

    public abstract Selector wakeup();

    public abstract void close() throws IOException;
}
