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

    public Date() {
    }

    public Date(long date) {
    }

    @Deprecated
    public Date(int year, int month, int date) {
    }

    @Deprecated
    public Date(int year, int month, int date, int hrs, int min) {
    }

    @Deprecated
    public Date(int year, int month, int date, int hrs, int min, int sec) {
    }

    @Deprecated
    public Date(String s) {
    }

    @SideEffectFree
    public Object clone(@GuardSatisfied Date this);

    @Deprecated
    public static long UTC(int year, int month, int date, int hrs, int min, int sec);

    @Deprecated
    public static long parse(String s);

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

    public void setTime(@GuardSatisfied Date this, long time);

    public boolean before(@GuardSatisfied Date this, Date when);

    public boolean after(@GuardSatisfied Date this, Date when);

    @Pure
    public boolean equals(@GuardSatisfied Date this, @GuardSatisfied @Nullable Object obj);

    @Pure
    public int compareTo(@GuardSatisfied Date this, @GuardSatisfied Date anotherDate);

    @Pure
    public int hashCode(@GuardSatisfied Date this);

    @SideEffectFree
    public String toString(@GuardSatisfied Date this);

    @Deprecated
    public String toLocaleString();

    @Deprecated
    public String toGMTString();

    @Deprecated
    public int getTimezoneOffset(@GuardSatisfied Date this);

    public static Date from(Instant instant);

    public Instant toInstant();
}
