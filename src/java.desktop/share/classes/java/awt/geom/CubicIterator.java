package java.awt.geom;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class CubicIterator implements PathIterator {

    CubicCurve2D cubic;

    AffineTransform affine;

    int index;

    CubicIterator(CubicCurve2D q, AffineTransform at) {
        this.cubic = q;
        this.affine = at;
    }

    public int getWindingRule();

    public boolean isDone();

    public void next();

    public int currentSegment(float[] coords);

    public int currentSegment(double[] coords);
}
