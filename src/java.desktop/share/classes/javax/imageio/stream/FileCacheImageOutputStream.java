package javax.imageio.stream;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import com.sun.imageio.stream.StreamCloser;

@AnnotatedFor({ "signedness" })
public class FileCacheImageOutputStream extends ImageOutputStreamImpl {

    public FileCacheImageOutputStream(OutputStream stream, File cacheDir) throws IOException {
    }

    public int read() throws IOException;

    public int read(byte[] b, int off, int len) throws IOException;

    public void write(int b) throws IOException;

    public void write(@PolySigned byte[] b, int off, int len) throws IOException;

    public long length();

    public void seek(long pos) throws IOException;

    public boolean isCached();

    public boolean isCachedFile();

    public boolean isCachedMemory();

    public void close() throws IOException;

    public void flushBefore(long pos) throws IOException;
}
