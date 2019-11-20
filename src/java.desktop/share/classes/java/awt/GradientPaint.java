package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.beans.ConstructorProperties;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class GradientPaint implements Paint {

    public GradientPaint(float x1, float y1, Color color1, float x2, float y2, Color color2) {
    }

    public GradientPaint(Point2D pt1, Color color1, Point2D pt2, Color color2) {
    }

    public GradientPaint(float x1, float y1, Color color1, float x2, float y2, Color color2, boolean cyclic) {
    }

    @ConstructorProperties({ "point1", "color1", "point2", "color2", "cyclic" })
    public GradientPaint(Point2D pt1, Color color1, Point2D pt2, Color color2, boolean cyclic) {
    }

    public Point2D getPoint1();

    public Color getColor1();

    public Point2D getPoint2();

    public Color getColor2();

    public boolean isCyclic();

    public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints);

    public int getTransparency();
}
