package java.util.zip;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import java.util.HashSet;
import static java.util.zip.ZipConstants64.*;
import static java.util.zip.ZipUtils.*;
import sun.security.action.GetPropertyAction;

@AnnotatedFor({ "index", "signedness" })
public class ZipOutputStream extends DeflaterOutputStream implements ZipConstants {

    private static final boolean inhibitZip64 = Boolean.parseBoolean(GetPropertyAction.privilegedGetProperty("jdk.util.zip.inhibitZip64"));

    private static class XEntry {

        final ZipEntry entry;

        final long offset;

        public XEntry(ZipEntry entry, long offset) {
            this.entry = entry;
            this.offset = offset;
        }
    }

    private XEntry current;

    private Vector<XEntry> xentries = new Vector<>();

    private HashSet<String> names = new HashSet<>();

    private CRC32 crc = new CRC32();

    private long written = 0;

    private long locoff = 0;

    private byte[] comment;

    private int method = DEFLATED;

    private boolean finished;

    private boolean closed = false;

    private final ZipCoder zc;

    private static int version(ZipEntry e) throws ZipException;

    private void ensureOpen() throws IOException;

    public static final int STORED = ZipEntry.STORED;

    public static final int DEFLATED = ZipEntry.DEFLATED;

    public ZipOutputStream(OutputStream out) {
        this(out, StandardCharsets.UTF_8);
    }

    public ZipOutputStream(OutputStream out, Charset charset) {
        super(out, new Deflater(Deflater.DEFAULT_COMPRESSION, true));
        if (charset == null)
            throw new NullPointerException("charset is null");
        this.zc = ZipCoder.get(charset);
        usesDefaultDeflater = true;
    }

    public void setComment(String comment);

    public void setMethod(int method);

    public void setLevel(int level);

    public void putNextEntry(ZipEntry e) throws IOException;

    public void closeEntry() throws IOException;

    public synchronized void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public void finish() throws IOException;

    public void close() throws IOException;

    private void writeLOC(XEntry xentry) throws IOException;

    private void writeEXT(ZipEntry e) throws IOException;

    private void writeCEN(XEntry xentry) throws IOException;

    private void writeEND(long off, long len) throws IOException;

    private int getExtraLen(byte[] extra);

    private void writeExtra(byte[] extra) throws IOException;

    private void writeByte(@PolySigned int v) throws IOException;

    private void writeShort(@PolySigned int v) throws IOException;

    private void writeInt(@PolySigned long v) throws IOException;

    private void writeLong(@PolySigned long v) throws IOException;

    private void writeBytes(@PolySigned byte[] b, int off, int len) throws IOException;
}
