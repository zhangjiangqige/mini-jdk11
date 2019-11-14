package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class GridBagConstraints implements Cloneable, java.io.Serializable {

    public static final int RELATIVE = -1;

    public static final int REMAINDER = 0;

    public static final int NONE = 0;

    public static final int BOTH = 1;

    public static final int HORIZONTAL = 2;

    public static final int VERTICAL = 3;

    public static final int CENTER = 10;

    public static final int NORTH = 11;

    public static final int NORTHEAST = 12;

    public static final int EAST = 13;

    public static final int SOUTHEAST = 14;

    public static final int SOUTH = 15;

    public static final int SOUTHWEST = 16;

    public static final int WEST = 17;

    public static final int NORTHWEST = 18;

    public static final int PAGE_START = 19;

    public static final int PAGE_END = 20;

    public static final int LINE_START = 21;

    public static final int LINE_END = 22;

    public static final int FIRST_LINE_START = 23;

    public static final int FIRST_LINE_END = 24;

    public static final int LAST_LINE_START = 25;

    public static final int LAST_LINE_END = 26;

    public static final int BASELINE = 0x100;

    public static final int BASELINE_LEADING = 0x200;

    public static final int BASELINE_TRAILING = 0x300;

    public static final int ABOVE_BASELINE = 0x400;

    public static final int ABOVE_BASELINE_LEADING = 0x500;

    public static final int ABOVE_BASELINE_TRAILING = 0x600;

    public static final int BELOW_BASELINE = 0x700;

    public static final int BELOW_BASELINE_LEADING = 0x800;

    public static final int BELOW_BASELINE_TRAILING = 0x900;

    public int gridx;

    public int gridy;

    public int gridwidth;

    public int gridheight;

    public double weightx;

    public double weighty;

    public int anchor;

    public int fill;

    public Insets insets;

    public int ipadx;

    public int ipady;

    int tempX;

    int tempY;

    int tempWidth;

    int tempHeight;

    int minWidth;

    int minHeight;

    transient int ascent;

    transient int descent;

    transient Component.BaselineResizeBehavior baselineResizeBehavior;

    transient int centerPadding;

    transient int centerOffset;

    private static final long serialVersionUID = -1000070633030801713L;

    public GridBagConstraints() {
        gridx = RELATIVE;
        gridy = RELATIVE;
        gridwidth = 1;
        gridheight = 1;
        weightx = 0;
        weighty = 0;
        anchor = CENTER;
        fill = NONE;
        insets = new Insets(0, 0, 0, 0);
        ipadx = 0;
        ipady = 0;
    }

    public GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.fill = fill;
        this.ipadx = ipadx;
        this.ipady = ipady;
        this.insets = insets;
        this.anchor = anchor;
        this.weightx = weightx;
        this.weighty = weighty;
    }

    public Object clone();

    boolean isVerticallyResizable();
}
