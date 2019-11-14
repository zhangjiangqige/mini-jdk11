package java.util;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.IntVal;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PermissionCollection;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.ProtectionDomain;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sun.util.BuddhistCalendar;
import sun.util.calendar.ZoneInfo;
import sun.util.locale.provider.CalendarDataUtility;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.TimeZoneNameUtility;
import sun.util.spi.CalendarProvider;

@AnnotatedFor({ "lock", "nullness", "index" })
public abstract class Calendar implements Serializable, Cloneable, Comparable<Calendar> {

    @IntVal({ 0 })
    public static final int ERA = 0;

    @IntVal({ 1 })
    public static final int YEAR = 1;

    @IntVal({ 2 })
    public static final int MONTH = 2;

    @IntVal({ 3 })
    public static final int WEEK_OF_YEAR = 3;

    @IntVal({ 4 })
    public static final int WEEK_OF_MONTH = 4;

    @IntVal({ 5 })
    public static final int DATE = 5;

    @IntVal({ 5 })
    public static final int DAY_OF_MONTH = 5;

    @IntVal({ 6 })
    public static final int DAY_OF_YEAR = 6;

    @IntVal({ 7 })
    public static final int DAY_OF_WEEK = 7;

    @IntVal({ 8 })
    public static final int DAY_OF_WEEK_IN_MONTH = 8;

    @IntVal({ 9 })
    public static final int AM_PM = 9;

    @IntVal({ 10 })
    public static final int HOUR = 10;

    @IntVal({ 11 })
    public static final int HOUR_OF_DAY = 11;

    @IntVal({ 12 })
    public static final int MINUTE = 12;

    @IntVal({ 13 })
    public static final int SECOND = 13;

    @IntVal({ 14 })
    public static final int MILLISECOND = 14;

    @IntVal({ 15 })
    public static final int ZONE_OFFSET = 15;

    @IntVal({ 16 })
    public static final int DST_OFFSET = 16;

    @IntVal({ 17 })
    public static final int FIELD_COUNT = 17;

    @IntVal({ 1 })
    public static final int SUNDAY = 1;

    @IntVal({ 2 })
    public static final int MONDAY = 2;

    @IntVal({ 3 })
    public static final int TUESDAY = 3;

    @IntVal({ 4 })
    public static final int WEDNESDAY = 4;

    @IntVal({ 5 })
    public static final int THURSDAY = 5;

    @IntVal({ 6 })
    public static final int FRIDAY = 6;

    @IntVal({ 7 })
    public static final int SATURDAY = 7;

    @IntVal({ 0 })
    public static final int JANUARY = 0;

    @IntVal({ 1 })
    public static final int FEBRUARY = 1;

    @IntVal({ 2 })
    public static final int MARCH = 2;

    @IntVal({ 3 })
    public static final int APRIL = 3;

    @IntVal({ 4 })
    public static final int MAY = 4;

    @IntVal({ 5 })
    public static final int JUNE = 5;

    @IntVal({ 6 })
    public static final int JULY = 6;

    @IntVal({ 7 })
    public static final int AUGUST = 7;

    @IntVal({ 8 })
    public static final int SEPTEMBER = 8;

    @IntVal({ 9 })
    public static final int OCTOBER = 9;

    @IntVal({ 10 })
    public static final int NOVEMBER = 10;

    @IntVal({ 11 })
    public static final int DECEMBER = 11;

    @IntVal({ 12 })
    public static final int UNDECIMBER = 12;

    @IntVal({ 0 })
    public static final int AM = 0;

    @IntVal({ 1 })
    public static final int PM = 1;

    @IntVal({ 0 })
    public static final int ALL_STYLES = 0;

    static final int STANDALONE_MASK = 0x8000;

    @IntVal({ 1 })
    public static final int SHORT = 1;

    @IntVal({ 2 })
    public static final int LONG = 2;

    public static final int NARROW_FORMAT = 4;

    public static final int NARROW_STANDALONE = NARROW_FORMAT | STANDALONE_MASK;

