package java.awt.geom;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class QuadIterator implements PathIterator {

    QuadCurve2D quad;

    AffineTransform affine;

    int index;

    QuadIterator(QuadCurve2D q, AffineTransform at) {
        this.quad = q;
        this.affine = at;
    }

    public int getWindingRule();

    public boolean isDone();

    public void next();

    public int currentSegment(float[] coords);

    public int currentSegment(double[] coords);
}
