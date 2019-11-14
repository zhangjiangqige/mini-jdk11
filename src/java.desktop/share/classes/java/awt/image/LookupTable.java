package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class LookupTable {

    int numComponents;

    int offset;

    int numEntries;

    protected LookupTable(int offset, int numComponents) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset must be greater than 0");
        }
        if (numComponents < 1) {
            throw new IllegalArgumentException("Number of components must " + " be at least 1");
        }
        this.numComponents = numComponents;
        this.offset = offset;
    }

    public int getNumComponents();

    public int getOffset();

    public abstract int[] lookupPixel(int[] src, int[] dest);
}
