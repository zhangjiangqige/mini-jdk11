package java.util;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.spi.CurrencyNameProvider;
import java.util.stream.Collectors;
import jdk.internal.util.StaticProperty;
import sun.util.locale.provider.CalendarDataUtility;
import sun.util.locale.provider.LocaleServiceProviderPool;
import sun.util.logging.PlatformLogger;

@AnnotatedFor({ "interning", "lock", "nullness" })
@UsesObjectEquals
public final class Currency implements Serializable {

    private static final long serialVersionUID = -158308464356906721L;

    private final String currencyCode;

    private final transient int defaultFractionDigits;

    private final transient int numericCode;

    private static ConcurrentMap<String, Currency> instances = new ConcurrentHashMap<>(7);

    private static HashSet<Currency> available;

    static int formatVersion;

    static int dataVersion;

    static int[] mainTable;

    static List<SpecialCaseEntry> specialCasesList;

    static List<OtherCurrencyEntry> otherCurrenciesList;

    private static final int MAGIC_NUMBER = 0x43757244;

    private static final int A_TO_Z = ('Z' - 'A') + 1;

    private static final int INVALID_COUNTRY_ENTRY = 0x0000007F;

    private static final int COUNTRY_WITHOUT_CURRENCY_ENTRY = 0x00000200;

    private static final int SIMPLE_CASE_COUNTRY_MASK = 0x00000000;

    private static final int SIMPLE_CASE_COUNTRY_FINAL_CHAR_MASK = 0x0000001F;

    private static final int SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_MASK = 0x000001E0;

    private static final int SIMPLE_CASE_COUNTRY_DEFAULT_DIGITS_SHIFT = 5;

    private static final int SIMPLE_CASE_COUNTRY_MAX_DEFAULT_DIGITS = 9;

    private static final int SPECIAL_CASE_COUNTRY_MASK = 0x00000200;

    private static final int SPECIAL_CASE_COUNTRY_INDEX_MASK = 0x0000001F;

    private static final int SPECIAL_CASE_COUNTRY_INDEX_DELTA = 1;

    private static final int COUNTRY_TYPE_MASK = SIMPLE_CASE_COUNTRY_MASK | SPECIAL_CASE_COUNTRY_MASK;

    private static final int NUMERIC_CODE_MASK = 0x000FFC00;

    private static final int NUMERIC_CODE_SHIFT = 10;

    private static final int VALID_FORMAT_VERSION = 3;

    static {
        AccessController.doPrivileged(new PrivilegedAction<>() {

            @Override
            public Void run() {
                try {
                    try (InputStream in = getClass().getResourceAsStream("/java/util/currency.data")) {
                        if (in == null) {
                            throw new InternalError("Currency data not found");
                        }
                        DataInputStream dis = new DataInputStream(new BufferedInputStream(in));
                        if (dis.readInt() != MAGIC_NUMBER) {
                            throw new InternalError("Currency data is possibly corrupted");
                        }
                        formatVersion = dis.readInt();
                        if (formatVersion != VALID_FORMAT_VERSION) {
                            throw new InternalError("Currency data format is incorrect");
                        }
                        dataVersion = dis.readInt();
                        mainTable = readIntArray(dis, A_TO_Z * A_TO_Z);
                        int scCount = dis.readInt();
                        specialCasesList = readSpecialCases(dis, scCount);
                        int ocCount = dis.readInt();
                        otherCurrenciesList = readOtherCurrencies(dis, ocCount);
                    }
                } catch (IOException e) {
                    throw new InternalError(e);
                }
                String propsFile = System.getProperty("java.util.currency.data");
                if (propsFile == null) {
                    propsFile = StaticProperty.javaHome() + File.separator + "lib" + File.separator + "currency.properties";
                }
                try {
                    File propFile = new File(propsFile);
                    if (propFile.exists()) {
                        Properties props = new Properties();
                        try (FileReader fr = new FileReader(propFile)) {
                            props.load(fr);
                        }
                        Pattern propertiesPattern = Pattern.compile("([A-Z]{3})\\s*,\\s*(\\d{3})\\s*,\\s*" + "(\\d+)\\s*,?\\s*(\\d{4}-\\d{2}-\\d{2}T\\d{2}:" + "\\d{2}:\\d{2})?");
                        List<CurrencyProperty> currencyEntries = getValidCurrencyData(props, propertiesPattern);
                        currencyEntries.forEach(Currency::replaceCurrencyData);
                    }
                } catch (IOException e) {
                    CurrencyProperty.info("currency.properties is ignored" + " because of an IOException", e);
                }
                return null;
            }
        });
    }

