package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Hashtable;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class GridBagLayout implements LayoutManager2, java.io.Serializable {

    static final int EMPIRICMULTIPLIER = 2;

    protected static final int MAXGRIDSIZE = 512;

    protected static final int MINSIZE = 1;

    protected static final int PREFERREDSIZE = 2;

    protected Hashtable<Component, GridBagConstraints> comptable;

    protected GridBagConstraints defaultConstraints;

    protected GridBagLayoutInfo layoutInfo;

    public int[] columnWidths;

    public int[] rowHeights;

    public double[] columnWeights;

    public double[] rowWeights;

    private Component componentAdjusting;

    public GridBagLayout() {
        comptable = new Hashtable<Component, GridBagConstraints>();
        defaultConstraints = new GridBagConstraints();
    }

    public void setConstraints(Component comp, GridBagConstraints constraints);

    public GridBagConstraints getConstraints(Component comp);

    protected GridBagConstraints lookupConstraints(Component comp);

    private void removeConstraints(Component comp);

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

    private long[] preInitMaximumArraySizes(Container parent);

    protected GridBagLayoutInfo GetLayoutInfo(Container parent, int sizeflag);

    private boolean calculateBaseline(Component c, GridBagConstraints constraints, Dimension size);

    protected void adjustForGravity(GridBagConstraints constraints, Rectangle r);

    protected void AdjustForGravity(GridBagConstraints constraints, Rectangle r);

    private void alignOnBaseline(GridBagConstraints cons, Rectangle r, int cellY, int cellHeight);

    private void alignAboveBaseline(GridBagConstraints cons, Rectangle r, int cellY, int cellHeight);

    private void alignBelowBaseline(GridBagConstraints cons, Rectangle r, int cellY, int cellHeight);

    private void centerVertically(GridBagConstraints cons, Rectangle r, int cellHeight);

    protected Dimension getMinSize(Container parent, GridBagLayoutInfo info);

    protected Dimension GetMinSize(Container parent, GridBagLayoutInfo info);

    transient boolean rightToLeft = false;

    protected void arrangeGrid(Container parent);

    protected void ArrangeGrid(Container parent);

    static final long serialVersionUID = 8838754796412211005L;
}
