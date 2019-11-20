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
    }

    public SimpleTimeZone(int rawOffset, String ID, int startMonth, int startDay, int startDayOfWeek, int startTime, int endMonth, int endDay, int endDayOfWeek, int endTime) {
    }

    public SimpleTimeZone(int rawOffset, String ID, int startMonth, int startDay, int startDayOfWeek, int startTime, int endMonth, int endDay, int endDayOfWeek, int endTime, int dstSavings) {
    }

    public SimpleTimeZone(int rawOffset, String ID, int startMonth, int startDay, int startDayOfWeek, int startTime, int startTimeMode, int endMonth, int endDay, int endDayOfWeek, int endTime, int endTimeMode, int dstSavings) {
    }

    public void setStartYear(@GuardSatisfied SimpleTimeZone this, int year);

    public void setStartRule(@GuardSatisfied SimpleTimeZone this, int startMonth, int startDay, int startDayOfWeek, int startTime);

    public void setStartRule(@GuardSatisfied SimpleTimeZone this, int startMonth, int startDay, int startTime);

    public void setStartRule(@GuardSatisfied SimpleTimeZone this, int startMonth, int startDay, int startDayOfWeek, int startTime, boolean after);

    public void setEndRule(@GuardSatisfied SimpleTimeZone this, int endMonth, int endDay, int endDayOfWeek, int endTime);

    public void setEndRule(@GuardSatisfied SimpleTimeZone this, int endMonth, int endDay, int endTime);

    public void setEndRule(@GuardSatisfied SimpleTimeZone this, int endMonth, int endDay, int endDayOfWeek, int endTime, boolean after);

    public int getOffset(@GuardSatisfied SimpleTimeZone this, long date);

    public int getOffset(@GuardSatisfied SimpleTimeZone this, int era, int year, int month, int day, int dayOfWeek, int millis);

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

    public static final int WALL_TIME;

    public static final int STANDARD_TIME;

    public static final int UTC_TIME;
}
