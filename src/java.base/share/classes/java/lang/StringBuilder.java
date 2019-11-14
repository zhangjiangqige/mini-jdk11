package java.lang;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.HotSpotIntrinsicCandidate;

@AnnotatedFor({ "lock", "nullness", "index" })
public final class StringBuilder extends AbstractStringBuilder implements java.io.Serializable, Comparable<StringBuilder>, CharSequence {

    static final long serialVersionUID = 4383685877147921099L;

    @HotSpotIntrinsicCandidate
    public StringBuilder() {
        super(16);
    }

    @HotSpotIntrinsicCandidate
    public StringBuilder(@NonNegative int capacity) {
        super(capacity);
    }

    @HotSpotIntrinsicCandidate
    public StringBuilder(String str) {
        super(str.length() + 16);
        append(str);
    }

    public StringBuilder(CharSequence seq) {
        this(seq.length() + 16);
        append(seq);
    }

    @Override
    public int compareTo(StringBuilder another);

    @Override
    public StringBuilder append(@Nullable Object obj);

    @Override
    @HotSpotIntrinsicCandidate
    public StringBuilder append(@Nullable String str);

    public StringBuilder append(@Nullable StringBuffer sb);

    @Override
    public StringBuilder append(@Nullable CharSequence s);

    @Override
    public StringBuilder append(@Nullable CharSequence s, @IndexOrHigh({ "#1" }) int start, @IndexOrHigh({ "#1" }) int end);

    @Override
    public StringBuilder append(char[] str);

    @Override
    public StringBuilder append(char[] str, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    @Override
    public StringBuilder append(boolean b);

    @Override
    @HotSpotIntrinsicCandidate
    public StringBuilder append(char c);

    @Override
    @HotSpotIntrinsicCandidate
    public StringBuilder append(int i);

    @Override
    public StringBuilder append(long lng);

    @Override
    public StringBuilder append(float f);

    @Override
    public StringBuilder append(double d);

    @Override
    public StringBuilder appendCodePoint(int codePoint);

    @Override
    public StringBuilder delete(@NonNegative int start, @NonNegative int end);

    @Override
    public StringBuilder deleteCharAt(@NonNegative int index);

    @Override
    public StringBuilder replace(@NonNegative int start, @NonNegative int end, String str);

    @Override
    public StringBuilder insert(@NonNegative int index, char[] str, @IndexOrHigh({ "#2" }) int offset, @IndexOrHigh({ "#2" }) int len);

    @Override
    public StringBuilder insert(@NonNegative int offset, @Nullable Object obj);

    @Override
    public StringBuilder insert(@NonNegative int offset, @Nullable String str);

    @Override
    public StringBuilder insert(@NonNegative int offset, char[] str);

    @Override
    public StringBuilder insert(@NonNegative int dstOffset, @Nullable CharSequence s);

    @Override
    public StringBuilder insert(@NonNegative int dstOffset, @Nullable CharSequence s, @NonNegative int start, @NonNegative int end);

    @Override
    public StringBuilder insert(@NonNegative int offset, boolean b);

    @Override
    public StringBuilder insert(@NonNegative int offset, char c);

    @Override
    public StringBuilder insert(@NonNegative int offset, int i);

    @Override
    public StringBuilder insert(@NonNegative int offset, long l);

    @Override
    public StringBuilder insert(@NonNegative int offset, float f);

    @Override
    public StringBuilder insert(@NonNegative int offset, double d);

    @Pure
    @Override
    @GTENegativeOne
    public int indexOf(@GuardSatisfied StringBuilder this, String str);

    @Pure
    @Override
    @GTENegativeOne
    public int indexOf(@GuardSatisfied StringBuilder this, String str, int fromIndex);

    @Pure
    @Override
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied StringBuilder this, String str);

    @Pure
    @Override
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied StringBuilder this, String str, int fromIndex);

    @Override
    public StringBuilder reverse();

    @SideEffectFree
    @Override
    @HotSpotIntrinsicCandidate
    public String toString(@GuardSatisfied StringBuilder this);

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;
}
