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

    private static final ResourceBundle.Control defaultControl = ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_DEFAULT);

    private static final String DOTCLDR = ".cldr";

    private static final Map<String, List<Locale>> CANDIDATES_MAP = new ConcurrentHashMap<>();

    private final LocaleProviderAdapter.Type type;

    public LocaleData(LocaleProviderAdapter.Type type) {
        this.type = type;
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

    private boolean setSupplementary(String suppName, ParallelListResourceBundle formatData);

    public ResourceBundle getNumberFormatData(Locale locale);

    public static ResourceBundle getBundle(final String baseName, final Locale locale);

    private static OpenListResourceBundle getSupplementary(final String baseName, final Locale locale);

    private static abstract class LocaleDataResourceBundleProvider implements ResourceBundleProvider {

        protected String toBundleName(String baseName, Locale locale);
    }

    public static abstract class CommonResourceBundleProvider extends LocaleDataResourceBundleProvider {
    }

    public static abstract class SupplementaryResourceBundleProvider extends LocaleDataResourceBundleProvider {
    }

    private static class LocaleDataStrategy implements Bundles.Strategy {

        private static final LocaleDataStrategy INSTANCE = new LocaleDataStrategy();

        private static Set<Locale> JAVA_BASE_LOCALES = Set.of(Locale.ROOT, Locale.ENGLISH, Locale.US, new Locale("en", "US", "POSIX"));

        private LocaleDataStrategy() {
        }

        @Override
        public List<Locale> getCandidateLocales(String baseName, Locale locale);

        boolean inJavaBaseModule(String baseName, Locale locale);

        @Override
        public String toBundleName(String baseName, Locale locale);

        @Override
        public Class<? extends ResourceBundleProvider> getResourceBundleProviderType(String baseName, Locale locale);
    }

    private static class SupplementaryStrategy extends LocaleDataStrategy {

        private static final SupplementaryStrategy INSTANCE = new SupplementaryStrategy();

        private static Set<Locale> JAVA_BASE_LOCALES = Set.of(Locale.ROOT, Locale.ENGLISH, Locale.US);

        private SupplementaryStrategy() {
        }

        @Override
        public List<Locale> getCandidateLocales(String baseName, Locale locale);

        @Override
        public Class<? extends ResourceBundleProvider> getResourceBundleProviderType(String baseName, Locale locale);

        @Override
        boolean inJavaBaseModule(String baseName, Locale locale);
    }
}
