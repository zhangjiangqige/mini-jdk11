package java.util;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.spi.LocaleNameProvider;
import java.util.stream.Collectors;
import sun.security.action.GetPropertyAction;
import sun.util.locale.BaseLocale;
import sun.util.locale.InternalLocaleBuilder;
import sun.util.locale.LanguageTag;
import sun.util.locale.LocaleExtensions;
import sun.util.locale.LocaleMatcher;
import sun.util.locale.LocaleObjectCache;
import sun.util.locale.LocaleSyntaxException;
import sun.util.locale.LocaleUtils;
import sun.util.locale.ParseStatus;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.LocaleResources;
import sun.util.locale.provider.LocaleServiceProviderPool;
import sun.util.locale.provider.TimeZoneNameUtility;

@AnnotatedFor({ "index", "interning", "lock", "nullness" })
public final class Locale implements Cloneable, Serializable {

    private static final Cache LOCALECACHE = new Cache();

    public static final Locale ENGLISH = createConstant("en", "");

    public static final Locale FRENCH = createConstant("fr", "");

    public static final Locale GERMAN = createConstant("de", "");

    public static final Locale ITALIAN = createConstant("it", "");

    public static final Locale JAPANESE = createConstant("ja", "");

    public static final Locale KOREAN = createConstant("ko", "");

    public static final Locale CHINESE = createConstant("zh", "");

    public static final Locale SIMPLIFIED_CHINESE = createConstant("zh", "CN");

    public static final Locale TRADITIONAL_CHINESE = createConstant("zh", "TW");

    public static final Locale FRANCE = createConstant("fr", "FR");

    public static final Locale GERMANY = createConstant("de", "DE");

    public static final Locale ITALY = createConstant("it", "IT");

    public static final Locale JAPAN = createConstant("ja", "JP");

    public static final Locale KOREA = createConstant("ko", "KR");

    public static final Locale CHINA = SIMPLIFIED_CHINESE;

    public static final Locale PRC = SIMPLIFIED_CHINESE;

    public static final Locale TAIWAN = TRADITIONAL_CHINESE;

    public static final Locale UK = createConstant("en", "GB");

    public static final Locale US = createConstant("en", "US");

    public static final Locale CANADA = createConstant("en", "CA");

    public static final Locale CANADA_FRENCH = createConstant("fr", "CA");

    public static final Locale ROOT = createConstant("", "");

    public static final char PRIVATE_USE_EXTENSION = 'x';

    public static final char UNICODE_LOCALE_EXTENSION = 'u';

    static final long serialVersionUID = 9149081749638150636L;

    public static enum IsoCountryCode {

        PART1_ALPHA2 {

            @Override
            Set<String> createCountryCodeSet() {
                return Set.of(Locale.getISOCountries());
            }
        }
        , PART1_ALPHA3 {

            @Override
            Set<String> createCountryCodeSet() {
                return LocaleISOData.computeISO3166_1Alpha3Countries();
            }
        }
        , PART3 {

            @Override
            Set<String> createCountryCodeSet() {
                return Set.of(LocaleISOData.ISO3166_3);
            }
        }
        ;

        abstract Set<String> createCountryCodeSet();

        private static Map<IsoCountryCode, Set<String>> iso3166CodesMap = new ConcurrentHashMap<>();

        static Set<String> retrieveISOCountryCodes(IsoCountryCode type) {
            return iso3166CodesMap.computeIfAbsent(type, IsoCountryCode::createCountryCodeSet);
        }
    }

    private static final int DISPLAY_LANGUAGE = 0;

    private static final int DISPLAY_COUNTRY = 1;

    private static final int DISPLAY_VARIANT = 2;

    private static final int DISPLAY_SCRIPT = 3;

    private static final int DISPLAY_UEXT_KEY = 4;

    private static final int DISPLAY_UEXT_TYPE = 5;

    private Locale(BaseLocale baseLocale, @Nullable LocaleExtensions extensions) {
        this.baseLocale = baseLocale;
        this.localeExtensions = extensions;
    }

    public Locale(String language, String country, String variant) {
        if (language == null || country == null || variant == null) {
            throw new NullPointerException();
        }
        baseLocale = BaseLocale.getInstance(convertOldISOCodes(language), "", country, variant);
        localeExtensions = getCompatibilityExtensions(language, "", country, variant);
    }

    public Locale(String language, String country) {
        this(language, country, "");
    }

    public Locale(String language) {
        this(language, "", "");
    }

    private static Locale createConstant(String lang, String country);

    static Locale getInstance(String language, String country, String variant);

