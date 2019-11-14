package java.awt.geom;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class ArcIterator implements PathIterator {

    double x, y, w, h, angStRad, increment, cv;

    AffineTransform affine;

    int index;

    int arcSegs;

    int lineSegs;

    ArcIterator(Arc2D a, AffineTransform at) {
        this.w = a.getWidth() / 2;
        this.h = a.getHeight() / 2;
        this.x = a.getX() + w;
        this.y = a.getY() + h;
        this.angStRad = -Math.toRadians(a.getAngleStart());
        this.affine = at;
        double ext = -a.getAngleExtent();
        if (ext >= 360.0 || ext <= -360) {
            arcSegs = 4;
            this.increment = Math.PI / 2;
            this.cv = 0.5522847498307933;
            if (ext < 0) {
                increment = -increment;
                cv = -cv;
            }
        } else {
            arcSegs = (int) Math.ceil(Math.abs(ext) / 90.0);
            this.increment = Math.toRadians(ext / arcSegs);
            this.cv = btan(increment);
            if (cv == 0) {
                arcSegs = 0;
            }
        }
        switch(a.getArcType()) {
            case Arc2D.OPEN:
                lineSegs = 0;
                break;
            case Arc2D.CHORD:
                lineSegs = 1;
                break;
            case Arc2D.PIE:
                lineSegs = 2;
                break;
        }
        if (w < 0 || h < 0) {
            arcSegs = lineSegs = -1;
        }
    }

    public int getWindingRule();

    public boolean isDone();

    public void next();

    private static double btan(double increment);

    public int currentSegment(float[] coords);

    public int currentSegment(double[] coords);
}
