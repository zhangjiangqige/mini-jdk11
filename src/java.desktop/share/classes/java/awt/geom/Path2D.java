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

    public static final int WIND_EVEN_ODD = PathIterator.WIND_EVEN_ODD;

    public static final int WIND_NON_ZERO = PathIterator.WIND_NON_ZERO;

    private static final byte SEG_MOVETO = (byte) PathIterator.SEG_MOVETO;

    private static final byte SEG_LINETO = (byte) PathIterator.SEG_LINETO;

    private static final byte SEG_QUADTO = (byte) PathIterator.SEG_QUADTO;

    private static final byte SEG_CUBICTO = (byte) PathIterator.SEG_CUBICTO;

    private static final byte SEG_CLOSE = (byte) PathIterator.SEG_CLOSE;

    transient byte[] pointTypes;

    transient int numTypes;

    transient int numCoords;

    transient int windingRule;

    static final int INIT_SIZE = 20;

    static final int EXPAND_MAX = 500;

    static final int EXPAND_MAX_COORDS = EXPAND_MAX * 2;

    static final int EXPAND_MIN = 10;

    Path2D() {
    }

    Path2D(int rule, int initialTypes) {
        setWindingRule(rule);
        this.pointTypes = new byte[initialTypes];
    }

    abstract float[] cloneCoordsFloat(AffineTransform at);

    abstract double[] cloneCoordsDouble(AffineTransform at);

    abstract void append(float x, float y);

    abstract void append(double x, double y);

    abstract Point2D getPoint(int coordindex);

    abstract void needRoom(boolean needMove, int newCoords);

    abstract int pointCrossings(double px, double py);

    abstract int rectCrossings(double rxmin, double rymin, double rxmax, double rymax);

    static byte[] expandPointTypes(byte[] oldPointTypes, int needed);

    public static class Float extends Path2D implements Serializable {

        transient float[] floatCoords;

        public Float() {
            this(WIND_NON_ZERO, INIT_SIZE);
        }

        public Float(int rule) {
            this(rule, INIT_SIZE);
        }

        public Float(int rule, int initialCapacity) {
            super(rule, initialCapacity);
            floatCoords = new float[initialCapacity * 2];
        }

        public Float(Shape s) {
            this(s, null);
        }

        public Float(Shape s, AffineTransform at) {
            if (s instanceof Path2D) {
                Path2D p2d = (Path2D) s;
                setWindingRule(p2d.windingRule);
                this.numTypes = p2d.numTypes;
                this.pointTypes = Arrays.copyOf(p2d.pointTypes, p2d.numTypes);
                this.numCoords = p2d.numCoords;
                this.floatCoords = p2d.cloneCoordsFloat(at);
            } else {
                PathIterator pi = s.getPathIterator(at);
                setWindingRule(pi.getWindingRule());
                this.pointTypes = new byte[INIT_SIZE];
                this.floatCoords = new float[INIT_SIZE * 2];
                append(pi, false);
            }
        }

        @Override
        public final void trimToSize();

        @Override
        float[] cloneCoordsFloat(AffineTransform at);

        @Override
        double[] cloneCoordsDouble(AffineTransform at);

        void append(float x, float y);

        void append(double x, double y);

        Point2D getPoint(int coordindex);

        @Override
        void needRoom(boolean needMove, int newCoords);

        static float[] expandCoords(float[] oldCoords, int needed);

        public final synchronized void moveTo(double x, double y);

        public final synchronized void moveTo(float x, float y);

        public final synchronized void lineTo(double x, double y);

        public final synchronized void lineTo(float x, float y);

        public final synchronized void quadTo(double x1, double y1, double x2, double y2);

        public final synchronized void quadTo(float x1, float y1, float x2, float y2);

        public final synchronized void curveTo(double x1, double y1, double x2, double y2, double x3, double y3);

        public final synchronized void curveTo(float x1, float y1, float x2, float y2, float x3, float y3);

        int pointCrossings(double px, double py);

        int rectCrossings(double rxmin, double rymin, double rxmax, double rymax);

        public final void append(PathIterator pi, boolean connect);

        public final void transform(AffineTransform at);

        public final synchronized Rectangle2D getBounds2D();

        public final PathIterator getPathIterator(AffineTransform at);

        public final Object clone();

        private static final long serialVersionUID = 6990832515060788886L;

        private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

        private void readObject(java.io.ObjectInputStream s) throws java.lang.ClassNotFoundException, java.io.IOException;

        static class CopyIterator extends Path2D.Iterator {

            float[] floatCoords;

            CopyIterator(Path2D.Float p2df) {
                super(p2df);
                this.floatCoords = p2df.floatCoords;
            }

            public int currentSegment(float[] coords);

            public int currentSegment(double[] coords);
        }

        static class TxIterator extends Path2D.Iterator {

            float[] floatCoords;

            AffineTransform affine;

            TxIterator(Path2D.Float p2df, AffineTransform at) {
                super(p2df);
                this.floatCoords = p2df.floatCoords;
                this.affine = at;
            }

            public int currentSegment(float[] coords);

            public int currentSegment(double[] coords);
        }
    }

    public static class Double extends Path2D implements Serializable {

        transient double[] doubleCoords;

        public Double() {
            this(WIND_NON_ZERO, INIT_SIZE);
        }

        public Double(int rule) {
            this(rule, INIT_SIZE);
        }

        public Double(int rule, int initialCapacity) {
            super(rule, initialCapacity);
            doubleCoords = new double[initialCapacity * 2];
        }

        public Double(Shape s) {
            this(s, null);
        }

        public Double(Shape s, AffineTransform at) {
            if (s instanceof Path2D) {
                Path2D p2d = (Path2D) s;
                setWindingRule(p2d.windingRule);
                this.numTypes = p2d.numTypes;
                this.pointTypes = Arrays.copyOf(p2d.pointTypes, p2d.numTypes);
                this.numCoords = p2d.numCoords;
                this.doubleCoords = p2d.cloneCoordsDouble(at);
            } else {
                PathIterator pi = s.getPathIterator(at);
                setWindingRule(pi.getWindingRule());
                this.pointTypes = new byte[INIT_SIZE];
                this.doubleCoords = new double[INIT_SIZE * 2];
                append(pi, false);
            }
        }

        @Override
        public final void trimToSize();

        @Override
        float[] cloneCoordsFloat(AffineTransform at);

        @Override
        double[] cloneCoordsDouble(AffineTransform at);

        void append(float x, float y);

        void append(double x, double y);

        Point2D getPoint(int coordindex);

        @Override
        void needRoom(boolean needMove, int newCoords);

        static double[] expandCoords(double[] oldCoords, int needed);

        public final synchronized void moveTo(double x, double y);

        public final synchronized void lineTo(double x, double y);

        public final synchronized void quadTo(double x1, double y1, double x2, double y2);

        public final synchronized void curveTo(double x1, double y1, double x2, double y2, double x3, double y3);

        int pointCrossings(double px, double py);

        int rectCrossings(double rxmin, double rymin, double rxmax, double rymax);

        public final void append(PathIterator pi, boolean connect);

        public final void transform(AffineTransform at);

        public final synchronized Rectangle2D getBounds2D();

        public final PathIterator getPathIterator(AffineTransform at);

        public final Object clone();

        private static final long serialVersionUID = 1826762518450014216L;

        private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException;

        private void readObject(java.io.ObjectInputStream s) throws java.lang.ClassNotFoundException, java.io.IOException;

        static class CopyIterator extends Path2D.Iterator {

            double[] doubleCoords;

            CopyIterator(Path2D.Double p2dd) {
                super(p2dd);
                this.doubleCoords = p2dd.doubleCoords;
            }

            public int currentSegment(float[] coords);

            public int currentSegment(double[] coords);
        }

        static class TxIterator extends Path2D.Iterator {

            double[] doubleCoords;

            AffineTransform affine;

            TxIterator(Path2D.Double p2dd, AffineTransform at) {
                super(p2dd);
                this.doubleCoords = p2dd.doubleCoords;
                this.affine = at;
            }

            public int currentSegment(float[] coords);

            public int currentSegment(double[] coords);
        }
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

    private static final byte SERIAL_STORAGE_FLT_ARRAY = 0x30;

    private static final byte SERIAL_STORAGE_DBL_ARRAY = 0x31;

    private static final byte SERIAL_SEG_FLT_MOVETO = 0x40;

    private static final byte SERIAL_SEG_FLT_LINETO = 0x41;

    private static final byte SERIAL_SEG_FLT_QUADTO = 0x42;

    private static final byte SERIAL_SEG_FLT_CUBICTO = 0x43;

    private static final byte SERIAL_SEG_DBL_MOVETO = 0x50;

    private static final byte SERIAL_SEG_DBL_LINETO = 0x51;

    private static final byte SERIAL_SEG_DBL_QUADTO = 0x52;

    private static final byte SERIAL_SEG_DBL_CUBICTO = 0x53;

    private static final byte SERIAL_SEG_CLOSE = 0x60;

    private static final byte SERIAL_PATH_END = 0x61;

    final void writeObject(java.io.ObjectOutputStream s, boolean isdbl) throws java.io.IOException;

    final void readObject(java.io.ObjectInputStream s, boolean storedbl) throws java.lang.ClassNotFoundException, java.io.IOException;

    abstract static class Iterator implements PathIterator {

        int typeIdx;

        int pointIdx;

        Path2D path;

        static final int[] curvecoords = { 2, 2, 4, 6, 0 };

        Iterator(Path2D path) {
            this.path = path;
        }

        public int getWindingRule();

        public boolean isDone();

        public void next();
    }
}
