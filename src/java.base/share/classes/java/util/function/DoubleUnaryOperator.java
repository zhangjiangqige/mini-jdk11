package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface DoubleUnaryOperator {

    double applyAsDouble(double operand);

    default DoubleUnaryOperator compose(DoubleUnaryOperator before);

    default DoubleUnaryOperator andThen(DoubleUnaryOperator after);

    static DoubleUnaryOperator identity();
}
