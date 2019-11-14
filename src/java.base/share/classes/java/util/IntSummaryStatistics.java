package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.IntConsumer;
import java.util.stream.Collector;

@AnnotatedFor({ "lock", "nullness" })
public class IntSummaryStatistics implements IntConsumer {

    private long count;

    private long sum;

    private int min = Integer.MAX_VALUE;

    private int max = Integer.MIN_VALUE;

    public IntSummaryStatistics() {
    }

    public IntSummaryStatistics(long count, int min, int max, long sum) throws IllegalArgumentException {
        if (count < 0L) {
            throw new IllegalArgumentException("Negative count value");
        } else if (count > 0L) {
            if (min > max)
                throw new IllegalArgumentException("Minimum greater than maximum");
            this.count = count;
            this.sum = sum;
            this.min = min;
            this.max = max;
        }
    }

    @Override
    public void accept(int value);

    public void combine(IntSummaryStatistics other);

    public final long getCount(@GuardSatisfied IntSummaryStatistics this);

    public final long getSum(@GuardSatisfied IntSummaryStatistics this);

    public final int getMin(@GuardSatisfied IntSummaryStatistics this);

    public final int getMax(@GuardSatisfied IntSummaryStatistics this);

    public final double getAverage(@GuardSatisfied IntSummaryStatistics this);

    @Override
    public String toString();
}
