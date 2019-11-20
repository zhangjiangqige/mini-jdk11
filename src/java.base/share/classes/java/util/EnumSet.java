package java.util;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.SharedSecrets;

@AnnotatedFor({ "nullness", "index" })
@SuppressWarnings("serial")
public abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E> implements Cloneable, java.io.Serializable {

    public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType);

    public static <E extends Enum<E>> EnumSet<E> allOf(Class<E> elementType);

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

    @SuppressWarnings("unchecked")
    public EnumSet<E> clone();
}
