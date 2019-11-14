package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Vector;
import sun.text.UCompactIntArray;
import sun.text.IntHashtable;
import sun.text.ComposedCharIter;
import sun.text.CollatorUtilities;
import sun.text.normalizer.NormalizerImpl;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
final class RBTableBuilder {

    public RBTableBuilder(RBCollationTables.BuildAPI tables) {
        this.tables = tables;
    }

    public void build(String pattern, int decmp) throws ParseException;

    private void addComposedChars() throws ParseException;

    private final void commit();

    private final int increment(int aStrength, int lastValue);

    private final void addOrder(int ch, int anOrder);

    private final void addContractOrder(String groupChars, int anOrder);

    private final void addContractOrder(String groupChars, int anOrder, boolean fwd);

    private int getContractOrder(String groupChars);

    private final int getCharOrder(int ch);

    private Vector<EntryPair> getContractValues(int ch);

    private Vector<EntryPair> getContractValuesImpl(int index);

    private final void addExpandOrder(String contractChars, String expandChars, int anOrder) throws ParseException;

    private final void addExpandOrder(int ch, String expandChars, int anOrder) throws ParseException;

    private int addExpansion(int anOrder, String expandChars);

    private void addContractFlags(String chars);

    static final int CHARINDEX = 0x70000000;

    private static final int IGNORABLEMASK = 0x0000ffff;

    private static final int PRIMARYORDERINCREMENT = 0x00010000;

    private static final int SECONDARYORDERINCREMENT = 0x00000100;

    private static final int TERTIARYORDERINCREMENT = 0x00000001;

    private static final int INITIALTABLESIZE = 20;

    private static final int MAXKEYSIZE = 5;

    private RBCollationTables.BuildAPI tables = null;

    private MergeCollation mPattern = null;

    private boolean isOverIgnore = false;

    private char[] keyBuf = new char[MAXKEYSIZE];

    private IntHashtable contractFlags = new IntHashtable(100);

    private boolean frenchSec = false;

    private boolean seAsianSwapping = false;

    private UCompactIntArray mapping = null;

    private Vector<Vector<EntryPair>> contractTable = null;

    private Vector<int[]> expandTable = null;

    private short maxSecOrder = 0;

    private short maxTerOrder = 0;
}
