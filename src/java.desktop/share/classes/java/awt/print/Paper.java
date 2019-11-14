package java.awt.print;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.Rectangle2D;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Paper implements Cloneable {

    private static final int INCH = 72;

    private static final double LETTER_WIDTH = 8.5 * INCH;

    private static final double LETTER_HEIGHT = 11 * INCH;

    private double mHeight;

    private double mWidth;

    private Rectangle2D mImageableArea;

    public Paper() {
        mHeight = LETTER_HEIGHT;
        mWidth = LETTER_WIDTH;
        mImageableArea = new Rectangle2D.Double(INCH, INCH, mWidth - 2 * INCH, mHeight - 2 * INCH);
    }

    public Object clone();

    public double getHeight();

    public void setSize(double width, double height);

    public double getWidth();

    public void setImageableArea(double x, double y, double width, double height);

    public double getImageableX();

    public double getImageableY();

    public double getImageableWidth();

    public double getImageableHeight();
}
