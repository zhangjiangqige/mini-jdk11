package java.awt;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Hashtable;

@AnnotatedFor({ "interning" })
public class BorderLayout implements LayoutManager2, java.io.Serializable {

    int hgap;

    int vgap;

    Component north;

    Component west;

    Component east;

    Component south;

    Component center;

    Component firstLine;

    Component lastLine;

    Component firstItem;

    Component lastItem;

    @Interned
    public static final String NORTH = "North";

    @Interned
    public static final String SOUTH = "South";

    @Interned
    public static final String EAST = "East";

    @Interned
    public static final String WEST = "West";

    @Interned
    public static final String CENTER = "Center";

    @Interned
    public static final String BEFORE_FIRST_LINE = "First";

    @Interned
    public static final String AFTER_LAST_LINE = "Last";

    @Interned
    public static final String BEFORE_LINE_BEGINS = "Before";

    @Interned
    public static final String AFTER_LINE_ENDS = "After";

    public static final String PAGE_START = BEFORE_FIRST_LINE;

    public static final String PAGE_END = AFTER_LAST_LINE;

    public static final String LINE_START = BEFORE_LINE_BEGINS;

    public static final String LINE_END = AFTER_LINE_ENDS;

    private static final long serialVersionUID = -8658291919501921765L;

    public BorderLayout() {
        this(0, 0);
    }

    public BorderLayout(int hgap, int vgap) {
        this.hgap = hgap;
        this.vgap = vgap;
    }

    public int getHgap();

    public void setHgap(int hgap);

    public int getVgap();

    public void setVgap(int vgap);

    public void addLayoutComponent(Component comp, Object constraints);

    @Deprecated
    public void addLayoutComponent(String name, Component comp);

    public void removeLayoutComponent(Component comp);

    public Component getLayoutComponent(Object constraints);

    public Component getLayoutComponent(Container target, Object constraints);

    public Object getConstraints(Component comp);

    public Dimension minimumLayoutSize(Container target);

    public Dimension preferredLayoutSize(Container target);

    public Dimension maximumLayoutSize(Container target);

    public float getLayoutAlignmentX(Container parent);

    public float getLayoutAlignmentY(Container parent);

    public void invalidateLayout(Container target);

    public void layoutContainer(Container target);

    private Component getChild(String key, boolean ltr);

    public String toString();
}
