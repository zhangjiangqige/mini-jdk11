package javax.swing.tree;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import sun.swing.DefaultLookup;
import sun.swing.SwingUtilities2;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class DefaultTreeCellRenderer extends JLabel implements TreeCellRenderer {

    protected boolean selected;

    protected boolean hasFocus;

    protected transient Icon closedIcon;

    protected transient Icon leafIcon;

    protected transient Icon openIcon;

    protected Color textSelectionColor;

    protected Color textNonSelectionColor;

    protected Color backgroundSelectionColor;

    protected Color backgroundNonSelectionColor;

    protected Color borderSelectionColor;

    public DefaultTreeCellRenderer() {
    }

    public void updateUI();

    public Icon getDefaultOpenIcon();

    public Icon getDefaultClosedIcon();

    public Icon getDefaultLeafIcon();

    public void setOpenIcon(Icon newIcon);

    public Icon getOpenIcon();

    public void setClosedIcon(Icon newIcon);

    public Icon getClosedIcon();

    public void setLeafIcon(Icon newIcon);

    public Icon getLeafIcon();

    public void setTextSelectionColor(Color newColor);

    public Color getTextSelectionColor();

    public void setTextNonSelectionColor(Color newColor);

    public Color getTextNonSelectionColor();

    public void setBackgroundSelectionColor(Color newColor);

    public Color getBackgroundSelectionColor();

    public void setBackgroundNonSelectionColor(Color newColor);

    public Color getBackgroundNonSelectionColor();

    public void setBorderSelectionColor(Color newColor);

    public Color getBorderSelectionColor();

    public void setFont(Font font);

    public Font getFont();

    public void setBackground(Color color);

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus);

    public void paint(Graphics g);

    public Dimension getPreferredSize();

    public void validate();

    public void invalidate();

    public void revalidate();

    public void repaint(long tm, int x, int y, int width, int height);

    public void repaint(Rectangle r);

    public void repaint();

    protected void firePropertyChange(@Interned String propertyName, Object oldValue, Object newValue);

    public void firePropertyChange(String propertyName, byte oldValue, byte newValue);

    public void firePropertyChange(String propertyName, char oldValue, char newValue);

    public void firePropertyChange(String propertyName, short oldValue, short newValue);

    public void firePropertyChange(String propertyName, int oldValue, int newValue);

    public void firePropertyChange(String propertyName, long oldValue, long newValue);

    public void firePropertyChange(String propertyName, float oldValue, float newValue);

    public void firePropertyChange(String propertyName, double oldValue, double newValue);

    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue);
}
