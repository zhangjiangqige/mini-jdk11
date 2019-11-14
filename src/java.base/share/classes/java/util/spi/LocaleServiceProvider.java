package java.util.spi;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Locale;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class LocaleServiceProvider {

    private static Void checkPermission();

    private LocaleServiceProvider(Void ignore) {
    }

    protected LocaleServiceProvider() {
        this(checkPermission());
    }

    public abstract Locale[] getAvailableLocales();

    public boolean isSupportedLocale(Locale locale);
}