    private static final int SYMBOL = 0;

    private static final int DISPLAYNAME = 1;

    private Currency(String currencyCode, int defaultFractionDigits, int numericCode) {
        this.currencyCode = currencyCode;
        this.defaultFractionDigits = defaultFractionDigits;
        this.numericCode = numericCode;
    }

    public static Currency getInstance(String currencyCode);

    private static Currency getInstance(String currencyCode, int defaultFractionDigits, int numericCode);

    public static Currency getInstance(Locale locale);

    public static Set<Currency> getAvailableCurrencies();

    public String getCurrencyCode(@GuardSatisfied Currency this);

    public String getSymbol(@GuardSatisfied Currency this);

    public String getSymbol(@GuardSatisfied Currency this, Locale locale);

    public int getDefaultFractionDigits(@GuardSatisfied Currency this);

    public int getNumericCode();

    public String getNumericCodeAsString();

    public String getDisplayName();

    public String getDisplayName(Locale locale);

    @SideEffectFree
    @Override
    public String toString(@GuardSatisfied Currency this);

    private Object readResolve();

    private static int getMainTableEntry(char char1, char char2);

    private static void setMainTableEntry(char char1, char char2, int entry);

    private static class CurrencyNameGetter implements LocaleServiceProviderPool.LocalizedObjectGetter<CurrencyNameProvider, String> {

        private static final CurrencyNameGetter INSTANCE = new CurrencyNameGetter();

        @Override
        public String getObject(CurrencyNameProvider currencyNameProvider, Locale locale, String key, Object... params);
    }

    private static int[] readIntArray(DataInputStream dis, int count) throws IOException;

    private static List<SpecialCaseEntry> readSpecialCases(DataInputStream dis, int count) throws IOException;

    private static List<OtherCurrencyEntry> readOtherCurrencies(DataInputStream dis, int count) throws IOException;

    private static List<CurrencyProperty> getValidCurrencyData(Properties props, Pattern pattern);

    private static void replaceCurrencyData(CurrencyProperty prop);

    private static void updateMainTableEntry(String code, int fraction, int numeric);

    private static class SpecialCaseEntry {

        final private long cutOverTime;

        final private String oldCurrency;

        final private String newCurrency;

        final private int oldCurrencyFraction;

        final private int newCurrencyFraction;

        final private int oldCurrencyNumericCode;

        final private int newCurrencyNumericCode;

        private SpecialCaseEntry(long cutOverTime, String oldCurrency, String newCurrency, int oldCurrencyFraction, int newCurrencyFraction, int oldCurrencyNumericCode, int newCurrencyNumericCode) {
            this.cutOverTime = cutOverTime;
            this.oldCurrency = oldCurrency;
            this.newCurrency = newCurrency;
            this.oldCurrencyFraction = oldCurrencyFraction;
            this.newCurrencyFraction = newCurrencyFraction;
            this.oldCurrencyNumericCode = oldCurrencyNumericCode;
            this.newCurrencyNumericCode = newCurrencyNumericCode;
        }

        private SpecialCaseEntry(String currencyCode, int fraction, int numericCode) {
            this(Long.MAX_VALUE, currencyCode, "", fraction, 0, numericCode, 0);
        }

        private static int indexOf(String code, int fraction, int numeric);

        private static int[] findEntry(String code);

        private static int currencyCodeIndex(String code);

        private static int toIndex(int tableEntry);
    }

    private static class OtherCurrencyEntry {

        final private String currencyCode;

        final private int fraction;

        final private int numericCode;

        private OtherCurrencyEntry(String currencyCode, int fraction, int numericCode) {
            this.currencyCode = currencyCode;
            this.fraction = fraction;
            this.numericCode = numericCode;
        }

        private static OtherCurrencyEntry findEntry(String code);
    }

    private static class CurrencyProperty {

        final private String country;

        final private String currencyCode;

        final private int fraction;

        final private int numericCode;

        final private String date;

        private CurrencyProperty(String country, String currencyCode, int fraction, int numericCode, String date) {
            this.country = country;
            this.currencyCode = currencyCode;
            this.fraction = fraction;
            this.numericCode = numericCode;
            this.date = date;
        }

        private static Optional<CurrencyProperty> getValidEntry(String ctry, String curData, Pattern pattern);

        private static CurrencyProperty parseProperty(String ctry, String curData, Pattern pattern);

        private static boolean containsInconsistentInstances(List<CurrencyProperty> list);

        private static boolean isPastCutoverDate(String s) throws ParseException;

        private static void info(String message, Throwable t);
    }
}
