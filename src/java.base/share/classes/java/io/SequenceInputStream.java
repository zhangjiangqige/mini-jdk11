package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;

@AnnotatedFor({ "nullness", "index" })
public class SequenceInputStream extends InputStream {

    Enumeration<? extends InputStream> e;

    InputStream in;

    public SequenceInputStream(Enumeration<? extends InputStream> e) {
        this.e = e;
        peekNextStream();
    }

    public SequenceInputStream(InputStream s1, InputStream s2) {
        Vector<InputStream> v = new Vector<>(2);
        v.addElement(s1);
        v.addElement(s2);
        e = v.elements();
        peekNextStream();
    }

    final void nextStream() throws IOException;

    private void peekNextStream();

    @NonNegative
    public int available() throws IOException;

    public int read() throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void close() throws IOException;
}
