package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface IntUnaryOperator {

    int applyAsInt(int operand);

    default IntUnaryOperator compose(IntUnaryOperator before);

    default IntUnaryOperator andThen(IntUnaryOperator after);

    static IntUnaryOperator identity();
}
