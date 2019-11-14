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

    private static final long serialVersionUID = -6460061437900069969L;

    private static final int MIN_LENGTH = 4;

    public Polygon() {
        xpoints = new int[MIN_LENGTH];
        ypoints = new int[MIN_LENGTH];
    }

    public Polygon(int[] xpoints, int[] ypoints, int npoints) {
        if (npoints > xpoints.length || npoints > ypoints.length) {
            throw new IndexOutOfBoundsException("npoints > xpoints.length || " + "npoints > ypoints.length");
        }
        if (npoints < 0) {
            throw new NegativeArraySizeException("npoints < 0");
        }
        this.npoints = npoints;
        this.xpoints = Arrays.copyOf(xpoints, npoints);
        this.ypoints = Arrays.copyOf(ypoints, npoints);
    }

    public void reset();

    public void invalidate();

    public void translate(int deltaX, int deltaY);

    void calculateBounds(int[] xpoints, int[] ypoints, int npoints);

    void updateBounds(int x, int y);

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

    private Crossings getCrossings(double xlo, double ylo, double xhi, double yhi);

    public boolean contains(Point2D p);

    public boolean intersects(double x, double y, double w, double h);

    public boolean intersects(Rectangle2D r);

    public boolean contains(double x, double y, double w, double h);

    public boolean contains(Rectangle2D r);

    public PathIterator getPathIterator(AffineTransform at);

    public PathIterator getPathIterator(AffineTransform at, double flatness);

    class PolygonPathIterator implements PathIterator {

        Polygon poly;

        AffineTransform transform;

        int index;

        public PolygonPathIterator(Polygon pg, AffineTransform at) {
            poly = pg;
            transform = at;
            if (pg.npoints == 0) {
                index = 1;
            }
        }

        public int getWindingRule();

        public boolean isDone();

        public void next();

        public int currentSegment(float[] coords);

        public int currentSegment(double[] coords);
    }
}
