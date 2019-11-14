package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

@AnnotatedFor({ "lock", "nullness" })
public final class OptionalDouble {

    private static final OptionalDouble EMPTY = new OptionalDouble();

    private final boolean isPresent;

    private final double value;

    private OptionalDouble() {
        this.isPresent = false;
        this.value = Double.NaN;
    }

    public static OptionalDouble empty();

    private OptionalDouble(double value) {
        this.isPresent = true;
        this.value = value;
    }

    public static OptionalDouble of(double value);

    public double getAsDouble();

    public boolean isPresent();

    public boolean isEmpty();

    public void ifPresent(DoubleConsumer action);

    public void ifPresentOrElse(DoubleConsumer action, Runnable emptyAction);

    public DoubleStream stream();

    public double orElse(double other);

    public double orElseGet(DoubleSupplier supplier);

    public double orElseThrow();

    public <X extends Throwable> double orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();

    @Override
    public String toString();
}
