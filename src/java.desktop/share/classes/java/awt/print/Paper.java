package java.awt.print;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.Rectangle2D;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Paper implements Cloneable {

    public Paper() {
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
