package java.awt;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Hashtable;

@AnnotatedFor({ "interning" })
public class BorderLayout implements LayoutManager2, java.io.Serializable {

    @Interned
    public static final String NORTH;

    @Interned
    public static final String SOUTH;

    @Interned
    public static final String EAST;

    @Interned
    public static final String WEST;

    @Interned
    public static final String CENTER;

    @Interned
    public static final String BEFORE_FIRST_LINE;

    @Interned
    public static final String AFTER_LAST_LINE;

    @Interned
    public static final String BEFORE_LINE_BEGINS;

    @Interned
    public static final String AFTER_LINE_ENDS;

    public static final String PAGE_START;

    public static final String PAGE_END;

    public static final String LINE_START;

    public static final String LINE_END;

    public BorderLayout() {
    }

    public BorderLayout(int hgap, int vgap) {
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

    public String toString();
}
