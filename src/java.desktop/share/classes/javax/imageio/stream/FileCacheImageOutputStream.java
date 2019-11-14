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

    private OutputStream stream;

    private File cacheFile;

    private RandomAccessFile cache;

    private long maxStreamPos = 0L;

    private final StreamCloser.CloseAction closeAction;

    public FileCacheImageOutputStream(OutputStream stream, File cacheDir) throws IOException {
        if (stream == null) {
            throw new IllegalArgumentException("stream == null!");
        }
        if ((cacheDir != null) && !(cacheDir.isDirectory())) {
            throw new IllegalArgumentException("Not a directory!");
        }
        this.stream = stream;
        if (cacheDir == null)
            this.cacheFile = Files.createTempFile("imageio", ".tmp").toFile();
        else
            this.cacheFile = Files.createTempFile(cacheDir.toPath(), "imageio", ".tmp").toFile();
        this.cache = new RandomAccessFile(cacheFile, "rw");
        this.closeAction = StreamCloser.createCloseAction(this);
        StreamCloser.addToQueue(closeAction);
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
