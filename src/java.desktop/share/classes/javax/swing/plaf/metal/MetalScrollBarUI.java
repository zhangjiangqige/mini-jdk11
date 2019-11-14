package javax.swing.plaf.metal;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import static sun.swing.SwingUtilities2.drawHLine;
import static sun.swing.SwingUtilities2.drawRect;
import static sun.swing.SwingUtilities2.drawVLine;

@AnnotatedFor({ "interning" })
public class MetalScrollBarUI extends BasicScrollBarUI {

    private static Color shadowColor;

    private static Color highlightColor;

    private static Color darkShadowColor;

    private static Color thumbColor;

    private static Color thumbShadow;

    private static Color thumbHighlightColor;

    private MetalBumps bumps;

    protected MetalScrollButton increaseButton;

    protected MetalScrollButton decreaseButton;

    protected int scrollBarWidth;

    @Interned
    public static final String FREE_STANDING_PROP = "JScrollBar.isFreeStanding";

    protected boolean isFreeStanding = true;

    public static ComponentUI createUI(JComponent c);

    protected void installDefaults();

    protected void installListeners();

    protected PropertyChangeListener createPropertyChangeListener();

    protected void configureScrollBarColors();

    public Dimension getPreferredSize(JComponent c);

    protected JButton createDecreaseButton(int orientation);

    protected JButton createIncreaseButton(int orientation);

    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds);

    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds);

    private void oceanPaintThumb(Graphics g, JComponent c, Rectangle thumbBounds);

    protected Dimension getMinimumThumbSize();

    protected void setThumbBounds(int x, int y, int width, int height);

    class ScrollBarListener extends BasicScrollBarUI.PropertyChangeHandler {

        public void propertyChange(PropertyChangeEvent e);

        public void handlePropertyChange(Object newValue);

        protected void toFlush();

        protected void toFreeStanding();
    }
}
