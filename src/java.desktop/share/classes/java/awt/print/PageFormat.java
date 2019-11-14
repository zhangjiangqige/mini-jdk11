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
    public static final int LANDSCAPE = 0;

    @Native
    public static final int PORTRAIT = 1;

    @Native
    public static final int REVERSE_LANDSCAPE = 2;

    private Paper mPaper;

    private int mOrientation = PORTRAIT;

    public PageFormat() {
        mPaper = new Paper();
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
