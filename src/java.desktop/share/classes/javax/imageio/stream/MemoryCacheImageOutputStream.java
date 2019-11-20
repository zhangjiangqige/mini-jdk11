package javax.imageio.stream;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.OutputStream;

@AnnotatedFor({ "signedness" })
public class MemoryCacheImageOutputStream extends ImageOutputStreamImpl {

    public MemoryCacheImageOutputStream(OutputStream stream) {
    }

    public int read() throws IOException;

    public int read(byte[] b, int off, int len) throws IOException;

    public void write(int b) throws IOException;

    public void write(@PolySigned byte[] b, int off, int len) throws IOException;

    public long length();

    public boolean isCached();

    public boolean isCachedFile();

    public boolean isCachedMemory();

    public void close() throws IOException;

    public void flushBefore(long pos) throws IOException;
}
