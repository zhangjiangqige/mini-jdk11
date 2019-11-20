package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.*;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@AnnotatedFor({ "index", "interning", "lock", "nullness" })
@UsesObjectEquals
public class Timer {

    public Timer() {
    }

    public Timer(boolean isDaemon) {
    }

    public Timer(String name) {
    }

    public Timer(String name, boolean isDaemon) {
    }

    public void schedule(TimerTask task, long delay);

    public void schedule(TimerTask task, Date time);

    public void schedule(TimerTask task, long delay, long period);

    public void schedule(TimerTask task, Date firstTime, long period);

    public void scheduleAtFixedRate(TimerTask task, long delay, long period);

    public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period);

    public void cancel();

    @NonNegative
    public int purge();
}
