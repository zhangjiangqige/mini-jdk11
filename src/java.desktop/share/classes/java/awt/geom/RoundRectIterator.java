package java.awt.geom;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class RoundRectIterator implements PathIterator {

    double x, y, w, h, aw, ah;

    AffineTransform affine;

    int index;

    RoundRectIterator(RoundRectangle2D rr, AffineTransform at) {
        this.x = rr.getX();
        this.y = rr.getY();
        this.w = rr.getWidth();
        this.h = rr.getHeight();
        this.aw = Math.min(w, Math.abs(rr.getArcWidth()));
        this.ah = Math.min(h, Math.abs(rr.getArcHeight()));
        this.affine = at;
        if (aw < 0 || ah < 0) {
            index = ctrlpts.length;
        }
    }

    public int getWindingRule();

    public boolean isDone();

    public void next();

    private static final double angle = Math.PI / 4.0;

    private static final double a = 1.0 - Math.cos(angle);

    private static final double b = Math.tan(angle);

    private static final double c = Math.sqrt(1.0 + b * b) - 1 + a;

    private static final double cv = 4.0 / 3.0 * a * b / c;

    private static final double acv = (1.0 - cv) / 2.0;

    private static double[][] ctrlpts = { { 0.0, 0.0, 0.0, 0.5 }, { 0.0, 0.0, 1.0, -0.5 }, { 0.0, 0.0, 1.0, -acv, 0.0, acv, 1.0, 0.0, 0.0, 0.5, 1.0, 0.0 }, { 1.0, -0.5, 1.0, 0.0 }, { 1.0, -acv, 1.0, 0.0, 1.0, 0.0, 1.0, -acv, 1.0, 0.0, 1.0, -0.5 }, { 1.0, 0.0, 0.0, 0.5 }, { 1.0, 0.0, 0.0, acv, 1.0, -acv, 0.0, 0.0, 1.0, -0.5, 0.0, 0.0 }, { 0.0, 0.5, 0.0, 0.0 }, { 0.0, acv, 0.0, 0.0, 0.0, 0.0, 0.0, acv, 0.0, 0.0, 0.0, 0.5 }, {} };

    private static int[] types = { SEG_MOVETO, SEG_LINETO, SEG_CUBICTO, SEG_LINETO, SEG_CUBICTO, SEG_LINETO, SEG_CUBICTO, SEG_LINETO, SEG_CUBICTO, SEG_CLOSE };

    public int currentSegment(float[] coords);

    public int currentSegment(double[] coords);
}