    public static final int SHORT_FORMAT = 1;

    public static final int LONG_FORMAT = 2;

    public static final int SHORT_STANDALONE = SHORT | STANDALONE_MASK;

    public static final int LONG_STANDALONE = LONG | STANDALONE_MASK;

    @SuppressWarnings("ProtectedField")
    protected int[] fields;

    @SuppressWarnings("ProtectedField")
    protected boolean[] isSet;

    private transient int[] stamp;

    @SuppressWarnings("ProtectedField")
    protected long time;

    @SuppressWarnings("ProtectedField")
    protected boolean isTimeSet;

    @SuppressWarnings("ProtectedField")
    protected boolean areFieldsSet;

    transient boolean areAllFieldsSet;

    private boolean lenient = true;

    private TimeZone zone;

    private transient boolean sharedZone = false;

    private int firstDayOfWeek;

    private int minimalDaysInFirstWeek;

    private static final ConcurrentMap<Locale, int[]> cachedLocaleData = new ConcurrentHashMap<>(3);

    private static final int UNSET = 0;

    private static final int COMPUTED = 1;

    private static final int MINIMUM_USER_STAMP = 2;

    static final int ALL_FIELDS = (1 << FIELD_COUNT) - 1;

    private int nextStamp = MINIMUM_USER_STAMP;

    static final int currentSerialVersion = 1;

    private int serialVersionOnStream = currentSerialVersion;

    static final long serialVersionUID = -1807547505821590642L;

    @SuppressWarnings("PointlessBitwiseExpression")
    static final int ERA_MASK = (1 << ERA);

    static final int YEAR_MASK = (1 << YEAR);

    static final int MONTH_MASK = (1 << MONTH);

    static final int WEEK_OF_YEAR_MASK = (1 << WEEK_OF_YEAR);

    static final int WEEK_OF_MONTH_MASK = (1 << WEEK_OF_MONTH);

    static final int DAY_OF_MONTH_MASK = (1 << DAY_OF_MONTH);

    static final int DATE_MASK = DAY_OF_MONTH_MASK;

    static final int DAY_OF_YEAR_MASK = (1 << DAY_OF_YEAR);

    static final int DAY_OF_WEEK_MASK = (1 << DAY_OF_WEEK);

    static final int DAY_OF_WEEK_IN_MONTH_MASK = (1 << DAY_OF_WEEK_IN_MONTH);

    static final int AM_PM_MASK = (1 << AM_PM);

    static final int HOUR_MASK = (1 << HOUR);

    static final int HOUR_OF_DAY_MASK = (1 << HOUR_OF_DAY);

    static final int MINUTE_MASK = (1 << MINUTE);

    static final int SECOND_MASK = (1 << SECOND);

    static final int MILLISECOND_MASK = (1 << MILLISECOND);

    static final int ZONE_OFFSET_MASK = (1 << ZONE_OFFSET);

    static final int DST_OFFSET_MASK = (1 << DST_OFFSET);

    public static class Builder {

        private static final int NFIELDS = FIELD_COUNT + 1;

        private static final int WEEK_YEAR = FIELD_COUNT;

        private long instant;

        private int[] fields;

        private int nextStamp;

        private int maxFieldIndex;

        private String type;

        private TimeZone zone;

        private boolean lenient = true;

        private Locale locale;

        private int firstDayOfWeek, minimalDaysInFirstWeek;

        public Builder() {
        }

        public Builder setInstant(long instant);

        public Builder setInstant(Date instant);

        public Builder set(int field, int value);

        public Builder setFields(int... fieldValuePairs);

        public Builder setDate(int year, int month, int dayOfMonth);

        public Builder setTimeOfDay(int hourOfDay, int minute, int second);

        public Builder setTimeOfDay(int hourOfDay, int minute, int second, int millis);

        public Builder setWeekDate(int weekYear, int weekOfYear, int dayOfWeek);

        public Builder setTimeZone(TimeZone zone);

        public Builder setLenient(boolean lenient);

        public Builder setCalendarType(String type);

