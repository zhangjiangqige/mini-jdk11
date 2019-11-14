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

    public static final int SHORT = 0;

    public static final int LONG = 1;

    private static final int ONE_MINUTE = 60 * 1000;

    private static final int ONE_HOUR = 60 * ONE_MINUTE;

    private static final int ONE_DAY = 24 * ONE_HOUR;

    static final long serialVersionUID = 3581463369166924961L;

    public abstract int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds);

    public int getOffset(long date);

    int getOffsets(long date, int[] offsets);

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

    private static String[] getDisplayNames(String id, Locale locale);

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

    private ZoneId toZoneId0();

    private static TimeZone getTimeZone(String ID, boolean fallback);

    public static synchronized String[] getAvailableIDs(int rawOffset);

    public static synchronized String[] getAvailableIDs();

    private static native String getSystemTimeZoneID(String javaHome);

    private static native String getSystemGMTOffsetID();

    public static TimeZone getDefault();

    static TimeZone getDefaultRef();

    private static synchronized TimeZone setDefaultZone();

    public static void setDefault(@Nullable TimeZone zone);

    public boolean hasSameRules(@Nullable TimeZone other);

    @SideEffectFree
    public Object clone(@GuardSatisfied TimeZone this);

    static final TimeZone NO_TIMEZONE = null;

    private String ID;

    private transient ZoneId zoneId;

    private static volatile TimeZone defaultTimeZone;

    static final String GMT_ID = "GMT";

    private static final int GMT_ID_LENGTH = 3;

    private static final TimeZone parseCustomTimeZone(String id);
}
