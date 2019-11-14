package java.util.zip;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import static java.util.zip.ZipConstants64.*;
import static java.util.zip.ZipUtils.*;

@AnnotatedFor({ "index" })
public class ZipInputStream extends InflaterInputStream implements ZipConstants {

    private ZipEntry entry;

    private int flag;

    private CRC32 crc = new CRC32();

    private long remaining;

    private byte[] tmpbuf = new byte[512];

    private static final int STORED = ZipEntry.STORED;

    private static final int DEFLATED = ZipEntry.DEFLATED;

    private boolean closed = false;

    private boolean entryEOF = false;

    private ZipCoder zc;

    private void ensureOpen() throws IOException;

    public ZipInputStream(InputStream in) {
        this(in, StandardCharsets.UTF_8);
    }

    public ZipInputStream(InputStream in, Charset charset) {
        super(new PushbackInputStream(in, 512), new Inflater(true), 512);
        usesDefaultInflater = true;
        if (in == null) {
            throw new NullPointerException("in is null");
        }
        if (charset == null)
            throw new NullPointerException("charset is null");
        this.zc = ZipCoder.get(charset);
    }

    public ZipEntry getNextEntry() throws IOException;

    public void closeEntry() throws IOException;

    public int available() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public long skip(long n) throws IOException;

    public void close() throws IOException;

    private byte[] b = new byte[256];

    private ZipEntry readLOC() throws IOException;

    protected ZipEntry createZipEntry(String name);

    private void readEnd(ZipEntry e) throws IOException;

    private void readFully(byte[] b, int off, int len) throws IOException;
}
