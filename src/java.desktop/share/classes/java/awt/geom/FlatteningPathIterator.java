package java.awt.geom;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class FlatteningPathIterator implements PathIterator {

    public FlatteningPathIterator(PathIterator src, double flatness) {
    }

    public FlatteningPathIterator(PathIterator src, double flatness, int limit) {
    }

    public double getFlatness();

    public int getRecursionLimit();

    public int getWindingRule();

    public boolean isDone();

    public void next();

    public int currentSegment(float[] coords);

    public int currentSegment(double[] coords);
}
