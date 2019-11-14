package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.DoubleConsumer;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;

@AnnotatedFor({ "lock", "nullness" })
public class DoubleSummaryStatistics implements DoubleConsumer {

    private long count;

    private double sum;

    private double sumCompensation;

    private double simpleSum;

    private double min = Double.POSITIVE_INFINITY;

    private double max = Double.NEGATIVE_INFINITY;

    public DoubleSummaryStatistics() {
    }

    public DoubleSummaryStatistics(long count, double min, double max, double sum) throws IllegalArgumentException {
        if (count < 0L) {
            throw new IllegalArgumentException("Negative count value");
        } else if (count > 0L) {
            if (min > max)
                throw new IllegalArgumentException("Minimum greater than maximum");
            var ncount = DoubleStream.of(min, max, sum).filter(Double::isNaN).count();
            if (ncount > 0 && ncount < 3)
                throw new IllegalArgumentException("Some, not all, of the minimum, maximum, or sum is NaN");
            this.count = count;
            this.sum = sum;
            this.simpleSum = sum;
            this.sumCompensation = 0.0d;
            this.min = min;
            this.max = max;
        }
    }

    @Override
    public void accept(double value);

    public void combine(DoubleSummaryStatistics other);

    private void sumWithCompensation(double value);

    public final long getCount(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getSum(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getMin(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getMax(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getAverage(@GuardSatisfied DoubleSummaryStatistics this);

    @Override
    public String toString();
}
