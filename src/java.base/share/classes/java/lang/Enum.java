package java.lang;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.GuardedByUnknown;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;

@AnnotatedFor({ "lock", "nullness", "index" })
@SuppressWarnings("serial")
public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable {

    private final String name;

    public final String name(@GuardedByUnknown @UnknownInitialization(java.lang.Enum.class) Enum<E> this);

    private final int ordinal;

    @NonNegative
    public final int ordinal();

    protected Enum(String name, @NonNegative int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    @SideEffectFree
    public String toString(@GuardSatisfied Enum<E> this);

    @Pure
    public final boolean equals(@GuardSatisfied Enum<E> this, @GuardSatisfied @Nullable Object other);

    @Pure
    public final int hashCode(@GuardSatisfied Enum<E> this);

    @SideEffectFree
    protected final Object clone(@GuardSatisfied Enum<E> this) throws CloneNotSupportedException;

    @SuppressWarnings({ "rawtypes" })
    public final int compareTo(E o);

    @SuppressWarnings("unchecked")
    public final Class<E> getDeclaringClass();

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name);

    @SuppressWarnings("deprecation")
    protected final void finalize();

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;

    private void readObjectNoData() throws ObjectStreamException;
}
