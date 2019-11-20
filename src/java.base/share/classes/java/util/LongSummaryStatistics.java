package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.Collector;

@AnnotatedFor({ "lock", "nullness" })
public class LongSummaryStatistics implements LongConsumer, IntConsumer {

    public LongSummaryStatistics() {
    }

    public LongSummaryStatistics(long count, long min, long max, long sum) throws IllegalArgumentException {
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
