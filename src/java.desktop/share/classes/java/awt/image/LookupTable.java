package java.awt.image;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class LookupTable {

    protected LookupTable(int offset, int numComponents) {
    }

    public int getNumComponents();

    public int getOffset();

    public abstract int[] lookupPixel(int[] src, int[] dest);
}
