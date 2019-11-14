package java.nio.channels;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.nio.ByteBuffer;

@AnnotatedFor({ "index" })
public interface WritableByteChannel extends Channel {

    public int write(ByteBuffer src) throws IOException;
}
