package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AnnotatedFor({ "nullness" })
public class ConcurrentLinkedQueue<E extends @NonNull Object> extends AbstractQueue<E> implements Queue<E>, java.io.Serializable {

    private static final long serialVersionUID = 196745693267521676L;

    static final class Node<E> {

        volatile E item;

        @Nullable
        volatile Node<E> next;

        Node(E item) {
            ITEM.set(this, item);
        }

        Node() {
        }

        void appendRelaxed(Node<E> next);

        boolean casItem(E cmp, E val);
    }

    transient volatile Node<E> head;

    private transient volatile Node<E> tail;

    public ConcurrentLinkedQueue() {
        head = tail = new Node<E>();
    }

    public ConcurrentLinkedQueue(Collection<? extends E> c) {
        Node<E> h = null, t = null;
        for (E e : c) {
            Node<E> newNode = new Node<E>(Objects.requireNonNull(e));
            if (h == null)
                h = t = newNode;
            else
                t.appendRelaxed(t = newNode);
        }
        if (h == null)
            h = t = new Node<E>();
        head = h;
        tail = t;
    }

    public boolean add(E e);

    final void updateHead(Node<E> h, Node<E> p);

    final Node<E> succ(Node<E> p);

    private boolean tryCasSuccessor(Node<E> pred, Node<E> c, Node<E> p);

    private Node<E> skipDeadNodes(Node<E> pred, Node<E> c, Node<E> p, Node<E> q);

    public boolean offer(E e);

    @Nullable
    public E poll();

    @Nullable
    public E peek();

    @Nullable
    Node<E> first();

    @Pure
    public boolean isEmpty();

    @Pure
    public int size();

    @Pure
    public boolean contains(Object o);

    public boolean remove(@Nullable Object o);

    public boolean addAll(Collection<? extends E> c);

    public String toString();

    private Object[] toArrayInternal(Object[] a);

    @SideEffectFree
    @PolyNull
    public Object[] toArray(ConcurrentLinkedQueue<@PolyNull E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a);

    @SideEffectFree
    public Iterator<E> iterator();

    private class Itr implements Iterator<E> {

        @Nullable
        private Node<E> nextNode;

        private E nextItem;

        private Node<E> lastRet;

        Itr() {
            restartFromHead: for (; ; ) {
                Node<E> h, p, q;
                for (p = h = head; ; p = q) {
                    final E item;
                    if ((item = p.item) != null) {
                        nextNode = p;
                        nextItem = item;
                        break;
                    } else if ((q = p.next) == null)
                        break;
                    else if (p == q)
                        continue restartFromHead;
                }
                updateHead(h, p);
                return;
            }
        }

        public boolean hasNext();

        public E next();

        public void remove();
    }

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    final class CLQSpliterator implements Spliterator<E> {

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
    @Override
    public Spliterator<E> spliterator();

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public void clear();

    private static final int MAX_HOPS = 8;

    private boolean bulkRemove(Predicate<? super E> filter);

    void forEachFrom(Consumer<? super E> action, Node<E> p);

    public void forEach(Consumer<? super E> action);

    private static final VarHandle HEAD;

    private static final VarHandle TAIL;

    static final VarHandle ITEM;

    static final VarHandle NEXT;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            HEAD = l.findVarHandle(ConcurrentLinkedQueue.class, "head", Node.class);
            TAIL = l.findVarHandle(ConcurrentLinkedQueue.class, "tail", Node.class);
            ITEM = l.findVarHandle(Node.class, "item", Object.class);
            NEXT = l.findVarHandle(Node.class, "next", Node.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
