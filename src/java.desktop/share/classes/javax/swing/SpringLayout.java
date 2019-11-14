package javax.swing;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.*;

@AnnotatedFor({ "interning" })
@SuppressWarnings("serial")
public class SpringLayout implements LayoutManager2 {

    private Map<Component, Constraints> componentConstraints = new HashMap<Component, Constraints>();

    private Spring cyclicReference = Spring.constant(Spring.UNSET);

    private Set<Spring> cyclicSprings;

    private Set<Spring> acyclicSprings;

    @Interned
    public static final String NORTH = "North";

    @Interned
    public static final String SOUTH = "South";

    @Interned
    public static final String EAST = "East";

    @Interned
    public static final String WEST = "West";

    @Interned
    public static final String HORIZONTAL_CENTER = "HorizontalCenter";

    @Interned
    public static final String VERTICAL_CENTER = "VerticalCenter";

    @Interned
    public static final String BASELINE = "Baseline";

    @Interned
    public static final String WIDTH = "Width";

    @Interned
    public static final String HEIGHT = "Height";

    private static String[] ALL_HORIZONTAL = { WEST, WIDTH, EAST, HORIZONTAL_CENTER };

    private static String[] ALL_VERTICAL = { NORTH, HEIGHT, SOUTH, VERTICAL_CENTER, BASELINE };

    public static class Constraints {

        private Spring x;

        private Spring y;

        private Spring width;

        private Spring height;

        private Spring east;

        private Spring south;

        private Spring horizontalCenter;

        private Spring verticalCenter;

        private Spring baseline;

        private List<String> horizontalHistory = new ArrayList<String>(2);

        private List<String> verticalHistory = new ArrayList<String>(2);

        private Component c;

        public Constraints() {
        }

        public Constraints(Spring x, Spring y) {
            setX(x);
            setY(y);
        }

        public Constraints(Spring x, Spring y, Spring width, Spring height) {
            setX(x);
            setY(y);
            setWidth(width);
            setHeight(height);
        }

        public Constraints(Component c) {
            this.c = c;
            setX(Spring.constant(c.getX()));
            setY(Spring.constant(c.getY()));
            setWidth(Spring.width(c));
            setHeight(Spring.height(c));
        }

        private void pushConstraint(String name, Spring value, boolean horizontal);

        private Spring sum(Spring s1, Spring s2);

        private Spring difference(Spring s1, Spring s2);

        private Spring scale(Spring s, float factor);

        private int getBaselineFromHeight(int height);

        private int getHeightFromBaseLine(int baseline);

        private Spring heightToRelativeBaseline(Spring s);

        private Spring relativeBaselineToHeight(Spring s);

        private boolean defined(List<?> history, String s1, String s2);

        public void setX(Spring x);

        public Spring getX();

        public void setY(Spring y);

        public Spring getY();

        public void setWidth(Spring width);

        public Spring getWidth();

        public void setHeight(Spring height);

        public Spring getHeight();

        private void setEast(Spring east);

        private Spring getEast();

        private void setSouth(Spring south);

        private Spring getSouth();

        private Spring getHorizontalCenter();

        private void setHorizontalCenter(Spring horizontalCenter);

        private Spring getVerticalCenter();

        private void setVerticalCenter(Spring verticalCenter);

        private Spring getBaseline();

        private void setBaseline(Spring baseline);

        public void setConstraint(String edgeName, Spring s);

        public Spring getConstraint(String edgeName);

        void reset();
    }

    private static class SpringProxy extends Spring {

        private String edgeName;

        private Component c;

        private SpringLayout l;

        public SpringProxy(String edgeName, Component c, SpringLayout l) {
            this.edgeName = edgeName;
            this.c = c;
            this.l = l;
        }

        private Spring getConstraint();

        public int getMinimumValue();

        public int getPreferredValue();

        public int getMaximumValue();

        public int getValue();

        public void setValue(int size);

        boolean isCyclic(SpringLayout l);

        public String toString();
    }

    public SpringLayout() {
    }

    private void resetCyclicStatuses();

    private void setParent(Container p);

    boolean isCyclic(Spring s);

    private Spring abandonCycles(Spring s);

    public void addLayoutComponent(String name, Component c);

    public void removeLayoutComponent(Component c);

    private static Dimension addInsets(int width, int height, Container p);

    public Dimension minimumLayoutSize(Container parent);

    public Dimension preferredLayoutSize(Container parent);

    public Dimension maximumLayoutSize(Container parent);

    public void addLayoutComponent(Component component, Object constraints);

    public float getLayoutAlignmentX(Container p);

    public float getLayoutAlignmentY(Container p);

    public void invalidateLayout(Container p);

    public void putConstraint(String e1, Component c1, int pad, String e2, Component c2);

    public void putConstraint(String e1, Component c1, Spring s, String e2, Component c2);

    private void putConstraint(String e, Component c, Spring s);

    private Constraints applyDefaults(Component c, Constraints constraints);

    private void applyDefaults(Constraints constraints, String name1, Spring spring1, String name2, Spring spring2, List<String> history);

    private void putConstraints(Component component, Constraints constraints);

    public Constraints getConstraints(Component c);

    public Spring getConstraint(String edgeName, Component c);

    public void layoutContainer(Container parent);
}
