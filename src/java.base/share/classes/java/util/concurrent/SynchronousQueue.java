package java.util.concurrent;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@AnnotatedFor({ "nullness" })
public class SynchronousQueue<E extends @NonNull Object> extends AbstractQueue<E> implements BlockingQueue<E>, java.io.Serializable {

    private static final long serialVersionUID = -3223113410248163686L;

    abstract static class Transferer<E> {

        abstract E transfer(E e, boolean timed, long nanos);
    }

    static final int MAX_TIMED_SPINS = (Runtime.getRuntime().availableProcessors() < 2) ? 0 : 32;

    static final int MAX_UNTIMED_SPINS = MAX_TIMED_SPINS * 16;

    static final long SPIN_FOR_TIMEOUT_THRESHOLD = 1000L;

    static final class TransferStack<E> extends Transferer<E> {

        static final int REQUEST = 0;

        static final int DATA = 1;

        static final int FULFILLING = 2;

        static boolean isFulfilling(int m);

        static final class SNode {

            volatile SNode next;

            volatile SNode match;

            volatile Thread waiter;

            Object item;

            int mode;

            SNode(Object item) {
                this.item = item;
            }

            boolean casNext(SNode cmp, SNode val);

            boolean tryMatch(SNode s);

            void tryCancel();

            boolean isCancelled();

            private static final VarHandle SMATCH;

            private static final VarHandle SNEXT;

            static {
                try {
                    MethodHandles.Lookup l = MethodHandles.lookup();
                    SMATCH = l.findVarHandle(SNode.class, "match", SNode.class);
                    SNEXT = l.findVarHandle(SNode.class, "next", SNode.class);
                } catch (ReflectiveOperationException e) {
                    throw new ExceptionInInitializerError(e);
                }
            }
        }

        volatile SNode head;

        boolean casHead(SNode h, SNode nh);

        static SNode snode(@Nullable SNode s, Object e, SNode next, int mode);

        @SuppressWarnings("unchecked")
        E transfer(E e, boolean timed, long nanos);

        SNode awaitFulfill(SNode s, boolean timed, long nanos);

        boolean shouldSpin(SNode s);

        void clean(SNode s);

        private static final VarHandle SHEAD;

        static {
            try {
                MethodHandles.Lookup l = MethodHandles.lookup();
                SHEAD = l.findVarHandle(TransferStack.class, "head", SNode.class);
            } catch (ReflectiveOperationException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    static final class TransferQueue<E> extends Transferer<E> {

        static final class QNode {

            volatile QNode next;

            volatile Object item;

            volatile Thread waiter;

            final boolean isData;

            QNode(Object item, boolean isData) {
                this.item = item;
                this.isData = isData;
            }

            boolean casNext(QNode cmp, QNode val);

            boolean casItem(Object cmp, Object val);

            void tryCancel(Object cmp);

            boolean isCancelled();

            boolean isOffList();

            private static final VarHandle QITEM;

            private static final VarHandle QNEXT;

            static {
                try {
                    MethodHandles.Lookup l = MethodHandles.lookup();
                    QITEM = l.findVarHandle(QNode.class, "item", Object.class);
                    QNEXT = l.findVarHandle(QNode.class, "next", QNode.class);
                } catch (ReflectiveOperationException e) {
                    throw new ExceptionInInitializerError(e);
                }
            }
        }

        transient volatile QNode head;

        transient volatile QNode tail;

        transient volatile QNode cleanMe;

        TransferQueue() {
            QNode h = new QNode(null, false);
            head = h;
            tail = h;
        }

        void advanceHead(QNode h, QNode nh);

        void advanceTail(QNode t, QNode nt);

        boolean casCleanMe(QNode cmp, QNode val);

        @SuppressWarnings("unchecked")
        E transfer(E e, boolean timed, long nanos);

        Object awaitFulfill(QNode s, E e, boolean timed, long nanos);

        void clean(QNode pred, QNode s);

        private static final VarHandle QHEAD;

        private static final VarHandle QTAIL;

        private static final VarHandle QCLEANME;

        static {
            try {
                MethodHandles.Lookup l = MethodHandles.lookup();
                QHEAD = l.findVarHandle(TransferQueue.class, "head", QNode.class);
                QTAIL = l.findVarHandle(TransferQueue.class, "tail", QNode.class);
                QCLEANME = l.findVarHandle(TransferQueue.class, "cleanMe", QNode.class);
            } catch (ReflectiveOperationException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    private transient volatile Transferer<E> transferer;

    public SynchronousQueue() {
        this(false);
    }

    public SynchronousQueue(boolean fair) {
        transferer = fair ? new TransferQueue<E>() : new TransferStack<E>();
    }

    public void put(E e) throws InterruptedException;

    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;

    public boolean offer(E e);

    public E take() throws InterruptedException;

    public E poll(long timeout, TimeUnit unit) throws InterruptedException;

    public E poll();

    @Pure
    public boolean isEmpty();

    @Pure
    public int size();

    public int remainingCapacity();

    public void clear();

    @Pure
    public boolean contains(Object o);

    public boolean remove(Object o);

    @Pure
    public boolean containsAll(Collection<?> c);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public E peek();

    @SideEffectFree
    public Iterator<E> iterator();

    @SideEffectFree
    public Spliterator<E> spliterator();

    @SideEffectFree
    @PolyNull
    public Object[] toArray(SynchronousQueue<@PolyNull E> this);

    @SideEffectFree
    public <T> T[] toArray(T[] a);

    public String toString();

    public int drainTo(Collection<? super E> c);

    public int drainTo(Collection<? super E> c, int maxElements);

    @SuppressWarnings("serial")
    static class WaitQueue implements java.io.Serializable {
    }

    static class LifoWaitQueue extends WaitQueue {

        private static final long serialVersionUID = -3633113410248163686L;
    }

    static class FifoWaitQueue extends WaitQueue {

        private static final long serialVersionUID = -3623113410248163686L;
    }

    private ReentrantLock qlock;

    private WaitQueue waitingProducers;

    private WaitQueue waitingConsumers;

    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;

    static {
        Class<?> ensureLoaded = LockSupport.class;
    }
}
