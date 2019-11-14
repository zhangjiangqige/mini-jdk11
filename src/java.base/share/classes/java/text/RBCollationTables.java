package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Vector;
import sun.text.UCompactIntArray;
import sun.text.IntHashtable;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
final class RBCollationTables {

    public RBCollationTables(String rules, int decmp) throws ParseException {
        this.rules = rules;
        RBTableBuilder builder = new RBTableBuilder(new BuildAPI());
        builder.build(rules, decmp);
    }

    final class BuildAPI {

        private BuildAPI() {
        }

        void fillInTables(boolean f2ary, boolean swap, UCompactIntArray map, Vector<Vector<EntryPair>> cTbl, Vector<int[]> eTbl, IntHashtable cFlgs, short mso, short mto);
    }

    public String getRules();

    public boolean isFrenchSec();

    public boolean isSEAsianSwapping();

    Vector<EntryPair> getContractValues(int ch);

    private Vector<EntryPair> getContractValuesImpl(int index);

    boolean usedInContractSeq(int c);

    int getMaxExpansion(int order);

    final int[] getExpandValueList(int idx);

    int getUnicodeOrder(int ch);

    short getMaxSecOrder();

    short getMaxTerOrder();

    static void reverse(StringBuffer result, int from, int to);

    static final int getEntry(Vector<EntryPair> list, String name, boolean fwd);

    static final int EXPANDCHARINDEX = 0x7E000000;

    static final int CONTRACTCHARINDEX = 0x7F000000;

    static final int UNMAPPED = 0xFFFFFFFF;

    static final int PRIMARYORDERMASK = 0xffff0000;

    static final int SECONDARYORDERMASK = 0x0000ff00;

    static final int TERTIARYORDERMASK = 0x000000ff;

    static final int PRIMARYDIFFERENCEONLY = 0xffff0000;

    static final int SECONDARYDIFFERENCEONLY = 0xffffff00;

    static final int PRIMARYORDERSHIFT = 16;

    static final int SECONDARYORDERSHIFT = 8;

    private String rules = null;

    private boolean frenchSec = false;

    private boolean seAsianSwapping = false;

    private UCompactIntArray mapping = null;

    private Vector<Vector<EntryPair>> contractTable = null;

    private Vector<int[]> expandTable = null;

    private IntHashtable contractFlags = null;

    private short maxSecOrder = 0;

    private short maxTerOrder = 0;
}
