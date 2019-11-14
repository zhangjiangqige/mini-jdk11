package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.locks.LockSupport;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Exchanger<V> {

    private static final int ASHIFT = 5;

    private static final int MMASK = 0xff;

    private static final int SEQ = MMASK + 1;

    private static final int NCPU = Runtime.getRuntime().availableProcessors();

    static final int FULL = (NCPU >= (MMASK << 1)) ? MMASK : NCPU >>> 1;

    private static final int SPINS = 1 << 10;

    private static final Object NULL_ITEM = new Object();

    private static final Object TIMED_OUT = new Object();

    @jdk.internal.vm.annotation.Contended
    static final class Node {

        int index;

        int bound;

        int collides;

        int hash;

        Object item;

        volatile Object match;

        volatile Thread parked;
    }

    static final class Participant extends ThreadLocal<Node> {

        public Node initialValue();
    }

    private final Participant participant;

    private volatile Node[] arena;

    private volatile Node slot;

    private volatile int bound;

    private final Object arenaExchange(Object item, boolean timed, long ns);

    private final Object slotExchange(Object item, boolean timed, long ns);

    public Exchanger() {
        participant = new Participant();
    }

    @SuppressWarnings("unchecked")
    public V exchange(V x) throws InterruptedException;

    @SuppressWarnings("unchecked")
    public V exchange(V x, long timeout, TimeUnit unit) throws InterruptedException, TimeoutException;

    private static final VarHandle BOUND;

    private static final VarHandle SLOT;

    private static final VarHandle MATCH;

    private static final VarHandle AA;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            BOUND = l.findVarHandle(Exchanger.class, "bound", int.class);
            SLOT = l.findVarHandle(Exchanger.class, "slot", Node.class);
            MATCH = l.findVarHandle(Node.class, "match", Object.class);
            AA = MethodHandles.arrayElementVarHandle(Node[].class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
