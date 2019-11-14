package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class PrintJob {

    public abstract Graphics getGraphics();

    public abstract Dimension getPageDimension();

    public abstract int getPageResolution();

    public abstract boolean lastPageFirst();

    public abstract void end();

    @Deprecated(since = "9")
    public void finalize();
}
