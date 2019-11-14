package java.text;

import org.checkerframework.checker.i18nformatter.qual.I18nFormatFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InvalidObjectException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@AnnotatedFor({ "i18nformatter", "nullness" })
public class MessageFormat extends Format {

    private static final long serialVersionUID = 6479157306784022952L;

    public MessageFormat(String pattern) {
        this.locale = Locale.getDefault(Locale.Category.FORMAT);
        applyPattern(pattern);
    }

    public MessageFormat(String pattern, Locale locale) {
        this.locale = locale;
        applyPattern(pattern);
    }

    public void setLocale(Locale locale);

    public Locale getLocale();

    @SuppressWarnings("fallthrough")
    public void applyPattern(String pattern);

    public String toPattern();

    public void setFormatsByArgumentIndex(Format[] newFormats);

    public void setFormats(Format[] newFormats);

    public void setFormatByArgumentIndex(int argumentIndex, Format newFormat);

    public void setFormat(int formatElementIndex, Format newFormat);

    @Nullable
    public Format[] getFormatsByArgumentIndex();

    public Format[] getFormats();

    public final StringBuffer format(@Nullable Object @Nullable [] arguments, StringBuffer result, @Nullable FieldPosition pos);

    public static String format(@I18nFormatFor("#2") String pattern, @Nullable Object... arguments);

    public final StringBuffer format(Object arguments, StringBuffer result, FieldPosition pos);

    public AttributedCharacterIterator formatToCharacterIterator(Object arguments);

    public Object[] parse(@Nullable String source, ParsePosition pos);

    public Object[] parse(String source) throws ParseException;

    @Nullable
    public Object parseObject(String source, ParsePosition pos);

    public Object clone();

    public boolean equals(Object obj);

    public int hashCode();

    public static class Field extends Format.Field {

        private static final long serialVersionUID = 7899943957617360810L;

        protected Field(String name) {
            super(name);
        }

        protected Object readResolve() throws InvalidObjectException;

        public static final Field ARGUMENT = new Field("message argument field");
    }

    private Locale locale;

    private String pattern = "";

    private static final int INITIAL_FORMATS = 10;

    private Format[] formats = new Format[INITIAL_FORMATS];

    private int[] offsets = new int[INITIAL_FORMATS];

    private int[] argumentNumbers = new int[INITIAL_FORMATS];

    private int maxOffset = -1;

    private StringBuffer subformat(@Nullable Object @Nullable [] arguments, StringBuffer result, FieldPosition fp, List<AttributedCharacterIterator> characterIterators);

    private void append(StringBuffer result, CharacterIterator iterator);

    private static final int SEG_RAW = 0;

    private static final int SEG_INDEX = 1;

    private static final int SEG_TYPE = 2;

    private static final int SEG_MODIFIER = 3;

    private static final int TYPE_NULL = 0;

    private static final int TYPE_NUMBER = 1;

    private static final int TYPE_DATE = 2;

    private static final int TYPE_TIME = 3;

    private static final int TYPE_CHOICE = 4;

    private static final String[] TYPE_KEYWORDS = { "", "number", "date", "time", "choice" };

    private static final int MODIFIER_DEFAULT = 0;

    private static final int MODIFIER_CURRENCY = 1;

    private static final int MODIFIER_PERCENT = 2;

    private static final int MODIFIER_INTEGER = 3;

    private static final String[] NUMBER_MODIFIER_KEYWORDS = { "", "currency", "percent", "integer" };

    private static final int MODIFIER_SHORT = 1;

    private static final int MODIFIER_MEDIUM = 2;

    private static final int MODIFIER_LONG = 3;

    private static final int MODIFIER_FULL = 4;

    private static final String[] DATE_TIME_MODIFIER_KEYWORDS = { "", "short", "medium", "long", "full" };

    private static final int[] DATE_TIME_MODIFIERS = { DateFormat.DEFAULT, DateFormat.SHORT, DateFormat.MEDIUM, DateFormat.LONG, DateFormat.FULL };

    private void makeFormat(int position, int offsetNumber, StringBuilder[] textSegments);

    private static final int findKeyword(String s, String[] list);

    private static final void copyAndFixQuotes(String source, int start, int end, StringBuilder target);

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException;
}
