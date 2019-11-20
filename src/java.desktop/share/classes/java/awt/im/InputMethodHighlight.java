package java.awt.im;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.font.TextAttribute;
import java.util.Map;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class InputMethodHighlight {

    public static final int RAW_TEXT;

    public static final int CONVERTED_TEXT;

    public static final InputMethodHighlight UNSELECTED_RAW_TEXT_HIGHLIGHT;

    public static final InputMethodHighlight SELECTED_RAW_TEXT_HIGHLIGHT;

    public static final InputMethodHighlight UNSELECTED_CONVERTED_TEXT_HIGHLIGHT;

    public static final InputMethodHighlight SELECTED_CONVERTED_TEXT_HIGHLIGHT;

    public InputMethodHighlight(boolean selected, int state) {
    }

    public InputMethodHighlight(boolean selected, int state, int variation) {
    }

    public InputMethodHighlight(boolean selected, int state, int variation, Map<TextAttribute, ?> style) {
    }

    public boolean isSelected();

    public int getState();

    public int getVariation();

    public Map<TextAttribute, ?> getStyle();
}