    static Locale getInstance(String language, String script, String country, String variant, @Nullable LocaleExtensions extensions);

    static Locale getInstance(BaseLocale baseloc, @Nullable LocaleExtensions extensions);

    private static class Cache extends LocaleObjectCache<Object, Locale> {

        private Cache() {
        }

        @Override
        protected Locale createObject(Object key);
    }

    private static final class LocaleKey {

        private final BaseLocale base;

        @Nullable
        private final LocaleExtensions exts;

        private final int hash;

        private LocaleKey(BaseLocale baseLocale, @Nullable LocaleExtensions extensions) {
            base = baseLocale;
            exts = extensions;
            int h = base.hashCode();
            if (exts != null) {
                h ^= exts.hashCode();
            }
            hash = h;
        }

        @Override
        public boolean equals(Object obj);

        @Override
        public int hashCode();
    }

    public static Locale getDefault();

    public static Locale getDefault(Locale.Category category);

    private static Locale initDefault();

    private static Locale initDefault(Locale.Category category);

    private static Optional<LocaleExtensions> getDefaultExtensions(String extensionsProp);

    public static synchronized void setDefault(Locale newLocale);

    public static synchronized void setDefault(Locale.Category category, Locale newLocale);

    public static Locale[] getAvailableLocales();

    public static String[] getISOCountries();

    public static Set<String> getISOCountries(IsoCountryCode type);

    public static String[] getISOLanguages();

    private static String[] getISO2Table(String table);

    public String getLanguage();

    public String getScript();

    @Interned
    public String getCountry();

    @Interned
    public String getVariant();

    public boolean hasExtensions();

    public Locale stripExtensions();

    public String getExtension(char key);

    public Set<Character> getExtensionKeys();

    public Set<String> getUnicodeLocaleAttributes();

    public String getUnicodeLocaleType(String key);

    public Set<String> getUnicodeLocaleKeys();

    BaseLocale getBaseLocale();

    @Nullable
    LocaleExtensions getLocaleExtensions();

    @SideEffectFree
    @Override
    public final String toString();

    public String toLanguageTag();

    public static Locale forLanguageTag(String languageTag);

    public String getISO3Language() throws MissingResourceException;

    public String getISO3Country() throws MissingResourceException;

    private static String getISO3Code(String iso2Code, String table);

    public final String getDisplayLanguage();

    public String getDisplayLanguage(Locale inLocale);

    public String getDisplayScript();

    public String getDisplayScript(Locale inLocale);

    public final String getDisplayCountry();

    public String getDisplayCountry(Locale inLocale);

    private String getDisplayString(String code, String cat, Locale inLocale, int type);

    public final String getDisplayVariant();

    public String getDisplayVariant(Locale inLocale);

    public final String getDisplayName();

    public String getDisplayName(Locale inLocale);

    @SideEffectFree
    @Override
    public Object clone(@GuardSatisfied Locale this);

    @Pure
    @Override
    public int hashCode();

    @Pure
    @Override
    public boolean equals(@Nullable Object obj);

    private transient BaseLocale baseLocale;

    private transient LocaleExtensions localeExtensions;

    private transient volatile int hashCodeValue;

    private static volatile Locale defaultLocale = initDefault();

    @MonotonicNonNull
    private static volatile Locale defaultDisplayLocale;

    @MonotonicNonNull
    private static volatile Locale defaultFormatLocale;

    private transient volatile String languageTag;

    private String[] getDisplayVariantArray(Locale inLocale);

    private String getDisplayKeyTypeExtensionString(String key, LocaleResources lr, Locale inLocale);

    private static String formatList(String[] stringList, String pattern);

