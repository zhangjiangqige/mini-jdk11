package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Hashtable;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class GridBagLayout implements LayoutManager2, java.io.Serializable {

    protected static final int MAXGRIDSIZE;

    protected static final int MINSIZE;

    protected static final int PREFERREDSIZE;

    protected Hashtable<Component, GridBagConstraints> comptable;

    protected GridBagConstraints defaultConstraints;

    protected GridBagLayoutInfo layoutInfo;

    public int[] columnWidths;

    public int[] rowHeights;

    public double[] columnWeights;

    public double[] rowWeights;

    public GridBagLayout() {
    }

    public void setConstraints(Component comp, GridBagConstraints constraints);

    public GridBagConstraints getConstraints(Component comp);

    protected GridBagConstraints lookupConstraints(Component comp);

    public Point getLayoutOrigin();

    public int[][] getLayoutDimensions();

    public double[][] getLayoutWeights();

    public Point location(int x, int y);

    public void addLayoutComponent(String name, Component comp);

    public void addLayoutComponent(Component comp, Object constraints);

    public void removeLayoutComponent(Component comp);

    public Dimension preferredLayoutSize(Container parent);

    public Dimension minimumLayoutSize(Container parent);

    public Dimension maximumLayoutSize(Container target);

    public float getLayoutAlignmentX(Container parent);

    public float getLayoutAlignmentY(Container parent);

    public void invalidateLayout(Container target);

    public void layoutContainer(Container parent);

    public String toString();

    protected GridBagLayoutInfo getLayoutInfo(Container parent, int sizeflag);

    protected GridBagLayoutInfo GetLayoutInfo(Container parent, int sizeflag);

    protected void adjustForGravity(GridBagConstraints constraints, Rectangle r);

    protected void AdjustForGravity(GridBagConstraints constraints, Rectangle r);

    protected Dimension getMinSize(Container parent, GridBagLayoutInfo info);

    protected Dimension GetMinSize(Container parent, GridBagLayoutInfo info);

    protected void arrangeGrid(Container parent);

    protected void ArrangeGrid(Container parent);
}
