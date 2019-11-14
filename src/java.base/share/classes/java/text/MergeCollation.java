package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
final class MergeCollation {

    public MergeCollation(String pattern) throws ParseException {
        for (int i = 0; i < statusArray.length; i++) statusArray[i] = 0;
        setPattern(pattern);
    }

    public String getPattern();

    public String getPattern(boolean withWhiteSpace);

    private final PatternEntry findLastWithNoExtension(int i);

    public String emitPattern();

    public String emitPattern(boolean withWhiteSpace);

    public void setPattern(String pattern) throws ParseException;

    public void addPattern(String pattern) throws ParseException;

    public int getCount();

    public PatternEntry getItemAt(int index);

    ArrayList<PatternEntry> patterns = new ArrayList<>();

    private transient PatternEntry saveEntry = null;

    private transient PatternEntry lastEntry = null;

    private transient StringBuffer excess = new StringBuffer();

    private transient byte[] statusArray = new byte[8192];

    private final byte BITARRAYMASK = (byte) 0x1;

    private final int BYTEPOWER = 3;

    private final int BYTEMASK = (1 << BYTEPOWER) - 1;

    private final void fixEntry(PatternEntry newEntry) throws ParseException;

    private final int findLastEntry(PatternEntry entry, StringBuffer excessChars) throws ParseException;
}
