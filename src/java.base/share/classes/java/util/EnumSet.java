package java.util;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.SharedSecrets;

@AnnotatedFor({ "nullness", "index" })
@SuppressWarnings("serial")
public abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E> implements Cloneable, java.io.Serializable {

    final transient Class<E> elementType;

    final transient Enum<?>[] universe;

    EnumSet(Class<E> elementType, Enum<?>[] universe) {
        this.elementType = elementType;
        this.universe = universe;
    }

    public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType);

    public static <E extends Enum<E>> EnumSet<E> allOf(Class<E> elementType);

    abstract void addAll();

    public static <E extends Enum<E>> EnumSet<E> copyOf(EnumSet<E> s);

    public static <E extends Enum<E>> EnumSet<E> copyOf(Collection<E> c);

    public static <E extends Enum<E>> EnumSet<E> complementOf(EnumSet<E> s);

    public static <E extends Enum<E>> EnumSet<E> of(E e);

    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2);

    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3);

    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3, E e4);

    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3, E e4, E e5);

    @SafeVarargs
    public static <E extends Enum<E>> EnumSet<E> of(E first, E... rest);

    public static <E extends Enum<E>> EnumSet<E> range(E from, E to);

    abstract void addRange(E from, E to);

    @SuppressWarnings("unchecked")
    public EnumSet<E> clone();

    abstract void complement();

    final void typeCheck(E e);

    private static <E extends Enum<E>> E[] getUniverse(Class<E> elementType);

    private static class SerializationProxy<E extends Enum<E>> implements java.io.Serializable {

        private static final Enum<?>[] ZERO_LENGTH_ENUM_ARRAY = new Enum<?>[0];

        private final Class<E> elementType;

        @Nullable
        private final Enum<?>[] elements;

        SerializationProxy(EnumSet<E> set) {
            elementType = set.elementType;
            elements = set.toArray(ZERO_LENGTH_ENUM_ARRAY);
        }

        @SuppressWarnings("unchecked")
        private Object readResolve();

        private static final long serialVersionUID = 362491234563181265L;
    }

    Object writeReplace();

    private void readObject(java.io.ObjectInputStream s) throws java.io.InvalidObjectException;
}
