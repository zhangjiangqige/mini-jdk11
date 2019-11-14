package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "index" })
class RegularEnumSet<E extends Enum<E>> extends EnumSet<E> {

    private static final long serialVersionUID = 3411599620347842686L;

    private long elements = 0L;

    RegularEnumSet(Class<E> elementType, Enum<?>[] universe) {
        super(elementType, universe);
    }

    void addRange(E from, E to);

    void addAll();

    void complement();

    public Iterator<E> iterator();

    private class EnumSetIterator<E extends Enum<E>> implements Iterator<E> {

        long unseen;

        long lastReturned = 0;

        EnumSetIterator() {
            unseen = elements;
        }

        public boolean hasNext();

        @SuppressWarnings("unchecked")
        public E next();

        public void remove();
    }

    @NonNegative
    public int size();

    public boolean isEmpty();

    public boolean contains(Object e);

    public boolean add(E e);

    public boolean remove(Object e);

    public boolean containsAll(Collection<?> c);

    public boolean addAll(Collection<? extends E> c);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public void clear();

    public boolean equals(Object o);
}
