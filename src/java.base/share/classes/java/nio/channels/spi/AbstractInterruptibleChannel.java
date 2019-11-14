package java.nio.channels.spi;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.nio.channels.*;
import jdk.internal.misc.SharedSecrets;
import sun.nio.ch.Interruptible;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AbstractInterruptibleChannel implements Channel, InterruptibleChannel {

    private final Object closeLock = new Object();

    private volatile boolean closed;

    protected AbstractInterruptibleChannel() {
    }

    public final void close() throws IOException;

    protected abstract void implCloseChannel() throws IOException;

    public final boolean isOpen();

    private Interruptible interruptor;

    private volatile Thread interrupted;

    protected final void begin();

    protected final void end(boolean completed) throws AsynchronousCloseException;

    static void blockedOn(Interruptible intr);
}
