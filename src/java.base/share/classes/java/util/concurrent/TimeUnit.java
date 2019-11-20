package java.util.concurrent;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@AnnotatedFor({ "lock" })
public enum TimeUnit {

    NANOSECONDS(TimeUnit.NANO_SCALE),
    MICROSECONDS(TimeUnit.MICRO_SCALE),
    MILLISECONDS(TimeUnit.MILLI_SCALE),
    SECONDS(TimeUnit.SECOND_SCALE),
    MINUTES(TimeUnit.MINUTE_SCALE),
    HOURS(TimeUnit.HOUR_SCALE),
    DAYS(TimeUnit.DAY_SCALE);

    public long convert(long sourceDuration, TimeUnit sourceUnit);

    public long convert(Duration duration);

    public long toNanos(long duration);

    public long toMicros(long duration);

    public long toMillis(long duration);

    public long toSeconds(long duration);

    public long toMinutes(long duration);

    public long toHours(long duration);

    public long toDays(long duration);

    public void timedWait(Object obj, long timeout) throws InterruptedException;

    public void timedJoin(Thread thread, long timeout) throws InterruptedException;

    public void sleep(long timeout) throws InterruptedException;

    public ChronoUnit toChronoUnit();

    public static TimeUnit of(ChronoUnit chronoUnit);
}
