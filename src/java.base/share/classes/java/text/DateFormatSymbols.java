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
    }

    public DateFormatSymbols(Locale locale) {
    }

    public static Locale[] getAvailableLocales();

    public static final DateFormatSymbols getInstance();

    public static final DateFormatSymbols getInstance(Locale locale);

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
}
