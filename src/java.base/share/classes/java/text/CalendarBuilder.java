package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Calendar;
import java.util.StringJoiner;
import static java.util.GregorianCalendar.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class CalendarBuilder {

    private static final int UNSET = 0;

    private static final int COMPUTED = 1;

    private static final int MINIMUM_USER_STAMP = 2;

    private static final int MAX_FIELD = FIELD_COUNT + 1;

    public static final int WEEK_YEAR = FIELD_COUNT;

    public static final int ISO_DAY_OF_WEEK = 1000;

    private final int[] field;

    private int nextStamp;

    private int maxFieldIndex;

    CalendarBuilder() {
        field = new int[MAX_FIELD * 2];
        nextStamp = MINIMUM_USER_STAMP;
        maxFieldIndex = -1;
    }

    CalendarBuilder set(int index, int value);

    CalendarBuilder addYear(int value);

    boolean isSet(int index);

    CalendarBuilder clear(int index);

    Calendar establish(Calendar cal);

    public String toString();

    static int toISODayOfWeek(int calendarDayOfWeek);

    static int toCalendarDayOfWeek(int isoDayOfWeek);

    static boolean isValidDayOfWeek(int dayOfWeek);
}
