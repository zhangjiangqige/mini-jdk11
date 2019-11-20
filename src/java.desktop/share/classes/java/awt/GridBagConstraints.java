package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class GridBagConstraints implements Cloneable, java.io.Serializable {

    public static final int RELATIVE;

    public static final int REMAINDER;

    public static final int NONE;

    public static final int BOTH;

    public static final int HORIZONTAL;

    public static final int VERTICAL;

    public static final int CENTER;

    public static final int NORTH;

    public static final int NORTHEAST;

    public static final int EAST;

    public static final int SOUTHEAST;

    public static final int SOUTH;

    public static final int SOUTHWEST;

    public static final int WEST;

    public static final int NORTHWEST;

    public static final int PAGE_START;

    public static final int PAGE_END;

    public static final int LINE_START;

    public static final int LINE_END;

    public static final int FIRST_LINE_START;

    public static final int FIRST_LINE_END;

    public static final int LAST_LINE_START;

    public static final int LAST_LINE_END;

    public static final int BASELINE;

    public static final int BASELINE_LEADING;

    public static final int BASELINE_TRAILING;

    public static final int ABOVE_BASELINE;

    public static final int ABOVE_BASELINE_LEADING;

    public static final int ABOVE_BASELINE_TRAILING;

    public static final int BELOW_BASELINE;

    public static final int BELOW_BASELINE_LEADING;

    public static final int BELOW_BASELINE_TRAILING;

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

    public GridBagConstraints() {
    }

    public GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady) {
    }

    public Object clone();
}
