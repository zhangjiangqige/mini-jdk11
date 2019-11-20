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

    public static final int NULLORDER;

    public void reset();

    public int next();

    public int previous();

    public static final int primaryOrder(int order);

    public static final short secondaryOrder(int order);

    public static final short tertiaryOrder(int order);

    @SuppressWarnings("deprecation")
    public void setOffset(int newOffset);

    public int getOffset();

    public int getMaxExpansion(int order);

    public void setText(String source);

    public void setText(CharacterIterator source);
}
