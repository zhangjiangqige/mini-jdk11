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

    protected AbstractInterruptibleChannel() {
    }

    public final void close() throws IOException;

    protected abstract void implCloseChannel() throws IOException;

    public final boolean isOpen();

    protected final void begin();

    protected final void end(boolean completed) throws AsynchronousCloseException;
}
