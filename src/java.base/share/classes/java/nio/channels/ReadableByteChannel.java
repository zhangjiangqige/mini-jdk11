package java.nio.channels;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.nio.ByteBuffer;

@AnnotatedFor({ "index" })
public interface ReadableByteChannel extends Channel {

    @GTENegativeOne
    public int read(ByteBuffer dst) throws IOException;
}