        public Builder setLocale(Locale locale);

        public Builder setWeekDefinition(int firstDayOfWeek, int minimalDaysInFirstWeek);

        public Calendar build();

        private void allocateFields();

        private void internalSet(int field, int value);

        private boolean isInstantSet();

        private boolean isSet(int index);

        private boolean isValidWeekParameter(int value);
    }

    protected Calendar() {
        this(TimeZone.getDefaultRef(), Locale.getDefault(Locale.Category.FORMAT));
        sharedZone = true;
    }

    protected Calendar(TimeZone zone, Locale aLocale) {
        fields = new int[FIELD_COUNT];
        isSet = new boolean[FIELD_COUNT];
        stamp = new int[FIELD_COUNT];
        this.zone = zone;
        setWeekCountData(aLocale);
    }

    public static Calendar getInstance();

    public static Calendar getInstance(TimeZone zone);

    public static Calendar getInstance(Locale aLocale);

    public static Calendar getInstance(TimeZone zone, Locale aLocale);

    private static TimeZone defaultTimeZone(Locale l);

    private static Calendar createCalendar(TimeZone zone, Locale aLocale);

    public static synchronized Locale[] getAvailableLocales();

    protected abstract void computeTime();

    protected abstract void computeFields();

    public final Date getTime(@GuardSatisfied Calendar this);

    public final void setTime(@GuardSatisfied Calendar this, Date date);

    public long getTimeInMillis(@GuardSatisfied Calendar this);

    public void setTimeInMillis(@GuardSatisfied Calendar this, long millis);

    @Pure
    @NonNegative
    public int get(@GuardSatisfied Calendar this, @NonNegative int field);

    protected final int internalGet(int field);

    final void internalSet(int field, int value);

    public void set(@GuardSatisfied Calendar this, @NonNegative int field, int value);

    public final void set(@GuardSatisfied Calendar this, @NonNegative int year, @NonNegative int month, @NonNegative int date);

    public final void set(@GuardSatisfied Calendar this, @NonNegative int year, @NonNegative int month, @NonNegative int date, @NonNegative int hourOfDay, @NonNegative int minute);

    public final void set(@GuardSatisfied Calendar this, @NonNegative int year, @NonNegative int month, @NonNegative int date, @NonNegative int hourOfDay, @NonNegative int minute, @NonNegative int second);

    public final void clear(@GuardSatisfied Calendar this);

    public final void clear(@GuardSatisfied Calendar this, @NonNegative int field);

    @Pure
    public final boolean isSet(@GuardSatisfied Calendar this, @NonNegative int field);

    @Nullable
    public String getDisplayName(@GuardSatisfied Calendar this, @NonNegative int field, int style, Locale locale);

    @Nullable
    public Map<String, Integer> getDisplayNames(@GuardSatisfied Calendar this, @NonNegative int field, int style, Locale locale);

    private Map<String, Integer> getDisplayNamesImpl(int field, int style, Locale locale);

    boolean checkDisplayNameParams(int field, int style, int minStyle, int maxStyle, Locale locale, int fieldMask);

    private String[] getFieldStrings(int field, int style, DateFormatSymbols symbols);

    protected void complete();

    final boolean isExternallySet(int field);

    final int getSetStateFields();

    final void setFieldsComputed(int fieldMask);

    final void setFieldsNormalized(int fieldMask);

    final boolean isPartiallyNormalized();

    final boolean isFullyNormalized();

    final void setUnnormalized();

    static boolean isFieldSet(int fieldMask, int field);

    final int selectFields();

    int getBaseStyle(int style);

    private int toStandaloneStyle(int style);

    private boolean isStandaloneStyle(int style);

    private boolean isNarrowStyle(int style);

    private boolean isNarrowFormatStyle(int style);

    private static int aggregateStamp(int stamp_a, int stamp_b);

    public static Set<String> getAvailableCalendarTypes();

    private static class AvailableCalendarTypes {

        private static final Set<String> SET;

