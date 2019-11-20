package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Arrays;

@AnnotatedFor({ "lock", "nullness", "index" })
public class CharArrayWriter extends Writer {

    protected char[] buf;

    protected int count;

    public CharArrayWriter() {
    }

    public CharArrayWriter(@NonNegative int initialSize) {
    }

    public void write(int c);

    public void write(char[] c, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    public void write(String str, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    public void writeTo(Writer out) throws IOException;

    public CharArrayWriter append(@Nullable CharSequence csq);

    public CharArrayWriter append(@Nullable CharSequence csq, @IndexOrHigh({ "#1" }) int start, @IndexOrHigh({ "#1" }) int end);

    public CharArrayWriter append(char c);

    public void reset();

    public char[] toCharArray();

    @Pure
    @NonNegative
    public int size(@GuardSatisfied CharArrayWriter this);

    @SideEffectFree
    public String toString(@GuardSatisfied CharArrayWriter this);

    public void flush();

    public void close();
}
