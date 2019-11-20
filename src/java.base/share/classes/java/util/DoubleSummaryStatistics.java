package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.DoubleConsumer;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;

@AnnotatedFor({ "lock", "nullness" })
public class DoubleSummaryStatistics implements DoubleConsumer {

    public DoubleSummaryStatistics() {
    }

    public DoubleSummaryStatistics(long count, double min, double max, double sum) throws IllegalArgumentException {
    }

    @Override
    public void accept(double value);

    public void combine(DoubleSummaryStatistics other);

    public final long getCount(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getSum(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getMin(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getMax(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getAverage(@GuardSatisfied DoubleSummaryStatistics this);

    @Override
    public String toString();
}
