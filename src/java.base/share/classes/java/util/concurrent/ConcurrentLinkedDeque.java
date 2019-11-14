package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AnnotatedFor({ "nullness" })
public class ConcurrentLinkedDeque<E extends @NonNull Object> extends AbstractCollection<E> implements Deque<E>, java.io.Serializable {

    private static final long serialVersionUID = 876323262645176354L;

    private transient volatile Node<E> head;

    private transient volatile Node<E> tail;

    private static final Node<Object> PREV_TERMINATOR, NEXT_TERMINATOR;

    @SuppressWarnings("unchecked")
    Node<E> prevTerminator();

    @SuppressWarnings("unchecked")
    Node<E> nextTerminator();

    static final class Node<E> {

        volatile Node<E> prev;

        volatile E item;

        volatile Node<E> next;
    }

    static <E> Node<E> newNode(E item);

    private void linkFirst(E e);

    private void linkLast(E e);

    private static final int HOPS = 2;

    void unlink(Node<E> x);

    private void unlinkFirst(Node<E> first, Node<E> next);

    private void unlinkLast(Node<E> last, Node<E> prev);

    private final void updateHead();

    private final void updateTail();

    private void skipDeletedPredecessors(Node<E> x);

    private void skipDeletedSuccessors(Node<E> x);

    final Node<E> succ(Node<E> p);

    final Node<E> pred(Node<E> p);

    Node<E> first();

    Node<E> last();

    private E screenNullResult(E v);

    public ConcurrentLinkedDeque() {
        head = tail = new Node<E>();
    }

    public ConcurrentLinkedDeque(Collection<? extends E> c) {
        Node<E> h = null, t = null;
        for (E e : c) {
            Node<E> newNode = newNode(Objects.requireNonNull(e));
            if (h == null)
                h = t = newNode;
            else {
                NEXT.set(t, newNode);
                PREV.set(newNode, t);
                t = newNode;
            }
        }
        initHeadTail(h, t);
    }

    private void initHeadTail(Node<E> h, Node<E> t);

    public void addFirst(E e);

    public void addLast(E e);

    public boolean offerFirst(E e);

    public boolean offerLast(E e);

    @Nullable
    public E peekFirst();

    @Nullable
    public E peekLast();

    public E getFirst();

    public E getLast();

    @Nullable
    public E pollFirst();

    @Nullable
    public E pollLast();

    public E removeFirst();

    public E removeLast();

    public boolean offer(E e);

    public boolean add(E e);

    @Nullable
    public E poll();

    @Nullable
    public E peek();

    public E remove();

    public E pop();

    public E element();

    public void push(E e);

    public boolean removeFirstOccurrence(Object o);

    public boolean removeLastOccurrence(Object o);

    @Pure
    public boolean contains(@Nullable Object o);

    @EnsuresNonNullIf(expression = { "peek()", "peekFirst()", "peekLast()", "poll()", "pollFirst()", "pollLast()" }, result = false)
    @Pure
    public boolean isEmpty();

    @Pure
    public int size();

    public boolean remove(Object o);

    public boolean addAll(Collection<? extends E> c);

    public void clear();

    public String toString();

    private Object[] toArrayInternal(Object[] a);

    @SideEffectFree
    @PolyNull
    public Object[] toArray(ConcurrentLinkedDeque<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a);

    @SideEffectFree
    public Iterator<E> iterator();

    public Iterator<E> descendingIterator();

    private abstract class AbstractItr implements Iterator<E> {

        @Nullable
        private Node<E> nextNode;

        @Nullable
        private E nextItem;

        @Nullable
        private Node<E> lastRet;

        abstract Node<E> startNode();

        abstract Node<E> nextNode(Node<E> p);

        AbstractItr() {
            advance();
        }

        private void advance();

        public boolean hasNext();

        public E next();

        public void remove();
    }

    private class Itr extends AbstractItr {

        Itr() {
        }

        Node<E> startNode();

        Node<E> nextNode(Node<E> p);
    }

    private class DescendingItr extends AbstractItr {

        DescendingItr() {
        }

        Node<E> startNode();

        Node<E> nextNode(Node<E> p);
    }

    final class CLDSpliterator implements Spliterator<E> {

        static final int MAX_BATCH = 1 << 25;

        Node<E> current;

        int batch;

        boolean exhausted;

        public Spliterator<E> trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        public boolean tryAdvance(Consumer<? super E> action);

        private void setCurrent(Node<E> p);

        private Node<E> current();

        public long estimateSize();

        public int characteristics();
    }

    @SideEffectFree
    public Spliterator<E> spliterator();

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    private boolean bulkRemove(Predicate<? super E> filter);

    public void forEach(Consumer<? super E> action);

    private static final VarHandle HEAD;

    private static final VarHandle TAIL;

    private static final VarHandle PREV;

    private static final VarHandle NEXT;

    private static final VarHandle ITEM;

    static {
        PREV_TERMINATOR = new Node<Object>();
        PREV_TERMINATOR.next = PREV_TERMINATOR;
        NEXT_TERMINATOR = new Node<Object>();
        NEXT_TERMINATOR.prev = NEXT_TERMINATOR;
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            HEAD = l.findVarHandle(ConcurrentLinkedDeque.class, "head", Node.class);
            TAIL = l.findVarHandle(ConcurrentLinkedDeque.class, "tail", Node.class);
            PREV = l.findVarHandle(Node.class, "prev", Node.class);
            NEXT = l.findVarHandle(Node.class, "next", Node.class);
            ITEM = l.findVarHandle(Node.class, "item", Object.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
