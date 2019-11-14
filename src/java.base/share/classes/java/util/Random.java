package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;
import jdk.internal.misc.Unsafe;

@AnnotatedFor({ "index", "interning", "lock", "nullness", "signedness" })
@UsesObjectEquals
public class Random implements java.io.Serializable {

    static final long serialVersionUID = 3905348978240129619L;

    private final AtomicLong seed;

    private static final long multiplier = 0x5DEECE66DL;

    private static final long addend = 0xBL;

    private static final long mask = (1L << 48) - 1;

    private static final double DOUBLE_UNIT = 0x1.0p-53;

    static final String BadBound = "bound must be positive";

    static final String BadRange = "bound must be greater than origin";

    static final String BadSize = "size must be non-negative";

    public Random() {
        this(seedUniquifier() ^ System.nanoTime());
    }

    private static long seedUniquifier();

    private static final AtomicLong seedUniquifier = new AtomicLong(8682522807148012L);

    public Random(long seed) {
        if (getClass() == Random.class)
            this.seed = new AtomicLong(initialScramble(seed));
        else {
            this.seed = new AtomicLong();
            setSeed(seed);
        }
    }

    private static long initialScramble(long seed);

    public synchronized void setSeed(@GuardSatisfied Random this, long seed);

    protected int next(int bits);

    public void nextBytes(@PolySigned byte[] bytes);

    final long internalNextLong(long origin, long bound);

    final int internalNextInt(int origin, int bound);

    final double internalNextDouble(double origin, double bound);

    public int nextInt();

    @NonNegative
    public int nextInt(@Positive int bound);

    public long nextLong();

    public boolean nextBoolean();

    public float nextFloat();

    public double nextDouble();

    private double nextNextGaussian;

    private boolean haveNextNextGaussian = false;

    public synchronized double nextGaussian();

    public IntStream ints(long streamSize);

    public IntStream ints();

    public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound);

    public IntStream ints(int randomNumberOrigin, int randomNumberBound);

    public LongStream longs(long streamSize);

    public LongStream longs();

    public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound);

    public LongStream longs(long randomNumberOrigin, long randomNumberBound);

    public DoubleStream doubles(long streamSize);

    public DoubleStream doubles();

    public DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound);

    public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound);

    static final class RandomIntsSpliterator implements Spliterator.OfInt {

        final Random rng;

        long index;

        final long fence;

        final int origin;

        final int bound;

        RandomIntsSpliterator(Random rng, long index, long fence, int origin, int bound) {
            this.rng = rng;
            this.index = index;
            this.fence = fence;
            this.origin = origin;
            this.bound = bound;
        }

        public RandomIntsSpliterator trySplit();

        public long estimateSize();

        public int characteristics();

        public boolean tryAdvance(IntConsumer consumer);

        public void forEachRemaining(IntConsumer consumer);
    }

    static final class RandomLongsSpliterator implements Spliterator.OfLong {

        final Random rng;

        long index;

        final long fence;

        final long origin;

        final long bound;

        RandomLongsSpliterator(Random rng, long index, long fence, long origin, long bound) {
            this.rng = rng;
            this.index = index;
            this.fence = fence;
            this.origin = origin;
            this.bound = bound;
        }

        public RandomLongsSpliterator trySplit();

        public long estimateSize();

        public int characteristics();

        public boolean tryAdvance(LongConsumer consumer);

        public void forEachRemaining(LongConsumer consumer);
    }

    static final class RandomDoublesSpliterator implements Spliterator.OfDouble {

        final Random rng;

        long index;

        final long fence;

        final double origin;

        final double bound;

        RandomDoublesSpliterator(Random rng, long index, long fence, double origin, double bound) {
            this.rng = rng;
            this.index = index;
            this.fence = fence;
            this.origin = origin;
            this.bound = bound;
        }

        public RandomDoublesSpliterator trySplit();

        public long estimateSize();

        public int characteristics();

        public boolean tryAdvance(DoubleConsumer consumer);

        public void forEachRemaining(DoubleConsumer consumer);
    }

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("seed", Long.TYPE), new ObjectStreamField("nextNextGaussian", Double.TYPE), new ObjectStreamField("haveNextNextGaussian", Boolean.TYPE) };

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    private synchronized void writeObject(ObjectOutputStream s) throws IOException;

    private static final Unsafe unsafe = Unsafe.getUnsafe();

    private static final long seedOffset;

    static {
        try {
            seedOffset = unsafe.objectFieldOffset(Random.class.getDeclaredField("seed"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private void resetSeed(long seedVal);
}
