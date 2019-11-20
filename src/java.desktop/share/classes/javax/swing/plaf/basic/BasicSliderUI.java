package javax.swing.plaf.basic;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.event.*;
import java.awt.*;
import java.beans.*;
import java.util.Dictionary;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import sun.swing.DefaultLookup;
import sun.swing.SwingUtilities2;
import sun.swing.UIAction;

@AnnotatedFor({ "interning" })
public class BasicSliderUI extends SliderUI {

    public static final int POSITIVE_SCROLL;

    public static final int NEGATIVE_SCROLL;

    public static final int MIN_SCROLL;

    public static final int MAX_SCROLL;

    protected Timer scrollTimer;

    protected JSlider slider;

    protected Insets focusInsets;

    protected Insets insetCache;

    protected boolean leftToRightCache;

    protected Rectangle focusRect;

    protected Rectangle contentRect;

    protected Rectangle labelRect;

    protected Rectangle tickRect;

    protected Rectangle trackRect;

    protected Rectangle thumbRect;

    protected int trackBuffer;

    protected TrackListener trackListener;

    protected ChangeListener changeListener;

    protected ComponentListener componentListener;

    protected FocusListener focusListener;

    protected ScrollListener scrollListener;

    protected PropertyChangeListener propertyChangeListener;

    protected Color getShadowColor();

    protected Color getHighlightColor();

    protected Color getFocusColor();

    protected boolean isDragging();

    public static ComponentUI createUI(JComponent b);

    public BasicSliderUI(JSlider b) {
    }

    public void installUI(JComponent c);

    public void uninstallUI(JComponent c);

    protected void installDefaults(JSlider slider);

    protected void uninstallDefaults(JSlider slider);

    protected TrackListener createTrackListener(JSlider slider);

    protected ChangeListener createChangeListener(JSlider slider);

    protected ComponentListener createComponentListener(JSlider slider);

    protected FocusListener createFocusListener(JSlider slider);

    protected ScrollListener createScrollListener(JSlider slider);

    protected PropertyChangeListener createPropertyChangeListener(JSlider slider);

    protected void installListeners(JSlider slider);

    protected void uninstallListeners(JSlider slider);

    protected void installKeyboardActions(JSlider slider);

    protected void uninstallKeyboardActions(JSlider slider);

    public int getBaseline(JComponent c, int width, int height);

    public Component.BaselineResizeBehavior getBaselineResizeBehavior(JComponent c);

    protected boolean labelsHaveSameBaselines();

    public Dimension getPreferredHorizontalSize();

    public Dimension getPreferredVerticalSize();

    public Dimension getMinimumHorizontalSize();

    public Dimension getMinimumVerticalSize();

    public Dimension getPreferredSize(JComponent c);

    public Dimension getMinimumSize(JComponent c);

    public Dimension getMaximumSize(JComponent c);

    protected void calculateGeometry();

    protected void calculateFocusRect();

    protected void calculateThumbSize();

    protected void calculateContentRect();

    protected void calculateThumbLocation();

    protected void calculateTrackBuffer();

    protected void calculateTrackRect();

    protected int getTickLength();

    protected void calculateTickRect();

    protected void calculateLabelRect();

    protected Dimension getThumbSize();

    public class PropertyChangeHandler implements PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent e);
    }

    protected int getWidthOfWidestLabel();

    protected int getHeightOfTallestLabel();

    protected int getWidthOfHighValueLabel();

    protected int getWidthOfLowValueLabel();

    protected int getHeightOfHighValueLabel();

    protected int getHeightOfLowValueLabel();

    protected boolean drawInverted();

    protected Integer getHighestValue();

    protected Integer getLowestValue();

    protected Component getLowestValueLabel();

    protected Component getHighestValueLabel();

    public void paint(Graphics g, JComponent c);

    protected void recalculateIfInsetsChanged();

    protected void recalculateIfOrientationChanged();

    public void paintFocus(Graphics g);

    public void paintTrack(Graphics g);

    public void paintTicks(Graphics g);

    protected void paintMinorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x);

    protected void paintMajorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x);

    protected void paintMinorTickForVertSlider(Graphics g, Rectangle tickBounds, int y);

    protected void paintMajorTickForVertSlider(Graphics g, Rectangle tickBounds, int y);

    public void paintLabels(Graphics g);

    protected void paintHorizontalLabel(Graphics g, int value, Component label);

    protected void paintVerticalLabel(Graphics g, int value, Component label);

    public void paintThumb(Graphics g);

    public void setThumbLocation(int x, int y);

    public void scrollByBlock(int direction);

    public void scrollByUnit(int direction);

    protected void scrollDueToClickInTrack(int dir);

    protected int xPositionForValue(int value);

    protected int yPositionForValue(int value);

    protected int yPositionForValue(int value, int trackY, int trackHeight);

    public int valueForYPosition(int yPos);

    public int valueForXPosition(int xPos);

    public class ChangeHandler implements ChangeListener {

        public void stateChanged(ChangeEvent e);
    }

    public class TrackListener extends MouseInputAdapter {

        protected transient int offset;

        protected transient int currentMouseX;

        protected transient int currentMouseY;

        public void mouseReleased(MouseEvent e);

        public void mousePressed(MouseEvent e);

        public boolean shouldScroll(int direction);

        public void mouseDragged(MouseEvent e);

        public void mouseMoved(MouseEvent e);
    }

    public class ScrollListener implements ActionListener {

        public ScrollListener() {
        }

        public ScrollListener(int dir, boolean block) {
        }

        public void setDirection(int direction);

        public void setScrollByBlock(boolean block);

        public void actionPerformed(ActionEvent e);
    }

    public class ComponentHandler extends ComponentAdapter {

        public void componentResized(ComponentEvent e);
    }

    public class FocusHandler implements FocusListener {

        public void focusGained(FocusEvent e);

        public void focusLost(FocusEvent e);
    }

    @SuppressWarnings("serial")
    public class ActionScroller extends AbstractAction {

        public ActionScroller(JSlider slider, int dir, boolean block) {
        }

        public void actionPerformed(ActionEvent e);

        public boolean isEnabled();
    }
}
