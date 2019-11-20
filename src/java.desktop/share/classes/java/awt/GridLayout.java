package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class GridLayout implements LayoutManager, java.io.Serializable {

    public GridLayout() {
    }

    public GridLayout(int rows, int cols) {
    }

    public GridLayout(int rows, int cols, int hgap, int vgap) {
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
