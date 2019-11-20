package java.util;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@AnnotatedFor({ "lock", "nullness", "index" })
public class BitSet implements Cloneable, java.io.Serializable {

    public BitSet() {
    }

    public BitSet(@NonNegative int nbits) {
    }

    public static BitSet valueOf(long[] longs);

    public static BitSet valueOf(LongBuffer lb);

    public static BitSet valueOf(byte[] bytes);

    public static BitSet valueOf(ByteBuffer bb);

    public byte[] toByteArray();

    public long[] toLongArray();

    public void flip(@GuardSatisfied BitSet this, @NonNegative int bitIndex);

    public void flip(@GuardSatisfied BitSet this, @NonNegative int fromIndex, @NonNegative int toIndex);

    public void set(@GuardSatisfied BitSet this, @NonNegative int bitIndex);

    public void set(@GuardSatisfied BitSet this, @NonNegative int bitIndex, boolean value);

    public void set(@GuardSatisfied BitSet this, @NonNegative int fromIndex, @NonNegative int toIndex);

    public void set(@GuardSatisfied BitSet this, @NonNegative int fromIndex, @NonNegative int toIndex, boolean value);

    public void clear(@GuardSatisfied BitSet this, @NonNegative int bitIndex);

    public void clear(@GuardSatisfied BitSet this, @NonNegative int fromIndex, @NonNegative int toIndex);

    public void clear(@GuardSatisfied BitSet this);

    @Pure
    public boolean get(@GuardSatisfied BitSet this, @NonNegative int bitIndex);

    @Pure
    public BitSet get(@GuardSatisfied BitSet this, @NonNegative int fromIndex, @NonNegative int toIndex);

    @Pure
    @GTENegativeOne
    public int nextSetBit(@GuardSatisfied BitSet this, @NonNegative int fromIndex);

    @Pure
    @NonNegative
    public int nextClearBit(@GuardSatisfied BitSet this, @NonNegative int fromIndex);

    @GTENegativeOne
    public int previousSetBit(@GTENegativeOne int fromIndex);

    @GTENegativeOne
    public int previousClearBit(@GTENegativeOne int fromIndex);

    @Pure
    @NonNegative
    public int length(@GuardSatisfied BitSet this);

    @Pure
    public boolean isEmpty(@GuardSatisfied BitSet this);

    @Pure
    public boolean intersects(@GuardSatisfied BitSet this, @GuardSatisfied BitSet set);

    @Pure
    @NonNegative
    public int cardinality(@GuardSatisfied BitSet this);

    public void and(@GuardSatisfied BitSet this, BitSet set);

    public void or(@GuardSatisfied BitSet this, BitSet set);

    public void xor(@GuardSatisfied BitSet this, BitSet set);

    public void andNot(@GuardSatisfied BitSet this, BitSet set);

    @Pure
    public int hashCode(@GuardSatisfied BitSet this);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied BitSet this);

    @Pure
    public boolean equals(@GuardSatisfied BitSet this, @GuardSatisfied @Nullable Object obj);

    @SideEffectFree
    public Object clone(@GuardSatisfied BitSet this);

    @SideEffectFree
    public String toString(@GuardSatisfied BitSet this);

    public IntStream stream();
}
