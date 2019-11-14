package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.text.DateFormat;
import java.time.LocalDate;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.lang.ref.SoftReference;
import java.time.Instant;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarDate;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;
import sun.util.calendar.Era;
import sun.util.calendar.Gregorian;
import sun.util.calendar.ZoneInfo;

@AnnotatedFor({ "lock", "nullness", "index" })
public class Date implements java.io.Serializable, Cloneable, Comparable<Date> {

    private static final BaseCalendar gcal = CalendarSystem.getGregorianCalendar();

    private static BaseCalendar jcal;

    private transient long fastTime;

    private transient BaseCalendar.Date cdate;

    private static int defaultCenturyStart;

    private static final long serialVersionUID = 7523967970034938905L;

    public Date() {
        this(System.currentTimeMillis());
    }

    public Date(long date) {
        fastTime = date;
    }

    @Deprecated
    public Date(int year, int month, int date) {
        this(year, month, date, 0, 0, 0);
    }

    @Deprecated
    public Date(int year, int month, int date, int hrs, int min) {
        this(year, month, date, hrs, min, 0);
    }

    @Deprecated
    public Date(int year, int month, int date, int hrs, int min, int sec) {
        int y = year + 1900;
        if (month >= 12) {
            y += month / 12;
            month %= 12;
        } else if (month < 0) {
            y += CalendarUtils.floorDivide(month, 12);
            month = CalendarUtils.mod(month, 12);
        }
        BaseCalendar cal = getCalendarSystem(y);
        cdate = (BaseCalendar.Date) cal.newCalendarDate(TimeZone.getDefaultRef());
        cdate.setNormalizedDate(y, month + 1, date).setTimeOfDay(hrs, min, sec, 0);
        getTimeImpl();
        cdate = null;
    }

    @Deprecated
    public Date(String s) {
        this(parse(s));
    }

    @SideEffectFree
    public Object clone(@GuardSatisfied Date this);

    @Deprecated
    public static long UTC(int year, int month, int date, int hrs, int min, int sec);

    @Deprecated
    public static long parse(String s);

    private static final String[] wtb = { "am", "pm", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday", "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december", "gmt", "ut", "utc", "est", "edt", "cst", "cdt", "mst", "mdt", "pst", "pdt" };

    private static final int[] ttb = { 14, 1, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 10000 + 0, 10000 + 0, 10000 + 0, 10000 + 5 * 60, 10000 + 4 * 60, 10000 + 6 * 60, 10000 + 5 * 60, 10000 + 7 * 60, 10000 + 6 * 60, 10000 + 8 * 60, 10000 + 7 * 60 };

    @Deprecated
    public int getYear(@GuardSatisfied Date this);

    @Deprecated
    public void setYear(@GuardSatisfied Date this, int year);

    @Deprecated
    public int getMonth(@GuardSatisfied Date this);

    @Deprecated
    public void setMonth(@GuardSatisfied Date this, int month);

    @Deprecated
    public int getDate(@GuardSatisfied Date this);

    @Deprecated
    public void setDate(@GuardSatisfied Date this, int date);

    @Deprecated
    public int getDay(@GuardSatisfied Date this);

    @Deprecated
    public int getHours(@GuardSatisfied Date this);

    @Deprecated
    public void setHours(@GuardSatisfied Date this, int hours);

    @Deprecated
    public int getMinutes(@GuardSatisfied Date this);

    @Deprecated
    public void setMinutes(@GuardSatisfied Date this, int minutes);

    @Deprecated
    public int getSeconds(@GuardSatisfied Date this);

    @Deprecated
    public void setSeconds(@GuardSatisfied Date this, int seconds);

    public long getTime(@GuardSatisfied Date this);

    private final long getTimeImpl();

    public void setTime(@GuardSatisfied Date this, long time);

    public boolean before(@GuardSatisfied Date this, Date when);

    public boolean after(@GuardSatisfied Date this, Date when);

    @Pure
    public boolean equals(@GuardSatisfied Date this, @GuardSatisfied @Nullable Object obj);

    static final long getMillisOf(Date date);

    @Pure
    public int compareTo(@GuardSatisfied Date this, @GuardSatisfied Date anotherDate);

    @Pure
    public int hashCode(@GuardSatisfied Date this);

    @SideEffectFree
    public String toString(@GuardSatisfied Date this);

    private static final StringBuilder convertToAbbr(StringBuilder sb, String name);

    @Deprecated
    public String toLocaleString();

    @Deprecated
    public String toGMTString();

    @Deprecated
    public int getTimezoneOffset(@GuardSatisfied Date this);

    private final BaseCalendar.Date getCalendarDate();

    private final BaseCalendar.Date normalize();

    private final BaseCalendar.Date normalize(BaseCalendar.Date date);

    private static final BaseCalendar getCalendarSystem(int year);

    private static final BaseCalendar getCalendarSystem(long utc);

    private static final BaseCalendar getCalendarSystem(BaseCalendar.Date cdate);

    private static final synchronized BaseCalendar getJulianCalendar();

    private void writeObject(ObjectOutputStream s) throws IOException;

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException;

    public static Date from(Instant instant);

    public Instant toInstant();
}
