package java.awt.im;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.font.TextAttribute;
import java.util.Map;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class InputMethodHighlight {

    public static final int RAW_TEXT = 0;

    public static final int CONVERTED_TEXT = 1;

    public static final InputMethodHighlight UNSELECTED_RAW_TEXT_HIGHLIGHT = new InputMethodHighlight(false, RAW_TEXT);

    public static final InputMethodHighlight SELECTED_RAW_TEXT_HIGHLIGHT = new InputMethodHighlight(true, RAW_TEXT);

    public static final InputMethodHighlight UNSELECTED_CONVERTED_TEXT_HIGHLIGHT = new InputMethodHighlight(false, CONVERTED_TEXT);

    public static final InputMethodHighlight SELECTED_CONVERTED_TEXT_HIGHLIGHT = new InputMethodHighlight(true, CONVERTED_TEXT);

    public InputMethodHighlight(boolean selected, int state) {
        this(selected, state, 0, null);
    }

    public InputMethodHighlight(boolean selected, int state, int variation) {
        this(selected, state, variation, null);
    }

    public InputMethodHighlight(boolean selected, int state, int variation, Map<TextAttribute, ?> style) {
        this.selected = selected;
        if (!(state == RAW_TEXT || state == CONVERTED_TEXT)) {
            throw new IllegalArgumentException("unknown input method highlight state");
        }
        this.state = state;
        this.variation = variation;
        this.style = style;
    }

    public boolean isSelected();

    public int getState();

    public int getVariation();

    public Map<TextAttribute, ?> getStyle();

    private boolean selected;

    private int state;

    private int variation;

    private Map<TextAttribute, ?> style;
}
