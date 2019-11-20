package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.time.ZoneId;
import jdk.internal.util.StaticProperty;
import sun.security.action.GetPropertyAction;
import sun.util.calendar.ZoneInfo;
import sun.util.calendar.ZoneInfoFile;
import sun.util.locale.provider.TimeZoneNameUtility;

@AnnotatedFor({ "lock", "nullness" })
public abstract class TimeZone implements Serializable, Cloneable {

    public TimeZone() {
    }

    public static final int SHORT;

    public static final int LONG;

    public abstract int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds);

    public int getOffset(long date);

    public abstract void setRawOffset(@GuardSatisfied TimeZone this, int offsetMillis);

    public abstract int getRawOffset();

    public String getID();

    public void setID(@GuardSatisfied TimeZone this, String ID);

    @Pure
    public final String getDisplayName();

    @Pure
    public final String getDisplayName(Locale locale);

    @Pure
    public final String getDisplayName(boolean daylight, int style);

    @Pure
    public String getDisplayName(boolean daylight, int style, Locale locale);

    @Pure
    public int getDSTSavings();

    @Pure
    public abstract boolean useDaylightTime();

    public boolean observesDaylightTime();

    @Pure
    public abstract boolean inDaylightTime(Date date);

    @Pure
    public static synchronized TimeZone getTimeZone(String ID);

    public static TimeZone getTimeZone(ZoneId zoneId);

    public ZoneId toZoneId();

    public static synchronized String[] getAvailableIDs(int rawOffset);

    public static synchronized String[] getAvailableIDs();

    public static TimeZone getDefault();

    public static void setDefault(@Nullable TimeZone zone);

    public boolean hasSameRules(@Nullable TimeZone other);

    @SideEffectFree
    public Object clone(@GuardSatisfied TimeZone this);
}
