package javax.swing;

import org.checkerframework.checker.fenum.qual.*;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@AnnotatedFor("fenum")
public interface SwingConstants {

    @SwingCompassDirection
    @SwingHorizontalOrientation
    @SwingVerticalOrientation
    public static final int CENTER;

    @SwingVerticalOrientation
    public static final int TOP;

    @SwingHorizontalOrientation
    public static final int LEFT;

    @SwingVerticalOrientation
    public static final int BOTTOM;

    @SwingHorizontalOrientation
    public static final int RIGHT;

    @SwingCompassDirection
    public static final int NORTH;

    @SwingCompassDirection
    public static final int NORTH_EAST;

    @SwingCompassDirection
    public static final int EAST;

    @SwingCompassDirection
    public static final int SOUTH_EAST;

    @SwingCompassDirection
    public static final int SOUTH;

    @SwingCompassDirection
    public static final int SOUTH_WEST;

    @SwingCompassDirection
    public static final int WEST;

    @SwingCompassDirection
    public static final int NORTH_WEST;

    @SwingElementOrientation
    public static final int HORIZONTAL;

    @SwingElementOrientation
    public static final int VERTICAL;

    @SwingHorizontalOrientation
    @SwingTextOrientation
    public static final int LEADING;

    @SwingHorizontalOrientation
    @SwingTextOrientation
    public static final int TRAILING;

    @SwingTextOrientation
    public static final int NEXT;

    @SwingTextOrientation
    public static final int PREVIOUS;
}
