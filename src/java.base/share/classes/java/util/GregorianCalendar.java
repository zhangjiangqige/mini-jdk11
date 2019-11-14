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
    public static final int BC = 0;

    static final int BCE = 0;

    @IntVal({ 1 })
    public static final int AD = 1;

    static final int CE = 1;

    private static final int EPOCH_OFFSET = 719163;

    private static final int EPOCH_YEAR = 1970;

    static final int[] MONTH_LENGTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    static final int[] LEAP_MONTH_LENGTH = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private static final int ONE_SECOND = 1000;

    private static final int ONE_MINUTE = 60 * ONE_SECOND;

    private static final int ONE_HOUR = 60 * ONE_MINUTE;

    private static final long ONE_DAY = 24 * ONE_HOUR;

    private static final long ONE_WEEK = 7 * ONE_DAY;

    static final int[] MIN_VALUES = { BCE, 1, JANUARY, 1, 0, 1, 1, SUNDAY, 1, AM, 0, 0, 0, 0, 0, -13 * ONE_HOUR, 0 };

    static final int[] LEAST_MAX_VALUES = { CE, 292269054, DECEMBER, 52, 4, 28, 365, SATURDAY, 4, PM, 11, 23, 59, 59, 999, 14 * ONE_HOUR, 20 * ONE_MINUTE };

    static final int[] MAX_VALUES = { CE, 292278994, DECEMBER, 53, 6, 31, 366, SATURDAY, 6, PM, 11, 23, 59, 59, 999, 14 * ONE_HOUR, 2 * ONE_HOUR };

    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    static final long serialVersionUID = -8125100834729963327L;

    private static final Gregorian gcal = CalendarSystem.getGregorianCalendar();

    private static JulianCalendar jcal;

    private static Era[] jeras;

    static final long DEFAULT_GREGORIAN_CUTOVER = -12219292800000L;

    private long gregorianCutover = DEFAULT_GREGORIAN_CUTOVER;

    private transient long gregorianCutoverDate = (((DEFAULT_GREGORIAN_CUTOVER + 1) / ONE_DAY) - 1) + EPOCH_OFFSET;

    private transient int gregorianCutoverYear = 1582;

    private transient int gregorianCutoverYearJulian = 1582;

    private transient BaseCalendar.Date gdate;

    private transient BaseCalendar.Date cdate;

    private transient BaseCalendar calsys;

    private transient int[] zoneOffsets;

    private transient int[] originalFields;

    public GregorianCalendar() {
        this(TimeZone.getDefaultRef(), Locale.getDefault(Locale.Category.FORMAT));
        setZoneShared(true);
    }

    public GregorianCalendar(TimeZone zone) {
        this(zone, Locale.getDefault(Locale.Category.FORMAT));
    }

    public GregorianCalendar(Locale aLocale) {
        this(TimeZone.getDefaultRef(), aLocale);
        setZoneShared(true);
    }

    public GregorianCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
        gdate = (BaseCalendar.Date) gcal.newCalendarDate(zone);
        setTimeInMillis(System.currentTimeMillis());
    }

    public GregorianCalendar(int year, int month, int dayOfMonth) {
        this(year, month, dayOfMonth, 0, 0, 0, 0);
    }

    public GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        this(year, month, dayOfMonth, hourOfDay, minute, 0, 0);
    }

    public GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
        this(year, month, dayOfMonth, hourOfDay, minute, second, 0);
    }

    GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second, int millis) {
        super();
        gdate = (BaseCalendar.Date) gcal.newCalendarDate(getZone());
        this.set(YEAR, year);
        this.set(MONTH, month);
        this.set(DAY_OF_MONTH, dayOfMonth);
        if (hourOfDay >= 12 && hourOfDay <= 23) {
            this.internalSet(AM_PM, PM);
            this.internalSet(HOUR, hourOfDay - 12);
        } else {
            this.internalSet(HOUR, hourOfDay);
        }
        setFieldsComputed(HOUR_MASK | AM_PM_MASK);
        this.set(HOUR_OF_DAY, hourOfDay);
        this.set(MINUTE, minute);
        this.set(SECOND, second);
        this.internalSet(MILLISECOND, millis);
    }

    GregorianCalendar(TimeZone zone, Locale locale, boolean flag) {
        super(zone, locale);
        gdate = (BaseCalendar.Date) gcal.newCalendarDate(getZone());
    }

    public void setGregorianChange(@GuardSatisfied GregorianCalendar this, Date date);

    private void setGregorianChange(long cutoverTime);

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

    private long getYearOffsetInMillis();

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

    private transient long cachedFixedDate = Long.MIN_VALUE;

    @Override
    protected void computeFields();

    private int computeFields(int fieldMask, int tzMask);

    private int getWeekNumber(long fixedDay1, long fixedDate);

    @Override
    protected void computeTime();

    private long getFixedDate(BaseCalendar cal, int year, int fieldMask);

    private GregorianCalendar getNormalizedCalendar();

    private static synchronized BaseCalendar getJulianCalendarSystem();

    private BaseCalendar getCutoverCalendarSystem();

    private boolean isCutoverYear(int normalizedYear);

    private long getFixedDateJan1(BaseCalendar.Date date, long fixedDate);

    private long getFixedDateMonth1(BaseCalendar.Date date, long fixedDate);

    private BaseCalendar.Date getCalendarDate(long fd);

    private BaseCalendar.Date getGregorianCutoverDate();

    private BaseCalendar.Date getLastJulianDate();

    private int monthLength(int month, int year);

    private int monthLength(int month);

    private int actualMonthLength();

    private int yearLength(int year);

    private int yearLength();

    private void pinDayOfMonth();

    private long getCurrentFixedDate();

    private static int getRolledValue(int value, int amount, int min, int max);

    private int internalGetEra();

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException;

    public ZonedDateTime toZonedDateTime();

    public static GregorianCalendar from(ZonedDateTime zdt);
}
