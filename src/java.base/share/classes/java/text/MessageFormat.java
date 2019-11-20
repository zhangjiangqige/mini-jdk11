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

    public MessageFormat(String pattern) {
    }

    public MessageFormat(String pattern, Locale locale) {
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

        protected Field(String name) {
        }

        protected Object readResolve() throws InvalidObjectException;

        public static final Field ARGUMENT;
    }
}
