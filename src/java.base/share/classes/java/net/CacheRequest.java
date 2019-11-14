package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.OutputStream;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class CacheRequest {

    public abstract OutputStream getBody() throws IOException;

    public abstract void abort();
}
