package java.util.zip;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.OutputStream;
import java.io.IOException;
import org.checkerframework.framework.qual.CFComment;

@AnnotatedFor({ "index", "signedness" })
public class GZIPOutputStream extends DeflaterOutputStream {

    protected CRC32 crc = new CRC32();

    private static final int GZIP_MAGIC = 0x8b1f;

    private static final int TRAILER_SIZE = 8;

    public GZIPOutputStream(OutputStream out, @Positive int size) throws IOException {
        this(out, size, false);
    }

    public GZIPOutputStream(OutputStream out, @Positive int size, boolean syncFlush) throws IOException {
        super(out, new Deflater(Deflater.DEFAULT_COMPRESSION, true), size, syncFlush);
        usesDefaultDeflater = true;
        writeHeader();
        crc.reset();
    }

    public GZIPOutputStream(OutputStream out) throws IOException {
        this(out, 512, false);
    }

    public GZIPOutputStream(OutputStream out, boolean syncFlush) throws IOException {
        this(out, 512, syncFlush);
    }

    public synchronized void write(@PolySigned byte[] buf, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public void finish() throws IOException;

    @SuppressWarnings("cast.unsafe")
    @CFComment("index: https://github.com/typetools/checker-framework/issues/2731")
    private void writeHeader() throws IOException;

    private void writeTrailer(byte[] buf, int offset) throws IOException;

    private void writeInt(int i, byte[] buf, int offset) throws IOException;

    private void writeShort(int s, byte[] buf, int offset) throws IOException;
}
