package java.nio.channels;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.nio.channels.spi.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Pipe {

    public abstract static class SourceChannel extends AbstractSelectableChannel implements ReadableByteChannel, ScatteringByteChannel {

        protected SourceChannel(SelectorProvider provider) {
            super(provider);
        }

        public final int validOps();
    }

    public abstract static class SinkChannel extends AbstractSelectableChannel implements WritableByteChannel, GatheringByteChannel {

        protected SinkChannel(SelectorProvider provider) {
            super(provider);
        }

        public final int validOps();
    }

    protected Pipe() {
    }

    public abstract SourceChannel source();

    public abstract SinkChannel sink();

    public static Pipe open() throws IOException;
}
