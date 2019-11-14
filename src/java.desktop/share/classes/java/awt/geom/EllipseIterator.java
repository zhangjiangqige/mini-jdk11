package java.awt.geom;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class EllipseIterator implements PathIterator {

    double x, y, w, h;

    AffineTransform affine;

    int index;

    EllipseIterator(Ellipse2D e, AffineTransform at) {
        this.x = e.getX();
        this.y = e.getY();
        this.w = e.getWidth();
        this.h = e.getHeight();
        this.affine = at;
        if (w < 0 || h < 0) {
            index = 6;
        }
    }

    public int getWindingRule();

    public boolean isDone();

    public void next();

    public static final double CtrlVal = 0.5522847498307933;

    private static final double pcv = 0.5 + CtrlVal * 0.5;

    private static final double ncv = 0.5 - CtrlVal * 0.5;

    private static double[][] ctrlpts = { { 1.0, pcv, pcv, 1.0, 0.5, 1.0 }, { ncv, 1.0, 0.0, pcv, 0.0, 0.5 }, { 0.0, ncv, ncv, 0.0, 0.5, 0.0 }, { pcv, 0.0, 1.0, ncv, 1.0, 0.5 } };

    public int currentSegment(float[] coords);

    public int currentSegment(double[] coords);
}
