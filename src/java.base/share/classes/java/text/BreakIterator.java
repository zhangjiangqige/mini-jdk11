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
    public static final int DONE = -1;

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

    private static final int CHARACTER_INDEX = 0;

    private static final int WORD_INDEX = 1;

    private static final int LINE_INDEX = 2;

    private static final int SENTENCE_INDEX = 3;

    @SuppressWarnings("unchecked")
    private static final SoftReference<BreakIteratorCache>[] iterCache = (SoftReference<BreakIteratorCache>[]) new SoftReference<?>[4];

    public static BreakIterator getWordInstance();

    public static BreakIterator getWordInstance(Locale locale);

    public static BreakIterator getLineInstance();

    public static BreakIterator getLineInstance(Locale locale);

    public static BreakIterator getCharacterInstance();

    public static BreakIterator getCharacterInstance(Locale locale);

    public static BreakIterator getSentenceInstance();

    public static BreakIterator getSentenceInstance(Locale locale);

    private static BreakIterator getBreakInstance(Locale locale, int type);

    private static BreakIterator createBreakInstance(Locale locale, int type);

    private static BreakIterator createBreakInstance(LocaleProviderAdapter adapter, Locale locale, int type);

    public static synchronized Locale[] getAvailableLocales();

    private static final class BreakIteratorCache {

        private BreakIterator iter;

        private Locale locale;

        BreakIteratorCache(Locale locale, BreakIterator iter) {
            this.locale = locale;
            this.iter = (BreakIterator) iter.clone();
        }

        Locale getLocale();

        BreakIterator createBreakInstance();
    }
}
