package java.text;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.text.spi.DateFormatSymbolsProvider;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sun.util.locale.provider.CalendarDataUtility;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.LocaleServiceProviderPool;
import sun.util.locale.provider.ResourceBundleBasedAdapter;
import sun.util.locale.provider.TimeZoneNameUtility;

@AnnotatedFor({ "index" })
public class DateFormatSymbols implements Serializable, Cloneable {

    public DateFormatSymbols() {
        initializeData(Locale.getDefault(Locale.Category.FORMAT));
    }

    public DateFormatSymbols(Locale locale) {
        initializeData(locale);
    }

    private DateFormatSymbols(boolean flag) {
    }

    String[] eras = null;

    String[] months = null;

    String[] shortMonths = null;

    String[] weekdays = null;

    String[] shortWeekdays = null;

    String[] ampms = null;

    String[][] zoneStrings = null;

    transient boolean isZoneStringsSet = false;

    static final String patternChars = "GyMdkHmsSEDFwWahKzZYuXL";

    static final int PATTERN_ERA = 0;

    static final int PATTERN_YEAR = 1;

    static final int PATTERN_MONTH = 2;

    static final int PATTERN_DAY_OF_MONTH = 3;

    static final int PATTERN_HOUR_OF_DAY1 = 4;

    static final int PATTERN_HOUR_OF_DAY0 = 5;

    static final int PATTERN_MINUTE = 6;

    static final int PATTERN_SECOND = 7;

    static final int PATTERN_MILLISECOND = 8;

    static final int PATTERN_DAY_OF_WEEK = 9;

    static final int PATTERN_DAY_OF_YEAR = 10;

    static final int PATTERN_DAY_OF_WEEK_IN_MONTH = 11;

    static final int PATTERN_WEEK_OF_YEAR = 12;

    static final int PATTERN_WEEK_OF_MONTH = 13;

    static final int PATTERN_AM_PM = 14;

    static final int PATTERN_HOUR1 = 15;

    static final int PATTERN_HOUR0 = 16;

    static final int PATTERN_ZONE_NAME = 17;

    static final int PATTERN_ZONE_VALUE = 18;

    static final int PATTERN_WEEK_YEAR = 19;

    static final int PATTERN_ISO_DAY_OF_WEEK = 20;

    static final int PATTERN_ISO_ZONE = 21;

    static final int PATTERN_MONTH_STANDALONE = 22;

    String localPatternChars = null;

    Locale locale = null;

    static final long serialVersionUID = -5987973545549424702L;

    public static Locale[] getAvailableLocales();

    public static final DateFormatSymbols getInstance();

    public static final DateFormatSymbols getInstance(Locale locale);

    static final DateFormatSymbols getInstanceRef(Locale locale);

    private static DateFormatSymbols getProviderInstance(Locale locale);

    public String[] getEras();

    public void setEras(String[] newEras);

    public String[] getMonths();

    public void setMonths(String[] newMonths);

    public String[] getShortMonths();

    public void setShortMonths(String[] newShortMonths);

    public String[] getWeekdays();

    public void setWeekdays(String[] newWeekdays);

    public String[] getShortWeekdays();

    public void setShortWeekdays(String[] newShortWeekdays);

    public String[] getAmPmStrings();

    public void setAmPmStrings(String[] newAmpms);

    public String[][] getZoneStrings();

    public void setZoneStrings(String[][] newZoneStrings);

    public String getLocalPatternChars();

    public void setLocalPatternChars(String newLocalPatternChars);

    public Object clone();

    @Override
    public int hashCode();

    public boolean equals(Object obj);

    static final int millisPerHour = 60 * 60 * 1000;

    private static final ConcurrentMap<Locale, SoftReference<DateFormatSymbols>> cachedInstances = new ConcurrentHashMap<>(3);

    private transient int lastZoneIndex;

    transient volatile int cachedHashCode;

    private void initializeData(Locale locale);

    private static String[] toOneBasedArray(String[] src);

    final int getZoneIndex(String ID);

    final String[][] getZoneStringsWrapper();

    private String[][] getZoneStringsImpl(boolean needsCopy);

    private boolean isSubclassObject();

    private void copyMembers(DateFormatSymbols src, DateFormatSymbols dst);

    private void writeObject(ObjectOutputStream stream) throws IOException;
}