        static {
            Set<String> set = new HashSet<>(3);
            set.add("gregory");
            set.add("buddhist");
            set.add("japanese");
            SET = Collections.unmodifiableSet(set);
        }

        private AvailableCalendarTypes() {
        }
    }

    public String getCalendarType();

    @Pure
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(@GuardSatisfied Calendar this, @GuardSatisfied @Nullable Object obj);

    @Pure
    @Override
    public int hashCode(@GuardSatisfied Calendar this);

    public boolean before(Object when);

    public boolean after(Object when);

    @Pure
    @Override
    public int compareTo(@GuardSatisfied Calendar this, @GuardSatisfied Calendar anotherCalendar);

    public abstract void add(@GuardSatisfied Calendar this, int field, int amount);

    public abstract void roll(@GuardSatisfied Calendar this, int field, boolean up);

    public void roll(@GuardSatisfied Calendar this, @NonNegative int field, int amount);

    public void setTimeZone(@GuardSatisfied Calendar this, TimeZone value);

    public TimeZone getTimeZone(@GuardSatisfied Calendar this);

    TimeZone getZone();

    void setZoneShared(boolean shared);

    public void setLenient(@GuardSatisfied Calendar this, boolean lenient);

    @Pure
    public boolean isLenient(@GuardSatisfied Calendar this);

    public void setFirstDayOfWeek(@GuardSatisfied Calendar this, int value);

    public int getFirstDayOfWeek(@GuardSatisfied Calendar this);

    public void setMinimalDaysInFirstWeek(@GuardSatisfied Calendar this, int value);

    public int getMinimalDaysInFirstWeek(@GuardSatisfied Calendar this);

    public boolean isWeekDateSupported();

    public int getWeekYear();

    public void setWeekDate(int weekYear, int weekOfYear, int dayOfWeek);

    public int getWeeksInWeekYear();

    public abstract int getMinimum(@GuardSatisfied Calendar this, @NonNegative int field);

    public abstract int getMaximum(@GuardSatisfied Calendar this, @NonNegative int field);

    public abstract int getGreatestMinimum(@GuardSatisfied Calendar this, @NonNegative int field);

    public abstract int getLeastMaximum(@GuardSatisfied Calendar this, @NonNegative int field);

    @NonNegative
    public int getActualMinimum(@GuardSatisfied Calendar this, @NonNegative int field);

    @NonNegative
    public int getActualMaximum(@GuardSatisfied Calendar this, @NonNegative int field);

    @SideEffectFree
    @Override
    public Object clone(@GuardSatisfied Calendar this);

    private static final String[] FIELD_NAME = { "ERA", "YEAR", "MONTH", "WEEK_OF_YEAR", "WEEK_OF_MONTH", "DAY_OF_MONTH", "DAY_OF_YEAR", "DAY_OF_WEEK", "DAY_OF_WEEK_IN_MONTH", "AM_PM", "HOUR", "HOUR_OF_DAY", "MINUTE", "SECOND", "MILLISECOND", "ZONE_OFFSET", "DST_OFFSET" };

    static String getFieldName(int field);

    @SideEffectFree
    @Override
    public String toString(@GuardSatisfied Calendar this);

    private static void appendValue(StringBuilder sb, String item, boolean valid, long value);

    private void setWeekCountData(Locale desiredLocale);

    private void updateTime();

    private int compareTo(long t);

    private static long getMillisOf(Calendar calendar);

    private void adjustStamp();

    private void invalidateWeekFields();

    private synchronized void writeObject(ObjectOutputStream stream) throws IOException;

    private static class CalendarAccessControlContext {

        private static final AccessControlContext INSTANCE;

        static {
            RuntimePermission perm = new RuntimePermission("accessClassInPackage.sun.util.calendar");
            PermissionCollection perms = perm.newPermissionCollection();
            perms.add(perm);
            INSTANCE = new AccessControlContext(new ProtectionDomain[] { new ProtectionDomain(null, perms) });
        }

        private CalendarAccessControlContext() {
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException;

    public final Instant toInstant();
}
