package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.Collector;

@AnnotatedFor({ "lock", "nullness" })
public class LongSummaryStatistics implements LongConsumer, IntConsumer {

    private long count;

    private long sum;

    private long min = Long.MAX_VALUE;

    private long max = Long.MIN_VALUE;

    public LongSummaryStatistics() {
    }

    public LongSummaryStatistics(long count, long min, long max, long sum) throws IllegalArgumentException {
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

    @Override
    public void accept(long value);

    public void combine(LongSummaryStatistics other);

    public final long getCount();

    public final long getSum();

    public final long getMin();

    public final long getMax();

    public final double getAverage();

    @Override
    public String toString();
}
