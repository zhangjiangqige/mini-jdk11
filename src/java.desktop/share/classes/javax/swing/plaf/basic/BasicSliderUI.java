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

    private static final Actions SHARED_ACTION = new Actions();

    public static final int POSITIVE_SCROLL = +1;

    public static final int NEGATIVE_SCROLL = -1;

    public static final int MIN_SCROLL = -2;

    public static final int MAX_SCROLL = +2;

    protected Timer scrollTimer;

    protected JSlider slider;

    protected Insets focusInsets = null;

    protected Insets insetCache = null;

    protected boolean leftToRightCache = true;

    protected Rectangle focusRect = null;

    protected Rectangle contentRect = null;

    protected Rectangle labelRect = null;

    protected Rectangle tickRect = null;

    protected Rectangle trackRect = null;

    protected Rectangle thumbRect = null;

    protected int trackBuffer = 0;

    private transient boolean isDragging;

    protected TrackListener trackListener;

    protected ChangeListener changeListener;

    protected ComponentListener componentListener;

    protected FocusListener focusListener;

    protected ScrollListener scrollListener;

    protected PropertyChangeListener propertyChangeListener;

    private Handler handler;

    private int lastValue;

    private Color shadowColor;

    private Color highlightColor;

    private Color focusColor;

    private boolean checkedLabelBaselines;

    private boolean sameLabelBaselines;

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

    private Handler getHandler();

    protected void installListeners(JSlider slider);

    protected void uninstallListeners(JSlider slider);

    protected void installKeyboardActions(JSlider slider);

    InputMap getInputMap(int condition, JSlider slider);

    static void loadActionMap(LazyActionMap map);

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

    private int getTickSpacing();

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

    private static Rectangle unionRect = new Rectangle();

    public void setThumbLocation(int x, int y);

    public void scrollByBlock(int direction);

    public void scrollByUnit(int direction);

    protected void scrollDueToClickInTrack(int dir);

    protected int xPositionForValue(int value);

    protected int yPositionForValue(int value);

    protected int yPositionForValue(int value, int trackY, int trackHeight);

    public int valueForYPosition(int yPos);

    public int valueForXPosition(int xPos);

    private class Handler implements ChangeListener, ComponentListener, FocusListener, PropertyChangeListener {

        public void stateChanged(ChangeEvent e);

        public void componentHidden(ComponentEvent e);

        public void componentMoved(ComponentEvent e);

        public void componentResized(ComponentEvent e);

        public void componentShown(ComponentEvent e);

        public void focusGained(FocusEvent e);

        public void focusLost(FocusEvent e);

        public void propertyChange(PropertyChangeEvent e);
    }

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

        int direction = POSITIVE_SCROLL;

        boolean useBlockIncrement;

        public ScrollListener() {
            direction = POSITIVE_SCROLL;
            useBlockIncrement = true;
        }

        public ScrollListener(int dir, boolean block) {
            direction = dir;
            useBlockIncrement = block;
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

        int dir;

        boolean block;

        JSlider slider;

        public ActionScroller(JSlider slider, int dir, boolean block) {
            this.dir = dir;
            this.block = block;
            this.slider = slider;
        }

        public void actionPerformed(ActionEvent e);

        public boolean isEnabled();
    }

    @SuppressWarnings("serial")
    static class SharedActionScroller extends AbstractAction {

        int dir;

        boolean block;

        public SharedActionScroller(int dir, boolean block) {
            this.dir = dir;
            this.block = block;
        }

        public void actionPerformed(ActionEvent evt);
    }

    private static class Actions extends UIAction {

        public static final String POSITIVE_UNIT_INCREMENT = "positiveUnitIncrement";

        public static final String POSITIVE_BLOCK_INCREMENT = "positiveBlockIncrement";

        public static final String NEGATIVE_UNIT_INCREMENT = "negativeUnitIncrement";

        public static final String NEGATIVE_BLOCK_INCREMENT = "negativeBlockIncrement";

        @Interned
        public static final String MIN_SCROLL_INCREMENT = "minScroll";

        @Interned
        public static final String MAX_SCROLL_INCREMENT = "maxScroll";

        Actions() {
            super(null);
        }

        public Actions(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent evt);

        private void scroll(JSlider slider, BasicSliderUI ui, int direction, boolean isBlock);
    }
}
