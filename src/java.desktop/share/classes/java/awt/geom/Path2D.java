package java.awt.geom;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Arrays;
import sun.awt.geom.Curve;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class Path2D implements Shape, Cloneable {

    public static final int WIND_EVEN_ODD;

    public static final int WIND_NON_ZERO;

    public static class Float extends Path2D implements Serializable {

        public Float() {
        }

        public Float(int rule) {
        }

        public Float(int rule, int initialCapacity) {
        }

        public Float(Shape s) {
        }

        public Float(Shape s, AffineTransform at) {
        }

        @Override
        public final void trimToSize();

        public final synchronized void moveTo(double x, double y);

        public final synchronized void moveTo(float x, float y);

        public final synchronized void lineTo(double x, double y);

        public final synchronized void lineTo(float x, float y);

        public final synchronized void quadTo(double x1, double y1, double x2, double y2);

        public final synchronized void quadTo(float x1, float y1, float x2, float y2);

        public final synchronized void curveTo(double x1, double y1, double x2, double y2, double x3, double y3);

        public final synchronized void curveTo(float x1, float y1, float x2, float y2, float x3, float y3);

        public final void append(PathIterator pi, boolean connect);

        public final void transform(AffineTransform at);

        public final synchronized Rectangle2D getBounds2D();

        public final PathIterator getPathIterator(AffineTransform at);

        public final Object clone();
    }

    public static class Double extends Path2D implements Serializable {

        public Double() {
        }

        public Double(int rule) {
        }

        public Double(int rule, int initialCapacity) {
        }

        public Double(Shape s) {
        }

        public Double(Shape s, AffineTransform at) {
        }

        @Override
        public final void trimToSize();

        public final synchronized void moveTo(double x, double y);

        public final synchronized void lineTo(double x, double y);

        public final synchronized void quadTo(double x1, double y1, double x2, double y2);

        public final synchronized void curveTo(double x1, double y1, double x2, double y2, double x3, double y3);

        public final void append(PathIterator pi, boolean connect);

        public final void transform(AffineTransform at);

        public final synchronized Rectangle2D getBounds2D();

        public final PathIterator getPathIterator(AffineTransform at);

        public final Object clone();
    }

    public abstract void moveTo(double x, double y);

    public abstract void lineTo(double x, double y);

    public abstract void quadTo(double x1, double y1, double x2, double y2);

    public abstract void curveTo(double x1, double y1, double x2, double y2, double x3, double y3);

    public final synchronized void closePath();

    public final void append(Shape s, boolean connect);

    public abstract void append(PathIterator pi, boolean connect);

    public final synchronized int getWindingRule();

    public final void setWindingRule(int rule);

    public final synchronized Point2D getCurrentPoint();

    public final synchronized void reset();

    public abstract void transform(AffineTransform at);

    public final synchronized Shape createTransformedShape(AffineTransform at);

    public final Rectangle getBounds();

    public static boolean contains(PathIterator pi, double x, double y);

    public static boolean contains(PathIterator pi, Point2D p);

    public final boolean contains(double x, double y);

    public final boolean contains(Point2D p);

    public static boolean contains(PathIterator pi, double x, double y, double w, double h);

    public static boolean contains(PathIterator pi, Rectangle2D r);

    public final boolean contains(double x, double y, double w, double h);

    public final boolean contains(Rectangle2D r);

    public static boolean intersects(PathIterator pi, double x, double y, double w, double h);

    public static boolean intersects(PathIterator pi, Rectangle2D r);

    public final boolean intersects(double x, double y, double w, double h);

    public final boolean intersects(Rectangle2D r);

    public final PathIterator getPathIterator(AffineTransform at, double flatness);

    public abstract Object clone();

    public abstract void trimToSize();
}
