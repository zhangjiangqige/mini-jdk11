package java.awt.font;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.Point2D;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class LayoutPath {

    public abstract boolean pointToPath(Point2D point, Point2D location);

    public abstract void pathToPoint(Point2D location, boolean preceding, Point2D point);
}
