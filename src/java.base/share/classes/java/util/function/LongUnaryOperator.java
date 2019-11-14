package java.util.function;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "lock", "nullness" })
@FunctionalInterface
public interface LongUnaryOperator {

    long applyAsLong(long operand);

    default LongUnaryOperator compose(LongUnaryOperator before);

    default LongUnaryOperator andThen(LongUnaryOperator after);

    static LongUnaryOperator identity();
}
