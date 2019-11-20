package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import sun.awt.geom.Crossings;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Polygon implements Shape, java.io.Serializable {

    public int npoints;

    public int[] xpoints;

    public int[] ypoints;

    protected Rectangle bounds;

    public Polygon() {
    }

    public Polygon(int[] xpoints, int[] ypoints, int npoints) {
    }

    public void reset();

    public void invalidate();

    public void translate(int deltaX, int deltaY);

    public void addPoint(int x, int y);

    public Rectangle getBounds();

    @Deprecated
    public Rectangle getBoundingBox();

    public boolean contains(Point p);

    public boolean contains(int x, int y);

    @Deprecated
    public boolean inside(int x, int y);

    public Rectangle2D getBounds2D();

    public boolean contains(double x, double y);

    public boolean contains(Point2D p);

    public boolean intersects(double x, double y, double w, double h);

    public boolean intersects(Rectangle2D r);

    public boolean contains(double x, double y, double w, double h);

    public boolean contains(Rectangle2D r);

    public PathIterator getPathIterator(AffineTransform at);

    public PathIterator getPathIterator(AffineTransform at, double flatness);
}
