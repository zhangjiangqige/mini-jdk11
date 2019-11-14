package java.util.concurrent;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Phaser {

    private volatile long state;

    private static final int MAX_PARTIES = 0xffff;

    private static final int MAX_PHASE = Integer.MAX_VALUE;

    private static final int PARTIES_SHIFT = 16;

    private static final int PHASE_SHIFT = 32;

    private static final int UNARRIVED_MASK = 0xffff;

    private static final long PARTIES_MASK = 0xffff0000L;

    private static final long COUNTS_MASK = 0xffffffffL;

    private static final long TERMINATION_BIT = 1L << 63;

    private static final int ONE_ARRIVAL = 1;

    private static final int ONE_PARTY = 1 << PARTIES_SHIFT;

    private static final int ONE_DEREGISTER = ONE_ARRIVAL | ONE_PARTY;

    private static final int EMPTY = 1;

    private static int unarrivedOf(long s);

    private static int partiesOf(long s);

    private static int phaseOf(long s);

    private static int arrivedOf(long s);

    private final Phaser parent;

    private final Phaser root;

    private final AtomicReference<QNode> evenQ;

    private final AtomicReference<QNode> oddQ;

    private String badArrive(long s);

    private String badRegister(long s);

    private int doArrive(int adjust);

    private int doRegister(int registrations);

    private long reconcileState();

    public Phaser() {
        this(null, 0);
    }

    public Phaser(int parties) {
        this(null, parties);
    }

    public Phaser(Phaser parent) {
        this(parent, 0);
    }

    public Phaser(Phaser parent, int parties) {
        if (parties >>> PARTIES_SHIFT != 0)
            throw new IllegalArgumentException("Illegal number of parties");
        int phase = 0;
        this.parent = parent;
        if (parent != null) {
            final Phaser root = parent.root;
            this.root = root;
            this.evenQ = root.evenQ;
            this.oddQ = root.oddQ;
            if (parties != 0)
                phase = parent.doRegister(1);
        } else {
            this.root = this;
            this.evenQ = new AtomicReference<QNode>();
            this.oddQ = new AtomicReference<QNode>();
        }
        this.state = (parties == 0) ? (long) EMPTY : ((long) phase << PHASE_SHIFT) | ((long) parties << PARTIES_SHIFT) | ((long) parties);
    }

    public int register();

    public int bulkRegister(int parties);

    public int arrive();

    public int arriveAndDeregister();

    public int arriveAndAwaitAdvance();

    public int awaitAdvance(int phase);

    public int awaitAdvanceInterruptibly(int phase) throws InterruptedException;

    public int awaitAdvanceInterruptibly(int phase, long timeout, TimeUnit unit) throws InterruptedException, TimeoutException;

    public void forceTermination();

    public final int getPhase();

    public int getRegisteredParties();

    public int getArrivedParties();

    public int getUnarrivedParties();

    public Phaser getParent();

    public Phaser getRoot();

    public boolean isTerminated();

    protected boolean onAdvance(int phase, int registeredParties);

    public String toString();

    private String stateToString(long s);

    private void releaseWaiters(int phase);

    private int abortWait(int phase);

    private static final int NCPU = Runtime.getRuntime().availableProcessors();

    static final int SPINS_PER_ARRIVAL = (NCPU < 2) ? 1 : 1 << 8;

    private int internalAwaitAdvance(int phase, QNode node);

    static final class QNode implements ForkJoinPool.ManagedBlocker {

        final Phaser phaser;

        final int phase;

        final boolean interruptible;

        final boolean timed;

        boolean wasInterrupted;

        long nanos;

        final long deadline;

        volatile Thread thread;

        QNode next;

        QNode(Phaser phaser, int phase, boolean interruptible, boolean timed, long nanos) {
            this.phaser = phaser;
            this.phase = phase;
            this.interruptible = interruptible;
            this.nanos = nanos;
            this.timed = timed;
            this.deadline = timed ? System.nanoTime() + nanos : 0L;
            thread = Thread.currentThread();
        }

        public boolean isReleasable();

        public boolean block();
    }

    private static final VarHandle STATE;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            STATE = l.findVarHandle(Phaser.class, "state", long.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
        Class<?> ensureLoaded = LockSupport.class;
    }
}
