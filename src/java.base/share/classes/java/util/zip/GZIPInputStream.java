package java.util.zip;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.SequenceInputStream;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;

@AnnotatedFor({ "index" })
public class GZIPInputStream extends InflaterInputStream {

    protected CRC32 crc = new CRC32();

    protected boolean eos;

    private boolean closed = false;

    private void ensureOpen() throws IOException;

    public GZIPInputStream(InputStream in, @Positive int size) throws IOException {
        super(in, new Inflater(true), size);
        usesDefaultInflater = true;
        readHeader(in);
    }

    public GZIPInputStream(InputStream in) throws IOException {
        this(in, 512);
    }

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] buf, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public void close() throws IOException;

    public static final int GZIP_MAGIC = 0x8b1f;

    private static final int FTEXT = 1;

    private static final int FHCRC = 2;

    private static final int FEXTRA = 4;

    private static final int FNAME = 8;

    private static final int FCOMMENT = 16;

    private int readHeader(InputStream this_in) throws IOException;

    private boolean readTrailer() throws IOException;

    private long readUInt(InputStream in) throws IOException;

    private int readUShort(InputStream in) throws IOException;

    private int readUByte(InputStream in) throws IOException;

    private byte[] tmpbuf = new byte[128];

    private void skipBytes(InputStream in, int n) throws IOException;
}
