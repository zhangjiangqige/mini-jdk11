package javax.swing.table;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Component;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;
import sun.swing.DefaultLookup;
import sun.swing.SwingUtilities2;

@AnnotatedFor({ "index", "interning" })
@SuppressWarnings("serial")
public class DefaultTableCellRenderer extends JLabel implements TableCellRenderer, Serializable {

    private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    protected static Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;

    private Color unselectedForeground;

    private Color unselectedBackground;

    public DefaultTableCellRenderer() {
        super();
        setOpaque(true);
        setBorder(getNoFocusBorder());
        setName("Table.cellRenderer");
    }

    private Border getNoFocusBorder();

    public void setForeground(Color c);

    public void setBackground(Color c);

    public void updateUI();

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, @NonNegative int row, @NonNegative int column);

    public boolean isOpaque();

    public void invalidate();

    public void validate();

    public void revalidate();

    public void repaint(long tm, int x, int y, int width, int height);

    public void repaint(Rectangle r);

    public void repaint();

    protected void firePropertyChange(@Interned String propertyName, Object oldValue, Object newValue);

    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue);

    protected void setValue(Object value);

    @SuppressWarnings("serial")
    public static class UIResource extends DefaultTableCellRenderer implements javax.swing.plaf.UIResource {
    }
}
