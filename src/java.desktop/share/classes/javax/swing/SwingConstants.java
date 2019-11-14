package javax.swing;

import org.checkerframework.checker.fenum.qual.*;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@AnnotatedFor("fenum")
public interface SwingConstants {

    @SwingCompassDirection
    @SwingHorizontalOrientation
    @SwingVerticalOrientation
    public static final int CENTER = 0;

    @SwingVerticalOrientation
    public static final int TOP = 1;

    @SwingHorizontalOrientation
    public static final int LEFT = 2;

    @SwingVerticalOrientation
    public static final int BOTTOM = 3;

    @SwingHorizontalOrientation
    public static final int RIGHT = 4;

    @SwingCompassDirection
    public static final int NORTH = 1;

    @SwingCompassDirection
    public static final int NORTH_EAST = 2;

    @SwingCompassDirection
    public static final int EAST = 3;

    @SwingCompassDirection
    public static final int SOUTH_EAST = 4;

    @SwingCompassDirection
    public static final int SOUTH = 5;

    @SwingCompassDirection
    public static final int SOUTH_WEST = 6;

    @SwingCompassDirection
    public static final int WEST = 7;

    @SwingCompassDirection
    public static final int NORTH_WEST = 8;

    @SwingElementOrientation
    public static final int HORIZONTAL = 0;

    @SwingElementOrientation
    public static final int VERTICAL = 1;

    @SwingHorizontalOrientation
    @SwingTextOrientation
    public static final int LEADING = 10;

    @SwingHorizontalOrientation
    @SwingTextOrientation
    public static final int TRAILING = 11;

    @SwingTextOrientation
    public static final int NEXT = 12;

    @SwingTextOrientation
    public static final int PREVIOUS = 13;
}
