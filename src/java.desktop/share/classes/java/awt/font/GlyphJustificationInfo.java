package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class GlyphJustificationInfo {

    public GlyphJustificationInfo(float weight, boolean growAbsorb, int growPriority, float growLeftLimit, float growRightLimit, boolean shrinkAbsorb, int shrinkPriority, float shrinkLeftLimit, float shrinkRightLimit) {
    }

    public static final int PRIORITY_KASHIDA;

    public static final int PRIORITY_WHITESPACE;

    public static final int PRIORITY_INTERCHAR;

    public static final int PRIORITY_NONE;

    public final float weight;

    public final int growPriority;

    public final boolean growAbsorb;

    public final float growLeftLimit;

    public final float growRightLimit;

    public final int shrinkPriority;

    public final boolean shrinkAbsorb;

    public final float shrinkLeftLimit;

    public final float shrinkRightLimit;
}
