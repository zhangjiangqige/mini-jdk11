package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.Gregorian;

@AnnotatedFor({ "lock", "nullness", "index" })
public class SimpleTimeZone extends TimeZone {

    public SimpleTimeZone(int rawOffset, String ID) {
        this.rawOffset = rawOffset;
        setID(ID);
        dstSavings = millisPerHour;
    }

    public SimpleTimeZone(int rawOffset, String ID, int startMonth, int startDay, int startDayOfWeek, int startTime, int endMonth, int endDay, int endDayOfWeek, int endTime) {
        this(rawOffset, ID, startMonth, startDay, startDayOfWeek, startTime, WALL_TIME, endMonth, endDay, endDayOfWeek, endTime, WALL_TIME, millisPerHour);
    }

    public SimpleTimeZone(int rawOffset, String ID, int startMonth, int startDay, int startDayOfWeek, int startTime, int endMonth, int endDay, int endDayOfWeek, int endTime, int dstSavings) {
        this(rawOffset, ID, startMonth, startDay, startDayOfWeek, startTime, WALL_TIME, endMonth, endDay, endDayOfWeek, endTime, WALL_TIME, dstSavings);
    }

    public SimpleTimeZone(int rawOffset, String ID, int startMonth, int startDay, int startDayOfWeek, int startTime, int startTimeMode, int endMonth, int endDay, int endDayOfWeek, int endTime, int endTimeMode, int dstSavings) {
        setID(ID);
        this.rawOffset = rawOffset;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.startDayOfWeek = startDayOfWeek;
        this.startTime = startTime;
        this.startTimeMode = startTimeMode;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.endDayOfWeek = endDayOfWeek;
        this.endTime = endTime;
        this.endTimeMode = endTimeMode;
        this.dstSavings = dstSavings;
        decodeRules();
        if (dstSavings <= 0) {
            throw new IllegalArgumentException("Illegal daylight saving value: " + dstSavings);
        }
    }

    public void setStartYear(@GuardSatisfied SimpleTimeZone this, int year);

    public void setStartRule(@GuardSatisfied SimpleTimeZone this, int startMonth, int startDay, int startDayOfWeek, int startTime);

    public void setStartRule(@GuardSatisfied SimpleTimeZone this, int startMonth, int startDay, int startTime);

    public void setStartRule(@GuardSatisfied SimpleTimeZone this, int startMonth, int startDay, int startDayOfWeek, int startTime, boolean after);

    public void setEndRule(@GuardSatisfied SimpleTimeZone this, int endMonth, int endDay, int endDayOfWeek, int endTime);

    public void setEndRule(@GuardSatisfied SimpleTimeZone this, int endMonth, int endDay, int endTime);

    public void setEndRule(@GuardSatisfied SimpleTimeZone this, int endMonth, int endDay, int endDayOfWeek, int endTime, boolean after);

    public int getOffset(@GuardSatisfied SimpleTimeZone this, long date);

    int getOffsets(long date, int[] offsets);

    public int getOffset(@GuardSatisfied SimpleTimeZone this, int era, int year, int month, int day, int dayOfWeek, int millis);

    private int getOffset(BaseCalendar cal, BaseCalendar.Date cdate, int year, long time);

    private long getStart(BaseCalendar cal, BaseCalendar.Date cdate, int year);

    private long getEnd(BaseCalendar cal, BaseCalendar.Date cdate, int year);

    private long getTransition(BaseCalendar cal, BaseCalendar.Date cdate, int mode, int year, int month, int dayOfMonth, int dayOfWeek, int timeOfDay);

    public int getRawOffset(@GuardSatisfied SimpleTimeZone this);

    public void setRawOffset(@GuardSatisfied SimpleTimeZone this, int offsetMillis);

    public void setDSTSavings(@GuardSatisfied SimpleTimeZone this, int millisSavedDuringDST);

    public int getDSTSavings(@GuardSatisfied SimpleTimeZone this);

    public boolean useDaylightTime(@GuardSatisfied SimpleTimeZone this);

    @Override
    public boolean observesDaylightTime();

    public boolean inDaylightTime(@GuardSatisfied SimpleTimeZone this, Date date);

    @SideEffectFree
    public Object clone(@GuardSatisfied SimpleTimeZone this);

    @Pure
    public int hashCode(@GuardSatisfied SimpleTimeZone this);

    @Pure
    public boolean equals(@GuardSatisfied SimpleTimeZone this, @GuardSatisfied @Nullable Object obj);

    public boolean hasSameRules(TimeZone other);

    @SideEffectFree
    public String toString(@GuardSatisfied SimpleTimeZone this);

    private int startMonth;

    private int startDay;

    private int startDayOfWeek;

    private int startTime;

    private int startTimeMode;

    private int endMonth;

    private int endDay;

    private int endDayOfWeek;

    private int endTime;

    private int endTimeMode;

    private int startYear;

    private int rawOffset;

    private boolean useDaylight = false;

    private static final int millisPerHour = 60 * 60 * 1000;

    private static final int millisPerDay = 24 * millisPerHour;

    private final byte[] monthLength = staticMonthLength;

    private static final byte[] staticMonthLength = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private static final byte[] staticLeapMonthLength = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private int startMode;

    private int endMode;

    private int dstSavings;

    private static final Gregorian gcal = CalendarSystem.getGregorianCalendar();

    private static final class Cache {

        final long year;

        final long start;

        final long end;

        Cache(long year, long start, long end) {
            this.year = year;
            this.start = start;
            this.end = end;
        }
    }

    private transient volatile Cache cache;

    private static final int DOM_MODE = 1;

    private static final int DOW_IN_MONTH_MODE = 2;

    private static final int DOW_GE_DOM_MODE = 3;

    private static final int DOW_LE_DOM_MODE = 4;

    public static final int WALL_TIME = 0;

    public static final int STANDARD_TIME = 1;

    public static final int UTC_TIME = 2;

    static final long serialVersionUID = -403250971215465050L;

    static final int currentSerialVersion = 2;

    private int serialVersionOnStream = currentSerialVersion;

    private static final int MAX_RULE_NUM = 6;

    private void invalidateCache();

    private void decodeRules();

    private void decodeStartRule();

    private void decodeEndRule();

    private void makeRulesCompatible();

    private byte[] packRules();

    private void unpackRules(byte[] rules);

    private int[] packTimes();

    private void unpackTimes(int[] times);

    private void writeObject(ObjectOutputStream stream) throws IOException;

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException;
}
