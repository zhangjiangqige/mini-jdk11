package java.util.function;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface DoubleSupplier {

    double getAsDouble(@GuardSatisfied DoubleSupplier this);
}
