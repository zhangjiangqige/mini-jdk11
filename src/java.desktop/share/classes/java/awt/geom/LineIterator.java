package java.awt.geom;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class LineIterator implements PathIterator {

    Line2D line;

    AffineTransform affine;

    int index;

    LineIterator(Line2D l, AffineTransform at) {
        this.line = l;
        this.affine = at;
    }

    public int getWindingRule();

    public boolean isDone();

    public void next();

    public int currentSegment(float[] coords);

    public int currentSegment(double[] coords);
}
