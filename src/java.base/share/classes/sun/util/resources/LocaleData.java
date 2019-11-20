package sun.util.resources;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.spi.ResourceBundleProvider;
import sun.util.locale.provider.JRELocaleProviderAdapter;
import sun.util.locale.provider.LocaleProviderAdapter;
import static sun.util.locale.provider.LocaleProviderAdapter.Type.CLDR;
import static sun.util.locale.provider.LocaleProviderAdapter.Type.JRE;
import sun.util.locale.provider.ResourceBundleBasedAdapter;

@AnnotatedFor({ "index" })
public class LocaleData {

    public LocaleData(LocaleProviderAdapter.Type type) {
    }

    public ResourceBundle getCalendarData(Locale locale);

    public OpenListResourceBundle getCurrencyNames(Locale locale);

    public OpenListResourceBundle getLocaleNames(Locale locale);

    public TimeZoneNamesBundle getTimeZoneNames(Locale locale);

    public ResourceBundle getBreakIteratorInfo(Locale locale);

    public ResourceBundle getBreakIteratorResources(Locale locale);

    public ResourceBundle getCollationData(Locale locale);

    public ResourceBundle getDateFormatData(Locale locale);

    public void setSupplementary(ParallelListResourceBundle formatData);

    public ResourceBundle getNumberFormatData(Locale locale);

    public static ResourceBundle getBundle(final String baseName, final Locale locale);

    public static abstract class CommonResourceBundleProvider extends LocaleDataResourceBundleProvider {
    }

    public static abstract class SupplementaryResourceBundleProvider extends LocaleDataResourceBundleProvider {
    }
}
