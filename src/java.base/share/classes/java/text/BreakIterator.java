package java.text;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.common.value.qual.IntVal;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.SoftReference;
import java.text.spi.BreakIteratorProvider;
import java.util.Locale;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.LocaleServiceProviderPool;

@AnnotatedFor({ "index" })
public abstract class BreakIterator implements Cloneable {

    protected BreakIterator() {
    }

    @Override
    public Object clone();

    @IntVal({ -1 })
    public static final int DONE;

    @GTENegativeOne
    public abstract int first();

    @GTENegativeOne
    public abstract int last();

    @GTENegativeOne
    public abstract int next(int n);

    @GTENegativeOne
    public abstract int next();

    @GTENegativeOne
    public abstract int previous();

    @GTENegativeOne
    public abstract int following(@NonNegative int offset);

    @GTENegativeOne
    public int preceding(@NonNegative int offset);

    public boolean isBoundary(@NonNegative int offset);

    @GTENegativeOne
    public abstract int current();

    public abstract CharacterIterator getText();

    public void setText(String newText);

    public abstract void setText(CharacterIterator newText);

    public static BreakIterator getWordInstance();

    public static BreakIterator getWordInstance(Locale locale);

    public static BreakIterator getLineInstance();

    public static BreakIterator getLineInstance(Locale locale);

    public static BreakIterator getCharacterInstance();

    public static BreakIterator getCharacterInstance(Locale locale);

    public static BreakIterator getSentenceInstance();

    public static BreakIterator getSentenceInstance(Locale locale);

    public static synchronized Locale[] getAvailableLocales();
}
