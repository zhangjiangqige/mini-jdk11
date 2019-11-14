package java.nio.channels;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.Closeable;

@AnnotatedFor({ "index" })
public interface Channel extends Closeable {

    public boolean isOpen();

    public void close() throws IOException;
}
