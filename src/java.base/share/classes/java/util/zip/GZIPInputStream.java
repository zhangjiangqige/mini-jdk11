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

    protected CRC32 crc;

    protected boolean eos;

    public GZIPInputStream(InputStream in, @Positive int size) throws IOException {
    }

    public GZIPInputStream(InputStream in) throws IOException {
    }

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] buf, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public void close() throws IOException;

    public static final int GZIP_MAGIC;
}
