package java.io;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;

@AnnotatedFor({ "nullness" })
public interface Flushable {

    void flush() throws IOException;
}