    private static boolean isUnicodeExtensionKey(String s);

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("language", String.class), new ObjectStreamField("country", String.class), new ObjectStreamField("variant", String.class), new ObjectStreamField("hashcode", int.class), new ObjectStreamField("script", String.class), new ObjectStreamField("extensions", String.class) };

    private void writeObject(ObjectOutputStream out) throws IOException;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;

    private Object readResolve() throws java.io.ObjectStreamException;

    private static volatile String @MonotonicNonNull [] isoLanguages;

    private static volatile String @MonotonicNonNull [] isoCountries;

    private static String convertOldISOCodes(String language);

    private static LocaleExtensions getCompatibilityExtensions(String language, String script, String country, String variant);

    private static class LocaleNameGetter implements LocaleServiceProviderPool.LocalizedObjectGetter<LocaleNameProvider, String> {

        private static final LocaleNameGetter INSTANCE = new LocaleNameGetter();

        @Override
        public String getObject(LocaleNameProvider localeNameProvider, Locale locale, String key, Object... params);
    }

    public enum Category {

        DISPLAY("user.language.display", "user.script.display", "user.country.display", "user.variant.display", "user.extensions.display"), FORMAT("user.language.format", "user.script.format", "user.country.format", "user.variant.format", "user.extensions.format");

        Category(String languageKey, String scriptKey, String countryKey, String variantKey, String extensionsKey) {
            this.languageKey = languageKey;
            this.scriptKey = scriptKey;
            this.countryKey = countryKey;
            this.variantKey = variantKey;
            this.extensionsKey = extensionsKey;
        }

        final String languageKey;

        final String scriptKey;

        final String countryKey;

        final String variantKey;

        final String extensionsKey;
    }

    public static final class Builder {

        private final InternalLocaleBuilder localeBuilder;

        public Builder() {
            localeBuilder = new InternalLocaleBuilder();
        }

        public Builder setLocale(Locale.@GuardSatisfied Builder this, Locale locale);

        public Builder setLanguageTag(Locale.@GuardSatisfied Builder this, @Nullable String languageTag);

        public Builder setLanguage(Locale.@GuardSatisfied Builder this, @Nullable String language);

        public Builder setScript(Locale.@GuardSatisfied Builder this, @Nullable String script);

        public Builder setRegion(Locale.@GuardSatisfied Builder this, @Nullable String region);

        public Builder setVariant(Locale.@GuardSatisfied Builder this, @Nullable String variant);

        public Builder setExtension(Locale.@GuardSatisfied Builder this, char key, @Nullable String value);

        public Builder setUnicodeLocaleKeyword(Locale.@GuardSatisfied Builder this, String key, @Nullable String type);

        public Builder addUnicodeLocaleAttribute(Locale.@GuardSatisfied Builder this, String attribute);

        public Builder removeUnicodeLocaleAttribute(Locale.@GuardSatisfied Builder this, String attribute);

        public Builder clear(Locale.@GuardSatisfied Builder this);

        public Builder clearExtensions(Locale.@GuardSatisfied Builder this);

        public Locale build();
    }

    public static enum FilteringMode {

        AUTOSELECT_FILTERING, EXTENDED_FILTERING, IGNORE_EXTENDED_RANGES, MAP_EXTENDED_RANGES, REJECT_EXTENDED_RANGES
    }

    public static final class LanguageRange {

        public static final double MAX_WEIGHT = 1.0;

        public static final double MIN_WEIGHT = 0.0;

        private final String range;

        private final double weight;

        private volatile int hash;

        public LanguageRange(String range) {
            this(range, MAX_WEIGHT);
        }

        public LanguageRange(String range, double weight) {
            if (range == null) {
                throw new NullPointerException();
            }
            if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
                throw new IllegalArgumentException("weight=" + weight);
            }
            range = range.toLowerCase(Locale.ROOT);
            boolean isIllFormed = false;
            String[] subtags = range.split("-");
            if (isSubtagIllFormed(subtags[0], true) || range.endsWith("-")) {
                isIllFormed = true;
            } else {
                for (int i = 1; i < subtags.length; i++) {
                    if (isSubtagIllFormed(subtags[i], false)) {
                        isIllFormed = true;
                        break;
                    }
                }
            }
            if (isIllFormed) {
                throw new IllegalArgumentException("range=" + range);
            }
            this.range = range;
            this.weight = weight;
        }

        private static boolean isSubtagIllFormed(String subtag, boolean isFirstSubtag);

        public String getRange();

        public double getWeight();

        public static List<LanguageRange> parse(String ranges);

        public static List<LanguageRange> parse(String ranges, Map<String, List<String>> map);

        public static List<LanguageRange> mapEquivalents(List<LanguageRange> priorityList, Map<String, List<String>> map);

        @Override
        public int hashCode();

        @Pure
        @Override
        public boolean equals(@Nullable Object obj);

        @Override
        public String toString();
    }

    public static List<Locale> filter(List<LanguageRange> priorityList, Collection<Locale> locales, FilteringMode mode);

    public static List<Locale> filter(List<LanguageRange> priorityList, Collection<Locale> locales);

    public static List<String> filterTags(List<LanguageRange> priorityList, Collection<String> tags, FilteringMode mode);

    public static List<String> filterTags(List<LanguageRange> priorityList, Collection<String> tags);

    @Nullable
    public static Locale lookup(List<LanguageRange> priorityList, Collection<Locale> locales);

    @Nullable
    public static String lookupTag(List<LanguageRange> priorityList, Collection<String> tags);
}
