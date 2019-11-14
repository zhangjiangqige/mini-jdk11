package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class TextJustifier {

    private GlyphJustificationInfo[] info;

    private int start;

    private int limit;

    static boolean DEBUG = false;

    TextJustifier(GlyphJustificationInfo[] info, int start, int limit) {
        this.info = info;
        this.start = start;
        this.limit = limit;
        if (DEBUG) {
            System.out.println("start: " + start + ", limit: " + limit);
            for (int i = start; i < limit; i++) {
                GlyphJustificationInfo gji = info[i];
                System.out.println("w: " + gji.weight + ", gp: " + gji.growPriority + ", gll: " + gji.growLeftLimit + ", grl: " + gji.growRightLimit);
            }
        }
    }

    public static final int MAX_PRIORITY = 3;

    public float[] justify(float delta);
}
