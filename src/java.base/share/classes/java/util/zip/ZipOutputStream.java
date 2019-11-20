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

    public static final int STORED;

    public static final int DEFLATED;

    public ZipOutputStream(OutputStream out) {
    }

    public ZipOutputStream(OutputStream out, Charset charset) {
    }

    public void setComment(String comment);

    public void setMethod(int method);

    public void setLevel(int level);

    public void putNextEntry(ZipEntry e) throws IOException;

    public void closeEntry() throws IOException;

    public synchronized void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public void finish() throws IOException;

    public void close() throws IOException;
}
