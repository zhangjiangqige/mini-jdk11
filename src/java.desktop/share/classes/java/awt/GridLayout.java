package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class GridLayout implements LayoutManager, java.io.Serializable {

    private static final long serialVersionUID = -7411804673224730901L;

    int hgap;

    int vgap;

    int rows;

    int cols;

    public GridLayout() {
        this(1, 0, 0, 0);
    }

    public GridLayout(int rows, int cols) {
        this(rows, cols, 0, 0);
    }

    public GridLayout(int rows, int cols, int hgap, int vgap) {
        if ((rows == 0) && (cols == 0)) {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }
        this.rows = rows;
        this.cols = cols;
        this.hgap = hgap;
        this.vgap = vgap;
    }

    public int getRows();

    public void setRows(int rows);

    public int getColumns();

    public void setColumns(int cols);

    public int getHgap();

    public void setHgap(int hgap);

    public int getVgap();

    public void setVgap(int vgap);

    public void addLayoutComponent(String name, Component comp);

    public void removeLayoutComponent(Component comp);

    public Dimension preferredLayoutSize(Container parent);

    public Dimension minimumLayoutSize(Container parent);

    public void layoutContainer(Container parent);

    public String toString();
}
