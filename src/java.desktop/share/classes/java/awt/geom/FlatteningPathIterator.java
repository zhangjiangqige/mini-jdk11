package java.awt.geom;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class FlatteningPathIterator implements PathIterator {

    static final int GROW_SIZE = 24;

    PathIterator src;

    double squareflat;

    int limit;

    double[] hold = new double[14];

    double curx, cury;

    double movx, movy;

    int holdType;

    int holdEnd;

    int holdIndex;

    int[] levels;

    int levelIndex;

    boolean done;

    public FlatteningPathIterator(PathIterator src, double flatness) {
        this(src, flatness, 10);
    }

    public FlatteningPathIterator(PathIterator src, double flatness, int limit) {
        if (flatness < 0.0) {
            throw new IllegalArgumentException("flatness must be >= 0");
        }
        if (limit < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        }
        this.src = src;
        this.squareflat = flatness * flatness;
        this.limit = limit;
        this.levels = new int[limit + 1];
        next(false);
    }

    public double getFlatness();

    public int getRecursionLimit();

    public int getWindingRule();

    public boolean isDone();

    void ensureHoldCapacity(int want);

    public void next();

    private void next(boolean doNext);

    public int currentSegment(float[] coords);

    public int currentSegment(double[] coords);
}
