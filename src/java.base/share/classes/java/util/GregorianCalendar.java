package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.IntVal;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarDate;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;
import sun.util.calendar.Era;
import sun.util.calendar.Gregorian;
import sun.util.calendar.JulianCalendar;
import sun.util.calendar.ZoneInfo;

@AnnotatedFor({ "lock", "nullness", "index" })
public class GregorianCalendar extends Calendar {

    @IntVal({ 0 })
    public static final int BC;

    @IntVal({ 1 })
    public static final int AD;

    public GregorianCalendar() {
    }

    public GregorianCalendar(TimeZone zone) {
    }

    public GregorianCalendar(Locale aLocale) {
    }

    public GregorianCalendar(TimeZone zone, Locale aLocale) {
    }

    public GregorianCalendar(int year, int month, int dayOfMonth) {
    }

    public GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
    }

    public GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
    }

    public void setGregorianChange(@GuardSatisfied GregorianCalendar this, Date date);

    public final Date getGregorianChange();

    @Pure
    public boolean isLeapYear(@GuardSatisfied GregorianCalendar this, int year);

    @Override
    public String getCalendarType();

    @Pure
    @Override
    public boolean equals(@GuardSatisfied GregorianCalendar this, @GuardSatisfied @Nullable Object obj);

    @Pure
    @Override
    public int hashCode(@GuardSatisfied GregorianCalendar this);

    @Override
    public void add(@GuardSatisfied GregorianCalendar this, int field, int amount);

    @Override
    public void roll(@GuardSatisfied GregorianCalendar this, int field, boolean up);

    @Override
    public void roll(@GuardSatisfied GregorianCalendar this, int field, int amount);

    @Override
    public int getMinimum(int field);

    @Override
    public int getMaximum(int field);

    @Override
    public int getGreatestMinimum(int field);

    @Override
    public int getLeastMaximum(int field);

    @Override
    public int getActualMinimum(int field);

    @Override
    public int getActualMaximum(int field);

    @SideEffectFree
    @Override
    public Object clone(@GuardSatisfied GregorianCalendar this);

    @Override
    public TimeZone getTimeZone();

    @Override
    public void setTimeZone(@GuardSatisfied GregorianCalendar this, TimeZone zone);

    @Override
    public final boolean isWeekDateSupported();

    @Override
    public int getWeekYear();

    @Override
    public void setWeekDate(int weekYear, int weekOfYear, int dayOfWeek);

    @Override
    public int getWeeksInWeekYear();

    @Override
    protected void computeFields();

    @Override
    protected void computeTime();

    public ZonedDateTime toZonedDateTime();

    public static GregorianCalendar from(ZonedDateTime zdt);
}
