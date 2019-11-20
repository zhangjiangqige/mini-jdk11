package java.awt.print;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.annotation.Native;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class PageFormat implements Cloneable {

    @Native
    public static final int LANDSCAPE;

    @Native
    public static final int PORTRAIT;

    @Native
    public static final int REVERSE_LANDSCAPE;

    public PageFormat() {
    }

    public Object clone();

    public double getWidth();

    public double getHeight();

    public double getImageableX();

    public double getImageableY();

    public double getImageableWidth();

    public double getImageableHeight();

    public Paper getPaper();

    public void setPaper(Paper paper);

    public void setOrientation(int orientation) throws IllegalArgumentException;

    public int getOrientation();

    public double[] getMatrix();
}
