package javax.imageio.stream;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

@AnnotatedFor({ "signedness" })
class MemoryCache {

    private static final int BUFFER_LENGTH = 8192;

    private ArrayList<byte[]> cache = new ArrayList<>();

    private long cacheStart = 0L;

    private long length = 0L;

    private byte[] getCacheBlock(long blockNum) throws IOException;

    public long loadFromStream(InputStream stream, long pos) throws IOException;

    public void writeToStream(OutputStream stream, long pos, long len) throws IOException;

    private void pad(long pos) throws IOException;

    public void write(@PolySigned byte[] b, int off, int len, long pos) throws IOException;

    public void write(int b, long pos) throws IOException;

    public long getLength();

    public int read(long pos) throws IOException;

    public void read(byte[] b, int off, int len, long pos) throws IOException;

    public void disposeBefore(long pos);

    public void reset();
}
