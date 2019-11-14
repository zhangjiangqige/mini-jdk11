package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.*;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@AnnotatedFor({ "index", "interning", "lock", "nullness" })
@UsesObjectEquals
public class Timer {

    private final TaskQueue queue = new TaskQueue();

    private final TimerThread thread = new TimerThread(queue);

    private final Object threadReaper = new Object() {

        @SuppressWarnings("deprecation")
        protected void finalize() throws Throwable {
            synchronized (queue) {
                thread.newTasksMayBeScheduled = false;
                queue.notify();
            }
        }
    };

    private static final AtomicInteger nextSerialNumber = new AtomicInteger(0);

    private static int serialNumber();

    public Timer() {
        this("Timer-" + serialNumber());
    }

    public Timer(boolean isDaemon) {
        this("Timer-" + serialNumber(), isDaemon);
    }

    public Timer(String name) {
        thread.setName(name);
        thread.start();
    }

    public Timer(String name, boolean isDaemon) {
        thread.setName(name);
        thread.setDaemon(isDaemon);
        thread.start();
    }

    public void schedule(TimerTask task, long delay);

    public void schedule(TimerTask task, Date time);

    public void schedule(TimerTask task, long delay, long period);

    public void schedule(TimerTask task, Date firstTime, long period);

    public void scheduleAtFixedRate(TimerTask task, long delay, long period);

    public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period);

    private void sched(TimerTask task, long time, long period);

    public void cancel();

    @NonNegative
    public int purge();
}

class TimerThread extends Thread {

    boolean newTasksMayBeScheduled = true;

    private TaskQueue queue;

    TimerThread(TaskQueue queue) {
        this.queue = queue;
    }

    public void run();

    private void mainLoop();
}

class TaskQueue {

    private TimerTask[] queue = new TimerTask[128];

    private int size = 0;

    int size();

    void add(TimerTask task);

    TimerTask getMin();

    TimerTask get(int i);

    void removeMin();

    void quickRemove(int i);

    void rescheduleMin(long newTime);

    boolean isEmpty();

    void clear();

    private void fixUp(int k);

    private void fixDown(int k);

    void heapify();
}
