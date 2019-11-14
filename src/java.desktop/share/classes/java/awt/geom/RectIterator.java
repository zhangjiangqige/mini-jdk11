package java.awt.geom;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class RectIterator implements PathIterator {

    double x, y, w, h;

    AffineTransform affine;

    int index;

    RectIterator(Rectangle2D r, AffineTransform at) {
        this.x = r.getX();
        this.y = r.getY();
        this.w = r.getWidth();
        this.h = r.getHeight();
        this.affine = at;
        if (w < 0 || h < 0) {
            index = 6;
        }
    }

    public int getWindingRule();

    public boolean isDone();

    public void next();

    public int currentSegment(float[] coords);

    public int currentSegment(double[] coords);
}
