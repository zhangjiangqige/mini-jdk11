package java.util.spi;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Locale;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class LocaleServiceProvider {

    protected LocaleServiceProvider() {
    }

    public abstract Locale[] getAvailableLocales();

    public boolean isSupportedLocale(Locale locale);
}
