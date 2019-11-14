package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface DoubleBinaryOperator {

    double applyAsDouble(double left, double right);
}
