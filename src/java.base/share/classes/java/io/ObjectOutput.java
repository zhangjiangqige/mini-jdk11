package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "index", "signedness" })
public interface ObjectOutput extends DataOutput, AutoCloseable {

    public void writeObject(@Nullable Object obj) throws IOException;

    public void write(@PolySigned int b) throws IOException;

    public void write(@PolySigned byte[] b) throws IOException;

    public void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void flush() throws IOException;

    public void close() throws IOException;
}
