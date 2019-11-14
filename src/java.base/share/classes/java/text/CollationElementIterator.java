package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.Character;
import java.util.Vector;
import sun.text.CollatorUtilities;
import sun.text.normalizer.NormalizerBase;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class CollationElementIterator {

    public static final int NULLORDER = 0xffffffff;

    CollationElementIterator(String sourceText, RuleBasedCollator owner) {
        this.owner = owner;
        ordering = owner.getTables();
        if (sourceText.length() != 0) {
            NormalizerBase.Mode mode = CollatorUtilities.toNormalizerMode(owner.getDecomposition());
            text = new NormalizerBase(sourceText, mode);
        }
    }

    CollationElementIterator(CharacterIterator sourceText, RuleBasedCollator owner) {
        this.owner = owner;
        ordering = owner.getTables();
        NormalizerBase.Mode mode = CollatorUtilities.toNormalizerMode(owner.getDecomposition());
        text = new NormalizerBase(sourceText, mode);
    }

    public void reset();

    public int next();

    public int previous();

    public static final int primaryOrder(int order);

    public static final short secondaryOrder(int order);

    public static final short tertiaryOrder(int order);

    final int strengthOrder(int order);

    @SuppressWarnings("deprecation")
    public void setOffset(int newOffset);

    public int getOffset();

    public int getMaxExpansion(int order);

    public void setText(String source);

    public void setText(CharacterIterator source);

    private static final boolean isThaiPreVowel(int ch);

    private static final boolean isThaiBaseConsonant(int ch);

    private static final boolean isLaoPreVowel(int ch);

    private static final boolean isLaoBaseConsonant(int ch);

    private int[] makeReorderedBuffer(int colFirst, int lastValue, int[] lastExpansion, boolean forward);

    static final boolean isIgnorable(int order);

    private int nextContractChar(int ch);

    private int prevContractChar(int ch);

    static final int UNMAPPEDCHARVALUE = 0x7FFF0000;

    private NormalizerBase text = null;

    private int[] buffer = null;

    private int expIndex = 0;

    private StringBuffer key = new StringBuffer(5);

    private int swapOrder = 0;

    private RBCollationTables ordering;

    private RuleBasedCollator owner;
}
