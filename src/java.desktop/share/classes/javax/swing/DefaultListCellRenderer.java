package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.Component;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;
import sun.swing.DefaultLookup;
import sun.swing.SwingUtilities2;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class DefaultListCellRenderer extends JLabel implements ListCellRenderer<Object>, Serializable {

    private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    protected static Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;

    public DefaultListCellRenderer() {
        super();
        setOpaque(true);
        setBorder(getNoFocusBorder());
        setName("List.cellRenderer");
    }

    private Border getNoFocusBorder();

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus);

    @Override
    public boolean isOpaque();

    @Override
    public void validate();

    @Override
    public void invalidate();

    @Override
    public void repaint();

    @Override
    public void revalidate();

    @Override
    public void repaint(long tm, int x, int y, int width, int height);

    @Override
    public void repaint(Rectangle r);

    @Override
    protected void firePropertyChange(@Interned String propertyName, Object oldValue, Object newValue);

    @Override
    public void firePropertyChange(String propertyName, byte oldValue, byte newValue);

    @Override
    public void firePropertyChange(String propertyName, char oldValue, char newValue);

    @Override
    public void firePropertyChange(String propertyName, short oldValue, short newValue);

    @Override
    public void firePropertyChange(String propertyName, int oldValue, int newValue);

    @Override
    public void firePropertyChange(String propertyName, long oldValue, long newValue);

    @Override
    public void firePropertyChange(String propertyName, float oldValue, float newValue);

    @Override
    public void firePropertyChange(String propertyName, double oldValue, double newValue);

    @Override
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue);

    @SuppressWarnings("serial")
    public static class UIResource extends DefaultListCellRenderer implements javax.swing.plaf.UIResource {
    }
}
