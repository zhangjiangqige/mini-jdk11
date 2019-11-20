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

    public static Currency getInstance(String currencyCode);

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
}
