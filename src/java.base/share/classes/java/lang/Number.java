package java.lang;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock", "nullness" })
public abstract class Number implements java.io.Serializable {

    @Pure
    public abstract int intValue(@GuardSatisfied Number this);

    @Pure
    public abstract long longValue(@GuardSatisfied Number this);

    @Pure
    public abstract float floatValue(@GuardSatisfied Number this);

    @Pure
    public abstract double doubleValue(@GuardSatisfied Number this);

    @Pure
    public byte byteValue(@GuardSatisfied Number this);

    @Pure
    public short shortValue(@GuardSatisfied Number this);

    private static final long serialVersionUID = -8742448824652078965L;
}
